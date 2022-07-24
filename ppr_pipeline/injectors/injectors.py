import csv
import os
from datetime import datetime

# Third-party libs
import boto3
from sqlalchemy import create_engine
from sqlalchemy.orm import Session

# App modules
import ppr_pipeline
from ppr_pipeline.injectors.orm_classes import Property_Transaction_Staging

class PPR_Hist_Injector():
    PPR_ALL_FILEPATH = 'tmp/PPR-ALL.csv'

    @classmethod
    def inject_ppr_data(cls):
        #cls._get_all_from_s3()
        ppr_data = cls._generate_iter_from_csv(cls.PPR_ALL_FILEPATH)
        cls._inject_data_to_staging(ppr_data)

    @classmethod
    def _get_all_from_s3(cls):
        s3 = boto3.client('s3')
        s3.download_file(ppr_pipeline.BUCKET_NAME, ppr_pipeline.ALL_OBJECT_NAME, cls.PPR_ALL_FILEPATH)

    @classmethod
    def _generate_iter_from_csv(cls, filename):
        try:
            with open(filename) as csv_file:
                reader = csv.DictReader(csv_file)
                return [row for row in reader]
        except FileNotFoundError:
            raise Exception("PPR_All file doesn't exist. Has it been downloaded?")

    @classmethod
    def _inject_data_to_staging(cls, ppr_rows):
        USER = os.environ.get('DB_USER')
        PASSWORD = os.environ.get('DB_PASSWORD')
        HOST = os.environ.get('DB_HOST')
        PORT = os.environ.get('DB_PORT')
        NAME = os.environ.get('DB_NAME')

        db_string = f"postgresql://{USER}:{PASSWORD}@{HOST}:{PORT}/{NAME}"
        engine = create_engine(db_string)
        
        with Session(engine) as session:
            for data in ppr_rows:
                try:
                    prop_trans = Property_Transaction_Staging(
                        address = data['Address'],
                        county = data['County'],
                        eircode = data['Eircode'],
                        price = data['Price (�)'],
                        not_full_market_price = data['Not Full Market Price'],
                        vat_exclusive = data['VAT Exclusive'],
                        description_of_property = data['Description of Property'],
                        property_size_description = data['Property Size Description'],
                        date_of_sale = data['Date of Sale (dd/mm/yyyy)']
                    )
                    session.add(prop_trans)
                    session.commit()
                except Exception as e:
                    with open('logs/staging-table-error.csv', 'a') as csv_file:
                        writer = csv.writer(csv_file)

                        if os.path.getsize('logs/staging-table-error.csv') == 0:
                            writer.writerow([
                                'time_of_error',
                                'error',
                                'Date of Sale (dd/mm/yyyy)',
                                'Address',
                                'Price (�)',
                            ])
                        writer.writerow([
                            datetime.now().strftime("%d%m%y-%H:%M"),
                            e,
                            data['Date of Sale (dd/mm/yyyy)'],
                            data['Address'],
                            data['Price (�)'],
                        ])
                    session.rollback()