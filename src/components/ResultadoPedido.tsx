import verIcon from '../assets/olho-icon-on.svg'
import editarIcon from '../assets/lapis-icon-on.svg'
import deletarIcon from '../assets/lixeira-icon-on.svg'
import aprovarIcon from '../assets/checkmark-circle-2.svg'
import naoPagoIcon from '../assets/unpaid_icon.svg'
import exclamacaoIcon from '../assets/exclamation_icon.svg'
import pagoIcon from '../assets/paid_icon.svg'


export function ResultadoPedido(){
    return(
        <>
            <li className="flex mx-7 border-b-2 border-purple-light py-1 mb-2 items-center">
                <h2 className="flex w-[50%] pl-3">Resultado Pedido</h2>
                <div className="flex w-[50%] justify-evenly">
                    <a href="#" className="flex items-center justify-center h-[32px] min-w-[32px] rounded-full bg-purple-medium"><img src={verIcon} alt="ver-resultado" className="w-5 h-5" /></a>
                    <a href="#" className="flex items-center justify-center h-[32px] min-w-[32px] rounded-full bg-purple-medium"><img src={editarIcon} alt="editar-resultado" className="w-5 h-5" /></a>
                    <a href="#" className="flex items-center justify-center h-[32px] min-w-[32px] rounded-full bg-purple-medium"><img src={deletarIcon} alt="deletar-resultado" className="w-5 h-5" /></a>
                    <a href="#" className="flex items-center justify-center"><img src={aprovarIcon} alt="aprovar-pedido" className="w-8 h-8" /></a>
                </div>
            </li>

            <div className='py-3 mx-7'>
                <p className='text-purple-light text-sm font-bold text-center'>LEGENDA</p>
                <div>
                    <div className='flex pt-2'>
                        <img src={aprovarIcon}></img> 
                        <p className='text-purple-medium pl-2'>Aprovar Orçamento</p>                     
                    </div>
                    <div className='flex pt-2'>
                        <img src={naoPagoIcon}></img>                       
                        <p className='text-purple-medium pl-2'>Pedidos a Pagar</p>                     
                    </div>
                    <div className='flex pt-2'>
                        <img src={exclamacaoIcon}></img>                       
                        <p className='text-purple-medium pl-2'>Pedido Parcialmente Pago</p>                     
                    </div>
                    <div className='flex pt-2'>
                        <img src={pagoIcon}></img>                       
                        <p className='text-purple-medium pl-2'>Pedido Totalmente Pago</p>                     
                    </div>
                </div>
            </div>
        </>


    )
}