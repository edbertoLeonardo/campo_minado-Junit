package be.com.com.company.cm.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class Tabuleiro {
	
	private int linhas;
	private int colunas;
	private int minas;
	
	private final List<Campo> campos = new ArrayList<>();
	
	public Tabuleiro() {
		
	}

	public Tabuleiro(int linas, int colunas, int minas) {
		this.linhas = linas;
		this.colunas = colunas;
		this.minas = minas;
		
		gerarCampos();
		associarOsVizinhos();
		sortearAsMinas();
	}
	
	
	public void abrirCampo(int linha, int coluna) {
		campos.parallelStream().filter(c -> c.getLinha() == linha && c.getColuna() == coluna)
		.findFirst().ifPresent(c -> c.abrir());
		
	}
	
	public void alterarMarcacao(int linha, int coluna) {
		campos.parallelStream().filter(c -> c.getLinha() == linha && c.getColuna() == coluna)
		.findFirst().ifPresent(c -> c.alterarMarcacao());
		
	}



	private void gerarCampos() {
		for(int i = 0; i< linhas; i++) {
			for (int j = 0; j< colunas; j++) {
				campos.add(new Campo( i, j));
				
			}
		}
		
	}
	
	private void associarOsVizinhos() {
		for(Campo c: campos) {
			for(Campo c2: campos) {
				c.adicionarVizinho(c2);
			}
		}
	}
	


	private void sortearAsMinas() {
		long minasArmada = 0;
		Predicate<Campo> minado = c -> c.isMinado();
		
		do {
			minasArmada = campos.stream().filter(minado).count();
			int valorAleatorio = (int) ((Math.random() * campos.size()));
			campos.get(valorAleatorio).minarCampo();
			
		}while(minasArmada < minas);
	}
	
	
	  public boolean objetivoAlcancado() {
		  return campos.stream().allMatch(c -> c.objetivoAlcancado());
		
	  }
	  
	  public void reiniciarGame() {
		  campos.stream().forEach(c-> c.reiniciar());
		  sortearAsMinas();
	  }
	  
	  public String toString() {
		  StringBuilder sb = new StringBuilder();
		  
		  int i = 0;
		  for (int l = 0; l < linhas; l++) {	
			  for (int c = 0; c < colunas; c++) {
				  sb.append(" ");
				  sb.append(campos.get(i));
				  sb.append(" ");
				  i++;
			}
		sb.append("\n");
		}
		  
		return sb.toString();
	}

}
