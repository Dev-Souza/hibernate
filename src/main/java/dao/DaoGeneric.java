package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import hibernete.HibernateUtil;

public class DaoGeneric<E> {

	private EntityManager entityManager = HibernateUtil.geEntityManager();

	public void salvar(E entidade) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(entidade);
		transaction.commit();
	}

	public E updateMerge(E entidade) { // salva ou atualiza
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		E entidadeSalvada = entityManager.merge(entidade);
		transaction.commit();

		return entidadeSalvada;
	}

	public E pesquisar(E entidade) {
		Object id = HibernateUtil.getPrimaryKey(entidade);

		E e = (E) entityManager.find(entidade.getClass(), id);

		return e;
	}

	public E pesquisar(Long id, Class<E> entidade) {

		E e = (E) entityManager.find(entidade, id);

		return e;
	}

	public void deletarPorId(E entidade) {

		Object id = HibernateUtil.getPrimaryKey(entidade);

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		entityManager.createNativeQuery(
				"delete from " + entidade.getClass().getSimpleName().toLowerCase() + 
				" where id =" + id).executeUpdate(); //faz delete
		
		transaction.commit(); //Grava alteração no banco
	}

	public List<E> listar(Class<E> entidade){
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		List<E> lista = entityManager.createQuery("from " + entidade.getName()).getResultList();
		
		transaction.commit();
		
		return lista;
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
}
