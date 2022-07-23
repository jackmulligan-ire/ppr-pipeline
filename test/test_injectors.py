import unittest
from unittest.mock import patch
from ppr_pipeline.injectors.injectors import PPR_Hist_Injector

class TestInjectors(unittest.TestCase):
    def test_get_from_s3(self):
        with patch('ppr_pipeline.injectors.injectors.boto3.client') as mocked_get:
            PPR_Hist_Injector._get_all_from_s3()
            mocked_get.assert_called_once()

if __name__ == '__main__':
    unittest.main()