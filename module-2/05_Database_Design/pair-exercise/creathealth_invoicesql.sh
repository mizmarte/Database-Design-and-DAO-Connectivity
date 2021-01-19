createhealth_invoicesql.sh

export PGPASSWORD = 'postgres1'

psql -U postgres -f "./dropandcreate2.sql" &&

psql -U postgres -d health_history -f "./health_invoice.sql"