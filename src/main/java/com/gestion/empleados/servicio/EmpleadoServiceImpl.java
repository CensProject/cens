package com.gestion.empleados.servicio;

import com.gestion.empleados.entidades.Empleado;
import com.gestion.empleados.entidades.Role;
import com.gestion.empleados.repositorios.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

	private EmpleadoRepository empleadoRepository;

	@Lazy
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository) {
		super();
		this.empleadoRepository = empleadoRepository;
	}


	@Override
	public Empleado save(@Valid Empleado empleadoRegisterDTO){
		Empleado empleado = new Empleado(empleadoRegisterDTO.getFirstName(),
				empleadoRegisterDTO.getLastName(),empleadoRegisterDTO.getEmail(),
				passwordEncoder.encode(empleadoRegisterDTO.getPassword()), Arrays.asList(new Role("ROLE_USER")));
		return empleadoRepository.save(empleado);
	}

	@Override
	public Empleado update(Empleado empleado){
		return empleadoRepository.save(empleado);
	}

	@Override
	public Empleado actualizar(Empleado empleado){
		return empleadoRepository.save(empleado);
	}


	@Override
	@Transactional
	public void delete(Long id) {
		empleadoRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Empleado findOne(Long id) {
		return empleadoRepository.findById(id).get();
	}



	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Empleado empleado = empleadoRepository.findByEmail(email);
		if(empleado == null) {
			throw new UsernameNotFoundException("Usuario o password inválidos");
		}
		return new org.springframework.security.core.userdetails.User(empleado.getEmail(), empleado.getPassword(), mapRolesToAuthorities(empleado.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public List<Empleado> findAll() {
		return (List<Empleado>) empleadoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Empleado> findAll(Pageable pageable) {
		return empleadoRepository.findAll(pageable);
	}


}
