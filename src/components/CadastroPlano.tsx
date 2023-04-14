import logoIcon from '../assets/logoIcon.svg'

export function CadastroPlano(){
    return(
        <section className="font-quicksand">
            <article className=" flex flex-col justify-center items-center">
                    <img src={logoIcon} alt="logomarca" className="w-20 h-20 mb-6 mt-10" />
                    <h1 className="font-semibold tracking-wider text-2xl">Você está quase lá</h1>
                    <h3 className="text-xs text-gray-lightgray pb-8">Por favor, escolha o plano</h3>
            </article>
            <article className="flex-col mx-5">
                <form action="">
                    <div className="flex p-4 items-center py-1.5 my-2 font-medium bg-green-soft text-green-hard border border-green-hard rounded-xl">
                        <input type="checkbox" className="w-5 h-5"/>
                        <div className="ml-5 w-[33%]">
                            <h4 className="font-semibold tracking-widest">Free-trial</h4>
                            <h5>1 mês</h5>
                        </div>
                        <h2 className="flex align-middle font-montserrat font-bold tracking-widest text-2xl ml-10">GRÁTIS*</h2>
                        <p className="text-xs"></p>
                    </div>

                    <div className="flex p-4 items-center py-1.5 my-2 font-medium bg-purple-plano text-white rounded-xl">
                        <input type="checkbox" className="w-5 h-5"/>
                        <div className="ml-5 w-[33%]">
                            <h4 className="font-semibold tracking-widest">Essencial</h4>
                            <h5>1 mês</h5>
                        </div>
                        <h2 className="flex align-middle font-montserrat font-bold tracking-widest text-2xl ml-10">R$ 90</h2>
                        <p className="text-xs"></p>
                    </div>

                    <div className="flex p-4 items-center py-1.5 my-2 font-medium bg-purple-plano text-white rounded-xl">
                        <input type="checkbox" className="w-5 h-5"/>
                        <div className="ml-3.5 w-[33%]">
                            <h4 className="font-semibold tracking-widest">Extra</h4>
                            <h5>1 mês</h5>
                        </div>
                        <h2 className="flex align-middle font-montserrat font-bold tracking-widest text-2xl ml-10">R$ 86</h2>
                        <p className="text-xs">,40/mês</p>
                    </div>

                    <div className="flex p-4 items-center py-1.5 my-2 font-medium bg-purple-plano text-white rounded-xl">
                        <input type="checkbox" className="w-5 h-5"/>
                        <div className="ml-3.5 w-[33%]">
                            <h4 className="font-semibold tracking-widest">Delux</h4>
                            <h5>1 mês</h5>
                        </div>
                        <h2 className="flex align-middle font-montserrat font-bold tracking-widest text-2xl ml-10">R$ 82</h2>
                        <p className="text-xs">,10/mês</p>
                    </div>

                    <div className="flex pb-1.5 font-quicksand text-gray-lightgray text-xs">
                        <h3>*Gratuito por 30 dias. Após esse período será cobrado valor do plano mais simples. </h3>
                    </div>
                </form>
                <div>
                    <button className="flex items-center justify-center w-full h-10 mt-2 mb-4 rounded-xl  bg-purple-medium shadow-md shadow-purple-shadow text-white tracking-widest">
                        Cadastrar
                    </button>
                </div>
                <div className="flex justify-center gap-2 py-4">
                    <p className="text-xs text-gray-lightgray">Já tem uma conta?</p>
                    <a href="#" className="text-sm text-purple-medium">FAÇA O LOGIN</a>
                </div>
            </article>
        </section>
    )
}