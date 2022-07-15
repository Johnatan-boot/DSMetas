package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

//Servico classe de Servico Componente responsavel
//pela logica do Negocio

//@Service esta registrando como uma classe de Servico
@Service
public class SaleService {
	
	//Ultilizando repositorie
	@Autowired
	private SaleRepository repository;

	//Função buscar vendar no banco dados
	public Page<Sale> findSales(String minDate, String maxDate,Pageable pageable){
		//Criando uma Data com hora no dia de Hoje atualizados
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		//convertendo datas para o tipo localDate formato texto para localdate
		LocalDate min = minDate.equals("")? today.minusDays(365) :LocalDate.parse(minDate);
		//Expressao Condicional ternaria =>LocalDate max = maxDate.equals("")? today :LocalDate.parse(maxDate);
        LocalDate max = maxDate.equals("")? today :LocalDate.parse(maxDate);
      // nota para pesquisa => http://localhost:8080/sales?minDate=2021-11-01&maxDate=2021-12-31
		//ultilizando o repository
		//buscar no banco de Dados todas as vendas findAll()
		 return repository.findSales(min,max,pageable);
	}
}
