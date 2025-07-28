import be.com.com.company.cm.exception.ExplosaoException;
import be.com.com.company.cm.model.Tabuleiro;

public class Aplicacao {
	
	   public static void main(String[] args) {
	        Tabuleiro tabuleiro = new Tabuleiro(6, 6, 6);

	        try {
	            tabuleiro.abrirCampo(3, 3);  // pode lançar ExplosaoException
	        } catch (ExplosaoException e) {
	            System.out.println(" Você explodiu! Fim de jogo.");
	        }

	        tabuleiro.alterarMarcacao(4, 4);
	        tabuleiro.alterarMarcacao(4, 5);

	        System.out.println(tabuleiro);
	    }
//	public static void main(String[] args) {
//		
//		Tabuleiro tabuleiro = new Tabuleiro(6,6,6);
//
//		
//		tabuleiro.abrirCampo(3, 3);
//		tabuleiro.alterarMarcacao(4, 4);
//		tabuleiro.alterarMarcacao(4, 5);
//		
//		
//		System.out.println(tabuleiro);
//		
//	}

}
