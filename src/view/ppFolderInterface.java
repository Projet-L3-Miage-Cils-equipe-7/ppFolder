package view;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class ppFolderInterface extends JFrame {
	
	ArrayList<String> regles = new ArrayList<String>();
	private static final long serialVersionUID = 1L;
	JPanel Pregles;
	JPanel panelStart;
	JPanel panelFichier;
	JScrollPane centre;
	JPanel contentPane;
	boolean accueil =false;
	
	//partie reste règles
	public ppFolderInterface(){
		super("ppFolder v1.1.9");
		for(int i=0; i<10;i++) {regles.add("regle "+ String.valueOf(i));}
		
		int largeur_regle = 200;
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE );
		this.setSize(1100,700);
		this.setLocationRelativeTo(null);
		
		contentPane = (JPanel) this.getContentPane();
		centre = new JScrollPane(fenetre());
		contentPane.add(centre);
		contentPane.setPreferredSize(new Dimension(600,768));
		
		
		// Logo partie supérieur de l'application
		JLabel label_ppf = new JLabel();
		label_ppf.setIcon(new ImageIcon("images/ppFolder.png"));
		contentPane.add(label_ppf,BorderLayout.NORTH);
		contentPane.setPreferredSize(new Dimension(800,200));
		
		JPanel Pregles = new JPanel(new GridLayout(regles.size(),1));
		for(String e : regles) {Pregles.add(new JButton(e)).setPreferredSize(new Dimension(150,50));}
		
		//Panel des régles
		JScrollPane SPregles = new JScrollPane(Pregles);
		SPregles.setPreferredSize(new Dimension(largeur_regle,600));
		contentPane.add(SPregles,BorderLayout.EAST);

	}
	
	private JPanel fenetre() {
		JPanel jpanel = new JPanel(new GridLayout((regles.size()/3)+1,3));
		{
			if(accueil) {
				for (String rule : regles) {
					jpanel.add(new JButton(new ImageIcon("images/Folder-icon-256.png")));
				}
			}else {
				jpanel.setLayout(new WrapLayout());
				jpanel.add(new JTextField("entrer le chemin vers le dossier a trier")).setPreferredSize(new Dimension(400,50));
				jpanel.add(new JButton(new ImageIcon("images/unnamed-2.png"))).setSize(new Dimension(300, 1));
			}
			return jpanel;
		}
	}
	
    public void actionPerformed(ActionEvent e){
    	accueil =true;
    	contentPane.remove(centre);
    	centre = new JScrollPane(fenetre());
    	contentPane.add(centre);
        this.revalidate();
        this.repaint();
    }
	
	public static void main(String[] args) throws UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(new NimbusLookAndFeel());
		ppFolderInterface myWindow = new ppFolderInterface();
		myWindow.setVisible(true);
	}
}