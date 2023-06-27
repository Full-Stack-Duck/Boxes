// import { z } from 'zod'
// import {zodResolver} from '@hookform/resolvers/zod'
// import logoIcon from '../assets/logoIcon.svg'
// import { useForm } from 'react-hook-form'
// import { useState } from 'react'
// import { Loading } from '../assets/aux_components/Loading'
// import { api, token } from '../server/api';
// import Cookies from 'js-cookie'
// import { useNavigate } from 'react-router-dom';
// import 'react-toastify/dist/ReactToastify.css';
// import { toast } from 'react-toastify';
// import { HeaderWeb } from './HeaderWeb'
// import { FooterNavWeb } from './FooterNavWeb'


// const usuarioSchema = z.object({
//     email: z.string(),
//     senha: z.string()
// })

// type UserData = z.infer<typeof usuarioSchema>

// export function Login(){
//     const navigate = useNavigate();
    
//     const [isLogging, setIsLogging] = useState<boolean>(false)
//     const [email, setEmail] = useState('');
//     const [senha, setSenha] = useState('');
    
//     const { 
//         register,
//         handleSubmit,
//         formState : { errors }
//         } = useForm<UserData>({
//         resolver: zodResolver(usuarioSchema)
//     })

//     async function entrar(){
//         try {
//             setIsLogging(true)
//         const response = await api.post('/login', {
//             email,
//             senha
//         });
//         // console.log(JSON.stringify(response))
//         const toJson = JSON.stringify(response)
//         Cookies.set('jwtToken', response.data.token)
//         const token = Cookies.get('jwtToken')
//         // console.log('do toJson', toJson.data.token)
//         // console.log(response.data.token)
//         console.log(token)
//         toast.success('Usuário Logado com sucesso!');
//         navigate('/dashboard');
//         setIsLogging(false)
//     } catch(e) {
//         console.log(e)
//         toast.error('Erro ao Logar usuário. Por favor, tente novamente.');
//         setIsLogging(false)
//         }
        
//     }

//     return(
//         <>
//         <HeaderWeb />
//         <section className="font-quicksand flex justify-center w-full">
//             <div className="hidden bg-purple-medium lg:block lg:w-1/2 lg:rounded-r-3xl"></div>

//             <div className="flex flex-col w-[23rem] sm:w-[50rem] h-fit justify-center items-center lg:px-32">
//                 <article className=" flex flex-col justify-center items-center">
//                         <img src={logoIcon} alt="logomarca" className="w-20 h-20 mb-6 mt-10" />
//                         <h1 className="font-semibold tracking-wider text-2xl">Seja bem-vindo</h1>
//                         <h3 className="text-xs text-gray-lightgray pb-12">Por favor, insira seus dados</h3>
//                 </article>
//                 <article className="flex-col mx-5">
//                     <form onSubmit={handleSubmit(entrar)}>
//                         <div className="py-1.5 font-medium">
//                             <label htmlFor="">Email</label>
//                             <input type="email"
//                             className="w-full h-8 border rounded border-purple-stroke placeholder:text-xs placeholder:px-3 placeholder:font-nunito" 
//                             placeholder="Escreva seu email"
//                             {...register('email')} 
//                             onChange={(e) => setEmail(e.target.value)}
//                             />
//                         </div>
                        
//                     <span className='text-xs text-red-600 h-3'>{errors.email && errors.email.message}</span>

//                         <div className="py-1.5 font-medium">
//                             <label htmlFor="">Senha</label>
//                             <input type="password" className="w-full h-8 border rounded border-purple-stroke"
//                             {...register('senha')} 
//                             onChange={(e) => setSenha(e.target.value)}
//                             />
//                             <span className='text-xs text-red-600 h-3'>{errors.senha && errors.senha.message}</span>
//                         </div>

//                         <div className="flex py-1.5 font-nunito text-xs">
//                             <div className="w-[50%]">
//                                 <input type="checkbox" className="mr-2 border rounded border-purple-stroke"/>
//                                 <label htmlFor="">Salvar senha</label>
//                             </div>
//                             <div className="w-[50%] flex justify-end">
//                                 <a href="#" className="text-sm text-purple-medium">
//                                     Esqueci minha senha
//                                 </a>
//                             </div>
//                         </div>
//                         <button
//                         type='submit'
//                         className="flex items-center justify-center w-full h-10 mt-2 mb-4 rounded-xl  bg-purple-medium shadow-md shadow-purple-shadow text-white tracking-widest"
//                         disabled={isLogging}
//                         >
//                     { isLogging ? <Loading /> :'Entrar' }
                            
//                         </button>
//                     </form>
//                     <div className="flex justify-center gap-2 py-4 items-center">
//                         <p className="text-xs text-gray-lightgray">Não tem uma conta?</p>
//                         <a href="/cadastro" className="text-sm text-purple-medium">CADASTRE-SE</a>
//                     </div>
//                 </article>
                
//             </div>
//         </section><FooterNavWeb /></>
//     )
// }

// 'use client'
// import { redirect } from 'next/navigation'

// import { z } from 'zod'
// import { zodResolver } from '@hookform/resolvers/zod'
// import logoIcon from '@/assets/logoIcon.svg'
// import { useForm } from 'react-hook-form'
// import { useContext, useState } from 'react'
// import { Loading } from '@/assets/aux_components/Loading'
// // import { api } from '../server/api'
// // import Cookies from 'js-cookie'
// import Image from 'next/image'
// import { UserContext } from '@/contexts/UserContext'
// import { api } from '@/server/api'
// import Cookies from 'js-cookie'

// const usuarioSchema = z.object({
//   email: z.string(),
//   senha: z.string(),
// })

// type UserData = z.infer<typeof usuarioSchema>
// // type JWT = {
// //   token: string
// // }
// export default function Login() {
//   const [isLogging, setIsLogging] = useState<boolean>(false)
//   // const [email, setEmail] = useState('')
//   // const [senha, setSenha] = useState('')

//   const {
//     register,
//     handleSubmit,
//     formState: { errors },
//   } = useForm<UserData>({
//     resolver: zodResolver(usuarioSchema),
//   })

//   //   async function entrar() {
//   //     try {
//   //       setIsLogging(true)
//   //       const response = await api.post('/login', {
//   //         email,
//   //         senha,
//   //       })
//   //       // console.log(JSON.stringify(response))
//   //       const toJson = JSON.stringify(response)
//   //       Cookies.set('jwtToken', response.data.token)
//   //       const token = Cookies.get('jwtToken')
//   //       // console.log('do toJson', toJson.data.token)
//   //       // console.log(response.data.token)
//   //       console.log(token)
//   //       setIsLogging(false)
//   //     } catch (e) {
//   //       console.log(e)
//   //       setIsLogging(false)
//   //     }
//   //   }
//   const { email, senha, setJwToken, setEmail, setSenha } =
//     useContext(UserContext)
//   async function login() {
//     try {
//       setIsLogging(true)
//       const response = await api.post('/login', {
//         email,
//         senha,
//       })
//       // console.log(JSON.stringify(response))
//       const toJson = JSON.stringify(response)
//       Cookies.set('jwtToken', response.data.token)
//       const token = Cookies.get('jwtToken')
//       console.log('do toJson', toJson)
//       setJwToken(toJson)
//       // console.log(response.data.token)
//       console.log(token)
//       // toast.success('Usuário Logado com sucesso!')
//       redirect('/application')
//       setIsLogging(false)
//     } catch (e) {
//       console.log(e)
//       // toast.error('Erro ao Logar usuário. Por favor, tente novamente.')
//       setIsLogging(false)
//     }
//   }
//   return (
//     <section className="font-quicksand flex justify-center w-full">
//       <div className="hidden bg-purple-medium lg:block lg:w-1/2 lg:rounded-r-3xl"></div>

//       <div className="flex flex-col w-[23rem] sm:w-[50rem] h-fit justify-center items-center lg:px-32">
//         <article className=" flex flex-col justify-center items-center">
//           <Image src={logoIcon} height={40} width={40} alt="Boxes logo" />
//           <h1 className="font-semibold tracking-wider text-2xl">
//             Seja bem-vindo
//           </h1>
//           <h3 className="text-xs text-gray-lightgray pb-12">
//             Por favor, insira seus dados
//           </h3>
//         </article>
//         <article className="flex-col mx-5">
//           <form onSubmit={handleSubmit(login)}>
//             <div className="py-1.5 font-medium">
//               <label htmlFor="">Email</label>
//               <input
//                 type="email"
//                 className="w-full h-8 border rounded border-purple-stroke placeholder:text-xs placeholder:px-3 placeholder:font-nunito"
//                 placeholder="Escreva seu email"
//                 {...register('email')}
//                 onChange={(e) => setEmail(e.target.value)}
//               />
//             </div>

//             <span className="text-xs text-red-600 h-3">
//               {errors.email && errors?.email?.message}
//             </span>

//             <div className="py-1.5 font-medium">
//               <label htmlFor="">Senha</label>
//               <input
//                 type="password"
//                 className="w-full h-8 border rounded border-purple-stroke"
//                 {...register('senha')}
//                 onChange={(e) => setSenha(e.target.value)}
//               />
//               <span className="text-xs text-red-600 h-3">
//                 {errors.senha && errors?.senha?.message}
//               </span>
//             </div>

//             <div className="flex py-1.5 font-nunito text-xs">
//               <div className="w-[50%]">
//                 <input
//                   type="checkbox"
//                   className="mr-2 border rounded border-purple-stroke"
//                 />
//                 <label htmlFor="">Salvar senha</label>
//               </div>
//               <div className="w-[50%] flex justify-end">
//                 <a href="#" className="text-sm text-purple-medium">
//                   Esqueci minha senha
//                 </a>
//               </div>
//             </div>
//             <button
//               type="submit"
//               className="flex items-center justify-center w-full h-10 mt-2 mb-4 rounded-xl  bg-purple-medium shadow-md shadow-purple-shadow text-white tracking-widest"
//               disabled={isLogging}
//             >
//               {isLogging ? <Loading /> : 'Entrar'}
//             </button>
//           </form>
//           <div className="flex justify-center gap-2 py-4 items-center">
//             <p className="text-xs text-gray-lightgray">Não tem uma conta?</p>
//             <a href="#" className="text-sm text-purple-medium">
//               CADASTRE-SE
//             </a>
//           </div>
//         </article>
//       </div>
//     </section>
//   )
// }
import { z } from 'zod'
import { zodResolver } from '@hookform/resolvers/zod'
import logoIcon from '../assets/logoIcon.svg'
import { useForm } from 'react-hook-form'
import { useContext, useEffect, useState } from 'react'
import { Loading } from '../assets/aux_components/Loading'
import { UserContext } from '../contexts/UserContext'
import { api } from '../server/api'
import Cookies from 'js-cookie'
import { toast } from 'react-toastify'
import { useNavigate } from 'react-router-dom';
import { AxiosResponse } from 'axios'

const usuarioSchema = z.object({
  email: z.string(),
  senha: z.string(),
})

interface LoginReturnData {
  tokenJWT: string
  id: number
}

type UserData = z.infer<typeof usuarioSchema>

export function Login() {
  const [isLogging, setIsLogging] = useState<boolean>(false)
//   const [email, setEmail] = useState('')
//   const [senha, setSenha] = useState('')
  const { email, senha, tokenJWT, setTokenJWT, setEmail, setSenha, id, setId} = useContext(UserContext)
  const navigate = useNavigate();

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<UserData>({
    resolver: zodResolver(usuarioSchema),
  })

  
  async function entrar() {
    try {
      setIsLogging(true)
      const response = await api.post('/login', {
        email,
        senha,
      })
      // console.log(JSON.stringify(response))
      // const toJson = JSON.stringify(response.data)
      // Cookies.set('jwtToken', response.data.token)
      // const token = Cookies.get('jwtToken')
      // console.log('do toJson', toJson)
      setTokenJWT(response.data.tokenJWT)
      setId(response.data.id)
      toast.success('Usuário Logado com sucesso!')
        navigate('/dashboard');
      setIsLogging(false)
    } catch (e) {
      console.log(e)
      toast.error('Erro ao Logar usuário. Por favor, tente novamente.')
      setIsLogging(false)
    }
  }
  
  return (
    <>
      <section className="font-quicksand flex justify-center w-full">
        <div className="hidden bg-purple-medium lg:block lg:w-1/2 lg:rounded-r-3xl"></div>

        <div className="flex flex-col w-[23rem] sm:w-[50rem] h-fit justify-center items-center lg:px-32">
          <article className=" flex flex-col justify-center items-center">
            <img
              src={logoIcon}
              alt="logomarca"
              className="w-20 h-20 mb-6 mt-10"
            />
            <h1 className="font-semibold tracking-wider text-2xl">
              Seja bem-vindo
            </h1>
            <h3 className="text-xs text-gray-lightgray pb-12">
              Por favor, insira seus dados
            </h3>
          </article>
          <article className="flex-col mx-5">
            <form onSubmit={handleSubmit(entrar)}>
              <div className="py-1.5 font-medium">
                <label htmlFor="">Email</label>
                <input
                  type="email"
                  className="w-full h-8 border rounded border-purple-stroke placeholder:text-xs placeholder:px-3 placeholder:font-nunito"
                  placeholder="Escreva seu email"
                  {...register('email')}
                  onChange={(e) => setEmail(e.target.value)}
                />
              </div>

              <span className="text-xs text-red-600 h-3">
                {errors.email && errors.email.message}
              </span>

              <div className="py-1.5 font-medium">
                <label htmlFor="">Senha</label>
                <input
                  type="password"
                  className="w-full h-8 border rounded border-purple-stroke"
                  {...register('senha')}
                  onChange={(e) => setSenha(e.target.value)}
                />
                <span className="text-xs text-red-600 h-3">
                  {errors.senha && errors.senha.message}
                </span>
              </div>

              <div className="flex py-1.5 font-nunito text-xs">
                <div className="w-[50%]">
                  <input
                    type="checkbox"
                    className="mr-2 border rounded border-purple-stroke"
                  />
                  <label htmlFor="">Salvar senha</label>
                </div>
                <div className="w-[50%] flex justify-end">
                  <a href="#" className="text-sm text-purple-medium">
                    Esqueci minha senha
                  </a>
                </div>
              </div>
              <button
                type="submit"
                className="flex items-center justify-center w-full h-10 mt-2 mb-4 rounded-xl  bg-purple-medium shadow-md shadow-purple-shadow text-white tracking-widest"
                disabled={isLogging}
              >
                {isLogging ? <Loading /> : 'Entrar'}
              </button>
            </form>
            <div className="flex justify-center gap-2 py-4 items-center">
              <p className="text-xs text-gray-lightgray">Não tem uma conta?</p>
              <a href="/cadastro" className="text-sm text-purple-medium">
                CADASTRE-SE
              </a>
            </div>
          </article>
        </div>
      </section>
    </>
  )
}
