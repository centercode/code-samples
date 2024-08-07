-- Show table's data size and rows in a database.
select
	TABLE_NAME,
	DATA_LENGTH,
	TABLE_ROWS
from
	information_schema.tables
where
	ENGINE = 'StarRocks'
	and TABLE_TYPE = 'BASE TABLE'
	and TABLE_SCHEMA = '${dbName}'
order by
	4 desc;

-- Show table's replica count.
select
	count(*) as replicaCount
from
	information_schema.be_tablets
where
	table_id = (
	select
		table_id
	from
		information_schema.tables_config
	where
		TABLE_ENGINE = 'OLAP'
		and TABLE_SCHEMA = '${dbName}'
		and TABLE_NAME = '${tableName}' )
