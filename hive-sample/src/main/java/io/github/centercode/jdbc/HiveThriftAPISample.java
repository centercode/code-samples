package io.github.centercode.jdbc;

import lombok.SneakyThrows;
import org.apache.hive.service.rpc.thrift.TCLIService;
import org.apache.hive.service.rpc.thrift.TCloseSessionReq;
import org.apache.hive.service.rpc.thrift.TCloseSessionResp;
import org.apache.hive.service.rpc.thrift.TExecuteStatementReq;
import org.apache.hive.service.rpc.thrift.TExecuteStatementResp;
import org.apache.hive.service.rpc.thrift.TFetchResultsReq;
import org.apache.hive.service.rpc.thrift.TFetchResultsResp;
import org.apache.hive.service.rpc.thrift.TOpenSessionReq;
import org.apache.hive.service.rpc.thrift.TOpenSessionResp;
import org.apache.hive.service.rpc.thrift.TProtocolVersion;
import org.apache.hive.service.rpc.thrift.TSessionHandle;
import org.apache.hive.service.rpc.thrift.TStatus;
import org.apache.hive.service.rpc.thrift.TStatusCode;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import java.util.HashMap;
import java.util.function.Consumer;

public class HiveThriftAPISample {

    public static void main(String[] args) throws TTransportException {
        String host = "localhost";
        int port = 21050;

        HiveThriftAPISample sample = new HiveThriftAPISample();
        sample.request(host, port, new Consumer<TCLIService.Client>() {
            @Override
            @SneakyThrows
            public void accept(TCLIService.Client client) {
                TSessionHandle tSessionHandle = sample.openSession(client);
                try {
                    sample.executeQuery(client);
                } finally {
                    sample.closeSession(client, tSessionHandle);
                }
            }
        });
    }

    void request(String host, int port, Consumer<TCLIService.Client> func) throws TTransportException {
        TTransport transport = new TSocket(host, port, 0);
        transport.open();
        try {
            TProtocol protocol = new TBinaryProtocol(transport);
            TCLIService.Client client = new TCLIService.Client(protocol);
            func.accept(client);
        } finally {
            transport.close();
        }
    }

    TSessionHandle openSession(TCLIService.Client client) throws TException {
        TOpenSessionReq openSessionReq = new TOpenSessionReq(TProtocolVersion.HIVE_CLI_SERVICE_PROTOCOL_V6);
        HashMap<String, String> conf = new HashMap<>();
        conf.put("use:database", "default");
        conf.put("set:hiveconf:hive.server2.thrift.resultset.default.fetch.size", "1000");

        openSessionReq.setConfiguration(conf);
        TOpenSessionResp openSessionResp = client.OpenSession(openSessionReq);
        verifySuccess(openSessionResp.getStatus());
        return openSessionResp.getSessionHandle();
    }

    void closeSession(TCLIService.Client client, TSessionHandle sessionHandle) throws TException {
        TCloseSessionReq closeSessionReq = new TCloseSessionReq(sessionHandle);
        TCloseSessionResp closeSessionResp = client.CloseSession(closeSessionReq);
        verifySuccess(closeSessionResp.getStatus());
    }

    void executeQuery(TCLIService.Client client) throws TException {
        TSessionHandle sessionHandle = openSession(client);
        try {
            TExecuteStatementReq executeStmtReq = new TExecuteStatementReq(sessionHandle, "select sleep(5000)");
            TExecuteStatementResp executeStmtResp = client.ExecuteStatement(executeStmtReq);
            verifySuccess(executeStmtResp.getStatus());
            TFetchResultsReq fetchResultsReq = new TFetchResultsReq();
            TFetchResultsResp fetchResultsResp = client.FetchResults(fetchResultsReq);
            fetchResultsResp.getResults();
        } finally {
            closeSession(client, sessionHandle);
        }
    }

    void verifySuccess(TStatus status) throws TException {
        if (status.getStatusCode() == TStatusCode.SUCCESS_STATUS ||
                (status.getStatusCode() == TStatusCode.SUCCESS_WITH_INFO_STATUS)) {
            return;
        }
        throw new TException("failed status:" + status.toString() + ":" + status.getErrorMessage());
    }
}
