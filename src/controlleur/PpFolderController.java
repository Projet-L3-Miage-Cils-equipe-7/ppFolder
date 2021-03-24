package controlleur;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Classifieur;
import view.PpFolderView;

public class PpFolderController {
	
	private PpFolderView vue;
	@SuppressWarnings("unused")
	private Classifieur model;
	
	public PpFolderController() {
		this.vue = new PpFolderView();


			this.vue.ajouterUnPathListener(new ActionListener() {
	
				
				public void actionPerformed(ActionEvent event) {
					vue.getPpFolderInterface().removeAll();
					vue.initView();
					vue.dessin_panel_fichier();
					vue.dessin_regles();
					vue.dess_panelbas();
					vue.getPpFolderInterface().add(vue.getPanelbas(),BorderLayout.SOUTH);
					refreshView();
			}});

			this.vue.allerAajouteruneregleListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					vue.getPpFolderInterface().removeAll();
					vue.initView();
					
					vue.getPpFolderInterface().add(vue.getPanelAjoutRegle());
					vue.dessin_regles();
					vue.getPpFolderInterface().add(vue.getPanelbas(),BorderLayout.SOUTH);
					
					
					refreshView();
				}});

			this.vue.allerASupprimerUneRegleListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
				vue.getPpFolderInterface().removeAll();
				vue.initView();
				
				vue.dessin_regles();
				
				
				vue.getPpFolderInterface().add(vue.getPanelSuppressionRegle());
				vue.dess_panelbas();
				vue.getPpFolderInterface().add(vue.getPanelbas(),BorderLayout.SOUTH);
				
				
				refreshView();
					

			}});


			this.vue.ajouterUneReglelistener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					vue.getPpFolderInterface().removeAll();
					vue.initView();
					vue.regles.add(vue.getAjout().getText());
					vue.getModelRegle().addElement(vue.getAjout().getText());
					
					vue.dessin_regles();
					vue.dessin_panel_fichier();
					vue.dessin_regles();
					
					vue.dess_panelbas();
					vue.getPpFolderInterface().add(vue.getPanelbas(),BorderLayout.SOUTH);
					refreshView();

				}
			});


			this.vue.supprimerUneRegleListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					
					vue.regles.remove(vue.getSelectRegle_list().getSelectedItem());
					vue.init_list();
					
					vue.getPpFolderInterface().removeAll();
					vue.initView();
					
					
					
					vue.dessin_panel_fichier();
					vue.dessin_regles();
					
					vue.dess_panelbas();
					vue.getPpFolderInterface().add(vue.getPanelbas(),BorderLayout.SOUTH);
					refreshView();
			    	}
				
			});
	}
	
	public void refreshView() {
		vue.getPpFolderInterface().revalidate();
	 	vue.getPpFolderInterface().repaint();
	}
}