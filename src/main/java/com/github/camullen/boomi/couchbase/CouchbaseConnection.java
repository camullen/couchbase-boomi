package com.github.camullen.boomi.couchbase;

import com.boomi.connector.api.BrowseContext;
import com.boomi.connector.api.ConnectorContext;
import com.boomi.connector.api.PropertyMap;
import com.boomi.connector.util.BaseConnection;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;

import static com.github.camullen.boomi.couchbase.CouchbaseContants.*;

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
     * Closes the connection to the Couchbase cluster and cleans up resources gracefully
     */
    protected void closeConnection() {
        if (null != cluster) cluster.disconnect();
    }
}
