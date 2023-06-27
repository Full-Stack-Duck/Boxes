import { useContext } from "react";
import product_plus_icon from "../assets/product_plus_icon.svg";
import { UserContext } from "../contexts/UserContext";


export function ProdutosEstoque() {

    return (
        <section className="w-full flex justify-center items-center mb-5 lg:mb-0 lg:w-1/3 lg:border lg:border-purple-light lg:rounded-xl lg:p-4 lg:ml-2">
            <div className="px-7 flex flex-col justify-center items-start w-[23rem] sm:w-[50rem] lg:px-0">
            <h1 className="font-quicksand font-bold text-lg pt-3 lg:pt-0">Produtos & Estoque</h1>
            <div className="my-3 flex flex-col justify-start items-start gap-2 w-full">
                <div>
                    <p className=" font-quicksand text-gray-400 text-sm font-semibold">TOTAL DE PRODUTOS EM ESTOQUE</p>
                    <p className=" text-purple-medium text-3xl font-bold">{0}</p>
                </div>
                <div className="flex flex-col w-full gap-3">
                    <div>
                        <p className=" font-quicksand text-gray-400 text-sm font-semibold">PRODUTO MAIS POPULAR</p>
                        <div className="flex items-center justify-between w-full">
                            <span className="text-purple-medium text-xl font-bold">{0}</span>
                        </div>
                    </div>
                    <div>
                        <p className="font-quicksand text-gray-400 text-sm font-semibold">PRODUTO MENOS POPULAR</p>
                        <div className="flex items-center justify-between">
                            { }
                            <span className="text-purple-medium text-xl font-bold">{0}</span>
                            <button>
                                <img 
                                src={product_plus_icon} 
                                alt="cadastrar novo produto" />
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            </div>
        </section>
    )
}