package com.devsuperior.dsmeta.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.services.SaleService;
import com.devsuperior.dsmeta.services.SmsService;

// As Controller Disponibiliza faz requisicaoes endpoints na Api
@RestController
@RequestMapping(value = "/sales")
public class SaleController {
 //Metodo Responsavel por Disponibilizar Lista de Vendas
//No FrontEnd
	@Autowired
	private SaleService service;
	
	//Variavel da Funcao Notify
	@Autowired
	private SmsService smsService;
	
	//Config do metodo para responder via http apresentar na tela @GetMapping
	@GetMapping
	public Page<Sale> findSales(
	@RequestParam(value ="minDate", defaultValue = "")String minDate, 
	@RequestParam(value ="maxDate", defaultValue = "")String maxDate,
	Pageable pageable){
		//resultado paginado busca apenas por 20 vendas elementos
	  return service.findSales(minDate,maxDate,pageable);	
	}
	
	//Metodo para endpoints envio mensagem celular
	//pasando o parametro id para notifica vendas pelo celular
	@GetMapping("/{id}/notification")
	public void notifySms(@PathVariable  Long id){
	smsService.sendSms(id);
	}
	
}
