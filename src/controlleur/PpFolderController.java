package controlleur;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.InvalidPathException;

import javax.swing.JScrollPane;

import model.Classifieur;
import view.PpFolderView;

public class PpFolderController {
	
	private PpFolderView vue;
	@SuppressWarnings("unused")
	private Classifieur model;
	
	public PpFolderController() {
		this.vue = new PpFolderView();
		
		/**
		 * POUR L'AJOUT D'UN PATH ET L'ACTION DE VALIDER
		 */
		try {
			this.vue.ajouterUnPathListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent event) {
					if(!vue.getpathDossierField().getText().isEmpty() || ! vue.getpathDossierField().getText().isBlank()) {
						try {
							
							System.out.println("le chemin est: "+ vue.getpathDossierField().getText());
							vue.getPpFolderInterface().remove(vue.getFenetreDepart());
							
							vue.getPpFolderInterface().add(vue.getPannelScrollableRegles(), BorderLayout.EAST);
							
							@SuppressWarnings("unused")
							Classifieur model = new Classifieur(vue.getpathDossierField().getText().toString());
							
							vue.getPpFolderInterface().add(vue.getPanelScrollableFichiers(), BorderLayout.CENTER);
							
							refreshView();
							
						}catch (InvalidPathException e) {
							vue.displayErrorMessage("You Need to Enter a correct Path file !");
						}
					}else {
						vue.displayErrorMessage("You Need to Enter a correct Path file !");
					}
				}
			});
		}catch (NullPointerException e) {vue.displayErrorMessage("Erreur, veuillez redemarer le programme !");}
		
		/**
		 * POUR ALLER SUR LE PANEL D'AJOUT DES REGLES (ACTION DUR LE BOUTON)
		 */
		try {
			this.vue.allerAajouteruneregleListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					try {

						vue.getPpFolderInterface().remove(vue.getPanelScrollableFichiers());
						vue.getPpFolderInterface().remove(vue.getPanelSuppressionRegle());
						
						vue.getPpFolderInterface().add(vue.getPanelAjoutRegle(), BorderLayout.CENTER);
						
						refreshView();
						
					}catch (Exception exeption) {
						vue.displayErrorMessage("");
					}
				}
			});
		}catch (NullPointerException e) {vue.displayErrorMessage("Erreur, veuillez redemarer le programme !");}
			
		/**
		 * POUR ALLER AU PANEL SUPPRESSION D'UNE REGLE
		 */
		try {
			this.vue.allerASupprimerUneRegleListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					vue.getPpFolderInterface().remove(vue.getPanelScrollableFichiers());
					vue.getPpFolderInterface().remove(vue.getPanelAjoutRegle());
					
					vue.init_list();
			    	
					vue.getPpFolderInterface().add(vue.getPanelSuppressionRegle());
					
					refreshView();
				}
			});
		}catch (NullPointerException e) {vue.displayErrorMessage("Erreur, veuillez redemarer le programme !");}
			
		/**
		 * POUR L'AJOUT D'UNE REGLE DEPUIS SON PANEL
		 */
		try {
			this.vue.ajouterUneReglelistener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(!vue.getAjout().getText().isEmpty() || !vue.getAjout().getText().isBlank()) {
						
						vue.getPpFolderInterface().remove(vue.getPanelSuppressionRegle());
			    		vue.getPpFolderInterface().remove(vue.getPanelAjoutRegle());
				    	vue.getPpFolderInterface().remove(vue.getPannelScrollableRegles());
				    	
						vue.getRegles().add(vue.getAjout().getText());
						
						vue.getPannelScrollableRegles().add(vue.refresh_regle(vue.getAjout().getText()));
				    	vue.setPannelScrollableRegles(new JScrollPane(vue.ajouter_boutons_regle()));
						vue.getPpFolderInterface().add(vue.getPannelScrollableRegles(), BorderLayout.EAST);
						
						vue.getPpFolderInterface().add(vue.getPanelScrollableFichiers(), BorderLayout.CENTER);

						refreshView();
				        
					}else {
						vue.displayErrorMessage("You Need to Enter a correct extension name !");
					}
				}
			});
		}catch (NullPointerException e) {vue.displayErrorMessage("Erreur, veuillez redemarer le programme !");}
			
		/**
		 * SUPPRIMER UNE REGLE DEPUIS LA LISTE DEROULANTE
		 */
		try {
			this.vue.supprimerUneRegleListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(vue.getSelectRegle_list().getSelectedItem() != null) {
						vue.getRegles().remove(vue.getSelectRegle_list().getSelectedItem());
						
			    		vue.init_list();
			    		
			    		vue.getPpFolderInterface().remove(vue.getPanelSuppressionRegle());
			    		vue.getPpFolderInterface().remove(vue.getPanelAjoutRegle());
				    	vue.getPpFolderInterface().remove(vue.getPannelScrollableRegles());
				    	
				    	vue.setPannelScrollableRegles(new JScrollPane(vue.ajouter_boutons_regle()));
						vue.getPpFolderInterface().add(vue.getPannelScrollableRegles(), BorderLayout.EAST);
						
						vue.getPpFolderInterface().add(vue.getPanelScrollableFichiers(), BorderLayout.CENTER);

						refreshView();
			    	}
				}
			});
		}catch (NullPointerException e) {vue.displayErrorMessage("Erreur, veuillez redemarer le programme !");}
	}
	
	public void refreshView() {
		vue.getPpFolderInterface().revalidate();
	 	vue.getPpFolderInterface().repaint();
	}
}