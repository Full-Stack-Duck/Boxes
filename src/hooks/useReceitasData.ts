// "id": 6,
// "nome": "Internet",
// "categoria": "FIXA",
// "valor": 250.0,
// "observacao": "Java.Network",
// "dataDespesa": "2023-06-22T17:43:45Z",
// "status": "ATIVO",
// "usuario": {
//     "id": 2,
//     "nome": "José",
//     "documento": "123456789",
//     "telefone": "7512345678",
//     "endereco": "Tomba",
//     "logo": null,
//     "datacadastro": null,
//     "status": "ATIVO",
//     "email": "jose@gmail.com",
//     "senha": "$2a$12$xzA6d8BHkEWEbeOfWY6IHuluK4dWShZkYbfVAmS3/gACPJwJVCnmC",
//     "enabled": true,
//     "authorities": [
//         {
//             "authority": "ROLE_USER"
//         }
//     ],
//     "password": "$2a$12$xzA6d8BHkEWEbeOfWY6IHuluK4dWShZkYbfVAmS3/gACPJwJVCnmC",
//     "username": "jose@gmail.com",
//     "accountNonExpired": true,
//     "credentialsNonExpired": true,
//     "accountNonLocked": true
// }


import { useContext } from 'react'
import { api } from '../server/api'
import { formattedDate } from '../utils/format-date'
import { useQuery } from '@tanstack/react-query'
import { subDays } from 'date-fns'
import { UserContext } from '../contexts/UserContext'

export default function useReceitasData(subDaysForDate: number) {
  const hoje = new Date()
  const data = subDays(hoje, subDaysForDate)
  const { id, tokenJWT } = useContext(UserContext)

  const fetchData = async () => {
    const response = await api.get(
      `/receitas/${id}/totalreceitaspd?dataInicio=${formattedDate(
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
    queryKey: ['receitas-data'],
  })
  return query
}
