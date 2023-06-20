import Dinero from "dinero.js";
import order_plus_icon from "../assets/order_plus_icon.svg";

const resourses = {
  pedidos: {
    total: 12,
    averageValue: 678.2,
  },
  orcamentos: {
    total: 17,
    averageValue: 927.3,
  },
};

export function PedidosOrcamentos() {
  const pedidosMedia = Dinero({
    amount: resourses.pedidos.averageValue * 100,
    currency: "BRL",
  }).toFormat("$0,0.00");

  const orcamentoMedia = Dinero({
    amount: resourses.orcamentos.averageValue * 100,
    currency: "BRL",
  }).toFormat("$0,0.00");

  return (
    <section className="flex h-full justify-center border-b-2 px-7 py-3.5 lg:w-1/3 lg:p-0 lg:border-0">
      <div className=" w-full lg:p-4 lg:border lg:rounded-xl lg:border-purple-light">
        <h3 className=" font-quicksand font-bold text-lg">
          Pedidos & Orçamentos
        </h3>
        <div className="flex justify-between pt-5 gap-1 my-5 lg:m-0">
          <div className="flex flex-col items-center w-1/2">
            <span className="text-sm text-gray-400 font-quicksand font-semibold whitespace-nowrap">
              PEDIDOS
            </span>
            <div className="h-[60px] min-w-[60px] flex bg-purple-stroke rounded-full items-center justify-center shadow-lg">
              <p className="text-4xl font-montserrat mx-1.5 font-bold text-purple-dark">
                {resourses.pedidos.total}
              </p>
            </div>
          </div>
          <div className="flex flex-col items-center w-1/2">
            <p className="text-sm text-gray-400 font-quicksand font-semibold whitespace-nowrap">
              ORÇAMENTOS
            </p>
            <div className="h-[60px] min-w-[60px] flex bg-purple-stroke rounded-full items-center justify-center shadow-lg">
              <p className="text-4xl font-montserrat mx-1.5 font-bold text-purple-dark">
                {resourses.orcamentos.total}
              </p>
            </div>
          </div>
        </div>

        <div className="flex justify-end my-5 flex-nowrap lg:justify-center lg:mb-0">
          <div className="flex flex-col justify-center items-center mr-7 p-2 lg:w-full lg:mr-0">
              <p className="text-gray-400 text-sm font-semibold font-quicksand">VALOR MÉDIO DOS PEDIDOS</p>
              <p className="text-3xl font-bold text-purple-medium font-montserrat">
                {pedidosMedia.replace(".", ",").replace(",", ".")}
              </p>
          </div>
          <button className="w-7 h-7 self-end mb-2">
            <img src={order_plus_icon} ></img>
          </button>

          {/* <div className="flex flex-col justify-center items-center gap-2 p-2 w-1/2 lg:hidden">
            <h1 className="font-quicksand text-center text-gray-400 text-sm font-semibold">
              VALOR MÉDIO DOS ORÇAMENTOS
            </h1>
            <div className="flex items-center justify-between">
              <h1 className="text-3xl font-bold text-purple-medium">
                {orcamentoMedia.replace(".", ",").replace(",", ".")}
              </h1>
            </div>
            <div className="flex justify-center items-center rounded-full h-11 w-11 ">
              <img src={order_plus_icon} className="w-7 h-7"></img>
            </div>
          </div> */}
        </div>
      </div>
    </section>
  );
}
