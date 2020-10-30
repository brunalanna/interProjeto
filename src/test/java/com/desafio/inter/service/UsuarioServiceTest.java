package com.desafio.inter.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.portable.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.desafio.inter.model.Usuario;
import com.desafio.inter.repository.UsuarioRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class UsuarioServiceTest {


	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	Usuario user = new Usuario();	
	
	@Before
	public void setUpData() {			
		user.setNome("Bruna Teste");
		user.setEmail("brunamoreiralanna@gmail.com");
		
		usuarioRepository.save(user);		
	}
	

	@Test
	public void buscarPorIDTest() {
		Usuario usuario = usuarioService.getUsuarioById(user.getId());
		assertEquals(usuario.getId(), user.getId());
		assertEquals(usuario.getEmail(), user.getEmail());
	}
	
	@Test
	public void listarBeneficiariosCadastrados() throws ApplicationException {
		List<Usuario> listUsuarios = usuarioService.getAllUsuarios();
		for (Usuario usuario : listUsuarios) {
			assertEquals(user.getId(), usuario.getId());
			assertEquals(user.getNome(), usuario.getNome());
			assertEquals(user.getEmail(), usuario.getEmail());
		}
	}
	
	@Test
	public void excluirUsuario() throws ApplicationException {
		int totalUsuarios, novoTotalUsuarios;
		
		List<Usuario> listaUsuarios = usuarioService.getAllUsuarios();
		totalUsuarios = listaUsuarios.size();
		
		usuarioService.deleteUsuario(1);
		
		List<Usuario> novalistaUsuario = usuarioService.getAllUsuarios();
		novoTotalUsuarios = novalistaUsuario.size();
		
		assertTrue(totalUsuarios > novoTotalUsuarios);
	}
}
