package com.example.demo.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Categoria;
import com.example.demo.domain.Cidade;
import com.example.demo.domain.Cliente;
import com.example.demo.domain.Endereco;
import com.example.demo.domain.Estado;
import com.example.demo.domain.ItemPedido;
import com.example.demo.domain.Pagamento;
import com.example.demo.domain.PagamentoComBoleto;
import com.example.demo.domain.PagamentoComCartao;
import com.example.demo.domain.Pedido;
import com.example.demo.domain.Produto;
import com.example.demo.domain.enums.EstadoPagamento;
import com.example.demo.domain.enums.TipoCliente;
import com.example.demo.repositories.CategoriaRepository;
import com.example.demo.repositories.CidadeRepository;
import com.example.demo.repositories.ClienteRepository;
import com.example.demo.repositories.EnderecoRepository;
import com.example.demo.repositories.EstadoRepository;
import com.example.demo.repositories.ItemPedidoRepository;
import com.example.demo.repositories.PagamentoRepository;
import com.example.demo.repositories.PedidoRepository;
import com.example.demo.repositories.ProdutoRepository;

@Service
public class DBservice {
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public void instantiateTestDatabase() throws ParseException {

		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");

		Produto p1 = new Produto(null, "computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mause", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "Sao paulo");

		Cidade c1 = new Cidade(null, "Minas gerais", est1);
		Cidade c2 = new Cidade(null, "Sao paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

		Cliente cli1 = new Cliente(null, "Maria silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

		Endereco e1 = new Endereco(null, "Rua flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida matos", "105", "sala 800", "centro", "15987512", cli1, c2);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 10:32"), cli1, e2);

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"),
				null);
		ped2.setPagamento(pagto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
		Categoria cat11 = new Categoria(null, "Informatica");
		Categoria cat21 = new Categoria(null, "Escritorio");

		Produto p11 = new Produto(null, "computador", 2000.00);
		Produto p21 = new Produto(null, "Impressora", 800.00);
		Produto p31 = new Produto(null, "Mause", 80.00);

		cat11.getProdutos().addAll(Arrays.asList(p11, p21, p31));
		cat21.getProdutos().addAll(Arrays.asList(p21));

		p11.getCategorias().addAll(Arrays.asList(cat11));
		p21.getCategorias().addAll(Arrays.asList(cat11, cat21));
		p31.getCategorias().addAll(Arrays.asList(cat11));

		Estado est11 = new Estado(null, "Minas Gerais");
		Estado est21 = new Estado(null, "Sao paulo");

		Cidade c11 = new Cidade(null, "Minas gerais", est11);
		Cidade c21 = new Cidade(null, "Sao paulo", est21);
		Cidade c31 = new Cidade(null, "Campinas", est21);

		est11.getCidades().addAll(Arrays.asList(c11));
		est21.getCidades().addAll(Arrays.asList(c21, c31));

		categoriaRepository.saveAll(Arrays.asList(cat11, cat21));
		produtoRepository.saveAll(Arrays.asList(p11, p21, p31));
		estadoRepository.saveAll(Arrays.asList(est11, est21));
		cidadeRepository.saveAll(Arrays.asList(c11, c21, c31));

		Cliente cli11 = new Cliente(null, "Maria silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cli11.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

		Endereco e11 = new Endereco(null, "Rua flores", "300", "Apto 303", "Jardim", "38220834", cli11, c11);
		Endereco e21 = new Endereco(null, "Avenida matos", "105", "sala 800", "centro", "15987512", cli11, c21);

		cli11.getEnderecos().addAll(Arrays.asList(e11, e21));

		clienteRepository.saveAll(Arrays.asList(cli11));
		enderecoRepository.saveAll(Arrays.asList(e11, e21));

		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped11 = new Pedido(null, sdf1.parse("30/09/2017 10:32"), cli11, e11);
		Pedido ped21 = new Pedido(null, sdf1.parse("10/10/2017 10:32"), cli11, e21);

		Pagamento pagto11 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped11, 6);
		ped11.setPagamento(pagto11);

		Pagamento pagto21 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped21, sdf1.parse("20/10/2017 00:00"),
				null);
		ped21.setPagamento(pagto21);

		cli11.getPedidos().addAll(Arrays.asList(ped11, ped21));

		pedidoRepository.saveAll(Arrays.asList(ped11, ped21));
		pagamentoRepository.saveAll(Arrays.asList(pagto11, pagto21));

		ItemPedido ip11 = new ItemPedido(ped11, p11, 0.00, 1, 2000.00);
		ItemPedido ip21 = new ItemPedido(ped11, p31, 0.00, 2, 80.00);
		ItemPedido ip31 = new ItemPedido(ped21, p21, 100.00, 1, 800.00);

		ped11.getItens().addAll(Arrays.asList(ip11, ip21));
		ped21.getItens().addAll(Arrays.asList(ip31));

		p11.getItens().addAll(Arrays.asList(ip11));
		p21.getItens().addAll(Arrays.asList(ip31));
		p31.getItens().addAll(Arrays.asList(ip21));

		itemPedidoRepository.saveAll(Arrays.asList(ip11, ip21, ip31));
	}

}
