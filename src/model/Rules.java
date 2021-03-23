package model;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Rules<rule> implements Serializable, Iterable<rule> {
	
	private static final long serialVersionUID = -6727582880792299950L;
	private List<rule> regles = new ArrayList<>();
	
	
	/**
	 * @param rule
	 * @return boolean
	 * addRule ==> permet d'ajouter une regle<?>
	 */
	public boolean addRule(rule rule) {
		if(regles.isEmpty()) {return regles.add(rule);}
		if(!regles.contains(rule)) {
			Iterator<rule> it = regles.iterator(); 
			List<rule> tmp = new ArrayList<>();
			while(it.hasNext()) {
				tmp.add(rule);
				tmp.addAll(regles);
				regles = tmp;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @param rule
	 * @return boolean
	 * removeRule ==> permet de supprimer une regle l'indiquant explicitement 
	 */
	public boolean removeRule(rule rule) { return regles.remove(rule);}
	
	/**
	 * @param index
	 * @return rule => String
	 * removeRule ==> permet de supprimer une regle en fonction de sa position dans la liste
	 */
	public rule removeRule(int index) {return regles.remove(index);}
	
	/**
	 * @param None
	 * @return rule => void
	 * removeRule ==> permet de vider la liste des regles
	 */
	public void destroyRules() {
		regles.removeAll(regles);
		this.readSerializedRules();
	}
	
	/**
	 * @return String
	 * @param null
	 * getRules ==> afficher l'ensemble des regles ajouté
	 */
	public String getRules(){return regles.toString();}
	
	/**
	 * @return void
	 * @param rules
	 * serializeRules ==> permet de serializer l'objet Rules<?>
	 */
	public void serializeRules() {
	    ObjectOutputStream oos = null;
	    try {
	      final FileOutputStream fichier = new FileOutputStream("Rules.ser");
	      oos = new ObjectOutputStream(fichier);
	      oos.writeObject(this);
	      oos.flush();
	    } catch (final java.io.IOException e) {
	      e.printStackTrace();
	    } finally {
	      try {
	        if (oos != null) {
	          oos.flush();
	          oos.close();
	        }
	      } catch (final IOException ex) {
	        ex.printStackTrace();
	      }
	    }
	  }
	
	/**
	 * @return void 
	 * @param null
	 * readSerializedRules ==> permet de lire un fichier serializé
	 */
	public List<rule> readSerializedRules() {
		ObjectInputStream ois = null;
	    try {
	    	// LE SEUL FICHIER OU SONT SAUVEGARDER LES REGLES
	    	final FileInputStream fichier = new FileInputStream("Rules.ser");
	    	ois = new ObjectInputStream(fichier);
	    	@SuppressWarnings("unchecked")
			final Rules<rule> rule = (Rules<rule>) ois.readObject();
	    	return rule.regles;
	    } catch (final java.io.IOException e) {
	    	e.printStackTrace();
	    } catch (final ClassNotFoundException e) {
	    	e.printStackTrace();
	    } finally {
	    	try {
	    		if (ois != null) {ois.close();}
	      } catch (final IOException ex) {
	    	  ex.printStackTrace();
	      }
	    }
		return null;
	}

	@Override
	public Iterator<rule> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
//	public static void main(String[] args) {
//		Rules<String> rule = new Rules<>();
////		rule.regles = rule.readSerializedRules();
//		
//		rule.addRule("php");
//		rule.addRule("java, class");
//		rule.addRule("pdf");
//		rule.addRule("txt");
//		rule.addRule("zip");
//		
//		rule.serializeRules();
//		System.out.println(rule.readSerializedRules());
//		
//		System.out.println("1");
//		System.out.println(rule.getRules());
//	}
	
}