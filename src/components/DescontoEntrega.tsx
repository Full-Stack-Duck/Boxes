
export function DescontoEntrega(){
    return(
        <section className="flex-col px-7 my-1 bg-white font-quicksand font-semibold"> 
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
                
            </form>
        </section>
    )
}