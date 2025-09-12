package br.com.rpires.dao;

import br.com.rpires.dao.generic.GenericDAO;
import br.com.rpires.domain.Produto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdutoDAO extends GenericDAO<Produto, String> implements IProdutoDAO {

    @Override
    protected String getTableName() {
        return "tb_produto";
    }

    @Override
    protected String getMostRelevantColumn() {
        return "codigo"; // usamos codigo como chave natural aqui
    }

    @Override
    protected String getInsertSql() {
        // mesma ordem dos parâmetros em setParametersForInsert
        return "INSERT INTO tb_produto (codigo, nome, categoria, quantidade_estoque, descricao) VALUES (?, ?, ?, ?, ?)";
    }

    @Override
    protected String getUpdateSql() {
        // WHERE usa a coluna mais relevante (codigo)
        return "UPDATE tb_produto SET nome = ?, categoria = ?, quantidade_estoque = ?, descricao = ? WHERE codigo = ?";
    }

    @Override
    protected void setParametersForInsert(PreparedStatement stm, Produto entity) throws SQLException {
        stm.setString(1, entity.getCodigo());
        stm.setString(2, entity.getNome());
        if (entity.getCategoria() != null) stm.setString(3, entity.getCategoria()); else stm.setNull(3, java.sql.Types.VARCHAR);
        if (entity.getQuantidadeEstoque() != null) stm.setInt(4, entity.getQuantidadeEstoque()); else stm.setNull(4, java.sql.Types.INTEGER);
        stm.setString(5, entity.getDescricao());
    }

    @Override
    protected void setParametersForUpdate(PreparedStatement stm, Produto entity) throws SQLException {
        stm.setString(1, entity.getNome());
        if (entity.getCategoria() != null) stm.setString(2, entity.getCategoria()); else stm.setNull(2, java.sql.Types.VARCHAR);
        if (entity.getQuantidadeEstoque() != null) stm.setInt(3, entity.getQuantidadeEstoque()); else stm.setNull(3, java.sql.Types.INTEGER);
        stm.setString(4, entity.getDescricao());
        stm.setString(5, entity.getCodigo()); // WHERE codigo = ?
    }

    @Override
    protected void setParametersForSelect(PreparedStatement stm, String valor) throws SQLException {
        stm.setString(1, valor);
    }

    @Override
    protected Produto getEntityFromResultSet(ResultSet rs) throws SQLException {
        Produto p = new Produto();
        // use exatamente os nomes de coluna que existem no seu DB (lowercase funciona)
        p.setId(rs.getLong("id"));
        p.setCodigo(rs.getString("codigo"));
        p.setNome(rs.getString("nome"));
        p.setCategoria(rs.getString("categoria"));
        // cuidado: getInt retorna 0 se NULL => se quiser null-safe:
        int q = rs.getInt("quantidade_estoque");
        if (rs.wasNull()) p.setQuantidadeEstoque(null); else p.setQuantidadeEstoque(q);
        p.setDescricao(rs.getString("descricao"));
        return p;
    }
}
