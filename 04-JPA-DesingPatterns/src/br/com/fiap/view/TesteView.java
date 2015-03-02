package br.com.fiap.view;

import java.util.Calendar;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import br.com.fiap.dao.EscolaSambaDAO;
import br.com.fiap.dao.impl.EscolaSambaDAOImpl;
import br.com.fiap.entity.EscolaSamba;
import br.com.fiap.entity.Grupo;
import br.com.fiap.exceptions.CodigoNaoEncontradoException;
import br.com.fiap.exceptions.DBCommitException;

public class TesteView {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("CLIENTE_ORACLE");

		EntityManager em = factory.createEntityManager();

		EscolaSambaDAO dao = new EscolaSambaDAOImpl(em);
		int opcao;
		do {
			opcao = Integer.parseInt(JOptionPane.showInputDialog("Opção:\n"
					+ "1 - Buscar\n" + "2 - Cadastrar\n" + "3 - Atualizar\n"
					+ "4 - Remover\n" + "0 - Sair"));
			switch (opcao) {
			case 1:
				buscar(dao);
				break;
			case 2:
				cadastrar(dao);
				break;
			case 3:
				atualizar(dao);
				break;
			case 4:
				remover(dao);
				break;
			case 0:
				System.out.println("Sistema Finalizando...");
				break;
			default:
				System.out.println("Opção Inválida!");
				break;
			}
		}while (opcao != 0);
	}

	private static void buscar(EscolaSambaDAO dao) {
		int codigo = Integer.parseInt(JOptionPane
				.showInputDialog("Código para buscar"));
		EscolaSamba buscar = dao.searchById(codigo);
		if (buscar != null) {
			System.out.println(buscar.getNome());
			System.out.println(buscar.getIntegrantes());
			System.out.println(buscar.getBairro());
		} else {
			System.out.println("Escola não encontrada");
		}
	}

	private static void atualizar(EscolaSambaDAO dao) {
		// Buscar
		int codigo = Integer.parseInt(JOptionPane
				.showInputDialog("Código para atualizar"));
		EscolaSamba busca = dao.searchById(codigo);
		if (busca == null) {
			System.out.println("Código inválido");
			return; // Sai do método
		}

		// Atualizar
		busca.setNome(JOptionPane.showInputDialog("Nome para atualizar"));
		try {
			dao.update(busca);
		} catch (DBCommitException e1) {
			System.out.println(e1.getMessage());
		}
	}

	private static void remover(EscolaSambaDAO dao) {
		// Remover
		int idRemover = Integer.parseInt(JOptionPane
				.showInputDialog("Código para remover"));

		try {
			dao.delete(idRemover);
			System.out.println("Removido com sucesso!");
		} catch (CodigoNaoEncontradoException e) {
			System.out.println("Entidade não encontrada para ser removida!");
		} catch (DBCommitException e) {
			System.out.println("Erro de constraint ao deletar");
		}
	}

	private static void cadastrar(EscolaSambaDAO dao) {
		// Cadastrar
		EscolaSamba escola = new EscolaSamba();
		escola.setNome(JOptionPane.showInputDialog("Digite o nome"));
		escola.setBairro(JOptionPane.showInputDialog("Digite o Bairro"));
		escola.setIntegrantes(Integer.parseInt(JOptionPane
				.showInputDialog("Digite o número de integrantes")));
		escola.setDataFundacao(Calendar.getInstance());
		escola.setGrupo(Grupo.ESPECIAL);

		try {
			dao.create(escola);
		} catch (DBCommitException e1) {
			System.out.println(e1.getMessage());
		}
	}

}
