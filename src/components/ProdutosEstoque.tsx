import product_plus_icon from "../assets/product_plus_icon.svg";

export function ProdutosEstoque() {
    return (
        <>
                <h1 className="ml-7 font-quicksand font-bold text-lg pt-3">Produtos & Estoques</h1>
            <div className="mx-8 my-3 flex flex-col justify-start items-start gap-2 ">
                <div>
                    <p className=" font-quicksand text-gray-400 text-sm font-semibold">TOTAL DE PRODUTOS EM ESTOQUE</p>
                    <p className=" text-purple-medium text-3xl font-bold">0.000</p>
                </div>
                <div className="flex flex-col w-full gap-3">
                    <div>
                        <p className=" font-quicksand text-gray-400 text-sm font-semibold">PRODUTO MENOS POPULAR</p>
                        <div className="flex items-center justify-between">
                            <span className="text-purple-medium text-xl font-bold">Brie com Damasco</span>
                            <img 
                            src={product_plus_icon} 
                            alt="Adicionar produto em novo pedido" />
                        </div>
                    </div>
                    <div>
                        <p className=" font-quicksand text-gray-400 text-sm font-semibold">PRODUTO MAIS POPULAR</p>
                        <div className="flex items-center justify-between">
                            <span className="text-purple-medium text-xl font-bold">Pão com ovo</span>
                            <img 
                            src={product_plus_icon} 
                            alt="Adicionar produto em novo pedido" />
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}