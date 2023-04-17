import { useState } from 'react'
import logoIcon from '../assets/box_logo_icon.svg'
import styles from '../components/CadastrarUsuario.module.css'
import { useForm } from 'react-hook-form'
import { z } from 'zod'
import {zodResolver} from '@hookform/resolvers/zod'


const userSchema = z.object({
    nome: z.string()
    .nonempty('Por favor, preencha o campo de nome.'),

    sobrenome: z.string()
    .nonempty('Por favor, preencha o campo de sobrenome.'),

    cpf: z.string()
    .nonempty('Por favor, preencha o campo de CPF.')
    .min(11, 'Faltam dígitos em seu CPF.'),

    cnpj: z.string()
    .nonempty('Por favor, preencha o campo de cnpj.')
    .min(11, 'Faltam dígitos em seu CNPJ.'),

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

type UserData = z.infer<typeof userSchema>

export function CadastrarUsuario(){

    const [output, setOutput ]= useState('')

    const [ radioOption, setRadioOption ] = useState(true)

    function handlePersonTypeChange( isCPF : boolean ){
        setRadioOption(isCPF)
    }

    const { register,
            handleSubmit,
            formState : { errors }
            } = useForm<UserData>({
            resolver: zodResolver(userSchema)
        })

    function criarUsuario(data: UserData){
        setOutput(JSON.stringify(data, null, 2))
    }

    return (
        <div className="flex flex-col mx-5 w-full h-[800px] justify-center items-center">
            <div>
                <article className="flex flex-col justify-center items-center mb-3">
                    <div>
                    <img src={logoIcon} alt="logomarca" className='w-20 h-20'/>
                    </div>
                    <h1 className="font-semibold tracking-wider text-2xl">Seja bem-vindo</h1>
                    <h3 className="text-xs text-gray-lightgray">Por favor, insira seus dados</h3>
                </article>
            </div>
            <form 
            className='flex flex-col gap-2'
            onSubmit={handleSubmit(criarUsuario)}
            >
                <div className='flex gap-3 items-center'>
                    <div className='flex gap-1 items-center'>
                        <input 
                        type="radio" 
                        id="person1" 
                        className={styles.inputRadio} 
                        {...register('tipoDePessoa')} 
                        value='Pessoa Fisica'
                        onClick={() => handlePersonTypeChange(true)}
                        checked={radioOption}
                        />
                        <label 
                        htmlFor="person1" 
                        className='cursor-pointer'
                        >
                            Pessoa Física
                            </label>
                    </div>
                    <div className='flex gap-1 items-center'>
                        <input 
                        type="radio"  
                        id="person2" 
                        className={styles.inputRadio} 
                        {...register('tipoDePessoa')} 
                        value='Pessoa Juridica'
                        onClick={() => handlePersonTypeChange(false)}
                        />
                        <label 
                        htmlFor="person2" 
                        className='cursor-pointer'
                        >
                            Pessoa Jurídica
                            </label>
                    </div>
                </div>

                <div>
                    <label htmlFor="nome">Nome:</label>
                    <input 
                    type="text" 
                    placeholder='Nome' 
                    className={styles.inputStyles} 
                    {...register('nome')}
                    />
                        {errors.nome && <span className='text-xs text-red-600'>{errors.nome.message}</span>}
                </div>

                <div>
                    <label htmlFor="sobrenome">Sobrenome:</label>
                    <input 
                    type="text" 
                    placeholder='Sobrenome' 
                    className={styles.inputStyles} 
                    {...register('sobrenome')}
                    />
                    {errors.sobrenome && <span className='text-xs text-red-600'>{errors.sobrenome.message}</span>}
                </div>
                <div>
                    {
                        radioOption?  <label htmlFor="cpf">CPF:</label> : <label htmlFor="cnpj">CNPJ:</label>
                    }
                    {radioOption && 
                    <input 
                    type="text" 
                    placeholder='CPF' 
                    className={styles.inputStyles} 
                    {...register('cpf')}
                    />}

                    {errors.cpf && radioOption &&
                    <span 
                    className='text-xs text-red-600'
                    >
                        {errors.cpf.message}
                        </span>}

                    {!radioOption && 
                    <input 
                    type="text" 
                    placeholder='CNPJ' 
                    className={styles.inputStyles} 
                    {...register('cnpj')}
                    />
                    }
                    {errors.cnpj && !radioOption && 
                    <span 
                    className='text-xs text-red-600'
                    >
                        {errors.cnpj.message}
                    </span>}
                </div>
                    
                <div>
                <label htmlFor="email">E-mail:</label>
                <input type="text" placeholder='E-mail' className={styles.inputStyles} {...register('email')}/>
                    {errors.email && <span className='text-xs text-red-600'>{errors.email.message}</span>}
                </div>

                <div>
                <label htmlFor="senha">Senha:</label>
                <input type="password" placeholder='Senha' className={styles.inputStyles} {...register('senha')}/>
                    {errors.senha && <span className='text-xs text-red-600'>{errors.senha.message}</span>}
                </div>

                <div>
                <label htmlFor="senhaConfirmada">Confirme sua senha:</label>
                <input type="password" placeholder='Confirmar Senha' className={styles.inputStyles} {...register('senhaConfirmada')}/>
                    {errors.senhaConfirmada && <span className='text-xs text-red-600'>{errors.senhaConfirmada.message}</span>}
                </div>

                <div className='flex gap-1 items-center'>
                <input type="checkbox" id="aceitar-termos" {...register('termoDeUso')}/>
                <label htmlFor="aceitar-termos">Eu li e concordo com os <a href='https://www.youtube.com/' className='text-purple-medium underline underline-offset-1'>termos de uso</a></label>
                </div>
                    {errors.termoDeUso && <span className='text-xs text-red-600'>{errors.termoDeUso.message}</span>}

            <button type="submit" className='bg-purple-medium border border-purple-dark py-2 w-full rounded hover:bg-purple-dark text-white font-bold'>Cadastrar</button>
            </form>

            <pre className='bg-purple-dark text-white font-bold text-sm mt-8'>{output}</pre>

        </div>
    )
}