import verIcon from '../assets/olho-icon-on.svg'
import editarIcon from '../assets/lapis-icon-on.svg'
import deletarIcon from '../assets/lixeira-icon-on.svg'


export function ItemLinha(){
    return(
            <li className="flex mx-7 border-b-2 border-purple-light py-1 my-2 items-center">
                <div className="flex-col w-full">
                    <p className="flex justify-around text-sm">
                        <span className="">#6</span>
                        <span className="">Coxinha de Frango</span>
                        <span className="">150 un.</span>
                        <span className="">R$ 270,00</span>
                    </p>
                    <div className="flex justify-evenly my-2">
                        <a href="#" className="flex items-center justify-center h-[32px] min-w-[32px] rounded-full bg-purple-medium"><img src={verIcon} alt="ver-resultado" className="w-5 h-5" /></a>
                        <a href="#" className="flex items-center justify-center h-[32px] min-w-[32px] rounded-full bg-purple-medium"><img src={editarIcon} alt="editar-resultado" className="w-5 h-5" /></a>
                        <a href="#" className="flex items-center justify-center h-[32px] min-w-[32px] rounded-full bg-purple-medium"><img src={deletarIcon} alt="deletar-resultado" className="w-5 h-5" /></a>
                    </div>
                </div>
            </li>
    )
}