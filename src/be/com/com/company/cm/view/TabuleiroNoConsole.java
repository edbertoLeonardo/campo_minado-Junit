package be.com.com.company.cm.view;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import be.com.com.company.cm.exception.ExplosaoException;
import be.com.com.company.cm.exception.SairException;
import be.com.com.company.cm.model.Tabuleiro;

public class TabuleiroNoConsole {

	private Tabuleiro tabuleiro;
	private Scanner entrada = new Scanner(System.in);
	
	public TabuleiroNoConsole(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		executarJogo();
	}

	private void executarJogo() {
		try {
			boolean continuar = true;
			
			while (continuar) {
				cicloDoJogo();
				System.out.println("Outra partida? (S/n)");
				String resposta = entrada.nextLine(); 
				
				if ("n".equalsIgnoreCase(resposta)) {
					continuar = false;
	
				}else {
					tabuleiro.reiniciarGame();
				}
			}
			
		} catch (SairException e) {
			System.out.println("saiu!!!");
		}finally {
			entrada.close();
		}
	}

	private void cicloDoJogo() {
		try {
			while(!tabuleiro.objetivoAlcancado()) {
				System.out.println(tabuleiro);
				
				String digitado = capturarValorDigitado("Digite (x, y): ");
				Iterator<Integer> xy =  Arrays.stream(digitado.split(",")).map(e -> Integer.parseInt(e.trim())).iterator();
				
				digitado = capturarValorDigitado("1 - Abrir ou 2 - (Des)marcar:");
				if ("1".equals(digitado)) {
					tabuleiro.abrirCampo(xy.next(), xy.next());
				}else if ("2".equals(digitado)) {
					tabuleiro.alterarMarcacao(xy.next(), xy.next());
				}
				
			}
			
			System.out.println("Você ganhou!");
			System.out.println(tabuleiro);
			
		} catch (ExplosaoException e) {
			System.out.println(tabuleiro);
			System.out.println("Você perdeu!");
		}
	}
	
	
	private String capturarValorDigitado(String texto) {
		System.out.print(texto);
		String digitado = entrada.nextLine();
		if ("sair".equalsIgnoreCase(digitado)) {
			throw new SairException();
		}

		return digitado;
	}
	
	
	
	
}
