

export function Vitrine(){
    return(
        <section className="flex flex-col bg-purple-dark text-white font-quicksand px-6 pt-8 lg:px-32">
            <div className="lg:flex lg: justify-around">
                <article className="text-center py-2 lg:w-[50%]">
                    <h1 className="text-2xl lg:text-4xl">Organize o seu negócio para
                        <strong className="text-purple-stroke"> vender mais com</strong><span className="font-viga"> Boxes</span>
                    </h1>
                    <p className="flex-col justify-self-center text-sm my-3 lg:text-lg">O sistema de gestão feito para <strong>organizar</strong> e <strong>integrar</strong> as etapas de seu negócio para você dedicar <strong>mais tempo pensando fora da caixa</strong></p>
                </article>

                <img src="/src/assets/img-ideia.svg" alt="ilustração-dois-homens-discutindo-ideias" className="flex items-center justify-center w-full lg:w-96" />

            </div>


            <div className="flex items-center justify-center w-full py-6">
                <button className="bg-purple-medium px-4 rounded-3xl w-52 h-10 tracking-widest font-medium hover:bg-purple-400">Assine Grátis!</button>
            </div>

        </section>
    )
}