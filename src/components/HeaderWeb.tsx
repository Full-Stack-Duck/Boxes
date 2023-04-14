import { DotsThreeVertical, UserCircle } from "phosphor-react";
import logoIcon from '../assets/logoIcon.svg'

export function HeaderWeb(){
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
          <div className="mr-2.5">
            <a href="" className="text-white font-quicksand text-sm tracking-wider px-2 py-1 bg-purple-medium border rounded-md border-white">Cadastrar</a>
          </div>
          <a href="#" className="hover:bg-purple-400 hover:bg-opacity-10 rounded-full">
            <DotsThreeVertical size={32} weight="duotone" className="text-[#fff]"/>
          </a>
    
        </div>
        </header>
    )
}