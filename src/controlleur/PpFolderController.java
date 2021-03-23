package controlleur;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
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
							vue.getPpFolderInterface().remove(vue.getFenetreDepart());
							
							vue.getPannelScrollableRegles().setPreferredSize(new Dimension(vue.getLargeur_regle(), 600));
							vue.getPpFolderInterface().add(vue.getPannelScrollableRegles(), BorderLayout.EAST);
							
							vue.getPpFolderInterface().add(vue.getPanelScrollableFichiers(), BorderLayout.CENTER);
							@SuppressWarnings("unused")
							Classifieur model = new Classifieur(vue.getpathDossierField().getText().toString());
							try {
								vue.getPpFolderInterface().add(vue.getPanelScrollableFichiers());
							}catch (Exception exeption) {vue.displayErrorMessage("Ajoutez des regles !");}
							vue.getPpFolderInterface().revalidate(); // synchonize les panels
							vue.getPpFolderInterface().repaint(); // les affiche
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
						init();
						
						vue.getPpFolderInterface().add(vue.getPanelAjoutRegle());
						vue.getPpFolderInterface().revalidate();
						vue.getPpFolderInterface().repaint();
					}catch (Exception exeption) {
						vue.displayErrorMessage("");
					}
				}
			});
		}catch (NullPointerException e) {vue.displayErrorMessage("Erreur, veuillez redemarer le programme !");}
			
		/**
		 * POUR LA SUPPRESSION D'UNE REGLE
		 */
		try {
			this.vue.supprimerUneRegleListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					init();
			    	
					vue.init_list();
			    	
					vue.getPpFolderInterface().add(vue.getPanelSuppressionRegle());
					vue.getPpFolderInterface().revalidate();
					vue.getPpFolderInterface().repaint();
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
						init();
						vue.ajouter_boutons_regle();
						vue.getRegles().add(vue.getAjout().getText());
						
						vue.getPannelScrollableRegles().add(vue.refresh_regle(vue.getAjout().getText()));
						vue.getPannelScrollableRegles().setLayout(new GridLayout(vue.getRegles().size()+2, 1));
						
						vue.getPpFolderInterface().add(vue.getPanelScrollableFichiers());
						vue.getPpFolderInterface().revalidate();
						vue.getPpFolderInterface().repaint();
				        
					}else {
						vue.displayErrorMessage("You Need to Enter a correct extension name !");
					}
				}
			});
		}catch (NullPointerException e) {vue.displayErrorMessage("Erreur, veuillez redemarer le programme !");}
			
		try {
			this.vue.miseAjourListeRegleListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(vue.getSelectRegle_list().getSelectedItem() != null) {
						vue.getRegles().remove(vue.getSelectRegle_list().getSelectedItem());
						
			    		vue.init_list();
			    		
			    		vue.getPpFolderInterface().remove(vue.getPanelSuppressionRegle());
				    	vue.getPpFolderInterface().remove(vue.getPannelScrollableRegles());
				    	
				    	vue.setPannelScrollableRegles(new JScrollPane(vue.ajouter_boutons_regle()));
				    	
				    	vue.getPpFolderInterface().add(vue.getPanelScrollableFichiers());
					 	
					 	vue.getPpFolderInterface().revalidate();
					 	vue.getPpFolderInterface().repaint();
			    	}
				}
			});
		}catch (NullPointerException e) {vue.displayErrorMessage("Erreur, veuillez redemarer le programme !");}
	}
	
	public void init() {
		vue.getPpFolderInterface().removeAll();
		vue.initView();
		vue.getPannelScrollableRegles().setPreferredSize(new Dimension(vue.getLargeur_regle(), 600));
		vue.getPannelScrollableRegles().add(vue.ajouter_boutons_regle());
		
		vue.getPpFolderInterface().add(vue.getPannelScrollableRegles(), BorderLayout.EAST);
	}
}