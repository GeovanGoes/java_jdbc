package br.com.penselink.ensino.java.view;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import br.com.penselink.ensino.java.modelo.dao.PessoaDao;
import br.com.penselink.ensino.java.modelo.entidades.Pessoa;
import br.com.penselink.ensino.java.util.UtilDados;

/**
 * Classe principal do projeto
 * @author geovan.goes
 * */
public class Principal 
{
	
	/**Mensagem de sucesso*/
	private static final String MENSAGEM_SUCESSO = "Operação realizada com sucesso";
	/**Mensagem de erro*/
	private static final String MENSAGEM_ERRO = "Operação não realizada";
	
	/**
	 * Método principal do projeto
	 * */
	public static void main(String[] args) 
	{
		PessoaDao dao = new PessoaDao();
		
		int opcao = -1;

		System.out.println("Bem vindo ao sistema de gerenciamento de pessoas");
		
		Scanner sc = new Scanner(System.in);
		
		while(opcao != 0)
		{	
			
			System.out.println("=================================================");
			System.out.println("1. Cadastrar");
			System.out.println("2. Listar");
			System.out.println("3. Atualizar");
			System.out.println("4. Deletar");
			System.out.println("0. Finalizar");
			System.out.println("==================================================");
			
			opcao = sc.nextInt();
			
			switch(opcao){
				
			case 1:
				cadastrar(dao);				
				break;				
			case 2:
				imprimirPessoas(listar(dao));			
				break;
			case 3:
				imprimirPessoas(listar(dao));
				atualizar(dao);			
				break;	
			case 4:
				imprimirPessoas(listar(dao));
				deletar(dao);
				break;
			case 0:
				System.out.println("Adeus humano!");
				sc.close();
				break;
			default:
				System.out.println("Selecione uma opção válida");
				break;
			}	
		}
	}
	
	/**
	 * Método responsável por deletar uma pessoa
	 * @param PessoaDao dao
	 * */
	private static void deletar(PessoaDao dao) 
	{
		System.out.println("Digite o id da pessoa que deseja deletar:");
		Scanner sc = new Scanner(System.in);
		try 
		{
			String idText = sc.nextLine();
			
			Long id = Long.parseLong(idText);
			
			Pessoa pessoa = obterPessoaPorId(listar(dao), id);
			
			if(pessoa == null)
			{
				System.out.println("Pessoa não localizada");
			}else
			{
				System.out.println("Tem certeza que deseja excluir essa pessoa? S/N:");
				String resposta = sc.nextLine();
				
				String respostaUpper = resposta.toUpperCase();
				
				if(respostaUpper.equals("S"))
				{
					if(pessoa != null)
					{
						dao.excluir(pessoa);
						System.out.println(MENSAGEM_SUCESSO);
					}else
					{
						System.out.println(MENSAGEM_ERRO);
					}				
				}else
				{
					System.out.println(MENSAGEM_ERRO);
				}
			}			
		} catch (Exception parseExceptio) 
		{
			System.out.println(MENSAGEM_ERRO);
		}
	}

	/**
	 * Método responsável por atualizar uma pessoa
	 * @param PessoaDao dao
	 * */
	private static void atualizar(PessoaDao dao) 
	{
		System.out.println("Digite o id da pessoa que deseja atualizar:");
		Scanner sc = new Scanner(System.in);
		try 
		{
			String idText = sc.nextLine();
			
			Long id = Long.parseLong(idText);	
			
			Pessoa pessoa = obterPessoaPorId(listar(dao), id);
			if(pessoa != null)
			{
				exibirDadosPessoa(pessoa);
				
				Pessoa pessoaAtualizada = exibeFormulario(pessoa);
				while(pessoaAtualizada == null)
				{
					pessoaAtualizada = exibeFormulario(pessoa);
				}
				
				System.out.println("Salvar alterações? S/N:");
				String resposta = sc.nextLine();
				
				String respostaUpper = resposta.toUpperCase();
				
				if(respostaUpper.equals("S"))
				{
					pessoaAtualizada.setId(pessoa.getId());
					dao.atualizar(pessoaAtualizada);
					System.out.println(MENSAGEM_SUCESSO);
				}else
				{
					System.out.println(MENSAGEM_ERRO);
				}
			}else
			{
				System.out.println("Pessoa não localizada");
			}			
		} catch (Exception parseExceptio) 
		{
			System.out.println(MENSAGEM_ERRO);
		}
	}

	/**
	 * Método responsável por obter a lista de pessoas
	 * @param PessoaDao dao
	 * @return List<Pessoa>
	 * */
	private static List<Pessoa> listar(PessoaDao dao) 
	{
		List<Pessoa> pessoas = null;
		try 
		{
			pessoas = dao.listar();
		} catch (Exception e) 
		{
			System.out.println("Problemas ao listar as pessoas.");
		}
		return pessoas;
	}

	/**
	 * Método responsável por cadastrar uma pessoa
	 * @param PessoaDao dao
	 * */
	private static void cadastrar(PessoaDao dao)
	{
		try 
		{
			Pessoa pessoa = exibeFormulario(null);
			dao.inserir(pessoa);
			System.out.println(MENSAGEM_SUCESSO);
		} catch (Exception e) 
		{
			System.out.println(MENSAGEM_ERRO);
		}
	}
	
	
	/**
	 * Método responsável por imprimir uma lista de pessoas na tela
	 * @param List<Pessoa> pessoas
	 * */
	private static void imprimirPessoas(List<Pessoa> pessoas)
	{
		if(pessoas == null || pessoas.size() == 0)
		{
			System.out.println("Nenhuma pessoa encontrada.");
		}else
		{
			System.out.println("============PESSOAS==============");
			for (Pessoa pessoa : pessoas) 
			{
				exibirDadosPessoa(pessoa);
			}
		}
	}
	
	/**
	 * Método responsável por obter uma pessoa que está inserida numa lista com base no seu id
	 * @param List<Pessoa> pessoas
	 * @param Long id
	 * @return Pessoa
	 * */
	private static Pessoa obterPessoaPorId(List<Pessoa> pessoas, Long id)
	{
		Pessoa result = null;
		
		if(pessoas != null && pessoas.size() != 0)
		{
			for (Pessoa pessoa : pessoas) 
			{
				if(pessoa.getId().equals(id))
				{
					result = pessoa;
				}
			}
		}
		return result;
	}
	
	/**
	 * Método responsável por exibir os dados da pessoa passada como parâmetro
	 * @param Pessoa pessoa
	 * */
	private static void exibirDadosPessoa(Pessoa pessoa)
	{
		if(pessoa != null)
		{
			System.out.println("");
			System.out.println("Id: " + pessoa.getId());
			System.out.println("Nome: " + pessoa.getNome());
			System.out.println("Email: " + pessoa.getEmail());
			System.out.println("Telefone: " + pessoa.getTelefone());
			System.out.println("Aniversário: " + pessoa.getAniversario());
			System.out.println("");
		}else
		{
			System.out.println(MENSAGEM_ERRO);
		}
	}
	
	/**
	 * Método responsável por exibir o formulário de cadastro/edicao de uma pessoa
	 * Se o parâmetro passado(pessoa) for == a null entende-se que é um cadastro, diferente disso é uma edição
	 * @param Pessoa pessoa
	 * @return Pessoa
	 * */
	private static Pessoa exibeFormulario(Pessoa pessoa)
	{
		Pessoa pessoaAtualizada = null;
		Scanner sc = new Scanner(System.in);
		try 
		{
			
			if(pessoa == null)
			{
				pessoaAtualizada = new Pessoa();
				
				System.out.println("Nome:");
				String nome = sc.nextLine();
				pessoaAtualizada.setNome(nome);
				
				System.out.println("Email:");
				String email = sc.nextLine();
				pessoaAtualizada.setEmail(email);
				
				System.out.println("Telefone:");
				String telefone = sc.nextLine();
				pessoaAtualizada.setTelefone(telefone);
				
				System.out.println("Aniversário");
				String aniversarioText = sc.nextLine();
				Date aniversarioData = UtilDados.stringToCalendar(aniversarioText).getTime();
				pessoaAtualizada.setAniversario(aniversarioData);
			}else
			{
				pessoaAtualizada = new Pessoa();
				
				System.out.println("Para manter a informação anterior, apenas pressione enter");
				
				System.out.println("Nome:");
				String nome = sc.nextLine();
				pessoaAtualizada.setNome(nome == null || nome.equals("") ? pessoa.getNome() : nome);
				
				System.out.println("Email:");
				String email = sc.nextLine();
				pessoaAtualizada.setEmail(email == null || email.equals("") ? pessoa.getEmail() : email);
				
				System.out.println("Telefone:");
				String telefone = sc.nextLine();
				pessoaAtualizada.setTelefone(telefone == null || telefone.equals("") ? pessoa.getTelefone() : telefone);
				
				System.out.println("Aniversário");
				String aniversarioText = sc.nextLine();
				Date aniversarioData = aniversarioText == null || aniversarioText.equals("") ? pessoa.getAniversario() : UtilDados.stringToCalendar(aniversarioText).getTime();
				pessoaAtualizada.setAniversario(aniversarioData);
			}
		} catch (Exception e) 
		{
			System.out.println("Dados inválidos");
		}
		return pessoaAtualizada;
	}
}