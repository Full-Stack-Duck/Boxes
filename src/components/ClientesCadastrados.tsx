import client_plus_icon from "../assets/client_plus_icon.svg";

export function ClientesCadastrados() {
  return (
    <section className="flex flex-col lg:border lg:border-purple-light lg:rounded-xl lg:p-4">
      <div className="flex flex-col px-7 items-start border-b-2 lg:px-0 lg:border-0">
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
          <p className="text-xs text-gray-400 lg:pr-6">
            *Dentro do período selecionado
          </p>
          <a className="flex items-center justify-center w-10 h-10 border-purple-medium">
            <img src={client_plus_icon} className="w-7 h-7" />
          </a>
        </div>
      </div>
    </section>
  );
}
