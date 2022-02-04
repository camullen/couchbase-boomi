package com.github.camullen.boomi.couchbase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.boomi.connector.api.ObjectData;
import com.boomi.connector.api.OperationResponse;
import com.boomi.connector.api.UpdateRequest;
import com.boomi.connector.util.BaseUpdateOperation;

public class CouchbaseCreateOperation extends BaseUpdateOperation {
    protected CouchbaseCreateOperation(CouchbaseConnection conn) {
        super(conn);
    }

    @Override
    protected void executeUpdate(UpdateRequest updateRequest, OperationResponse operationResponse) {
        List<ObjectData> trackedData = new ArrayList<ObjectData>();
        for (ObjectData obj : updateRequest) {
            trackedData.add(obj);
        }
        Map<String, Object> inputConfig = getConnection().prepareInputConfig(this.getContext(),
                operationResponse.getLogger());

    }

    /**
     * Gets the connection.
     *
     * @return the connection
     */
    /*
     * (non-Javadoc)
     * 
     * @see com.boomi.connector.util.BaseOperation#getConnection()
     */
    @Override
    public CouchbaseConnection getConnection() {
        return (CouchbaseConnection) super.getConnection();
    }
}
