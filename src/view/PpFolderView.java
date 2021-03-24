package view;

import javax.swing.*;

import model.Classifieur;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import view.PpFolderView;

public class PpFolderView extends JFrame{

	private static final long serialVersionUID = 1L;
	
	public List<String> regles = new ArrayList<String>();
	public List<String> regles_selectionnees = new ArrayList<String>();
	
	private int largeurPanelRegle = 250;
	
	private int longeurRegle = 200;
	private int hauteurRegle = 50;
	
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
	
	@SuppressWarnings("unused")
	public PpFolderView() {
		super("ppFolder v1.1.16");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE );
		this.setSize(1500, 900);
		
		this.setLocationRelativeTo(null);
		ppFolderInterface = new JPanel();
		ppFolderInterface = (JPanel) this.getContentPane();
		ppFolderInterface.setLayout(new BorderLayout());
		

		
		this.regles = Classifieur.getRule().readSerializedRules();
		regles.add("1");
		regles.add("1");
		regles.add("1");
		regles.add("1");
		regles.add("1");
		
		
		regles_selectionnees.add("exemple");
		regles_selectionnees.add("exemple");

		
		panelFichiers = new JPanel(new GridLayout((regles.size()/4)+1, 4));
		
		ppFolderInterface.setPreferredSize(new Dimension(1100, 700));
				
		/**
		 * Panel de depart
		 * Donner le chemin absolue du dossier a tirer
		 * Bouton valider !
		 */
		
		pathDossierField = new PlaceholderTextField("");
		pathDossierField.setPreferredSize(new Dimension(400, hauteurRegle));
		pathDossierField.setColumns(20);
		pathDossierField.setPlaceholder("Donner un chemin...");
        final Font f = pathDossierField.getFont();
        pathDossierField.setFont(new Font(f.getName(), f.getStyle(), 30));

		BoutonValiderPath = new JButton(new ImageIcon("images/validate-btn.png"));
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
		PannelScrollableReglesUtilisees.setPreferredSize(new Dimension(1100, 200));
		
		ppFolderInterface.add(getPanelbas(), BorderLayout.SOUTH);
	
		
		getPanelbas().add(lancerTrier,BorderLayout.EAST);
		getPanelbas().add(PannelScrollableReglesUtilisees, BorderLayout.CENTER);
		
		this.initView();
		
		
		
		/**
		 * Panel des regles de droite
		 */
		panelRegles = new JPanel(new GridLayout(regles.size()+2, 1));
		allerAAjouterRegle = new JButton("Ajouter regle");
		supprimerRegle = new JButton("Supprimer regle");
		ajouter_boutons_regle();
		
		PannelScrollableRegles = new JScrollPane(panelRegles);
		PannelScrollableRegles.setPreferredSize(new Dimension(getlargeurPanelRegle(), 600));
		
		// zone de texte pour ajouter une regle
		ajout = new PlaceholderTextField("");
		ajout.setPreferredSize(new Dimension(longeurRegle, hauteurRegle));
		ajout.setColumns(10);
		ajout.setPlaceholder("Ajouter une regle...");
        final Font f1 = ajout.getFont();
        ajout.setFont(new Font(f1.getName(), f1.getStyle(), 30));
		ajouterUneRegle = new JButton(new ImageIcon("images/validate-btn.png"));
		
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
		allerAAjouterRegle.addActionListener(listenerAjouterUneRegle);
	}
	
	/**
	 * @param listenerPourSupprimerUneRegle
	 * listener pour supprimer une regle
	 */
	public void allerASupprimerUneRegleListener(ActionListener listenerPourSupprimerUneRegle){
		supprimerRegle.addActionListener(listenerPourSupprimerUneRegle);
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
	

	
	

	
//	::::::::::::::::::::::::::: Fin des listeners :::::::::::::::::::::::::::::::::
	
//	::::::::::::::::::::::::: debut des getters/setters :::::::::::::::::::::::::::::
	
	public JTextField getpanelRegleUtiliser() {
		// TODO Auto-generated method stub
		return null;
	}

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
	public int getlargeurPanelRegle() {return largeurPanelRegle;}
	
	/**
	 * @return {@link JPanel}
	 */
	public JPanel getPanelRegles() {return panelRegles;}
	
	
//	::::::::::::::::::::::::: fin des getters/setters :::::::::::::::::::::::::::::
	
	/**
	 * @return {@link JPanel}
	 */
	public void ajouter_boutons_regle() {
		panelRegles.removeAll();
		
		panelRegles.setPreferredSize(new Dimension(0, regles.size()*50));
		
		panelRegles.setLayout(new GridLayout(regles.size()+2, 1));
		
		panelRegles.add(allerAAjouterRegle).setPreferredSize(new Dimension(largeurPanelRegle, hauteurRegle));
		panelRegles.add(supprimerRegle).setPreferredSize(new Dimension(largeurPanelRegle, hauteurRegle));
		
		for(String rule : getRegles()) {
			JButton tmp = new JButton(rule);
			tmp.setPreferredSize(new Dimension(largeurPanelRegle, hauteurRegle));
			panelRegles.add(tmp);
			tmp.addActionListener(
		            new ActionListener() {
		                public void actionPerformed(ActionEvent e) {
		                	regles_selectionnees.add(tmp.getText());
		                	refresh_regle_selectionnees();
		                	revalidate();
		                	repaint();
		                }
		            });
		}
		
	}
	
	public void reset() {
		ppFolderInterface.removeAll();
		initView();
	}
	
	/**
	 * init a list of rule in the DefaultComboBoxModel
	 */

	
	/**
	 * @param rule
	 * @return {@link Component}
	 */
	public Component refresh_regle(String rule) {
		JButton tmp = new JButton(rule);
		tmp.setPreferredSize(new Dimension(longeurRegle, hauteurRegle));
		return tmp;
	}
	
	public void initView() {
		/**
		 * Logo partie superieur de l'application
		*/ 
		JLabel label_ppf = new JLabel();
		label_ppf.setPreferredSize(new Dimension(1100, 100));
		label_ppf.setBackground(Color.blue);
		label_ppf.setIcon(new ImageIcon("images/ppFolder.png")); // image du logo
		ppFolderInterface.add(label_ppf,BorderLayout.NORTH);
	}
	
	public void afficherFichiers() {
		panelFichiers.removeAll();
		
		panelFichiers.setLayout(new GridLayout((Classifieur.getListeOfPathsFiles().keySet().size()/4)+1, 4));
		
		for (String file : Classifieur.getListeOfPathsFiles().keySet()) {
            JButton tmp = new JButton(new ImageIcon("images/Folder-icon-256.png"));
            tmp.setPreferredSize(new Dimension(256,276));
            
            File path = new File(file).getAbsoluteFile();
            
            if(!path.isDirectory()) {
            	int index = file.lastIndexOf('/');
                String file1 = file.substring(index + 1);
                tmp.setText(file1);
                tmp.setVerticalTextPosition(SwingConstants.BOTTOM);
                tmp.setHorizontalTextPosition(SwingConstants.CENTER);
                panelFichiers.add(tmp);
            }
        }
		PanelScrollableFichiers = new JScrollPane(panelFichiers);
	}
	
	public void dessin_panel_fichier() {
		panelFichiers = new JPanel(new GridLayout((regles.size()/4)+1, 4));
		
		for(String e : regles) {
			  JButton tmp = new JButton(new ImageIcon("images/Folder-icon-256.png"));
	            tmp.setPreferredSize(new Dimension(256,276));
                tmp.setText(e);
                tmp.setVerticalTextPosition(SwingConstants.BOTTOM);
                tmp.setHorizontalTextPosition(SwingConstants.CENTER);
                panelFichiers.add(tmp);
		}
		
		
		PanelScrollableFichiers = new JScrollPane(panelFichiers);
		ppFolderInterface.add(PanelScrollableFichiers,BorderLayout.CENTER);
	}
	
	
	
	public void dessin_regles() {
		ajouter_boutons_regle();
		PannelScrollableRegles.setPreferredSize(new Dimension(getlargeurPanelRegle(), 600));
		ppFolderInterface.add(PannelScrollableRegles,BorderLayout.EAST);
	}
	
	
	public void dessin_ajoutregle() {
		ppFolderInterface.add(panelAjoutRegle,BorderLayout.CENTER);
	}
	
	
	public void refresh_scrollRegle(){
		ajouter_boutons_regle();
		PannelScrollableRegles = new JScrollPane(panelRegles);
	}
	
	public void refresh_regle_selectionnees() {

		panelRegleUtiliser.removeAll();
		panelRegleUtiliser.setLayout(new GridLayout(1, regles_selectionnees.size()+1));
		panelRegleUtiliser.setPreferredSize(new Dimension(regles_selectionnees.size()*300, 180));
		
		for(String s : regles_selectionnees) {
			JButton tmp = new JButton(s);
			tmp.setPreferredSize(new Dimension(200, 150));
			panelRegleUtiliser.add(tmp);
			tmp.addActionListener(
		            new ActionListener() {
		                public void actionPerformed(ActionEvent e) {
		                	panelRegleUtiliser.remove(tmp);
		                	regles_selectionnees.remove(tmp.getText());
		                	revalidate();
		                	repaint();
		                }
		            });
		}	

	}
	
	
	public void init_list() {
		this.getModelRegle().removeAllElements();
		for(String rule : this.regles) {this.getModelRegle().addElement(rule);}
		getSelectRegle_list().setModel(modelRegle);
		
	}
	/**
	 * @param errorMessage
	 * => Open a popup that contains the error message passed
	 */
	public void displayErrorMessage(String errorMessage){
		JOptionPane.showMessageDialog(this, errorMessage);
	}
	 
	
	
	public void dess_panelbas() {
		refresh_regle_selectionnees();
		panelbas.add(lancerTrier,BorderLayout.EAST);
		panelbas.add(PannelScrollableReglesUtilisees,BorderLayout.CENTER);
		ppFolderInterface.add(panelbas,BorderLayout.SOUTH);
	}
	
	
	public void clear_panelbas() {
		panelbas.removeAll();
	}
//	public static void main(String[] args) {
//		PpFolderView a = new PpFolderView();
//		a.setVisible(true);
//	}

	public JPanel getPanelbas() {
		return panelbas;
	}

	public void setPanelbas(JPanel panelbas) {
		this.panelbas = panelbas;
	}
}