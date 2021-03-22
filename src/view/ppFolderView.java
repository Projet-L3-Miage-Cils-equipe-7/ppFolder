package view;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ppFolderView extends JFrame{

	private static final long serialVersionUID = 1L;
	
	List<String> regles = new ArrayList<String>();
	int largeur_regle = 200;
	
	// les pannels
	private JPanel ppFolderInterface;
	private JPanel fenetreDepart = new JPanel(); // panel pour saisir le chemin vers le dossier
	private JPanel panelRegles; // panel pour afficher les regles encré a droite
	private JPanel panelFichiers; // pannel pour afficher les fichiers partie centrale
	
	private JPanel panelAjoutRegle;
	private JPanel panelSuppressionRegle;
	
	// champ de texte aveec un placeHolder
	private PlaceholderTextField pathDossierField;
	
	// les pannel scrollable
	private JScrollPane PanelScrollableFichiers;
	private JScrollPane PannelScrollableRegles;
	
	// les boutons
	private JButton supprimerRegle;
	private JButton allerAAjouterRegle;
	private JButton ajouterUneRegle;
	private JButton BoutonValiderPath;
	
	// champs de texte
	private JTextField ajout;
	
	// JComboBox (select en html)
	private JComboBox<String> selectRegle_list;
	private DefaultComboBoxModel<String> modelRegle;
	
	@SuppressWarnings("unused")
	public ppFolderView() {
		
		//création de la fenetre
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE );
		this.setSize(1100, 700);
		this.setLocationRelativeTo(null);
		ppFolderInterface = (JPanel) this.getContentPane();
		ppFolderInterface.setLayout(new BorderLayout());

		for (String rules : getRegles()) {panelFichiers.add(new JButton(new ImageIcon("images/Folder-icon-256.png")));}
		
		/**
		 * Logo partie superieur de l'application
		*/ 
		JLabel label_ppf = new JLabel();
		label_ppf.setPreferredSize(new Dimension(1100,100));
		label_ppf.setBackground(Color.blue);
		label_ppf.setIcon(new ImageIcon("images/ppFolder.png"));
		ppFolderInterface.add(label_ppf,BorderLayout.NORTH);
				
		/**
		 * Panel de depart
		 * Donner le chemin absolue du dossier a tirer
		 * Bouton valider !
		 */
		pathDossierField = new PlaceholderTextField("");
		getPathDossierField().setPreferredSize(new Dimension(400, 50));
		getPathDossierField().setColumns(20);
		getPathDossierField().setPlaceholder("Donner le chemin d'un dossier à trier !");
        final Font f = getPathDossierField().getFont();
        getPathDossierField().setFont(new Font(f.getName(), f.getStyle(), 30));

		JButton BoutonValiderPath = new JButton(new ImageIcon("images/unnamed-2.png"));
		BoutonValiderPath.setSize(new Dimension(300, 1));
		this.getFenetreDepart().add(getPathDossierField());
		this.getFenetreDepart().add(BoutonValiderPath);
		this.getPpFolderInterface().add(this.getFenetreDepart(), BorderLayout.CENTER);
		
		/**
		 * Panel des regles de droite
		 */
		panelRegles = new JPanel(new GridLayout(getRegles().size()+2,1));
		allerAAjouterRegle = new JButton("Ajouter règle");
		supprimerRegle = new JButton("Supprimer règle");
		
		PannelScrollableRegles = new JScrollPane(ajouter_boutons_regle());
		PannelScrollableRegles.setPreferredSize(new Dimension(getLargeur_regle(), 600));
		
		panelAjoutRegle = new JPanel();
		ajout = new JTextField("Rentrer le nom de règle à ajouter");
		ajouterUneRegle = new JButton("Valider");
		
		panelAjoutRegle.add(ajout);
		panelAjoutRegle.add(ajouterUneRegle);
		
		
		panelSuppressionRegle = new JPanel();
		JLabel texte_suppression = new JLabel("Choisissez la règle a supprimer");
		panelSuppressionRegle.add(texte_suppression);
		selectRegle_list = new JComboBox<String>();
		modelRegle = new DefaultComboBoxModel<String>();
		
		init_list();
		
		getSelectRegle_list().setModel(modelRegle);
		panelSuppressionRegle.add(getSelectRegle_list());
		
		
	}
	
//	::::::::::::::::::::::::: debut des getters/setters :::::::::::::::::::::::::::::
	
	/**
	 * @return {@link JPanel}
	 * retourne la fenetre de Depart (text + bouton pour valider le path du dossier)
	 */
	public JPanel getFenetreDepart() {return fenetreDepart;}
	
	/**
	 * @return the pathDossierField
	 */
	public PlaceholderTextField getPathDossierField() {return pathDossierField;}

	/**
	 * @return {@link JScrollPane}
	 * retourne le panel de regles de droite
	 */
	public JScrollPane getPannelScrollableRegles() {return PannelScrollableRegles;}
	
	public void setPannelScrollableRegles(JScrollPane e) {this.PannelScrollableRegles = e;}
	
	/**
	 * @return {@link JScrollPane}
	 * retourne le panel des fichiers du centre
	 */
	public JScrollPane getPanelScrollableFichiers() {return PanelScrollableFichiers;}
	
	/**
	 * @return {@link JPanel}
	 * l'interface ppFolderJPanel
	 */
	public JPanel getPpFolderInterface() {return this.ppFolderInterface;}
	
	/**
	 * @return {@link JButton}
	 */
	public JButton getBoutonValiderPath() {return BoutonValiderPath;}

	/**
	 * @return {@link JPanel}
	 */
	public JPanel getPanelAjoutRegle() {return panelAjoutRegle;}
	
	/**
	 * @return {@link JPanel}
	 */
	public JPanel getPanelSuppressionRegle() {return panelSuppressionRegle;}
	
	/**
	 * @return the {@link List}
	 */
	public List<String> getRegles() {return regles;}

	/**
	 * @return {@link JTextField}
	 */
	public JTextField getAjout() {return ajout;}

	/**
	 * @return {@link DefaultComboBoxModel}
	 */
	public DefaultComboBoxModel<String> getModelRegle() {	return modelRegle;}
	
	/**
	 * @return {@link JComboBox}
	 */
	public JComboBox<String> getSelectRegle_list() {return selectRegle_list;}

	/**
	 * @return the largeur_regle
	 */
	public int getLargeur_regle() {return largeur_regle;}
	
//	::::::::::::::::::::::::: fin des getters/setters :::::::::::::::::::::::::::::
	
//	::::::::::::::::::::::::::: Debut des listeners :::::::::::::::::::::::::::::::
	
	/**
	 * @param listenerPourValiderUnPath
	 * listener pour valider un path entrer par l'utilisateur
	 */
	public void ajouterUnPathListener(ActionListener listenerPourValiderUnPath){
		this.getBoutonValiderPath().addActionListener(listenerPourValiderUnPath);
	}
	

	/**
	 * @param listenerAjouterUneRegle
	 * listener pour aller dans le panel ajouter une régle
	 */
	public void allerAajouteruneregleListener(ActionListener listenerAjouterUneRegle){
		allerAAjouterRegle.addActionListener(listenerAjouterUneRegle);
	}
	
	/**
	 * @param listenerPourSupprimerUneRegle
	 * listener pour supprimer une regle
	 */
	public void supprimerUneRegleListener(ActionListener listenerPourSupprimerUneRegle){
		supprimerRegle.addActionListener(listenerPourSupprimerUneRegle);
	}
	
	/**
	 * @param listenerPourSupprimerUneRegle
	 * listener pour ajouter une regle
	 */
	public void ajouterUneReglelistener(ActionListener listenerAjouterUneRegle){
		ajouterUneRegle.addActionListener(listenerAjouterUneRegle);
	}
	
	public void miseAjourListeRegleListener(ActionListener listenerListRegleUpdate) {
		getSelectRegle_list().addActionListener(listenerListRegleUpdate);
	}
	
//	::::::::::::::::::::::::::: Fin des listeners :::::::::::::::::::::::::::::::::
	
	/**
	 * @return {@link JPanel}
	 */
	public JPanel ajouter_boutons_regle() {
		this.panelRegles.removeAll();
		this.panelRegles.add(allerAAjouterRegle).setPreferredSize(new Dimension(150,50));
		this.panelRegles.add(supprimerRegle).setPreferredSize(new Dimension(150,50));
		for(String e : getRegles()) {panelRegles.add(new JButton(e)).setPreferredSize(new Dimension(150,50));}
		return this.panelRegles;
	}
	
	/**
	 * init a list of rule in the DefaultComboBoxModel
	 */
	public void init_list() {
		for(String rule : this.getRegles()) {this.getModelRegle().addElement(rule);}
	}
	
	/**
	 * @param rule
	 * @return {@link Component}
	 */
	public Component refresh_regle(String rule) {
		JButton tmp = new JButton(rule);
		tmp.setPreferredSize(new Dimension(150,50));
		return tmp;
	}
	
	/**
	 * @param errorMessage
	 * => Open a popup that contains the error message passed
	 */
	public void displayErrorMessage(String errorMessage){
		JOptionPane.showMessageDialog(this, errorMessage);
	}
}