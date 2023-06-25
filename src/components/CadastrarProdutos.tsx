import { useState, ChangeEvent } from 'react';
import productIcon from "../assets/product-icon-active.svg";
import styles from "../components/CadastrarUsuario.module.css";
import { api,token } from "../server/api";
import { z } from "zod";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { Loading } from "../assets/aux_components/Loading";
import Cookies from "js-cookie";
import 'react-toastify/dist/ReactToastify.css';
import { toast } from 'react-toastify';


const enumType = [
  "Selecione o tipo...",
  "FRITO",
  "FORNO",
  "PRATO",
  "BEBIDA",
  "SOBREMESA",
  "DOCE",
  "ACOMPANHAMENTO",
  "BEBIDA_NE",
  "BOLOS E TORTAS",
] as const;

const enumCategory = [
  "ESTOCAVEL", 
  "NAO-ESTOCAVEL"
] as const;

const produtoSchema = z.object({
  nome: z.string(),
  valor: z.number(),
  categoria: z.number(),
  tipo: z.number(),
  observacao: z.string(),
});

type ProdutoType = z.infer<typeof produtoSchema>;

export function CadastrarProdutos() {
  const { register, handleSubmit } = useForm<ProdutoType>({
    resolver: zodResolver(produtoSchema),
  });

  const [isSendingFeedback, setIsSendingFeedback] = useState<boolean>(false);
  const [ radioOption, setRadioOption ] = useState(true)

  function handleProductTypeChange(){
    setRadioOption(!radioOption)
    radioOption 
    ? 
    setCategoria(1) 
    : 
    setCategoria(0) 

  }

  const [nome, setNome] = useState<string>('')
  const [valor, setValor] = useState<number>(0)
  const [tipo, setTipo] = useState<number>(0)
  const [categoria, setCategoria] = useState<number>(0)
  const [observacao, setObservacao] = useState<string>('Sem descrição...')


  async function insertNewProduto(){
    setIsSendingFeedback(true);
    try {
      const response = await api.post("/produtos", {
        nome,
        valor,
        tipo,
        categoria,
        observacao
      }, {headers: {Authorization: `Bearer ${token()}`}});
      setIsSendingFeedback(false);
      console.log(JSON.stringify(response));
      toast.success('Produto cadastrado com sucesso!');
    } catch (error) {
      console.log(error);
      toast.error('Produto não cadastrado!');
      setIsSendingFeedback(false);
    }
  }

  const handleChange = (event: ChangeEvent<HTMLSelectElement>) => {
    const selectedIndex = Number(event.target.value); 
    setTipo(selectedIndex)
    console.log(`Opção selecionada no índice ${selectedIndex}`);
  };


  return (
    <section className="w-full flex justify-center lg:w-1/2">
      <div className="flex flex-col w-[23rem] sm:w-[50rem]">
        <h1 className="font-quicksand font-bold text-2xl py-3 px-7 lg:hidden">
          Produtos
        </h1>
          <div className="flex-col px-7 my-1 bg-white font-quicksand font-semibold text-purple-medium border-y lg:border lg:border-purple-light lg:rounded-xl lg:p-4 lg:m-0">
            <form onSubmit={handleSubmit(insertNewProduto)}>
              <h3 className="text-lg py-2 text-black lg:pt-0">Cadastrar Produtos</h3>
                <article className="flex justify-between mx-4 mb-4 text-sm">
                  <div className="flex items-center justify-center w-1/2">
                    <input
                      type="radio"
                      id={`${enumCategory[0]}`}
                      value={0}
                      className={styles.inputRadio}
                      {...register("categoria")}
                      onChange={() => handleProductTypeChange()}
                      checked={radioOption}
                      />
                    <label htmlFor={`${enumCategory[0]}`} className="cursor-pointer px-1.5">
                      ESTOCÁVEL
                    </label>
                  </div>
                  <div className="flex items-center justify-center w-1/2">
                    <input
                      type="radio"
                      id={`${enumCategory[1]}`}
                      value={1}
                      {...register("categoria")}
                      className={styles.inputRadio}
                      onChange={() => handleProductTypeChange()}
                      checked={!radioOption}
                    />
                    <label htmlFor={`${enumCategory[1]}`} className="cursor-pointer px-1.5">
                      NÃO-ESTOCÁVEL
                    </label>
                  </div>
                  </article>
                  <label>NOME DO PRODUTO</label>
                  <input
                    type="text"
                    className="w-full h-8 border rounded border-purple-light mb-4"
                    {...register('nome')}
                    onChange={(e) => setNome(e.target.value)}
                    />
                <article className="flex mb-4 text-sm justify-between gap-x-6 max-w-full">
                  <div className="flex flex-col w-1/2">
                    <label>
                      PREÇO UNITÁRIO
                    </label>
                    <input
                      className="border rounded h-8 placeholder:text-xs placeholder:p-2 placeholder:italic placeholder:text-gray-lightgray"
                      type="number"
                      placeholder="Ex: 3.25"
                      {...register('valor')}
                      onChange={(e) => setValor(parseFloat(e.target.value.replace(',', '.')))}
                      />
                  </div>
                  <div className="flex flex-col w-1/2">
                    <label htmlFor="">TIPO</label>
                    <select
                      className="border rounded font-nunito h-8"
                      {...register('tipo')}
                      onChange={(e) => handleChange(e)}
                      >
                      {enumType.map((field, index) => {
                        return (
                          <option
                          className="text-purple-light text-xs"
                          key={index.toString()}
                          value={index - 1}
                          >
                            {field}
                          </option>
                        );
                      })}
                      </select>
                  </div>
                </article>

                <article className="flex flex-wrap items-baseline text-sm mb-5 gap-1.5">
                  <label>DESCRIÇÃO</label>
                  <span className="italic text-xs text-purple-light">
                    (opcional){" "}
                  </span>
                  <textarea
                    className="w-full h-20 resize-none over-flow-y-auto border rounded placeholder:text-xs placeholder:p-2 placeholder:italic placeholder:text-gray-lightgray"
                    placeholder="Escreva uma breve descrição da despesa..."
                    {...register('observacao')}
                    onChange={(e) => setObservacao(e.target.value)}
                  />
                </article>
              
                <button
                  className="text-base flex items-center justify-center w-full h-10 mt-2 mb-4 border-2 rounded  bg-purple-medium border-purple-dark shadow-md shadow-purple-shadow text-white"
                  type="submit"
                  disabled={isSendingFeedback}
                  onClick={insertNewProduto}
                >
                  {" "}
                  {isSendingFeedback ? <Loading /> : "CADASTRAR PRODUTO"}
                  <img className="mx-1" src={productIcon} alt="" />
                </button>
        </form>
          </div>
      </div>
    </section>
  );
}
