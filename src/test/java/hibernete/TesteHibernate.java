package hibernete;

import org.junit.jupiter.api.Test;

import Model.UsuarioPessoa;
import dao.DaoGeneric;

public class TesteHibernate {

	@Test
	public void testeHibernateUtil() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
	
		UsuarioPessoa pessoa = new UsuarioPessoa();
		pessoa.setIdade(45);
		pessoa.setLogin("teste");
		pessoa.setNome("Só testando 2");
		pessoa.setSenha("123");
		pessoa.setSobrenome("Souza");
		pessoa.setEmail("teste@gmail.com");
		
		daoGeneric.salvar(pessoa);
	}
}
