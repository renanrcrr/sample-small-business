package br.com.rcrr.cadastro;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.rcrr.categoria.Categoria;
import br.com.rcrr.categoria.CategoriaDAO;
import br.com.rcrr.produto.Produto;
import br.com.rcrr.produto.ProdutoDAO;

public class Cadastro 
{
	public static void main(String[] args)
	{
		Cadastro cadastro = new Cadastro();
		cadastro.cadastraProdutos();
		cadastro.cadastraCategorias();
		
		System.out.println("Cadastros gerados com sucesso!");
	}
	
	public void cadastraProdutos()
	{
		String[] descricao = {"Bicicleta", "Televisão", "DVD"};
		Double[] preco = {356.44, 120.32, 200.32};
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = null;
		
		for(int i = 0; i < 3; i++)
		{
			produto = new Produto();
			produto.setDescricao(descricao[i]);
			produto.setPreco(preco[i]);
			produtoDAO.salvar(produto);
		}
	}
	
	public void cadastraCategorias()
	{
		String[] descricao = {"Utilidades", "Geral"};
		Categoria categoria = null;
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		ProdutoDAO produtoDAO = new ProdutoDAO();
		List<Produto> produtosListagem = produtoDAO.listar();
		Set<Produto> produtos = new HashSet<Produto>();
		
		for(int i = 0; i < produtosListagem.size(); i++)
		{
			produtos.add(produtosListagem.get(i));
		}
		
		for(int i = 0; i < 2; i++)
		{
			categoria = new Categoria();
			categoria.setDescricao(descricao[i]);
			categoria.setProdutos(produtos);
			categoriaDAO.salvar(categoria);
		}
	}
}
