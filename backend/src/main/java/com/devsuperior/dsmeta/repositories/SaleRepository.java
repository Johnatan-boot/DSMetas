package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.entities.Sale;

//Responsavel Por Acessar o Banco de Dados
//spring ja cria componente quee salva, deleta, faz operacoes
public interface SaleRepository extends JpaRepository<Sale, Long> {
	//Função Pesquisa no Banco de Dados As Datas Como Argumentos
	//Adaptacao do ling pql para jpa
	//Vai buscar as 20 primeiras melhores vendas max ORDER BY obj.amount DESC"
	@Query("SELECT obj FROM Sale obj WHERE obj.date BETWEEN :min AND :max ORDER BY obj.amount DESC")
	Page<Sale> findSales(LocalDate min, LocalDate max, Pageable pageable);

}



