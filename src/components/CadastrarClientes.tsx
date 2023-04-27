import clienteIcon from '../assets/costumer-icon-on.svg'

export function CadastrarClientes() {
    return (
            <section className='w-full flex flex-col items-center'>
                <p className="font-quicksand font-bold text-2xl py-3 px-7">Clientes</p>
                <div className='flex-col items-center px-7 my-1 bg-white font-quicksand font-semibold border-y w-[23rem] sm:w-[50rem] '>
                    <div>
                        <h3 className="text-lg py-2">Cadastrar Cliente</h3>
                        <form className="text-sm text-purple-medium">
                            <div className="mb-4">
                                <label htmlFor="">NOME COMPLETO</label>
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
                            <button type="submit" className="flex items-center justify-center w-full h-10 mt-2 mb-4 border-2 rounded  bg-purple-medium border-purple-dark shadow-md shadow-purple-shadow text-white text-base"> CADASTRAR CLIENTE
                                <img className="mx-1" src={clienteIcon} alt="" />
                            </button>
                        </form>
                    </div>
                </div>
            </section>
    )
}