import verIcon from '../assets/olho-icon-on.svg'
import editarIcon from '../assets/lapis-icon-on.svg'
import deletarIcon from '../assets/lixeira-icon-on.svg'


import styles from "../components/BotaoPesquisar.module.css";
import searchIcon from "../assets/searchIcon.svg";
import { ResultadoCliente } from "./ResultadoCliente";
import { Values, z } from "zod";
import { api } from "../server/api";
import { ChangeEvent, useEffect, useState } from "react";
// import Dinero from 'dinero.js';

const enumType = [
  "Selecione o filtro...",
  "NOME",
  "EMAIL",
  "TELEFONE",
] as const;
// FRITO(1),
// FORNO(2),
// PRATO(3),
// BEBIDA(4),
// SOBREMESA(5),
// DOCE(6),
// ACOMPANHAMENTO(7),
// BEBIDA_NE(8),
// BOLOS_E_TORTAS(9);

const enumCategory = [
  "Selecione a categoria...",
  "ESTOCAVEL", 
  "NAO_ESTOCAVEL"
] as const;

const CategoryEnumSchema = z.enum(enumCategory)
const TypeEnumSchema = z.enum(enumType)

const ProdutoSchema = z.object(
  {
    id: z.number(),
    nome: z.string(),
    valor: z.number(),
    categoria: z.string(),
    quantidade: z.number(),
    tipo: z.string(),
    status: z.string(),
    observacao: z.string(),
  }
) 

type ProdutoType = z.infer<typeof ProdutoSchema>
type CategoryTypes = z.infer<typeof CategoryEnumSchema>
type TypeTypes = z.infer<typeof TypeEnumSchema>

export function PesquisarCliente() {

  const [data, setData] = useState<ProdutoType[]>();
  const [category, setCategory] = useState<CategoryTypes>(enumCategory[0])
  const [type, setType] = useState<TypeTypes>(enumType[0])


  const fetchProdutos =async () => {
    try {
        const response = await api.get('/produtos', {})
        console.log(JSON.stringify(response))
        setData(response.data)
        return response.data
      
    } catch(e){
      console.log(e)
    }
  }

function filtrarProdutosPor(
  categoria : string, 
  // tipo? : string, 
  // nome? : string
  ){
    if(categoria != enumCategory[0]){
      pesquisarPorCategoria(categoria)
    } else {
      fetchProdutos()
    }
}

  const pesquisarPorCategoria = async ( category : string ) => {
    try {
      const response = await api.get('/produtos/1/categoria', {
        params: 
        {
          categoria: category.toUpperCase()
        }
      })
      setData(response.data)
      console.log(JSON.stringify(response))
      return response.data

    } catch(e){
      console.log(e)
    }
  }
  
  useEffect(() => {
    data
  }, [data])

  const handleTypeChange = (event: ChangeEvent<HTMLSelectElement>) => {
    const selectedIndex = Number(event.target.value); 
    setType(enumType[selectedIndex])
    console.log(`Opção selecionada: ${enumType[selectedIndex]}`);
  };

  const handleCategoryChange = (event: ChangeEvent<HTMLSelectElement>) => {
    const selectedIndex = Number(event.target.value); 
    setCategory(enumCategory[selectedIndex])
    console.log(`Opção selecionada: ${enumCategory[selectedIndex]}`);
  };


  return (
    <section className="w-full flex justify-center border-y bg-white font-quicksand font-semibold lg:w-1/2 lg:border-purple-light lg:border lg:rounded-xl lg:ml-5">
      <div className="flex-col items-center px-7 w-full">
        <div>
          <h3 className="text-lg py-2">Pesquisar Cliente</h3>
          <article className="flex-row text-sm text-purple-medium items-center">
            <form>
              <div className="flex flex-col">
                
                <label htmlFor="">PESQUISAR POR:</label>
                <select
                  className="border rounded font-nunito h-8 mb-4"
                  onChange={(e) => handleTypeChange(e)}
                  >
                  {enumType.map((field, index) => {
                    return (
                      <option
                      className="text-purple-light text-xs"
                      key={index.toString()}
                      value={field}
                      >
                        {field}
                      </option>
                    );
                  })}
                </select>
                <label htmlFor="">PALAVRA-CHAVE:</label>
                <input
                  className="border rounded h-8 border-purple-light"
                  type="text"
                  />
                <button
                className="flex items-center justify-center w-full h-10 mt-4 mb-4 border-2 rounded  bg-purple-medium border-purple-dark shadow-md shadow-purple-shadow text-white"
                type="button"
                onClick={() => filtrarProdutosPor(category)}
                >
                PESQUISAR
                  <img
                    className="w-5 h-5 mx-1 bg-purple-medium"
                    src={searchIcon}
                    alt="lupa_de_pesquisa"
                    />
                </button>
              </div>
            </form>
          </article>
        </div>

        <div className="">
          <ResultadoCliente />
          <ResultadoCliente />
        </div>
        
      </div>
    </section>
  );
}
