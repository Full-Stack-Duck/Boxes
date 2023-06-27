import { createContext } from 'react'

export const PeriodContext = createContext({
  periodo: 30,
  setPeriodo: (value: number) => {},
})
