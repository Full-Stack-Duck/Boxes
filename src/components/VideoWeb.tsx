

export function VideoWeb(){
    return(
        <section className="flex-col bg-white text-gray-solidgray font-quicksand px-6 pt-4">
            <article className="text-center py-2">
                <h2 className="text-3xl pb-6">Juntamos
                    <em className="font-playfair font-semibold"> tecnologia e gestão </em>para ajudar a micro e pequena empresa a crescer
                </h2>
                <p className="flex-col text-sm text-justify my-3">No <strong>Boxes</strong>, todas as funcionalides são integradas e desenvolvidas para fazer sua empresa crescer. Emissão de <strong>orçamentos</strong>, controle de estoque e financeiro, além de cadastro de clientes e produtos.</p>
                <p className="flex-col text-sm my-3">Nosso objetivo é trazer as melhores <strong>práticas de gestão</strong>, especializada ao setor de Eventos e Buffet, através de uma plataforma <strong>intuitiva e eficiente.</strong></p>
            </article>
            <div className="flex items-center justify-center w-full mb-4">
                <iframe className="w-full h-60 rounded-3xl" src="https://www.youtube.com/embed/-DXU_8_9s3A" title="YouTube video player" frameBorder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowFullScreen></iframe>
            </div>
        </section>
    )
}