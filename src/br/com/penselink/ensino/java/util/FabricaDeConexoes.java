package br.com.penselink.ensino.java.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Essa classe cria uma nova conexão com o banco de dados quando solicitado
 * @author geovan.goes
 * */
public class FabricaDeConexoes 
{

	/**
	 * Método estático responsável por criar uma nova conexão com o banco de dados MySQL
	 * @return
	 * */
	public static final Connection obterConexao()
	{
		Connection connection = null;
		try
		{
			/**Carrega o driver do MySQL usando reflection*/
			Class.forName("com.mysql.jdbc.Driver");
			
			/**Essas configurações geralmente são salvas em um arquivo de configuração e não diretamente no código*/
			String servidor = "localhost";
			String banco = "stb_bd";
			String usuario = "root";
			String senha = "root";
			
			/**Estabelecendo conexão com o banco de dados*/
			connection = DriverManager.getConnection("jdbc:mysql://"+servidor+"/"+banco+"",usuario,senha);
		} catch (ClassNotFoundException e) 
		{
			/**Caso exista algum problema com o carregamento do driver do MySQL essa exceção será lancada*/
			System.out.println("Problemas ao carregar o driver do MySQL");
			e.printStackTrace();
			throw new RuntimeException(e);
		}		
		catch(SQLException e)
		{
			/**Caso exista qualquer outro problema relacionado a conexão com o banco essa exceção será lançada*/
			System.out.println(e);
			throw new RuntimeException(e);
		}
		
		return connection;
	}
	
	/**
	 * Método responsável por fechar a conexão com o banco de dados
	 * @param Connection conexao
	 * */
	public static final void fecharConexao(Connection conexao)
	{
		try 
		{
			conexao.close();
		} catch (Exception e) 
		{
			System.out.println("Problemas ao fechar a conexão com o banco de dados");
		}
	}
}
