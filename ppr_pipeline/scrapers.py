import requests
from zipfile import ZipFile

class PPR_Hist_Scraper():
    @classmethod
    def request_zipfile(cls):
        response = requests.get(
            "https://www.propertypriceregister.ie/website/npsra/ppr/npsra-ppr.nsf/Downloads/PPR-ALL.zip/$FILE/PPR-ALL.zip",
            verify=False)
        with open("tmp/ppr-hist-data.zip", 'wb') as zip_file:
            zip_file.write(response.content)

    @classmethod
    def transform_zipfile_to_csv(cls):
        with ZipFile('tmp/ppr-hist-data.zip', 'r') as zipObj:
            zipObj.extractall('tmp')
