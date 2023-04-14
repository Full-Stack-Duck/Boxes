export function Login(){
    return(
        <section>
            <article>
                <div>
                    <img src="" alt="" />
                    <h1>Seja bem-vindo</h1>
                    <h3>Por favor, insira seus dados</h3>
                </div>
            </article>
            <article>
                <form action="">
                    <label htmlFor="">Email</label>
                    <input type="email" placeholder="Escreva seu email"/>

                    <label htmlFor="">Senha</label>
                    <input type="password" />

                    <input type="checkbox" />
                    <label htmlFor="">Lembrar minha senha</label>
                </form>
                <a href="">
                    Esqueci minha senha
                </a>
                <div>
                    <button>
                        Entrar
                    </button>
                </div>
                <p>Não tem uma conta?</p>
                <a href="">CADASTRE-SE</a>
            </article>
        </section>
    )
}