import os
import psycopg2

class PPR_Hist_Transformer():
    @classmethod
    def transform_stage_to_dwh(cls):
        commands = [
            'DELETE FROM fact_property_transactions;',
            'CALL populate_dim_date();',
            'CALL populate_dim_property();',
            'CALL populate_fact();',
        ]

        with psycopg2.connect(
            dbname=f"{os.environ.get('DB_NAME')}", 
            user=f"{os.environ.get('POSTGRES_USER')}", 
            password=f"{os.environ.get('POSTGRES_PASSWORD')}", 
            host=f"{os.environ.get('DB_HOST')}", 
            port=f"{os.environ.get('DB_PORT')}"
        ) as conn:
            with conn.cursor() as cur:
                for command in commands:
                    cur.execute(command)
                conn.commit()