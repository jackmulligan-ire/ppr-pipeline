docker exec ppr-pipeline-pg-database-1 pg_dumpall -U postgres > new-backup.sql
sed -i '' '/CREATE ROLE postgres;/d' new-backup.sql
