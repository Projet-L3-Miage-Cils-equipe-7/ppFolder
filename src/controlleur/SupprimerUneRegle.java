package controlleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.ppFolderView;

class SupprimerUneRegle implements ActionListener {
	
	private ppFolderView vue;
	
	public void actionPerformed(ActionEvent e) {
		try {
			vue.getPpFolderInterface().remove(vue.getPanelAjoutRegle());
			vue.getPpFolderInterface().remove(vue.getPanelScrollableFichiers());
	    	
			vue.init_list();
	    	
	    	vue.getPpFolderInterface().add(vue.getPanelSuppressionRegle());
	    	vue.getPpFolderInterface().revalidate();
	    	vue.getPpFolderInterface().repaint();
		}catch (Exception exeption) {
			vue.displayErrorMessage("You Need to Enter a correct Path file !");
		}
	}
}