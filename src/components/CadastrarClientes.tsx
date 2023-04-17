import clienteIcon from '../assets/costumer-icon-on.svg'

export function CadastrarClientes(){
    return(
        <section className="flex-col px-7 my-1 bg-white font-quicksand font-semibold border-y"> 
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
        </section>
    )
}