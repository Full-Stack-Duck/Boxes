import estrela from '../assets/estrela.svg'

export function Planos(){
    return(
        <section className="flex flex-col bg-white text-gray-purplegray font-quicksand px-6 py-8 lg:px-32">
            <article className="text-center">
                <h2 className="font-playfair font-bold text-3xl tracking-widest pb-6 lg:text-4xl">PLANOS</h2>
                <p className="text-gray-purplegray font-semibold text-lg pb-5">Conheça os nossos planos de assinatura e escolha aquele que melhor atende a sua empresa.</p>
            </article>
            <div className="lg:flex lg:justify-around">
                <div className="flex justify-center">
                    <div className="bg-purple-dark rounded text-center text-white mb-5 h-60 w-60 justify-items-center hover:shadow-md hover:shadow-pink-medium">
                        <h3 className="font-montserrat font-medium tracking-widest text-xl py-4">ESSENCIAL</h3>
                        <p className="text-4xl font-montserrat">1<span className="font-playfair italic text-3xl"> mês</span></p>
                        <p className="font-playfair italic text-xl pb-4">de acesso</p>
                        <p className="font-montserrat font-bold tracking-wider text-3xl mb-4">R$ 90</p>
                        <a href="" className="px-7 py-1.5 border rounded-2xl font-semibold tracking-widest text-sm">Assine agora!</a>
                    </div>
                </div>
                <div className="flex justify-center">
                    <div className="bg-purple-dark rounded text-center text-white mb-5 h-60 w-60 justify-items-center shadow-lg shadow-pink-medium hover:shadow-xl hover:shadow-pink-medium relative">
                        <h3 className="font-montserrat font-medium tracking-widest text-xl py-4">DELUXE</h3>
                        <p className="text-4xl font-montserrat">12<span className="font-playfair italic text-3xl"> meses</span></p>
                        <p className="font-playfair italic text-xl pb-4">de acesso</p>
                        <p className="font-montserrat font-bold tracking-wider text-3xl mb-4">R$ 82<span className="text-xs font-normal">,10/mês</span></p>
                        <a href="" className="px-7 py-1.5 bg-purple-medium border rounded-2xl font-semibold tracking-widest text-sm">Assine agora!</a>
                        <div className="absolute top-0 right-0">
                            <img src={estrela} alt="" />
                        </div>
                    </div>
                </div>
                <div className="flex justify-center">
                    <div className="bg-purple-dark rounded text-center text-white h-60 w-60 justify-items-center hover:shadow-md hover:shadow-pink-medium">
                        <h3 className="font-montserrat font-medium tracking-widest text-xl py-4">EXTRA</h3>
                        <p className="text-4xl font-montserrat">6<span className="font-playfair italic text-3xl"> meses</span></p>
                        <p className="font-playfair italic text-xl pb-4">de acesso</p>
                        <p className="font-montserrat font-bold tracking-wider text-3xl mb-4">R$ 86<span className="text-xs font-normal">,40/mês</span></p>
                        <a href="" className="px-7 py-1.5 border rounded-2xl font-semibold tracking-widest text-sm">Assine agora!</a>
                    </div>
                </div>
            </div>
        </section>
    )
}