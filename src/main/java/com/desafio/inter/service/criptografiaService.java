package com.desafio.inter.service;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.security.PrivateKey;
import java.security.PublicKey;

import com.desafio.inter.model.Usuario;
import com.desafio.inter.security.CriptografiaRSA;

public class criptografiaService {


	public void gerarChave() {
		try {

			Usuario user = new Usuario();
			CriptografiaRSA rsa = new CriptografiaRSA();
			if (!CriptografiaRSA.verificaSeExisteChavesNoSO()) {

				CriptografiaRSA.geraChave();
			}
			final String msgOriginal = "Exemplo de mensagem";
			ObjectInputStream inputStream = null;

			// Criptografa a Mensagem usando a Chave Pública
			inputStream = new ObjectInputStream(new FileInputStream(CriptografiaRSA.PATH_CHAVE_PUBLICA));
			final PublicKey chavePublica = (PublicKey) inputStream.readObject();
			final byte[] textoCriptografado = CriptografiaRSA.criptografa(msgOriginal, chavePublica);

			// Decriptografa a Mensagem usando a Chave Privada
			inputStream = new ObjectInputStream(new FileInputStream(CriptografiaRSA.PATH_CHAVE_PRIVADA));
			final PrivateKey chavePrivada = (PrivateKey) inputStream.readObject();
			final String textoPuro = CriptografiaRSA.decriptografa(textoCriptografado, chavePrivada);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

