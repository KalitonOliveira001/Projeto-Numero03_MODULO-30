package br.com.rpires.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String URL = "jdbc:postgresql://localhost:5432/loja";
    private static final String USER = "postgres";
    private static final String PASSWORD = "13112020@Marialaura01";

    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new SQLException("Erro ao conectar com o banco de dados: " + e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        try (Connection c = getConnection()) {
            System.out.println(" Conexão com PostgreSQL bem-sucedida!");
        } catch (SQLException e) {
            System.out.println(" Falha ao conectar com PostgreSQL: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
