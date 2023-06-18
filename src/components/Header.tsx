import { UserCircle } from "phosphor-react";
import logoIcon from '../assets/logoIcon.svg'
import UserIcon from '../assets/user-icon.svg'

export function Header(){
    return(
        <header className="flex px-7 justify-between items-center bg-purple-dark w-full h-16 lg:px-32">
        <div className="w-[50%] flex items-center">
          <a href="_blank" className="pr-4">
            <img src={logoIcon} alt="Logomarca Boxes" />
          </a>
          <div className="w-0.5 h-9 bg-purple-light"></div>
          <h1 className="font-viga text-2xl tracking-widest text-white pl-4">Boxes</h1>
        </div>
        <div className="w-[50%] flex items-center justify-end">
          <a href="#" className="hover:bg-purple-400 hover:bg-opacity-10 rounded-full">
            <img src={UserIcon} alt="Ícone do Usuario" />
          </a>
    
        </div>
        </header>
    )
}