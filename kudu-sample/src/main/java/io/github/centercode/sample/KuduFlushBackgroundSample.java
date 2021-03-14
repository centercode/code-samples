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

/**
 * run in {@link SessionConfiguration.FlushMode#AUTO_FLUSH_BACKGROUND}, no order guarantee
 */
public class KuduFlushBackgroundSample extends KuduWriter {

    private final KuduClient kuduClient;

    public KuduFlushBackgroundSample(String masterAddress) {
        this.kuduClient = newKuduClient(masterAddress);
    }

    public void batchDelete(String kuduTableName, List<List<Object>> records) throws KuduException {
        KuduTable table = kuduClient.openTable(kuduTableName);
        List<Type> keyTypes = getPrimaryKeyTypes(table);
        KuduSession session = kuduClient.newSession();
        session.setFlushMode(SessionConfiguration.FlushMode.AUTO_FLUSH_BACKGROUND);
        try {
            for (List<Object> record : records) {
                Operation operation = table.newDelete();
                PartialRow row = operation.getRow();
                bindRowValues(row, keyTypes, record);
                session.apply(operation);
            }
        } finally {
            // block until the session buffer is empty(no need to call flush() repeatedly)
            session.close();
            handleBackgroundErrors(session);
        }
    }
}
