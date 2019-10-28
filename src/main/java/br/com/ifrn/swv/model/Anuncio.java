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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import br.com.ifrn.swv.model.Anuncio;

import br.com.ifrn.swv.model.Endereco;

@Entity
@Table(name="anuncio")
public class Anuncio implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String dataAnucio;
    private String precoVeiculo;
    private String cep;
    private String quilometragem;
    private String direcao;
    private String arCondicionado;
    private String trioEletrico;
        
    
	@ManyToOne
	@JoinColumn(name="usuario_id")
	public Usuario usuario;
	
	@OneToOne
	@JoinColumn(name="veiculo_id")  
	public Veiculo veiculo;
	
	@Lob
	private byte[] imagem;
	
	
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
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
	
	public String getPrecoVeiculo() {
		return precoVeiculo;
	}
	public void setPrecoVeiculo(String precoVeiculo) {
		this.precoVeiculo = precoVeiculo;
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
	public byte[] getImagem() {
		return imagem;
	}
	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}
	public String getQuilometragem() {
		return quilometragem;
	}
	public void setQuilometragem(String quilometragem) {
		this.quilometragem = quilometragem;
	}
	public String getDirecao() {
		return direcao;
	}
	public void setDirecao(String direcao) {
		this.direcao = direcao;
	}
	public String getArCondicionado() {
		return arCondicionado;
	}
	public void setArCondicionado(String arCondicionado) {
		this.arCondicionado = arCondicionado;
	}
	public String getTrioEletrico() {
		return trioEletrico;
	}
	public void setTrioEletrico(String trioEletrico) {
		this.trioEletrico = trioEletrico;
	}
	
	
}
