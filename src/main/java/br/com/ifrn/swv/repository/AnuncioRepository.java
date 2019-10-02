package br.com.ifrn.swv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ifrn.swv.model.Anuncio;


@Repository
public interface AnuncioRepository extends JpaRepository<Anuncio, Long>{
	
	//@Query
	//public List<Anuncio> findByNomeLike(String modelo);
	
	@Query("select a from Anuncio a where a.modelo = ?1")
	public List<Anuncio> findByModelo(String modelo);

}
