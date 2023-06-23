import despesaIcon from '../assets/despesaIcon.svg'

export function CadastrarDespesa(){
    return(
        <section className='flex flex-col justify-center w-full lg:w-2/3 lg:border lg:rounded-xl lg:border-purple-light lg:ml-4'> 
            <form className="px-7 my-1 bg-white font-quicksand font-semibold border-y lg:border-0 lg:m-0 lg:p-4">
                <div className='w-full'>
                    <h3 className="text-lg pb-2 font-bold">Cadastrar Despesas</h3>
                </div>
                <div className="text-sm text-purple-medium lg:flex lg:flex-row">
                    <div className="lg:w-1/2 lg:pr-4">
                        <article className="mb-4 text-sm">
                            <label htmlFor="">NOME DA DESPESA</label>
                            <input type="text" className="w-full h-8 border rounded border-purple-light" />
                        </article>
                        <article className="flex mb-4 text-sm justify-between gap-x-6 max-w-full">
                            <div className="flex flex-col w-1/2">
                                <label className="" htmlFor="">VALOR</label>
                                <input className="border rounded h-8 placeholder:text-xs placeholder:p-2 placeholder:italic placeholder:text-gray-lightgray" type="number" placeholder="Ex: 3.25" />
                            </div>
                            <div className="flex flex-col w-1/2">
                                <label htmlFor="">TIPO</label>
                                <select className="border rounded font-nunito h-8" name="" id="" >
                                    <option className="text-purple-light text-xs" value="">Escolha o tipo</option>
                                    <option value="">Fixa</option>
                                    <option value="">Variável</option>
                                    <option value="">Material</option>
                                    <option value="">Outros</option>
                                </select>
                            </div>
                        </article>
                    </div>

                    <div className="lg:w-1/2">
                        <article className="mb-4 text-sm">
                            <label htmlFor="">DATA DA DESPESA</label>
                            <input type="date" id="data" name="data" className="w-full h-8 border rounded border-purple-light" />
                        </article>
                        <article className="flex flex-wrap items-center text-sm mb-4">
                            <label htmlFor="">DESCRIÇÃO</label>
                            <p className="italic pl-5 text-xs text-purple-light"> (opcional) </p>
                            <textarea className="w-full h-20 resize-none over-flow-y-auto border rounded placeholder:text-xs placeholder:p-2 placeholder:italic placeholder:text-gray-lightgray lg:h-8" id="" name="" placeholder="Escreva uma breve descrição da despesa..." />
                        </article>
                    </div>
                </div>
                <div className="">
                    <button className="flex items-center justify-center w-full h-10 mt-2 mb-4 border-2 rounded bg-purple-medium border-purple-dark shadow-md shadow-purple-shadow text-white lg:mb-0"> CADASTRAR DESPESA
                        <img className="ml-2" src={despesaIcon} alt="" />
                    </button>
                </div>
            </form>
        </section>
    )
}