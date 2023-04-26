// import styles from '../components/BotaoPesquisar.module.css'
import searchIcon from '../assets/searchIcon.svg'

export function PesquisarEstoque() {
    return (
        <section className="flex-col px-7 my-1 border-y bg-white font-quicksand font-semibold">

            <div className="flex pt-3 pb-4">
                <p className="text-lg pr-2">Selecionar Produto em Estoque</p>
            </div>

            <article className="flex-row text-sm  items-center">
                <form action="">
                    <label className='text-purple-medium' htmlFor="">ESCOLHA TIPO:</label>
                    <select className="border rounded w-44 h-8 text-purple-light border-purple-light font-nunito ml-7 my-1.5" name="" id="">
                        <option className="text-xs" value="valor0">Selecione o tipo</option>
                        <option value="valor1">Valor 1</option>
                        <option value="valor2">Valor 2</option>
                        <option value="valor3">Valor 3</option>
                    </select>
                </form>

                <form action="">
                    <label className='text-purple-medium' htmlFor="">ESCOLHA ITEM:</label>
                    <select className="border rounded w-44 h-8 text-purple-light  border-purple-light font-nunito ml-7 my-1.5" name="" id="">
                        <option className="text-xs" value="valor0">Selecione o tipo</option>
                        <option value="valor1">Valor 1</option>
                        <option value="valor2">Valor 2</option>
                        <option value="valor3">Valor 3</option>
                    </select>
                </form>
            </article>

            <div className="flex justify-end">
                <button className="flex items-center justify-center w-44 h-10 mt-2 mb-4 border-2 rounded  bg-purple-medium border-purple-dark shadow-md shadow-purple-shadow text-white" type="submit">PESQUISAR
                    <img className="w-5 h-5 mx-1 bg-purple-medium" src={searchIcon} alt="lupa_de_pesquisa" />
                </button>
            </div>

            <div className='flex justify-center py-3'>
                <div className='border rounded-full items-center shadow-lg bg-gray-light'>
                    <p className='font-quicksand text-xs mx-3 my-3 font-bold'>OU</p>
                </div>
            </div>

            <article className="flex-row text-sm text-purple-medium items-center">

                <form action="">
                    <p>NOME DO PRODUTO</p>
                    <input className="border rounded w-full h-8 border-purple-light font-nunito my-1.5 placeholder:italic placeholder:text-xs placeholder:font-normal placeholder:pl-2" type="text" placeholder=' Insira o nome do produto ' />
                </form>

            </article>

            <div className="flex justify-end">
                <button className="flex items-center justify-center w-44 h-10 mt-2 mb-4 border-2 rounded  bg-purple-medium border-purple-dark shadow-md shadow-purple-shadow text-white" type="submit">PESQUISAR
                    <img className="w-5 h-5 mx-1 bg-purple-medium" src={searchIcon} alt="lupa_de_pesquisa" />
                </button>
            </div>

        </section>
    )
}