package br.com.rcrr.produto;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.rcrr.util.HibernateUtil;

public class ProdutoDAO 
{
	private Session sessao;
	private Transaction transacao;
	
	public void salvar(Produto produto)
	{		
		try
		{
			this.sessao = HibernateUtil.getSessionFactory().openSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.save(produto);
			this.transacao.commit();
		}
		catch(Throwable e)
		{
			System.out.println("N�o foi poss�vel salvar o produto. Erro: " + e.getMessage());
		}
		finally
		{
			try
			{
				if(this.sessao.isOpen())
					this.sessao.close();
			}
			catch(HibernateException e)
			{
				System.out.println("Erro ao fechar opera��o de inser��o. Mensagem: " + e.getMessage());
			}
		}
	}
	
	public void atualizar(Produto produto)
	{
		try
		{
			this.sessao = HibernateUtil.getSessionFactory().openSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.update(produto);
			this.transacao.commit();
		}
		catch(HibernateException e)
		{
			System.out.println("N�o foi poss�vel alterar o produto. Erro: " + e.getMessage());
		}
		finally
		{
			try
			{
				if(this.sessao.isOpen())
					sessao.close();
			}
			catch(Throwable e)
			{
				System.out.println("Erro ao fechar a opera��o de atualiza��o. Erro: " + e.getMessage());
			}
		}
	}
	
	public void excluir(Produto produto)
	{
		try
		{
			this.sessao = HibernateUtil.getSessionFactory().openSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.delete(produto);
			this.transacao.commit();
		}
		catch(HibernateException e)
		{
			System.out.println("N�o foi poss�vel excluir o produto. Erro: " + e.getMessage());
		}
		finally
		{
			try
			{
				if(this.sessao.isOpen())
				sessao.close();
			}
			catch(Throwable e)
			{
				System.out.println("Erro ao fechar a opera��o de exclus�o. Erro: " + e.getMessage());
			}
		}
	}
	
	public Produto buscaCategoria(Integer codigo)
	{
		Produto produto = null;
		
		try
		{
			this.sessao = HibernateUtil.getSessionFactory().openSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Produto.class);
			filtro.add(Restrictions.eq("produto", codigo));
			produto = (Produto) filtro.uniqueResult();
			transacao.commit();			
		}
		catch(Throwable e)
		{
			if(this.transacao.isActive())
				this.transacao.rollback();
		}
		finally
		{
			try
			{
				sessao.close();
			}
			catch(Throwable e)
			{
				System.out.println("Erro ao fechar a opera��o buscar. Erro: " + e.getMessage());
			}
		}
		return produto;
	}
	
	public List<Produto> listar()
	{
		List<Produto> produtos = null;
		
		try
		{
			this.sessao = HibernateUtil.getSessionFactory().openSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Produto.class);
			produtos = filtro.list();
			this.transacao.commit();			
		}
		catch(HibernateException e)
		{
			if(this.transacao.isActive())
				this.transacao.rollback();
		}
		finally
		{
			try
			{
				if(this.sessao.isOpen())
				this.sessao.close();
			}
			catch(Throwable e)
			{
				System.out.println("Erro ao fechar a opera��o de consulta. Erro: " + e.getMessage());
			}
		}
		return produtos;
	}

}
