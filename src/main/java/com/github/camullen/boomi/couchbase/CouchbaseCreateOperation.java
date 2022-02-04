package com.github.camullen.boomi.couchbase;

import com.boomi.connector.api.OperationResponse;
import com.boomi.connector.api.UpdateRequest;
import com.boomi.connector.util.BaseConnection;
import com.boomi.connector.util.BaseUpdateOperation;

public class CouchbaseCreateOperation extends BaseUpdateOperation {
    protected CouchbaseCreateOperation(BaseConnection conn) {
        super(conn);
    }

    @Override
    protected void executeUpdate(UpdateRequest updateRequest, OperationResponse operationResponse) {

    }
}
