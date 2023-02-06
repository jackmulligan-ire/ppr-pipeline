import csv
from dataclasses import field
import os
from datetime import datetime

# Third-party libs
import boto3
from sqlalchemy import create_engine, delete
from sqlalchemy.orm import Session

# App modules
import ppr_pipeline
from ppr_pipeline.loaders.orm_classes import Property_Transaction_Staging

class PPR_Loader():
    PPR_ALL_DIRTY_FILEPATH = 'tmp/PPR-ALL-dirty.csv'
    PPR_ALL_CLEAN_FILEPATH = 'tmp/PPR-ALL-clean.csv'

    @classmethod
    def load_ppr_data(cls):
        if not os.path.exists(cls.PPR_ALL_DIRTY_FILEPATH):
            cls._get_all_from_s3()
            cls._clean_csv_of_euro()
        ppr_data = cls._generate_iter_from_csv(cls.PPR_ALL_CLEAN_FILEPATH)
        cls._inject_data_to_staging(ppr_data)

    @classmethod
    def _get_all_from_s3(cls):
        s3 = boto3.client('s3')
        s3.download_file(ppr_pipeline.BUCKET_NAME, ppr_pipeline.ALL_OBJECT_NAME, cls.PPR_ALL_DIRTY_FILEPATH)

    @classmethod
    def _clean_csv_of_euro(cls):
        with open(cls.PPR_ALL_DIRTY_FILEPATH, 'r', encoding='windows-1252') as dirty_file, open(cls.PPR_ALL_CLEAN_FILEPATH, 'a') as clean_file:
            fieldnames = [
                'Date of Sale (dd/mm/yyyy)',
                'Address',
                'County',
                'Eircode',
                'Price',
                'Not Full Market Price',
                'VAT Exclusive',
                'Description of Property',
                'Property Size Description',
            ]
            
            reader = csv.DictReader(dirty_file)
            writer = csv.DictWriter(clean_file, fieldnames=fieldnames)
            
            if os.path.getsize(cls.PPR_ALL_CLEAN_FILEPATH) == 0:
                writer.writeheader()
            
            for row in reader:
                clean_row = {}
                for key in row.keys():
                    if key == 'Price (€)':
                        row[key] = row[key].replace('€', '')
                        clean_row['Price'] = row[key]
                    else:
                        clean_row[key] = row[key]
                writer.writerow(clean_row)

    @classmethod
    def _generate_iter_from_csv(cls, filename):
        try:
            with open(filename, 'r') as csv_file:
                reader = csv.DictReader(csv_file)
                return [row for row in reader]
        except FileNotFoundError:
            raise Exception("PPR_All file doesn't exist. Has it been downloaded?")

    @classmethod
    def _inject_data_to_staging(cls, ppr_rows):
        USER = os.environ.get('POSTGRES_USER')
        PASSWORD = os.environ.get('POSTGRES_PASSWORD')
        HOST = os.environ.get('DB_HOST')
        PORT = os.environ.get('DB_PORT')
        NAME = os.environ.get('DB_NAME')

        db_string = f"postgresql://{USER}:{PASSWORD}@{HOST}:{PORT}/{NAME}"
        engine = create_engine(db_string)
        
        with Session(engine) as session:
            session.execute(delete(Property_Transaction_Staging.metadata.tables["staging"]))
            session.commit()

            for data in ppr_rows:
                try:
                    prop_trans = Property_Transaction_Staging(
                        address = data['Address'],
                        county = data['County'],
                        eircode = data['Eircode'],
                        price = data['Price'],
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
                            data['Price'],
                        ])
                    session.rollback()

