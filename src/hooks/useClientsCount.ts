import { useContext } from 'react'
import { api } from '../server/api'
import { useQuery } from '@tanstack/react-query'
import { UserContext } from '../contexts/UserContext'

export default function useClientsCount() {
  const { id, tokenJWT } = useContext(UserContext)
  const fetchData = async () => {
    const response = await api.get(`/clientes/${id}/totalclientes`, {
      headers: {
        "Accept":"application/json",
        Authorization: `Bearer ${tokenJWT}`
      }
    })
    console.log(response.data)
    return response.data
  }

  const query = useQuery({
    queryFn: fetchData,
    queryKey: ['contagem-clientes'],
  })
  return query
}
