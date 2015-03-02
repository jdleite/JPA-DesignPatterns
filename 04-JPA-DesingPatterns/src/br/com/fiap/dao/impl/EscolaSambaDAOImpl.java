package br.com.fiap.dao.impl;

import javax.persistence.EntityManager;

import br.com.fiap.dao.EscolaSambaDAO;
import br.com.fiap.entity.EscolaSamba;
import br.com.fiap.exceptions.CodigoNaoEncontradoException;
import br.com.fiap.exceptions.DBCommitException;

public class EscolaSambaDAOImpl implements EscolaSambaDAO {

	private EntityManager em;

	public EscolaSambaDAOImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	public void create(EscolaSamba escola) throws DBCommitException {
		try {
			em.getTransaction().begin();
			em.persist(escola);
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			throw new DBCommitException("Erro ao persistir");
		}
	}

	@Override
	public void update(EscolaSamba escola) throws DBCommitException {
		try {
			em.getTransaction().begin();
			em.merge(escola);
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			throw new DBCommitException("Erro ao atualizar");
		}
	}

	@Override
	public void delete(int codigo) throws CodigoNaoEncontradoException, DBCommitException {
		EscolaSamba escola = searchById(codigo);
		if (escola == null)
			throw new CodigoNaoEncontradoException("Código não encontrado");
		try {
			em.getTransaction().begin();
			em.remove(escola);
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			throw new DBCommitException("Erro ao deletar");
		}
	}

	@Override
	public EscolaSamba searchById(int codigo) {
		return em.find(EscolaSamba.class, codigo);
	}

}
