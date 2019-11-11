package soe.mdeis.modulo5.virtualwallet.web;

import soe.mdeis.modulo5.virtualwallet.web.users.UserException;

import java.util.List;

public interface ResourceService<TRequest, TResponse> {
    List<TResponse> findAll();

    TResponse findById(int id) throws UserException;

    TResponse update(int id, TRequest request);

    TResponse store(TRequest request);

    void delete(int id);
}
