package com.gestion.empleados.repositorios;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.gestion.empleados.entidades.Empleado;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends PagingAndSortingRepository<Empleado, Long>{
     public Empleado findByEmail(String email);


}
