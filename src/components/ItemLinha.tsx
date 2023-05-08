import verIcon from "../assets/olho-icon-on.svg";
import editarIcon from "../assets/lapis-icon-on.svg";
import deletarIcon from "../assets/lixeira-icon-on.svg";

export function ItemLinha() {
  return (
    <li className="flex border-b-2 border-purple-light justify-center items-center w-full my-2">
      <div className="flex-col items-center justify-center w-full">

          <p className="flex justify-around text-sm">
            <span className="">#6</span>
            <span className="">Coxinha de Frango</span>
            <span className="">150 un.</span>
            <span className="">R$ 270,00</span>
          </p>

          <div className="flex justify-center">
            <div className="flex justify-evenly my-2 w-32 gap-2">
              <a
                href="#"
                className="flex items-center justify-center h-[32px] min-w-[32px] rounded-full bg-purple-medium"
              >
                <img src={verIcon} alt="ver-resultado" className="w-5 h-5" />
              </a>
              <a
                href="#"
                className="flex items-center justify-center h-[32px] min-w-[32px] rounded-full bg-purple-medium"
              >
                <img
                  src={editarIcon}
                  alt="editar-resultado"
                  className="w-5 h-5"
                />
              </a>
              <a
                href="#"
                className="flex items-center justify-center h-[32px] min-w-[32px] rounded-full bg-purple-medium"
              >
                <img
                  src={deletarIcon}
                  alt="deletar-resultado"
                  className="w-5 h-5"
                />
              </a>
          </div>
        </div>
      </div>
    </li>
  );
}
