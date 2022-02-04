package com.github.camullen.boomi.couchbase;

import com.boomi.connector.api.AtomConfig;
import com.boomi.connector.api.BrowseContext;
import com.boomi.connector.api.OperationContext;
import com.boomi.connector.api.OperationType;
import com.boomi.connector.api.PropertyMap;
import com.boomi.connector.util.BaseConnection;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;

import static com.github.camullen.boomi.couchbase.CouchbaseContants.*;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class CouchbaseConnection extends BaseConnection<BrowseContext> {

    /** The Couchbase cluster */
    private Cluster cluster;

    private Bucket bucket;

    public CouchbaseConnection(BrowseContext context) {
        super(context);
        openConnection();
    }

    /**
     * Opens a connection to the Couchbase cluster
     */
    protected void openConnection() {
        PropertyMap connectionProperties = getContext().getConnectionProperties();
        String connectionString = connectionProperties.getProperty(CONNECTION_STRING);
        String username = connectionProperties.getProperty(USERNAME);
        String password = connectionProperties.getProperty(PASSWORD);
        String bucketName = connectionProperties.getProperty(BUCKET);
        cluster = Cluster.connect(connectionString, username, password);
        bucket = cluster.bucket(bucketName);
    }

    /**
     * Closes the connection to the Couchbase cluster and cleans up resources
     * gracefully
     */
    protected void closeConnection() {
        if (null != cluster)
            cluster.disconnect();
    }

    /**
     * Prepare input configuration using
     * {@link BATCH_SIZE,DEFAULT_MAX_RETRIES,INCLUDE_SIZE_EXCEEDED_PAYLOAD}.
     *
     * @param operationContext the operation context
     * @param responseLogger   the response logger
     * @return the map
     */
    public Map<String, Object> prepareInputConfig(OperationContext operationContext, Logger responseLogger) {
        Map<String, Object> inputConfig = new HashMap<>();
        PropertyMap operationProperties = operationContext.getOperationProperties();
        Long batchSizeLong = operationProperties.getLongProperty(BATCH_SIZE, DEFALUT_BATCH_SIZE);
        AtomConfig atomConfig = operationContext.getConfig();
        int batchSize = Math.min(batchSizeLong.intValue(), atomConfig.getMaxPageSize());
        boolean includeSizeLimit = includeSizeLimitForInput(operationContext);
        if (batchSize != batchSizeLong) {
            responseLogger.info(new StringBuffer("Batch size reduced to max batch size allowed in atom config-")
                    .append(batchSize).toString());
        }
        inputConfig.put(BATCH_SIZE, batchSize);
        Long maxRetryAttemptsLong = operationProperties.getLongProperty(MAX_RETRIES, DEFAULT_MAX_RETRIES);
        int maxRetryAttempts = maxRetryAttemptsLong.intValue();
        inputConfig.put(MAX_RETRIES, maxRetryAttempts);
        inputConfig.put(IS_UPSERT_OPERATION, OperationType.UPSERT.equals(operationContext.getOperationType()));
        inputConfig.put(INCLUDE_SIZE_EXCEEDED_PAYLOAD, includeSizeLimit);
        return inputConfig;
    }

    /**
     * Include size limit for input.
     *
     * @param operationContext the operation context
     * @return true, if successful
     */
    private boolean includeSizeLimitForInput(OperationContext operationContext) {
        PropertyMap operationProperties = operationContext.getOperationProperties();
        OperationType operationType = ((BrowseContext) getContext()).getOperationType();
        if (OperationType.DELETE == operationType || OperationType.QUERY == operationType) {
            return false;
        } else {
            return (operationProperties.getBooleanProperty(INCLUDE_SIZE_EXCEEDED_PAYLOAD));
        }
    }
}
