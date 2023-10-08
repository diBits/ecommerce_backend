package labs.dibits.ecommerce.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import labs.dibits.ecommerce.domain.PagamentoComBoleto;

@Service
public class BoletoService {
	
	public void preencherrPagamentoComBoleto(PagamentoComBoleto pgto, Date InstanteDoPedido) {
		
		//Trocar futuramente para webService que gere o boleto;
		Calendar cal = Calendar.getInstance();
		cal.setTime(InstanteDoPedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pgto.setDataVencimento(cal.getTime());
	}

}
