// import styles from '../components/BotaoPesquisar.module.css'
import searchIcon from '../assets/searchIcon.svg'
import adicionarIcon from '../assets/botao-adicionar-item.svg'
import retirarIcon from '../assets/botao-retirar-item.svg'

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
                    <input className="border rounded w-full h-8 border-purple-light font-nunito my-1.5 pl-2 placeholder:italic placeholder:text-xs placeholder:font-normal " type="text" placeholder=' Insira o nome do produto ' />
                </form>

            </article>

            <div className="flex justify-end">
                <button className="flex items-center justify-center w-44 h-10 mt-2 mb-4 border-2 rounded  bg-purple-medium border-purple-dark shadow-md shadow-purple-shadow text-white" type="submit">PESQUISAR
                    <img className="w-5 h-5 mx-1 bg-purple-medium" src={searchIcon} alt="lupa_de_pesquisa" />
                </button>
            </div>

            <div className='pt-10'>
                <div className='flex items-center'>
                    <p className='text-purple-light font-quicksand text-sm'>ESTOQUE ATUAL DE</p>
                    <p className='text-purple-medium font-quicksand text-base pl-2'>"RESULTADO PESQUISA"</p>
                </div>

                <div className='flex items-end my-2'>
                    <p className='text-purple-medium text-4xl font-bold font-montserrat pl-4'>324</p>
                    <p className='text-purple-light text-sm font-quicksand pl-3'>unidades</p>
                </div>

                <div className='mb-5'>
                        <article className="flex ">
                            <form className='flex items-center' action="">
                                <p className='text-sm text-purple-medium font-semibold font-quicksand pr-4'>ADICIONAR:</p>
                                <input className="border rounded w-full h-8 border-purple-light font-nunito my-1.5 pl-2" type="text" />
                            </form>


                            <a href="#" className="flex items-center rounded-full ml-5">
                                <img src={adicionarIcon} alt="deletar-resultado" className="w-5 h-5" />
                            </a>
                        </article>


                        <article className="flex">
                            <form className='flex items-center' action="">
                                <p className='text-sm text-purple-medium font-semibold font-quicksand pr-8'>RETIRAR:</p>
                                <input className="border rounded w-full h-8 border-purple-light font-nunito my-1.5 pl-2" type="text" />
                            </form>

                            <a href="#" className="flex items-center rounded-full ml-5 ">
                                <img src={retirarIcon} alt="deletar-resultado" className="w-5 h-5" />
                            </a>
                            
                        </article>


                </div>
            </div>

        </section>
    )
}