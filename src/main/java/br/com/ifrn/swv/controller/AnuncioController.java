package br.com.ifrn.swv.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.ifrn.swv.model.Anuncio;
import br.com.ifrn.swv.service.AnuncioService;


@Controller
@RequestMapping("/anuncio")
public class AnuncioController {
	
	@Autowired
	private AnuncioService anuncioService;
			
	@RequestMapping("/cadastrar")
	public ModelAndView cadastrar(Anuncio anuncio) {
		ModelAndView mv = new ModelAndView("anuncio/cadastro");
		mv.addObject("anuncio", anuncio);
		return mv;
	}
	
	@PostMapping("/salvar")
    public ModelAndView salvar(@Valid Anuncio anuncio, BindingResult result) {
		 
		if(result.hasErrors()) {
			return cadastrar(anuncio);
	    }

		anuncioService.cadastrar(anuncio);
		ModelAndView rec = findAll(); 
		return rec;
    }
	
	@GetMapping("/editar/{id}")
	private ModelAndView editar( @PathVariable("id") Long id) {
		Anuncio anuncio = anuncioService.findOne(id);
		return cadastrar(anuncio);
	}
	

	@GetMapping("/excluir/{id}")
	private ModelAndView excluir( @PathVariable("id") Long id) {
		anuncioService.delete(id); 
		return findAll();
	}
	
	@GetMapping("/lista")
	private ModelAndView findAll() {
		ModelAndView mv = new ModelAndView("anuncio/lista");
        mv.addObject("anuncio", anuncioService.listaAll());
        return mv;
	}

}