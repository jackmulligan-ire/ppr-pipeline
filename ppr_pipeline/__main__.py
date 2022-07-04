from ppr_pipeline.scrapers import PPR_Hist_Scraper

if __name__ == "__main__":
    PPR_Hist_Scraper.request_zipfile()
    PPR_Hist_Scraper.transform_zipfile_to_csv()