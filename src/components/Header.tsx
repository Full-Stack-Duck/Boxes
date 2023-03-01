import { UserCircle } from "phosphor-react";
import logoIcon from '../assets/logoIcon.svg'

export function Header(){
    return(
        <header className="flex px-7 justify-between items-center bg-purple-dark w-full h-16 ">
        <div className="w-[50%] flex items-center justify-around ">
          <a href="" className="">
            <img src={logoIcon} alt="Logomarca da Manager Box" />
          </a>
          <div className="w-0.5 h-9 bg-purple-light"></div>
          <h1 className="font-viga text-2xl tracking-widest text-white">Boxes</h1>
        </div>
        <div className="w-[50%] flex items-center justify-end">
          <a href="#" className="hover:bg-purple-400 hover:bg-opacity-10 rounded-full">
            <UserCircle size={40} weight="duotone" className="text-[#6851AC]"/>
          </a>
    
        </div>
        </header>
    )
}