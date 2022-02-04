package com.github.camullen.boomi.couchbase;

import com.boomi.connector.api.*;
import com.boomi.connector.util.BaseBrowser;
import com.boomi.util.StringUtil;

import java.util.Collection;

public class CouchbaseBrowser extends BaseBrowser {

    protected CouchbaseBrowser(CouchbaseConnection connection) {
        super(connection);

    }

    @Override
    public ObjectTypes getObjectTypes() {
        return null;
    }

    @Override
    public ObjectDefinitions getObjectDefinitions(String s, Collection<ObjectDefinitionRole> collection) {
        return null;
    }

    @Override
    public CouchbaseConnection getConnection() {
        return (CouchbaseConnection) super.getConnection();
    }

    /**
     * Returns an object with given id and label.
     *
     * @param id    the id
     * @param label the label
     * @return the object type
     */
    private ObjectType getObjectType(String id, String label) {
        ObjectType objectType = new ObjectType();
        objectType.setId(id);
        if (!StringUtil.isBlank(label)) {
            objectType.setLabel(label);
        }
        return objectType;
    }
}
