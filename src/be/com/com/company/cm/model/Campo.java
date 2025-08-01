package be.com.com.company.cm.model;

import java.util.ArrayList;
import java.util.List;

import be.com.com.company.cm.exception.ExplosaoException;

public class Campo {
	
	private final int linha;
	private final int coluna;
	private boolean aberto;
	private boolean minado;
	private boolean marcado;
	
	private List<Campo> vizinhos = new ArrayList<>();
	
	
  public Campo(int linha, int coluna) {
	this.linha = linha;
	this.coluna = coluna;
  }
  

  
  public boolean adicionarVizinho(Campo vizinho) {
	    int deltaLinha = Math.abs(linha - vizinho.linha);
	    int deltaColuna = Math.abs(coluna - vizinho.coluna);

	    boolean eMesmoCampo = deltaLinha == 0 && deltaColuna == 0;
	    boolean eVizinho = deltaLinha <= 1 && deltaColuna <= 1 && !eMesmoCampo;

	    if (eVizinho) {
	        vizinhos.add(vizinho);
	        return true;
	    } else {
	        return false;
	    }
	  
  }
  
  
  
  public void alterarMarcacao() {
	  if (!aberto) {
		marcado = !marcado;
		  
	}
  }
  
  
  public boolean abrir() {
	  if (!aberto && !marcado) {
		aberto = true;
		
		if (minado) {
			throw new ExplosaoException();
		}
		
		if (vizinhancaSegura()) {
			vizinhos.forEach(v -> v.abrir());
		}
		
		return true;
	}else {
		 return false;
	}
	  
	 
  }
  
  
  boolean vizinhancaSegura() {
	  return vizinhos.stream().noneMatch(v -> v.minado);
  }
  
  public boolean estaMarcado() {
	  return marcado;
  }
  
  public void minarCampo() {
	  minado = true;
  }
  
  public boolean isMinado() {
	  return minado;
  }
  
   void setAberto(boolean aberto) {
	this.aberto = aberto;
}
  
  public boolean estaAberto() {
	  return aberto;
  }
  
  public boolean estaFechado() {
	  return !aberto;
  }
  
  public int getLinha() {
	return linha;
}
  
  public int getColuna() {
	return coluna;
}
  
  public boolean objetivoAlcancado() {
	  boolean campoDesvendado = !minado && aberto;
	  boolean campoProtegido = minado && marcado;
	  return campoDesvendado || campoProtegido;
  }
  
  public long minasNavizinhanca() {
	  return vizinhos.stream().filter(v -> v.minado).count();
  }
  
  public void reiniciar() {
	  aberto = false;
	  minado = false;
	  marcado = false;
  }
  
  
  public String toString() {
	  if (marcado) {
		return "x";
	}else if (aberto && minado) {
		return "*";
	}else if (aberto && minasNavizinhanca() > 0) {
		return Long.toString(minasNavizinhanca());
	}else if (aberto) {
		return " ";
		
	}else {
		return "?";
	}
  }
  
  
}
