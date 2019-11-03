package DAO.Implementacoes;

import DAO.VendedorDAO;

public class OficinaDAO {

	public static VendedorDAO criarVendedorDAO() {
		return new VendedorDaoJDBC();
	}
}
