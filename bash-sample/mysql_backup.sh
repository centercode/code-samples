#!/bin/bash
# backup all databases in a mysql instance
USER="root"
PASSWORD="123456"

DATABASES=`mysql -u $USER -p$PASSWORD -e "SHOW DATABASES;" | tr -d "| " | grep -v Database`

for DB in $DATABASES; do
    if [[ "$DB" != "information_schema" ]] && [[ "$DB" != "performance_schema" ]] && [[ "$DB" != "mysql" ]] && [[ "$DB" != _* ]] ; then
        echo "Dumping database: $DB"
        mysqldump -u $USER -p $PASSWORD --databases $DB > `date +%Y%m%d`.$db.sql
       # gzip $OUTPUT/`date +%Y%m%d`.$db.sql
    fi
done