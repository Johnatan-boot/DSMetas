import axios from 'axios';
import { toast } from 'react-toastify';
import icon from '../../assets/img/notificationIcon.svg';
import { BASE_URL } from '../../utils/request';
import './styles.css';

type Props = {
  //o dado ou os dados que o componente vai receber
  /*
  id da venda para chamar o endpoints da API

  */

  saleId: number;
}


function handleClick(saleId : number){
  //Chamada da API COM ENDOPOINTS QUE ENVIA O SMS
  axios(`${BASE_URL}/sales/${saleId}/notification`)
  //dando certo a resposta da requisicao
  .then(response => {
    toast.info("SMS Enviado com Sucesso!");  
  })
}

function NotificationButton({saleId} : Props) {
//Props parametros que o componente pode receber para atualizacao ou renderizacao


    return(
        <div className="dsmeta-red-btn" onClick={()=> 
        //Quando o Botao For Clicado ele Vai Chamar A função que vai enviar Dados Da Venda
        handleClick(saleId)}>
        <img src= {icon} alt="Notificar" />
      </div>
    )
   }
   
   export default NotificationButton;
   