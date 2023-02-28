import twitterIcon from '../assets/twitterIcon.svg'
import whatsappIcon from '../assets/whatsappIcon.svg'
import facebookIcon from '../assets/facebookIcon.svg'
import githubIcon from '../assets/githubIcon.svg'

export function FooterNav(){
    return(
       <footer className="flex flex-col">
        <h5>BOXERS</h5>
        <nav className="flex flex-col">
            <a href=""> Sua Conta</a>
            <a href=""> Contatos</a>
            <a href=""> Planos e Preços</a>
            <a href=""> Termo de Uso</a>
            <a href=""> Declaração de Uso de Cookies</a>
            <a href=""> Política de Privacidade e Segurança</a>
        </nav>
        <p>SOCIAL</p>
        <div className="flex flex-row">
            <a href=""><img src={twitterIcon} alt="logo-twitter" /></a>
            <a href=""><img src={whatsappIcon} alt="logo-wpp" /></a>
            <a href=""><img src={facebookIcon} alt="logo-facebook" /></a>
            <a href=""><img src={githubIcon} alt="logo-github" /></a>
        </div>
        <address>
            <p>FEIRA DE SANTANA, BA</p>
            <p>Av. Eduardo Froes da Mota, 500</p>
            <div className="flex">
                <p>Telefone:</p>
                <a href="tel:(+55)075991988661">75 9999-8888</a>
            </div>
        </address>
       </footer>
    )
}