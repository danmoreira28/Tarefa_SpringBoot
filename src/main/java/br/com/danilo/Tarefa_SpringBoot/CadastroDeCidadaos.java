package br.com.danilo.Tarefa_SpringBoot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.sql.DataSource;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class CadastroDeCidadaos {
    private final DataSource dataSource;
    private final Connection connection;

    public CadastroDeCidadaos() {
        this.dataSource = null;
        this.connection = null;
    }

    public CadastroDeCidadaos(DataSource dataSource) {
        this.dataSource = dataSource;
        this.connection = null;
    }

    public CadastroDeCidadaos(Connection connection) {
        this.connection = connection;
        this.dataSource = null;
    }

    public void cadastrarCidadao(String nome) {
        String nis = gerarNIS();

        try {
            String sql = "INSERT INTO cidadaos (nome, nis) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nome);
            statement.setString(2, nis);
            statement.executeUpdate();
            System.out.println("Cidadão cadastrado com sucesso!\nNome: " + nome + "\nNIS: " + nis);
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar cidadão: " + e.getMessage());
        }
    }

    public String pesquisarPorNIS(String nis) {
        try {
            String sql = "SELECT nome FROM cidadaos WHERE nis = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nis);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                return "Cidadão encontrado:\nNome: " + nome + "\nNIS: " + nis;
            } else {
                return "Cidadão não encontrado.";
            }
        } catch (SQLException e) {
            return "Erro ao pesquisar por NIS: " + e.getMessage();
        }
    }

    private String gerarNIS() {
        Random random = new Random();
        StringBuilder nis = new StringBuilder("NIS-");
        while (nis.length() < 15) {
            nis.append(random.nextInt(10));
        }
        return nis.toString();
    }
}
