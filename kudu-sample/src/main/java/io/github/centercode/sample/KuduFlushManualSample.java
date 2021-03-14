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

public class KuduFlushManualSample extends KuduWriter {

    private static final int bufferSize = 1024;

    private final KuduClient kuduClient;

    public KuduFlushManualSample(String masterAddress) {
        this.kuduClient = newKuduClient(masterAddress);
    }

    public void batchDelete(String kuduTableName, List<List<Object>> records) throws KuduException {
        KuduTable table = kuduClient.openTable(kuduTableName);
        List<Type> keyTypes = getPrimaryKeyTypes(table);
        KuduSession session = kuduClient.newSession();
        session.setFlushMode(SessionConfiguration.FlushMode.MANUAL_FLUSH);
        session.setMutationBufferSpace(bufferSize);
        try {
            for (int i = 0, flushSize = bufferSize / 2; i < records.size(); i++) {
                List<Object> record = records.get(i);
                Operation operation = table.newDelete();
                PartialRow row = operation.getRow();
                bindRowValues(row, keyTypes, record);
                session.apply(operation);
                if (i % (flushSize) == 0) {
                    session.flush();
                }
            }
        } finally {
            // block until the session buffer is empty
            session.close();
        }
    }
}
