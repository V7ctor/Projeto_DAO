package DAO;

import java.util.List;

import Entidades.Vendedor;

public interface VendedorDAO {

	void inserirDados(Vendedor vendedor);
	void atualizarDados(Vendedor vendedor);
	void excluirPerID(Vendedor vendedor);
	Vendedor pesquisarPerID(Vendedor vendedor);
	List<Vendedor> encontrarTodos();
	
}
