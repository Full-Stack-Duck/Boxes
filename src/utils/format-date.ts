import { format, parseISO } from 'date-fns'

export const formattedDate = (dateToUse: Date) => {
  return format(dateToUse, 'yyyy-MM-dd')
}

export const formateToDate = (dateToUse: string): string => {
  const date = parseISO(dateToUse);
  console.log(date)
  return date.toString()
}

