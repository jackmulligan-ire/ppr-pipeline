import sys
from dotenv import load_dotenv

from ppr_pipeline.scrapers.scrapers import PPR_Hist_Scraper
from ppr_pipeline.injectors.injectors import PPR_Hist_Injector
from ppr_pipeline.transformers.transformers import PPR_Hist_Transformer

def main(): 
    load_dotenv()

    if sys.argv[1] == "scrape_all":
        PPR_Hist_Scraper.upload_hist_data()
    elif sys.argv[1] == "inject_all":
        PPR_Hist_Injector.inject_ppr_data()
    elif sys.argv[1] == "transform":
        PPR_Hist_Transformer.transform_stage_to_dwh()
    else:
        raise SystemExit(f"Error. Usage: python -m ppr_pipeline [scrape_all|inject_all|transform]")

if __name__ == "__main__":
    try:
        cmd_line_arg = sys.argv[1]
        main()
    except IndexError:
        raise SystemExit(f"Error. Usage: python -m ppr_pipeline [scrape_all|inject_all|transform]")