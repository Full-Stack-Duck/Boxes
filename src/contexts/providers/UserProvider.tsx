import { ReactNode, useState, useEffect } from 'react';
import { UserContext } from '../UserContext'
import { QueryClient, QueryClientProvider } from '@tanstack/react-query'

interface ProviderProps {
  children: ReactNode
}

const queryClient = new QueryClient()


export function UserProvider({ children }: ProviderProps) {
  const [email, setEmail] = useState<string>('')
  const [senha, setSenha] = useState<string>('')
  const [tokenJWT, setTokenJWT] = useState<string>('')
  const [id, setId] = useState<number>(0)

  useEffect(() => {
console.log(tokenJWT)
console.log(id)
  }, [tokenJWT, id])

  return (
    <UserContext.Provider
      value={{id, email, senha, tokenJWT, setEmail, setSenha, setTokenJWT, setId }}
    >
      <QueryClientProvider client={queryClient}>
      {children}
      </QueryClientProvider>
    </UserContext.Provider>
  )
}
