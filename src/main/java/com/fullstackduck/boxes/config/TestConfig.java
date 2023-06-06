package com.fullstackduck.boxes.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.fullstackduck.boxes.entities.Cliente;
import com.fullstackduck.boxes.entities.Despesa;
import com.fullstackduck.boxes.entities.ItensOrcamento;
import com.fullstackduck.boxes.entities.Licenca;
import com.fullstackduck.boxes.entities.MovimentacaoEstoque;
import com.fullstackduck.boxes.entities.Orcamento;
import com.fullstackduck.boxes.entities.Pagamento;
import com.fullstackduck.boxes.entities.Pedido;
import com.fullstackduck.boxes.entities.Produto;
import com.fullstackduck.boxes.entities.Receita;
import com.fullstackduck.boxes.entities.Usuario;
import com.fullstackduck.boxes.entities.enums.Categoria;
import com.fullstackduck.boxes.entities.enums.FormaPagamento;
import com.fullstackduck.boxes.entities.enums.Status;
import com.fullstackduck.boxes.entities.enums.StatusLicenca;
import com.fullstackduck.boxes.entities.enums.StatusPagamento;
import com.fullstackduck.boxes.entities.enums.StatusPagamentoPedido;
import com.fullstackduck.boxes.entities.enums.StatusPedido;
import com.fullstackduck.boxes.entities.enums.TipoArmazenamento;
import com.fullstackduck.boxes.entities.enums.TipoEntrega;
import com.fullstackduck.boxes.entities.enums.TipoLicenca;
import com.fullstackduck.boxes.entities.enums.TipoProduto;
import com.fullstackduck.boxes.repositories.ClienteRepository;
import com.fullstackduck.boxes.repositories.DespesaRepository;
import com.fullstackduck.boxes.repositories.ItensOrcamentoRepository;
import com.fullstackduck.boxes.repositories.LicencaRepository;
import com.fullstackduck.boxes.repositories.MovimentacaoEstoqueRepository;
import com.fullstackduck.boxes.repositories.OrcamentoRepository;
import com.fullstackduck.boxes.repositories.PagamentoRepository;
import com.fullstackduck.boxes.repositories.PedidoRepository;
import com.fullstackduck.boxes.repositories.ProdutoRepository;
import com.fullstackduck.boxes.repositories.ReceitaRepository;
import com.fullstackduck.boxes.repositories.UsuarioRepository;

// Classe auxiliar de configuração para o perfil de testes
@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{

	@Autowired
	private LicencaRepository licencaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private DespesaRepository despesaRepository;
	
	@Autowired
	private OrcamentoRepository orcamentoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ReceitaRepository receitaRepository;
	
	@Autowired
	private ItensOrcamentoRepository itensOrcamentoRepository;
	
	@Autowired
	private MovimentacaoEstoqueRepository movimentacaoEstoqueRepository;

	@Override
	public void run(String... args) throws Exception {
		
		//Teste Usuário
		Usuario u1 = new Usuario(null, "Bruno", "123456789", "bruno@gmail.com", "7512345678", "$2a$12$0AOkkAhQ7GAPexQSVEORsuNiDEyQbnlrz6d6r1qV3cVTIp2/Ycwn2", "Tomba", "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.pngwing.com%2Fen%2Fsearch%3Fq%3Djava&psig=AOvVaw3tTPnzEE5A094sE5h23Ik0&ust=1685803990633000&source=images&cd=vfe&ved=0CBEQjRxqFwoTCPjGjbXrpP8CFQAAAAAdAAAAABAE", null, Status.ATIVO);
		Usuario u2 = new Usuario(null, "José", "123456789", "jose@gmail.com", "7512345678", "$2a$12$xzA6d8BHkEWEbeOfWY6IHuluK4dWShZkYbfVAmS3/gACPJwJVCnmC", "Tomba", null, null, Status.ATIVO);
		Usuario u3 = new Usuario(null, "Kelvin", "123456789", "kelvin@gmail.com", "7512345678", "cccccccc", "Tomba", null, null, Status.ATIVO);
		Usuario u4 = new Usuario(null, "Lucas", "123456789", "lucas@gmail.com", "7512345678", "dddddddd", "Tomba", null, null, Status.ATIVO);
		Usuario u5 = new Usuario(null, "Vinícius", "123456789", "vinicius@gmail.com", "7512345678", "eeeeeeee", "Tomba", null, null, Status.ATIVO);
		
		usuarioRepository.saveAll(Arrays.asList(u1, u2, u3, u4, u5));
		
		
		//Teste Licença
		Licenca l01 = new Licenca(null, StatusLicenca.ATIVA, Instant.now(), null,  null, TipoLicenca.MENSAL, 91.00, u1);
		Licenca l02 = new Licenca(null, StatusLicenca.ATIVA, Instant.now(), null, null, TipoLicenca.SEMESTRAL, 516.00, u2);
		Licenca l03 = new Licenca(null, StatusLicenca.ATIVA, Instant.now(), null, null, TipoLicenca.ANUAL, 984.00, u3);
		Licenca l04 = new Licenca(null, StatusLicenca.ATIVA, Instant.now(), null, null, TipoLicenca.MENSAL, 91.00, u4);
		Licenca l05 = new Licenca(null, StatusLicenca.ATIVA, Instant.now(), null, null, TipoLicenca.GRATUITA, 00.00, u5);
		Licenca l06 = new Licenca(null, StatusLicenca.EXPIRADA, Instant.now(), null, null, TipoLicenca.MENSAL, 91.00, u1);
		Licenca l07 = new Licenca(null, StatusLicenca.EXPIRADA, Instant.now(), null, null, TipoLicenca.SEMESTRAL, 516.00, u2);
		Licenca l08 = new Licenca(null, StatusLicenca.EXPIRADA, Instant.now(), null, null, TipoLicenca.ANUAL, 984.00, u3);
		Licenca l09 = new Licenca(null, StatusLicenca.EXPIRADA, Instant.now(), null, null, TipoLicenca.MENSAL, 91.00, u4);
		Licenca l10 = new Licenca(null, StatusLicenca.EXPIRADA, Instant.now(), null, null, TipoLicenca.GRATUITA, 00.00, u5);
		Licenca l11 = new Licenca(null, StatusLicenca.CANCELADA, Instant.now(), null, null, TipoLicenca.MENSAL, 91.00, u1);
		Licenca l12 = new Licenca(null, StatusLicenca.CANCELADA, Instant.now(), null, null, TipoLicenca.SEMESTRAL, 516.00, u2);
		Licenca l13 = new Licenca(null, StatusLicenca.CANCELADA, Instant.now(), null, null, TipoLicenca.ANUAL, 984.00, u3);
		Licenca l14 = new Licenca(null, StatusLicenca.CANCELADA, Instant.now(), null, null, TipoLicenca.MENSAL, 91.00, u4);
		Licenca l15 = new Licenca(null, StatusLicenca.CANCELADA, Instant.now(), null, null, TipoLicenca.GRATUITA, 00.00, u5);
		
		licencaRepository.saveAll(Arrays.asList(l01, l02, l03, l04, l05, l06, l07, l08, l09, l10, l11, l12, l13, l14, l15));

		
		//Teste Cliente
		Cliente c01 = new Cliente(null, "Vinicius","vinicius@gmail.com", "7512345678", null, "123456789", null, Status.ATIVO, u1);
		Cliente c02 = new Cliente(null, "Vinicius","vinicius@gmail.com", "7512345678", null, "123456789", null, Status.ATIVO, u1);
		Cliente c03 = new Cliente(null, "Vinicius","vinicius@gmail.com", "7512345678", null, "123456789", null, Status.ATIVO, u2);
		Cliente c04 = new Cliente(null, "Vinicius","vinicius@gmail.com", "7512345678", null, "123456789", null, Status.ATIVO, u2);
		Cliente c05 = new Cliente(null, "Vinicius","vinicius@gmail.com", "7512345678", null, "123456789", null, Status.ATIVO, u3);
		Cliente c06 = new Cliente(null, "Vinicius","vinicius@gmail.com", "7512345678", null, "123456789", null, Status.ATIVO, u3);
		Cliente c07 = new Cliente(null, "Vinicius","vinicius@gmail.com", "7512345678", null, "123456789", null, Status.ATIVO, u4);
		Cliente c08 = new Cliente(null, "Vinicius","vinicius@gmail.com", "7512345678", null, "123456789", null, Status.ATIVO, u4);
		Cliente c09 = new Cliente(null, "Vinicius","vinicius@gmail.com", "7512345678", null, "123456789", null, Status.ATIVO, u5);
		Cliente c10 = new Cliente(null, "Vinicius","vinicius@gmail.com", "7512345678", null, "123456789", null, Status.ATIVO, u5);
		
		clienteRepository.saveAll(Arrays.asList(c01, c02, c03, c04, c05, c06, c07, c08, c09, c10));
		
		
		/*//Teste Estoque
		Estoque e01 = new Estoque(null, u1.getNome(), u1);
		Estoque e02 = new Estoque(null, u2.getNome(), u2);
		Estoque e03 = new Estoque(null, u3.getNome(), u3);
		Estoque e04 = new Estoque(null, u4.getNome(), u4);
		Estoque e05 = new Estoque(null, u5.getNome(), u5);
		
		estoqueRepository.saveAll(Arrays.asList(e01, e02, e03, e04, e05));*/
		
		
		//Teste Produto
		Produto p01 = new Produto(null, "Coxinha", 4.5, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u1);
		Produto p02 = new Produto(null, "Empada", 5.0, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p03 = new Produto(null, "Lasanha", 25.0, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.PRATO, Status.ATIVO, "Usar massa de pastel", u1);
		Produto p04 = new Produto(null, "Mini Quiche", 3.5, TipoArmazenamento.ESTOCAVEL, 30, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p05 = new Produto(null, "Mini Hot Dog", 2.0, TipoArmazenamento.ESTOCAVEL, 40, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p06 = new Produto(null, "Batata Frita", 6.0, TipoArmazenamento.ESTOCAVEL, 50, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u1);
		Produto p07 = new Produto(null, "Bruschetta", 7.5, TipoArmazenamento.ESTOCAVEL, 25, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p08 = new Produto(null, "Mini Quibe", 3.0, TipoArmazenamento.ESTOCAVEL, 30, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p09 = new Produto(null, "Pastel Assado", 4.0, TipoArmazenamento.ESTOCAVEL, 40, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p10 = new Produto(null, "Mini Sanduíche", 5.5, TipoArmazenamento.ESTOCAVEL, 35, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p11 = new Produto(null, "Salada de Frutas", 6.5, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.PRATO, Status.ATIVO, "Embalagem de plástico", u1);
		Produto p12 = new Produto(null, "Sopa de Cebola", 7.0, TipoArmazenamento.ESTOCAVEL, 15, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p13 = new Produto(null, "Pão de Queijo", 3.0, TipoArmazenamento.ESTOCAVEL, 50, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p14 = new Produto(null, "Mini Hambúrguer", 4.5, TipoArmazenamento.ESTOCAVEL, 30, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p15 = new Produto(null, "Tábua de Frios", 8.5, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.PRATO, Status.ATIVO, "Montar com variedade de queijos e embutidos", u1);
		Produto p16 = new Produto(null, "Croquete de Carne", 5.0, TipoArmazenamento.ESTOCAVEL, 25, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u1);
		Produto p17 = new Produto(null, "Sanduíche Natural", 6.0, TipoArmazenamento.ESTOCAVEL, 35, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p18 = new Produto(null, "Cuscuz Paulista", 7.5, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p19 = new Produto(null, "Salada Caprese", 5.0, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.PRATO, Status.ATIVO, "Embalagem de plástico", u1);
		Produto p20 = new Produto(null, "Pizza de Calabresa", 8.0, TipoArmazenamento.ESTOCAVEL, 40, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p21 = new Produto(null, "Bolinho de Bacalhau", 7.0, TipoArmazenamento.ESTOCAVEL, 30, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u1);
		Produto p22 = new Produto(null, "Cachorro-Quente", 4.5, TipoArmazenamento.ESTOCAVEL, 35, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p23 = new Produto(null, "Quiche de Queijo", 6.0, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p24 = new Produto(null, "Enroladinho de Salsicha", 3.5, TipoArmazenamento.ESTOCAVEL, 40, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p25 = new Produto(null, "Salpicão", 8.5, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.PRATO, Status.ATIVO, "Embalagem de plástico", u1);
		Produto p26 = new Produto(null, "Pastel Frito", 4.0, TipoArmazenamento.ESTOCAVEL, 45, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u1);
		Produto p27 = new Produto(null, "Escondidinho de Carne Seca", 7.5, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p28 = new Produto(null, "Mini Salgadinhos", 3.5, TipoArmazenamento.ESTOCAVEL, 40, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p29 = new Produto(null, "Canapés", 6.0, TipoArmazenamento.ESTOCAVEL, 30, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p30 = new Produto(null, "Salada de Maionese", 5.0, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.PRATO, Status.ATIVO, "Embalagem de plástico", u1);
		Produto p31 = new Produto(null, "Pão de Alho", 4.5, TipoArmazenamento.ESTOCAVEL, 35, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p32 = new Produto(null, "Empadão", 6.0, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p33 = new Produto(null, "Bolinha de Queijo", 3.0, TipoArmazenamento.ESTOCAVEL, 40, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u1);
		Produto p34 = new Produto(null, "Mini Wrap", 5.5, TipoArmazenamento.ESTOCAVEL, 30, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p35 = new Produto(null, "Torta de Frango", 7.0, TipoArmazenamento.ESTOCAVEL, 25, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p36 = new Produto(null, "Pastel de Belém", 3.5, TipoArmazenamento.ESTOCAVEL, 40, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p37 = new Produto(null, "Mini Pizza", 4.5, TipoArmazenamento.ESTOCAVEL, 30, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p38 = new Produto(null, "Coxinha de Frango", 6.0, TipoArmazenamento.ESTOCAVEL, 25, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u1);
		Produto p39 = new Produto(null, "Mini Esfiha", 5.0, TipoArmazenamento.ESTOCAVEL, 35, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p40 = new Produto(null, "Salada Caesar", 6.5, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.PRATO, Status.ATIVO, "Embalagem de plástico", u1);
		Produto p41 = new Produto(null, "Sopa de Legumes", 7.0, TipoArmazenamento.ESTOCAVEL, 15, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p42 = new Produto(null, "Bolinho de Queijo", 3.0, TipoArmazenamento.ESTOCAVEL, 50, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u1);
		Produto p43 = new Produto(null, "Mini Coxinha", 4.5, TipoArmazenamento.ESTOCAVEL, 30, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u1);
		Produto p44 = new Produto(null, "Sanduíche de Metro", 5.5, TipoArmazenamento.ESTOCAVEL, 35, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p45 = new Produto(null, "Esfiha de Carne", 6.0, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p46 = new Produto(null, "Coxinha de Catupiry", 4.5, TipoArmazenamento.ESTOCAVEL, 30, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u1);
		Produto p47 = new Produto(null, "Quibe Frito", 3.0, TipoArmazenamento.ESTOCAVEL, 40, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u1);
		Produto p48 = new Produto(null, "Risoles", 5.0, TipoArmazenamento.ESTOCAVEL, 35, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u1);
		Produto p49 = new Produto(null, "Salada de Rúcula com Tomate Seco", 6.5, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.PRATO, Status.ATIVO, "Embalagem de plástico", u1);
		Produto p50 = new Produto(null, "Bolinho de Bacalhau", 7.0, TipoArmazenamento.ESTOCAVEL, 30, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u1);
		Produto p51 = new Produto(null, "Salada de Batata", 4.5, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.PRATO, Status.ATIVO, "Embalagem de plástico", u1);
		Produto p52 = new Produto(null, "Cuscuz de Sardinha", 5.0, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p53 = new Produto(null, "Enroladinho de Presunto e Queijo", 3.5, TipoArmazenamento.ESTOCAVEL, 40, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p54 = new Produto(null, "Salada Waldorf", 8.5, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.PRATO, Status.ATIVO, "Embalagem de plástico", u1);
		Produto p55 = new Produto(null, "Pão de Queijo Recheado", 4.0, TipoArmazenamento.ESTOCAVEL, 45, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p56 = new Produto(null, "Escondidinho de Frango", 7.5, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p57 = new Produto(null, "Mini Quindim", 3.5, TipoArmazenamento.ESTOCAVEL, 40, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p58 = new Produto(null, "Esfiha de Calabresa", 6.0, TipoArmazenamento.ESTOCAVEL, 25, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p59 = new Produto(null, "Salada Grega", 5.0, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.PRATO, Status.ATIVO, "Embalagem de plástico", u1);
		Produto p60 = new Produto(null, "Pizza de Queijo", 8.0, TipoArmazenamento.ESTOCAVEL, 40, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p61 = new Produto(null, "Bolinho de Carne", 7.0, TipoArmazenamento.ESTOCAVEL, 30, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u1);
		Produto p62 = new Produto(null, "Mini Wrap de Frango", 5.5, TipoArmazenamento.ESTOCAVEL, 30, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p63 = new Produto(null, "Torta de Legumes", 7.0, TipoArmazenamento.ESTOCAVEL, 25, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p64 = new Produto(null, "Pastel de Nata", 3.5, TipoArmazenamento.ESTOCAVEL, 40, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p65 = new Produto(null, "Mini Enroladinho", 4.5, TipoArmazenamento.ESTOCAVEL, 30, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p66 = new Produto(null, "Biscoito de Polvilho", 6.0, TipoArmazenamento.ESTOCAVEL, 25, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p67 = new Produto(null, "Pão com Linguiça", 4.5, TipoArmazenamento.ESTOCAVEL, 35, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p68 = new Produto(null, "Empada de Frango", 6.0, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p69 = new Produto(null, "Coxinha de Frango com Catupiry", 4.5, TipoArmazenamento.ESTOCAVEL, 30, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u1);
		Produto p70 = new Produto(null, "Bolinha de Queijo com Presunto", 3.0, TipoArmazenamento.ESTOCAVEL, 40, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u1);
		Produto p71 = new Produto(null, "Mini Hot Dog", 5.5, TipoArmazenamento.ESTOCAVEL, 35, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p72 = new Produto(null, "Esfiha de Queijo", 6.0, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p73 = new Produto(null, "Coxinha de Frango com Catupiry", 4.5, TipoArmazenamento.ESTOCAVEL, 30, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u1);
		Produto p74 = new Produto(null, "Bolinho de Arroz", 3.0, TipoArmazenamento.ESTOCAVEL, 40, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u1);
		Produto p75 = new Produto(null, "Brigadeiro de Colher", 5.5, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.DOCE, Status.ATIVO, "Embalagem de plástico", u1);
		Produto p76 = new Produto(null, "Pão de Batata", 4.0, TipoArmazenamento.ESTOCAVEL, 45, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p77 = new Produto(null, "Escondidinho de Camarão", 7.5, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p78 = new Produto(null, "Rabanada", 3.5, TipoArmazenamento.ESTOCAVEL, 40, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p79 = new Produto(null, "Salada de Frutas", 5.5, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.PRATO, Status.ATIVO, "Embalagem de plástico", u1);
		Produto p80 = new Produto(null, "Pão de Queijo", 4.0, TipoArmazenamento.ESTOCAVEL, 45, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p81 = new Produto(null, "Empada de Palmito", 6.0, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p82 = new Produto(null, "Bolinha de Queijo", 3.5, TipoArmazenamento.ESTOCAVEL, 40, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u1);
		Produto p83 = new Produto(null, "Torta de Limão", 6.5, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.DOCE, Status.ATIVO, "Embalagem de plástico", u1);
		Produto p84 = new Produto(null, "Pão de Mel", 4.0, TipoArmazenamento.ESTOCAVEL, 45, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p85 = new Produto(null, "Escondidinho de Carne Seca", 7.5, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p86 = new Produto(null, "Cachorro Quente", 3.5, TipoArmazenamento.ESTOCAVEL, 40, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p87 = new Produto(null, "Salpicão", 5.5, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.PRATO, Status.ATIVO, "Embalagem de plástico", u1);
		Produto p88 = new Produto(null, "Brigadeiro", 4.0, TipoArmazenamento.ESTOCAVEL, 45, TipoProduto.DOCE, Status.ATIVO, "quero congelado", u1);
		Produto p89 = new Produto(null, "Creme de Milho", 6.5, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.PRATO, Status.ATIVO, "Embalagem de plástico", u1);
		Produto p90 = new Produto(null, "Bolo de Cenoura", 4.0, TipoArmazenamento.ESTOCAVEL, 45, TipoProduto.DOCE, Status.ATIVO, "quero congelado", u1);
		Produto p91 = new Produto(null, "Risoto de Frango", 7.5, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p92 = new Produto(null, "Cuscuz Paulista", 3.5, TipoArmazenamento.ESTOCAVEL, 40, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p93 = new Produto(null, "Torta de Maracujá", 6.5, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.DOCE, Status.ATIVO, "Embalagem de plástico", u1);
		Produto p94 = new Produto(null, "Pão de Forma", 4.0, TipoArmazenamento.ESTOCAVEL, 45, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p95 = new Produto(null, "Empada de Frango com Catupiry", 6.0, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p96 = new Produto(null, "Coxinha de Frango", 4.5, TipoArmazenamento.ESTOCAVEL, 30, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u1);
		Produto p97 = new Produto(null, "Enroladinho de Salsicha", 3.0, TipoArmazenamento.ESTOCAVEL, 40, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u1);
		Produto p98 = new Produto(null, "Torta de Frango com Catupiry", 6.0, TipoArmazenamento.ESTOCAVEL, 25, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		Produto p99 = new Produto(null, "Salgado de Queijo", 5.0, TipoArmazenamento.ESTOCAVEL, 30, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u1);
		Produto p100 = new Produto(null, "Bolo de Fubá", 4.5, TipoArmazenamento.ESTOCAVEL, 35, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1);
		/*Produto p101 = new Produto(null, "Coxinha de Frango com Catupiry", 4.5, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u2);
		Produto p102 = new Produto(null, "Enroladinho de Salsicha", 3.0, TipoArmazenamento.ESTOCAVEL, 30, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u2);
		Produto p103 = new Produto(null, "Torta de Frango com Catupiry", 6.0, TipoArmazenamento.ESTOCAVEL, 25, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u2);
		Produto p104 = new Produto(null, "Salgado de Queijo", 5.0, TipoArmazenamento.ESTOCAVEL, 30, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u2);
		Produto p105 = new Produto(null, "Bolo de Fubá", 4.5, TipoArmazenamento.ESTOCAVEL, 35, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u2);
		Produto p106 = new Produto(null, "Arroz à Grega", 7.5, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u2);
		Produto p107 = new Produto(null, "Feijoada", 9.0, TipoArmazenamento.ESTOCAVEL, 40, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u2);
		Produto p108 = new Produto(null, "Pudim de Leite", 6.5, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.DOCE, Status.ATIVO, "Embalagem de plástico", u2);
		Produto p109 = new Produto(null, "Torta de Limão", 5.0, TipoArmazenamento.ESTOCAVEL, 45, TipoProduto.DOCE, Status.ATIVO, "quero congelado", u2);
		Produto p110 = new Produto(null, "Pão de Queijo", 3.5, TipoArmazenamento.ESTOCAVEL, 40, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u2);
		Produto p111 = new Produto(null, "Brigadeirão", 6.0, TipoArmazenamento.ESTOCAVEL, 25, TipoProduto.DOCE, Status.ATIVO, "quero congelado", u2);
		Produto p112 = new Produto(null, "Feijão Tropeiro", 7.0, TipoArmazenamento.ESTOCAVEL, 30, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u2);
		Produto p113 = new Produto(null, "Torta de Morango", 5.5, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.DOCE, Status.ATIVO, "Embalagem de plástico", u2);
		Produto p114 = new Produto(null, "Esfiha de Carne", 4.0, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u2);
		Produto p115 = new Produto(null, "Creme de Papaia com Cassis", 8.0, TipoArmazenamento.ESTOCAVEL, 35, TipoProduto.DOCE, Status.ATIVO, "quero congelado", u2);
		Produto p116 = new Produto(null, "Risoto de Camarão", 12.0, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.PRATO, Status.ATIVO, "Usar camarões frescos", u2);
		Produto p117 = new Produto(null, "Pão de Alho", 3.0, TipoArmazenamento.ESTOCAVEL, 30, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u2);
		Produto p118 = new Produto(null, "Sorvete de Chocolate", 6.0, TipoArmazenamento.ESTOCAVEL, 25, TipoProduto.DOCE, Status.ATIVO, "quero congelado", u2);
		Produto p119 = new Produto(null, "Pão Francês", 1.5, TipoArmazenamento.ESTOCAVEL, 40, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u2);
		Produto p120 = new Produto(null, "Torta de Maçã", 5.0, TipoArmazenamento.ESTOCAVEL, 45, TipoProduto.DOCE, Status.ATIVO, "quero congelado", u2);
		Produto p121 = new Produto(null, "Coxinha de Frango", 4.0, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u2);
		Produto p122 = new Produto(null, "Torta de Frango", 6.0, TipoArmazenamento.ESTOCAVEL, 30, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u2);
		Produto p123 = new Produto(null, "Camarão à Baiana", 15.0, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.PRATO, Status.ATIVO, "Usar camarões frescos", u2);
		Produto p124 = new Produto(null, "Salada de Frutas", 4.5, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.DOCE, Status.ATIVO, "Embalagem de plástico", u2);
		Produto p125 = new Produto(null, "Kibe", 3.5, TipoArmazenamento.ESTOCAVEL, 40, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u2);
		Produto p126 = new Produto(null, "Mousse de Maracujá", 5.5, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.DOCE, Status.ATIVO, "Embalagem de plástico", u2);
		Produto p127 = new Produto(null, "Coxinha de Frango com Catupiry", 4.5, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u2);
		Produto p128 = new Produto(null, "Enroladinho de Salsicha", 3.0, TipoArmazenamento.ESTOCAVEL, 30, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u2);
		Produto p129 = new Produto(null, "Torta de Frango com Catupiry", 6.0, TipoArmazenamento.ESTOCAVEL, 25, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u2);
		Produto p130 = new Produto(null, "Salgado de Queijo", 5.0, TipoArmazenamento.ESTOCAVEL, 30, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u2);
		Produto p131 = new Produto(null, "Bolo de Fubá", 4.5, TipoArmazenamento.ESTOCAVEL, 35, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u2);
		Produto p132 = new Produto(null, "Arroz à Grega", 7.5, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u2);
		Produto p133 = new Produto(null, "Feijoada", 9.0, TipoArmazenamento.ESTOCAVEL, 40, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u2);
		Produto p134 = new Produto(null, "Pudim de Leite", 6.5, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.DOCE, Status.ATIVO, "Embalagem de plástico", u2);
		Produto p135 = new Produto(null, "Torta de Limão", 5.0, TipoArmazenamento.ESTOCAVEL, 45, TipoProduto.DOCE, Status.ATIVO, "quero congelado", u2);
		Produto p136 = new Produto(null, "Pão de Queijo", 3.5, TipoArmazenamento.ESTOCAVEL, 40, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u2);
		Produto p137 = new Produto(null, "Brigadeirão", 6.0, TipoArmazenamento.ESTOCAVEL, 25, TipoProduto.DOCE, Status.ATIVO, "quero congelado", u2);
		Produto p138 = new Produto(null, "Feijão Tropeiro", 7.0, TipoArmazenamento.ESTOCAVEL, 30, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u2);
		Produto p139 = new Produto(null, "Torta de Morango", 5.5, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.DOCE, Status.ATIVO, "Embalagem de plástico", u2);
		Produto p140 = new Produto(null, "Esfiha de Carne", 4.0, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u2);
		Produto p141 = new Produto(null, "Creme de Papaia com Cassis", 8.0, TipoArmazenamento.ESTOCAVEL, 35, TipoProduto.DOCE, Status.ATIVO, "quero congelado", u2);
		Produto p142 = new Produto(null, "Risoto de Camarão", 12.0, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.PRATO, Status.ATIVO, "Usar camarões frescos", u2);
		Produto p143 = new Produto(null, "Pão de Alho", 3.0, TipoArmazenamento.ESTOCAVEL, 30, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u2);
		Produto p144 = new Produto(null, "Sorvete de Chocolate", 6.0, TipoArmazenamento.ESTOCAVEL, 25, TipoProduto.DOCE, Status.ATIVO, "quero congelado", u2);
		Produto p145 = new Produto(null, "Pão Francês", 1.5, TipoArmazenamento.ESTOCAVEL, 40, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u2);
		Produto p146 = new Produto(null, "Torta de Maçã", 5.0, TipoArmazenamento.ESTOCAVEL, 45, TipoProduto.DOCE, Status.ATIVO, "quero congelado", u2);
		Produto p147 = new Produto(null, "Coxinha de Frango", 4.0, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u2);
		Produto p148 = new Produto(null, "Torta de Frango", 6.0, TipoArmazenamento.ESTOCAVEL, 30, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u2);
		Produto p149 = new Produto(null, "Camarão à Baiana", 15.0, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.PRATO, Status.ATIVO, "Usar camarões frescos", u2);
		Produto p150 = new Produto(null, "Salada de Frutas", 4.5, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.DOCE, Status.ATIVO, "Embalagem de plástico", u2);
		Produto p151 = new Produto(null, "Kibe", 3.5, TipoArmazenamento.ESTOCAVEL, 40, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u2);
		Produto p152 = new Produto(null, "Mousse de Maracujá", 5.5, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.DOCE, Status.ATIVO, "Embalagem de plástico", u2);
		Produto p153 = new Produto(null, "Coxinha de Frango com Catupiry", 4.5, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u2);
		Produto p154 = new Produto(null, "Enroladinho de Salsicha", 3.0, TipoArmazenamento.ESTOCAVEL, 30, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u2);
		Produto p155 = new Produto(null, "Torta de Frango com Catupiry", 6.0, TipoArmazenamento.ESTOCAVEL, 25, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u2);
		Produto p156 = new Produto(null, "Salgado de Queijo", 5.0, TipoArmazenamento.ESTOCAVEL, 30, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u2);
		Produto p157 = new Produto(null, "Bolo de Fubá", 4.5, TipoArmazenamento.ESTOCAVEL, 35, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u2);
		Produto p158 = new Produto(null, "Arroz à Grega", 7.5, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u2);
		Produto p159 = new Produto(null, "Feijoada", 9.0, TipoArmazenamento.ESTOCAVEL, 40, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u2);
		Produto p160 = new Produto(null, "Pudim de Leite", 6.5, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.DOCE, Status.ATIVO, "Embalagem de plástico", u2);
		Produto p161 = new Produto(null, "Torta de Limão", 5.0, TipoArmazenamento.ESTOCAVEL, 45, TipoProduto.DOCE, Status.ATIVO, "quero congelado", u2);
		Produto p162 = new Produto(null, "Pão de Queijo", 3.5, TipoArmazenamento.ESTOCAVEL, 40, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u2);
		Produto p163 = new Produto(null, "Brigadeirão", 6.0, TipoArmazenamento.ESTOCAVEL, 25, TipoProduto.DOCE, Status.ATIVO, "quero congelado", u2);
		Produto p164 = new Produto(null, "Feijão Tropeiro", 7.0, TipoArmazenamento.ESTOCAVEL, 30, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u2);
		Produto p165 = new Produto(null, "Torta de Morango", 5.5, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.DOCE, Status.ATIVO, "Embalagem de plástico", u2);
		Produto p166 = new Produto(null, "Esfiha de Carne", 4.0, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u2);
		Produto p167 = new Produto(null, "Creme de Papaia com Cassis", 8.0, TipoArmazenamento.ESTOCAVEL, 35, TipoProduto.DOCE, Status.ATIVO, "quero congelado", u2);
		Produto p168 = new Produto(null, "Risoto de Camarão", 12.0, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.PRATO, Status.ATIVO, "Usar camarões frescos", u2);
		Produto p169 = new Produto(null, "Pão de Alho", 3.0, TipoArmazenamento.ESTOCAVEL, 30, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u2);
		Produto p170 = new Produto(null, "Sorvete de Chocolate", 6.0, TipoArmazenamento.ESTOCAVEL, 25, TipoProduto.DOCE, Status.ATIVO, "quero congelado", u2);
		Produto p171 = new Produto(null, "Pão Francês", 1.5, TipoArmazenamento.ESTOCAVEL, 40, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u2);
		Produto p172 = new Produto(null, "Torta de Maçã", 5.0, TipoArmazenamento.ESTOCAVEL, 45, TipoProduto.DOCE, Status.ATIVO, "quero congelado", u2);
		Produto p173 = new Produto(null, "Coxinha de Frango", 4.0, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u2);
		Produto p174 = new Produto(null, "Torta de Frango", 6.0, TipoArmazenamento.ESTOCAVEL, 30, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u2);
		Produto p175 = new Produto(null, "Camarão à Baiana", 15.0, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.PRATO, Status.ATIVO, "Usar camarões frescos", u2);
		Produto p176 = new Produto(null, "Salada de Frutas", 4.5, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.DOCE, Status.ATIVO, "Embalagem de plástico", u2);
		Produto p177 = new Produto(null, "Kibe", 3.5, TipoArmazenamento.ESTOCAVEL, 40, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u2);
		Produto p178 = new Produto(null, "Mousse de Maracujá", 5.5, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.DOCE, Status.ATIVO, "Embalagem de plástico", u2);
		Produto p179 = new Produto(null, "Coxinha de Frango com Catupiry", 4.5, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u2);
		Produto p180 = new Produto(null, "Enroladinho de Salsicha", 3.0, TipoArmazenamento.ESTOCAVEL, 30, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u2);
		Produto p181 = new Produto(null, "Torta de Frango com Catupiry", 6.0, TipoArmazenamento.ESTOCAVEL, 25, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u2);
		Produto p182 = new Produto(null, "Salgado de Queijo", 5.0, TipoArmazenamento.ESTOCAVEL, 30, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u2);
		Produto p183 = new Produto(null, "Bolo de Fubá", 4.5, TipoArmazenamento.ESTOCAVEL, 35, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u2);
		Produto p184 = new Produto(null, "Arroz à Grega", 7.5, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u2);
		Produto p185 = new Produto(null, "Feijoada", 9.0, TipoArmazenamento.ESTOCAVEL, 40, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u2);
		Produto p186 = new Produto(null, "Pudim de Leite", 6.5, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.DOCE, Status.ATIVO, "Embalagem de plástico", u2);
		Produto p187 = new Produto(null, "Torta de Limão", 5.0, TipoArmazenamento.ESTOCAVEL, 45, TipoProduto.DOCE, Status.ATIVO, "quero congelado", u2);
		Produto p188 = new Produto(null, "Pão de Queijo", 3.5, TipoArmazenamento.ESTOCAVEL, 40, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u2);
		Produto p189 = new Produto(null, "Brigadeirão", 6.0, TipoArmazenamento.ESTOCAVEL, 25, TipoProduto.DOCE, Status.ATIVO, "quero congelado", u2);
		Produto p190 = new Produto(null, "Feijão Tropeiro", 7.0, TipoArmazenamento.ESTOCAVEL, 30, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u2);
		Produto p191 = new Produto(null, "Torta de Morango", 5.5, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.DOCE, Status.ATIVO, "Embalagem de plástico", u2);
		Produto p192 = new Produto(null, "Esfiha de Carne", 4.0, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u2);
		Produto p193 = new Produto(null, "Creme de Papaia com Cassis", 8.0, TipoArmazenamento.ESTOCAVEL, 35, TipoProduto.DOCE, Status.ATIVO, "quero congelado", u2);
		Produto p194 = new Produto(null, "Risoto de Camarão", 12.0, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.PRATO, Status.ATIVO, "Usar camarões frescos", u2);
		Produto p195 = new Produto(null, "Pão de Alho", 3.0, TipoArmazenamento.ESTOCAVEL, 30, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u2);
		Produto p196 = new Produto(null, "Sorvete de Chocolate", 6.0, TipoArmazenamento.ESTOCAVEL, 25, TipoProduto.DOCE, Status.ATIVO, "quero congelado", u2);
		Produto p197 = new Produto(null, "Pão Francês", 1.5, TipoArmazenamento.ESTOCAVEL, 40, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u2);
		Produto p198 = new Produto(null, "Torta de Maçã", 5.0, TipoArmazenamento.ESTOCAVEL, 45, TipoProduto.DOCE, Status.ATIVO, "quero congelado", u2);
		Produto p199 = new Produto(null, "Coxinha de Frango", 4.0, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u2);
		Produto p200 = new Produto(null, "Torta de Frango", 6.0, TipoArmazenamento.ESTOCAVEL, 30, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u2);
		Produto p201 = new Produto(null, "Bolo de Cenoura", 5.0, TipoArmazenamento.ESTOCAVEL, 40, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u3);
		Produto p202 = new Produto(null, "Salada de Frutas", 4.5, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.PRATO, Status.ATIVO, "quero congelado", u3);
		Produto p203 = new Produto(null, "Sorvete de Morango", 6.0, TipoArmazenamento.ESTOCAVEL, 25, TipoProduto.DOCE, Status.ATIVO, "quero congelado", u3);
		Produto p204 = new Produto(null, "Lasanha de Frango", 7.0, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.PRATO, Status.ATIVO, "Usar massa de pastel", u3);
		Produto p205 = new Produto(null, "Pão de Queijo Recheado", 4.5, TipoArmazenamento.ESTOCAVEL, 40, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u3);
		Produto p206 = new Produto(null, "Brigadeiro", 2.5, TipoArmazenamento.ESTOCAVEL, 30, TipoProduto.DOCE, Status.ATIVO, "quero congelado", u3);
		Produto p207 = new Produto(null, "Feijoada de Frutos do Mar", 12.0, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.PRATO, Status.ATIVO, "Usar frutos do mar frescos", u3);
		Produto p208 = new Produto(null, "Pão Integral", 3.5, TipoArmazenamento.ESTOCAVEL, 40, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u3);
		Produto p209 = new Produto(null, "Torta de Chocolate", 5.5, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.DOCE, Status.ATIVO, "Embalagem de plástico", u3);
		Produto p210 = new Produto(null, "Pastel de Queijo", 4.0, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u3);
		Produto p211 = new Produto(null, "Sorvete de Creme", 5.0, TipoArmazenamento.ESTOCAVEL, 35, TipoProduto.DOCE, Status.ATIVO, "quero congelado", u3);
		Produto p212 = new Produto(null, "Risoto de Funghi", 11.0, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.PRATO, Status.ATIVO, "Usar cogumelos frescos", u3);
		Produto p213 = new Produto(null, "Pão de Alho com Queijo", 3.5, TipoArmazenamento.ESTOCAVEL, 30, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u3);
		Produto p214 = new Produto(null, "Sorvete de Flocos", 5.5, TipoArmazenamento.ESTOCAVEL, 25, TipoProduto.DOCE, Status.ATIVO, "quero congelado", u3);
		Produto p215 = new Produto(null, "Feijoada Vegetariana", 10.0, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.PRATO, Status.ATIVO, "Substituir carne por legumes", u3);
		Produto p216 = new Produto(null, "Pão de Queijo Tradicional", 4.0, TipoArmazenamento.ESTOCAVEL, 40, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u3);
		Produto p217 = new Produto(null, "Pudim de Leite", 5.0, TipoArmazenamento.ESTOCAVEL, 30, TipoProduto.DOCE, Status.ATIVO, "quero congelado", u3);
		Produto p218 = new Produto(null, "Strogonoff de Carne", 11.0, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.PRATO, Status.ATIVO, "Usar carne de qualidade", u3);
		Produto p219 = new Produto(null, "Pão de Batata com Recheio", 4.5, TipoArmazenamento.ESTOCAVEL, 35, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u3);
		Produto p220 = new Produto(null, "Mousse de Maracujá", 5.5, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.DOCE, Status.ATIVO, "quero congelado", u3);
		Produto p221 = new Produto(null, "Risoto de Camarão", 13.0, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.PRATO, Status.ATIVO, "Usar camarão fresco", u3);
		Produto p222 = new Produto(null, "Pão de Milho", 3.5, TipoArmazenamento.ESTOCAVEL, 40, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u3);
		Produto p223 = new Produto(null, "Torta de Morango", 6.0, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.DOCE, Status.ATIVO, "Embalagem de plástico", u3);
		Produto p224 = new Produto(null, "Pastel de Carne", 4.0, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u3);
		Produto p225 = new Produto(null, "Sorvete de Chocolate", 5.0, TipoArmazenamento.ESTOCAVEL, 35, TipoProduto.DOCE, Status.ATIVO, "quero congelado", u3);
		Produto p226 = new Produto(null, "Macarrão ao Molho Branco", 10.0, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.PRATO, Status.ATIVO, "Usar queijo parmesão", u3);
		Produto p227 = new Produto(null, "Pão de Calabresa", 3.5, TipoArmazenamento.ESTOCAVEL, 30, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u3);
		Produto p228 = new Produto(null, "Sorvete de Limão", 5.5, TipoArmazenamento.ESTOCAVEL, 25, TipoProduto.DOCE, Status.ATIVO, "quero congelado", u3);
		Produto p229 = new Produto(null, "Bacalhoada", 12.0, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.PRATO, Status.ATIVO, "Usar bacalhau dessalgado", u3);
		Produto p230 = new Produto(null, "Pão de Ervas Finas", 4.0, TipoArmazenamento.ESTOCAVEL, 35, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u3);
		Produto p231 = new Produto(null, "Torta de Limão", 5.5, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.DOCE, Status.ATIVO, "Embalagem de plástico", u3);
		Produto p232 = new Produto(null, "Pastel de Palmito", 4.0, TipoArmazenamento.ESTOCAVEL, 25, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u3);
		Produto p233 = new Produto(null, "Sorvete de Abacaxi", 5.0, TipoArmazenamento.ESTOCAVEL, 40, TipoProduto.DOCE, Status.ATIVO, "quero congelado", u3);
		Produto p234 = new Produto(null, "Strogonoff de Frango", 11.0, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.PRATO, Status.ATIVO, "Usar frango desfiado", u3);
		Produto p235 = new Produto(null, "Pão de Linguiça", 4.0, TipoArmazenamento.ESTOCAVEL, 30, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u3);
		Produto p236 = new Produto(null, "Sorvete de Coco", 5.5, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.DOCE, Status.ATIVO, "quero congelado", u3);
		Produto p237 = new Produto(null, "Risoto de Queijo", 11.0, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.PRATO, Status.ATIVO, "Usar queijo parmesão", u3);
		Produto p238 = new Produto(null, "Pão de Queijo com Recheio", 4.5, TipoArmazenamento.ESTOCAVEL, 35, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u3);
		Produto p239 = new Produto(null, "Torta de Limão com Chocolate", 6.0, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.DOCE, Status.ATIVO, "Embalagem de plástico", u3);
		Produto p240 = new Produto(null, "Pastel de Frango", 4.0, TipoArmazenamento.ESTOCAVEL, 25, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u3);
		Produto p241 = new Produto(null, "Sorvete de Leite Condensado", 5.0, TipoArmazenamento.ESTOCAVEL, 40, TipoProduto.DOCE, Status.ATIVO, "quero congelado", u3);
		Produto p242 = new Produto(null, "Espaguete à Bolonhesa", 10.0, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.PRATO, Status.ATIVO, "Usar carne moída de qualidade", u3);
		Produto p243 = new Produto(null, "Pão de Azeitona", 3.5, TipoArmazenamento.ESTOCAVEL, 30, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u3);
		Produto p244 = new Produto(null, "Sorvete de Maracujá", 5.5, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.DOCE, Status.ATIVO, "quero congelado", u3);
		Produto p245 = new Produto(null, "Feijoada de Vegetais", 10.0, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.PRATO, Status.ATIVO, "Substituir carne por legumes", u3);
		Produto p246 = new Produto(null, "Pão de Queijo Tradicional", 4.0, TipoArmazenamento.ESTOCAVEL, 35, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u3);
		Produto p247 = new Produto(null, "Pudim de Leite", 5.0, TipoArmazenamento.ESTOCAVEL, 30, TipoProduto.DOCE, Status.ATIVO, "quero congelado", u3);
		Produto p248 = new Produto(null, "Strogonoff de Carne", 11.0, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.PRATO, Status.ATIVO, "Usar carne de qualidade", u3);
		Produto p249 = new Produto(null, "Pão de Batata com Recheio", 4.5, TipoArmazenamento.ESTOCAVEL, 35, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u3);
		Produto p250 = new Produto(null, "Mousse de Maracujá", 5.5, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.DOCE, Status.ATIVO, "quero congelado", u3);
		Produto p251 = new Produto(null, "Risoto de Camarão", 13.0, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.PRATO, Status.ATIVO, "Usar camarão fresco", u3);
		Produto p252 = new Produto(null, "Pão de Milho", 3.5, TipoArmazenamento.ESTOCAVEL, 40, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u3);
		Produto p253 = new Produto(null, "Torta de Morango", 6.0, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.DOCE, Status.ATIVO, "Embalagem de plástico", u3);
		Produto p254 = new Produto(null, "Pastel de Carne", 4.0, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u3);
		Produto p255 = new Produto(null, "Sorvete de Chocolate", 5.0, TipoArmazenamento.ESTOCAVEL, 35, TipoProduto.DOCE, Status.ATIVO, "quero congelado", u3);
		Produto p256 = new Produto(null, "Macarrão ao Molho Branco", 10.0, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.PRATO, Status.ATIVO, "Usar queijo parmesão", u3);
		Produto p257 = new Produto(null, "Pão de Calabresa", 3.5, TipoArmazenamento.ESTOCAVEL, 30, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u3);
		Produto p258 = new Produto(null, "Sorvete de Limão", 5.5, TipoArmazenamento.ESTOCAVEL, 25, TipoProduto.DOCE, Status.ATIVO, "quero congelado", u3);
		Produto p259 = new Produto(null, "Risoto de Queijo", 11.0, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.PRATO, Status.ATIVO, "Usar queijo parmesão", u3);
		Produto p260 = new Produto(null, "Pão de Queijo com Recheio", 4.5, TipoArmazenamento.ESTOCAVEL, 35, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u3);
		Produto p261 = new Produto(null, "Torta de Limão com Chocolate", 6.0, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.DOCE, Status.ATIVO, "Embalagem de plástico", u3);
		Produto p262 = new Produto(null, "Pastel de Frango", 4.0, TipoArmazenamento.ESTOCAVEL, 25, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u3);
		Produto p263 = new Produto(null, "Sorvete de Leite Condensado", 5.0, TipoArmazenamento.ESTOCAVEL, 40, TipoProduto.DOCE, Status.ATIVO, "quero congelado", u3);
		Produto p264 = new Produto(null, "Espaguete à Bolonhesa", 10.0, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.PRATO, Status.ATIVO, "Usar carne moída de qualidade", u3);
		Produto p265 = new Produto(null, "Pão de Azeitona", 3.5, TipoArmazenamento.ESTOCAVEL, 30, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u3);
		Produto p266 = new Produto(null, "Sorvete de Maracujá", 5.5, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.DOCE, Status.ATIVO, "quero congelado", u3);
		Produto p267 = new Produto(null, "Feijoada de Vegetais", 10.0, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.PRATO, Status.ATIVO, "Substituir carne por legumes", u3);
		Produto p268 = new Produto(null, "Pão de Queijo Tradicional", 4.0, TipoArmazenamento.ESTOCAVEL, 35, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u3);
		Produto p269 = new Produto(null, "Pudim de Leite", 5.0, TipoArmazenamento.ESTOCAVEL, 30, TipoProduto.DOCE, Status.ATIVO, "quero congelado", u3);
		Produto p270 = new Produto(null, "Strogonoff de Carne", 11.0, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.PRATO, Status.ATIVO, "Usar carne de qualidade", u3);
		Produto p271 = new Produto(null, "Pão de Batata com Recheio", 4.5, TipoArmazenamento.ESTOCAVEL, 35, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u3);
		Produto p272 = new Produto(null, "Mousse de Maracujá", 5.5, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.DOCE, Status.ATIVO, "quero congelado", u3);
		Produto p273 = new Produto(null, "Risoto de Camarão", 13.0, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.PRATO, Status.ATIVO, "Usar camarão fresco", u3);
		Produto p274 = new Produto(null, "Pão de Milho", 3.5, TipoArmazenamento.ESTOCAVEL, 40, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u3);
		Produto p275 = new Produto(null, "Torta de Morango", 6.0, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.DOCE, Status.ATIVO, "Embalagem de plástico", u3);
		Produto p276 = new Produto(null, "Pastel de Carne", 4.0, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u3);
		Produto p277 = new Produto(null, "Sorvete de Chocolate", 5.0, TipoArmazenamento.ESTOCAVEL, 35, TipoProduto.DOCE, Status.ATIVO, "quero congelado", u3);
		Produto p278 = new Produto(null, "Macarrão ao Molho Branco", 10.0, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.PRATO, Status.ATIVO, "Usar queijo parmesão", u3);
		Produto p279 = new Produto(null, "Pão de Calabresa", 3.5, TipoArmazenamento.ESTOCAVEL, 30, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u3);
		Produto p280 = new Produto(null, "Sorvete de Limão", 5.5, TipoArmazenamento.ESTOCAVEL, 25, TipoProduto.DOCE, Status.ATIVO, "quero congelado", u3);
		Produto p281 = new Produto(null, "Risoto de Queijo", 11.0, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.PRATO, Status.ATIVO, "Usar queijo parmesão", u3);
		Produto p282 = new Produto(null, "Pão de Queijo com Recheio", 4.5, TipoArmazenamento.ESTOCAVEL, 35, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u3);
		Produto p283 = new Produto(null, "Torta de Limão com Chocolate", 6.0, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.DOCE, Status.ATIVO, "Embalagem de plástico", u3);
		Produto p284 = new Produto(null, "Pastel de Frango", 4.0, TipoArmazenamento.ESTOCAVEL, 25, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u3);
		Produto p285 = new Produto(null, "Sorvete de Leite Condensado", 5.0, TipoArmazenamento.ESTOCAVEL, 40, TipoProduto.DOCE, Status.ATIVO, "quero congelado", u3);
		Produto p286 = new Produto(null, "Espaguete à Bolonhesa", 10.0, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.PRATO, Status.ATIVO, "Usar carne moída de qualidade", u3);
		Produto p287 = new Produto(null, "Pão de Azeitona", 3.5, TipoArmazenamento.ESTOCAVEL, 30, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u3);
		Produto p288 = new Produto(null, "Sorvete de Maracujá", 5.5, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.DOCE, Status.ATIVO, "quero congelado", u3);
		Produto p289 = new Produto(null, "Feijoada de Vegetais", 10.0, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.PRATO, Status.ATIVO, "Substituir carne por legumes", u3);
		Produto p290 = new Produto(null, "Pão de Queijo Tradicional", 4.0, TipoArmazenamento.ESTOCAVEL, 35, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u3);
		Produto p291 = new Produto(null, "Pudim de Leite", 5.0, TipoArmazenamento.ESTOCAVEL, 30, TipoProduto.DOCE, Status.ATIVO, "quero congelado", u3);
		Produto p292 = new Produto(null, "Strogonoff de Carne", 11.0, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.PRATO, Status.ATIVO, "Usar carne de qualidade", u3);
		Produto p293 = new Produto(null, "Pão de Batata com Recheio", 4.5, TipoArmazenamento.ESTOCAVEL, 35, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u3);
		Produto p294 = new Produto(null, "Mousse de Maracujá", 5.5, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.DOCE, Status.ATIVO, "quero congelado", u3);
		Produto p295 = new Produto(null, "Risoto de Camarão", 13.0, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.PRATO, Status.ATIVO, "Usar camarão fresco", u3);
		Produto p296 = new Produto(null, "Pão de Milho", 3.5, TipoArmazenamento.ESTOCAVEL, 40, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u3);
		Produto p297 = new Produto(null, "Torta de Morango", 6.0, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.DOCE, Status.ATIVO, "Embalagem de plástico", u3);
		Produto p298 = new Produto(null, "Pastel de Carne", 4.0, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u3);
		Produto p299 = new Produto(null, "Sorvete de Chocolate", 5.0, TipoArmazenamento.ESTOCAVEL, 35, TipoProduto.DOCE, Status.ATIVO, "quero congelado", u3);
		Produto p300 = new Produto(null, "Macarrão ao Molho Branco", 10.0, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.PRATO, Status.ATIVO, "Usar queijo parmesão", u3);*/

		
		produtoRepository.saveAll(Arrays.asList(
			    p01, p02, p03, p04, p05, p06, p07, p08, p09, p10,
			    p11, p12, p13, p14, p15, p16, p17, p18, p19, p20,
			    p21, p22, p23, p24, p25, p26, p27, p28, p29, p30,
			    p31, p32, p33, p34, p35, p36, p37, p38, p39, p40,
			    p41, p42, p43, p44, p45, p46, p47, p48, p49, p50,
			    p51, p52, p53, p54, p55, p56, p57, p58, p59, p60,
			    p61, p62, p63, p64, p65, p66, p67, p68, p69, p70,
			    p71, p72, p73, p74, p75, p76, p77, p78, p79, p80,
			    p81, p82, p83, p84, p85, p86, p87, p88, p89, p90,
			    p91, p92, p93, p94, p95, p96, p97, p98, p99, p100/*,
			    p101, p102, p103, p104, p105, p106, p107, p108, p109, p110,
			    p111, p112, p113, p114, p115, p116, p117, p118, p119, p120,
			    p121, p122, p123, p124, p125, p126, p127, p128, p129, p130,
			    p131, p132, p133, p134, p135, p136, p137, p138, p139, p140,
			    p141, p142, p143, p144, p145, p146, p147, p148, p149, p150,
			    p151, p152, p153, p154, p155, p156, p157, p158, p159, p160,
			    p161, p162, p163, p164, p165, p166, p167, p168, p169, p170,
			    p171, p172, p173, p174, p175, p176, p177, p178, p179, p180,
			    p181, p182, p183, p184, p185, p186, p187, p188, p189, p190,
			    p191, p192, p193, p194, p195, p196, p197, p198, p199, p200,
			    p201, p202, p203, p204, p205, p206, p207, p208, p209, p210,
			    p211, p212, p213, p214, p215, p216, p217, p218, p219, p220,
			    p221, p222, p223, p224, p225, p226, p227, p228, p229, p230,
			    p231, p232, p233, p234, p235, p236, p237, p238, p239, p240,
			    p241, p242, p243, p244, p245, p246, p247, p248, p249, p250,
			    p251, p252, p253, p254, p255, p256, p257, p258, p259, p260,
			    p261, p262, p263, p264, p265, p266, p267, p268, p269, p270,
			    p271, p272, p273, p274, p275, p276, p277, p278, p279, p280,
			    p281, p282, p283, p284, p285, p286, p287, p288, p289, p290,
			    p291, p292, p293, p294, p295, p296, p297, p298, p299, p300*/
			));

		
		
		//Teste Despesa
		Despesa d01 = new Despesa(null, "Energia", Categoria.FIXA, 250.0, "Coelba", Instant.now(), Status.ATIVO, u1);
		Despesa d02 = new Despesa(null, "Água e Esgoto", Categoria.FIXA, 250.0, "Embasa", Instant.now(), Status.ATIVO, u1);
		Despesa d03 = new Despesa(null, "Internet", Categoria.FIXA, 250.0, "Java.Network", Instant.now(), Status.ATIVO,  u1);
		Despesa d04 = new Despesa(null, "Energia", Categoria.FIXA, 250.0, "Coelba", Instant.now(), Status.ATIVO,  u2);
		Despesa d05 = new Despesa(null, "Água e Esgoto", Categoria.FIXA, 250.0, "Embasa", Instant.now(), Status.ATIVO,  u2);
		Despesa d06 = new Despesa(null, "Internet", Categoria.FIXA, 250.0, "Java.Network", Instant.now(), Status.ATIVO,  u2);
		Despesa d07 = new Despesa(null, "Energia", Categoria.FIXA, 250.0, "Coelba", Instant.now(), Status.ATIVO,  u3);
		Despesa d08 = new Despesa(null, "Água e Esgoto", Categoria.FIXA, 250.0, "Embasa", Instant.now(), Status.ATIVO,  u3);
		Despesa d09 = new Despesa(null, "Internet", Categoria.FIXA, 250.0, "Java.Network", Instant.now(), Status.ATIVO,  u3);
		Despesa d10 = new Despesa(null, "Energia", Categoria.FIXA, 250.0, "Coelba", Instant.now(), Status.ATIVO,  u4);
		Despesa d11 = new Despesa(null, "Água e Esgoto", Categoria.FIXA, 250.0, "Embasa", Instant.now(), Status.ATIVO,  u4);
		Despesa d12 = new Despesa(null, "Internet", Categoria.FIXA, 250.0, "Java.Network", Instant.now(), Status.ATIVO,  u4);
		Despesa d13 = new Despesa(null, "Energia", Categoria.FIXA, 250.0, "Coelba", Instant.now(), Status.ATIVO,  u5);
		Despesa d14 = new Despesa(null, "Água e Esgoto", Categoria.FIXA, 250.0, "Embasa", Instant.now(), Status.ATIVO,  u5);
		Despesa d15 = new Despesa(null, "Internet", Categoria.FIXA, 250.0, "Java.Network", Instant.now(), Status.ATIVO,  u5);
		
		despesaRepository.saveAll(Arrays.asList(d01, d02, d03, d04, d05, d06, d07, d08, d09, d10, d11, d12, d13, d14, d15));
		
		
		//Teste Orçamento
		Orcamento o01 = new Orcamento(null, TipoEntrega.RETIRADA, Instant.now(), Instant.now(), Status.ATIVO, FormaPagamento.DINHEIRO, c01.getUsuario(), c01, "Tomba");
		Orcamento o02 = new Orcamento(null, TipoEntrega.RETIRADA, Instant.now(), Instant.now(), Status.ATIVO, FormaPagamento.DINHEIRO, c02.getUsuario(), c02, "Tomba");
		Orcamento o03 = new Orcamento(null, TipoEntrega.RETIRADA, Instant.now(), Instant.now(), Status.ATIVO, FormaPagamento.DINHEIRO, c03.getUsuario(), c03, "Tomba");
		Orcamento o04 = new Orcamento(null, TipoEntrega.RETIRADA, Instant.now(), Instant.now(), Status.ATIVO, FormaPagamento.DINHEIRO, c04.getUsuario(), c04, "Tomba");
		Orcamento o05 = new Orcamento(null, TipoEntrega.RETIRADA, Instant.now(), Instant.now(), Status.ATIVO, FormaPagamento.DINHEIRO, c04.getUsuario(), c05, "Tomba");
		Orcamento o06 = new Orcamento(null, TipoEntrega.RETIRADA, Instant.now(), Instant.now(), Status.ATIVO, FormaPagamento.DINHEIRO, c06.getUsuario(), c06, "Tomba");
		Orcamento o07 = new Orcamento(null, TipoEntrega.RETIRADA, Instant.now(), Instant.now(), Status.ATIVO, FormaPagamento.DINHEIRO, c01.getUsuario(), c01, "Tomba");
		Orcamento o08 = new Orcamento(null, TipoEntrega.RETIRADA, Instant.now(), Instant.now(), Status.ATIVO, FormaPagamento.DINHEIRO, c04.getUsuario(), c04, "Tomba");
		Orcamento o09 = new Orcamento(null, TipoEntrega.RETIRADA, Instant.now(), Instant.now(), Status.ATIVO, FormaPagamento.DINHEIRO, c07.getUsuario(), c07, "Tomba");
		Orcamento o10 = new Orcamento(null, TipoEntrega.RETIRADA, Instant.now(), Instant.now(), Status.ATIVO, FormaPagamento.DINHEIRO, c08.getUsuario(), c08, "Tomba");
		Orcamento o11 = new Orcamento(null, TipoEntrega.RETIRADA, Instant.now(), Instant.now(), Status.ATIVO, FormaPagamento.DINHEIRO, c10.getUsuario(), c10, "Tomba");
		Orcamento o12 = new Orcamento(null, TipoEntrega.RETIRADA, Instant.now(), Instant.now(), Status.ATIVO, FormaPagamento.DINHEIRO, c10.getUsuario(), c10, "Tomba");
		Orcamento o13 = new Orcamento(null, TipoEntrega.RETIRADA, Instant.now(), Instant.now(), Status.ATIVO, FormaPagamento.DINHEIRO, c05.getUsuario(), c05, "Tomba");
		Orcamento o14 = new Orcamento(null, TipoEntrega.RETIRADA, Instant.now(), Instant.now(), Status.ATIVO, FormaPagamento.DINHEIRO, c09.getUsuario(), c09, "Tomba");
		Orcamento o15 = new Orcamento(null, TipoEntrega.RETIRADA, Instant.now(), Instant.now(), Status.ATIVO, FormaPagamento.DINHEIRO, c01.getUsuario(), c01, "Tomba");
		
		orcamentoRepository.saveAll(Arrays.asList(o01, o02, o03, o04, o05, o06, o07, o08, o09, o10, o11, o12, o13, o14, o15));

		ItensOrcamento io01 = new ItensOrcamento(o01, p01, 10, p01.getValor(), 0.0, null);
		ItensOrcamento io02 = new ItensOrcamento(o01, p02, 10, p02.getValor(), 0.0, null);
		ItensOrcamento io03 = new ItensOrcamento(o01, p03, 10, p03.getValor(), 0.0, null);
		ItensOrcamento io04 = new ItensOrcamento(o01, p04, 10, p04.getValor(), 0.0, null);
		ItensOrcamento io05 = new ItensOrcamento(o01, p05, 10, p05.getValor(), 0.0, null);
		ItensOrcamento io06 = new ItensOrcamento(o01, p06, 10, p06.getValor(), 0.0, null);
		ItensOrcamento io07 = new ItensOrcamento(o01, p07, 10, p07.getValor(), 0.0, null);
		ItensOrcamento io08 = new ItensOrcamento(o01, p08, 10, p08.getValor(), 0.0, null);
		ItensOrcamento io09 = new ItensOrcamento(o01, p09, 10, p09.getValor(), 0.0, null);
		ItensOrcamento io10 = new ItensOrcamento(o01, p10, 10, p10.getValor(), 0.0, null);
		ItensOrcamento io11 = new ItensOrcamento(o01, p11, 10, p11.getValor(), 0.0, null);
		ItensOrcamento io12 = new ItensOrcamento(o01, p12, 10, p12.getValor(), 0.0, null);
		ItensOrcamento io13 = new ItensOrcamento(o01, p13, 10, p13.getValor(), 0.0, null);
		ItensOrcamento io14 = new ItensOrcamento(o01, p14, 10, p14.getValor(), 0.0, null);
		ItensOrcamento io15 = new ItensOrcamento(o01, p15, 10, p15.getValor(), 0.0, null);
		ItensOrcamento io16 = new ItensOrcamento(o01, p16, 10, p16.getValor(), 0.0, null);
		ItensOrcamento io17 = new ItensOrcamento(o01, p17, 10, p17.getValor(), 0.0, null);
		ItensOrcamento io18 = new ItensOrcamento(o01, p18, 10, p18.getValor(), 0.0, null);
		ItensOrcamento io19 = new ItensOrcamento(o01, p19, 10, p19.getValor(), 0.0, null);
		ItensOrcamento io20 = new ItensOrcamento(o01, p20, 10, p20.getValor(), 0.0, null);
		ItensOrcamento io21 = new ItensOrcamento(o01, p21, 10, p21.getValor(), 0.0, null);
		ItensOrcamento io22 = new ItensOrcamento(o01, p22, 10, p22.getValor(), 0.0, null);
		ItensOrcamento io23 = new ItensOrcamento(o01, p23, 10, p23.getValor(), 0.0, null);
		ItensOrcamento io24 = new ItensOrcamento(o01, p24, 10, p24.getValor(), 0.0, null);
		ItensOrcamento io25 = new ItensOrcamento(o01, p25, 10, p25.getValor(), 0.0, null);
		ItensOrcamento io26 = new ItensOrcamento(o01, p26, 10, p26.getValor(), 0.0, null);
		ItensOrcamento io27 = new ItensOrcamento(o01, p27, 10, p27.getValor(), 0.0, null);
		ItensOrcamento io28 = new ItensOrcamento(o01, p28, 10, p28.getValor(), 0.0, null);
		ItensOrcamento io29 = new ItensOrcamento(o01, p29, 10, p29.getValor(), 0.0, null);
		ItensOrcamento io30 = new ItensOrcamento(o01, p30, 10, p30.getValor(), 0.0, null);
		ItensOrcamento io31 = new ItensOrcamento(o01, p31, 10, p31.getValor(), 0.0, null);
		ItensOrcamento io32 = new ItensOrcamento(o01, p32, 10, p32.getValor(), 0.0, null);
		ItensOrcamento io33 = new ItensOrcamento(o01, p33, 10, p33.getValor(), 0.0, null);
		ItensOrcamento io34 = new ItensOrcamento(o01, p34, 10, p34.getValor(), 0.0, null);
		ItensOrcamento io35 = new ItensOrcamento(o01, p35, 10, p35.getValor(), 0.0, null);
		ItensOrcamento io36 = new ItensOrcamento(o01, p36, 10, p36.getValor(), 0.0, null);
		ItensOrcamento io37 = new ItensOrcamento(o01, p37, 10, p37.getValor(), 0.0, null);
		ItensOrcamento io38 = new ItensOrcamento(o01, p38, 10, p38.getValor(), 0.0, null);
		ItensOrcamento io39 = new ItensOrcamento(o01, p39, 10, p39.getValor(), 0.0, null);
		ItensOrcamento io40 = new ItensOrcamento(o01, p40, 10, p40.getValor(), 0.0, null);
		ItensOrcamento io41 = new ItensOrcamento(o01, p41, 10, p41.getValor(), 0.0, null);
		ItensOrcamento io42 = new ItensOrcamento(o01, p42, 10, p42.getValor(), 0.0, null);
		ItensOrcamento io43 = new ItensOrcamento(o01, p43, 10, p43.getValor(), 0.0, null);
		ItensOrcamento io44 = new ItensOrcamento(o01, p44, 10, p44.getValor(), 0.0, null);
		ItensOrcamento io45 = new ItensOrcamento(o01, p45, 10, p45.getValor(), 0.0, null);
		ItensOrcamento io46 = new ItensOrcamento(o01, p46, 10, p46.getValor(), 0.0, null);
		ItensOrcamento io47 = new ItensOrcamento(o01, p47, 10, p47.getValor(), 0.0, null);
		ItensOrcamento io48 = new ItensOrcamento(o01, p48, 10, p48.getValor(), 0.0, null);
		ItensOrcamento io49 = new ItensOrcamento(o01, p49, 10, p49.getValor(), 0.0, null);
		ItensOrcamento io50 = new ItensOrcamento(o01, p50, 10, p50.getValor(), 0.0, null);
		ItensOrcamento io51 = new ItensOrcamento(o01, p51, 10, p51.getValor(), 0.0, null);
		ItensOrcamento io52 = new ItensOrcamento(o01, p52, 10, p52.getValor(), 0.0, null);
		ItensOrcamento io53 = new ItensOrcamento(o01, p53, 10, p53.getValor(), 0.0, null);
		ItensOrcamento io54 = new ItensOrcamento(o01, p54, 10, p54.getValor(), 0.0, null);
		ItensOrcamento io55 = new ItensOrcamento(o01, p55, 10, p55.getValor(), 0.0, null);
		ItensOrcamento io56 = new ItensOrcamento(o01, p56, 10, p56.getValor(), 0.0, null);
		ItensOrcamento io57 = new ItensOrcamento(o01, p57, 10, p57.getValor(), 0.0, null);
		ItensOrcamento io58 = new ItensOrcamento(o01, p58, 10, p58.getValor(), 0.0, null);
		ItensOrcamento io59 = new ItensOrcamento(o01, p59, 10, p59.getValor(), 0.0, null);
		ItensOrcamento io60 = new ItensOrcamento(o01, p60, 10, p60.getValor(), 0.0, null);
		ItensOrcamento io61 = new ItensOrcamento(o01, p61, 10, p61.getValor(), 0.0, null);
		ItensOrcamento io62 = new ItensOrcamento(o01, p62, 10, p62.getValor(), 0.0, null);
		ItensOrcamento io63 = new ItensOrcamento(o01, p63, 10, p63.getValor(), 0.0, null);
		ItensOrcamento io64 = new ItensOrcamento(o01, p64, 10, p64.getValor(), 0.0, null);
		ItensOrcamento io65 = new ItensOrcamento(o01, p65, 10, p65.getValor(), 0.0, null);
		ItensOrcamento io66 = new ItensOrcamento(o01, p66, 10, p66.getValor(), 0.0, null);
		ItensOrcamento io67 = new ItensOrcamento(o01, p67, 10, p67.getValor(), 0.0, null);
		ItensOrcamento io68 = new ItensOrcamento(o01, p68, 10, p68.getValor(), 0.0, null);
		ItensOrcamento io69 = new ItensOrcamento(o01, p69, 10, p69.getValor(), 0.0, null);
		ItensOrcamento io70 = new ItensOrcamento(o01, p70, 10, p70.getValor(), 0.0, null);
		ItensOrcamento io71 = new ItensOrcamento(o01, p71, 10, p71.getValor(), 0.0, null);
		ItensOrcamento io72 = new ItensOrcamento(o01, p72, 10, p72.getValor(), 0.0, null);
		ItensOrcamento io73 = new ItensOrcamento(o01, p73, 10, p73.getValor(), 0.0, null);
		ItensOrcamento io74 = new ItensOrcamento(o01, p74, 10, p74.getValor(), 0.0, null);
		ItensOrcamento io75 = new ItensOrcamento(o01, p75, 10, p75.getValor(), 0.0, null);
		ItensOrcamento io76 = new ItensOrcamento(o01, p76, 10, p76.getValor(), 0.0, null);
		ItensOrcamento io77 = new ItensOrcamento(o01, p77, 10, p77.getValor(), 0.0, null);
		ItensOrcamento io78 = new ItensOrcamento(o01, p78, 10, p78.getValor(), 0.0, null);
		ItensOrcamento io79 = new ItensOrcamento(o01, p79, 10, p79.getValor(), 0.0, null);
		ItensOrcamento io80 = new ItensOrcamento(o01, p80, 10, p80.getValor(), 0.0, null);
		ItensOrcamento io81 = new ItensOrcamento(o01, p81, 10, p81.getValor(), 0.0, null);
		ItensOrcamento io82 = new ItensOrcamento(o01, p82, 10, p82.getValor(), 0.0, null);
		ItensOrcamento io83 = new ItensOrcamento(o01, p83, 10, p83.getValor(), 0.0, null);
		ItensOrcamento io84 = new ItensOrcamento(o01, p84, 10, p84.getValor(), 0.0, null);
		ItensOrcamento io85 = new ItensOrcamento(o01, p85, 10, p85.getValor(), 0.0, null);
		ItensOrcamento io86 = new ItensOrcamento(o01, p86, 10, p86.getValor(), 0.0, null);
		ItensOrcamento io87 = new ItensOrcamento(o01, p87, 10, p87.getValor(), 0.0, null);
		ItensOrcamento io88 = new ItensOrcamento(o01, p88, 10, p88.getValor(), 0.0, null);
		ItensOrcamento io89 = new ItensOrcamento(o01, p89, 10, p89.getValor(), 0.0, null);
		ItensOrcamento io90 = new ItensOrcamento(o01, p90, 10, p90.getValor(), 0.0, null);
		ItensOrcamento io91 = new ItensOrcamento(o01, p91, 10, p91.getValor(), 0.0, null);
		ItensOrcamento io92 = new ItensOrcamento(o01, p92, 10, p92.getValor(), 0.0, null);
		ItensOrcamento io93 = new ItensOrcamento(o01, p93, 10, p93.getValor(), 0.0, null);
		ItensOrcamento io94 = new ItensOrcamento(o01, p94, 10, p94.getValor(), 0.0, null);
		ItensOrcamento io95 = new ItensOrcamento(o01, p95, 10, p95.getValor(), 0.0, null);
		ItensOrcamento io96 = new ItensOrcamento(o01, p96, 10, p96.getValor(), 0.0, null);
		ItensOrcamento io97 = new ItensOrcamento(o01, p97, 10, p97.getValor(), 0.0, null);
		ItensOrcamento io98 = new ItensOrcamento(o01, p98, 10, p98.getValor(), 0.0, null);
		ItensOrcamento io99 = new ItensOrcamento(o01, p99, 10, p99.getValor(), 0.0, null);
		ItensOrcamento io100 = new ItensOrcamento(o01, p100, 10, p100.getValor(), 0.0, null);

		
		itensOrcamentoRepository.saveAll(Arrays.asList(
			    io01, io02, io03, io04, io05, io06, io07, io08, io09, io10,
			    io11, io12, io13, io14, io15, io16, io17, io18, io19, io20,
			    io21, io22, io23, io24, io25, io26, io27, io28, io29, io30,
			    io31, io32, io33, io34, io35, io36, io37, io38, io39, io40,
			    io41, io42, io43, io44, io45, io46, io47, io48, io49, io50,
			    io51, io52, io53, io54, io55, io56, io57, io58, io59, io60,
			    io61, io62, io63, io64, io65, io66, io67, io68, io69, io70,
			    io71, io72, io73, io74, io75, io76, io77, io78, io79, io80,
			    io81, io82, io83, io84, io85, io86, io87, io88, io89, io90,
			    io91, io92, io93, io94, io95, io96, io97, io98, io99, io100
			));
		
		//Teste Pedido

		Pedido pd01 = new Pedido(null, o01.getTotal(), o01.getTipoEntrega(), o01.getDataOrcamento(), Instant.now(), Status.ATIVO, FormaPagamento.DINHEIRO, StatusPedido.EM_FILA_PREPARACAO, StatusPagamentoPedido.INTEGRALMENTE_PAGO, o01.getUsuario(), o01.getCliente(), o01);
		Pedido pd02 = new Pedido(null, o02.getTotal(), o02.getTipoEntrega(), o02.getDataOrcamento(), Instant.now(), Status.ATIVO, FormaPagamento.DINHEIRO, StatusPedido.EM_FILA_PREPARACAO, StatusPagamentoPedido.INTEGRALMENTE_PAGO, o02.getUsuario(), o02.getCliente(), o02);
		Pedido pd03 = new Pedido(null, o03.getTotal(), o03.getTipoEntrega(), o03.getDataOrcamento(), Instant.now(), Status.ATIVO, FormaPagamento.DINHEIRO, StatusPedido.EM_FILA_PREPARACAO, StatusPagamentoPedido.INTEGRALMENTE_PAGO, o03.getUsuario(), o03.getCliente(), o03);
		Pedido pd04 = new Pedido(null, o04.getTotal(), o04.getTipoEntrega(), o04.getDataOrcamento(), Instant.now(), Status.ATIVO, FormaPagamento.DINHEIRO, StatusPedido.EM_FILA_PREPARACAO, StatusPagamentoPedido.INTEGRALMENTE_PAGO, o04.getUsuario(), o04.getCliente(), o04);
		Pedido pd05 = new Pedido(null, o05.getTotal(), o05.getTipoEntrega(), o05.getDataOrcamento(), Instant.now(), Status.ATIVO, FormaPagamento.DINHEIRO, StatusPedido.EM_FILA_PREPARACAO, StatusPagamentoPedido.INTEGRALMENTE_PAGO, o05.getUsuario(), o05.getCliente(), o05);
		Pedido pd06 = new Pedido(null, o06.getTotal(), o06.getTipoEntrega(), o06.getDataOrcamento(), Instant.now(), Status.ATIVO, FormaPagamento.DINHEIRO, StatusPedido.EM_FILA_PREPARACAO, StatusPagamentoPedido.INTEGRALMENTE_PAGO, o06.getUsuario(), o06.getCliente(), o06);
		Pedido pd07 = new Pedido(null, o07.getTotal(), o07.getTipoEntrega(), o07.getDataOrcamento(), Instant.now(), Status.ATIVO, FormaPagamento.DINHEIRO, StatusPedido.EM_FILA_PREPARACAO, StatusPagamentoPedido.INTEGRALMENTE_PAGO, o07.getUsuario(), o07.getCliente(), o07);
		Pedido pd08 = new Pedido(null, o08.getTotal(), o08.getTipoEntrega(), o08.getDataOrcamento(), Instant.now(), Status.ATIVO, FormaPagamento.DINHEIRO, StatusPedido.EM_FILA_PREPARACAO, StatusPagamentoPedido.INTEGRALMENTE_PAGO, o08.getUsuario(), o08.getCliente(), o08);
		Pedido pd09 = new Pedido(null, o09.getTotal(), o09.getTipoEntrega(), o09.getDataOrcamento(), Instant.now(), Status.ATIVO, FormaPagamento.DINHEIRO, StatusPedido.EM_FILA_PREPARACAO, StatusPagamentoPedido.INTEGRALMENTE_PAGO, o09.getUsuario(), o09.getCliente(), o09);
		Pedido pd10 = new Pedido(null, o10.getTotal(), o10.getTipoEntrega(), o10.getDataOrcamento(), Instant.now(), Status.ATIVO, FormaPagamento.DINHEIRO, StatusPedido.EM_FILA_PREPARACAO, StatusPagamentoPedido.INTEGRALMENTE_PAGO, o10.getUsuario(), o10.getCliente(), o10);
		Pedido pd11 = new Pedido(null, o11.getTotal(), o11.getTipoEntrega(), o11.getDataOrcamento(), Instant.now(), Status.ATIVO, FormaPagamento.DINHEIRO, StatusPedido.EM_FILA_PREPARACAO, StatusPagamentoPedido.INTEGRALMENTE_PAGO, o11.getUsuario(), o11.getCliente(), o11);
		Pedido pd12 = new Pedido(null, o12.getTotal(), o12.getTipoEntrega(), o12.getDataOrcamento(), Instant.now(), Status.ATIVO, FormaPagamento.DINHEIRO, StatusPedido.EM_FILA_PREPARACAO, StatusPagamentoPedido.INTEGRALMENTE_PAGO, o12.getUsuario(), o12.getCliente(), o12);
		Pedido pd13 = new Pedido(null, o13.getTotal(), o13.getTipoEntrega(), o13.getDataOrcamento(), Instant.now(), Status.ATIVO, FormaPagamento.DINHEIRO, StatusPedido.EM_FILA_PREPARACAO, StatusPagamentoPedido.INTEGRALMENTE_PAGO, o13.getUsuario(), o13.getCliente(), o13);
		Pedido pd14 = new Pedido(null, o14.getTotal(), o14.getTipoEntrega(), o14.getDataOrcamento(), Instant.now(), Status.ATIVO, FormaPagamento.DINHEIRO, StatusPedido.EM_FILA_PREPARACAO, StatusPagamentoPedido.INTEGRALMENTE_PAGO, o14.getUsuario(), o14.getCliente(), o14);
		Pedido pd15 = new Pedido(null, o15.getTotal(), o15.getTipoEntrega(), o15.getDataOrcamento(), Instant.now(), Status.ATIVO, FormaPagamento.DINHEIRO, StatusPedido.EM_FILA_PREPARACAO, StatusPagamentoPedido.INTEGRALMENTE_PAGO, o15.getUsuario(), o15.getCliente(), o15);
		
		pedidoRepository.saveAll(Arrays.asList(pd01, pd02, pd03, pd04, pd05, pd06, pd07, pd08, pd09, pd10, pd11, pd12, pd13, pd14, pd15));
		
		MovimentacaoEstoque mv01 = new MovimentacaoEstoque(null, 20, Instant.now(), p15);
		
		movimentacaoEstoqueRepository.saveAll(Arrays.asList(mv01));
		
		//Teste Pagamento
		
		Pagamento pg01 = new Pagamento(null, 957.90, Instant.now(), FormaPagamento.DINHEIRO, StatusPagamento.PAGO, pd01);
		Pagamento pg02 = new Pagamento(null, 478.95, Instant.now(), FormaPagamento.DINHEIRO, StatusPagamento.PAGO, pd02);
		Pagamento pg03 = new Pagamento(null, 478.95, Instant.now(), FormaPagamento.DINHEIRO, StatusPagamento.PAGO, pd02);
		Pagamento pg04 = new Pagamento(null, 319.30, Instant.now(), FormaPagamento.DINHEIRO, StatusPagamento.PAGO, pd03);
		Pagamento pg05 = new Pagamento(null, 319.30, Instant.now(), FormaPagamento.DINHEIRO, StatusPagamento.PAGO, pd03);
		Pagamento pg06 = new Pagamento(null, 319.30, Instant.now(), FormaPagamento.DINHEIRO, StatusPagamento.PAGO, pd03);
		Pagamento pg07 = new Pagamento(null, 957.90, Instant.now(), FormaPagamento.DINHEIRO, StatusPagamento.PAGO, pd04);
		Pagamento pg08 = new Pagamento(null, 957.90, Instant.now(), FormaPagamento.DINHEIRO, StatusPagamento.PAGO, pd05);
		Pagamento pg09 = new Pagamento(null, 191.58, Instant.now(), FormaPagamento.DINHEIRO, StatusPagamento.PAGO, pd06);
		Pagamento pg10 = new Pagamento(null, 191.58, Instant.now(), FormaPagamento.DINHEIRO, StatusPagamento.PAGO, pd06);
		Pagamento pg11 = new Pagamento(null, 191.58, Instant.now(), FormaPagamento.DINHEIRO, StatusPagamento.PAGO, pd06);
		Pagamento pg12 = new Pagamento(null, 191.58, Instant.now(), FormaPagamento.DINHEIRO, StatusPagamento.PAGO, pd06);
		Pagamento pg13 = new Pagamento(null, 191.58, Instant.now(), FormaPagamento.DINHEIRO, StatusPagamento.PAGO, pd06);
		Pagamento pg14 = new Pagamento(null, 478.95, Instant.now(), FormaPagamento.DINHEIRO, StatusPagamento.PAGO, pd07);
		Pagamento pg15 = new Pagamento(null, 478.95, Instant.now(), FormaPagamento.DINHEIRO, StatusPagamento.PAGO, pd07);
		Pagamento pg16 = new Pagamento(null, 957.90, Instant.now(), FormaPagamento.DINHEIRO, StatusPagamento.PAGO, pd08);
		Pagamento pg17 = new Pagamento(null, 957.90, Instant.now(), FormaPagamento.DINHEIRO, StatusPagamento.PAGO, pd09);
		Pagamento pg18 = new Pagamento(null, 957.90, Instant.now(), FormaPagamento.DINHEIRO, StatusPagamento.PAGO, pd10);
		Pagamento pg19 = new Pagamento(null, 957.90, Instant.now(), FormaPagamento.DINHEIRO, StatusPagamento.PAGO, pd11);
		Pagamento pg20 = new Pagamento(null, 957.90, Instant.now(), FormaPagamento.DINHEIRO, StatusPagamento.PAGO, pd12);
		Pagamento pg21 = new Pagamento(null, 957.90, Instant.now(), FormaPagamento.DINHEIRO, StatusPagamento.PAGO, pd13);
		Pagamento pg22 = new Pagamento(null, 478.95, Instant.now(), FormaPagamento.DINHEIRO, StatusPagamento.PAGO, pd14);
		Pagamento pg23 = new Pagamento(null, 478.95, Instant.now(), FormaPagamento.DINHEIRO, StatusPagamento.PAGO, pd14);
		Pagamento pg24 = new Pagamento(null, 478.95, Instant.now(), FormaPagamento.DINHEIRO, StatusPagamento.PAGO, pd15);
		Pagamento pg25 = new Pagamento(null, 478.95, Instant.now(), FormaPagamento.DINHEIRO, StatusPagamento.PAGO, pd15);
		
		
		pagamentoRepository.saveAll(Arrays.asList(pg01, pg02, pg03, pg04, pg05, pg06, pg07, pg08, pg09, pg10, pg11, pg12, pg13, pg14,
				pg15, pg16, pg17, pg18, pg19, pg20, pg21, pg22, pg23, pg24, pg25));
		
		
		//Teste Receita
		Receita r01 = new Receita(null, Instant.now(), pg01.getPedido().getUsuario(), pg01);
		Receita r02 = new Receita(null, Instant.now(), pg02.getPedido().getUsuario(), pg02);
		Receita r03 = new Receita(null, Instant.now(), pg03.getPedido().getUsuario(), pg03);
		Receita r04 = new Receita(null, Instant.now(), pg04.getPedido().getUsuario(), pg04);
		Receita r05 = new Receita(null, Instant.now(), pg05.getPedido().getUsuario(), pg05);
		Receita r06 = new Receita(null, Instant.now(), pg06.getPedido().getUsuario(), pg06);
		Receita r07 = new Receita(null, Instant.now(), pg07.getPedido().getUsuario(), pg07);
		Receita r08 = new Receita(null, Instant.now(), pg08.getPedido().getUsuario(), pg08);
		Receita r09 = new Receita(null, Instant.now(), pg09.getPedido().getUsuario(), pg09);
		Receita r10 = new Receita(null, Instant.now(), pg10.getPedido().getUsuario(), pg10);
		Receita r11 = new Receita(null, Instant.now(), pg11.getPedido().getUsuario(), pg11);
		Receita r12 = new Receita(null, Instant.now(), pg12.getPedido().getUsuario(), pg12);
		Receita r13 = new Receita(null, Instant.now(), pg13.getPedido().getUsuario(), pg13);
		Receita r14 = new Receita(null, Instant.now(), pg14.getPedido().getUsuario(), pg14);
		Receita r15 = new Receita(null, Instant.now(), pg15.getPedido().getUsuario(), pg15);
		Receita r16 = new Receita(null, Instant.now(), pg16.getPedido().getUsuario(), pg16);
		Receita r17 = new Receita(null, Instant.now(), pg17.getPedido().getUsuario(), pg17);
		Receita r18 = new Receita(null, Instant.now(), pg18.getPedido().getUsuario(), pg18);
		Receita r19 = new Receita(null, Instant.now(), pg19.getPedido().getUsuario(), pg19);
		Receita r20 = new Receita(null, Instant.now(), pg20.getPedido().getUsuario(), pg20);
		Receita r21 = new Receita(null, Instant.now(), pg21.getPedido().getUsuario(), pg21);
		Receita r22 = new Receita(null, Instant.now(), pg22.getPedido().getUsuario(), pg22);
		Receita r23 = new Receita(null, Instant.now(), pg23.getPedido().getUsuario(), pg23);
		Receita r24 = new Receita(null, Instant.now(), pg24.getPedido().getUsuario(), pg24);
		Receita r25 = new Receita(null, Instant.now(), pg25.getPedido().getUsuario(), pg25);
		
		receitaRepository.saveAll(Arrays.asList(r01, r02, r03, r04, r05, r06, r07, r08, r09, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19,
				r20, r21, r22, r23, r24, r25));
	}
}