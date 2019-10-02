package br.com.ifrn.swv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ifrn.swv.model.Veiculo;

@Repository
public interface  VeiculoRepository extends JpaRepository<Veiculo, Long>{
	
	//@Query
	//public List<Veiculo> findByNomeLike(String modelo);
	
	@Query("select v from Veiculo v where v.modelo = ?1")
	public List<Veiculo> findByName(String modelo);

}
