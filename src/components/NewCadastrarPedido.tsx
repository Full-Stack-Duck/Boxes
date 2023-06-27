import verIcon from "../assets/olho-icon-on.svg";
import editarIcon from "../assets/lapis-icon-on.svg";
import deletarIcon from "../assets/lixeira-icon-on.svg";
import pedidoIcon from "../assets/order-icon-active.svg";

import { useState, FormEvent } from "react";
import styles from "./CadastrarUsuario.module.css";
import adicionarIcon from "../assets/botao-adicionar-item.svg";

import { z } from "zod";
import { useForm, useFieldArray } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { Loading } from "../assets/aux_components/Loading";
// import Dinero from "dinero.js";

import { Fragment, useEffect } from "react";
import { Combobox, Transition } from "@headlessui/react";
import { ArrowFatLinesDown, Check } from "phosphor-react";
import { api, token } from "../server/api";

const produtoSchema = z.object({
  nome: z.string(),
  quantidade: z.number(),
  valor: z.number(),
  desconto: z.number().default(0),
});

const pedidoSchema = z.object({
  id: z.string(),
  carrinho: z.array(produtoSchema),
});

export type OrcamentoData = z.infer<typeof pedidoSchema>;
export type ProdutoData = z.infer<typeof produtoSchema>;

export function NewCadastrarPedidos() {
  const [products, setProducts] = useState<ProdutoData[] | null>(null);
  const [selected, setSelected] = useState<ProdutoData>({
    nome: "",
    desconto: 0,
    quantidade: 0,
    valor: 0,
  });
  const [query, setQuery] = useState("");

  const requestProductsData = async ( ) => {
    const response = await api.get('/produtos', {
      headers: {Authorization: `Bearer ${token()}`}
    })
    try {
      if(products == null){
        setProducts(response.data)
      }
    } catch(e){
      setProducts(response.data)
      console.log(e)
    }
  }

  const filteredProduct =
    query === ""
      ? products
      : products?.filter((product, index) => {
          return product.nome.toLowerCase().includes(query.toLowerCase());
        });

  const { register, control, handleSubmit } = useForm<OrcamentoData>({
    resolver: zodResolver(pedidoSchema),
  });
  const { fields, append, remove } = useFieldArray<OrcamentoData>({
    control,
    name: "carrinho",
  });
  // const [isSigningUp, setIsSigningUp] = useState<boolean>(false)
  // const [radioOption, setRadioOption] = useState(true);

  const [fieldsIndex, setFieldsIndex] = useState<number>(fields.length);
  const [productData, setProductData] = useState<ProdutoData | null>(null);
  const [productName, setProductName] = useState<string>("");
  const [productQuantity, setProductQuantity] = useState<number>(0);
  const [productValue, setProductValue] = useState<number>(0);
  const [productDiscount, setProductDiscount] = useState<number>(0);

  function handleSetProductName(newValue: string): void {
    requestProductsData()
    setProductName(newValue)
    setQuery(newValue)
    setProductValue(selected.valor)
  }

  function onSubmitForm() {
    console.log("AAA");
  }

  // function fieldAppending(){
  //   setFieldsIndex()
  // }

  // function handleIsToDelivery() {
  //     setRadioOption(!radioOption);
  //   }

  return (
    <section className="flex flex-col justify-center items-center w-full">
      <div className="flex flex-col px-7 my-1 bg-white font-quicksand font-semibold border-t w-[23rem] sm:w-[50rem]">
        <p className="font-quicksand font-bold text-2xl py-3 px-7">Pedidos</p>
      </div>

      <div className="flex justify-center  my-1 bg-white font-quicksand font-semibold ">
        <form
          onSubmit={handleSubmit(onSubmitForm)}
          className="text-sm text-purple-medium w-[23rem] sm:w-[50rem] px-7"
          >
          <h3 className="text-lg py-2">INSIRA O PRODUTO</h3>

          <div className="mb-4">
            <label htmlFor="">NOME DO PRODUTO</label>
          {/* ==============================COMBOBOX START================================= */}
            <div className="relative">
              <Combobox 
              value={selected} 
              onChange={setSelected}

              >
                <div className="relative mt-1">
                  <div className="relative w-full cursor-default overflow-hidden rounded bg-purple-50 text-left border focus:outline-none focus-visible:ring-2 focus-visible:ring-white focus-visible:ring-opacity-75 focus-visible:ring-offset-2 focus-visible:ring-offset-teal-300 sm:text-sm font-nunito text-purple-medium">
                    <Combobox.Input
                      className="w-full border-none py-2 pl-3 pr-10 text-sm leading-5 focus:ring-0"
                      {...register(`carrinho.${fieldsIndex}.nome`)}
                      displayValue={(product: ProdutoData) => product.nome}
                      onChange={() => handleSetProductName(selected.nome)}
                    />
                    <Combobox.Button 
                    className="absolute inset-y-0 right-0 flex items-center pr-2"
                    onClick={requestProductsData}
                    >
                      <ArrowFatLinesDown
                        size={16}
                        color="#030303"
                        weight="fill"
                      />
                    </Combobox.Button>
                  </div>
                  <Transition
                    as={Fragment}
                    leave="transition ease-in duration-100"
                    leaveFrom="opacity-100"
                    leaveTo="opacity-0"
                    afterLeave={() => setQuery("")}
                  >
                    <Combobox.Options className="absolute mt-1 max-h-60 w-full overflow-auto cursor-pointer rounded-md bg-white py-1 text-base shadow-lg ring-1 ring-black ring-opacity-5 focus:outline-none sm:text-sm">
                      {filteredProduct?.length === 0 && query !== "" ? (
                        <div className="relative cursor-default select-none py-2 px-4 text-gray-700">
                          Produto não encontrado.
                        </div>
                      ) : (
                        filteredProduct?.map((product, index) => (
                          <Combobox.Option
                            key={index}
                            className={({ active }) =>
                              `relative cursor-pointer select-none py-2 pl-10 pr-4 ${
                                active
                                  ? "bg-teal-600 text-white"
                                  : "text-gray-900"
                              }`
                            }
                            value={product}
                          >
                            {({ selected, active }) => (
                              <>
                                <span
                                  className={`block truncate ${
                                    selected ? "font-medium" : "font-normal"
                                  }`}
                                >
                                  {product.nome}
                                </span>
                                {selected ? (
                                  <span
                                    className={`absolute inset-y-0 left-0 flex items-center pl-3 ${
                                      active ? "text-white" : "text-teal-600"
                                    }`}
                                  >
                                    <Check
                                      size={16}
                                      color="#030303"
                                      weight="duotone"
                                    />
                                  </span>
                                ) : null}
                              </>
                            )}
                          </Combobox.Option>
                        ))
                      )}
                    </Combobox.Options>
                  </Transition>
                </div>
              </Combobox>
            </div>

            {/* ==============================COMBOBOX END================================= */}
          </div>

          <div className="flex mb-4 justify-between gap-x-6 max-w-full">
            <div className="flex flex-col w-[50%]">
              <label className="" htmlFor="">
                PREÇO UN.
              </label>
              <input
                type="number"
                disabled
                className="border rounded h-8 text-base pl-2 py-1 border-purple-light bg-slate-100"
                value={selected.valor}
                {...register(`carrinho.${fieldsIndex}.valor`)}
                onChange={
                  (event) =>
                  setProductValue(parseFloat(event.target.value))
                }
              />
            </div>

            <div className="flex flex-col w-[50%]">
              <label htmlFor="">QUANTIDADE</label>
              <input
                className="border rounded h-8 placeholder:text-xs placeholder:p-2 placeholder:italic placeholder:text-gray-lightgray"
                type="number"
                placeholder="Insira a quantidade"
                {...register(`carrinho.${fieldsIndex}.quantidade`)}
                onChange={(event) =>
                  setProductQuantity(parseInt(event.target.value))
                }
              />
            </div>
          </div>

          <div className="flex mb-4 justify-between gap-x-6 max-w-full">
            <div className="flex flex-col w-[50%]">
              <label htmlFor="">DESCONTO UN.</label>
              <input
                className="border rounded h-8 placeholder:text-xs placeholder:p-2 placeholder:italic placeholder:text-gray-lightgray"
                type="number"
                placeholder="Insira o valor"
                {...register(`carrinho.${fieldsIndex}.desconto`)}
                onChange={(event) =>
                  setProductDiscount(parseFloat(event.target.value))
                }
              />
            </div>

            <div className="flex justify-end self-end w-[50%]">
              <button
                type="button"
                className="flex items-center justify-center h-[32px] min-w-[32px] rounded-full"
                onClick={() =>
                  append({
                    nome: productName,
                    quantidade: productQuantity,
                    valor: productValue,
                    desconto: productDiscount,
                  })
                }
              >
                <img
                  src={adicionarIcon}
                  alt="deletar-resultado"
                  className="w-8 h-8"
                />
              </button>
            </div>
          </div>

          <div className="flex mb-4 justify-evenly items-center">
            <h3 className="font-quicksand text-base text-purple-darker ">
              SUBTOTAL:
            </h3>
            <div className="flex font-montserrat font-bold text-xl text-purple-medium gap-2">
              {/* {Dinero({amount: productValue * 100,currency: "BRL"}).setLocale('pt-BR').toFormat("$0,0.00")} */}
            </div>
          </div>
          <div className="flex border-b-2 border-purple-light justify-center items-center w-full my-2 ">
            <li className="flex w-full justify-around items-center">
              <span className="">#ID</span>
              <span className="">Nome</span>
              <span className="">Quantidade(UND)</span>
              <span className="">Desconto(R$)</span>
              <span className="">Valor(R$)</span>
            </li>
          </div>
          {fields.map((field, index) => {
            console.log(JSON.stringify(fields));
            return (
              <div key={field.id}>
                <li className="flex border-b-2 border-purple-light justify-center items-center w-full my-2 rounded hover:bg-purple-100">
                  <div className="flex-col items-center justify-center w-full">
                    <p className="flex justify-around text-sm">
                      <span className="">#{index}</span>
                      <span className="">{field.nome}</span>
                      <span className="">{field.quantidade} un.</span>
                      <span className="">{field.desconto}R$</span>
                      <span className="">R${field.valor}</span>
                    </p>

                    <div className="flex justify-center">
                      <div className="flex justify-evenly my-2 w-32 gap-2">
                        <a
                          href="#"
                          className="flex items-center justify-center h-[32px] min-w-[32px] rounded-full bg-purple-medium"
                        >
                          <img
                            src={verIcon}
                            alt="ver-resultado"
                            className="w-5 h-5"
                          />
                        </a>
                        <a
                          href="#"
                          className="flex items-center justify-center h-[32px] min-w-[32px] rounded-full bg-purple-medium"
                        >
                          <img
                            src={editarIcon}
                            alt="editar-resultado"
                            className="w-5 h-5"
                          />
                        </a>
                        <button
                          onClick={() => remove(index)}
                          className="flex items-center justify-center h-[32px] min-w-[32px] rounded-full bg-purple-medium"
                        >
                          <img
                            src={deletarIcon}
                            alt="deletar-resultado"
                            className="w-5 h-5"
                          />
                        </button>
                      </div>
                    </div>
                  </div>
                </li>
              </div>
            );
          })}
        </form>
      </div>
      <div className="flex justify-center">
        <div className="flex-col items-center justify-center w-[23rem] sm:w-[50rem]"></div>
      </div>
      <div className="flex-col px-7 my-1 pt-6 bg-white font-quicksand font-semibold border-b border-purple-light w-[23rem] sm:w-[50rem]">
        <form className="text-sm text-purple-medium">
          <div className="flex mb-4 justify-between gap-2 max-w-full">
            <div className="flex flex-col w-[50%]">
              <div className="flex items-center gap-x-2">
                <label className="" htmlFor="">
                  DESCONTO
                </label>
                <p className="text-xs text-gray-lightgray italic"> EM REAIS</p>
              </div>
              <input
                className="border rounded h-8 border-purple-light placeholder:text-xs placeholder:p-2 placeholder:italic placeholder:text-gray-lightgray"
                type="number"
                placeholder="Insira o valor"
              />
            </div>

            <div className="flex flex-col w-[50%]">
              <label htmlFor="">CUSTO DE ENTREGA</label>
              <input
                className="border rounded h-8 border-purple-light placeholder:text-xs placeholder:p-2 placeholder:italic placeholder:text-gray-lightgray"
                type="number"
                placeholder="Insira o valor"
              />
            </div>
          </div>
          <article className="text-base text-black">
            <div className="my-4">
              <h3>SUBTOTAL</h3>
              <span className="font-montserrat text-xl text-purple-shadow">
                R$ 1.800,00
              </span>
            </div>
            <div className="my-4">
              <h3>TOTAL DE DESCONTO</h3>
              <span className="font-montserrat text-xl text-purple-shadow">
                R$ 200,00
              </span>
            </div>
            <div className="my-4">
              <h3 className="text-lg">TOTAL</h3>
              <span className="font-montserrat text-3xl text-purple-medium font-bold">
                R$ 1.600,00
              </span>
            </div>
          </article>
          <button
            type="submit"
            className="flex items-center justify-center w-full h-10 mt-2 mb-4 border-2 rounded  bg-purple-medium border-purple-dark shadow-md shadow-purple-shadow text-white text-base"
          >
            {/* { isSigningUp ? <Loading/> : "CADASTRAR" } */}
            CADASTRAR
            <img className="mx-3" src={pedidoIcon} />
          </button>
        </form>
      </div>
    </section>
  );
}
