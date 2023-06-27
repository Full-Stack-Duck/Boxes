import { useContext } from 'react'
import { api } from '../server/api'
import { formattedDate } from '../utils/format-date'
import { useQuery } from '@tanstack/react-query'
import { subDays } from 'date-fns'
import { UserContext } from '../contexts/UserContext'

export default function useQuantPedidosData(subDaysForDate: number) {
  const hoje = new Date()
  const data = subDays(hoje, subDaysForDate)
  const { id, tokenJWT } = useContext(UserContext)

  const fetchData = async () => {
    const response = await api.get(
      `/pedidos/${id}/npedidostotal?dataInicio=${formattedDate(
        data,
      )}T00:00:00Z&dataFim=${formattedDate(hoje)}T23:59:59Z`,{
        headers: {
          "Accept":"application/json",
          Authorization: `Bearer ${tokenJWT}`
        }
      }
    )
    return response.data
  }

  const query = useQuery({
    queryFn: fetchData,
    queryKey: ['quant-pedidos-data'],
  })
  return query
}
