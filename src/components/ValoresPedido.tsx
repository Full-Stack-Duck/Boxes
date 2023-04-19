import pedidoIcon from '../assets/order-icon-active.svg'

export function ValoresPedido(){
    return(
        <section className="flex-col px-7 my-1 pt-6 bg-white font-quicksand font-semibold border-b border-purple-light"> 
            <form className="text-sm text-purple-medium">

                <div className="flex mb-4 justify-between gap-2 max-w-full">
                    <div className="flex flex-col w-[50%]">
                        <div className="flex items-center gap-x-2">
                            <label className="" htmlFor="">DESCONTO</label>
                            <p className="text-xs text-gray-lightgray italic"> EM REAIS</p>
                        </div>
                        <input className="border rounded h-8 border-purple-light placeholder:text-xs placeholder:p-2 placeholder:italic placeholder:text-gray-lightgray" type="number" placeholder="Insira o valor" />
                    </div>

                    <div className="flex flex-col w-[50%]">
                        <label htmlFor="">CUSTO DE ENTREGA</label>
                        <input className="border rounded h-8 border-purple-light placeholder:text-xs placeholder:p-2 placeholder:italic placeholder:text-gray-lightgray" type="number" placeholder="Insira o valor" />
                    </div>
                </div>
                <article className="text-base text-black">
                    <div className="my-4">
                        <h3>SUBTOTAL</h3>
                        <span className="font-montserrat text-xl text-purple-shadow">R$ 1.800,00</span>
                    </div>
                    <div className="my-4">
                        <h3>TOTAL DE DESCONTO</h3>
                        <span className="font-montserrat text-xl text-purple-shadow">R$ 200,00</span>
                    </div>
                    <div className="my-4">
                        <h3 className="text-lg">TOTAL</h3>
                        <span className="font-montserrat text-3xl text-purple-medium font-bold">R$ 1.600,00</span>
                    </div>
                </article>
                <button type="submit" className="flex items-center justify-center w-full h-10 mt-2 mb-4 border-2 rounded  bg-purple-medium border-purple-dark shadow-md shadow-purple-shadow text-white text-base"> CADASTRAR<img className="mx-3" src={pedidoIcon} alt="" />
                </button>
            </form>
        </section>
    )
}