import deletarIcon from '../assets/lixeira-icon-on.svg'

export function ItemPedido(){
    return(
        <section className="flex-col px-7 my-1 bg-white font-quicksand font-semibold"> 
            <div>
                <h3 className="text-lg py-2"># ITEM 01</h3>
                <form className="text-sm text-purple-medium">

                    <div className="flex mb-4 text-sm justify-between gap-x-6 max-w-full">
                        <div className="flex flex-col w-1/2">
                            <label className="" htmlFor="">CATEGORIA</label>
                            <select className="border rounded-md font-nunito h-8" name="" id="" >
                                <option className="text-purple-light text-xs" value="">Escolha a categoria</option>
                                <option value="">Estocável</option>
                                <option value="">Não-Estocável</option>
                            </select>
                        </div>
                        <div className="flex flex-col w-1/2">
                            <label htmlFor="">TIPO</label>
                            <select className="border rounded-md font-nunito h-8" name="" id="" >
                                <option className="text-purple-light text-xs" value="">Escolha o tipo</option>
                                <option value="">        </option>
                            </select>
                        </div>
                    </div>
                   
                    <div className="mb-4">
                        <label htmlFor="">NOME DO PRODUTO</label>
                        <select className="w-full border rounded-md font-nunito h-8" name="" id="" >
                                <option className="text-purple-light text-xs" value="">Escolha o produto</option>
                                <option value="">     </option>
                        </select>
                    </div>

                    <div className="flex mb-4 justify-between gap-x-6 max-w-full">
                        <div className="flex flex-col w-[50%]">
                            <label className="" htmlFor="">PREÇO UN.</label>
                            <p className="border rounded h-8 text-base pl-2 py-1 border-purple-light bg-slate-100"></p>
                        </div>

                        <div className="flex flex-col w-[50%]">
                            <label htmlFor="">QUANTIDADE</label>
                            <input className="border rounded h-8 placeholder:text-xs placeholder:p-2 placeholder:italic placeholder:text-gray-lightgray" type="number" placeholder="Insira a quantidade" />
                        </div>
                    </div>

                    <div className="flex mb-4 justify-between gap-x-6 max-w-full">
                        <div className="flex flex-col w-[50%]">
                            <label htmlFor="">DESCONTO UN.</label>
                            <input className="border rounded h-8 placeholder:text-xs placeholder:p-2 placeholder:italic placeholder:text-gray-lightgray" type="number" placeholder="Insira o valor" />
                        </div>

                        <div className="flex justify-end self-end w-[50%]">
                            <a href="#" className="flex items-center justify-center h-[32px] min-w-[32px] rounded-full bg-purple-medium"><img src={deletarIcon} alt="deletar-resultado" className="w-5 h-5" /></a>
                        </div>
                    </div>

                    <div className="flex mb-4 justify-evenly items-center">
                        <h3 className="font-quicksand text-base text-purple-darker ">SUBTOTAL:</h3>
                        <div className="flex font-montserrat font-bold text-xl text-purple-medium gap-2">
                            <p>R$</p>
                            <p>450,00</p>
                        </div>
                    </div>
                    
                </form>
            </div>
        </section>
    )
}