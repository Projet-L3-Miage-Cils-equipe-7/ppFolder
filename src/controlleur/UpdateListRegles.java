package controlleur;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JScrollPane;
import view.ppFolderView;

class UpdateListRegles implements ActionListener {
	
	private ppFolderView vue;
	
	public void actionPerformed(ActionEvent e) {
		try {
			if(vue.getSelectRegle_list().getSelectedItem() != null) {
	    		vue.getRegles().remove(vue.getSelectRegle_list().getSelectedItem());
	    		
	    		vue.init_list();
	    		
	    		vue.getPpFolderInterface().remove(vue.getPanelSuppressionRegle());
	    		vue.getPpFolderInterface().remove(vue.getPannelScrollableRegles());
		    	vue.setPannelScrollableRegles(new JScrollPane(vue.ajouter_boutons_regle()));
		    	vue.getPannelScrollableRegles().setPreferredSize(new Dimension(vue.getLargeur_regle(), 600));
		    	vue.getPpFolderInterface().add(vue.getPanelScrollableFichiers());
		    	vue.getPpFolderInterface().add(vue.getPannelScrollableRegles(), BorderLayout.EAST);
		    	vue.getPpFolderInterface().revalidate();
		    	vue.getPpFolderInterface().repaint();
	    	}
		}catch (Exception exeption) {
			vue.displayErrorMessage("You Need to Enter a correct Path file !");
		}
	}
}
