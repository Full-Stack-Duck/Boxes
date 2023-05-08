import aprovarIcon from '../assets/checkmark-circle-2.svg'
import naoPagoIcon from '../assets/unpaid_icon.svg'
import exclamacaoIcon from '../assets/exclamation_icon.svg'
import pagoIcon from '../assets/paid_icon.svg'

export function LegendaPedido (){
    return(
        <section className='w-full flex justify-center'>
        <div className='flex flex-col py-3 px-7 w-[23rem] sm:w-[50rem]'>
                <p className='text-purple-light text-sm font-bold text-center'>LEGENDA</p>
                <div className='flex flex-col gap-3 items-start sm:flex-row sm:flex-wrap'>
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
        </section>
    )
}