package br.com.ifrn.swv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifrn.swv.model.Role;
import br.com.ifrn.swv.repository.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository repository;
	
	public void add(Role role) {
		repository.saveAndFlush(role);
	}
	
	public Role getNome(String nome) {
		return repository.findByNome(nome);
	}
	
	public List<Role> buscarTodos(){
		return repository.findAll();
	}

}
