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
		//Teste Licença
		Licenca l1 = new Licenca(null, StatusLicenca.ATIVA, null, TipoLicenca.ANUAL, 98.90);
		
		licencaRepository.saveAll(Arrays.asList(l1));
		
		//Teste Usuário
		Usuario u1 = new Usuario(null, "Lucas", "123456789","lucas@gmail.com","7512345678", null, "Tomba", null,Status.ATIVO);
		
		usuarioRepository.saveAll(Arrays.asList(u1));
		
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
