import clienteIcon from "../assets/costumer-icon-on.svg";
import { z } from "zod";
import { useForm } from "react-hook-form";
import { useState } from "react";
import { api, token } from "../server/api";
import { Loading } from "../assets/aux_components/Loading";
import { zodResolver } from "@hookform/resolvers/zod";
import Cookies from "js-cookie";
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';


const clientSchema = z.object({
  nome: z.string(),
  email: z.string(),
  telefone: z.string(),
});

type ClienteData = z.infer<typeof clientSchema>;

export function CadastrarClientes() {
  const { register, handleSubmit } = useForm<ClienteData>({
    resolver: zodResolver(clientSchema),
  });

  const [isSendingFeedback, setIsSendingFeedback] = useState<boolean>(false);
  const [nome, setNome] = useState<string>("");
  const [email, setEmail] = useState<string>("");
  const [telefone, setTelefone] = useState<string>("");

  async function insertNewClient(){
    setIsSendingFeedback(true);
    try {
      await api.post("/clientes", {
        nome,
        email,
        telefone,
      }, {headers: {Authorization: `Bearer ${token()}`}});
      setIsSendingFeedback(false)
      toast.success('Cliente cadastrado com sucesso!');
      //console.log(JSON.stringify(response)) deve ficar comentada pois aqui possui dados sensíveis
  } catch (error) {
      console.log(error)
      toast.error('Cliente não cadastrado , verifique os dados e tente novamente!');
      setIsSendingFeedback(false)
  }
  }

  return (
    <section className="w-full flex flex-col items-center">
      <p className="font-quicksand font-bold text-2xl py-3 px-7">Clientes</p>
      <div className="flex-col items-center px-7 my-1 bg-white font-quicksand font-semibold border-y w-[23rem] sm:w-[50rem] ">
        <div>
          <h3 className="text-lg py-2">Cadastrar Cliente</h3>
        <form
        onSubmit={handleSubmit(insertNewClient)}
        className="text-sm text-purple-medium"
        >
            <div className="mb-4">
              <label htmlFor="">NOME COMPLETO</label>
              <input
                type="text"
                className="w-full h-8 border rounded border-purple-light"
                {...register("nome")}
                onChange={(e) => setNome(e.target.value)}
              />
            </div>

            <div className="mb-4">
              <label htmlFor="email">E-MAIL</label>
              <input
                type="email"
                placeholder="ex: cliente123@email.com"
                className="w-full h-8 border rounded border-purple-light placeholder:p-2 placeholder:italic placeholder:text-gray-lightgray"
                {...register("email")}
                onChange={(e) => setEmail(e.target.value)}
              />
            </div>

            <div className="mb-4">
              <label htmlFor="telefone">TELEFONE</label>
              <input
                type="tel"
                placeholder="ex: (DDD) 0 0000-0000"
                className="w-full h-8 border rounded border-purple-light placeholder:p-2 placeholder:italic placeholder:text-gray-lightgray"
                {...register("telefone")}
                onChange={(e) => setTelefone(e.target.value)}
              />
            </div>
            <button
              type="submit"

              className=" flex items-center justify-evenly w-full h-10 mt-2 mb-4 border-2 rounded  bg-purple-medium border-purple-dark shadow-md shadow-purple-shadow text-white text-base"

              disabled={isSendingFeedback}
            >
              <span>{""}</span>
              {isSendingFeedback ? <Loading /> : "CADASTRAR CLIENTE"}
              <img className="mx-1" src={clienteIcon} alt="" />
            </button>
          </form>
        </div>
      </div>
    </section>
  );
}
