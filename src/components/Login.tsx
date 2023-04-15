import logoIcon from '../assets/logoIcon.svg'

export function Login(){
    return(
        <section className="font-quicksand">
            <article className=" flex flex-col justify-center items-center">
                    <img src={logoIcon} alt="logomarca" className="w-20 h-20 mb-6 mt-10" />
                    <h1 className="font-semibold tracking-wider text-2xl">Seja bem-vindo</h1>
                    <h3 className="text-xs text-gray-lightgray pb-12">Por favor, insira seus dados</h3>
            </article>
            <article className="flex-col mx-5">
                <form action="">
                    <div className="py-1.5 font-medium">
                        <label htmlFor="">Email</label>
                        <input type="email" className="w-full h-8 border rounded border-purple-stroke placeholder:text-xs placeholder:px-3 placeholder:font-nunito" placeholder="Escreva seu email"/>
                    </div>

                    <div className="py-1.5 font-medium">
                        <label htmlFor="">Senha</label>
                        <input type="password" className="w-full h-8 border rounded border-purple-stroke"/>
                    </div>

                    <div className="flex py-1.5 font-nunito text-xs">
                        <div className="w-[50%]">
                            <input type="checkbox" className="mr-2 border rounded border-purple-stroke"/>
                            <label htmlFor="">Salvar senha</label>
                        </div>
                        <div className="w-[50%] flex justify-end">
                            <a href="#" className="text-sm text-purple-medium">
                                Esqueci minha senha
                            </a>
                        </div>
                    </div>
                </form>
                <div>
                    <button className="flex items-center justify-center w-full h-10 mt-2 mb-4 rounded-xl  bg-purple-medium shadow-md shadow-purple-shadow text-white tracking-widest">
                        Entrar
                    </button>
                </div>
                <div className="flex items-center justify-center gap-2 py-4">
                    <p className="text-xs text-gray-lightgray">Não tem uma conta?</p>
                    <a href="#" className="text-sm text-purple-medium">CADASTRE-SE</a>
                </div>
            </article>
        </section>
    )
}