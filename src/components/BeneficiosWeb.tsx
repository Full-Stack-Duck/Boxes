

export function Beneficios(){
    return(
        <section className="flex flex-col bg-pink-light text-gray-purplegray font-quicksand px-6 py-8">
            <article className="text-center">
                <h2 className="font-playfair font-bold text-3xl tracking-widest pb-6">BENEFÍCIOS</h2>
                <p className="text-gray-purplegray font-semibold pb-8">Veja como o sistema de gestão <span className="font-viga text-lg">Boxes</span> irá ajudar você e sua empresa.</p>
            </article>
            <div className="bg-white rounded border border-pink-soft text-center my-4">
                <h3 className="font-open font-bold py-6">GESTÃO DE TEMPO</h3>
                <p className="text-sm px-7 pb-4">Com o conceito “All-in-One” Tudo-em-Um, do inglês, você terá ao alcance todas as etapas de seu negócio integradas em um só aplicativo, possibilitando um melhor investimento de seu tempo</p>
            </div>
            <div className="bg-white rounded border border-pink-soft text-center my-4">
                <h3 className="font-open font-bold py-6">AGILIDADE NA VENDA</h3>
                <p className="text-sm px-7 pb-4">A agilidade no momento de produzir um orçamento e enviar para o cliente, de forma clara e de fácil leitura, aumenta as suas chances de fechar os contratos de venda, além de repercussão do excelente atendimento ao cliente.</p>
            </div>
            <div className="bg-white rounded border border-pink-soft text-center my-4">
                <h3 className="font-open font-bold py-6">CONTROLE FINANCEIRO</h3>
                <p className="text-sm px-7 pb-4">Conquiste um controle maior de suas finanças - despesas categorizadas, faturamentos, contas a receber e um painel comparativo compõem as funções do sistema.</p>
            </div>
            <div className="bg-white rounded border border-pink-soft text-center my-4">
                <h3 className="font-open font-bold py-6">PRODUTOS EM ESTOQUE</h3>
                <p className="text-sm px-7 pb-4">Cadastre seus produtos e mantenha controle de seu estoque real conforme sua produção e o escoamento do produto através dos pedidos realizados. Saiba exatamente a quantidade de cada produto você tem para pronta-entrega.</p>
            </div>
        </section>
    )
}