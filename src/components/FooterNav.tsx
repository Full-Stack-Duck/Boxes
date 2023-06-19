import twitterIcon from '../assets/twitterIcon.svg'
import whatsappIcon from '../assets/whatsappIcon.svg'
import facebookIcon from '../assets/facebookIcon.svg'
import githubIcon from '../assets/githubIcon.svg'
import styles from '../components/FooterNav.module.css'

export function FooterNav(){
    return(
       <footer className="font-quicksand font-medium flex flex-col px-7 bg-purple-dark text-purple-light lg:px-32 lg:flex-row lg:pb-4">
        <div className="xl:w-1/3 lg:w-1/4">
            <h5 className="my-7 text-white lg:mt-0 lg:pt-4 ">BOXES</h5>
            <nav className="flex flex-col justify-center">
                <a href="#" className={styles.aFooter}><span className="material-symbols-outlined">chevron_right</span>Sua conta</a>
                <a href="#" className={styles.aFooter}><span className="material-symbols-outlined">chevron_right</span>Contatos</a>
                <a href="#" className={styles.aFooter}><span className="material-symbols-outlined">chevron_right</span>Planos e Preços</a>
            </nav>
        </div>

        <div className="xl:w-1/3 lg:w-2/4 lg:px-10 lg:pt-16 lg:mt-1 xl:mx-16 xl:px-0">
            <nav className="flex flex-col justify-center">
                <a href="#" className={styles.aFooter}><span className="material-symbols-outlined">chevron_right</span>Termo de Uso</a>
                <a href="#" className={styles.aFooter}><span className="material-symbols-outlined">chevron_right</span>Declaração de Uso de Cookies</a>
                <a href="#" className={styles.aFooter}><span className="material-symbols-outlined">chevron_right</span>Política de Privacidade e Segurança</a>
            </nav>
        </div>

        <div className="xl:w-1/3 lg:w-1/4 lg:pt-4">
            <p className="text-white mb-2.5">SOCIAL</p>
            <div className="flex gap-4 mb-9 items-center">
                <a href="#"><img src={twitterIcon} alt="logo-twitter" /></a>
                <a href="#"><img src={whatsappIcon} alt="logo-wpp" /></a>
                <a href="#"><img src={facebookIcon} alt="logo-facebook" /></a>
                <a href="#"><img src={githubIcon} alt="logo-github" /></a>
            </div>
            <address className="not-italic">
                <p className="text-white">FEIRA DE SANTANA, BA</p>
                <p className="mt-4">Av. Eduardo Froes da Mota, 500</p>
                <div className="flex mb-9">
                    <p>Telefone:</p>
                    <a className="text-white pl-2" href="tel:(+55)07599998888">(75) 9999-8888</a>
                </div>
            </address>
        </div>
       </footer>
    )
}