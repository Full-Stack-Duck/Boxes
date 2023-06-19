import verIcon from '../assets/olho-icon-on.svg'
import editarIcon from '../assets/lapis-icon-on.svg'
import deletarIcon from '../assets/lixeira-icon-on.svg'


import styles from "../components/BotaoPesquisar.module.css";
import searchIcon from "../assets/searchIcon.svg";
import { ResultadoCliente } from "./ResultadoCliente";
import { Values, z } from "zod";
import { api } from "../server/api";
import { ChangeEvent, useEffect, useState } from "react";
import Dinero from 'dinero.js';

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

export function Pesquisar() {

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
    <section className="w-full flex justify-center border-y bg-white font-quicksand font-semibold">
      <div className="flex-col items-center px-7 my-1 w-[23rem] sm:w-[50rem]">
        <div>
          <div className="flex pt-3">
            <h3 className="text-lg pr-2">Pesquisar produto</h3>
          </div>
          <article className="flex-row text-sm text-purple-medium items-center">
            <form >
              <div className="flex flex-col gap-2">

                    <label htmlFor="">PESQUISAR CATEGORIA:</label>
              <select
                      className="border rounded font-nunito h-8"
                      onChange={(e) => handleCategoryChange(e)}
                      >
                      {enumCategory.map((field, index) => {
                        return (
                          <option
                          className="text-purple-light text-xs"
                          key={index.toString()}
                          value={index}
                          >
                            {field}
                          </option>
                        );
                      })}
                    </select>
              <label htmlFor="">PESQUISAR TIPO:</label>
              <select
                      className="border rounded font-nunito h-8"
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
                className="border rounded h-8 border-purple-light my-1.5"
                type="text"
                />
            <button
              className="flex items-center justify-center w-full h-10 mt-2 mb-4 border-2 rounded  bg-purple-medium border-purple-dark shadow-md shadow-purple-shadow text-white"
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
          <div className="flex justify-end">
          </div>
        </div>
      <div className="flex justify-center">
      <div className="flex-col items-center justify-center w-[23rem] sm:w-[50rem]">
      <div className="flex w-[23rem] sm:w-[50rem] mx-7 border-b-2 border-purple-light py-1 mb-2 items-center justify-evenly pl-3 gap-11 whitespace-nowrap">
                <span className='w-[12rem]'>CÓDIGO</span>
                <span className='w-[12rem]'>NOME</span>
                <span className='w-[12rem]'>QUANT(UND)</span>
                <span className='w-[12rem]'>TIPO</span>
                <span className='w-[12rem]'>CATEGORIA</span>
                <span className='w-[12rem]'>VALOR(R$)</span>
                </div>
        {data?.map((res, index) => {
          return (
            <li className="flex flex-col w-[23rem] sm:w-[50rem] mx-7 border-b-2 border-purple-light py-1 mb-2 items-center justify-evenly hover:bg-purple-100 transition-colors flex-wrap" key={index.toString()}>
                <div className="flex justify-evenly w-full gap-11 flex-nowrap">
                {/* <h2>#{index}</h2> */}
                <span className='w-[12rem]'>#{res.id}</span>
                <span className='w-[20rem] '>{res.nome}</span>
                <span className='w-[12rem]'>{res.quantidade}UND.</span>
                <span className='w-[12rem]'>{res.tipo}</span>
                <span className='w-[12rem]'>{res.categoria}</span>
                <span className='w-[12rem]'>{Dinero({amount: res.valor * 100, currency: "BRL"}).setLocale('pt-BR').toFormat("$0,0.00")}</span>
                </div>
                <div className="flex w-[50%] justify-evenly">
                    <a href="#" className="flex items-center justify-center h-[32px] min-w-[32px] rounded-full bg-purple-medium"><img src={verIcon} alt="ver-resultado" className="w-5 h-5" /></a>
                    <a href="#" className="flex items-center justify-center h-[32px] min-w-[32px] rounded-full bg-purple-medium"><img src={editarIcon} alt="editar-resultado" className="w-5 h-5" /></a>
                    <a href="#" className="flex items-center justify-center h-[32px] min-w-[32px] rounded-full bg-purple-medium"><img src={deletarIcon} alt="deletar-resultado" className="w-5 h-5" /></a>
                </div>
            </li>
          )
        })}
        {/* <ResultadoCliente />
        <ResultadoCliente />
        <ResultadoCliente /> */}
      </div>
      </div>
      </div>
    </section>
  );
}
