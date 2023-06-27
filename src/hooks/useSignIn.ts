import { UserContext } from '../contexts/UserContext'
import { useContext } from 'react'
import { useNavigate } from 'react-router-dom'
import { api } from '../server/api'

type JWT = {
  token: string
}

const API_URL = 'https://localhost:8080/login'

export default async function SignIn() {
  const { email, senha, setTokenJWT, setId } = useContext(UserContext)
  try {
    const response = await api.post(API_URL, {
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ email, senha }),
    })
    const userID = await api.get(API_URL)
    if (response.status) {
      // Parse the JWT from the response body
      const jwt: JWT = await response.data.json()
      // Save the JWT in the Authorization header for future requests
      // localStorage.setItem('jwt', jwt.token)
      console.log(userID.data)
      setId(userID.data)
      setTokenJWT(jwt.token)
      return jwt
    } else {
      throw new Error(
        `Failed to log in: ${response.status} ${response.statusText}`,
      )
    }
  } catch (error) {
    throw new Error(`Failed to log in: ${error}`)
  }
}
