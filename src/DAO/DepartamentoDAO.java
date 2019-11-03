package DAO;

import java.util.List;

import Entidades.Departamento;

public interface DepartamentoDAO {

	void inserirDados(Departamento departamento);
	void atualizarDados(Departamento departamento);
	void excluirPerID(Departamento departamento);
	Departamento pesquisarPerID(Departamento departamento);
	List<Departamento> encontrarTodos();
}
