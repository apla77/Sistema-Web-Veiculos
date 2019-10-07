package br.com.ifrn.swv.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import br.com.ifrn.swv.model.Anuncio;

import br.com.ifrn.swv.model.UF;

@Entity
@Table(name="anuncio")
public class Anuncio implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String dataAnucio;
	
	@ManyToOne
	@JoinTable(name="anuncio_usuario")
	public Usuario usuario;

	
	@OneToOne
	@JoinColumn(name="veiculo_id")  
	public Veiculo veiculo;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private UF uf;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDataAnucio() {
		return dataAnucio;
	}
	public void setDataAnucio(String dataAnucio) {
		this.dataAnucio = dataAnucio;
	}
	
	public UF getUf() {
		return uf;
	}
	public void setUf(UF uf) {
		this.uf = uf;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Veiculo getVeiculo() {
		return veiculo;
	}
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	
}
