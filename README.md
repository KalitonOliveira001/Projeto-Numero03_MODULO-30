Projeto Número 03 – Módulo 30
Sistema de Cadastro com JDBC – Cliente e Produto

Este projeto foi desenvolvido como parte do Módulo 30 do curso, o objetivo é demonstrar a implementação completa de um sistema de persistência com JDBC, usando padrão DAO genérico, para entidades Cliente e Produto.

 

Java 11

PostgreSQL 15

JDBC

IntelliJ IDEA Community Edition

Maven

JUnit 4

Estrutura do Banco de Dados
Tabela tb_cliente
Coluna	Tipo
id	BIGSERIAL PK
codigo	VARCHAR(20)
nome	VARCHAR(100)
email	VARCHAR(255)
Tabela tb_produto
Coluna	Tipo
id	BIGSERIAL PK
codigo	VARCHAR(20)
nome	VARCHAR(100)
categoria	VARCHAR(100)
quantidade_estoque	INT
descricao	VARCHAR(255)
📝 Classes Principais

Cliente.java – entidade Cliente

Produto.java – entidade Produto

GenericDAO.java – DAO genérico para operações CRUD

ClienteDAO.java – DAO específico do Cliente

ProdutoDAO.java – DAO específico do Produto

ConnectionFactory.java – conexão JDBC com PostgreSQL

 Testes Automatizados

ClienteTest.java – testa cadastro, pesquisa e exclusão de Cliente

ProdutoTest.java – testa cadastro, pesquisa e exclusão de Produto

Resultado esperado no console:

✅ Produto cadastrado (init)
✅ Teste pesquisar produto PASSOU!
✅ Produto excluído (cleanup)
Process finished with exit code 0

 Como Executar

Configurar o PostgreSQL:

Criar o banco loja

Executar os scripts de criação de tabelas (acima)

Configurar usuário e senha no ConnectionFactory.java

Abrir o projeto no IntelliJ IDEA

File → Open Project

Build → Rebuild Project

Maven → Reload All Projects

Executar os testes

Clique com o botão direito em ClienteTest ou ProdutoTest → Run
