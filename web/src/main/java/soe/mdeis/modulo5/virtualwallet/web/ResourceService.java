package soe.mdeis.modulo5.virtualwallet.web;

import java.util.List;

public interface ResourceService<TRequest, TResponse, TException extends Exception> {
    List<TResponse> findAll();

    TResponse findById(int id) throws TException;

    TResponse update(int id, TRequest request) throws TException;

    TResponse store(TRequest request) throws TException;

    void delete(int id) throws TException;
}
