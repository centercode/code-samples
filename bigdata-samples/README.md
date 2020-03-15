# 大数据简例

## Hive

Beeline直接连接HiveServer2：

```shell
beeline -u "jdbc:hive2://localhost:10000/default;"
```

Beeline连接高可用HiveServer2：

```shell
beeline -u "jdbc:hive2://zk1:2181,zk2:2181,zk3:2181/default;serviceDiscoveryMode=zooKeeper;zooKeeperNamespace=hiveserver2;"
```

动态分区：

```sql
SET hive.exec.dynamic.partition=true;
SET hive.exec.dynamic.partition.mode=nonstrict;
SET hive.exec.max.dynamic.partitions.pernode=1000;

INSERT OVERWRITE TABLE table2 PARTITION(dt)
SELECT * from table1 where dt = '20200202';
```

## Spark

编译源码：

```shell
./dev/make-distribution.sh --name 2.6.0 --tgz  -Pyarn -Phadoop-2.6 -Phive -Phive-thriftserver -Dhadoop.version=2.6.0 -X
```

spark-shell测试：

```Scala
sqlContext.sql("select * from ods.o_m_kd_menu").show();
```

spark2-shell测试：

```scala
spark.sql("select * from ods.o_m_kd_menu").show();
```

