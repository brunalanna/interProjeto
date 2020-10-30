package com.desafio.inter.service;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.desafio.inter.model.DigitoCalculadora;
import com.desafio.inter.model.Usuario;
import com.desafio.inter.repository.DigitoCalculadoraRepository;
import com.desafio.inter.repository.UsuarioRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class DigitoCalculadoraServiceTest {
			
		
		@Autowired
		DigitoCalculadoraRepository digitoUnicoRepository;
		
		@Autowired
		DigitoCalculadoraService digitoUnicoService;
		
		@Autowired
		UsuarioRepository usuarioRepository;
		
		@Before
		public void setUpData() {		
			Usuario usuario = new Usuario();
			usuario.setNome("Bruna teste");
			usuario.setEmail("brunateste@mail.com");
			usuarioRepository.save(usuario);

			List<Usuario> listaUsuarios = usuarioRepository.findAll();
			DigitoCalculadora digitoUnico = new DigitoCalculadora();
			digitoUnico.setMultiplo(4);
			digitoUnico.setNumero("2034");
			digitoUnico.setUsuario(listaUsuarios.get(0));
			digitoUnicoRepository.save(digitoUnico);		
		}
		
		@Test
		public void salvarDigitoUnico() {
			DigitoCalculadora digitoUnico = new DigitoCalculadora();
			digitoUnico.setMultiplo(4);
			digitoUnico.setNumero("102");
			digitoUnico.setUsuario(usuarioRepository.getOne(1));		
			DigitoCalculadora digitoSalvo = digitoUnicoRepository.save(digitoUnico);
			
			Optional<DigitoCalculadora> digitoPesquisado = digitoUnicoRepository.findById(digitoSalvo.getId());
			
			assertTrue(digitoSalvo.getId() == digitoPesquisado.get().getId());
		}

}
