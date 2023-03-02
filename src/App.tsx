import { Header } from "./components/Header";
import { CadastrarDespesa } from "./components/CadastrarDespesas";
import { Pesquisar } from "./components/Pesquisar";
import { FooterNav } from "./components/FooterNav";
import { Footer } from "./components/Footer";

export function App() {


  return (
   <>
    <Header />
    <CadastrarDespesa />
    <Pesquisar />
    <FooterNav />
    <Footer />
   </>
  )
}

