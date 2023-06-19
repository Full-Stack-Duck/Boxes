import verIcon from "../assets/olho-icon-on.svg";
import editarIcon from "../assets/lapis-icon-on.svg";
import deletarIcon from "../assets/lixeira-icon-on.svg";
import { ProductValidatingSchema } from "./CadastrarPedidos"
import { object } from "zod";
import Dinero from "dinero.js";


interface ProductPropertys{
  itemProps: ProductValidatingSchema
}

export function ItemLinha( { itemProps } : ProductPropertys) {



  return (
    <div>
      {
        (itemProps.produto.map( (field, index) => {
          return(
            <li key={index} className="flex border-b-2 border-purple-light justify-center items-center w-full my-2 rounded hover:bg-purple-100">
            <div className="flex-col items-center justify-center w-full">
  
            <p className="flex justify-around text-sm">
              <span className="">#{ field.itemID }</span>
              <span className="">{ field.nome }</span>
              <span className="">{ field.quantidade } un.</span>
              <span className="">{ Dinero({amount: (field.valor * 100), currency: "BRL"}).setLocale('pt-BR').toFormat("$0,0.00")}</span>
            </p>
  
            <div className="flex justify-center">
              <div className="flex justify-evenly my-2 w-32 gap-2">
                <a
                  href="#"
                  className="flex items-center justify-center h-[32px] min-w-[32px] rounded-full bg-purple-medium"
                >
                  <img src={verIcon} alt="ver-resultado" className="w-5 h-5" />
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
                <a
                  href="#"
                  className="flex items-center justify-center h-[32px] min-w-[32px] rounded-full bg-purple-medium"
                >
                  <img
                    src={deletarIcon}
                    alt="deletar-resultado"
                    className="w-5 h-5"
                  />
                </a>
            </div>
          </div>
        </div>
      </li>
          )
        } ))
      }

    </div>
  );
}
