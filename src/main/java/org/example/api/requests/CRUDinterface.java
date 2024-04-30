package org.example.api.requests;

public interface CRUDinterface {

    Object create(Object o);

    public Object get(String id);

    public Object update(Object o);

    public Object delete(String id);
}
