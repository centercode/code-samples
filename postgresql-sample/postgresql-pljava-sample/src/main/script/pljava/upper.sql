--
-- Reference: https://github.com/tada/pljava/wiki/SQL-functions
--
SELECT sqlj.install_jar('file:/var/lib/pgsql/javaudf/example.jar', 'example', TRUE);

-- 验证SQL： SELECT name, setting FROM pg_settings WHERE name LIKE 'pljava.%' AND source = 'database';
ALTER DATABASE postgres SET pljava.libjvm_location = '/opt/jdk1.8.0/jre/lib/amd64/server/libjvm.so';

-- 验证SQL： SELECT sqlj.get_classpath('public');
SELECT sqlj.set_classpath('public', 'example');

-- 验证SQL： SELECT java_upperstring ('abc', 0, 3);
CREATE FUNCTION upper(VARCHAR, INTEGER, INTEGER)
RETURNS VARCHAR
AS 'io.github.centercode.sample.UpperStringSample.upperString'
LANGUAGE JAVA;
