import { HeaderWeb } from "../components/HeaderWeb";
import { Vitrine } from "../components/VitrineWeb";
import { VideoWeb } from "../components/VideoWeb";
import { Beneficios } from "../components/BeneficiosWeb";
import { Planos } from "../components/PlanosWeb";
import { Contato } from "../components/ContatoWeb";
import { FooterNavWeb } from "../components/FooterNavWeb";

export function HomePage() {
  return (
    <>
      <HeaderWeb />
      <Vitrine />
      <VideoWeb />
      <Beneficios />
      <Planos />
      <Contato />
      <FooterNavWeb />
    </>
  );
}
