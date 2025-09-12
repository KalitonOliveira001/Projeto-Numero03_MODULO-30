package br.com.rpires.test;

import br.com.rpires.dao.IProdutoDAO;
import br.com.rpires.dao.ProdutoDAO;
import br.com.rpires.domain.Produto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProdutoTest {

    private IProdutoDAO produtoDAO;
    private Produto produto;

    @Before
    public void init() throws Exception {
        produtoDAO = new ProdutoDAO();
        produto = new Produto();
        produto.setCodigo("T100");
        produto.setNome("Teste Produto");
        produto.setCategoria("Eletrônicos");
        produto.setQuantidadeEstoque(10);
        produto.setDescricao("Descrição para teste");

        // cadastra (se já existir, depende do DAO: pode lançar erro; trate conforme seu DAO)
        produtoDAO.cadastrar(produto);
        System.out.println("✅ Produto cadastrado (init)");
    }

    @After
    public void end() throws Exception {
        // tenta excluir pelo código (se o DAO tem excluir por chave natural)
        try {
            produtoDAO.excluir(produto.getCodigo());
            System.out.println(" Produto excluído (cleanup)");
        } catch (Exception e) {
            // ignora erro de cleanup
        }
    }

    @Test
    public void pesquisarProduto() throws Exception {
        Produto p = produtoDAO.consultar(produto.getCodigo());
        assertNotNull("produtoConsultado não pode ser nulo", p);
        assertEquals(produto.getCodigo(), p.getCodigo());
        assertEquals(produto.getNome(), p.getNome());
        assertEquals(produto.getCategoria(), p.getCategoria());
        assertEquals(produto.getQuantidadeEstoque(), p.getQuantidadeEstoque());
        assertEquals(produto.getDescricao(), p.getDescricao());
        System.out.println(" Teste pesquisar produto PASSOU!");
    }
}
