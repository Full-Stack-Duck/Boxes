import verIcon from '../assets/olho-icon-on.svg'
import editarIcon from '../assets/lapis-icon-on.svg'
import deletarIcon from '../assets/lixeira-icon-on.svg'
import pedidoIcon from '../assets/order-icon-on-plus.svg'


export function ResultadoCliente(){
    return(
            <li className="flex mx-7 border-b-2 border-purple-light py-1 mb-2 items-center">
                <h2 className="flex w-[50%] pl-3">Resultado Cliente</h2>
                <div className="w-full flex w-[50%] justify-evenly">
                    <a href="#" className="flex items-center justify-center h-[32px] min-w-[32px] rounded-full bg-purple-medium"><img src={verIcon} alt="ver-resultado" className="w-5 h-5" /></a>
                    <a href="#" className="flex items-center justify-center h-[32px] min-w-[32px] rounded-full bg-purple-medium"><img src={editarIcon} alt="editar-resultado" className="w-5 h-5" /></a>
                    <a href="#" className="flex items-center justify-center h-[32px] min-w-[32px] rounded-full bg-purple-medium"><img src={deletarIcon} alt="deletar-resultado" className="w-5 h-5" /></a>
                    <a href="#" className="flex items-center justify-center h-[32px] min-w-[32px] rounded-full bg-purple-medium"><img src={pedidoIcon} alt="deletar-resultado" className="w-5 h-5" /></a>
                </div>
            </li>
    )
}