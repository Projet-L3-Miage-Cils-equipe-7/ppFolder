package controlleur;

import java.io.IOException;
import java.util.Scanner;
import javax.swing.UnsupportedLookAndFeelException;

//import java.nio.file.Path;
//import javax.swing.UIManager;
//import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import ppFolder.Classifieur;

public class Main {

	/**
	 * Main methode
	 * @param args
	 * @throws IOException, UnsupportedLookAndFeelException
	 */
	public static void main(String[] args) throws IOException, UnsupportedLookAndFeelException {
		String source;
		
		Scanner scan = new Scanner(System.in);
		
		boolean quitter = true;
		
		System.out.print("choisir le chemin à traiter : ");
		source = scan.nextLine();
		
		Classifieur application = new Classifieur(source);
		
		// vider les regles déja presente
		Classifieur.getRule().destroyRules();
		
		Classifieur.getRule().serializeRules();
		while(quitter) {
			System.out.print("1 : Ajouter règle \n2 : Quitter\n");
			source = scan.nextLine();
			if(source.equals("1")) {
				System.out.print("Saisir extension : ");
				source = scan.nextLine();
				Classifieur.getRule().addRule(source);
				Classifieur.getRule().serializeRules();
			}
			else {
				quitter = false;
			}
		}
		application.trier();
		scan.close();
	}

}