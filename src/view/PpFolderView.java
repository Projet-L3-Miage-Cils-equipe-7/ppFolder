package view;

import javax.swing.*;
import model.Classifieur;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import view.PpFolderView;

public class PpFolderView extends JFrame{

	private static final long serialVersionUID = -1135505697208097249L;
	
	public List<String> regles = new ArrayList<String>();
	public List<String> regles_selectionnees = new ArrayList<String>();
	
	private int largeurPanelRegle = 250;
	
	private int longeurRegle = 200;
	private int hauteurRegle = 50;
	
	public ImageIcon logo;
	public ImageIcon btnValider;
	public ImageIcon fileIcon;
	
	// les pannels
	private JPanel ppFolderInterface ;
	private JPanel fenetreDepart = new JPanel(); // panel pour saisir le chemin vers le dossier
	private JPanel panelRegles; // panel pour afficher les regles encré a droite
	private JPanel panelFichiers; // pannel pour afficher les fichiers partie centrale
	private JPanel panelRegleUtiliser;
	private JPanel panelbas;
	
	private JPanel panelAjoutRegle;
	private JPanel panelSuppressionRegle;
	
	// champ de texte aveec un placeHolder
	private PlaceholderTextField pathDossierField;
	private PlaceholderTextField ajout;
	
	// les pannel scrollable
	private JScrollPane PanelScrollableFichiers;
	private JScrollPane PannelScrollableRegles;
	private JScrollPane PannelScrollableReglesUtilisees;
	
	// les boutons
	private JButton supprimerRegle;
	private JButton allerAAjouterRegle;
	private JButton ajouterUneRegle;
	private JButton BoutonValiderPath;
	private JButton lancerTrier;
	
	// JComboBox (select en html)
	private JComboBox<String> selectRegle_list;
	private DefaultComboBoxModel<String> modelRegle;
	
	public PpFolderView() {
		super("ppFolder v1.1.16");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE );
		this.setSize(1500, 900);
		
		logo = new ImageIcon(getClass().getClassLoader().getResource("ppFolder.png"));
		btnValider = new ImageIcon(getClass().getClassLoader().getResource("validate-btn.png"));
		fileIcon = new ImageIcon(getClass().getClassLoader().getResource("Folder-icon-256.png"));
		
		this.setLocationRelativeTo(null);
		ppFolderInterface = new JPanel();
		ppFolderInterface = (JPanel) this.getContentPane();
		ppFolderInterface.setLayout(new BorderLayout());
		
		
		
		if(new File("Rules.ser").exists()) {
			this.regles = Classifieur.getRule().readSerializedRules();
		}else {
			this.regles.add("pdf");
			this.regles.add("zip");
			this.regles.add("png");
		}
		
		panelFichiers = new JPanel(new GridLayout((regles.size()/4)+1, 4));
		
		ppFolderInterface.setPreferredSize(new Dimension(1100, 700));
				
		/**
		 * Panel de depart
		 * Donner le chemin absolue du dossier a tirer
		 * Bouton valider !
		 */
		
		pathDossierField = new PlaceholderTextField("");
		pathDossierField.setPreferredSize(new Dimension(400, getHauteurRegle()));
		pathDossierField.setColumns(20);
		pathDossierField.setPlaceholder("Donner un chemin...");
        final Font f = pathDossierField.getFont();
        pathDossierField.setFont(new Font(f.getName(), f.getStyle(), 30));

		BoutonValiderPath = new JButton(btnValider);
		BoutonValiderPath.setSize(new Dimension(300, 1));
		fenetreDepart.add(pathDossierField);
		fenetreDepart.add(BoutonValiderPath);
		
		ppFolderInterface.add(fenetreDepart, BorderLayout.CENTER);
		
		setPanelbas(new JPanel());
		getPanelbas().setLayout(new BorderLayout());
		getPanelbas().setSize(new Dimension(1200, 200));
		
		panelRegleUtiliser = new JPanel();
		panelRegleUtiliser.setLayout(new GridLayout(1, regles_selectionnees.size()+1));
		lancerTrier = new JButton("Lancer tri");
		lancerTrier.setPreferredSize(new Dimension(250, 180));
		PannelScrollableReglesUtilisees = new JScrollPane(panelRegleUtiliser);
		
		getPannelScrollableReglesUtilisees().setPreferredSize(new Dimension(1100, 200));
		
		ppFolderInterface.add(getPanelbas(), BorderLayout.SOUTH);
		
		if(regles_selectionnees.isEmpty()) {lancerTrier.setEnabled(false);}
		
		getPanelbas().add(lancerTrier,BorderLayout.EAST);
		getPanelbas().add(getPannelScrollableReglesUtilisees(), BorderLayout.CENTER);
		
		this.initView();
		
		/**
		 * Panel des regles de droite
		 */
		panelRegles = new JPanel(new GridLayout(regles.size()+2, 1));
		allerAAjouterRegle = new JButton("Ajouter regle");
		supprimerRegle = new JButton("Supprimer regle");

		PannelScrollableRegles = new JScrollPane(panelRegles);
		PannelScrollableRegles.setPreferredSize(new Dimension(getlargeurPanelRegle(), 600));
		
		// zone de texte pour ajouter une regle
		ajout = new PlaceholderTextField("");
		ajout.setPreferredSize(new Dimension(longeurRegle, getHauteurRegle()));
		ajout.setColumns(10);
		ajout.setPlaceholder("Ajouter une regle...");
        final Font f1 = ajout.getFont();
        ajout.setFont(new Font(f1.getName(), f1.getStyle(), 30));
		ajouterUneRegle = new JButton(btnValider);
		
		panelAjoutRegle = new JPanel();
		panelAjoutRegle.add(ajout);
		panelAjoutRegle.add(ajouterUneRegle);
		
		panelSuppressionRegle = new JPanel();
		JLabel texte_suppression = new JLabel("Choisissez la regle a supprimer");
		panelSuppressionRegle.add(texte_suppression);
		selectRegle_list = new JComboBox<String>();
		modelRegle = new DefaultComboBoxModel<String>();
		
		clear_panelbas();
		this.init_list();
		
		getSelectRegle_list().setModel(modelRegle);
		panelSuppressionRegle.add(getSelectRegle_list());
		this.setVisible(true);
	}
	
//	::::::::::::::::::::::::::: Debut des listeners :::::::::::::::::::::::::::::::
	
	/**
	 * @param listenerPourValiderUnPath
	 * listener pour valider un path entrer par l'utilisateur
	 */
	public void ajouterUnPathListener(ActionListener listenerPourValiderUnPath){
		BoutonValiderPath.addActionListener(listenerPourValiderUnPath);
	}

	/**
	 * @param listenerAjouterUneRegle
	 * listener pour aller dans le panel ajouter une régle
	 */
	public void allerAajouteruneregleListener(ActionListener listenerAjouterUneRegle){
		getAllerAAjouterRegle().addActionListener(listenerAjouterUneRegle);
	}
	
	/**
	 * @param listenerPourSupprimerUneRegle
	 * listener pour supprimer une regle
	 */
	public void allerASupprimerUneRegleListener(ActionListener listenerPourSupprimerUneRegle){
		getSupprimerRegle().addActionListener(listenerPourSupprimerUneRegle);
	}
	
	/**
	 * @param listenerPourSupprimerUneRegle
	 * listener pour ajouter une regle
	 */
	public void ajouterUneReglelistener(ActionListener listenerAjouterUneRegle){
		ajouterUneRegle.addActionListener(listenerAjouterUneRegle);
	}
	
	/**
	 * @param listenerListRegleUpdate
	 * mise a jours des regles !
	 */
	public void supprimerUneRegleListener(ActionListener listenerListRegleUpdate) {
		getSelectRegle_list().addActionListener(listenerListRegleUpdate);
	}
	
	/**
	 * listener sur le {@link JButton} permet de lancer le tri avec les regles selectionnées
	 * @param lancerLeTri
	 */
	public void lancerLeTri(ActionListener lancerLeTri) {getLancerTrier().addActionListener(lancerLeTri);}
	
//	::::::::::::::::::::::::::: Fin des listeners :::::::::::::::::::::::::::::::::
	
//	::::::::::::::::::::::::: debut des getters/setters :::::::::::::::::::::::::::::
	
	/**
	 * @return
	 */
	public JPanel getpanelRegleUtiliser() {return panelRegleUtiliser;}

	/**
	 * @return {@link JPanel}
	 * retourne la fenetre de Depart (text + bouton pour valider le path du dossier)
	 */
	public JPanel getFenetreDepart() {return fenetreDepart;}
	
	/**
	 * @return the pathDossierField
	 */
	public PlaceholderTextField getpathDossierField() {return pathDossierField;}

	/**
	 * @return {@link JScrollPane}
	 * retourne le panel de regles de droite
	 */
	public JScrollPane getPannelScrollableRegles() {
		return PannelScrollableRegles;
	}
	
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
	 * @param panel
	 * affecte l'interface
	 */
	public void setPpFolderInterface(JPanel panel) {this.ppFolderInterface = panel;}
	
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
	 * @return the largeurPanelRegle
	 */
	public int getlargeurPanelRegle() {return getLargeurPanelRegle();}
	
	/**
	 * @return {@link JPanel}
	 */
	public JPanel getPanelRegles() {return panelRegles;}
	
	/**
	 * @return {@link Void}
	 */
	public void clear_panelbas() {panelbas.removeAll();}

	/**
	 * 
	 * @return {@link JPanel}
	 */
	public JPanel getPanelbas() {return panelbas;}

	/**
	 * @return {@link Void}
	 * @param panelbas
	 */
	public void setPanelbas(JPanel panelbas) {this.panelbas = panelbas;}

	/**
	 * @return {@link Integer}
	 */
	public int getLargeurPanelRegle() {return largeurPanelRegle;}

	/**
	 * @return {@link Integer}
	 */
	public int getHauteurRegle() {return hauteurRegle;}

	/**
	 * @return JButton
	 */
	public JButton getSupprimerRegle() {return supprimerRegle;}

	/**
	 * @return {@link JButton}
	 */
	public JButton getAllerAAjouterRegle() {return allerAAjouterRegle;}

	/**
	 * @return {@link JScrollPane}
	 */
	public JScrollPane getPannelScrollableReglesUtilisees() {return PannelScrollableReglesUtilisees;}

	/**
	 * @return {@link JButton}
	 */
	public JButton getLancerTrier() {return lancerTrier;}
	
	
//	::::::::::::::::::::::::: fin des getters/setters :::::::::::::::::::::::::::::
	
	/**
	 * @param rule
	 * @return {@link Component}
	 */
	public Component refresh_regle(String rule) {
		JButton tmp = new JButton(rule);
		tmp.setPreferredSize(new Dimension(longeurRegle, getHauteurRegle()));
		return tmp;
	}
	
	/**
	 * @return {@link Void}
	 * ajoute le Logo dans partie superieur de l'application
	*/ 
	public void initView() {
		JLabel label_ppf = new JLabel();
		label_ppf.setPreferredSize(new Dimension(1100, 100));
		label_ppf.setBackground(Color.blue);
		label_ppf.setIcon(logo); // image du logo
		ppFolderInterface.add(label_ppf, BorderLayout.NORTH);
	}
	
	/**
	 * @return {@link Void}
	 */
	public void dessin_ajoutregle() {ppFolderInterface.add(panelAjoutRegle,BorderLayout.CENTER);}
	
	/**
	 * @return {@link Void}
	 */
	public void init_list() {
		this.getModelRegle().removeAllElements();
		for(String rule : this.regles) {this.getModelRegle().addElement(rule);}
		getSelectRegle_list().setModel(modelRegle);
	}
	
	/**
	 * @param errorMessage
	 * => Open a popup that contains the error message passed
	 */
	public void displayErrorMessage(String errorMessage){JOptionPane.showMessageDialog(this, errorMessage);}

	/**
	 * @return the panelFichiers
	 */
	public void setPanelFichiers(JPanel newPanelFichiers) {panelFichiers = newPanelFichiers;}
	
	/**
	 * 
	 * @param newPanelFichiers
	 */
	public JPanel getPanelFichiers() {return panelFichiers;}

	/**
	 * @param panelScrollableFichiers the panelScrollableFichiers to set
	 */
	public void setPanelScrollableFichiers(JScrollPane panelScrollableFichiers) {PanelScrollableFichiers = panelScrollableFichiers;}

	/**
	 * @return the fileIcon
	 */
	public ImageIcon getFileIcon() {
		return fileIcon;
	}
}