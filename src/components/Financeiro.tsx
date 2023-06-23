import polygon_despesaG_icon from '../assets/polygon_despesa_G.svg'
import polygon_despesaR_icon from '../assets/polygon_despesa_R.svg'

export function Financeiro() {
    return (
        <section className='flex justify-center w-full lg:w-1/3'>
            <div className='flex flex-col w-[23rem] sm:w-[50rem] border-b-2'>
                <div className="flex justify-between py-4 lg:hidden">
                    <p className="font-quicksand font-bold pl-7 text-2xl">Financeiro</p>
                </div>

            <div className='px-7 lg:p-4 lg:border lg:rounded-xl lg:border-purple-light'>
                <p className='font-quicksand font-bold text-lg pb-2'>Vendas & Gastos</p>
                <div className='pb-5'>
                    <p className='font-quicksand text-lg font-normal'>Vendas</p>
                    <div className='flex items-center'>
                        <p className='font-bold text-3xl text-purple-medium'>R$ 5.765,60</p>
                        <div className='flex items-center bg-green-300 rounded-xl h-5 w-14 ml-3 justify-center'>
                            <div className="rotate-180 -mt-2">
                                <img src={polygon_despesaG_icon} className="animate-bounce transition-all"/>
                            </div>
                            <p className='font-bold text-xs text-green-800'>5.2%</p>
                        </div>
                    </div>
                    <p className=' font-quicksand text-xs'>Comparado com o período anterior</p>
                </div>

                <div className=''>
                    <p className='font-quicksand text-lg font-normal'>Gastos</p>
                    <div className='flex items-center'>
                        <p className='font-bold text-3xl text-purple-medium'>R$2.218,25</p>
                        <div className='flex items-center bg-red-400 rounded-xl h-5 w-14 ml-3 justify-center'>
                            <img src={polygon_despesaR_icon} className='animate-bounce transition-opacity mt-2'></img>
                            <p className='font-bold text-xs text-red-900'>3.7%</p>
                        </div>
                    </div>
                    <p className=' font-quicksand text-xs'>Comparado com o período anterior</p>
                </div>
            </div>
            </div>
        </section>
    )
}