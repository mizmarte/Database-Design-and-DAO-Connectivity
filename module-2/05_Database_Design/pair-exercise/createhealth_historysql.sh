createhealth_historysql.sh

export PGPASSWORD = 'postgres1'

psql -U postgres -f "./dropandcreate.sql" &&

psql -U postgres -d health_history -f "./health_history.sql"