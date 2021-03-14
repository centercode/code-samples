package io.github.centercode.sample;

import org.apache.kudu.Type;
import org.apache.kudu.client.KuduClient;
import org.apache.kudu.client.KuduException;
import org.apache.kudu.client.KuduSession;
import org.apache.kudu.client.KuduTable;
import org.apache.kudu.client.Operation;
import org.apache.kudu.client.PartialRow;
import org.apache.kudu.client.SessionConfiguration;

import java.util.List;

public class KuduFlushAutoSyncSample extends KuduWriter {

    private final KuduClient kuduClient;

    public KuduFlushAutoSyncSample(String masterAddress) {
        this.kuduClient = newKuduClient(masterAddress);
    }

    public void batchDelete(String kuduTableName, List<List<Object>> records) throws KuduException {
        KuduTable table = kuduClient.openTable(kuduTableName);
        List<Type> keyTypes = getPrimaryKeyTypes(table);
        KuduSession session = kuduClient.newSession();
        session.setFlushMode(SessionConfiguration.FlushMode.AUTO_FLUSH_SYNC);
        try {
            for (List<Object> record : records) {
                Operation operation = table.newDelete();
                PartialRow row = operation.getRow();
                bindRowValues(row, keyTypes, record);
                //No batching will occur
                session.apply(operation);
            }
        } finally {
            session.close();
        }
    }

}
