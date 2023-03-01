import twitterIcon from '../assets/twitterIcon.svg'
import whatsappIcon from '../assets/whatsappIcon.svg'
import facebookIcon from '../assets/facebookIcon.svg'
import githubIcon from '../assets/githubIcon.svg'
import styles from '../components/FooterNav.module.css'

export function FooterNav(){
    return(
       <footer className="font-quicksand font-medium flex flex-col px-7 bg-purple-dark text-purple-light">
        <h5 className="my-7 text-white">BOXES</h5>
        <nav className="flex flex-col my-2.5">
            <a href="#" className={styles.aFooter}> Sua Conta</a>
            <a href="#" className={styles.aFooter}> Contatos</a>
            <a href="#" className={styles.aFooter}> Planos e Preços</a>
            <a href="#" className={styles.aFooter}> Termo de Uso</a>
            <a href="#" className={styles.aFooter}> Declaração de Uso de Cookies</a>
            <a href="#" className={styles.aFooter}> Política de Privacidade e Segurança</a>
        </nav>
        <p className="text-white">SOCIAL</p>
        <div className="flex flex-row">
            <a href="#"><img src={twitterIcon} alt="logo-twitter" /></a>
            <a href="#"><img src={whatsappIcon} alt="logo-wpp" /></a>
            <a href="#"><img src={facebookIcon} alt="logo-facebook" /></a>
            <a href="#"><img src={githubIcon} alt="logo-github" /></a>
        </div>
        <address>
            <p className="text-white">FEIRA DE SANTANA, BA</p>
            <p>Av. Eduardo Froes da Mota, 500</p>
            <div className="flex">
                <p>Telefone:</p>
                <a className="text-white" href="tel:(+55)075991988661">75 9999-8888</a>
            </div>
        </address>
       </footer>
    )
}