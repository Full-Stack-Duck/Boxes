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
    <section className="flex justify-center w-full flex-wrap py-3.5">
      <div className="px-7 border-b-2 w-[23rem] sm:w-[50rem]">
        <h3 className=" font-quicksand font-bold text-lg">
          Pedidos & Orçamentos
        </h3>
        <div className="flex justify-between pt-5 gap-1 my-5">
          <div className="flex flex-col items-center w-1/2">
            <span className="text-sm text-gray-400 font-quicksand font-semibold whitespace-nowrap">
              PEDIDOS
            </span>
            <div className="h-[60px] min-w-[60px] flex bg-purple-medium rounded-full items-center justify-center shadow-lg">
              <h1 className="text-4xl font-montserrat mx-1.5 font-bold">
                {resourses.pedidos.total}
              </h1>
            </div>
          </div>
          <div className="flex flex-col items-center w-1/2">
            <h1 className="text-sm text-gray-400 font-quicksand font-semibold whitespace-nowrap">
              ORÇAMENTOS
            </h1>
            <div className="h-[60px] min-w-[60px] flex bg-purple-medium rounded-full items-center justify-center shadow-lg">
              <h1 className="text-4xl font-montserrat mx-1.5 font-bold">
                {resourses.orcamentos.total}
              </h1>
            </div>
          </div>
        </div>
        <div className="flex justify-between gap-2 my-5 flex-nowrap">
          <div className="flex flex-col justify-center items-center gap-2 p-2 w-1/2 rounded-2xl border border-purple-medium">
            <h1 className="font-quicksand text-center text-gray-400 text-sm font-semibold">
              VALOR MÉDIO DOS PEDIDOS
            </h1>
            <div className="flex items-center justify-between">
              <h1 className="text-3xl font-bold text-purple-medium">
                {pedidosMedia.replace(".", ",").replace(",", ".")}
              </h1>
            </div>
            <div className="flex justify-center items-center rounded-full h-11 w-11 ">
              <img src={order_plus_icon} className="w-7 h-7"></img>
            </div>
          </div>
          <div className="flex flex-col justify-center items-center gap-2 p-2 w-1/2 rounded-2xl border border-purple-medium">
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
          </div>
        </div>
      </div>
    </section>
  );
}
