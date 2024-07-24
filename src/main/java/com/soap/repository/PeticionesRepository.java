/**
 * 
 */
package com.soap.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soap.entity.Peticiones;

/**
 * @author John
 *
 */
public interface PeticionesRepository extends JpaRepository<Peticiones, Long>{
	
	Optional<Peticiones> findTopByOrderByIdDesc();
}
