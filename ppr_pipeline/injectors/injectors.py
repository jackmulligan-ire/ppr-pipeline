import csv
import os

# Third-party libs
import boto3
from sqlalchemy import create_engine
from sqlalchemy.orm import Session

# App modules
import ppr_pipeline
from ppr_pipeline.injectors.orm_classes import Property_Transaction_Staging

class PPR_Hist_Injector():
    @classmethod
    def inject_ppr_data(cls):
        cls._get_all_from_s3()
        ppr_data = cls._generate_iter_from_csv('tmp/PPR-ALL.csv')
        cls._inject_data_to_staging(ppr_data)

    @classmethod
    def _get_all_from_s3(cls):
        s3 = boto3.client('s3')
        s3.download_file(ppr_pipeline.BUCKET_NAME, ppr_pipeline.ALL_OBJECT_NAME, 'tmp/PPR-ALL.csv')

    @classmethod
    def _generate_iter_from_csv(cls, filename):
        with open(filename) as csv_file:
            reader = csv.DictReader(csv_file)
            return [row for row in reader]

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