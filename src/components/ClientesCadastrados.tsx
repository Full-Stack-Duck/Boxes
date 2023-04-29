import client_plus_icon from "../assets/client_plus_icon.svg";

export function ClientesCadastrados() {
  return (
    <section className="flex flex-col w-full items-center">
      <div className="flex flex-col px-7 items-start w-[23rem] sm:w-[50rem] border-b-2">
        <h1 className=" font-quicksand font-bold text-lg pt-3">
          Clientes Cadastrados
        </h1>

        <div className="py-5">
          <p className=" font-quicksand text-gray-400 text-sm font-semibold ">
            TOTAL DE CLIENTES
          </p>
          <p className="text-purple-medium text-3xl font-bold">239</p>
        </div>
        <div className="py-5">
          <p className=" font-quicksand text-gray-400 text-sm font-semibold">
            NOVOS CLIENTES
          </p>
          <p className="text-purple-medium text-3xl font-bold">5</p>
        </div>
        <div className="w-full flex justify-between items-center pb-5">
          <p className="text-xs text-gray-400">
            *Dentro do período selecionado
          </p>
          <a className="flex items-center justify-center w-10 h-10 rounded-full border border-purple-medium">
            <img src={client_plus_icon} className="w-7 h-7" />
          </a>
        </div>
      </div>
    </section>
  );
}
