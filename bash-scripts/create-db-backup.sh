/usr/local/bin/docker exec ppr-pipeline-pg-database-1 pg_dumpall -U postgres > db-backups/new-backup.sql
sed -i '' '/CREATE ROLE postgres;/d' db-backups/backup.sql
echo "Backup updated \n"
