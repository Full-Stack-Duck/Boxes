import { FormEvent, useEffect, useState } from 'react'
import logoIcon from '../assets/logo-icon.svg'
import styles from '../components/CadastrarUsuario.module.css'
import { useForm } from 'react-hook-form'
import { z } from 'zod'
import {zodResolver} from '@hookform/resolvers/zod'
import axios from 'axios'
import { Loading } from '../assets/aux_components/Loading'
import { api } from '../server/api'


const userSchema = z.object({
    nome: z.string()
    .nonempty('Por favor, preencha o campo de nome.'),

    documento: z.string()
    .nonempty('Por favor, preencha o campo com CPF ou CNPJ.')
    .min(11, 'Faltam dígitos em seu CPF ou CNPJ.')
    .max(14, 'Tem números demais em seu CNPJ'),

    email: z.string()
    .nonempty('Por favor, preencha o campo de e-mail.')
    .email('O e-mail não é válido.'),

    senha: z.string()
    .min(7, 'A senha deve conter no mínimo 7 caracteres.')
    .nonempty('Preencha o campo de senha'),

    senhaConfirmada: z.string()
    .nonempty('Preencha o campo de confirmação de senha'),

    termoDeUso: z.boolean() 
    .refine((value) => value === true, 'Para continuar, aceite os termos.'),

    tipoDePessoa: z.string(),

}).refine((field) => field.senha === field.senhaConfirmada , {
    path: ['senhaConfirmada'],
    message: 'Senha incorreta, confirme sua senha'
})

export type UserData = z.infer<typeof userSchema>

export function CadastrarUsuario(){

    const [output, setOutput ]= useState('')

    const [ radioOption, setRadioOption ] = useState(true)
    const [isSendingFeedback, setIsSendingFeedback] = useState<boolean>(false)


    function handlePersonTypeChange(){
        setRadioOption(!radioOption)
    }

    const { register,
            handleSubmit,
            formState : { errors }
            } = useForm<UserData>({
            resolver: zodResolver(userSchema)
        })

        const [nome, setNome] = useState('');
        const [documento, setDocumento] = useState('');
        const [email, setEmail] = useState('');
        const [senha, setSenha] = useState('');

        async function  criarUsuario() {
            try {
                setIsSendingFeedback(true)
                const response = await api.post('/usuarios', {
                    nome,
                    documento,
                    email,
                    senha,
                });
                setIsSendingFeedback(false)
                console.log(JSON.stringify(response))
            } catch (error) {
                console.log(error)
                setIsSendingFeedback(false)
            }
          };

    return (
        <div className="flex justify-center w-full">
            <div className="hidden bg-purple-medium lg:block lg:w-1/2 lg:rounded-r-3xl"></div>
            <div className="flex flex-col w-[23rem] sm:w-[50rem] h-fit justify-center items-center lg:px-32">
                <div>
                    <article className="flex flex-col justify-center items-center mb-3">
                        <div>
                            <img src={logoIcon} alt="logomarca"/>
                        </div>
                        <h1 className="font-semibold tracking-wider text-2xl">Seja bem-vindo</h1>
                        <h3 className="text-xs text-gray-lightgray">Por favor, insira seus dados</h3>
                    </article>
                </div>
                <form 
                className='flex flex-col gap-2 w-full px-5'
                onSubmit={handleSubmit(criarUsuario)}
                >
                    <div className='flex items-center gap-4 justify-between w-full flex-wrap sm:justify-center sm:gap-28'>
                        <div className='flex gap-2 items-center flex-wrap'>
                            <input 
                            type="radio" 
                            id="person1" 
                            className={styles.inputRadio} 
                            {...register('tipoDePessoa')} 
                            value='Pessoa Fisica'
                            onChange={() => handlePersonTypeChange()}
                            checked={radioOption}
                            />
                            <label 
                            htmlFor="person1" 
                            className='cursor-pointer text-purple-medium uppercase font-semibold'
                            >
                                Pessoa Física
                                </label>
                        </div>
                        <div className='flex gap-2 items-center flex-wrap'>
                            <input 
                            type="radio"  
                            id="person2" 
                            className={styles.inputRadio} 
                            {...register('tipoDePessoa')} 
                            value='Pessoa Juridica'
                            onChange={() => handlePersonTypeChange()}
                            />
                            <label 
                            htmlFor="person2" 
                            className='cursor-pointer text-purple-medium uppercase font-semibold'
                            >
                                Pessoa Jurídica
                                </label>
                        </div>
                    </div>

                    <div>
                        <label htmlFor="email" className={styles.labelsStyles}>E-mail:</label>
                        <input type="text" placeholder='E-mail' className={styles.inputStyles} {...register('email')} onChange={(e) => setEmail(e.target.value)}/>
                    </div>
                    <span className='text-xs text-red-600 h-3'>{errors.email && errors.email.message}</span>

                    <div>
                        <label htmlFor="nome" className={styles.labelsStyles}>Nome de usuário:</label>
                        <input 
                        type="text" 
                        placeholder='Nome' 
                        className={styles.inputStyles} 
                        {...register('nome')}
                        onChange={(e) => setNome(e.target.value)}
                        />
                    </div>
                    <span className='text-xs text-red-600 h-3'>{errors.nome && errors.nome.message}</span>

                    <div>
                        <label 
                        htmlFor="documento" 
                        className={styles.labelsStyles}>{radioOption? "CPF" : "CNPJ"}
                            <span className='text-xs'>
                            (apenas números)
                            :
                            </span>
                        </label> 
                        <input 
                        type="text" 
                        placeholder={radioOption? 'Ex.: 000.000.000-00' : 'Ex.: 00.000.000/0001-00'} 
                        className={styles.inputStyles} 
                        {...register('documento')}
                        onChange={(e) => setDocumento(e.target.value)}
                        />    
                    </div>
                    <span className='text-xs text-red-600 h-3'>{errors.documento && errors.documento.message}</span>
                        
                    <div>
                        <label htmlFor="senha" className={styles.labelsStyles}>Senha:</label>
                        <input type="password" placeholder='Senha' className={styles.inputStyles} {...register('senha')} onChange={(e) => setSenha(e.target.value)}/>
                    </div>
                    <span className='text-xs text-red-600 h-3'>{errors.senha && errors.senha.message}</span>

                    <div>
                        <label htmlFor="senhaConfirmada" className={styles.labelsStyles}>Confirme sua senha:</label>
                        <input type="password" placeholder='Confirmar Senha' className={styles.inputStyles} {...register('senhaConfirmada')}/>
                    </div>
                    <span className='text-xs text-red-600 h-3'>{errors.senhaConfirmada && errors.senhaConfirmada.message}</span>

                    <div className='flex gap-1 items-center justify-start'>
                        <input type="checkbox" id="aceitar-termos" {...register('termoDeUso')} className={styles.checkboxStyle}/>
                        <label htmlFor="aceitar-termos" className={styles.labelsStyles && "pl-3 font-nunito text-sm"}>Eu li e concordo com os <a href='https://www.youtube.com/' className='text-purple-medium underline underline-offset-1'>Termos de Uso</a></label>
                    </div>
                    <span className='text-xs text-red-600 whitespace-nowrap h-4'>{errors.termoDeUso && errors.termoDeUso.message}</span>

                <button 
                type="submit" 
                className='flex justify-center items-center disabled:bg-purple-medium/80 disabled:hover:bg-purple-900/70 bg-purple-medium border py-2 w-full rounded-xl hover:bg-purple-900 text-white font-bold mt-1'
                disabled={isSendingFeedback}
                >
                    { isSendingFeedback ? <Loading /> :'Avançar' }
                </button>
                </form>

                <span className='text-purple-stroke text-sm mt-9'>Já tem uma conta? <a href="_blank" className='font-semibold text-purple-medium text-base' >FAÇA O LOGIN</a></span>

                <a ></a>
                <pre className='bg-purple-dark text-white font-bold text-sm mt-8'>{output}</pre>
            </div>
            

        </div>
    )
}