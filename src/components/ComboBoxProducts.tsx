import { Fragment, useState, useEffect } from "react";
import { Combobox, Transition } from "@headlessui/react";
import { ArrowFatLinesDown, Check } from "phosphor-react";
import { ProdutoData } from "./NewCadastrarPedido";
import Dinero from 'dinero.js';


// interface DaddyProps {
//   onValueChange: (value : ProdutoData) => void;
// }


export function ComboBoxProducts( ) {
  const [products, setProducts] = useState<ProdutoData[] | null>(null);
  const [selected, setSelected] = useState<ProdutoData>({nome: '', desconto: 0, quantidade:0, valor:0});
  const [query, setQuery] = useState("");

  useEffect(() => {
    fetch("http://localhost:8080/produtos")
      .then((response) => response.json())
      .then((data) => setProducts(data))
      .catch((error) => console.log(error));
  }, []);
  
  const filteredProduct =
  query === ""
  ? products
  : products?.filter((product, index) => {
    return product.nome.toLowerCase().includes(query.toLowerCase());
  });
  
  selected ? onValueChange(selected) : null

  return (
    <div className="relative">
      <Combobox value={selected} onChange={setSelected}>
        <div className="relative mt-1">
          <div className="relative w-full cursor-default overflow-hidden rounded bg-purple-50 text-left border focus:outline-none focus-visible:ring-2 focus-visible:ring-white focus-visible:ring-opacity-75 focus-visible:ring-offset-2 focus-visible:ring-offset-teal-300 sm:text-sm font-nunito text-purple-medium">
            <Combobox.Input
              className="w-full border-none py-2 pl-3 pr-10 text-sm leading-5 focus:ring-0"
              displayValue={(product: ProdutoData) => product.nome}
              onChange={(event) => setQuery(event.target.value)}
            />
            <Combobox.Button className="absolute inset-y-0 right-0 flex items-center pr-2">
              <ArrowFatLinesDown size={16} color="#030303" weight="fill" />
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
                        active ? "bg-teal-600 text-white" : "text-gray-900"
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
                            <Check size={16} color="#030303" weight="duotone" />
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
  );
}
