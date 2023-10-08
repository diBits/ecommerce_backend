package labs.dibits.ecommerce.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import labs.dibits.ecommerce.domain.ItemPedido;
import labs.dibits.ecommerce.domain.PagamentoComBoleto;
import labs.dibits.ecommerce.domain.Pedido;
import labs.dibits.ecommerce.domain.enums.EstadoPagamento;
import labs.dibits.ecommerce.repositories.ItemPedidoRepository;
import labs.dibits.ecommerce.repositories.PagamentoRepository;
import labs.dibits.ecommerce.repositories.PedidoRepository;
import labs.dibits.ecommerce.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	
	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository  pagamentoRepository;
	
	@Autowired
	private ProdutoService  produtoService;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public Pedido find(Integer id) {
		
		Pedido obj = repo.findById(id).orElse(null);
		if (obj == null) {
			
			throw new ObjectNotFoundException("Objeto inexistente! Id: "+id+
				", Pacote: "+ Pedido.class.getName());
		}
		return obj;
	}
	
	@Transactional
	public @Valid Pedido insert(@Valid Pedido obj) {
		// TODO Auto-generated method stub
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pgto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherrPagamentoComBoleto(pgto, obj.getInstante());
		}
		
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for(ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(produtoService.find(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		
		return obj;
	}
	
}
