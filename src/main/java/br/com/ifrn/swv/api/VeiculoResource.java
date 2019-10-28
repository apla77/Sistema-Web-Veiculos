package br.com.ifrn.swv.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifrn.swv.model.Veiculo;	
import br.com.ifrn.swv.service.VeiculoService;

@RestController
@RequestMapping("/api")
public class VeiculoResource {
	
	@Autowired
	private VeiculoService veiculoService;
	
	@GetMapping("/veiculo")
	public List<Veiculo> veiculo(){
		return veiculoService.listaAll();
	}
	
	@PostMapping("/veiculo")
	public Veiculo salvar(@RequestBody Veiculo veiculo) {
		return veiculoService.save(veiculo);
	} 

}
