import order_plus_icon from "../assets/order_plus_icon.svg";

export function PedidosOrcamentos() {
    return (
        <>
            <div className="px-7 border-b-2">
                <h3 className=" font-quicksand font-bold text-lg pt-3">Pedidos & Orçamentos</h3>
                <div className="flex justify-between pt-5 gap-1 my-5">
                        <div className="flex flex-col items-center w-1/2">
                            <span className="text-sm text-gray-400 font-quicksand font-semibold whitespace-nowrap">
                                PEDIDOS
                            </span>
                            <div className="h-[60px] min-w-[60px] flex bg-purple-medium rounded-full items-center justify-center shadow-lg">
                                <h1 className="text-4xl font-montserrat mx-1.5">00</h1>
                            </div>
                        </div>
                        <div className="flex flex-col items-center w-1/2">
                            <h1 className="text-sm text-gray-400 font-quicksand font-semibold whitespace-nowrap">
                                ORÇAMENTOS
                            </h1>
                            <div className="h-[60px] min-w-[60px] flex bg-purple-medium rounded-full items-center justify-center shadow-lg">
                                <h1 className="text-4xl font-montserrat mx-1.5">00</h1>
                            </div>
                        </div>
                </div>
                    <h1 className="font-quicksand text-center text-gray-400 text-sm font-semibold">
                        VALOR MÉDIO DOS PEDIDOS
                    </h1>
                <div className="flex items-center justify-between">
                    <div>

                    </div>
                    <h1 className="text-3xl font-medium text-purple-medium">
                        R$000,00
                    </h1>
                    <div>
                        <img src={order_plus_icon} className=" w-7"></img>
                    </div>
                </div>
            </div>
        </>
    )
}