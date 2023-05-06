import wppIcon from '../assets/whatsapp-verde.svg'

export function Contato(){
    return(
        <div className="bg-purple-medium">
            <section className="flex flex-col text-white font-quicksand mx-6 py-8">
            <article className="text-center">
                <h2 className="font-playfair font-bold text-3xl tracking-wide pb-4">CONTATO</h2>
                <div className="mb-4">
                    <p className="">Alguma dúvida ou sugestão?</p>
                    <p className="">Fale conosco por email ou Whatsapp</p>
                </div>
            </article>
            
            <form className="flex mb-4 text-sm gap-x-2">
                <div className="flex-1">
                    <input className="w-full border rounded-lg h-8 placeholder:text-xs placeholder:px-3 placeholder:italic placeholder:text-purple-light placeholder:font-nunito bg-purple-dark" type="text" placeholder="Nome*" />
                </div>
                <div className="flex-1">
                    <input className="w-full border rounded-lg h-8 placeholder:text-xs placeholder:px-3 placeholder:italic placeholder:text-purple-light placeholder:font-nunito bg-purple-dark" type="tel" placeholder="Número de contato" />
                </div>
            </form>

            <form className="mb-4 text-sm">
                <input type="email" id="email" name="email" className="w-full h-8 border rounded-lg border-purple-light placeholder:text-xs placeholder:px-3 placeholder:italic placeholder:text-purple-light placeholder:font-nunito bg-purple-dark" placeholder="E-mail*" />
            </form>

            <form className="flex flex-wrap items-center text-sm mb-5">
                <textarea className="w-full h-28 resize-none over-flow-y-auto border rounded-lg placeholder:text-xs placeholder:px-3 placeholder:pt-2 placeholder:italic placeholder:text-purple-light placeholder:font-nunito bg-purple-dark" id="" name="" placeholder="Insira sua mensagem..." />
                <p className="italic text-xs">* Campos obrigatórios</p>
                <div className="ml-auto">
                    <button className="border border-purple-dark rounded-2xl px-4 py-0.5 mt-2 shadow-md shadow-stone-700">Enviar mensagem</button>
                </div>
            </form>
            <div className="flex justify-center">
                <button className="flex bg-green-medium border border-green-dark rounded-3xl px-5 py-1.5 mt-2 gap-x-2 shadow-md shadow-stone-700">FALE POR WHATSAPP<img src={wppIcon} alt="" /></button>
            </div>
        </section>
        </div>
        
    )
}