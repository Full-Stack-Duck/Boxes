import ListboxSelector from '../ListboxSelector'
import useReceitasData from '../../hooks/useReceitasData'
import useGastosData from '../../hooks/useGastosData'
import { formatPrice } from '../../utils/format-price'
import { ChangeEvent, useState } from 'react'

const periodoSelect = [
  { name: 'Últimos 30 dias', periodo: 30},
  { name: 'Últimos 90 dias' , periodo: 90},
  { name: 'Últimos 180 dias', periodo: 180},
  { name: 'Últimos 360 dias', periodo: 360},
] as const;

export function Dashboard() {
  const [ periodo, setPeriodo ] = useState<number>(0)

  const gastosTotal = useGastosData(periodo).data
  const receitasTotal = useReceitasData(periodo).data

  const handlePeriodChange = (event: ChangeEvent<HTMLSelectElement>) => {
    const selectedIndex = Number(event.target.value); 
    setPeriodo(periodoSelect[selectedIndex].periodo)
  };

  
  return (
    <section className="flex w-full justify-center py-3.5 lg:w-1/3 lg:py-0 lg:mr-2">
      <div className="flex flex-col items-center w-[23rem] sm:w-[50rem]">
        <div className="w-full flex items-center justify-between py-4 border-b-2 lg:hidden">
          <p className="font-quicksand font-bold pl-7 text-2xl">Dashboard</p>
          <div>
          <select
                  className="border rounded font-nunito h-8 mb-4"
                  onChange={(e) => handlePeriodChange(e)}
                  >
                  {periodoSelect.map((field, index) => {
                    return (
                      <option
                      className="text-purple-light text-xs"
                      key={index.toString()}
                      value={index}
                      >
                        {field.name}
                      </option>
                    );
                  })}
                </select>
          </div>
        </div>

        <div className="flex items-center justify-between flex-wrap w-full border-b-2 px-7 py-5 lg:p-4 lg:border lg:rounded-xl lg:border-purple-light">
          <p className=" font-quicksand font-bold text-lg pb-2">
            Vendas & Gastos
          </p>
          <div className="pb-5">
            <p className="font-quicksand text-lg font-normal">Vendas</p>
            <div className="flex items-center">
              <p className="font-bold text-3xl text-purple-medium">
                {formatPrice(parseFloat(receitasTotal))}
              </p>
              {/* <div className="flex items-center bg-green-300 rounded-xl h-5 w-14 ml-3 justify-center gap-0.5">
                <div className="rotate-180 -mt-2">
                  <Image
                    src={PoligonDespesaGIcon}
                    width={20}
                    height={20}
                    className="animate-bounce transition-all"
                    alt="Setinha apontando pra cima"
                  />
                </div>
                <span className="font-bold text-xs text-green-800">
                  {resources.vendas.estatistica.espacoTempo}%
                </span>
              </div> */}
            </div>
            <p className=" font-quicksand text-xs">
              Comparado com o período anterior
            </p>
          </div>

          <div className="">
            <p className="font-quicksand text-lg font-normal">Gastos</p>
            <div className="flex items-center">
              <p className="font-bold text-3xl text-purple-medium">
                {formatPrice(parseFloat(gastosTotal))}
              </p>
              {/* <div className="flex items-center bg-red-400 rounded-xl h-5 w-14 ml-3 justify-center">
                <Image
                  src={PolygonDespesaGGIcon}
                  className="animate-bounce transition-opacity mt-2"
                  width={20}
                  height={20}
                  alt="Setinha pra baixo"
                />
                <span className="font-bold text-xs text-red-900">
                  {resources.gastos.estatistica.espacoTempo}%
                </span>
              </div> */}
            </div>
            <p className=" font-quicksand text-xs">
              Comparado com o período anterior
            </p>
          </div>
        </div>
      </div>
    </section>
  )
}
