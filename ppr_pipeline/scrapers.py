# Python libs
import requests
from zipfile import ZipFile
from io import BytesIO

# Third-party libs
import boto3

class PPR_Hist_Scraper():
    @classmethod
    def upload_hist_data(cls):
        response_content = PPR_Hist_Scraper._request_zipfile()
        csv_data = PPR_Hist_Scraper._extract_csv_data(response_content)
        PPR_Hist_Scraper._upload_csv_to_s3(csv_data)

    @classmethod
    def _request_zipfile(cls):
        response = requests.get(
            "https://www.propertypriceregister.ie/website/npsra/ppr/npsra-ppr.nsf/Downloads/PPR-ALL.zip/$FILE/PPR-ALL.zip",
            verify=False)
        return response.content

    @classmethod
    def _extract_csv_data(cls, response_content):
        with ZipFile(BytesIO(response_content)) as zipObj:
            csv_data = zipObj.read('PPR-ALL.csv')
            return BytesIO(csv_data)

    @classmethod
    def _upload_csv_to_s3(cls, csv_data):
        s3_client = boto3.client('s3')
        s3_client.upload_fileobj(csv_data, 'ppr-pipeline', 'PPR-ALL.csv')