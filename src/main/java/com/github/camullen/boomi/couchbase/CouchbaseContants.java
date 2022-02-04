package com.github.camullen.boomi.couchbase;

public class CouchbaseContants {
    public static final String CONNECTION_STRING = "connectionString";

    public static final String USERNAME = "username";

    public static final String PASSWORD = "password";

    public static final String BUCKET = "bucket";

    public static final String BATCH_SIZE = "batchSize";
    public static final long DEFALUT_BATCH_SIZE = 1;

    public static final String MAX_RETRIES = "maxRetries";
    public static final long DEFAULT_MAX_RETRIES = 3;

    public static final String IS_UPSERT_OPERATION = "isUpsertOperation";

    public static final String INCLUDE_SIZE_EXCEEDED_PAYLOAD = "inlcudeSizeExceededPayload";
}
