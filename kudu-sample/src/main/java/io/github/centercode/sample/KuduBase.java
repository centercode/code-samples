package io.github.centercode.sample;

import org.apache.kudu.ColumnSchema;
import org.apache.kudu.Type;
import org.apache.kudu.client.KuduClient;
import org.apache.kudu.client.KuduException;
import org.apache.kudu.client.KuduTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class KuduBase {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    public KuduClient newKuduClient(String masterAddress) {
        return new KuduClient.KuduClientBuilder(masterAddress)
                .defaultAdminOperationTimeoutMs(30_000L)
                .defaultOperationTimeoutMs(30_000L)
                .defaultSocketReadTimeoutMs(10_000L)
                .build();
    }

    /**
     * kudu表名是大小写敏感的，Impala建表会转为小写字母
     */
    public boolean exist(KuduClient kuduClient, String tableName) throws KuduException {
        return kuduClient.tableExists(tableName);
    }

    public Set<String> describe(KuduClient kuduClient, String tableName) throws KuduException {
        KuduTable table = kuduClient.openTable(tableName);
        return table.getSchema().getColumns().stream().map(ColumnSchema::getName).collect(Collectors.toSet());
    }

    public List<Type> getPrimaryKeyTypes(KuduTable table) {
        return table.getSchema().getPrimaryKeyColumns().stream()
                .map(ColumnSchema::getType)
                .collect(Collectors.toList());
    }
}
