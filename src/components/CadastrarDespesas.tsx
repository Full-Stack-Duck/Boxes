import despesaIcon from '../assets/despesaIcon.svg'

export function CadastrarDespesa(){
    return(
        <section className="flex-col px-7 my-1 bg-white font-quicksand font-semibold border-y"> 
            <div>
                <h3 className="text-lg py-2">Cadastrar Despesas</h3>
                <div className=" flextext-sm text-purple-medium">
                    <article className="mb-4 text-sm">
                        <label htmlFor="">NOME DA DESPESA</label>
                        <input type="text" className="w-full h-8 border rounded border-purple-light" />
                    </article>
                    <article className="flex mb-4 text-sm justify-between gap-x-6 max-w-full">
                        <div className="flex flex-col w-1/2">
                            <label className="" htmlFor="">VALOR</label>
                            <input className="border rounded h-8" type="number" placeholder="Ex: 3.25" />
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
                    <article className="mb-4 text-sm">
                        <label htmlFor="">DATA DA DESPESA</label>
                        <input type="date" id="data" name="data" className="w-full h-8 border rounded border-purple-light" />
                    </article>
                    <article className="flex flex-wrap items-center text-sm mb-5">
                        <label htmlFor="">DESCRIÇÃO</label>
                        <p className="italic pl-5 text-xs text-purple-light"> (opcional) </p>
                        <textarea className="w-full max-h-20 resize-none over-flow-y-auto border rounded" id="" name="" placeholder="Escreva uma breve descrição da despesa..." />
                    </article>
                </div>
                <div>
                    <button className="flex items-center justify-center w-full h-10 mt-2 mb-4 border-2 rounded  bg-purple-medium border-purple-dark shadow-md shadow-purple-shadow text-white"> CADASTRAR DESPESA
                        <img className="mx-1" src={despesaIcon} alt="" />
                    </button>
                </div>
            </div>
        </section>
    )
}