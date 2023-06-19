import { useForm, useFieldArray } from "react-hook-form";
import { z } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";

const techsSchema = z.object({
  titulo: z.string(),
  conhecimento: z.coerce.number().min(0).max(5),
});

const userSchema = z.object({
  nome: z.string().nonempty("Por favor, preencha o campo de nome."),

  email: z
    .string()
    .nonempty("Por favor, preencha o campo de e-mail.")
    .email("O e-mail não é válido."),

  senha: z
    .string()
    .min(7, "A senha deve conter no mínimo 7 caracteres.")
    .nonempty("Preencha o campo de senha"),

  telefone: z.string().nonempty("Preencha o campo de telefone."),

  empresa: z.string().nonempty("Preencha o campo com o nome da empresa."),

  cargo: z.string().nonempty("Preencha o campo de cargo."),

  expectativas: z.string(),

  techs: z.array(techsSchema),
});

export type UserData = z.infer<typeof userSchema>;
export type TechsData = z.infer<typeof techsSchema>;

const prefs: TechsData[] = [
  {
    titulo: "Java",
    conhecimento: 0,
  },
  {
    titulo: "JavaScript",
    conhecimento: 0,
  },
  {
    titulo: "Python",
    conhecimento: 0,
  },
  {
    titulo: "Ruby",
    conhecimento: 0,
  },
  {
    titulo: "C#",
    conhecimento: 0,
  },
  {
    titulo: ".NET",
    conhecimento: 0,
  },
  {
    titulo: "C",
    conhecimento: 0,
  },
  {
    titulo: "Swift",
    conhecimento: 0,
  },
  {
    titulo: "Kotlin",
    conhecimento: 0,
  },
];

export default function Cadastro() {
  const {
    control,
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<UserData>({
    resolver: zodResolver(userSchema),
  });

  const { fields, append, remove } = useFieldArray<UserData>({
    control,
    name: "techs",
  });

  function onHandleSubmit(objData: object) {
    console.log(JSON.stringify(objData));
  }

  return (
    <section className="flex justify-center w-full bg-transparent p-2 min-h-[920px] my-10">
      <div className="flex flex-col justify-center w-60 gap-2.5">
        <form
          action="submit"
          className="flex flex-col items-center justify-center gap-11"
        >
          <div className="flex flex-col justify-center gap-2.5">
            <span className="font-bold text-sm text-roxo-azulado">
              PREENCHA SEUS DADOS:
            </span>
            <input
              type="text"
              placeholder="Nome"
              className="w-96 h-10 px-5 py-1 rounded-full border border-roxo-inicial text-zinc-50 bg-purple-100/10"
              {...register("nome")}
            />
            <span className="text-xs text-red-600 h-3">
              {errors.nome && errors.nome.message}
            </span>

            <input
              type="text"
              placeholder="Email"
              className="w-96 h-10 px-5 py-1 rounded-full border border-roxo-inicial text-zinc-50 bg-purple-100/10"
              {...register("email")}
            />
            <span className="text-xs text-red-600 h-3">
              {errors.email && errors.email.message}
            </span>
            <input
              type="text"
              placeholder="Senha"
              className="w-96 h-10 px-5 py-1 rounded-full border border-roxo-inicial text-zinc-50 bg-purple-100/10"
              {...register("senha")}
            />

            <span className="text-xs text-red-600 h-3">
              {errors.senha && errors.senha.message}
            </span>
            <input
              type="text"
              placeholder="Telefone"
              className="w-96 h-10 px-5 py-1 rounded-full border border-roxo-inicial text-zinc-50 bg-purple-100/10"
              {...register("telefone")}
            />
            <span className="text-xs text-red-600 h-3">
              {errors.telefone && errors.telefone.message}
            </span>
            <input
              type="text"
              placeholder="Nome da empresa"
              className="w-96 h-10 px-5 py-1 rounded-full border border-roxo-inicial text-zinc-50 bg-purple-100/10"
              {...register("empresa")}
            />
            <span className="text-xs text-red-600 h-3">
              {errors.empresa && errors.empresa.message}
            </span>
            <input
              type="text"
              placeholder="Cargo"
              className="w-96 h-10 px-5 py-1 rounded-full border border-roxo-inicial text-zinc-50 bg-purple-100/10"
              {...register("cargo")}
            />
            <span className="text-xs text-red-600 h-3">
              {errors.cargo && errors.cargo.message}
            </span>
            <textarea
              placeholder="Descriçao breve sobre espectativas..."
              className="text-center w-96 h-24 px-5 py-1 rounded-full border border-roxo-inicial text-zinc-50 bg-purple-100/10"
              {...register("expectativas")}
            />
            <span className="text-xs text-red-600 h-3">
              {errors.expectativas && errors.expectativas.message}
            </span>
          </div>
          <div className="flex flex-col items-center justify-center gap-2.5">
            <span className="font-bold text-sm text-roxo-azulado">
              PREFERENCIAS:
            </span>
            <div className="flex justify-evenly gap-3 flex-wrap whitespace-break-spaces">
              {prefs.map((tech, index) => {
                return (
                  <div key={index} className="flex flex-nowrap items-center">
                    <div className="flex justify-between items-center gap-3 bg-roxo-escuro text-zinc-50 rounded-full px-3 py-1">
                      <span className="text-base font-semibold">
                        {tech.titulo}
                      </span>
                      {/* <input
                        type="number"
                        name={`${tech.titulo}`}
                        defaultValue={`${tech.conhecimento}`}
                        id={`${index}`}
                        className="flex justify-center whitespace-nowrap w-12 bg-transparent border border-white rounded-full px-3 text-white"
                      /> */}
                      <div
                        onClick={() => append(tech)}
                        className="flex items-center justify-center h-5 w-5 pb-1 rounded-full border border-white cursor-pointer"
                      >
                        <span className="text-lg font-semibold">+</span>
                      </div>
                    </div>
                    <span className="text-xs text-red-600 h-3">
                      {errors.expectativas && errors.expectativas.message}
                    </span>
                  </div>
                );
              })}
            </div>
            <div className="flex flex-wrap justify-evenly items-stretch gap-3">
              <span className="w-full h-0.5 bg-roxo-inicial/70 rounded-full"></span>
              <p className="font-bold text-sm text-roxo-azulado" >Preferencias selecionadas:</p>
              {fields.map((fields, key) => {
                return (
                  <div
                    key={fields.id}
                    className="flex justify-between items-center gap-3 bg-roxo-escuro border border-purple-400/60 text-zinc-50 rounded-full px-3 py-1"
                  >
                    <span
                      {...register(`techs.${key}.titulo`)}
                      className="text-base font-semibold"
                    >
                      {fields.titulo}
                    </span>
                    {/* <span {...register(`techs.${key}.conhecimento`)}>
                      {fields.conhecimento}
                    </span> */}
                    <div
                      onClick={() => remove(key)}
                      className="flex items-center justify-center h-5 w-5 pb-1 rounded-full border border-white cursor-pointer"
                    >
                      <span className="text-lg font-semibold">-</span>
                    </div>
                  </div>
                );
              })}
            </div>
          </div>
          <button
            onSubmit={handleSubmit(onHandleSubmit)}
            className="w-48 h-12 rounded-full bg-roxo-brand font-semibold text-zinc-50 hover:bg-roxo-inicial transition-colors duration-300"
          >
            CADASTRAR
          </button>
        </form>
      </div>
    </section>
  );
}
