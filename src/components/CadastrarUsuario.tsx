import logoIcon from '../assets/box_logo_icon.svg'
import styles from '../components/CadastrarUsuario.module.css'

export function CadastrarUsuario(){

    return (
        <div className="flex flex-col mx-5 w-full h-[800px] justify-center items-center">
            <div>
                <img src={logoIcon} alt="" />
            <h1>
                Seja bem-vindo!
            </h1>
            <span>Insira seus dados</span>
                
            </div>
            <form action="submit" className='flex flex-col gap-2 mt-3'>
                <div className='flex gap-3 items-center'>
                    <div className='flex gap-1 items-center'>
                        <input type="radio" name="pessoa" id="person1" value="pessoa-fisica" checked className={styles.inputRadio}/>
                        <label htmlFor="person1" className='cursor-pointer'>Pessoa Física</label>
                    </div>
                    <div className='flex gap-1 items-center'>
                        <input type="radio" name="pessoa" id="person2" value="pessoa-juridica" className={styles.inputRadio}/>
                        <label htmlFor="person2" className='cursor-pointer'>Pessoa Jurídica</label>
                    </div>
                </div>
                <input type="text" placeholder='Nome' className={styles.inputStyles}/>
                <input type="text" placeholder='Nome de usuário' className={styles.inputStyles}/>
                <input type="text" placeholder='CPF' className={styles.inputStyles}/>
                <input type="text" placeholder='E-mail' className={styles.inputStyles}/>
                <input type="password" placeholder='Senha' className={styles.inputStyles}/>
                <input type="password" placeholder='Confirmar Senha' className={styles.inputStyles}/>
                <div className='flex gap-1 items-center'>
                <input type="checkbox" name="termos-de-uso" id="aceitar-termos" />
                <label htmlFor="aceitar-termos">Eu li e concordo com os <a href='https://www.youtube.com/' className='text-purple-medium underline underline-offset-1'>termos de uso</a></label>
                </div>
            </form>

        </div>
    )
}