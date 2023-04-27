import productIcon from '../assets/product-icon-active.svg'

export function CadastrarProdutos() {
    return (
      <section className='w-full flex flex-col items-center'>
        <p className="font-quicksand font-bold text-2xl py-3 px-7">Produtos</p>

        <div className="max-w-[375px] flex-col px-7 my-1 bg-white font-quicksand font-semibold border-y">
          <div>
            <h3 className="text-lg py-2">Cadastrar Produtos</h3>
            <div className="text-sm text-purple-medium">
              <article className="mb-4 text-sm">
                <form className="flex justify-around text-xs text-purple-medium py-2">
                  <div className="flex items-center gap-x-1">
                    <input type="radio" name="categoria" value="estocavel" />
                    <label htmlFor="">ESTOCÁVEL</label>
                  </div>
                  <div className="flex items-center gap-x-1">
                    <input type="radio" name="categoria" value="naoestocavel" />
                    <label htmlFor="">NÃO-ESTOCÁVEL</label>
                  </div>
                </form>
                <label htmlFor="">NOME DO PRODUTO</label>
                <input
                  type="text"
                  className="w-full h-8 border rounded border-purple-light"
                />
              </article>
              <article className="flex mb-4 text-sm justify-between gap-x-6 max-w-full">
                <div className="flex flex-col w-1/2">
                  <label className="" htmlFor="">
                    PREÇO UNITÁRIO
                  </label>
                  <input
                    className="border rounded h-8 placeholder:text-xs placeholder:p-2 placeholder:italic placeholder:text-gray-lightgray"
                    type="number"
                    placeholder="Ex: 3.25"
                  />
                </div>
                <div className="flex flex-col w-1/2">
                  <label htmlFor="">TIPO</label>
                  <select
                    className="border rounded font-nunito h-8"
                    name=""
                    id=""
                  >
                    <option className="text-purple-light text-xs" value="">
                      Escolha o tipo
                    </option>
                    <option value="">Fixa</option>
                    <option value="">Variável</option>
                    <option value="">Material</option>
                    <option value="">Outros</option>
                  </select>
                </div>
              </article>

              <article className="flex flex-wrap items-center text-sm mb-5">
                <label htmlFor="">DESCRIÇÃO</label>
                <p className="italic pl-5 text-xs text-purple-light">
                  {" "}
                  (opcional){" "}
                </p>
                <textarea
                  className="w-full h-20 resize-none over-flow-y-auto border rounded placeholder:text-xs placeholder:p-2 placeholder:italic placeholder:text-gray-lightgray"
                  id=""
                  name=""
                  placeholder="Escreva uma breve descrição da despesa..."
                />
              </article>
            </div>
            <div>
              <button className="flex items-center justify-center w-full h-10 mt-2 mb-4 border-2 rounded  bg-purple-medium border-purple-dark shadow-md shadow-purple-shadow text-white">
                {" "}
                CADASTRAR PRODUTO
                <img className="mx-1" src={productIcon} alt="" />
              </button>
            </div>
          </div>
        </div>
      </section>
    );
}