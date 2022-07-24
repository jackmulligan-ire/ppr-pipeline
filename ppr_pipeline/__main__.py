import sys

from ppr_pipeline.scrapers.scrapers import PPR_Hist_Scraper
from ppr_pipeline.injectors.injectors import PPR_Hist_Injector

def main():
    try:
        cmd_line_arg = sys.argv[1]
        if cmd_line_arg == "scrape_all":
            PPR_Hist_Scraper.upload_hist_data()
        elif cmd_line_arg == "inject_all":
            PPR_Hist_Injector.inject_ppr_data()
        else:
            raise Exception
    except:
        raise SystemExit(f"Error. Usage: python -m ppr_pipeline [scrape_all|inject_all]")

if __name__ == "__main__":
    main()