import { UserCircle } from "phosphor-react";
import logoIcon from '../assets/logoIcon.svg'
import UserIcon from '../assets/user-icon.svg'

export function Header(){
    return(
        <header className="flex px-7 justify-between items-center bg-purple-dark w-full h-16 ">
        <div className="w-[50%] flex items-center justify-around gap-4">
          <a href="_blank" className="h-11 w-11">
            <img src={logoIcon} alt="Logomarca Boxes" />
          </a>
          <div className="w-[1px] h-9 bg-purple-light"></div>
          <h1 className="font-viga text-2xl tracking-widest text-white">Boxes</h1>
        </div>
        <div className="w-[50%] flex items-center justify-end">
          <a href="#" className="hover:bg-purple-400 hover:bg-opacity-10 rounded-full">
            <img src={UserIcon} alt="Ícone do Usuario" />
          </a>
    
        </div>
        </header>
    )
}