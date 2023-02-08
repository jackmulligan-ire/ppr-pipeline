if [[ "$OSTYPE" == "linux-gnu"* ]]; then
	conda activate etl-pipeline
fi

python -m ppr_pipeline extract
python -m ppr_pipeline load
python -m ppr_pipeline transform
bash bash-scripts/create-db-backup.sh
