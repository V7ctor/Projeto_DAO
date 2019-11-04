package Aplicacao;

import java.util.List;

import DAO.VendedorDAO;
import DAO.Implementacoes.OficinaDAO;
import Entidades.Departamento;
import Entidades.Vendedor;

public class Programa {

	public static void main(String[] args) {

		VendedorDAO vendedorDAO = OficinaDAO.criarVendedorDAO();
		
		System.out.println("=== Teste 1: Vendedor pesquisa Por ID ===");
		Vendedor vend = vendedorDAO.pesquisarPerID(3);
		System.out.println(vend);
		
		System.out.println();
		
		System.out.println("=== Teste 2: Vendedor pesquisa Por Departamento ===");
		Departamento dep = new Departamento(2, null);
		List<Vendedor> lista = vendedorDAO.encontrarPerDepartamento(dep);
		for (Vendedor obj : lista) {
			System.out.println(obj);
		}
	}

}
