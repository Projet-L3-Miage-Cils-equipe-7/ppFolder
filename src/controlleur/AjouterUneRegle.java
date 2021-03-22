package controlleur;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.ppFolderView;

class AjouterUneRegle implements ActionListener {
	
	private ppFolderView vue;
	
	public void actionPerformed(ActionEvent e) {
		try {
			vue.getRegles().add(vue.getAjout().getText());
			vue.getPannelScrollableRegles().add(vue.refresh_regle(vue.getAjout().getText()));
			vue.getPannelScrollableRegles().setLayout(new GridLayout(vue.getRegles().size()+2,1));
			
			vue.getPpFolderInterface().remove(vue.getPanelAjoutRegle());
			vue.getPpFolderInterface().add(vue.getPanelScrollableFichiers());
			vue.getPpFolderInterface().revalidate();
			vue.getPpFolderInterface().repaint();
		}catch (Exception exeption) {
			vue.displayErrorMessage("You Need to Enter a correct Path file !");
		}
	}
}