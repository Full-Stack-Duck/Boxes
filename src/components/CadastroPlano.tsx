import logoIcon from '../assets/logoIcon.svg'

export function CadastroPlano(){
    return(
        <section className='w-full flex justify-center items-center'>
        <div className="flex flex-col items-center justify-center font-quicksand max-w-[375px]">
            <article className=" flex flex-col justify-center items-center">
                    <img src={logoIcon} alt="logomarca" className="w-20 h-20 mb-6 mt-10" />
                    <h1 className="font-semibold tracking-wider text-2xl">Você está quase lá</h1>
                    <h3 className="text-xs text-gray-lightgray pb-8">Por favor, escolha o plano</h3>
            </article>
            <article className="flex-col mx-5">
                <form action="submit">
                    <div className="flex p-4 items-center py-1.5 my-2 font-medium bg-emerald-500 text-white border border-none rounded-xl">
                        <input type="radio" className="w-5 h-5" name='box'/>
                        <div className="ml-5 w-[33%]">
                            <h4 className="font-semibold tracking-widest">Free-trial</h4>
                            <h5>1 mês</h5>
                        </div>
                        <div className='flex flex-col items-end'>
                        <h2 className=" font-montserrat font-bold tracking-widest text-2xl ml-10">GRÁTIS</h2>
                        <p className="text-xs -mt-2">1º mês*</p>
                        </div>
                    </div>

                    <div className="flex p-4 items-center py-1.5 my-2 font-medium bg-purple-plano text-white rounded-xl">
                        <input type="radio" className="w-5 h-5" name='box' checked/>
                        <div className="ml-5 w-[33%]">
                            <h4 className="font-semibold tracking-widest">Essencial</h4>
                            <h5>1 mês</h5>
                        </div>
                        <h2 className="flex align-middle font-montserrat font-bold tracking-widest text-2xl ml-10 whitespace-nowrap">R$ 59</h2>
                        <p className="text-xs">,90/mês</p>

                    </div>

                    <div className="flex p-4 items-center py-1.5 my-2 font-medium bg-purple-plano text-white rounded-xl">
                        <input type="radio" className="w-5 h-5" name='box'/>
                        <div className="ml-3.5 w-[33%]">
                            <h4 className="font-semibold tracking-widest">Extra</h4>
                            <h5>1 mês</h5>
                        </div>
                        <h2 className="flex align-middle font-montserrat font-bold tracking-widest text-2xl ml-10 whitespace-nowrap">R$ 79</h2>
                        <p className="text-xs">,90/mês</p>
                    </div>

                    <div className="flex p-4 items-center py-1.5 my-2 font-medium bg-purple-plano text-white rounded-xl">
                        <input type="radio" className="w-5 h-5" name='box'/>
                        <div className="ml-3.5 w-[33%]">
                            <h4 className="font-semibold tracking-widest">Deluxe</h4>
                            <h5>1 mês</h5>
                        </div>
                        <h2 className="align-middle font-montserrat font-bold tracking-widest text-2xl ml-10 whitespace-nowrap">R$ 99</h2>
                        <p className="text-xs">,90/mês</p>
                    </div>

                    <div className="flex pb-1.5 font-quicksand text-gray-lightgray text-xs whitespace-nowrap">
                        <h3>*Após o período de 30 dias, será cobrado pelo plano <span className='font-bold text-purple-stroke'>Essencial</span>. </h3>
                    </div>
                </form>
                <div>
                    <button type='submit' className="flex items-center justify-center w-full h-10 mt-2 mb-4 rounded-xl  bg-purple-medium shadow-md shadow-purple-shadow text-white tracking-widest">
                        Cadastrar
                    </button>
                </div>
                <div className="flex items-center justify-center gap-2 py-4">
                    <p className="text-xs text-gray-lightgray">Já tem uma conta?</p>
                    <a href="#" className="text-sm text-purple-medium">FAÇA O LOGIN</a>
                </div>
            </article>
        </div>
        </section>
    )
}