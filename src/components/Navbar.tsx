import dash_icon from "../assets/dash_icon.svg";
import finance_icon from "../assets/finance_icon.svg";
import order_icon from "../assets/order_icon.svg";
import client_icon from "../assets/client_icon.svg";
import product_icon from "../assets/product_icon.svg";
import config_icon from "../assets/config_icon.svg";
import styles from "../components/Navbar.module.css";

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
import { CadastrarPedidos } from "./CadastrarPedidos";
import { ItemPedido } from "./ItemPedido";
import { ItemLinha } from "./ItemLinha";
import { ValoresPedido } from "./ValoresPedido";
import { ResultadoPedido } from "./ResultadoPedido";
import { LegendaPedido } from "./LegendaPedido";
import { CadastrarClientes } from "./CadastrarClientes";
import { ResultadoCliente } from "./ResultadoCliente";
import { CadastrarProdutos } from "./CadastrarProdutos";


const setinha = <svg width="18" height="15" viewBox="0 0 18 15" fill="none" xmlns="http://www.w3.org/2000/svg">
<path d="M9 0L17.6603 15H0.339746L9 0Z" fill="white"/>
</svg>

export function Navbar() {
    return (
        <>
            <Tab.Group>
                <div className="max-h-12 min-h-12 bg-purple-medium">
                    <div className='flex items-center w-full h-full max-w-[100%] overflow-x-scroll scroll whitespace-nowrap scroll-smooth scrollbar-hide'>
                        <Tab.List className="flex items-center gap-5 px-3">
                            <Tab>
                                {({ selected }) => (
                                    <div className={selected ? styles.isSelected : styles.isNotSelected}>
                                        <img src={dash_icon} className={styles.iconNavbar} />
                                        <div className="flex flex-col items-center">
                                            Dashboard
                                            <span className="relative">
                                            <span className="absolute -bottom-3 left-0">
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
                                        <img src={finance_icon} className={styles.iconNavbar} />
                                        <div className="flex flex-col items-center">
                                        Finanças
                                        <span className="relative">
                                        <span className="absolute -bottom-3 left-0">
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
                                        <img src={order_icon} className={styles.iconNavbar} />
                                        <div className="flex flex-col items-center">
                                        Pedidos
                                        <span className="relative">
                                        <span className="absolute -bottom-3 left-0">
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
                                        <img src={client_icon} className={styles.iconNavbar} />
                                        <div className="flex flex-col items-center">
                                        Clientes
                                        <span className="relative">
                                        <span className="absolute -bottom-3 left-0">
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
                                        <img src={product_icon} className={styles.iconNavbar} />
                                        <div className="flex flex-col items-center">
                                        Produtos
                                        <span className="relative">
                                        <span className="absolute -bottom-3 left-0">
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
                                        <img src={config_icon} className={styles.iconNavbar} />
                                        <div className="flex flex-col items-center">
                                        Configurações
                                        <span className="relative">
                                        <span className="absolute -bottom-3 left-0">
                                        {selected && setinha}
                                        </span>
                                        </span>
                                        </div>
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
                        <CadastrarPedidos />
                        <ItemPedido />
                        <ItemLinha />
                        <ValoresPedido />
                        <Pesquisar />
                        <ResultadoPedido />
                        <LegendaPedido />
                    </Tab.Panel>
                    {/* CLIENTES */}
                    <Tab.Panel>
                        <CadastrarClientes />
                        <Pesquisar />
                        <ResultadoCliente />
                    </Tab.Panel>
                    {/* PRODUTOS */}
                    <Tab.Panel>
                        <CadastrarProdutos />
                        <CadastrarUsuario />
                    </Tab.Panel>
                    {/* CONFIGURACÕES */}
                    <Tab.Panel>
                        <CadastrarUsuario />
                    </Tab.Panel>

                </Tab.Panels>
            </Tab.Group>
        </>
    );
}

