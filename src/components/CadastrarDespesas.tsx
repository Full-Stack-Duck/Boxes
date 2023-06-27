import { useForm } from 'react-hook-form';
import despesaIcon from '../assets/despesaIcon.svg'
import { z } from 'zod';
import { ChangeEvent, useContext, useState } from 'react';
import { UserContext } from '../contexts/UserContext';
import { api, token } from '../server/api';
import { toast } from 'react-toastify';
import { Loading } from '../assets/aux_components/Loading';
import { formateToDate } from '../utils/format-date';


// "id": 1,
// "nome": "Energia",
// "categoria": "FIXA",
// "valor": 250.0,
// "observacao": "Coelba",
// "dataDespesa": "2023-06-26T18:29:28Z",
// "status": "ATIVO",
// "usuario": {
//     "id": 1,
//     "nome": "Bruno",
//     "documento": "123456789",
//     "telefone": "7512345678",
//     "endereco": "Tomba",
//     "logo": "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.pngwing.com%2Fen%2Fsearch%3Fq%3Djava&psig=AOvVaw3tTPnzEE5A094sE5h23Ik0&ust=1685803990633000&source=images&cd=vfe&ved=0CBEQjRxqFwoTCPjGjbXrpP8CFQAAAAAdAAAAABAE",
//     "datacadastro": null,
//     "status": "ATIVO",
//     "email": "bruno@gmail.com",
//     "senha": "$2a$12$BodnS42Y3/ALFPXFGYUZEuQ7qqAO.ZmVuEEDvCikvA1VhKOX656sG",
//     "enabled": true,
//     "accountNonLocked": true,
//     "username": "bruno@gmail.com",
//     "password": "$2a$12$BodnS42Y3/ALFPXFGYUZEuQ7qqAO.ZmVuEEDvCikvA1VhKOX656sG",
//     "authorities": [
//         {
//             "authority": "ROLE_USER"
//         }
//     ],
//     "accountNonExpired": true,
//     "credentialsNonExpired": true
// }

const enumType = ['Escolha o tipo...', 'Fixa', 'Variável', 'Material', 'Outros'] as const


const despesaSchema = z.object({
    nome: z.string(),
    valor: z.number(),
    categoria: z.string(),
    observacao: z.number(),
    dataDespesa: z.string().default('2023-06-02T03:00:00.00Z')
})

type DespesaData = z.infer<typeof despesaSchema>

export function CadastrarDespesa(){
    const { register, handleSubmit } = useForm<DespesaData>()

    const [isSendingData, setIsSendingData] = useState<boolean>(false);


    const [nome, setNome] = useState<string>('')
    const [valor, setValor] = useState<number>(0)
    const [categoria, setCategoria] = useState<number>(0)
    const [observacao, setObservacao] = useState<string>('')
    const [dataDespesa, setDataDespesa] = useState<string>('')

    const { tokenJWT } = useContext(UserContext)

    async function insertNewExpense(){
        setIsSendingData(true);
        try {
          const response = await api.post("/despesas", {
            nome,
            valor, 
            categoria, 
            observacao,
            dataDespesa
          }, {headers: {Authorization: `Bearer ${tokenJWT}`}});
          setIsSendingData(false);
          console.log(JSON.stringify(response));
          toast.success('Produto cadastrado com sucesso!');
        } catch (error) {
          console.log(error);
          toast.error('Produto não cadastrado!');
          setIsSendingData(false);
        }
      }

      const handleChange = (event: ChangeEvent<HTMLSelectElement>) => {
        const selectedIndex = Number(event.target.value); 
        setCategoria(selectedIndex)
        console.log(`Opção selecionada no índice ${selectedIndex}`);
      };

    return(
        <section className='flex flex-col justify-center w-full lg:w-2/3  lg:ml-4'> 
            <form onSubmit={handleSubmit(insertNewExpense)} className="px-7 my-1 bg-white font-quicksand font-semibold border-y lg:m-0 lg:p-4 lg:border lg:rounded-xl lg:border-purple-light">
                <div className='w-full'>
                    <h3 className="text-lg pb-2 font-bold">Cadastrar Despesas</h3>
                </div>
                <div className="text-sm text-purple-medium lg:flex lg:flex-row">
                    <div className="lg:w-1/2 lg:pr-4">
                        <article className="mb-4 text-sm">
                            <label htmlFor="nome-despesa">NOME DA DESPESA</label>
                            <input 
                            type="text" 
                            className="w-full h-8 border rounded border-purple-light" 
                            placeholder='Nome da despesa'
                            id='nome-despesa'
                            {...register('nome')}
                            onChange={(e) => setNome(e.target.value)}
                            />
                        </article>
                        <article className="flex mb-4 text-sm justify-between gap-x-6 max-w-full">
                            <div className="flex flex-col w-1/2">
                                <label htmlFor="valor">VALOR</label>
                                <input 
                                className="border rounded h-8 placeholder:text-xs placeholder:p-2 placeholder:italic placeholder:text-gray-lightgray" 
                                type="number" 
                                id='valor'
                                placeholder="Ex: 3.25" 
                                {...register('valor')}
                                onChange={(e) => setValor(parseFloat(e.target.value.replace(',', '.')))}
                                />
                            </div>
                            <div className="flex flex-col w-1/2">
                                <label htmlFor="categoria">TIPO</label>
                                <select
                                    className="border rounded font-nunito h-8"
                                    {...register('categoria')}
                                    onChange={(e) => handleChange(e)}
                                    id='categoria'
                                    >
                                    {enumType.map((field, index) => {
                                        return (
                                            <option
                                        className="text-purple-light text-xs"
                                        key={index.toString()}
                                        value={index - 1}
                                        >
                                            {field}
                                        </option>
                                        );
                                    })}
                                </select>
                            </div>
                        </article>
                    </div>

                    <div className="lg:w-1/2">
                        <article className="mb-4 text-sm">
                            <label htmlFor="data">DATA DA DESPESA</label>
                            <input 
                            type="date" 
                            id="data" 
                            className="w-full h-8 border rounded border-purple-light" 
                            {...register('dataDespesa')}
                            onChange={(e) => setDataDespesa(formateToDate(e.target.value))}
                            />
                        </article>
                        <article className="flex flex-wrap items-center text-sm mb-4">
                            <label htmlFor="description">DESCRIÇÃO</label>
                            <p className="italic pl-5 text-xs text-purple-light"> (opcional) </p>
                            <textarea 
                            className="w-full h-20 resize-none over-flow-y-auto border rounded placeholder:text-xs placeholder:p-2 placeholder:italic placeholder:text-gray-lightgray lg:h-8" 
                            id="description" 
                            placeholder="Escreva uma breve descrição da despesa..." 
                            {...register('observacao')}    
                            onChange={(e) => setObservacao(e.target.value)}     
                            />
                        </article>
                    </div>
                </div>
                <div className="flex w-full justify-center">
                    <button 
                    className="flex items-center h-10 mt-2 mb-4 border-2 rounded bg-purple-medium border-purple-dark shadow-md shadow-purple-shadow text-white px-10 font-normal lg:mb-0 lg:px-14 lg:mt-1"
                    disabled={isSendingData}
                    type='submit'
                    onClick={insertNewExpense}
                    >
                        {" "}
                        {isSendingData ? <Loading /> : "CADASTRAR DESPESA"}
                        <img className="ml-2" src={despesaIcon} alt="Ícone de despesa" />
                    </button>
                </div>
            </form>
        </section>
    )
}