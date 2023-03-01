

export function Pesquisar(){
    return(
        <section className="flex-col px-7 bg-white font-quicksand font-semibold">
            <div className="h-px bg-purple-light my-1.5"></div>
            <div className="flex">
                <h3 className="text-lg">Pesquisar</h3>
                <p>"INSERIR VARIAVEL"</p>
            </div>
            <article className="font-sm text-purple-dark">
                <form action="">
                    <label htmlFor="">PESQUISAR POR:</label>
                    <select className="border-2 rounded w-44 h-8 border-purple-light" name="" id="">
                        <option value="valor0">Selecione o filtro</option>
                        <option value="valor1">Valor 1</option>
                        <option value="valor2">Valor 2 </option>
                        <option value="valor3">Valor 3</option>
                    </select>
                </form>
                <form action="">
                    <label htmlFor="">PALAVRA-CHAVE:</label>
                    <input className="border-2 rounded w-44 h-8 border-purple-light" type="text" />
                </form>
            </article>
            <button type="submit">Pesquisar</button>
            <div className="h-px bg-purple-light my-1.5"></div>
        </section>
    )
}