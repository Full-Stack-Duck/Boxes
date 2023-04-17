import dash_icon from "../assets/dash_icon.svg";
import finance_icon from "../assets/finance_icon.svg";
import order_icon from "../assets/order_icon.svg";
import client_icon from "../assets/client_icon.svg";
import product_icon from "../assets/product_icon.svg";
import config_icon from "../assets/config_icon.svg";
import styles from "../components/Navbar.module.css";
import dash_dark_icon from "../assets/dark_icons/dash_dark_icon.svg"
import client_dark_icon from "../assets/dark_icons/client_dark_icon.svg"
import order_dark_icon from "../assets/dark_icons/order_dark_icon.svg"
import product_dark_icon from "../assets/dark_icons/product_dark_icon.svg"
import finance_dark_icon from "../assets/dark_icons/finance_dark_icon.svg"
import config_dark_icon from "../assets/dark_icons/config_dark_icon.svg"

import { Tab } from "@headlessui/react";
import { FooterNav } from "./FooterNav";
import { Footer } from "./Footer";
import { CadastrarDespesa } from "./CadastrarDespesas";
import { Pesquisar } from "./Pesquisar";
import { Financeiro } from "./Financeiro";
import { Dashboard } from "./Dashboard";
import { PedidosOrcamentos } from "./PedidosOrcamentos";
import { ClientesCadastrados } from "./ClientesCadastrados";
import { ProdutosEstoque } from "./ProdutosEstoque";
import { CadastrarUsuario } from "./CadastrarUsuario";


const setinha = <svg width="18" height="15" viewBox="0 0 18 15" fill="none" xmlns="http://www.w3.org/2000/svg">
<path d="M9 0L17.6603 15H0.339746L9 0Z" fill="white"/>
</svg>

export function Navbar() {
    return (
        <>
            <Tab.Group>
                <div className="max-h-12 min-h-12 bg-purple-medium">
                    <div className='flex items-center w-full h-full max-w-[100%] overflow-x-scroll scroll whitespace-nowrap scroll-smooth scrollbar-hide'>
                        <Tab.List className="flex items-center gap-5 pl-3">
                            <Tab>
                                {({ selected }) => (
                                    <div className={selected ? styles.isSelected : styles.isNotSelected}>
                                        <img src={selected ? dash_dark_icon : dash_icon} className={styles.iconNavbar} />
                                        <div className="flex flex-col items-center">
                                            Dashboard
                                            <span className="relative">
                                            <span className="absolute -bottom-4 left-0">
                                            {selected && setinha}
                                        </span>
                                        </span>
                                        </div>
                                    </div>
                                )}
                            </Tab>

                            <div className="w-0.5 h-5 bg-purple-light"></div>
                            <Tab>
                                {({ selected }) => (
                                    <div className={selected ? styles.isSelected : styles.isNotSelected}>
                                        <img src={selected ? finance_dark_icon : finance_icon} className={styles.iconNavbar} />
                                        Finanças
                                    </div>
                                )}
                            </Tab>

                            <div className="w-0.5 h-5 bg-purple-light"></div>
                            <Tab>
                                {({ selected }) => (
                                    <div className={selected ? styles.isSelected : styles.isNotSelected}>
                                        <img src={selected ? order_dark_icon : order_icon} className={styles.iconNavbar} />
                                        Pedidos
                                    </div>
                                )}
                            </Tab>

                            <div className="w-0.5 h-5 bg-purple-light"></div>
                            <Tab>
                                {({ selected }) => (
                                    <div className={selected ? styles.isSelected : styles.isNotSelected}>
                                        <img src={selected ? client_dark_icon : client_icon} className={styles.iconNavbar} />
                                        Clientes
                                    </div>
                                )}
                            </Tab>

                            <div className="w-0.5 h-5 bg-purple-light"></div>
                            <Tab>
                                {({ selected }) => (
                                    <div className={selected ? styles.isSelected : styles.isNotSelected}>
                                        <img src={selected ? product_dark_icon : product_icon} className={styles.iconNavbar} />
                                        Produtos
                                    </div>
                                )}
                            </Tab>

                            <div className="w-0.5 h-5 bg-purple-light"></div>
                            <Tab>
                                {({ selected }) => (
                                    <div className={selected ? styles.isSelected : styles.isNotSelected}>
                                        <img src={selected ? config_dark_icon : config_icon} className={styles.iconNavbar} />
                                        Configurações
                                    </div>
                                )}
                            </Tab>

                        </Tab.List>
                    </div>
                </div>

                <Tab.Panels>
                    {/* DASHBOARD */}
                    <Tab.Panel>
                        {/*<div className="h-60 w-full bg-white text-purple-dark font-quicksand text-5xl flex justify-center items-center">
                                Dashboard AQUI.
                            </div>*/}
                        <Dashboard />
                        <PedidosOrcamentos />
                        <ClientesCadastrados />
                        <ProdutosEstoque />
                    </Tab.Panel>
                    {/* FINANCAS */}
                    <Tab.Panel>
                        <Financeiro />
                        <CadastrarDespesa />
                        <Pesquisar />
                    </Tab.Panel>
                    {/* PEDIDOS */}
                    <Tab.Panel>
                        <Pesquisar />
                    </Tab.Panel>
                    {/* CLIENTES */}
                    <Tab.Panel>
                        <FooterNav />
                    </Tab.Panel>
                    {/* PRODUTOS */}
                    <Tab.Panel>
                        <CadastrarUsuario />
                    </Tab.Panel>
                    {/* CONFIGURACÕES */}
                    <Tab.Panel>
                        <Footer />
                    </Tab.Panel>

                </Tab.Panels>
            </Tab.Group>
        </>
    );
}
