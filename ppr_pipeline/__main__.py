from ppr_pipeline.scrapers.scrapers import PPR_Hist_Scraper
from ppr_pipeline.injectors.injectors import PPR_Hist_Injector

if __name__ == "__main__":
    #PPR_Hist_Scraper.upload_hist_data()
    PPR_Hist_Injector._get_all_from_s3()