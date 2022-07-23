import unittest
import os
from unittest.mock import patch, call
from ppr_pipeline.injectors.injectors import PPR_Hist_Injector

class TestInjectors(unittest.TestCase):
    def test_get_from_s3(self):
        with patch('ppr_pipeline.injectors.injectors.boto3.client') as mocked_get:
            PPR_Hist_Injector._get_all_from_s3()
            mocked_get.assert_called_once()
    
    def test_generate_iter_from_csv(self):
        PPR_iter_result = PPR_Hist_Injector._generate_iter_from_csv('test/test-data/test.csv')
        PPR_iter_data = [{
            'Date of Sale (dd/mm/yyyy)': '01/01/2010',
            'Address' : '5 Braemor Drive, Churchtown, Co.Dublin',
            'County' : 'Dublin',
            'Eircode' : '',
            'Price (�)' : '�343,000.00',
            'Not Full Market Price' : 'No',
            'VAT Exclusive' : 'No',
            'Description of Property' : 'Second-Hand Dwelling house /Apartment',
            'Property Size Description': ''
        }]

        self.assertEqual(PPR_iter_result, PPR_iter_data)

    def test_create_engine(self):
        USER = os.environ.get('DB_USER')
        PASSWORD = os.environ.get('DB_PASSWORD')
        HOST = os.environ.get('DB_HOST')
        PORT = os.environ.get('DB_PORT')
        DBNAME = os.environ.get('DB_NAME')

        PPR_iter_data = [{
            'Date of Sale (dd/mm/yyyy)': '01/01/2010',
            'Address' : '5 Braemor Drive, Churchtown, Co.Dublin',
            'County' : 'Dublin',
            'Eircode' : '',
            'Price (�)' : '�343,000.00',
            'Not Full Market Price' : 'No',
            'VAT Exclusive' : 'No',
            'Description of Property' : 'Second-Hand Dwelling house /Apartment',
            'Property Size Description': ''
        }]

        db_string = f"postgresql://{USER}:{PASSWORD}@{HOST}:{PORT}/{DBNAME}"

        with patch('ppr_pipeline.injectors.injectors.create_engine') as mocked_engine:
            PPR_Hist_Injector._inject_data_to_staging(PPR_iter_data)
            mocked_engine.assert_called_once_with(db_string)

    def test_staging_orm(self):
        PPR_iter_data = [
            {
            'Date of Sale (dd/mm/yyyy)': '01/01/2010',
            'Address' : '5 Braemor Drive, Churchtown, Co.Dublin',
            'County' : 'Dublin',
            'Eircode' : '',
            'Price (�)' : '�343,000.00',
            'Not Full Market Price' : 'No',
            'VAT Exclusive' : 'No',
            'Description of Property' : 'Second-Hand Dwelling house /Apartment',
            'Property Size Description': ''
            },
            {
            'Date of Sale (dd/mm/yyyy)': '01/01/2010',
            'Address' : '5 Braemor Drive, Churchtown, Co.Dublin',
            'County' : 'Dublin',
            'Eircode' : '',
            'Price (�)' : '�343,000.00',
            'Not Full Market Price' : 'No',
            'VAT Exclusive' : 'No',
            'Description of Property' : 'Second-Hand Dwelling house /Apartment',
            'Property Size Description': ''
            },
        ]

        with patch('ppr_pipeline.injectors.injectors.Property_Transaction_Staging') as mocked_orm:
            PPR_Hist_Injector._inject_data_to_staging(PPR_iter_data)
            mocked_orm.assert_has_calls([
                call(
                    address = PPR_iter_data[0]['Address'],
                    county = PPR_iter_data[0]['County'],
                    eircode = PPR_iter_data[0]['Eircode'],
                    price = PPR_iter_data[0]['Price (�)'],
                    not_full_market_price = PPR_iter_data[0]['Not Full Market Price'],
                    vat_exclusive = PPR_iter_data[0]['VAT Exclusive'],
                    description_of_property = PPR_iter_data[0]['Description of Property'],
                    property_size_description = PPR_iter_data[0]['Property Size Description'],
                    date_of_sale = PPR_iter_data[0]['Date of Sale (dd/mm/yyyy)']
                ),
                call(
                    address = PPR_iter_data[1]['Address'],
                    county = PPR_iter_data[1]['County'],
                    eircode = PPR_iter_data[1]['Eircode'],
                    price = PPR_iter_data[1]['Price (�)'],
                    not_full_market_price = PPR_iter_data[1]['Not Full Market Price'],
                    vat_exclusive = PPR_iter_data[1]['VAT Exclusive'],
                    description_of_property = PPR_iter_data[1]['Description of Property'],
                    property_size_description = PPR_iter_data[1]['Property Size Description'],
                    date_of_sale = PPR_iter_data[1]['Date of Sale (dd/mm/yyyy)']
                )
            ])

if __name__ == '__main__':
    unittest.main()