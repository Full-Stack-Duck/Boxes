import { DotsThreeVertical, UserCircle } from "phosphor-react";
import logoIcon from '../assets/logoIcon.svg'
import { Link } from 'react-router-dom';

export function HeaderWeb(){
    return(
        <header className="flex px-7 justify-between items-center bg-purple-dark w-full h-16 lg:px-32">
        <div className="w-[50%] flex items-center">
          <a href="" className="pr-4">
            <img src={logoIcon} alt="Logomarca Boxes" />
          </a>
          <div className="w-0.5 h-9 bg-purple-light"></div>
          <h1 className="font-viga text-2xl tracking-widest text-white pl-4">Boxes</h1>
        </div>
        <div className="w-[50%] flex items-center justify-end">
          <div className="mr-3 hidden md:block">
          <Link to="/cadastro" className="text-white font-quicksand text-sm tracking-wider px-3 py-1 bg-purple-medium border rounded-md border-white hover:bg-purple-400 ">Cadastrar</Link>
          </div>
          <div className="mr-3">
          <Link to="/login" className="text-white font-quicksand text-sm tracking-wider px-7 py-1 bg-purple-dark border rounded-md border-white hover:bg-purple-medium">Entrar</Link>
          </div>
          <button className="hover:bg-purple-400 hover:bg-opacity-10 rounded-full">
            <DotsThreeVertical size={32} weight="duotone" className="text-[#fff]"/>
          </button>
    
        </div>
        </header>
    )}
