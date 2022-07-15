import axios from "axios";
import { useEffect, useState } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import { Sale } from "../../models/sale";
import { BASE_URL } from "../../utils/request";
import NotificationButton from '../NotificationButton';
import './styles.css';


function SalesCard() {
  //Mantendo o Estado das Datas

  //Criadao uma data de 1 ano atras
  const min = new Date(new Date().setDate(new Date().getDate() - 365));
  const max = new Date();
  //constante com data maxima e minima 
  const [minDate, setMinDate] = useState(min);
  const [maxDate, setMaxDate] = useState(new Date());
  //constante com data maxima e minima

  //Aramzenar lista de vendas com useState tipado do tipo Sales
  const [sales, setSales] = useState<Sale[]>([]);
  
useEffect(
  //funcao
  ()=>{
    /*Tratamento  para converter data padrao ano mes e dia*/
    //recortando 10 caracteres da esquerda para direita  codigo js com slice
    const dmin = minDate.toISOString().slice(0, 10);
    const dmax = maxDate.toISOString().slice(0, 10);
    



    /*VARIVEL de Ambiente index.ts */
    /*url onde esta chamando a requisicao*/
   axios.get(`${BASE_URL }/sales?minDate=${dmin}&maxDate=${dmax}`)
   /*esta sendo feita requisicao no backend*/
   .then(/*requisicao retorna promisse receber objeto que deu certo*/response =>{
   //Quando Buscar no Backend a resposta da requisição vou chamar a funcao setSales();
   //com a resposta da API
        setSales(response.data.content);
   })
  },
  /*lista dependencias argumentos*/
  [
    //indicando mudanças na filtragem das datas para acessar vendas daquela data no useEffect
    /*Configurando o useEffect para rodar novamente toda vez que fazer a filtragem das datas*/
    minDate,maxDate
  ]);


  //Mantendo o Estado das Datas
 return(
    <div className="dsmeta-card">
    <h2 className="dsmeta-sales-title">Vendas</h2>
    <div>
          <div className="dsmeta-form-control-container">
          <DatePicker
        selected={minDate}
        onChange={(date: Date) => setMinDate(date)}
        className="dsmeta-form-control"
        dateFormat="dd/MM/yyyy"
    />
      </div>
      <div className="dsmeta-form-control-container">
          <DatePicker
        selected={maxDate}
        onChange={(date: Date) => setMaxDate(date)}
        className="dsmeta-form-control"
        dateFormat="dd/MM/yyyy"
    />
    
      </div>
    </div>

    <div>
      <table className="dsmeta-sales-table">
        <thead>
          <tr>
            <th className="show992">ID</th>
            <th className="show576">Data</th>
            <th>Vendedor</th>
            <th className="show992">Visitas</th>
            <th className="show992">Vendas</th>
            <th>Total</th>
            <th>Notificar</th>
          </tr>
        </thead>
        <tbody>
          
          {sales.map(sale => {
            //trocando uma lista Statica por Dinamica
            //Quando se faz uma Renderização de uma Lista no React
            //é necessario colocar um valor unico key={sale.id}//contendo o id da venda
              return(
                <tr key={sale.id}>
                <td className="show992">{sale.id}</td>
                <td className="show576">{new Date(sale.date).toLocaleDateString()}</td>
                <td>{sale.sellerName}</td>
                <td className="show992">{sale.visited}</td>
                <td className="show992">{sale.deals}</td>
                <td>R$ {sale.amount.toFixed(2)}</td>
                <td>
                  <div className="dsmeta-red-btn-container">
                   <NotificationButton saleId={sale.id} />
                  </div>
                </td>
              </tr>
              
              )
            })}
          
          
        </tbody>

      </table>
    </div>

  </div>
 )
}
// componente pedaco de codigo reaproveitavel
export default SalesCard
