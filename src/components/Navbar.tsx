import dash_icon from '../assets/dash_icon.svg'
import finance_icon from '../assets/finance_icon.svg'
import order_icon from '../assets/order_icon.svg'
import client_icon from '../assets/client_icon.svg'
import product_icon from '../assets/product_icon.svg'
import config_icon from '../assets/config_icon.svg'
import styles from '../components/Navbar.module.css'


export function Navbar() {
    return (
        <>
            <div className="h-12 bg-purple-medium">
                <div id="slider" className='flex items-center w-full h-full overflow-x-scroll scroll whitespace-nowrap scroll-smooth scrollbar-hide'>
                    <div className="flex pl-7">
                        <a href="" className="flex pr-10 hover:scale-105 ease-in-out">
                            <img src={dash_icon} className={styles.iconNavbar}></img>
                            <p className={styles.fontNavbar}>Dashboard</p>
                        </a>

                        <div className="w-0.5 h-5 bg-purple-light"></div>

                        <a href="" className="flex pl-4 pr-10 hover:scale-105 ease-in-out">
                            <img src={finance_icon} className={styles.iconNavbar}></img>
                            <p className={styles.fontNavbar}>Finanças</p>
                        </a>

                        <div className="w-0.5 h-5 bg-purple-light"></div>

                        <a href="" className="flex pl-4 pr-10 hover:scale-105 ease-in-out">
                            <img src={order_icon} className={styles.iconNavbar}></img>
                            <p className={styles.fontNavbar}>Pedidos</p>
                        </a>

                        <div className="w-0.5 h-5 bg-purple-light"></div>

                        <a href="" className="flex pl-4 pr-10 hover:scale-105 ease-in-out">
                            <img src={client_icon} className={styles.iconNavbar}></img>
                            <p className={styles.fontNavbar}>Clientes</p>
                        </a>

                        <div className="w-0.5 h-5 bg-purple-light"></div>

                        <a href="" className="flex pl-4 pr-10 hover:scale-105 ease-in-out">
                            <img src={product_icon} className={styles.iconNavbar}></img>
                            <p className={styles.fontNavbar}>Produtos</p>
                        </a>

                        <div className="w-0.5 h-5 bg-purple-light"></div>

                        <a href="" className="flex pl-4 pr-10 hover:scale-105 ease-in-out">
                            <img src={config_icon} className={styles.iconNavbar}></img>
                            <p className={styles.fontNavbar}>Configurações </p>
                        </a>

                    </div>
                </div>
            </div>
        </>
    )
}