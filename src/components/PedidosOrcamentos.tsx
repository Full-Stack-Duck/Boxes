import order_plus_icon from "../assets/order_plus_icon.svg";

export function PedidosOrcamentos() {
    return (
        <>
            <div className="px-7 border-b-2">
                <h3 className=" font-quicksand font-semibold text-lg pt-3">Pedidos & Orçamentos</h3>
                <div className="flex justify-between pt-5">
                    <div className="text-center">
                        <h1 className="text-sm text-gray-400 font-quicksand font-semibold">
                            TOTAL DE PEDIDOS
                        </h1>
                        <div className="flex mt-2 mb-5 mx-7 h-16 bg-purple-medium rounded-full items-center justify-center">
                            <h1 className="text-4xl mx-2">00</h1>
                        </div>
                    </div>
                    <div className="text-center">
                        <h1 className="text-sm text-gray-400 font-quicksand font-semibold">
                            TOTAL DE ORÇAMENTOS
                        </h1>
                        <div className="flex mt-2 mb-5 mx-12 h-16 bg-purple-medium rounded-full items-center justify-center">
                            <h1 className="text-4xl mx-2">00</h1>
                        </div>
                    </div>
                </div>
                <div className="pb-5">
                    <h1 className="font-quicksand text-center text-gray-400 text-sm font-semibold">
                        VALOR MÉDIO DOS PEDIDOS
                    </h1>
                    <div className="flex pb-5 justify-center">
                        <h1 className="text-3xl text-purple-medium">
                            R$ 000,00
                        </h1>
                    </div>
                    <div className="flex justify-center">
                        <img src={order_plus_icon} className=" w-7"></img>
                    </div>
                </div>
            </div>
        </>
    )
}