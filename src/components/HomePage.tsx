import { HeaderWeb } from "./HeaderWeb";
import { Vitrine } from "./VitrineWeb";
import { VideoWeb } from "./VideoWeb";
import { Beneficios } from "./BeneficiosWeb";
import { Planos } from "./PlanosWeb";
import { Contato } from "./ContatoWeb";
import { FooterNavWeb } from "./FooterNavWeb";

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
