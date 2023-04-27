import verIcon from '../assets/olho-icon-on.svg'
import editarIcon from '../assets/lapis-icon-on.svg'
import deletarIcon from '../assets/lixeira-icon-on.svg'
import pedidoIcon from '../assets/order-icon-on-plus.svg'


export function ResultadoCliente(){
    return(
        <section className='w-full flex items-center justify-center'>

            <li className="flex px-7 border-b-2 border-purple-light py-1 mb-2 items-center w-[23rem] sm:w-[50rem]">
                <h2 className="px-3">Resultado Cliente</h2>
                <div className="w-full flex justify-evenly">
                    <a href="#" className="flex items-center justify-center h-[32px] min-w-[32px] rounded-full bg-purple-medium"><img src={verIcon} alt="ver-resultado" className="w-5 h-5" /></a>
                    <a href="#" className="flex items-center justify-center h-[32px] min-w-[32px] rounded-full bg-purple-medium"><img src={editarIcon} alt="editar-resultado" className="w-5 h-5" /></a>
                    <a href="#" className="flex items-center justify-center h-[32px] min-w-[32px] rounded-full bg-purple-medium"><img src={deletarIcon} alt="deletar-resultado" className="w-5 h-5" /></a>
                    <a href="#" className="flex items-center justify-center h-[32px] min-w-[32px] rounded-full bg-purple-medium"><img src={pedidoIcon} alt="criar-pedido" className="w-5 h-5" /></a>
                </div>
            </li>
        </section>
    )
}