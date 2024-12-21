package br.com.danilo.Tarefa_SpringBoot;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestDesafioApplication {

    static Connection connection;

    public static void main(String[] args) {
        SpringApplication.run(TestDesafioApplication.class, args);

        conectarAoBancoDeDados();

        if (connection != null) {
            CadastroDeCidadaos cadastro = new CadastroDeCidadaos(connection);
            Scanner scanner = new Scanner(System.in);

            while (true) {
                exibirMenu();
                int opcao = scanner.nextInt();
                scanner.nextLine(); 

                switch (opcao) {
                    case 1:
                        System.out.print("Informe o nome do cidadão: ");
                        String nome = scanner.nextLine();
                        cadastro.cadastrarCidadao(nome);
                        break;
                    case 2:
                        System.out.print("Informe o NIS do cidadão: ");
                        String nis = scanner.nextLine();
                        String resultado = cadastro.pesquisarPorNIS(nis);
                        System.out.println(resultado);
                        break;
                    case 3:
                        System.out.println("Encerrando o programa...");
                        fecharConexao();
                        scanner.close();
                        return;
                    default:
                        System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                }
            }
        } else {
            System.out.println("Não foi possível conectar ao banco de dados. Encerrando o programa.");
        }
    }

    private static void conectarAoBancoDeDados() {
        try {
            String url = "jdbc:postgresql://localhost:5432/vendas_online";
            String usuario = "postgres";
            String senha = "Alado2jk*";
            connection = DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void fecharConexao() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("Escolha uma opção:");
        System.out.println("1. Cadastrar novo cidadão");
        System.out.println("2. Pesquisar cidadão pelo NIS");
        System.out.println("3. Sair");
    }
}
