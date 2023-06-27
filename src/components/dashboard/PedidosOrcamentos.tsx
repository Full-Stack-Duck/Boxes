import OrderPlusIcon from '../../assets/order_plus_icon.svg'
import useQuantPedidosData from '../../hooks/useQuantPedidosData'
import useQuantOrcamentosData from '../../hooks/useQuantOrcamentosData'

export function PedidosOrcamentos() {
  const quantPedidos = useQuantPedidosData(365 * 23).data
  const quantOrcamentos = useQuantOrcamentosData(1000).data

  return (
    <section className="flex h-full justify-center border-b-2 px-7 py-3.5 lg:w-1/3 lg:p-0 lg:border-0">
      <div className="w-full lg:p-4 lg:border lg:rounded-xl lg:border-purple-light">
        <h3 className="font-quicksand font-bold text-lg">
          Pedidos & Orçamentos
        </h3>

        <div className="flex justify-between pt-5 gap-2 my-5 lg:m-0">
          <div className="flex flex-col items-center w-1/2">
            <span className="text-sm text-gray-400 font-quicksand font-semibold whitespace-nowrap">
              PEDIDOS
            </span>
            <div className="h-[60px] min-w-[60px] flex bg-purple-medium rounded-full items-center justify-center shadow-lg">
              <p className="text-4xl font-montserrat mx-1.5 font-bold text-purple-dark">
                {quantPedidos}
              </p>
            </div>
          </div>

          <div className="flex flex-col items-center w-1/2">
            <p className="text-sm text-gray-400 font-quicksand font-semibold whitespace-nowrap">
              ORÇAMENTOS
            </p>
            <div className="h-[60px] min-w-[60px] flex bg-purple-medium rounded-full items-center justify-center shadow-lg">
              <p className="text-4xl font-montserrat mx-1.5 font-bold text-purple-dark">
                {quantOrcamentos}
              </p>
            </div>
          </div>
        </div>

        <div className="flex justify-end my-5 flex-nowrap lg:justify-center lg:mb-0">
          <div className="flex flex-col justify-center items-center mr-7 p-2 lg:w-full lg:mr-0">
              <p className="text-gray-400 text-sm font-semibold font-quicksand">VALOR MÉDIO DOS PEDIDOS</p>
              <p className="text-3xl font-bold text-purple-medium font-montserrat">
                {/* {pedidosMedia.replace(".", ",").replace(",", ".")} */}
                R$0,00
              </p>
          </div>
          <button className="w-7 h-7 self-end mb-2">
            <img src={OrderPlusIcon} ></img>
          </button>
        </div>
      </div>
    </section>
  )
}
