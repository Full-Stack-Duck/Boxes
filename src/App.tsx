import { UserCircle } from "phosphor-react";
import logoIcon from './assets/logoIcon.svg'


export function App() {


  return (
    <div className="flex justify-between items-center bg-purple-500 w-full h-16 ">
    <div className="w-[50%] flex items-center justify-start">
      <a href="" className="pl-5 ">
        <img src={logoIcon} alt="Logomarca da Manager Box" />

      </a>
      <h1 className="text-white">Box Manager</h1>
    </div>
    <div className="w-[50%] flex items-center justify-end">
      <a href="" className="mr-5 hover:bg-purple-400 hover:bg-opacity-10 rounded-full">
        <UserCircle size={40} weight="duotone" className="text-[#6851AC]"/>
      </a>

    </div>
    </div>
  )
}

