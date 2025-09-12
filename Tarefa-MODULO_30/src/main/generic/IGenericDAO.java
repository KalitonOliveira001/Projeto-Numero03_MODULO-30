package br.com.rpires.dao.generic;

import java.io.Serializable;
import java.util.Collection;

/**
 * Interface genérica para operações de DAO
 * @param <T> Tipo da entidade
 * @param <E> Tipo da chave primária
 */
public interface IGenericDAO <T, E extends Serializable> {

    public Boolean cadastrar(T entity) throws Exception;

    public void excluir(E valor) throws Exception;

    public void alterar(T entity) throws Exception;

    public T consultar(E valor) throws Exception;

    public Collection<T> buscarTodos() throws Exception;
}
