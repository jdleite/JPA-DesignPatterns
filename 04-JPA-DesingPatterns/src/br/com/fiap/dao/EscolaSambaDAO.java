package br.com.fiap.dao;

import br.com.fiap.entity.EscolaSamba;
import br.com.fiap.exceptions.CodigoNaoEncontradoException;
import br.com.fiap.exceptions.DBCommitException;

public interface EscolaSambaDAO {

	void create(EscolaSamba escola) throws DBCommitException;
	void update(EscolaSamba escola) throws DBCommitException;
	void delete(int codigo) throws DBCommitException, CodigoNaoEncontradoException;
	EscolaSamba searchById(int codigo);
	
}
