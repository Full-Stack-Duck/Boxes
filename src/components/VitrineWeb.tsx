

export function Vitrine(){
    return(
        <section className="flex-col bg-purple-dark text-white font-quicksand px-6 pt-8">
            <article className="text-center py-2">
                <h1 className="text-2xl">Organize o seu negócio para
                    <strong className="text-purple-stroke"> vender mais com</strong><span className="font-viga"> Boxes</span>
                </h1>
                <p className="flex-col justify-self-center text-sm my-3">O sistema de gestão feito para <strong>organizar</strong> e <strong>integrar</strong> as etapas de seu negócio para você dedicar <strong>mais tempo pensando fora da caixa</strong></p>
            </article>
            <img src="/src/assets/img-ideia.svg" alt="ilustração-dois-homens-discutindo-ideias" className="flex items-center justify-center w-full" />
            <div className="flex items-center justify-center w-full py-6">
                <button className="bg-purple-medium px-4 rounded-3xl w-52 h-10 tracking-widest font-medium">Assine Grátis!</button>
            </div>
        </section>
    )
}