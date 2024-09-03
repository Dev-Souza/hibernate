package hibernete;

import java.util.List;

import org.junit.jupiter.api.Test;

import Model.TelefoneUser;
import Model.UsuarioPessoa;
import dao.DaoGeneric;

public class TesteHibernate {

	@Test
	public void testeHibernateUtil() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
	
		UsuarioPessoa pessoa = new UsuarioPessoa();
		pessoa.setIdade(45);
		pessoa.setLogin("teste");
		pessoa.setNome("Kauan L.");
		pessoa.setSenha("123");
		pessoa.setSobrenome("Souza");
		pessoa.setEmail("teste@gmail.com");
		
		daoGeneric.salvar(pessoa);
	}
	
	@Test
	public void testeBuscar() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		UsuarioPessoa pessoa = new UsuarioPessoa();
		pessoa.setId(2L);
		
		pessoa = daoGeneric.pesquisar(pessoa);
		
		System.out.println(pessoa);
	}
	
	@Test
	public void testeBuscar2() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		UsuarioPessoa pessoa = daoGeneric.pesquisar(2L, UsuarioPessoa.class);
		
		System.out.println(pessoa);
	}
	
	@Test
	public void testeUpdate() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		UsuarioPessoa pessoa = daoGeneric.pesquisar(1L, UsuarioPessoa.class);
		
		pessoa.setIdade(19);
		pessoa.setNome("Nome atualizado hibernate");
		pessoa.setSenha("fldjkgpiofd");
		
		pessoa = daoGeneric.updateMerge(pessoa);
		
		System.out.println(pessoa);
	}
	
	@Test
	public void testeDelete() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		UsuarioPessoa pessoa = daoGeneric.pesquisar(3L, UsuarioPessoa.class);
		
		daoGeneric.deletarPorId(pessoa);
	}
	
	@Test
	public void testeConsultarTodos() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		List<UsuarioPessoa> list = daoGeneric.listar(UsuarioPessoa.class);
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
			System.out.println("-------------------------------------------------");
		}
	}
	
	@Test
	public void testeQueryList() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createQuery(" from UsuarioPessoa").getResultList();
	
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
			System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
		}
	}
	
	@Test
	public void testeQueryListMaxResult() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> list = daoGeneric.getEntityManager()
				.createQuery(" from UsuarioPessoa order by nome")
				.setMaxResults(5)												
				.getResultList();
	
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
			System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
		}
	}
	
	@Test
	public void testeQueryListParameter() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		List<UsuarioPessoa> list = daoGeneric.
				getEntityManager().createQuery(" from UsuarioPessoa where nome = :nome or sobrenome = :sobrenome")
				.setParameter("nome", "Kauan L.")
				.setParameter("sobrenome", "Souza").getResultList();
	
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
			System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------");
		}
	}
	
	@Test
	public void testeQueryMediaIdade() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		Double somaIdade = (Double) daoGeneric.getEntityManager()
				.createQuery("select avg(u.idade) from UsuarioPessoa u").getSingleResult();
		
		System.out.println("Média de todas as idades é: " + somaIdade);
	}
	
	//Buscar com namedQuery
	@Test
	public void testeNamedQuery1() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createNamedQuery("UsuarioPessoa.todos").getResultList();
	
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}
	}
	
	//Buscar por nome com namedQuery
	@Test
	public void testeNamedQuery2() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		List<UsuarioPessoa> list = daoGeneric.getEntityManager()
				.createNamedQuery("UsuarioPessoa.buscaPorNome")
				.setParameter("nome", "Kauan L.")
				.getResultList();
	
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}
	}
	
	@Test
	public void testeGravaTelefone() {
		
		DaoGeneric daoGeneric = new DaoGeneric();
		
		UsuarioPessoa pessoa = (UsuarioPessoa) daoGeneric.pesquisar(7L, UsuarioPessoa.class);
	
		TelefoneUser telefoneUser = new TelefoneUser();
		telefoneUser.setTipo("Casa");
		telefoneUser.setNumero("156484548");
		telefoneUser.setUsuarioPessoa(pessoa);
		
		daoGeneric.salvar(telefoneUser);
	}
	
	@Test
	public void testeConsultaTelefones() {
		
		DaoGeneric daoGeneric = new DaoGeneric();
		
		UsuarioPessoa pessoa = (UsuarioPessoa) daoGeneric.pesquisar(7L, UsuarioPessoa.class);
	
		for (TelefoneUser fone : pessoa.getTelefoneUsers()) {
			System.out.println(fone.getNumero());
			System.out.println(fone.getTipo());
			System.out.println(fone.getUsuarioPessoa().getNome());
			System.out.println("----------------------------------------------------------------------------");
		}
	}
}
