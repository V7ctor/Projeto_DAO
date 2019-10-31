package Aplicacao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import BD.Conexao;
import BD.DBExcecaoIntegracao;
import Entidades.Departamento;

public class Programa {

	public static void main(String[] args) {

		Connection con = null;
		
		try {
			
			con = Conexao.getConexao();
		    
			List<Departamento> dep = new ArrayList<>();
			
			dep.add(new Departamento(1, "Livros"));
			dep.add(new Departamento(2, "Bibliotecas"));
			dep.add(new Departamento(3, "Faxinaria"));
			dep.add(new Departamento(4, "Administracao"));
			dep.add(new Departamento(5, "Programação"));
			
			for (Departamento d : dep) {
				System.out.println(d.toString());
			}
			
		} catch (RuntimeException e) {
			throw new DBExcecaoIntegracao(e.getMessage());
		}finally {
			Conexao.fecharConexao();
		}
	}

}
