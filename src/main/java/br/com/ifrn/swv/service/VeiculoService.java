package br.com.ifrn.swv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifrn.swv.model.Veiculo;
import br.com.ifrn.swv.repository.VeiculoRepository;


@Service
public class VeiculoService {
	
	@Autowired
	private VeiculoRepository repository;

	public List<Veiculo> listaAll(){
		return repository.findAll();
	}
	
	public void cadastrar(Veiculo veiculo) {
		repository.saveAndFlush(veiculo);
	}
	
	public Veiculo findOne(Long id) {
        return repository.getOne(id);
    }
     
    public Veiculo save(Veiculo veiculo) {
        return repository.saveAndFlush(veiculo);
    }
     
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
