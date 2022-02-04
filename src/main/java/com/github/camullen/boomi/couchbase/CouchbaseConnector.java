package com.github.camullen.boomi.couchbase;

import com.boomi.connector.api.BrowseContext;
import com.boomi.connector.api.Browser;
import com.boomi.connector.api.Operation;
import com.boomi.connector.api.OperationContext;
import com.boomi.connector.util.BaseConnector;

public class CouchbaseConnector extends BaseConnector {
    @Override
    public Browser createBrowser(BrowseContext browseContext) {
        return null;
    }

    @Override
    protected Operation createCreateOperation(OperationContext opContext) {
        return new CouchbaseCreateOperation(createConnection(opContext));
    }

    protected CouchbaseConnection createConnection(BrowseContext context) {
        return new CouchbaseConnection(context);
    }
}
