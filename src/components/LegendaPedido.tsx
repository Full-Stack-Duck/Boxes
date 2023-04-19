import aprovarIcon from '../assets/checkmark-circle-2.svg'
import naoPagoIcon from '../assets/unpaid_icon.svg'
import exclamacaoIcon from '../assets/exclamation_icon.svg'
import pagoIcon from '../assets/paid_icon.svg'

export function LegendaPedido (){
    return(
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
    )
}