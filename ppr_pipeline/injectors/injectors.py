import csv

# Third-party libs
import boto3

# App modules
import ppr_pipeline

class PPR_Hist_Injector():
    @classmethod
    def _get_all_from_s3(cls):
        s3 = boto3.client('s3')
        s3.download_file(ppr_pipeline.BUCKET_NAME, ppr_pipeline.ALL_OBJECT_NAME, 'tmp/PPR-ALL.csv')

        '''
        with open('tmp/PPR-ALL.csv', 'r') as csv_file:
            reader = csv.reader(csv_file)
            return reader
        '''