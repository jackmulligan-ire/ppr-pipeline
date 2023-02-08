docker exec ppr-pipeline-pg-database-1 pg_dumpall -U postgres > db-backups/backup.sql

if [[ "$OSTYPE" == "linux-gnu"* ]] 
then
	sed -i '/CREATE ROLE postgres;/d' db-backups/backup.sql
else
	sed -i '' '/CREATE ROLE postgres;/d' db-backups/backup.sql
fi
 
echo "Backup updated \n"
