package labs.dibits.ecommerce.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import labs.dibits.ecommerce.domain.Categoria;
import labs.dibits.ecommerce.domain.Cidade;
import labs.dibits.ecommerce.domain.Cliente;
import labs.dibits.ecommerce.domain.Endereco;
import labs.dibits.ecommerce.domain.Estado;
import labs.dibits.ecommerce.domain.ItemPedido;
import labs.dibits.ecommerce.domain.Pagamento;
import labs.dibits.ecommerce.domain.PagamentoComBoleto;
import labs.dibits.ecommerce.domain.PagamentoComCartao;
import labs.dibits.ecommerce.domain.Pedido;
import labs.dibits.ecommerce.domain.Produto;
import labs.dibits.ecommerce.domain.enums.EstadoPagamento;
import labs.dibits.ecommerce.domain.enums.TipoCliente;
import labs.dibits.ecommerce.repositories.CategoriaRepository;
import labs.dibits.ecommerce.repositories.CidadeRepository;
import labs.dibits.ecommerce.repositories.ClienteRepository;
import labs.dibits.ecommerce.repositories.EnderecoRepository;
import labs.dibits.ecommerce.repositories.EstadoRepository;
import labs.dibits.ecommerce.repositories.ItemPedidoRepository;
import labs.dibits.ecommerce.repositories.PagamentoRepository;
import labs.dibits.ecommerce.repositories.PedidoRepository;
import labs.dibits.ecommerce.repositories.ProdutoRepository;


@Service
public class DBService {

	@Autowired
	private CategoriaRepository categoriaRepostory;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public void instantiateTestDatabase() throws ParseException {
		
		

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Colecionáveis");
		Categoria cat4 = new Categoria(null, "Vale Compras");
		Categoria cat5 = new Categoria(null, "Decoração");
		Categoria cat6 = new Categoria(null, "E-books");
		Categoria cat7 = new Categoria(null, "DIY");
		
		
		
		
		
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
		
		
		Estado e1 = new Estado(null,"Minas Gerais");
		Estado e2 = new Estado(null,"São Paulo");
		Estado e3 = new Estado(null, "Goiás");
		
		Cidade c1 = new Cidade(null, "Uberlândia",e1);
		Cidade c2 = new Cidade(null, "São Paulo",e2);
		Cidade c3 = new Cidade(null, "Campinas",e2);
		Cidade c4 = new Cidade(null, "Rio Quente",e3);
		
		e1.getCidades().addAll(Arrays.asList(c1));
		e2.getCidades().addAll(Arrays.asList(c2, c3));
		e3.getCidades().addAll(Arrays.asList(c4));
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p1,p2, p3));
		cat3.getProdutos().addAll(Arrays.asList(p10));
		cat4.getProdutos().addAll(Arrays.asList(p4,p5, p6));
		cat5.getProdutos().addAll(Arrays.asList(p7,p10, p11));
		cat6.getProdutos().addAll(Arrays.asList(p8,p9));
		cat7.getProdutos().addAll(Arrays.asList(p7));
		
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
		
		

		categoriaRepostory.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11));
		estadoRepository.saveAll(Arrays.asList(e1,e2,e3));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3,c4));
		
		Cliente cli1 = new Cliente(null, "Diogo Martins", "dibits.labs@gmail.com", "80118723030", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("64992768765","34998336599"));
		
		Endereco end1 = new Endereco(null, "Alameda dos Brasileiros", "2", "Qd 2 lt 1", "Brasilzinho", "77777000", cli1, c4);
		Endereco end2 = new Endereco(null, "Avenida Brasil", "2112", "Apto 212", "Brasil", "77777001", cli1, c1);
		
		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(end1, end2));
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("05/09/2023 10:25"), cli1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("05/09/2023 18:00"), cli1, end2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagtot2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("05/10/2023 00:00"), null);
		ped2.setPagamento(pagtot2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagtot2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
		
	}
	
}
