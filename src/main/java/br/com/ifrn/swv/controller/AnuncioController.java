package br.com.ifrn.swv.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.ifrn.swv.model.Anuncio;
import br.com.ifrn.swv.model.Usuario;
import br.com.ifrn.swv.model.Veiculo;
import br.com.ifrn.swv.service.AnuncioService;
import br.com.ifrn.swv.service.SessionService;
import br.com.ifrn.swv.service.UsuarioService;
import br.com.ifrn.swv.service.VeiculoService;


@Controller
@RequestMapping("/anuncio")
public class AnuncioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private VeiculoService veiculoService;
	
	@Autowired
	private AnuncioService anuncioService;
	
	@Autowired
	private SessionService<Usuario> serviceSession;
			
	@RequestMapping("/cadastrar")
	public ModelAndView cadastrar(Anuncio anuncio) {
		ModelAndView mv = new ModelAndView("anuncio/cadastro");
		mv.addObject("anuncio", anuncio);
		return mv;
	}
	
	@PostMapping("/salvar")
    public ModelAndView salvar(@Valid Anuncio anuncio, @RequestParam("file")MultipartFile file, BindingResult result) throws IOException {
		 Usuario usuarioSession = serviceSession.getSession("usuario");
		 Usuario usuario = usuarioService.getOne(usuarioSession.getId());
		 Veiculo veiculo = veiculoService.findOne(anuncio.getVeiculo().getId());
		
		if(result.hasErrors()) {
			return cadastrar(anuncio);
	    }
		if (!file.isEmpty()) {
			anuncio.setImagem(file.getBytes());
		}
		Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		String dataCadastro = formatador.format(data);
		anuncio.setDataAnucio(dataCadastro);
		ViaCEPClient cep = new ViaCEPClient();
		usuario.setEndereco(cep.buscaEnderecoPor(anuncio.getCep()));
		anuncio.setUsuario(usuario);
		anuncio.setVeiculo(veiculo);
		anuncioService.cadastrar(anuncio);
		//ModelAndView rec = findAll(); 
		
		ModelAndView rec = details(anuncio.getId());
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
	
	@GetMapping("/details/{id}")
	public ModelAndView details(@PathVariable("id") Long id) {
		
		ModelAndView mv = new ModelAndView("anuncio/detalhes");
		mv.addObject("anuncio", anuncioService.findOne(id));
		
		return mv;
	}
	
	@RequestMapping(path = {"/imagem/{id}"}, produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> getImagem(@PathVariable("id") Long id){
		Anuncio anuncio = anuncioService.findOne(id);
		byte[] imagem = anuncio.getImagem();
		final org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		return new ResponseEntity<byte[]>(imagem, headers, HttpStatus.OK);
	}
	
}