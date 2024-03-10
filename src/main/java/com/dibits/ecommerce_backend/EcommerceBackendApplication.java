package com.dibits.ecommerce_backend;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dibits.ecommerce_backend.domain.Categoria;
import com.dibits.ecommerce_backend.domain.Produto;
import com.dibits.ecommerce_backend.repositories.CategoriaRepository;
import com.dibits.ecommerce_backend.repositories.ProdutoRepository;

@SpringBootApplication
public class EcommerceBackendApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepostory;
	@Autowired
	private ProdutoRepository produtoRepository;

	public static void main(String[] args) {
		SpringApplication.run(EcommerceBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Colecionáveis");
		Categoria cat4 = new Categoria(null, "Vale Compras");
		Categoria cat5 = new Categoria(null, "Decoração");
		Categoria cat6 = new Categoria(null, "E-books");
		Categoria cat7 = new Categoria(null, "DIY");
		
		/*-------------------------------------------------------------*/
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		Produto p4 = new Produto(null, "Cartao Playstore", 50.00);
		Produto p5 = new Produto(null, "Cartao Americanas", 50.00);
		Produto p6 = new Produto(null, "Cartao Carrefour", 50.00);
		Produto p7 = new Produto(null, "Pingente", 30.00);
		Produto p8 = new Produto(null, "Livro How to NFT", 10.00);
		Produto p9 = new Produto(null, "Livro How to DEFI ", 10.00);
		Produto p10 = new Produto(null, "Boneco One Piece", 180.00);
		Produto p11 = new Produto(null, "Abajour Gamer", 100.00);
		
		
		/*-------------------------------------------------------------*/
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p1,p2, p3));
		cat3.getProdutos().addAll(Arrays.asList(p10));
		cat4.getProdutos().addAll(Arrays.asList(p4,p5, p6));
		cat5.getProdutos().addAll(Arrays.asList(p7,p10, p11));
		cat6.getProdutos().addAll(Arrays.asList(p8,p9));
		cat7.getProdutos().addAll(Arrays.asList(p7));
		
		/*-------------------------------------------------------------*/
		
		p1.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p4.getCategorias().addAll(Arrays.asList(cat4));
		p5.getCategorias().addAll(Arrays.asList(cat4));
		p6.getCategorias().addAll(Arrays.asList(cat4));
		p7.getCategorias().addAll(Arrays.asList(cat5, cat7));
		p8.getCategorias().addAll(Arrays.asList(cat6));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat3, cat5));
		p11.getCategorias().addAll(Arrays.asList(cat5));
		
		/*-------------------------------------------------------------*/
		/*-------------------------------------------------------------*/
		
		categoriaRepostory.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11));
	}

}
