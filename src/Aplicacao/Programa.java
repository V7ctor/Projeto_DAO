package Aplicacao;

import java.sql.Connection;

import BD.Conexao;
import BD.DBExcecaoIntegracao;

public class Programa {

	public static void main(String[] args) {

		Connection con = null;
		
		try {
			
			con = Conexao.getConexao();
			
			
		} catch (RuntimeException e) {
			throw new DBExcecaoIntegracao(e.getMessage());
		}finally {
			Conexao.fecharConexao();
		}
	}

}
