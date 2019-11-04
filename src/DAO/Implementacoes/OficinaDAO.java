package DAO.Implementacoes;

import BD.Conexao;
import DAO.VendedorDAO;

public class OficinaDAO {

	public static VendedorDAO criarVendedorDAO() {
		return new VendedorDaoJDBC(Conexao.getConexao());
	}
}
