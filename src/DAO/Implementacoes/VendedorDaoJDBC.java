package DAO.Implementacoes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.Statement;

import BD.Conexao;
import BD.DBExcecao;
import DAO.VendedorDAO;
import Entidades.Departamento;
import Entidades.Vendedor;

public class VendedorDaoJDBC implements VendedorDAO {

	private Connection conn;
	
	public VendedorDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void inserirDados(Vendedor vendedor) {
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(
					"INSERT INTO vendedor" + 
					"(Nome, Email, DataNascimento, SalarioBase, DepartamentoId) " + 
					"VALUES " + 
					"(?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			pst.setString(1, vendedor.getNome());
			pst.setString(2, vendedor.getEmail());
			pst.setDate(3, new java.sql.Date(vendedor.getDataNascimento().getTime()));
			pst.setDouble(4, vendedor.getSalarioBase());
			pst.setInt(5, vendedor.getDepartamento().getId());
			
			int linhasAfetadas = pst.executeUpdate();
			
			if (linhasAfetadas > 0) {
				ResultSet rs = pst.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					vendedor.setId(id);
				}
				Conexao.fecharResultSet(rs);
			} else {
				throw new DBExcecao("Erro: Nenhuma linha foi afetada: ");
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			Conexao.fecharStatement(pst);
		}
	}

	@Override
	public void atualizarDados(Vendedor vendedor) {
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(
				    "UPDATE vendedor " + 
				    "SET Nome = ?, Email = ?, DataNascimento = ?, SalarioBase = ?, DepartamentoId = ? " + 
				    "WHERE Id = ?");
			
			pst.setString(1, vendedor.getNome());
			pst.setString(2, vendedor.getEmail());
			pst.setDate(3, new java.sql.Date(vendedor.getDataNascimento().getTime()));
			pst.setDouble(4, vendedor.getSalarioBase());
			pst.setInt(5, vendedor.getDepartamento().getId());
			pst.setInt(6, vendedor.getId());
			
			pst.executeUpdate();
			
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			Conexao.fecharStatement(pst);
		}
	}

	@Override
	public void excluirPerID(Integer Id) {
		PreparedStatement pst = null;
		
		try {
			
			pst = conn.prepareStatement("DELETE FROM vendedor WHERE Id = ?");
			
			pst.setInt(1, Id);	
			pst.executeUpdate();
			
		}catch (SQLException e) {
			throw new DBExcecao(e.getMessage());
		} finally {
			Conexao.fecharStatement(pst);
		}
	}

	@Override
	public Vendedor pesquisarPerID(Integer Id) {
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			
			pst = conn.prepareStatement(
					"SELECT vendedor.*,departamento.Nome as DepNome " + 
					"FROM vendedor INNER JOIN departamento " + 
					"ON vendedor.DepartamentoId = departamento.Id " + 
					"WHERE vendedor.Id = ?"
					);
			
			pst.setInt(1, Id);
			rs = pst.executeQuery();
			
			// Por padrão, o nosso resultSet começa com 0, caso ele retorne um registro, testaremos abaixo: 
			if (rs.next()) {
				Departamento dep = instanciaDepartamento(rs);
				Vendedor vend = instanciaVendedor(rs, dep);
				return vend;
			}
			return null; // Caso não tenha nenhum registro com o Id passado como argumento, então o método retornará nulo.
		} catch (SQLException e) {
			throw new DBExcecao(e.getMessage());
		}finally {
			Conexao.fecharResultSet(rs);
			Conexao.fecharStatement(pst);
		}
	}

	private Vendedor instanciaVendedor(ResultSet rs, Departamento dep) throws SQLException {
		Vendedor vend = new Vendedor();
		vend.setId(rs.getInt("Id"));
		vend.setNome(rs.getString("Nome"));
		vend.setEmail(rs.getString("Email"));
		vend.setSalarioBase(rs.getDouble("SalarioBase"));
		vend.setDataNascimento(rs.getDate("DataNascimento"));
		vend.setDepartamento(dep);
		return vend;
	}
	
	private Departamento instanciaDepartamento(ResultSet rs) throws SQLException {
		Departamento dep = new Departamento();
		dep.setId(rs.getInt("DepartamentoId"));
		dep.setNome(rs.getString("DepNome"));
		return dep;
	}
	
	@Override
	public List<Vendedor> encontrarTodos() {
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			
			pst = conn.prepareStatement(
					  "SELECT vendedor.*,departamento.Nome as DepNome " + 
		              "FROM vendedor INNER JOIN departamento " + 
		              "ON vendedor.DepartamentoId = departamento.Id " + 
		              "ORDER BY Nome"
					);
 
			rs = pst.executeQuery();
			
			List<Vendedor> lista = new ArrayList<Vendedor>();
			Map<Integer, Departamento> map = new HashMap<>();			
			
			while (rs.next()) {
				
				Departamento dep = map.get(rs.getInt("DepartamentoId"));
				
				if (dep == null) {
					dep = instanciaDepartamento(rs);
					map.put(rs.getInt("DepartamentoId"), dep);
				}

				Vendedor vend = instanciaVendedor(rs, dep);		
				lista.add(vend);
				
			}
			
			
				
			return lista;

		} catch (SQLException e) {
			throw new DBExcecao(e.getMessage());
		}finally {
			Conexao.fecharResultSet(rs);
			Conexao.fecharStatement(pst);
		}
	}
	
	@Override
	public List<Vendedor> encontrarPerDepartamento(Departamento departamento) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			
			pst = conn.prepareStatement(
					  "SELECT vendedor.*,departamento.Nome as DepNome " + 
		              "FROM vendedor INNER JOIN departamento " + 
		              "ON vendedor.DepartamentoId = departamento.Id " + 
		              "WHERE DepartamentoId = ? " + 
		              "ORDER BY Nome"
					);
 
			pst.setInt(1, departamento.getId());		
			rs = pst.executeQuery();
			
			List<Vendedor> lista = new ArrayList<Vendedor>();
			Map<Integer, Departamento> map = new HashMap<>();			
			
			while (rs.next()) {
				
				Departamento dep = map.get(rs.getInt("DepartamentoId"));
				
				if (dep == null) {
					dep = instanciaDepartamento(rs);
					map.put(rs.getInt("DepartamentoId"), dep);
				}

				Vendedor vend = instanciaVendedor(rs, dep);		
				lista.add(vend);
				
			}
			
			
				
			return lista;

		} catch (SQLException e) {
			throw new DBExcecao(e.getMessage());
		}finally {
			Conexao.fecharResultSet(rs);
			Conexao.fecharStatement(pst);
		}
	}

}
