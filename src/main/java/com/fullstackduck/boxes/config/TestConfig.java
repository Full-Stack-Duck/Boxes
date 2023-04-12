package com.fullstackduck.boxes.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.fullstackduck.boxes.entities.Cliente;
import com.fullstackduck.boxes.entities.Despesa;
import com.fullstackduck.boxes.entities.Estoque;
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
import com.fullstackduck.boxes.entities.enums.StatusCliente;
import com.fullstackduck.boxes.entities.enums.StatusLicenca;
import com.fullstackduck.boxes.entities.enums.StatusPedido;
import com.fullstackduck.boxes.entities.enums.TipoArmazenamento;
import com.fullstackduck.boxes.entities.enums.TipoEntrega;
import com.fullstackduck.boxes.entities.enums.TipoLicenca;
import com.fullstackduck.boxes.entities.enums.TipoProduto;
import com.fullstackduck.boxes.repositories.ClienteRepository;
import com.fullstackduck.boxes.repositories.DespesaRepository;
import com.fullstackduck.boxes.repositories.EstoqueRepository;
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
	private EstoqueRepository estoqueRepository;
	
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
		Usuario u1 = new Usuario(null, "Bruno", "123456789", "bruno@gmail.com", "7512345678", "aaaaaaaa", "Tomba", null, null, Status.ATIVO);
		Usuario u2 = new Usuario(null, "José", "123456789", "jose@gmail.com", "7512345678", "bbbbbbbb", "Tomba", null, null, Status.ATIVO);
		Usuario u3 = new Usuario(null, "Kelvin", "123456789", "kelvin@gmail.com", "7512345678", "cccccccc", "Tomba", null, null, Status.ATIVO);
		Usuario u4 = new Usuario(null, "Lucas", "123456789", "lucas@gmail.com", "7512345678", "dddddddd", "Tomba", null, null, Status.ATIVO);
		Usuario u5 = new Usuario(null, "Vinícius", "123456789", "vinicius@gmail.com", "7512345678", "eeeeeeee", "Tomba", null, null, Status.ATIVO);
		
		usuarioRepository.saveAll(Arrays.asList(u1, u2, u3, u4, u5));
		
		
		//Teste Licença
		Licenca l01 = new Licenca(1L, StatusLicenca.ATIVA, Instant.now(), TipoLicenca.MENSAL, 91.00, u1);
		Licenca l02 = new Licenca(2L, StatusLicenca.ATIVA, Instant.now(), TipoLicenca.SEMESTRAL, 516.00, u2);
		Licenca l03 = new Licenca(null, StatusLicenca.ATIVA, Instant.now(), TipoLicenca.ANUAL, 984.00, u3);
		Licenca l04 = new Licenca(null, StatusLicenca.ATIVA, Instant.now(), TipoLicenca.MENSAL, 91.00, u4);
		Licenca l05 = new Licenca(null, StatusLicenca.ATIVA, Instant.now(), TipoLicenca.GRATUITA, 00.00, u5);
		Licenca l06 = new Licenca(null, StatusLicenca.EXPIRADA, Instant.now(), TipoLicenca.MENSAL, 91.00, u1);
		Licenca l07 = new Licenca(null, StatusLicenca.EXPIRADA, Instant.now(), TipoLicenca.SEMESTRAL, 516.00, u2);
		Licenca l08 = new Licenca(null, StatusLicenca.EXPIRADA, Instant.now(), TipoLicenca.ANUAL, 984.00, u3);
		Licenca l09 = new Licenca(null, StatusLicenca.EXPIRADA, Instant.now(), TipoLicenca.MENSAL, 91.00, u4);
		Licenca l10 = new Licenca(null, StatusLicenca.EXPIRADA, Instant.now(), TipoLicenca.GRATUITA, 00.00, u5);
		Licenca l11 = new Licenca(null, StatusLicenca.CANCELADA, Instant.now(), TipoLicenca.MENSAL, 91.00, u1);
		Licenca l12 = new Licenca(null, StatusLicenca.CANCELADA, Instant.now(), TipoLicenca.SEMESTRAL, 516.00, u2);
		Licenca l13 = new Licenca(null, StatusLicenca.CANCELADA, Instant.now(), TipoLicenca.ANUAL, 984.00, u3);
		Licenca l14 = new Licenca(null, StatusLicenca.CANCELADA, Instant.now(), TipoLicenca.MENSAL, 91.00, u4);
		Licenca l15 = new Licenca(null, StatusLicenca.CANCELADA, Instant.now(), TipoLicenca.GRATUITA, 00.00, u5);
		
		licencaRepository.saveAll(Arrays.asList(l01, l02, l03, l04, l05, l06, l07, l08, l09, l10, l11, l12, l13, l14, l15));

		
		//Teste Cliente
		Cliente c01 = new Cliente(null, "Vinicius","vinicius@gmail.com", "7512345678", null, "123456789", null,StatusCliente.ATIVA, u1);
		Cliente c02 = new Cliente(null, "Vinicius","vinicius@gmail.com", "7512345678", null, "123456789", null,StatusCliente.ATIVA, u1);
		Cliente c03 = new Cliente(null, "Vinicius","vinicius@gmail.com", "7512345678", null, "123456789", null,StatusCliente.ATIVA, u2);
		Cliente c04 = new Cliente(null, "Vinicius","vinicius@gmail.com", "7512345678", null, "123456789", null,StatusCliente.ATIVA, u2);
		Cliente c05 = new Cliente(null, "Vinicius","vinicius@gmail.com", "7512345678", null, "123456789", null,StatusCliente.ATIVA, u3);
		Cliente c06 = new Cliente(null, "Vinicius","vinicius@gmail.com", "7512345678", null, "123456789", null,StatusCliente.ATIVA, u3);
		Cliente c07 = new Cliente(null, "Vinicius","vinicius@gmail.com", "7512345678", null, "123456789", null,StatusCliente.ATIVA, u4);
		Cliente c08 = new Cliente(null, "Vinicius","vinicius@gmail.com", "7512345678", null, "123456789", null,StatusCliente.ATIVA, u4);
		Cliente c09 = new Cliente(null, "Vinicius","vinicius@gmail.com", "7512345678", null, "123456789", null,StatusCliente.ATIVA, u5);
		Cliente c10 = new Cliente(null, "Vinicius","vinicius@gmail.com", "7512345678", null, "123456789", null,StatusCliente.ATIVA, u5);
		
		clienteRepository.saveAll(Arrays.asList(c01, c02, c03, c04, c05, c06, c07, c08, c09, c10));
		
		
		//Teste Estoque
		Estoque e01 = new Estoque(null, u1.getNome(), u1);
		Estoque e02 = new Estoque(null, u2.getNome(), u2);
		Estoque e03 = new Estoque(null, u3.getNome(), u3);
		Estoque e04 = new Estoque(null, u4.getNome(), u4);
		Estoque e05 = new Estoque(null, u5.getNome(), u5);
		
		estoqueRepository.saveAll(Arrays.asList(e01, e02, e03, e04, e05));
		
		
		//Teste Produto
		Produto p01 = new Produto(null, "Coxinha", 3.0, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u1, e01);
		Produto p02 = new Produto(null, "Empada", 3.0, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u1, e01);
		Produto p03 = new Produto(null, "Lasanha", 3.0, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.PRATO, Status.ATIVO, "Usar massa de pastel", u1, e01);
		Produto p04 = new Produto(null, "Coxinha", 3.0, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u2, e02);
		Produto p05 = new Produto(null, "Empada", 3.0, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u2, e02);
		Produto p06 = new Produto(null, "Lasanha", 3.0, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.PRATO, Status.ATIVO, "Usar massa de pastel", u2, e02);
		Produto p07 = new Produto(null, "Coxinha", 3.0, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u3, e03);
		Produto p08 = new Produto(null, "Empada", 3.0, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u3, e03);
		Produto p09 = new Produto(null, "Lasanha", 3.0, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.PRATO, Status.ATIVO, "Usar massa de pastel", u3, e03);
		Produto p10 = new Produto(null, "Coxinha", 3.0, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u4, e04);
		Produto p11 = new Produto(null, "Empada", 3.0, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u4, e04);
		Produto p12 = new Produto(null, "Lasanha", 3.0, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.PRATO, Status.ATIVO, "Usar massa de pastel", u4, e04);
		Produto p13 = new Produto(null, "Coxinha", 3.0, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.FRITO, Status.ATIVO, "quero congelado", u5, e05);
		Produto p14 = new Produto(null, "Empada", 3.0, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.FORNO, Status.ATIVO, "quero congelado", u5, e05);
		Produto p15 = new Produto(null, "Lasanha", 3.0, TipoArmazenamento.NAO_ESTOCAVEL, null, TipoProduto.PRATO, Status.ATIVO, "Usar massa de pastel", u5, e05);
		
		produtoRepository.saveAll(Arrays.asList(p01, p02, p03, p04, p05, p06, p07, p08, p09, p10, p11, p12, p13, p14, p15));
		
		
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
		Orcamento o01 = new Orcamento(null, TipoEntrega.RETIRADA, Instant.now(), Status.ATIVO, c01.getUsuario(), c01);
		Orcamento o02 = new Orcamento(null, TipoEntrega.RETIRADA, Instant.now(), Status.ATIVO, c02.getUsuario(), c02);
		Orcamento o03 = new Orcamento(null, TipoEntrega.RETIRADA, Instant.now(), Status.ATIVO, c03.getUsuario(), c03);
		Orcamento o04 = new Orcamento(null, TipoEntrega.RETIRADA, Instant.now(), Status.ATIVO, c04.getUsuario(), c04);
		Orcamento o05 = new Orcamento(null, TipoEntrega.RETIRADA, Instant.now(), Status.ATIVO, c04.getUsuario(), c05);
		Orcamento o06 = new Orcamento(null, TipoEntrega.RETIRADA, Instant.now(), Status.ATIVO, c06.getUsuario(), c06);
		Orcamento o07 = new Orcamento(null, TipoEntrega.RETIRADA, Instant.now(), Status.ATIVO, c01.getUsuario(), c01);
		Orcamento o08 = new Orcamento(null, TipoEntrega.RETIRADA, Instant.now(), Status.ATIVO, c04.getUsuario(), c04);
		Orcamento o09 = new Orcamento(null, TipoEntrega.RETIRADA, Instant.now(), Status.ATIVO, c07.getUsuario(), c07);
		Orcamento o10 = new Orcamento(null, TipoEntrega.RETIRADA, Instant.now(), Status.ATIVO, c08.getUsuario(), c08);
		Orcamento o11 = new Orcamento(null, TipoEntrega.RETIRADA, Instant.now(), Status.ATIVO, c10.getUsuario(), c10);
		Orcamento o12 = new Orcamento(null, TipoEntrega.RETIRADA, Instant.now(), Status.ATIVO, c10.getUsuario(), c10);
		Orcamento o13 = new Orcamento(null, TipoEntrega.RETIRADA, Instant.now(), Status.ATIVO, c05.getUsuario(), c05);
		Orcamento o14 = new Orcamento(null, TipoEntrega.RETIRADA, Instant.now(), Status.ATIVO, c09.getUsuario(), c09);
		Orcamento o15 = new Orcamento(null, TipoEntrega.RETIRADA, Instant.now(), Status.ATIVO, c01.getUsuario(), c01);
		
		orcamentoRepository.saveAll(Arrays.asList(o01, o02, o03, o04, o05, o06, o07, o08, o09, o10, o11, o12, o13, o14, o15));
		
		ItensOrcamento io01 = new ItensOrcamento(o01, p01, 2, p01.getValor());
		ItensOrcamento io02 = new ItensOrcamento(o01, p02, 5, p02.getValor());
		ItensOrcamento io03 = new ItensOrcamento(o01, p03, 10, p03.getValor());
		
		itensOrcamentoRepository.saveAll(Arrays.asList(io01, io02, io03));	
		
		
		//Teste Pedido

		Pedido pd01 = new Pedido(null, 957.90, TipoEntrega.ENTREGA, o01.getDataOrcamento(), Instant.now(), Status.ATIVO, StatusPedido.EM_FILA_PREPARACAO, o01.getUsuario(), o01);
		Pedido pd02 = new Pedido(null, 957.90, TipoEntrega.ENTREGA, o01.getDataOrcamento(), Instant.now(), Status.ATIVO, StatusPedido.EM_FILA_PREPARACAO, o02.getUsuario(), o02);
		Pedido pd03 = new Pedido(null, 957.90, TipoEntrega.ENTREGA, o01.getDataOrcamento(), Instant.now(), Status.ATIVO, StatusPedido.EM_FILA_PREPARACAO, o03.getUsuario(), o03);
		Pedido pd04 = new Pedido(null, 957.90, TipoEntrega.ENTREGA, o01.getDataOrcamento(), Instant.now(), Status.ATIVO, StatusPedido.EM_FILA_PREPARACAO, o04.getUsuario(), o04);
		Pedido pd05 = new Pedido(null, 957.90, TipoEntrega.ENTREGA, o01.getDataOrcamento(), Instant.now(), Status.ATIVO, StatusPedido.EM_FILA_PREPARACAO, o05.getUsuario(), o05);
		Pedido pd06 = new Pedido(null, 957.90, TipoEntrega.ENTREGA, o01.getDataOrcamento(), Instant.now(), Status.ATIVO, StatusPedido.EM_FILA_PREPARACAO, o06.getUsuario(), o06);
		Pedido pd07 = new Pedido(null, 957.90, TipoEntrega.ENTREGA, o01.getDataOrcamento(), Instant.now(), Status.ATIVO, StatusPedido.EM_FILA_PREPARACAO, o07.getUsuario(), o07);
		Pedido pd08 = new Pedido(null, 957.90, TipoEntrega.ENTREGA, o01.getDataOrcamento(), Instant.now(), Status.ATIVO, StatusPedido.EM_FILA_PREPARACAO, o08.getUsuario(), o08);
		Pedido pd09 = new Pedido(null, 957.90, TipoEntrega.ENTREGA, o01.getDataOrcamento(), Instant.now(), Status.ATIVO, StatusPedido.EM_FILA_PREPARACAO, o09.getUsuario(), o09);
		Pedido pd10 = new Pedido(null, 957.90, TipoEntrega.ENTREGA, o01.getDataOrcamento(), Instant.now(), Status.ATIVO, StatusPedido.EM_FILA_PREPARACAO, o10.getUsuario(), o10);
		Pedido pd11 = new Pedido(null, 957.90, TipoEntrega.ENTREGA, o01.getDataOrcamento(), Instant.now(), Status.ATIVO, StatusPedido.EM_FILA_PREPARACAO, o11.getUsuario(), o11);
		Pedido pd12 = new Pedido(null, 957.90, TipoEntrega.ENTREGA, o01.getDataOrcamento(), Instant.now(), Status.ATIVO, StatusPedido.EM_FILA_PREPARACAO, o12.getUsuario(), o12);
		Pedido pd13 = new Pedido(null, 957.90, TipoEntrega.ENTREGA, o01.getDataOrcamento(), Instant.now(), Status.ATIVO, StatusPedido.EM_FILA_PREPARACAO, o13.getUsuario(), o13);
		Pedido pd14 = new Pedido(null, 957.90, TipoEntrega.ENTREGA, o01.getDataOrcamento(), Instant.now(), Status.ATIVO, StatusPedido.EM_FILA_PREPARACAO, o14.getUsuario(), o14);
		Pedido pd15 = new Pedido(null, 957.90, TipoEntrega.ENTREGA, o01.getDataOrcamento(), Instant.now(), Status.ATIVO, StatusPedido.EM_FILA_PREPARACAO, o15.getUsuario(), o15);
		
		pedidoRepository.saveAll(Arrays.asList(pd01, pd02, pd03, pd04, pd05, pd06, pd07, pd08, pd09, pd10, pd11, pd12, pd13, pd14, pd15));
		
		MovimentacaoEstoque mv01 = new MovimentacaoEstoque(null, 20, Instant.now(), p15, e05);
		
		movimentacaoEstoqueRepository.saveAll(Arrays.asList(mv01));
		
		//Teste Pagamento
		
		Pagamento pg01 = new Pagamento(null, 957.90, Instant.now(), FormaPagamento.DINHEIRO, pd01);
		Pagamento pg02 = new Pagamento(null, 478.95, Instant.now(), FormaPagamento.DINHEIRO, pd02);
		Pagamento pg03 = new Pagamento(null, 478.95, Instant.now(), FormaPagamento.DINHEIRO, pd02);
		Pagamento pg04 = new Pagamento(null, 319.30, Instant.now(), FormaPagamento.DINHEIRO, pd03);
		Pagamento pg05 = new Pagamento(null, 319.30, Instant.now(), FormaPagamento.DINHEIRO, pd03);
		Pagamento pg06 = new Pagamento(null, 319.30, Instant.now(), FormaPagamento.DINHEIRO, pd03);
		Pagamento pg07 = new Pagamento(null, 957.90, Instant.now(), FormaPagamento.DINHEIRO, pd04);
		Pagamento pg08 = new Pagamento(null, 957.90, Instant.now(), FormaPagamento.DINHEIRO, pd05);
		Pagamento pg09 = new Pagamento(null, 191.58, Instant.now(), FormaPagamento.DINHEIRO, pd06);
		Pagamento pg10 = new Pagamento(null, 191.58, Instant.now(), FormaPagamento.DINHEIRO, pd06);
		Pagamento pg11 = new Pagamento(null, 191.58, Instant.now(), FormaPagamento.DINHEIRO, pd06);
		Pagamento pg12 = new Pagamento(null, 191.58, Instant.now(), FormaPagamento.DINHEIRO, pd06);
		Pagamento pg13 = new Pagamento(null, 191.58, Instant.now(), FormaPagamento.DINHEIRO, pd06);
		Pagamento pg14 = new Pagamento(null, 478.95, Instant.now(), FormaPagamento.DINHEIRO, pd07);
		Pagamento pg15 = new Pagamento(null, 478.95, Instant.now(), FormaPagamento.DINHEIRO, pd07);
		Pagamento pg16 = new Pagamento(null, 957.90, Instant.now(), FormaPagamento.DINHEIRO, pd08);
		Pagamento pg17 = new Pagamento(null, 957.90, Instant.now(), FormaPagamento.DINHEIRO, pd09);
		Pagamento pg18 = new Pagamento(null, 957.90, Instant.now(), FormaPagamento.DINHEIRO, pd10);
		Pagamento pg19 = new Pagamento(null, 957.90, Instant.now(), FormaPagamento.DINHEIRO, pd11);
		Pagamento pg20 = new Pagamento(null, 957.90, Instant.now(), FormaPagamento.DINHEIRO, pd12);
		Pagamento pg21 = new Pagamento(null, 957.90, Instant.now(), FormaPagamento.DINHEIRO, pd13);
		Pagamento pg22 = new Pagamento(null, 478.95, Instant.now(), FormaPagamento.DINHEIRO, pd14);
		Pagamento pg23 = new Pagamento(null, 478.95, Instant.now(), FormaPagamento.DINHEIRO, pd14);
		Pagamento pg24 = new Pagamento(null, 478.95, Instant.now(), FormaPagamento.DINHEIRO, pd15);
		Pagamento pg25 = new Pagamento(null, 478.95, Instant.now(), FormaPagamento.DINHEIRO, pd15);
		
		
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