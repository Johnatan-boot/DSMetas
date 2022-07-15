package com.devsuperior.dsmeta.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
//Registrada comoc componente service
@Service
public class SmsService {
     //Variaveis vao trazer informações do Aplication.properties
	@Value("${twilio.sid}")
	private String twilioSid;

	@Value("${twilio.key}")
	private String twilioKey;

	@Value("${twilio.phone.from}")
	private String twilioPhoneFrom;

	@Value("${twilio.phone.to}")
	private String twilioPhoneTo;

	//Fazendo Busca da venda Pelo Id para envio Sms
	@Autowired
	private SaleRepository saleRepository;
	
	//Metodo de Envio sms teste
	public void sendSms(Long saleId) {
		Sale sale = saleRepository.findById(saleId).get();
		
		//Montando uma mensagem com os dados da venda
		String date = sale.getDate().getMonthValue() +"/" +sale.getDate().getYear();
		String msg = " O Vendedor: " +sale.getSellerName() + " Foi destaque em "+date
		+" Com um Total de R$" +String.format("%.2f", sale.getAmount());
		//formatando a Remuneração em 2 casas  +String.format("%.2f", sale.getAmount());

		Twilio.init(twilioSid, twilioKey);
//rementente
		PhoneNumber to = new PhoneNumber(twilioPhoneTo);
		//destinatario
		PhoneNumber from = new PhoneNumber(twilioPhoneFrom);
//Testes envio
		Message message = Message.creator(to, from, msg).create();

		System.out.println(message.getSid());
	}
}
