package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The Class DAO.
 */
public class DAO {
	
	/** The driver. */
	private final String driver = "com.mysql.cj.jdbc.Driver";
	
	/** The url. */
	private final String url = "jdbc:mysql://localhost:3306/dbagenda?useSSL=false&allowPublicKeyRetrieval=true";
	
	/** The user. */
	private final String user = "developer1";
	
	/** The password. */
	private final String password = "1234567";
	
	/**
	 * Inserir contato.
	 *
	 * @param contato the contato
	 */
	public void inserirContato(JavaBeans contato) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url, user, password);
						
			ps = conn.prepareStatement(
				"insert into contatos (Nome,Telefone,Email) "
			    +"values (?,?,?)");
			
			 ps.setString(1, contato.getNome());
		     ps.setString(2, contato.getTelefone());
		     ps.setString(3, contato.getEmail());
		     
		     int linhasAlteradas = ps.executeUpdate();
					
		     System.out.printf("Fim! O contato foi adicionado. %d linha(s) alterada(s).\n", linhasAlteradas);
		     
		} catch(SQLException  | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) ps.close();
				if(conn != null) conn.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Listar contato.
	 *
	 * @return the array list
	 */
	public ArrayList<JavaBeans> listarContato() {
		ArrayList<JavaBeans> contatos = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url, user, password);
						
			ps = conn.prepareStatement(
				"select * from contatos order by nome;");
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String id = rs.getString(1);
				String nome = rs.getString(2);
				String telefone = rs.getString(3);
				String email = rs.getString(4);

				contatos.add(new JavaBeans(id, nome, telefone, email));
			}
								
		} catch(SQLException  | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) ps.close();
				if(rs != null) rs.close();
				if(conn != null) conn.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return contatos;
	}
	
	/**
	 * Selecionar contato.
	 *
	 * @param contato the contato
	 */
	public void selecionarContato(JavaBeans contato) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
				
			conn = DriverManager.getConnection(url, user, password);
							
			ps = conn.prepareStatement(
				  "select * from contatos where id = ?");
			ps.setString(1, contato.getId());
			rs = ps.executeQuery();
			
			while(rs.next()) {
				contato.setId(rs.getString(1));
				contato.setNome(rs.getString(2));
				contato.setTelefone(rs.getString(3));
				contato.setEmail(rs.getString(4));
			}
							
		} catch(SQLException  | ClassNotFoundException e){
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) ps.close();
				if(rs != null) rs.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Alterar contato.
	 *
	 * @param contato the contato
	 */
	public void alterarContato(JavaBeans contato) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url, user, password);
							
			ps = conn.prepareStatement(
					"update contatos set nome=?, telefone=?, email=? "
					+ "where id=?");
			ps.setString(1, contato.getNome());
			ps.setString(2, contato.getTelefone());
			ps.setString(3, contato.getEmail());
			ps.setString(4, contato.getId());
			
			ps.executeUpdate();
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) ps.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Deletar contato.
	 *
	 * @param contato the contato
	 */
	public void deletarContato(JavaBeans contato) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url, user, password);
							
			ps = conn.prepareStatement("delete from contatos where id = ?");
		
			ps.setString(1, contato.getId());
			
			ps.executeUpdate();
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps != null) ps.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

