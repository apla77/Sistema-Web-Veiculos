 package br.com.ifrn.swv.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.ifrn.swv.model.Anuncio;
import br.com.ifrn.swv.model.Veiculo;
import br.com.ifrn.swv.service.AnuncioService;
import br.com.ifrn.swv.service.VeiculoService;

@Controller
@RequestMapping("/veiculo")
public class VeiculoController {
	
	@Autowired
	private VeiculoService veiculoService;
	
	@Autowired
	private AnuncioService anuncioService;
			
	@GetMapping("/cadastrar")
	public ModelAndView cadastrar(Veiculo veiculo) {
		ModelAndView mv = new ModelAndView("veiculo/cadastro");
		mv.addObject("veiculo", veiculo);
		return mv;
	}
	
	@PostMapping("/salvar")
    public ModelAndView salvar(@Valid Veiculo veiculo, BindingResult result) {
		 
		if(result.hasErrors()) {
			return cadastrar(veiculo);
	    }
		Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		String dataCadastro = formatador.format(data);
		veiculo.setData(dataCadastro);
		veiculoService.cadastrar(veiculo);
		ModelAndView rec = findAll().addObject("success","Veículo cadastrado!"); 
		return cadastrarAnuncio(veiculo);
		//return rec;
    }
	
	@GetMapping("/editar/{id}")
	private ModelAndView editar( @PathVariable("id") Long id) {
		Veiculo veiculo = veiculoService.findOne(id);
		return cadastrar(veiculo);
	}
	

	@GetMapping("/excluir/{id}")
	private ModelAndView excluir( @PathVariable("id") Long id) {
		veiculoService.delete(id); 
		return findAll();
	}
	
	@GetMapping("/lista")
	private ModelAndView findAll() {
		ModelAndView mv = new ModelAndView("veiculo/lista");
        mv.addObject("veiculo", veiculoService.listaAll());
        return mv;
	}
	
	
	
	@RequestMapping("/cadastrarAnuncio")
	public ModelAndView cadastrarAnuncio(Veiculo veiculo) {
		Anuncio anuncio = new Anuncio();
		anuncio.setVeiculo(veiculo);
		
		ModelAndView mv = new ModelAndView("anuncio/cadastro");
		mv.addObject("anuncio", anuncio);
		//mv.addObject("veiculo", veiculo);
		return mv;
	}
	
	
}
