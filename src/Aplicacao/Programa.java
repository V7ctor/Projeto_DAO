package Aplicacao;

import DAO.VendedorDAO;
import DAO.Implementacoes.OficinaDAO;
import Entidades.Vendedor;

public class Programa {

	public static void main(String[] args) {

		VendedorDAO vendedorDAO = OficinaDAO.criarVendedorDAO();
		
		System.out.println("=== Teste 1: Vendedor pesquisaPerID ===");
		Vendedor vend = vendedorDAO.pesquisarPerID(3);
		
		System.out.println(vend);
	}

}
