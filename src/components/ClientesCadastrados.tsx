import client_plus_icon from "../assets/client_plus_icon.svg";

export function ClientesCadastrados() {
    return (
        <>
            <div className="px-7 border-b-2">
                <h1 className=" font-quicksand font-bold text-lg pt-3">Clientes Cadastrados</h1>

                <div className="py-5">
                    <p className=" font-quicksand text-gray-400 text-sm font-semibold ">TOTAL DE CLIENTES
                    </p>
                    <p className="text-purple-medium text-3xl font-semibold">
                        000
                    </p>
                </div>
                <div className="">
                    <p className=" font-quicksand text-gray-400 text-sm font-semibold">NOVOS CLIENTES
                    </p>
                    <p className="text-purple-medium text-3xl font-semibold">
                        000
                    </p>
                </div>
                <div className="flex justify-between items-center  pb-5">
                    <p className="text-xs text-gray-400">*Dentro do período selecionado</p>
                    <img src={client_plus_icon} className="w-7"></img>
                </div>
            </div>
        </>
    )
}