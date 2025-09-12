package br.com.rpires.dao;

import br.com.rpires.dao.generic.GenericDAO;
import br.com.rpires.domain.Cliente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDAO extends GenericDAO<Cliente, Long> implements IClienteDAO {

    @Override
    protected String getTableName() { return "TB_CLIENTE"; }

    @Override
    protected String getMostRelevantColumn() { return "CODIGO"; }

    @Override
    protected String getInsertSql() {
        return "INSERT INTO TB_CLIENTE (CODIGO, NOME, TELEFONE, EMAIL) VALUES (?, ?, ?, ?)";
    }

    @Override
    protected String getUpdateSql() {
        return "UPDATE TB_CLIENTE SET NOME = ?, TELEFONE = ?, EMAIL = ? WHERE CODIGO = ?";
    }

    @Override
    protected void setParametersForInsert(PreparedStatement stm, Cliente entity) throws SQLException {
        stm.setLong(1, entity.getCodigo());
        stm.setString(2, entity.getNome());
        if (entity.getTelefone() != null) stm.setLong(3, entity.getTelefone()); else stm.setNull(3, java.sql.Types.BIGINT);
        stm.setString(4, entity.getEmail());
    }

    @Override
    protected void setParametersForUpdate(PreparedStatement stm, Cliente entity) throws SQLException {
        stm.setString(1, entity.getNome());
        if (entity.getTelefone() != null) stm.setLong(2, entity.getTelefone()); else stm.setNull(2, java.sql.Types.BIGINT);
        stm.setString(3, entity.getEmail());
        stm.setLong(4, entity.getCodigo());
    }

    @Override
    protected void setParametersForSelect(PreparedStatement stm, Long valor) throws SQLException {
        stm.setLong(1, valor);
    }

    @Override
    protected Cliente getEntityFromResultSet(ResultSet rs) throws SQLException {
        Cliente c = new Cliente();
        c.setId(rs.getLong("ID"));
        c.setCodigo(rs.getLong("CODIGO"));
        c.setNome(rs.getString("NOME"));
        c.setTelefone(rs.getLong("TELEFONE"));
        c.setEmail(rs.getString("EMAIL"));
        return c;
    }
}
