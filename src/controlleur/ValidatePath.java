package controlleur;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Classifieur;
import view.ppFolderView;

class ValidatePath implements ActionListener {
	
	private ppFolderView vue;
	
	public void actionPerformed(ActionEvent event) {
		try{
			vue.getPpFolderInterface().remove(vue.getFenetreDepart());
			vue.getPpFolderInterface().add(vue.getPannelScrollableRegles(), BorderLayout.EAST);
			
			@SuppressWarnings("unused")
			Classifieur model = new Classifieur(vue.getPathDossierField().getText().toString());
			
			vue.getPpFolderInterface().add(vue.getPanelScrollableFichiers());
		 	vue.getPpFolderInterface().revalidate(); // synchonize les panels
		 	vue.getPpFolderInterface().repaint(); // les affiche
		}catch (Exception exeption) {
			vue.displayErrorMessage("You Need to Enter a correct Path file !");
		}
	}
}