/opt/anaconda3/envs/etl-pipeline/bin/python -m ppr_pipeline extract
/opt/anaconda3/envs/etl-pipeline/bin/python -m ppr_pipeline load
/opt/anaconda3/envs/etl-pipeline/bin/python -m ppr_pipeline transform
bash bash-scripts/create-db-backup.sh