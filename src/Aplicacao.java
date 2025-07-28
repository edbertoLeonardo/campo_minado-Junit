import be.com.com.company.cm.model.Tabuleiro;
import be.com.com.company.cm.view.TabuleiroNoConsole;

public class Aplicacao {
	
	   public static void main(String[] args) {
	        Tabuleiro tabuleiro = new Tabuleiro(6, 6, 3);
	         new TabuleiroNoConsole(tabuleiro);
	   }
	        
   }