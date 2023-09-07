package labs.dibits.ecommerce;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

@SpringBootApplication
public class EcommerceApplication implements CommandLineRunner {
	
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
	
	

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
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
		
		
		
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
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
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		

		categoriaRepostory.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		estadoRepository.saveAll(Arrays.asList(e1,e2,e3));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3,c4));
		
		Cliente cli1 = new Cliente(null, "Diogo Martins", "dibits.labs@gmail.com", "02208937139", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("64992768765","34998336599"));
		
		Endereco end1 = new Endereco(null, "Alameda dos Jaos", "2", "Qd 2 lt 1", "Fauna II", "75667000", cli1, c4);
		Endereco end2 = new Endereco(null, "Avenida Brasil", "2115", "Apto 212", "Brasil", "38400718", cli1, c1);
		
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
