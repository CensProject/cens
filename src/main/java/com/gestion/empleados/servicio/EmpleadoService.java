package com.gestion.empleados.servicio;

import com.gestion.empleados.entidades.Empleado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;


@Service
public interface EmpleadoService extends UserDetailsService {

	Empleado save(@Valid Empleado empleadoRegisterDTO);

	Empleado update(Empleado empleado);

	Empleado actualizar(Empleado empleado);

	public List<Empleado> findAll();

	public Page<Empleado> findAll(Pageable pageable);

	public Empleado findOne(Long id);

	public void delete(Long id);
}
