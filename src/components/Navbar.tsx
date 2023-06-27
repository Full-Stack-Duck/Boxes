import dash_icon from "../assets/dash_icon.svg";
import finance_icon from "../assets/finance_icon.svg";
import order_icon from "../assets/order_icon.svg";
import client_icon from "../assets/client_icon.svg";
import product_icon from "../assets/product_icon.svg";
import config_icon from "../assets/config_icon.svg";
import styles from "../components/Navbar.module.css";

import { Tab } from "@headlessui/react";
import { CadastrarDespesa } from "./CadastrarDespesas";
import { Pesquisar } from "./Pesquisar";
import { PesquisarProduto } from "./PesquisarProduto";
import { Financeiro } from "./Financeiro";
import { FiltroDashboard } from "./FiltroDashboard";
import { Dashboard } from "./dashboard/Dashboard";
import { PedidosOrcamentos } from "./dashboard/PedidosOrcamentos";
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
import { ResultadoPadrao } from "./ResultadoPadrao";
import { PesquisarEstoque } from "./PesquisarEstoque";
import Cadastro from "./Cadastro";
import { Login } from "./Login";
import { NewCadastrarPedidos } from "./NewCadastrarPedido";
import { CadastrarProduto1 } from "./CadastrarProduto1";
import { CabecalhoCliente } from "./CabecalhoCliente";
import { PesquisarCliente } from "./PesquisarCliente";
import { CabecalhoFinanceiro } from "./CabecalhoFinanceiro";
import { PesquisarDespesas } from "./PesquisarDespesas";
import { CabecalhoProduto } from "./CabecalhoProduto";
import { CabecalhoEstoque } from "./CabecalhoEstoque";


const setinha = <svg width="18" height="15" viewBox="0 0 18 15" fill="none" xmlns="http://www.w3.org/2000/svg">
    <path d="M9 0L17.6603 15H0.339746L9 0Z" fill="white" />
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

                <Tab.Panels >
                    {/* DASHBOARD */}
                    <Tab.Panel >
                        <div className="hidden lg:block">
                            <FiltroDashboard />
                        </div>
                        <div className="lg:flex lg:px-32 lg:pt-10 lg:justify-between lg:mb-5">
                            <Dashboard />
                            <PedidosOrcamentos />
                            <ProdutosEstoque /> 
                        </div>
                        <div className="lg:flex lg:justify-between lg:pb-4 lg:px-32">
                            <div className="lg:w-2/3 lg:border lg:border-purple-light lg:rounded-xl"></div>
                            <ClientesCadastrados />
                        </div>
                    </Tab.Panel>
                    {/* FINANCAS */}
                    <Tab.Panel>
                    <div className="hidden lg:block">
                            <CabecalhoFinanceiro />
                        </div>
                        <div className="lg:flex lg:justify-between lg:pb-4 lg:px-32 lg:pt-10">
                            <Financeiro />
                            <CadastrarDespesa />
                        </div>
                        <div className="lg:flex lg:justify-between lg:pb-4 lg:px-32">
                            <PesquisarDespesas />
                        </div>
                    </Tab.Panel>
                    {/* PEDIDOS */}
                    <Tab.Panel>
                        {/* <Login /> */}
                        <NewCadastrarPedidos />
                        {/* <ItemPedido />
                        <ValoresPedido />
                        <Pesquisar />
                        <LegendaPedido /> */}
                    </Tab.Panel>
                    {/* CLIENTES */}
                    <Tab.Panel>
                        <div className="hidden lg:block">
                            <CabecalhoCliente />
                        </div>
                        <div className="lg:flex lg:px-32 lg:pt-10 lg:justify-between lg:mb-5">
                            <CadastrarClientes />
                            <PesquisarCliente />
                        </div>
                    </Tab.Panel>
                    {/* PRODUTOS */}
                    <Tab.Panel>
                        <div className="hidden lg:block">
                            <CabecalhoProduto />
                        </div>
                        <div className="lg:flex lg:px-32 lg:pt-10 lg:justify-between lg:mb-5">
                            <CadastrarProdutos />
                            <PesquisarProduto />
                        </div>
                        <div className="hidden lg:block">
                            <CabecalhoEstoque />
                        </div>
                        <div className="lg:flex lg:px-32 lg:pt-10 lg:justify-between lg:mb-5">
                            <PesquisarEstoque />
                        </div>
                    </Tab.Panel>
                    {/* CONFIGURACÕES */}
                    <Tab.Panel>
                        {/* <NewCadastrarPedidos /> */}
                        <CadastrarUsuario />
                        {/* <Cadastro /> */}
                       {/*} <Login /> */}
                    </Tab.Panel>

                </Tab.Panels>
            </Tab.Group>
        </>
    );
}

