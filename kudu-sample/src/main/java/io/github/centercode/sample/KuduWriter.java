package io.github.centercode.sample;

import org.apache.kudu.Type;
import org.apache.kudu.client.KuduSession;
import org.apache.kudu.client.OperationResponse;
import org.apache.kudu.client.PartialRow;
import org.apache.kudu.client.RowError;
import org.apache.kudu.client.RowErrorsAndOverflowStatus;
import org.apache.kudu.client.SessionConfiguration;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

public class KuduWriter extends KuduBase {

    /**
     * handle background errors when using {@link SessionConfiguration.FlushMode#AUTO_FLUSH_BACKGROUND} flush mode
     */
    protected void handleBackgroundErrors(KuduSession session) {
        if (session.countPendingErrors() != 0) {
            RowErrorsAndOverflowStatus status = session.getPendingErrors();
            RowError[] errs = status.getRowErrors();
            StringBuilder sb = new StringBuilder(256);
            sb.append("Error when write to Kudu, top few errors:");
            for (int i = 0, num = Math.min(errs.length, 5); i < num; i++) {
                sb.append(errs[i].toString());
            }
            if (status.isOverflowed()) {
                sb.append(", some errors were discarded because of error buffer overflowed");
            }
            throw new IllegalStateException(sb.toString());
        }
    }

    protected void handleResponseErrors(List<OperationResponse> responses) {
        String message = responses.stream().filter(OperationResponse::hasRowError)
                .map(r -> r.getRowError().toString())
                .collect(Collectors.joining(", ", "Error when write to Kudu:[", "]"));
        throw new IllegalStateException(message);
    }

    protected void bindRowValues(PartialRow row, List<Type> types, List<Object> values) {
        if (types == null || values == null || types.size() != values.size()) {
            String message = "Types and values not match, types: " + types + ", values: " + values;
            throw new IllegalArgumentException(message);
        }
        logger.debug("bind Row:{}, {}", types, values);
        for (int i = 0; i < types.size(); i++) {
            Type type = types.get(i);
            Object value = values.get(i);
            try {
                if (value == null) {
                    row.setNull(i);
                } else {
                    bindColumnValue(row, i, type, value);
                }
            } catch (Exception e) {
                logger.error("Error when bind column value: No.{}, type: {}", i, type);
                throw e;
            }

        }
    }

    protected void bindColumnValue(PartialRow row, int i, Type type, Object value) {
        switch (type) {
            case INT8:
                row.addByte(i, (Byte) value);
                break;
            case INT16:
                row.addShort(i, (Short) value);
                break;
            case INT32:
                row.addInt(i, (Integer) value);
                break;
            case INT64:
            case UNIXTIME_MICROS:
                row.addLong(i, (Long) value);
                break;
            case BINARY:
                row.addBinary(i, (byte[]) value);
                break;
            case STRING:
                if (value instanceof byte[]) {
                    row.addString(i, new String((byte[]) value, UTF_8));
                } else {
                    row.addString(i, (String) value);
                }
                break;
            case BOOL:
                row.addBoolean(i, (Boolean) value);
                break;
            case FLOAT:
                row.addFloat(i, (Float) value);
                break;
            case DOUBLE:
                row.addDouble(i, (Double) value);
                break;
            case DECIMAL:
                row.addDecimal(i, (BigDecimal) value);
                break;
            default:
                throw new IllegalStateException("Not support data type:" + type);
        }
    }
}
