import product_plus_icon from "../assets/product_plus_icon.svg";

export function ProdutosEstoque() {
    return (
        <>
            <div className="px-7 border-b-2">
                <p className="font-quicksand font-semibold text-lg pt-3">Produtos & Estoques</p>
                <div className="pt-5">
                    <p className=" font-quicksand text-gray-400 text-sm font-semibold">TOTAL DE PRODUTOS EM ESTOQUE</p>
                    <p className=" text-purple-medium text-3xl font-bold">0.000</p>
                </div>
                <div className="pt-4">
                    <p className=" font-quicksand text-gray-400 text-sm font-semibold">PRODUTO MAIS POPULAR</p>
                    <p className=" text-purple-medium text-xl font-bold">'Pastel de carne'</p>
                </div>
                <div className="py-4">
                    <p className=" font-quicksand text-gray-400 text-sm font-semibold">PRODUTO MENOS POPULAR</p>
                    <div className="flex justify-between">
                        <p className=" text-purple-medium text-xl font-bold">'Brie com Damasco'</p>
                        <img src={product_plus_icon} className="w-7"></img>
                    </div> 
                </div>
            </div>
        </>
    )
}