package Vue;

import Controleur.*;
import java.io.*;

/**
 * La sauvegarde
 * @author rohautb
 * 
 */

public class Sauvegarde {
	File[] slot = new File[10];
	

	@SuppressWarnings("null")
	
	/**
	 * Creation des emplacements de sauvegarde
	 */
	
	public Sauvegarde(){
		String chemin = System.getProperty("user.dir"); //+ "/saves"
		File[] files = new File(chemin).listFiles();
		String name;
		char[] numero;
		int num = 1;
		for (File f : files){
			numero = null;
			name = f.getName();
			name.getChars(0, 1, numero, 0);
			num = (int)numero[0];
			if( name.endsWith(".ser") && num >=0 && num <= 9 ){
				slot[num] = f;
			}
		}
		
	}
	
	/**
	 * Sauvegarder la partie p au slot i 
	 * @param p la partie a sauvegarder
	 * @param i le numero de l'emplacement de sauvegarde
	 */
	
	public void Sauvegarder(Partie p, int i){
		try{
			File f = new File(i+1 + "." + p.getJoueur1().getNom() + "-vs-" + p.getJoueur2().getNom() + ".ser");
			FileOutputStream fos = new FileOutputStream(i + ". " + p.getJoueur1().getNom() + " vs " + p.getJoueur2().getNom() + ".ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(p);
			oos.close();
			slot[i] =  f;
		}
		catch(IOException|NullPointerException e){
			System.out.println("Erreur lors de la sauvegarde du fichier");
		}
	}
	
	/**
	 * Charger la partie au slot i
	 * @param i le numero de l'emplacement de sauvegarde
	 * @return null
	 */
	
	public Partie Charger(int i){
		try{
			FileInputStream fis = new FileInputStream(slot[i].getName());
			ObjectInputStream ois = new ObjectInputStream(fis);
			Object p = ois.readObject();
			ois.close();
			if( p instanceof Partie ){
				return (Partie)p;
			}
		}
		catch(IOException|NullPointerException|ClassNotFoundException e){
			System.out.println("Erreur lors du chargement du fichier");
		}
		return null;
	}
}