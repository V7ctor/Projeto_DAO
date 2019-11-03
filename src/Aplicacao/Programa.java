package Aplicacao;

import java.sql.Connection;
import java.util.Date;

import BD.Conexao;
import BD.DBExcecaoIntegracao;
import DAO.VendedorDAO;
import DAO.Implementacoes.OficinaDAO;
import Entidades.Departamento;
import Entidades.Vendedor;

public class Programa {

	public static void main(String[] args) {

		Connection con = null;
		
		try {
			
			con = Conexao.getConexao();
		    
			Departamento dep = new Departamento(1, "Livros");
			Vendedor vend = new Vendedor(1, "Majaha", "Piratao@gmail.com", new Date() , 700.0, dep);
			
			VendedorDAO vendDAO = OficinaDAO.criarVendedorDAO();
			
			System.out.println(vend);
			
		} catch (RuntimeException e) {
			throw new DBExcecaoIntegracao(e.getMessage());
		}finally {
			Conexao.fecharConexao();
		}
	}

}
