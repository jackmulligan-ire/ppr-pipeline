import unittest
from unittest.mock import patch
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

if __name__ == '__main__':
    unittest.main()