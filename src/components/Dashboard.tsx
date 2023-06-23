import { useEffect, useState } from "react";
import polygon_despesaG_icon from "../assets/polygon_despesa_G.svg";
import polygon_despesaR_icon from "../assets/polygon_despesa_R.svg";
import Dinero from "dinero.js";
import ListboxSelector from "./ListboxSelector";


interface TB_RECEITA {
  id: number,
  data_receita: Date,
  valor_pagamento: number,
  usuario_id: number
}

const resources = {
    usuario: 5,
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
}

export function Dashboard() {

    useEffect( () => {
      fetch("http://localhost:3333/resources")
      .then(response => response.json())
      .then(data => setGastoTotal(data))
      .catch(error => console.log(error))
    }, [])
    
    const totalVendas = Dinero({amount: (resources.vendas.total.espacoTempo * 100), currency: "BRL"}).setLocale('pt-BR').toFormat("$0,0.00")
    
    const [gastoTotal, setGastoTotal] = useState<TB_RECEITA[]>([])
    const [vendasTotal, setVendasTotal] = useState(totalVendas)

    function defGastoTotal(userID : number, list : TB_RECEITA[]): number{
      let valor : number = 0
      list.forEach(element => {
        if(verifyUser(element.usuario_id, userID)){
          valor += element.valor_pagamento
        }
      });
      return valor
    }

    function verifyUser(id : number, idDesejado : number){
      if (id == idDesejado) {
        return true
      } else{
        return false
      }
    }

  return (
    <section className="flex w-full justify-center py-3.5 w-1/3 lg:py-0 lg:mr-2">

      <div className="flex flex-col items-center w-[23rem] sm:w-[50rem]">
        <div className="w-full flex items-center justify-between py-4 border-b-2 lg:hidden">
          <p className="font-quicksand font-bold pl-7 text-2xl">Dashboard</p>
          <ListboxSelector />
        </div>

        <div className="flex items-center justify-between flex-wrap w-full border-b-2 px-7 py-5 lg:p-4 lg:border lg:rounded-xl lg:border-purple-light">
          <p className=" font-quicksand font-bold text-lg pb-2">
            Vendas & Gastos
          </p>
          <div className="pb-5">
            <p className="font-quicksand text-lg font-normal">Vendas</p>
            <div className="flex items-center">
              <p className="font-bold text-3xl text-purple-medium">
                {vendasTotal}
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
                  {
                  Dinero({amount: defGastoTotal(resources.usuario, gastoTotal), currency: "BRL"}).setLocale('pt-BR').toFormat("$0,0.00")
                  }
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
