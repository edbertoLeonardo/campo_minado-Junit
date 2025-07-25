package br.com.company.cm;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import be.com.com.company.cm.exception.ExplosaoException;
import be.com.com.company.cm.model.Campo;

class CampoTeste {

	private Campo campo = new Campo(3, 3);
	
	@Test
	void testeVizinhoDistacia() {
		Campo vizinho = new Campo(3, 2);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoDistacia2() {
		Campo vizinho = new Campo(2, 2);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	
	@Test
	void testeNaoVizinho() {
		Campo vizinho = new Campo(1, 1);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertFalse(resultado);
	}

	@Test
	void testeValorPadraoAtributoMarcado() {
		assertFalse(campo.estaMarcado());
	}
	
	@Test
	void testeAlternarMarcacao() {
		campo.alterarMarcacao();
		assertTrue(campo.estaMarcado());
	}
	
	@Test
	void testeAlternarMarcacaoDuasChamadas() {
		campo.alterarMarcacao();
		campo.alterarMarcacao();
		assertFalse(campo.estaMarcado());
	}
	
	@Test
	void testeAbrirCampoNaoMinadoNaoMarcado() {

		assertTrue(campo.abrir());
	}
	
	@Test
	void testeAbrirCampoNaoMinadoMarcado() {
		campo.alterarMarcacao();
		assertFalse(campo.abrir());
	}
	
	@Test
	void testeAbrirCampoMinadoMarcado() {
		campo.alterarMarcacao();
		campo.minarCampo();
		assertFalse(campo.abrir());
	}
	
	@Test
	void testeAbrirCampoMinadoNaoMarcado() {
		campo.minarCampo();
		assertThrows(ExplosaoException.class,  () -> {
		campo.abrir();
		});
	}
	
	@Test
	void testeAbrirCampoComVizinhos() {
		Campo vizinhoDoVizinho = new Campo(1, 1);
		Campo vizinho = new Campo(2, 2);
		
		vizinho.adicionarVizinho(vizinhoDoVizinho);
		campo.adicionarVizinho(vizinho);
		campo.abrir();
		
		assertTrue(vizinho.estaAberto() && vizinhoDoVizinho.estaAberto());
	}
	
	
	@Test
	void testeAbrirCampoComVizinhos2() {
		Campo vizinhoDoVizinho = new Campo(1, 1);
		Campo vizinhoDoVizinhoMinado = new Campo(1, 1);
		vizinhoDoVizinhoMinado.minarCampo();
		
		Campo vizinho = new Campo(2, 2);
		vizinho.adicionarVizinho(vizinhoDoVizinho);
		vizinho.adicionarVizinho(vizinhoDoVizinhoMinado);
		
		campo.adicionarVizinho(vizinho);
		campo.abrir();
		
		assertTrue(vizinho.estaAberto() && vizinhoDoVizinho.estaFechado());
	}
	
	
	
}
