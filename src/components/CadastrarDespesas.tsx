

export function CadastrarDespesa(){
    return(
        <section className="flex-col px-7 bg-white font-quicksand font-semibold">
            <div className="h-px bg-purple-light my-1.5"></div>
            <div>
                <h3 className="text-lg py-2">Cadastrar Despesas</h3>
                <div className="text-sm text-purple-medium">
                    <article>
                        <label htmlFor="">NOME DA DESPESA</label>
                        <input type="text" className="w-full border rounded border-purple-light" />
                    </article>
                    <article className="flex-wrap">
                        <label className="" htmlFor="">VALOR</label>
                        <input className="border rounded"type="number" placeholder="Ex: 3.25" />
                        <label htmlFor="">TIPO</label>
                        <select className="border rounded" name="" id="" >
                            <option value="">Escolha o tipo</option>
                            <option value="">Valor1</option>
                            <option value="">Valor2</option>
                            <option value="">Valor3</option>
                        </select>
                    </article>
                    <article className="flex flex-wrap items-center">
                        <label htmlFor="">DESCRIÇÃO</label>
                        <p className="italic pl-5 text-xs text-purple-light"> (opcional) </p>
                        <input className="w-full h-20 border rounded" type="text" placeholder="Escreva uma breve descrição da despesa..." />
                    </article>
                </div>
                <div>
                    <button className="flex items-center justify-center w-full h-10 mt-2 mb-4 border-2 rounded  bg-purple-medium border-purple-dark shadow-md shadow-purple-shadow text-white"> CADASTRAR DESPESA
                        <img src="" alt="" />
                    </button>
                </div>
                <div className="h-px bg-purple-light my-1.5"></div>
            </div>
        </section>
    )
}