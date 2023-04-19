import clienteIcon from '../assets/costumer-icon-on.svg'

export function CadastrarPedidos() {
    return (
        <>
            <p className="font-quicksand font-bold text-2xl py-3 px-7">Pedidos</p>

            <section className="flex-col px-7 my-1 bg-white font-quicksand font-semibold border-t">
                <div>
                    <h3 className="text-lg py-2">Cadastrar Pedido ou Orçamento</h3>
                    <form className="text-sm text-purple-medium">

                        <div className="flex justify-around text-xs text-purple-medium py-2 mb-2">
                            <div className="flex items-center gap-x-1">
                                <input type="radio" name="categoria" value="estocavel" />
                                <label htmlFor="">ORÇAMENTO</label>
                            </div>
                            <div className="flex items-center gap-x-1">
                                <input type="radio" name="categoria" value="naoestocavel" />
                                <label htmlFor="">PEDIDO</label>
                            </div>
                        </div>

                        <div className="mb-4">
                            <label htmlFor="">NOME DO CLIENTE</label>
                            <input type="text" className="w-full h-8 border rounded border-purple-light" />
                        </div>

                        <div className="mb-4">
                            <label htmlFor="email">E-MAIL</label>
                            <input type="email" id="email" name="email" placeholder="ex: cliente123@email.com" className="w-full h-8 border rounded border-purple-light placeholder:p-2 placeholder:italic placeholder:text-gray-lightgray" />
                        </div>

                        <div className="mb-4">
                            <label htmlFor="telefone">TELEFONE</label>
                            <input type="tel" id="telefone" name="telefone" placeholder="ex: (DDD) 0 0000-0000" className="w-full h-8 border rounded border-purple-light placeholder:p-2 placeholder:italic placeholder:text-gray-lightgray" />
                        </div>

                        <div className="mb-4">
                            <label htmlFor="">DATA DA DESPESA</label>
                            <input type="date" id="data" name="data" className="w-full h-8 border rounded border-purple-light" />
                        </div>

                        <div className="flex justify-around text-xs text-purple-medium py-2 mb-2">
                            <div className="flex items-center gap-x-1">
                                <input type="checkbox" name="categoria" value="estocavel" />
                                <label htmlFor="">RETIRADA NO LOCAL</label>
                            </div>
                            <div className="flex items-center gap-x-1">
                                <input type="checkbox" name="categoria" value="naoestocavel" />
                                <label htmlFor="">ENTREGA</label>
                            </div>
                        </div>

                        <div className="flex flex-wrap items-center text-sm mb-5">
                            <label htmlFor="">ENDEREÇO DA ENTREGA</label>
                            <textarea className="w-full h-16 resize-none over-flow-y-auto border rounded placeholder:text-xs placeholder:p-2 placeholder:italic placeholder:text-gray-lightgray" id="" name="" placeholder="Ex: Rua, nº, Bairro, complemento" />
                        </div>
                    </form>
                </div>
            </section>
        </>
    )
}