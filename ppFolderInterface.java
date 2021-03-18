package graphisme_projet;


import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ppFolderInterface extends JFrame implements ActionListener{
	
	ArrayList<String> regles = new ArrayList<String>();
	private static final long serialVersionUID = 1L;
	JPanel Pregles;
	JPanel panelFichier;
	JPanel panelStart = new JPanel() ;
	JPanel contentPane;
	JScrollPane Pfichier;
	JScrollPane SPregles;
	JButton bouton_supprimer;
	JButton bouton_ajouter;
	JPanel ajout_regle;
	JPanel Suppression;
	JTextField ajout;
	JButton ajouter_regle;
	JComboBox liste_regle;
	DefaultComboBoxModel modele_regle;
	//partie reste règles
	public ppFolderInterface(){
		//création fenetre
		super("Application ppFolder");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE );
		this.setSize(1100,700);
		this.setLocationRelativeTo(null);
		contentPane = (JPanel) this.getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		
		
		int largeur_regle = 200;
		for(int i=0; i<10;i++) {
			regles.add("regle "+ String.valueOf(i));
		}
		
		

		//partie centrale
		
		//Le panel fichier regroupe l'ensemble des fichiers de règles 
		//et pfichier permet de parcourir panelfichier avec une barre de scroll
		panelFichier = new JPanel(new GridLayout((regles.size()/3)+1,3));
		JScrollPane Pfichier = new JScrollPane(panelFichier);
		contentPane.setPreferredSize(new Dimension(600,768));
		
		for (String e : regles) {
			panelFichier.add(new JButton(new ImageIcon("images/Folder-icon-256.png")));
			}
		
		//panel start
		JTextField saisir_chemin = new JTextField("entrer le chemin vers le dossier a trier");
		saisir_chemin.setPreferredSize(new Dimension(400,50));
		JButton bouton_valider = new JButton(new ImageIcon("images/unnamed-2.png"));
		bouton_valider.setSize(new Dimension(300, 1));
		panelStart.add(saisir_chemin);
		panelStart.add(bouton_valider);
		contentPane.add(panelStart,BorderLayout.CENTER);
		bouton_valider.addActionListener( new ActionListener()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		    	System.out.println("La règle a ajouter est : "+saisir_chemin.getText());
		    	contentPane.remove(panelStart);
			 	contentPane.add(Pfichier);
			 	contentPane.add(SPregles,BorderLayout.EAST);
			 	contentPane.revalidate();
		        contentPane.repaint();
		    }});

	   
		
	

		
		// Logo partie superieur de l'application
		JLabel label_ppf = new JLabel();
		label_ppf.setPreferredSize(new Dimension(1100,100));
		label_ppf.setBackground(Color.blue);
		label_ppf.setIcon(new ImageIcon("images/ppFolderp.png"));
		contentPane.add(label_ppf,BorderLayout.NORTH);

		//Panel des regles de droite
		Pregles = new JPanel(new GridLayout(regles.size()+2,1));
		bouton_ajouter = new JButton("Ajouter règle");
		bouton_supprimer = new JButton("Supprimer règle");
		Pregles.add(bouton_ajouter).setPreferredSize(new Dimension(150,50));
		Pregles.add(bouton_supprimer).setPreferredSize(new Dimension(150,50));
		
		for(String e : regles) {Pregles.add(new JButton(e)).setPreferredSize(new Dimension(150,50));}
		SPregles = new JScrollPane(Pregles);
		SPregles.setPreferredSize(new Dimension(largeur_regle,600));
		
		bouton_ajouter.addActionListener( new ActionListener()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		    	contentPane.remove(Pfichier);
		    	contentPane.add(ajout_regle);
			 	contentPane.revalidate();
		        contentPane.repaint();
		    }});
		bouton_supprimer.addActionListener( new ActionListener()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		    	contentPane.remove(ajout_regle);
		    	contentPane.remove(Pfichier);
		    	init_list();
		    	contentPane.add(Suppression);
			 	contentPane.revalidate();
		        contentPane.repaint();
		    }});
		
		//ajout de regle
		ajout_regle = new JPanel();
		ajout = new JTextField("Rentrer le nom de règle à ajouter");
		ajouter_regle = new JButton("Valider");
		ajouter_regle.addActionListener( new ActionListener()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		    	System.out.println("La règle a ajouter est : "+ajout.getText());
		    	regles.add(ajout.getText());
		    	Pregles.setLayout(new GridLayout(regles.size()+2,1));
		    	Pregles.add(refresh_regle(ajout.getText()));
		    	
		    	contentPane.remove(ajout_regle);
		    	contentPane.add(Pfichier);
			 	contentPane.revalidate();
		        contentPane.repaint();
		    }});
		ajout_regle.add(ajout);
		ajout_regle.add(ajouter_regle);
		
		
		
		
		
		//supprimer regle
		bouton_supprimer.addActionListener( new ActionListener()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		    	

		    }});
		Suppression = new JPanel();
		JLabel texte_suppression = new JLabel("Choisissez la règle a supprimer");
		Suppression.add(texte_suppression);
		liste_regle = new JComboBox();
		modele_regle = new DefaultComboBoxModel();
		init_list();
		liste_regle.setModel(modele_regle);
		Suppression.add(liste_regle);
		
		
		
}


	private void init_list() {
	for(String r : regles) {
		modele_regle.addElement(r);
	}
	}
	
	private JButton refresh_regle(String rule) {
		JButton tmp = new JButton(rule);
		tmp.setPreferredSize(new Dimension(150,50));
		return tmp;
	}
	    
	
	public static void main(String[] args) throws UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(new NimbusLookAndFeel());
		ppFolderInterface myWindow = new ppFolderInterface();
		myWindow.setVisible(true);
	}






}