package Aplicacao;

import java.util.List;

import DAO.DepartamentoDAO;
import DAO.Implementacoes.OficinaDAO;
import Entidades.Departamento;

public class Programa2 {

	public static void main(String[] args) {

		DepartamentoDAO departamentoDAO = OficinaDAO.criarDepartamentoDAO();
		
		
		System.out.println("=== Teste 1: Inserindo Departamento ===");
			Departamento dep1 = new Departamento(null, "Ciências");
			departamentoDAO.inserirDados(dep1);
			System.out.println("Inserido!, novo ID é: "+dep1.getId());
	
		System.out.println();
		
		System.out.println("=== Teste 2: Atualizando Departamento ===");
			Departamento dep2 = departamentoDAO.pesquisarPerID(6);
			dep2.setNome("Crime");
			departamentoDAO.atualizarDados(dep2);
			System.out.println("Atualizacao Completa!!");
	
		System.out.println();
		
		System.out.println("=== Teste 3: deletando Departamento ===");
			departamentoDAO.excluirPerID(7);
			System.out.println("Deleção Completa!!");
		
		System.out.println();
	
		System.out.println("=== Teste 4: Departamento pesquisa Por Tudo ===");
		List<Departamento> lista = departamentoDAO.encontrarTodos();
		for (Departamento obj : lista) {
			System.out.println(obj);
		}
	
		System.out.println("=== Teste 5: Departamento pesquisa Por Id ===");
			Departamento dep3 = departamentoDAO.pesquisarPerID(3);
			System.out.println(dep3);
		
		System.out.println();
	}

}
