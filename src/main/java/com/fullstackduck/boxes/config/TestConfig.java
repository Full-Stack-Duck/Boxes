package com.fullstackduck.boxes.config;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.fullstackduck.boxes.entities.Cliente;
import com.fullstackduck.boxes.entities.Despesa;
import com.fullstackduck.boxes.entities.Estoque;
import com.fullstackduck.boxes.entities.Licenca;
import com.fullstackduck.boxes.entities.Orcamento;
import com.fullstackduck.boxes.entities.Pedido;
import com.fullstackduck.boxes.entities.Produto;
import com.fullstackduck.boxes.entities.Receita;
import com.fullstackduck.boxes.entities.Usuario;
import com.fullstackduck.boxes.entities.enums.Categoria;
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
import com.fullstackduck.boxes.repositories.LicencaRepository;
import com.fullstackduck.boxes.repositories.OrcamentoRepository;
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
	private ReceitaRepository receitaRepository;

	@Override
	public void run(String... args) throws Exception {
		
		List<Licenca> licencas = new ArrayList<>();
		
		//Teste Usuário
		Usuario u1 = new Usuario(null, "Bruno", "123456789", "bruno@gmail.com", "7512345678", "aaaaaaaa", "Tomba", null, Status.ATIVO);
		Usuario u2 = new Usuario(null, "José", "123456789", "jose@gmail.com", "7512345678", "bbbbbbbb", "Tomba", null, Status.ATIVO);
		Usuario u3 = new Usuario(null, "Kelvin", "123456789", "kelvin@gmail.com", "7512345678", "cccccccc", "Tomba", null, Status.ATIVO);
		Usuario u4 = new Usuario(null, "Lucas", "123456789", "lucas@gmail.com", "7512345678", "dddddddd", "Tomba", null, Status.ATIVO);
		Usuario u5 = new Usuario(null, "Vinícius", "123456789", "vinicius@gmail.com", "7512345678", "eeeeeeee", "Tomba", null, Status.ATIVO);
		
		//Teste Licença
		Licenca l01 = new Licenca(null, StatusLicenca.ATIVA, Instant.now(), TipoLicenca.MENSAL, 91.00, u1);
		Licenca l02 = new Licenca(null, StatusLicenca.ATIVA, Instant.now(), TipoLicenca.SEMESTRAL, 516.00, u2);
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
		
		usuarioRepository.saveAll(Arrays.asList(u1, u2, u3, u4, u5));
		licencaRepository.saveAll(Arrays.asList(l01, l02, l03, l04, l05, l06, l07, l08, l09, l10, l11, l12, l13, l14, l15));

		
		//Teste Cliente
		Cliente c1 = new Cliente(null, "Vinicius","vinicius@gmail.com", "7512345678", null, "123456789", null,StatusCliente.ATIVA);
		
		clienteRepository.saveAll(Arrays.asList(c1));
		
		//Teste Produto
		Produto p1 = new Produto(null, "Coxinha",3.0, TipoArmazenamento.ESTOCAVEL, TipoProduto.FRITO, Status.ATIVO, "quero congelado");
		
		produtoRepository.saveAll(Arrays.asList(p1));
		
		//Teste Despesa
		Despesa d1 = new Despesa(null, "Energia",Categoria.FIXA, 250.0, null, null);
		
		despesaRepository.saveAll(Arrays.asList(d1));
		
		//Teste Estoque
		Estoque e1 = new Estoque(null, 20,"Coxinha", TipoProduto.FRITO, 2.50);
		
		estoqueRepository.saveAll(Arrays.asList(e1));
		
		//Teste Orçamento
		Orcamento o1 = new Orcamento(null, TipoEntrega.RETIRADA, Instant.now(), Status.ATIVO);
		
		orcamentoRepository.saveAll(Arrays.asList(o1));
		
		//Teste Pedido
		Pedido pd1 = new Pedido(null, 957.90, TipoEntrega.ENTREGA, o1.getDataOrcamento(), Instant.now(), Status.ATIVO, StatusPedido.EM_FILA_PREPARACAO);
		
		pedidoRepository.saveAll(Arrays.asList(pd1));
		
		//Teste Receita
		Receita r1 = new Receita(null, Instant.now());
		
		receitaRepository.saveAll(Arrays.asList(r1));
		
	}
}
