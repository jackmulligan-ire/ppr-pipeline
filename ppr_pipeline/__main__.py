import sys
from dotenv import load_dotenv

from ppr_pipeline.extractors.extractors import PPR_Extractor
from ppr_pipeline.loaders.loaders import PPR_Loader
from ppr_pipeline.transformers.transformers import PPR_Transformer

def main(): 
    load_dotenv()

    if sys.argv[1] == "extract":
        PPR_Extractor.extract_ppr_data()
        print("Extraction completed! \n")
    elif sys.argv[1] == "load":
        PPR_Loader.load_ppr_data()
        print("Loading completed! \n")
    elif sys.argv[1] == "transform":
        PPR_Transformer.transform_staging_to_dwh()
        print("Transformation completed! \n")
    else:
        raise SystemExit(f"Error. Usage: python -m ppr_pipeline [extract|load|transform]")

if __name__ == "__main__":
    try:
        cmd_line_arg = sys.argv[1]
        main()
    except IndexError:
        raise SystemExit(f"Error. Usage: python -m ppr_pipeline [extract|load|transform]")