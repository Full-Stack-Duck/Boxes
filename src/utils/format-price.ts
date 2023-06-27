export function formatPrice(value: number) {
  const formattedValue = value
  return formattedValue.toLocaleString('pt-BR', {
    style: 'currency',
    currency: 'BRL',
  })
}
