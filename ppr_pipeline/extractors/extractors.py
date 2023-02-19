# Python libs
import requests
from zipfile import ZipFile
from io import BytesIO
import csv

# Third-party libs
import boto3
from bs4 import BeautifulSoup

# App modules
import ppr_pipeline

class PPR_Extractor():
    @classmethod
    def extract_ppr_data(cls):
        response_content = PPR_Extractor._request_zipfile()
        csv_data = PPR_Extractor._extract_csv_data(response_content)
        last_updated_timestamp = PPR_Extractor._extract_update_timestamp()
        PPR_Extractor._upload_data_to_s3(csv_data, last_updated_timestamp)

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
    def _upload_data_to_s3(cls, csv_data, last_updated_timestamp):
        s3_client = boto3.client('s3')
        s3_client.upload_fileobj(csv_data, ppr_pipeline.BUCKET_NAME, ppr_pipeline.CSV_FILE_NAME)
        s3_client.upload_fileobj(last_updated_timestamp, ppr_pipeline.BUCKET_NAME, ppr_pipeline.TIMESTAMP_FILE_NAME)

    @classmethod
    def _extract_update_timestamp(cls):
        response = requests.get(
            "https://www.propertypriceregister.ie/website/npsra/pprweb.nsf/PPR?OpenForm",
            verify=False)
        soup = BeautifulSoup(response.content, 'html.parser')
        timestamp_string = soup.find(id="LastUpdated").string
        return BytesIO(str.encode(timestamp_string))
