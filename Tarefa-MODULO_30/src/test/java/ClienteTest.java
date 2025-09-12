package br.com.rpires.test;

import br.com.rpires.dao.ClienteDAO;
import br.com.rpires.dao.IClienteDAO;
import br.com.rpires.domain.Cliente;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClienteTest {

    private IClienteDAO clienteDAO;
    private Cliente cliente;

    @Before
    public void init() throws Exception {
        clienteDAO = new ClienteDAO();
        cliente = new Cliente();
        cliente.setCodigo(200L);
        cliente.setNome("Teste Cliente");
        cliente.setTelefone(11999999999L);
        cliente.setEmail("teste.cliente@email.com"); // novo campo
        clienteDAO.cadastrar(cliente);
    }

    @After
    public void end() throws Exception {
        Cliente c = clienteDAO.consultar(cliente.getCodigo());
        if (c != null) clienteDAO.excluir(cliente.getCodigo());
    }

    @Test
    public void pesquisarCliente() throws Exception {
        Cliente c = clienteDAO.consultar(cliente.getCodigo());
        assertNotNull(c);
        assertEquals(cliente.getCodigo(), c.getCodigo());
        assertEquals(cliente.getNome(), c.getNome());
        assertEquals(cliente.getTelefone(), c.getTelefone());
        assertEquals(cliente.getEmail(), c.getEmail()); // novo campo
        System.out.println(" Teste pesquisar cliente PASSOU!");
    }
}
