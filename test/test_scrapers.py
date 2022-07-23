import unittest
from unittest.mock import patch
from ppr_pipeline.scrapers.scrapers import PPR_Hist_Scraper

class TestScrapers(unittest.TestCase):
    def test_get_ppr_hist(self):
        ppr_hist_url = "https://www.propertypriceregister.ie/website/npsra/ppr/npsra-ppr.nsf/Downloads/PPR-ALL.zip/$FILE/PPR-ALL.zip"
        with patch('ppr_pipeline.scrapers.scrapers.requests.get') as mocked_get:
            PPR_Hist_Scraper._request_zipfile()
            mocked_get.assert_called_with(ppr_hist_url, verify=False)

if __name__ == '__main__':
    unittest.main()