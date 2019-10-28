package br.com.ifrn.swv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifrn.swv.model.Anuncio;
import br.com.ifrn.swv.repository.AnuncioRepository;

@Service
public class AnuncioService {

	@Autowired
	private AnuncioRepository repository;

	public List<Anuncio> listaAll(){
		return repository.findAll();
	}
	
	public void cadastrar(Anuncio anuncio) {
		repository.saveAndFlush(anuncio);
	}
	
	public Anuncio findOne(Long id) {
        return repository.getOne(id);
    }
	
	public Anuncio findOneUsuario(Long id) {
        return repository.findOneUsuario(id);
    }
     
    public Anuncio save(Anuncio anuncio) {
        return repository.saveAndFlush(anuncio);
    }
     
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
