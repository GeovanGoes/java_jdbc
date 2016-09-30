package br.com.penselink.ensino.java.modelo.dao;

import br.com.penselink.ensino.java.modelo.entidades.Pessoa;
import br.com.penselink.ensino.java.util.FabricaDeConexoes;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoaDao 
{	
	
	/**
	 * Método responsável por persistir uma pessoa
	 * */
	public boolean inserir(Pessoa pessoa)
	{
		boolean resultado = true;
		Connection conexao = FabricaDeConexoes.obterConexao();
		
		if(conexao != null)
		{
			try 
			{
				String query = "INSERT INTO PESSOAS (nome,email,telefone,aniversario) values (?,?,?,?)";
				PreparedStatement statement = conexao.prepareStatement(query);
				
				statement.setString(1, pessoa.getNome());
				statement.setString(2, pessoa.getEmail());
				statement.setString(3, pessoa.getTelefone());
				statement.setDate(4, new Date(pessoa.getAniversario().getTime()));
				
				statement.execute();
			} catch (Exception e) 
			{
				System.out.println("Problemas ao persistir uma pessoa no SGBD.");
				e.printStackTrace();
			}finally
			{
				FabricaDeConexoes.fecharConexao(conexao);
			}
		}
		
		return resultado;
	}
	
	/**
	 * Método responsável por atualizar uma pessoa
	 * @param Pessoa pessoa
	 * @return boolean
	 * @throws SQLException
	 * */
	public boolean atualizar(Pessoa pessoa)
	{
		boolean resultado = true;
		Connection conexao = FabricaDeConexoes.obterConexao();
		
		if(conexao != null)
		{
			try 
			{
				String query = "UPDATE PESSOAS set nome = ? ,email = ? ,telefone = ? ,aniversario = ? WHERE id = ?";
				PreparedStatement statement = conexao.prepareStatement(query);
				
				statement.setString(1, pessoa.getNome());
				statement.setString(2, pessoa.getEmail());
				statement.setString(3, pessoa.getTelefone());
				statement.setDate(4, new Date(pessoa.getAniversario().getTime()));
				statement.setLong(5, pessoa.getId());
				
				statement.execute();
			} catch (Exception e) 
			{
				System.out.println("Problemas ao persistir uma pessoa no SGBD.");
				e.printStackTrace();
			}finally
			{
				FabricaDeConexoes.fecharConexao(conexao);
			}
		}		
		return resultado;
	}
	
	/**
	 * Método responsável por excluir uma pessoa
	 * @param Pessoa pessoa
	 * @return boolean
	 * @throws throws SQLException
	 * */
	public boolean excluir(Pessoa pessoa)
	{
		boolean resultado = true;
		Connection conexao = FabricaDeConexoes.obterConexao();
		
		if(conexao != null)
		{
			try 
			{
				String query = "DELETE FROM PESSOAS WHERE id = ?";
				PreparedStatement statement = conexao.prepareStatement(query);
				
				statement.setLong(1, pessoa.getId());
				
				statement.execute();
			} catch (Exception e) 
			{
				System.out.println("Problemas ao persistir uma pessoa no SGBD.");
				e.printStackTrace();
			}finally
			{
				FabricaDeConexoes.fecharConexao(conexao);
			}
		}		
		return resultado;
	}
	
	/**
	 * Método responsável por listar as pessoas
	 * @return List<Pessoa>
	 * @throws SQLException
	 * */
	public List<Pessoa> listar()
	{
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		Connection conexao = FabricaDeConexoes.obterConexao();
		
		if(conexao != null)
		{
			try 
			{
				String query = "SELECT * FROM PESSOAS";
				PreparedStatement statement = conexao.prepareStatement(query);				
				ResultSet queryResult = statement.executeQuery();
				while(queryResult.next())
				{
					Pessoa pessoa = new Pessoa();
					pessoa.setId(queryResult.getLong("id"));
					pessoa.setNome(queryResult.getString("nome"));
					pessoa.setEmail(queryResult.getString("email"));
					pessoa.setTelefone(queryResult.getString("telefone"));
					pessoa.setAniversario(new Date(queryResult.getDate("aniversario").getTime()));
					
					pessoas.add(pessoa);
				}
			} catch (Exception e) 
			{
				System.out.println("Problemas ao listar as pessoas.");
				e.printStackTrace();
			}finally
			{
				FabricaDeConexoes.fecharConexao(conexao);
			}
		}		
		return pessoas;
	}
}
