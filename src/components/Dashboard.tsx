import { useEffect, useState } from "react";
import polygon_despesaG_icon from "../assets/polygon_despesa_G.svg";
import polygon_despesaR_icon from "../assets/polygon_despesa_R.svg";
import Dinero from "dinero.js";
import ListboxSelector from "./ListboxSelector";



const resources = {
    vendas: {   
      total: {
        espacoTempo: 3675.50,
      },
      estatistica: {
        espacoTempo: 5.30,
        dias45: 16.1,
        dias60: 21.0
      }
    },
    gastos: {
        total: {
          espacoTempo: 2218.25,
        },
        estatistica: {
          espacoTempo: -5.30,
        }
    },
    receita: ''
}

export function Dashboard() {

    useEffect( () => {
      fetch(resources.receita)
      .then(response => response.json())
      .then(data => setGastoTotal(data))
      .catch(error => console.log(error))
    }, [])
    
    const totalGastos = Dinero({amount: (resources.gastos.total.espacoTempo * 100), currency: "BRL"}).toFormat("$0.0")
    const totalVendas = Dinero({amount: (resources.vendas.total.espacoTempo * 100), currency: "BRL"}).toFormat("$0,0.00")
    
    const [gastoTotal, setGastoTotal] = useState(totalGastos)
    const [vendasTotal, setVendasTotal] = useState(totalVendas)

  return (
    <section className="flex w-full justify-center py-3.5">
      <div className="flex flex-col items-center w-[23rem] sm:w-[50rem]">
        <div className="w-full flex items-center justify-between py-4 border-b-2">
          <p className="font-quicksand font-bold pl-7 text-2xl">Dashboard</p>
          <ListboxSelector />
        </div>
        <div className="flex w-full px-7 justify-start">
          <p className=" font-quicksand font-bold text-lg">
            Vendas & Gastos
          </p>
        </div>
        <div className="flex items-center justify-between flex-wrap w-full border-b-2 px-7 py-5">
          <div className="pb-5">
            <p className="font-quicksand text-lg font-normal">Vendas</p>
            <div className="flex items-center">
              <p className="font-bold text-3xl text-purple-medium">
                {vendasTotal.replace('.', ',').replace(',', '.')}
              </p>
              <div className="flex items-center bg-green-300 rounded-xl h-5 w-14 ml-3 justify-center gap-0.5">
                <div className="rotate-180 -mt-2">
                <img src={polygon_despesaG_icon} className="animate-bounce transition-all"/>
                </div>
                <span className="font-bold text-xs text-green-800">{resources.vendas.estatistica.espacoTempo}%</span>
              </div>
            </div>
            <p className=" font-quicksand text-xs">
              Comparado com o período anterior
            </p>
          </div>

          <div className="">
            <p className="font-quicksand text-lg font-normal">Gastos</p>
            <div className="flex items-center">
              <p className="font-bold text-3xl text-purple-medium">
                {gastoTotal.replace('.', ',').replace(',', '.')}
              </p>
              <div className="flex items-center bg-red-400 rounded-xl h-5 w-14 ml-3 justify-center">
                <img src={polygon_despesaR_icon} className="animate-bounce transition-opacity mt-2"></img>
                <span className="font-bold text-xs text-red-900">{resources.gastos.estatistica.espacoTempo}%</span>
              </div>
            </div>
            <p className=" font-quicksand text-xs">
              Comparado com o período anterior
            </p>
          </div>
        </div>
      </div>
    </section>
  );
}
