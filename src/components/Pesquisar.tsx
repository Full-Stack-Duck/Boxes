import styles from '../components/BotaoPesquisar.module.css'
import searchIcon from '../assets/searchIcon.svg'

export function Pesquisar(){
    return(
        <section className="flex-col px-7 bg-white font-quicksand font-semibold">
            <div className="h-px bg-purple-light my-1.5"></div>
            <div className="flex pt-3">
                <h3 className="text-lg pr-2">Pesquisar</h3>
                <p>"INSERIR VARIAVEL"</p>
            </div>
            <article className="flex-row text-sm text-purple-medium items-center">
                <form action="">
                    <label htmlFor="">PESQUISAR POR:</label>
                    <select className="border-2 rounded w-44 h-8 border-purple-light font-nunito ml-7 my-1.5" name="" id="">
                        <option className=" text-purple-light text-xs" value="valor0">Selecione o filtro</option>
                        <option value="valor1">Valor 1</option>
                        <option value="valor2">Valor 2 </option>
                        <option value="valor3">Valor 3</option>
                    </select>
                </form>
                <form action="">
                    <label htmlFor="">PALAVRA-CHAVE:</label>
                    <input className="border-2 rounded w-44 h-8 border-purple-light ml-7 my-1.5" type="text" />
                </form>
            </article>
            <div className="flex justify-end">
                <button className={styles.botaoPesquisar} type="submit">PESQUISAR 
                    <img className={styles.iconPesquisar} src={searchIcon} alt="lupa_de_pesquisa" />
                </button>
            </div>
            <div className="h-px bg-purple-light my-1.5"></div>
        </section>
    )
}