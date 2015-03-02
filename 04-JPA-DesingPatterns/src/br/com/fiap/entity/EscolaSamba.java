package br.com.fiap.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="JPA_ESCOLA_SAMBA")
@SequenceGenerator(name="seqEscola",
sequenceName="SEQ_ESCOLA_SAMBA",allocationSize=1)
public class EscolaSamba {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,
	generator="seqEscola")
	private int codigo;
	
	@Column(nullable=false,length=50)
	private String nome;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DT_FUNDACAO")
	private Calendar dataFundacao;
	
	@Lob
	private byte[] simbolo;
	
	private int integrantes;
	
	private String bairro;
	
	private Grupo grupo;
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Calendar getDataFundacao() {
		return dataFundacao;
	}

	public void setDataFundacao(Calendar dataFundacao) {
		this.dataFundacao = dataFundacao;
	}

	public byte[] getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(byte[] simbolo) {
		this.simbolo = simbolo;
	}

	public int getIntegrantes() {
		return integrantes;
	}

	public void setIntegrantes(int integrantes) {
		this.integrantes = integrantes;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

}
