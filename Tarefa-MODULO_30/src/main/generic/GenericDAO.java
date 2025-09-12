package br.com.rpires.dao.generic;

import br.com.rpires.dao.jdbc.ConnectionFactory;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.ArrayList;

/**
 * Classe genérica para operações básicas de DAO
 */
public abstract class GenericDAO <T, E extends Serializable> implements IGenericDAO <T, E> {

    protected abstract String getTableName();

    protected abstract String getMostRelevantColumn();

    protected abstract void setParametersForInsert(PreparedStatement stm, T entity) throws SQLException;

    protected abstract void setParametersForUpdate(PreparedStatement stm, T entity) throws SQLException;

    protected abstract void setParametersForSelect(PreparedStatement stm, E valor) throws SQLException;

    protected abstract T getEntityFromResultSet(ResultSet rs) throws SQLException;

    protected abstract String getInsertSql();

    protected abstract String getUpdateSql();

    @Override
    public Boolean cadastrar(T entity) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = getInsertSql();
            stm = connection.prepareStatement(sql);
            setParametersForInsert(stm, entity);
            int result = stm.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Erro ao cadastrar: " + e.getMessage(), e);
        } finally {
            closeConnections(connection, stm, null);
        }
    }

    @Override
    public void alterar(T entity) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = getUpdateSql();
            stm = connection.prepareStatement(sql);
            setParametersForUpdate(stm, entity);
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Erro ao alterar: " + e.getMessage(), e);
        } finally {
            closeConnections(connection, stm, null);
        }
    }

    @Override
    public T consultar(E valor) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = "SELECT * FROM " + getTableName() + " WHERE " + getMostRelevantColumn() + " = ?";
            stm = connection.prepareStatement(sql);
            setParametersForSelect(stm, valor);
            rs = stm.executeQuery();
            if (rs.next()) {
                return getEntityFromResultSet(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new Exception("Erro ao consultar: " + e.getMessage(), e);
        } finally {
            closeConnections(connection, stm, rs);
        }
    }

    @Override
    public void excluir(E valor) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = "DELETE FROM " + getTableName() + " WHERE " + getMostRelevantColumn() + " = ?";
            stm = connection.prepareStatement(sql);
            setParametersForSelect(stm, valor);
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Erro ao excluir: " + e.getMessage(), e);
        } finally {
            closeConnections(connection, stm, null);
        }
    }

    @Override
    public Collection<T> buscarTodos() throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Collection<T> lista = new ArrayList<>();

        try {
            connection = ConnectionFactory.getConnection();
            String sql = "SELECT * FROM " + getTableName();
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                lista.add(getEntityFromResultSet(rs));
            }

        } catch (SQLException e) {
            throw new Exception("Erro ao buscar todos: " + e.getMessage(), e);
        } finally {
            closeConnections(connection, stm, rs);
        }

        return lista;
    }

    protected void closeConnections(Connection connection, PreparedStatement stm, ResultSet rs) {
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
            if (stm != null && !stm.isClosed()) {
                stm.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
}
