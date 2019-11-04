package DAO;

import java.util.List;

import Entidades.Departamento;
import Entidades.Vendedor;

public interface VendedorDAO {

	void inserirDados(Vendedor vendedor);
	void atualizarDados(Vendedor vendedor);
	void excluirPerID(Vendedor vendedor);
	Vendedor pesquisarPerID(Integer Id);
	List<Vendedor> encontrarTodos();
	List<Vendedor> encontrarPerDepartamento(Departamento departamento);
	
}
