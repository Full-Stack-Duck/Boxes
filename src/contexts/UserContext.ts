import { createContext } from 'react'

export const UserContext = createContext({
  id: 1,
  email: '',
  senha: '',
  tokenJWT: '',
  setEmail: (value: string) => {},
  setSenha: (value: string) => {},
  setTokenJWT: (value: string) => {},
  setId: (value: number) => {},
})
