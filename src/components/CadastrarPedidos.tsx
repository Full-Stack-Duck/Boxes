import verIcon from "../assets/olho-icon-on.svg";
import editarIcon from "../assets/lapis-icon-on.svg";
import deletarIcon from "../assets/lixeira-icon-on.svg";

import { useState } from "react";
import styles from "./CadastrarUsuario.module.css";
import adicionarIcon from "../assets/botao-adicionar-item.svg";

import { ComboBoxProducts } from "./ComboBoxProducts";

import { z } from "zod";
import { useForm, useFieldArray } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import Dinero from "dinero.js";

export const productSchema = z.object({
  id: z.number(),
  nome: z.string(),
  valor: z.number(),
});

const itemOrcamentoSchema = z.object({
  quantidade: z.number(),
  precoUnit: z.number(),
  precoTotal: z.number(),
  desconto: z.number(),
  produto: productSchema.nullable(),
});

const clientSchema = z.object({
  id: z.number(),
  nome: z.string(),
  email: z.string(),
  telefone: z.string(),
  dataNascimento: z.null(),
  documento: z.string(),
  dataCliente: z.null(),
  statusCliente: z.string(),
});

const pedidoSchema = z.object({
  id: z.number(),
  total: z.number(),
  tipoEntrega: z.string(),
  dataOrcamento: z.date(),
  status: z.string(),
  dataEntrega: z.date(),
  desconto: z.number(),
  cliente: clientSchema,
  itens: z.array(itemOrcamentoSchema),
});

export type PedidoData = z.infer<typeof pedidoSchema>;
export type ProductData = z.infer<typeof productSchema>;
export type ItemData = z.infer<typeof itemOrcamentoSchema>;

export function CadastrarPedidos() {
  const { register, control } = useForm<PedidoData>({
    resolver: zodResolver(pedidoSchema),
  });

  const { fields, append, remove } = useFieldArray<PedidoData>({
    control,
    name: "itens",
  });

  const [radioOption, setRadioOption] = useState(true);

  function handleIsToDelivery() {
    setRadioOption(!radioOption);
  }

  function handleSetValue(newValue: ProductData): void {
    setProductValue(newValue);
  }

  const [inputIndexs, setInputIndexs] = useState<number>(0);
  const [productValue, setProductValue] = useState<ProductData | null>(null);
  const [desconto, setDesconto] = useState<number>(0);
  const [quantidade, setQuantidade] = useState<number>(0);
  // const [nome, setNome] = useState<string>('')
  const [preco, setPreco] = useState<number>(0);
  const [precoTotal, setPrecoTotal] = useState<number>(0);

  function addNewItem(){
    append({
      quantidade: quantidade,
      desconto: desconto,
      precoUnit: preco,
      produto: productValue,
      precoTotal: precoTotal,
    })
  }

  return (
    <section className="flex flex-col justify-center items-center w-full">
      <div className="flex flex-col px-7 my-1 bg-white font-quicksand font-semibold border-t w-[23rem] sm:w-[50rem]">
        <p className="font-quicksand font-bold text-2xl py-3 px-7">Pedidos</p>
        <div>
          <div>
            <h3 className="text-lg py-2">Cadastrar Pedido ou Orçamento</h3>
            <form className="text-sm text-purple-medium">
              <div className="flex justify-around text-xs text-purple-medium py-2 mb-2">
                <div className="flex items-center gap-x-1">
                  <input
                    type="radio"
                    name="categoria"
                    id="orcamento"
                    value="estocavel"
                    className={styles.inputRadio}
                    defaultChecked
                  />
                  <label htmlFor="orcamento" className="cursor-pointer">
                    ORÇAMENTO
                  </label>
                </div>

                <div className="flex items-center gap-x-1">
                  <input
                    type="radio"
                    name="categoria"
                    id="pedido"
                    value="naoestocavel"
                    className={styles.inputRadio}
                  />
                  <label htmlFor="pedido" className="cursor-pointer">
                    PEDIDO
                  </label>
                </div>
              </div>

              <div className="mb-4">
                <label htmlFor="">NOME DO CLIENTE</label>
                <input
                  type="text"
                  className="w-full h-8 border rounded border-purple-light"
                />
              </div>

              <div className="mb-4">
                <label htmlFor="email">E-MAIL</label>
                <input
                  type="email"
                  id="email"
                  name="email"
                  placeholder="ex: cliente123@email.com"
                  className="w-full h-8 border rounded border-purple-light placeholder:p-2 placeholder:italic placeholder:text-gray-lightgray"
                />
              </div>

              <div className="mb-4">
                <label htmlFor="telefone">TELEFONE</label>
                <input
                  type="tel"
                  id="telefone"
                  name="telefone"
                  placeholder="ex: (DDD) 0 0000-0000"
                  className="w-full h-8 border rounded border-purple-light placeholder:p-2 placeholder:italic placeholder:text-gray-lightgray"
                />
              </div>

              <div className="mb-4">
                <label htmlFor="">DATA DO EVENTO</label>
                <input
                  type="date"
                  id="data"
                  name="data"
                  className="w-full h-8 border rounded border-purple-light"
                />
              </div>

              <div className="flex justify-around text-xs text-purple-medium py-2 mb-2">
                <div className="flex items-center gap-x-1">
                  <input
                    type="radio"
                    name="tipoEntrega"
                    id="retirar"
                    value="estocavel"
                    className={styles.inputRadio}
                    onChange={() => handleIsToDelivery()}
                  />
                  <label htmlFor="retirar" className="cursor-pointer">
                    RETIRADA NO LOCAL
                  </label>
                </div>
                <div className="flex items-center gap-x-1">
                  <input
                    type="radio"
                    name="tipoEntrega"
                    id="entregar"
                    value="naoestocavel"
                    className={styles.inputRadio}
                    onChange={() => handleIsToDelivery()}
                    defaultChecked={radioOption}
                  />
                  <label htmlFor="entregar" className="cursor-pointer">
                    ENTREGA
                  </label>
                </div>
              </div>

              {radioOption && (
                <div className="flex flex-wrap items-center text-sm mb-5">
                  <label htmlFor="">ENDEREÇO DA ENTREGA</label>
                  <textarea
                    className="w-full h-16 resize-none over-flow-y-auto border rounded placeholder:text-xs placeholder:p-2 placeholder:italic placeholder:text-gray-lightgray"
                    id=""
                    name=""
                    placeholder="Ex: Rua, nº, Bairro, complemento"
                  />
                </div>
              )}
            </form>
          </div>
        </div>
      </div>

      <div className="flex justify-center  my-1 bg-white font-quicksand font-semibold ">
        <form className="text-sm text-purple-medium w-[23rem] sm:w-[50rem] px-7">
          <h3 className="text-lg py-2">INSIRA O PRODUTO</h3>
          <div className="flex mb-4 text-sm justify-between gap-x-6 max-w-full">
            <div className="flex flex-col w-1/2">
              <label className="" htmlFor="">
                CATEGORIA
              </label>
              <select
                className="border rounded-md font-nunito h-8"
                name=""
                id=""
              >
                <option className="text-purple-light text-xs" value="">
                  Escolha a categoria
                </option>
                <option value="Estocavel">Estocável</option>
                <option value="Nao-Estocavel">Não-Estocável</option>
              </select>
            </div>
            <div className="flex flex-col w-1/2">
              <label htmlFor="">TIPO</label>
              <select
                className="border rounded-md font-nunito h-8"
                name=""
                id=""
              >
                <option className="text-purple-light text-xs" value="">
                  Escolha o tipo
                </option>
              </select>
            </div>
          </div>

          <div className="mb-4">
            <label htmlFor="name">NOME DO PRODUTO</label>
            {/* <select
              className="w-full border rounded-md font-nunito h-8"
              name=""
              id=""
            >
              <option className="text-purple-light text-xs" value="">
                Escolha o produto
              </option>
              <option value=""> </option>
            </select> */}
            {/* <input type="text" 
            {...register(`produto.${inputIndexs}.nome`)} id="name" className="w-full border rounded-md font-nunito h-8 px-3" onChange={event => setProductName(event.target.value)}/> */}
            {/* <ComboBoxProducts
              onValueChange={handleSetValue}
              {...register(`itens.${fields.length}.produto.nome`)}
            /> */}
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
                {...register(`itens.${fields.length}.produto.valor`)}
                value={productValue?.valor}
                onChange={(event) => setPreco(parseFloat(event.target.value))}
              />
            </div>

            <div className="flex flex-col w-[50%]">
              <label htmlFor="">QUANTIDADE</label>
              <input
                className="border rounded h-8 placeholder:text-xs placeholder:p-2 placeholder:italic placeholder:text-gray-lightgray"
                type="number"
                placeholder="Insira a quantidade"
                {...register(`itens.${fields.length}.quantidade`)}
                onChange={(event) =>
                  setQuantidade(parseFloat(event.target.value))
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
                {...register(`itens.${fields.length}.desconto`)}
                onChange={(event) =>
                  setDesconto(parseFloat(event.target.value))
                }
              />
            </div>

            <div className="flex justify-end self-end w-[50%]">
              <button
                type="button"
                className="flex items-center justify-center h-[32px] min-w-[32px] rounded-full"
                onClick={addNewItem}
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
                <span>{Dinero({amount: precoTotal * 100, currency: "BRL"}).setLocale("pt-BR").toFormat("$0,0.00")}</span>
            </div>
          </div>
        </form>
      </div>
      <div className="flex justify-center">
        <div className="flex-col items-center justify-center w-[23rem] sm:w-[50rem]">
          {fields.map((field, index) => {
            console.log(JSON.stringify(fields));
            return (
              <div key={field.id}>
                <li className="flex border-b-2 border-purple-light justify-center items-center w-full my-2 rounded hover:bg-purple-100">
                  <div className="flex-col items-center justify-center w-full">
                    <p className="flex justify-around text-sm">
                      <span className="">#{index}</span>
                      <span className="">{field.produto?.nome}</span>
                      <span className="">{field.quantidade} un.</span>
                      <span className="">
                        {/* {Dinero({amount: (parseInt(((field.precoUnit * 100) * (field.desconto / 100)).toString())), currency: "BRL"}).setLocale("pt-BR").toFormat("$0,0.00")} */}
                        {field.precoUnit}
                      </span>
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
        </div>
      </div>
    </section>
  );
}
