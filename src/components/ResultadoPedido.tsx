import verIcon from '../assets/olho-icon-on.svg'
import editarIcon from '../assets/lapis-icon-on.svg'
import deletarIcon from '../assets/lixeira-icon-on.svg'
import aprovarIcon from '../assets/checkmark-circle-2.svg'


export function ResultadoPedido(){
    return(
            <li className="flex mx-7 border-b-2 border-purple-light py-1 mb-2 items-center">
                <h2 className="flex w-[50%] pl-3">Resultado Pedido</h2>
                <div className="flex w-[50%] justify-evenly">
                    <a href="#" className="flex items-center justify-center h-[32px] min-w-[32px] rounded-full bg-purple-medium"><img src={verIcon} alt="ver-resultado" className="w-5 h-5" /></a>
                    <a href="#" className="flex items-center justify-center h-[32px] min-w-[32px] rounded-full bg-purple-medium"><img src={editarIcon} alt="editar-resultado" className="w-5 h-5" /></a>
                    <a href="#" className="flex items-center justify-center h-[32px] min-w-[32px] rounded-full bg-purple-medium"><img src={deletarIcon} alt="deletar-resultado" className="w-5 h-5" /></a>
                    <a href="#" className="flex items-center justify-center"><img src={aprovarIcon} alt="aprovar-pedido" className="w-8 h-8" /></a>
                </div>
            </li>
    )
}