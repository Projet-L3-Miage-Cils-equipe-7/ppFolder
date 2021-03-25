package controlleur;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;

import javax.swing.JButton;
import javax.swing.JScrollPane;

import model.Classifieur;
import model.Rules;
import view.PpFolderView;

public class PpFolderController {
	
	private PpFolderView vue;
	private Classifieur model;
	private Rules<String> mesRegles;
	private Rules<String> mesReglesSelectionner;
	
	public PpFolderController() {
		this.model = new Classifieur();
		this.vue = new PpFolderView();
		this.mesRegles = new Rules<>(vue.regles);
		this.mesReglesSelectionner = new Rules<>(vue.regles_selectionnees);
			
		/**
		 * 
		 */
		this.vue.ajouterUnPathListener(new ActionListener() {
				
			@Override
			public void actionPerformed(ActionEvent event) {
				if(!vue.getpathDossierField().getText().isEmpty() || !vue.getpathDossierField().getText().isBlank()) {
					File path = new File(vue.getpathDossierField().getText().toString()).getAbsoluteFile();
					if(path.isDirectory()) {
						if(path.list().length > 0){
							try {
								vue.getPpFolderInterface().removeAll();
								vue.initView();
								
								model = new Classifieur(vue.getpathDossierField().getText().toString());
								
								vue.dessin_panel_fichier();
								dessin_regles();
								dess_panelbas();
								vue.getPpFolderInterface().add(vue.getPanelbas(),BorderLayout.SOUTH);
								refreshView();
							}catch (NullPointerException e) {vue.displayErrorMessage("Erreur, inconnue veuillez redemarer l'application !");}
						}else {vue.displayErrorMessage("Erreur, le dossier ciblé ne peux pas etre vide !");}
					}else {vue.displayErrorMessage("You Need to Enter a correct Path file !");}
				}else {vue.displayErrorMessage("Le chemin ne peux pas être vide !");}
			}
		});

		/**
		 * 
		 */
		this.vue.allerAajouteruneregleListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					vue.getPpFolderInterface().removeAll();
					vue.initView();
					vue.getPpFolderInterface().add(vue.getPanelAjoutRegle());
					dessin_regles();
					dess_panelbas();
					vue.getPpFolderInterface().add(vue.getPanelbas(),BorderLayout.SOUTH);
					refreshView();
				}catch (NullPointerException e1) {vue.displayErrorMessage("Erreur, inconnue veuillez redemarer l'application !");}
			}
		});

		/**
		 * 
		 */
		this.vue.allerASupprimerUneRegleListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					vue.getPpFolderInterface().removeAll();
					vue.initView();
					dessin_regles();
					vue.getPpFolderInterface().add(vue.getPanelSuppressionRegle());
					dess_panelbas();
					vue.getPpFolderInterface().add(vue.getPanelbas(),BorderLayout.SOUTH);
					refreshView();
				}catch (NullPointerException e1) {vue.displayErrorMessage("Erreur, veuillez redemarer le programme !");}
			}
		});

		/**
		 * 
		 */
		this.vue.ajouterUneReglelistener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String rule = vue.getAjout().getText().trim();
				if(!rule.isEmpty() || !rule.isBlank()) {
					try {
						vue.getPpFolderInterface().removeAll();
						vue.initView();
						
						vue.regles.add(rule);
						mesRegles.serializeRules();
						
						vue.getModelRegle().addElement(rule);
						
						dessin_regles();
						vue.dessin_panel_fichier();
						dessin_regles();
						
						dess_panelbas();
						vue.getPpFolderInterface().add(vue.getPanelbas(),BorderLayout.SOUTH);
						refreshView();
					}catch (NullPointerException e3) {vue.displayErrorMessage("Erreur, inconnue veuillez redemarer l'application !");}
				}else {vue.displayErrorMessage("Une règle ne pas pas être vide !");}
			}
		});

		/**
		 * 
		 */
		this.vue.supprimerUneRegleListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					mesRegles.removeRule(vue.getSelectRegle_list().getSelectedItem().toString());
					mesRegles.serializeRules();
					
					vue.init_list();
					
					vue.getPpFolderInterface().removeAll();
					vue.initView();

					vue.dessin_panel_fichier();
					dessin_regles();
					
					dess_panelbas();
					vue.getPpFolderInterface().add(vue.getPanelbas(),BorderLayout.SOUTH);

					refreshView();
				}catch (NullPointerException e4) {vue.displayErrorMessage("Erreur, inconnue veuillez redemarer l'application !");}
			}
		});
		
		/**
		 * 
		 */
		this.vue.lancerLeTri(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					model = new Classifieur(vue.getpathDossierField().getText().toString());
					model.trier(mesReglesSelectionner.getListOfRegles());
					vue.dessin_panel_fichier();
					refreshView();
				} catch (IOException e) {vue.displayErrorMessage("Erreur, Impossible de trier ce dossier !");}
				catch (NullPointerException e) {vue.displayErrorMessage("Erreur, Impossible de trier ce dossier !");}
				catch (NoSuchElementException e) {vue.displayErrorMessage("Erreur, redémarer l'application !");}
			}
		});
	}
	
	public void refreshView() {
		vue.getPpFolderInterface().revalidate();
	 	vue.getPpFolderInterface().repaint();
	}
	
	/**
	 * @return {@link Void}
	 */
	public void refresh_regle_selectionnees() {

		vue.getpanelRegleUtiliser().removeAll();
		vue.getpanelRegleUtiliser().setLayout(new GridLayout(1, vue.regles_selectionnees.size()+1));
		vue.getpanelRegleUtiliser().setPreferredSize(new Dimension(vue.regles_selectionnees.size()*300, 180));
		
		for(String s : vue.regles_selectionnees) {
			JButton btn = new JButton(s);
			btn.setPreferredSize(new Dimension(200, 150));
			vue.getpanelRegleUtiliser().add(btn);
			
			/**
			 * SUPPRIMER UNE REGLE SELECTIONNÉE
			 */
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
                	try {
	                	vue.getpanelRegleUtiliser().remove(btn);
	                	mesReglesSelectionner.removeRule(btn.getText().toString());
	                	refresh_regle_selectionnees();
//	                	mesReglesSelectionner.serializeRules();
	                	vue.getLancerTrier().setEnabled( (!vue.regles_selectionnees.isEmpty())? true : false );
	                	refreshView();
                	}catch (NullPointerException e5) {vue.displayErrorMessage("Erreur, inconnue veuillez redemarer l'application !");}
                }
			});
		}	
	}
	
	/**
	 * @return {@link Void}
	 */
	public void ajouter_boutons_regle() {
		vue.getPanelRegles().removeAll();
		
		vue.getPanelRegles().setPreferredSize(new Dimension(0, vue.getRegles().size()*50));
		
		vue.getPanelRegles().setLayout(new GridLayout(vue.getRegles().size()+2, 1));
		
		vue.getPanelRegles().add(vue.getAllerAAjouterRegle()).setPreferredSize(new Dimension(vue.getLargeurPanelRegle(), vue.getHauteurRegle()));
		vue.getPanelRegles().add(vue.getSupprimerRegle()).setPreferredSize(new Dimension(vue.getLargeurPanelRegle(), vue.getHauteurRegle()));
		
		for(String rule : vue.getRegles()) {
			JButton btn = new JButton(rule);
			btn.setPreferredSize(new Dimension(vue.getlargeurPanelRegle(), vue.getHauteurRegle()));
			vue.getPanelRegles().add(btn);
			
			/**
			 * AJOUTER UNE REGLE SELECTIONNÉE
			 */
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						if(!vue.regles_selectionnees.contains(btn.getText().toString())){
							vue.regles_selectionnees.add(btn.getText());
//							mesReglesSelectionner.serializeRules();
							refresh_regle_selectionnees();
							vue.getLancerTrier().setEnabled( (!vue.regles_selectionnees.isEmpty())? true : false );
						}
						refreshView();
					}catch (NullPointerException e6) {vue.displayErrorMessage("Erreur, inconnue veuillez redemarer l'application !");}
				}
			});
		}
	}
	
	public void dessin_regles() {
		ajouter_boutons_regle();
		vue.getPannelScrollableRegles().setPreferredSize(new Dimension(vue.getlargeurPanelRegle(), 600));
		vue.getPpFolderInterface().add(vue.getPannelScrollableRegles(),BorderLayout.EAST);
	}
	
	public void refresh_scrollRegle(){
		ajouter_boutons_regle();
		vue.setPannelScrollableRegles(new JScrollPane(vue.getPanelRegles()));
	}
	
	public void dess_panelbas() {
		refresh_regle_selectionnees();
		vue.getPanelbas().add(vue.getLancerTrier(), BorderLayout.EAST);
		vue.getPanelbas().add(vue.getPannelScrollableReglesUtilisees(), BorderLayout.CENTER);
		vue.getPpFolderInterface().add(vue.getPanelbas(), BorderLayout.SOUTH);
	}
	
}