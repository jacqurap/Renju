/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Controleur.Partie;

import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URLDecoder;

import javax.swing.*;
import Listener.PopupsListener;

/**
 *
 * @author bochatom
 */
public class Popups {

    private Fenetre f;
	private File[] slot = new File[10];
	private Partie part;
	private int SaveNum = 20;
	private String SaveName = "Sauvegarde";
	private int index;
	private boolean erase = false;
	private String SavePath;

    public Popups(Fenetre f) {
        this.f = f;
    }

    public void popRegles() {
        JDialog popRegle = new JDialog();
        popRegle.setTitle("Aide de jeu");

        JDialog.setDefaultLookAndFeelDecorated(true);
        popRegle.setSize(500, 200);
        popRegle.setLocationRelativeTo(null);
        popRegle.setLayout(new FlowLayout());
        popRegle.add(new JLabel("Coups interdits"));

        JButton btnRetour = new JButton("Retour");
        btnRetour.addActionListener(new PopupsListener(popRegle));
        popRegle.add(btnRetour);

        popRegle.setVisible(true);
    }

    public void popQuitterMenu() {
        JDialog popQuitterMenu = new JDialog();
        popQuitterMenu.setTitle("Quitter");

        JDialog.setDefaultLookAndFeelDecorated(true);

        popQuitterMenu.setSize(500, 200);
        popQuitterMenu.setLocationRelativeTo(null);
        popQuitterMenu.setLayout(new FlowLayout());

        JButton btnQuitter = new JButton("Quitter");
        btnQuitter.addActionListener(new PopupsListener(popQuitterMenu,f,1));

        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.addActionListener(new PopupsListener(popQuitterMenu));

        popQuitterMenu.add(new JLabel("Voulez-vous vraiment quitter l'application ?"));
        popQuitterMenu.add(btnQuitter);
        popQuitterMenu.add(btnAnnuler);
        popQuitterMenu.setVisible(true);
    }

    public void popCharger(Partie partie) {
    	Sauvegarde();
    	this.part = partie;
        JDialog popCharger = new JDialog();
        popCharger.setTitle("Charger");

        JDialog.setDefaultLookAndFeelDecorated(true);

        popCharger.setSize(500, 200);

        popCharger.setLocationRelativeTo(null);
        popCharger.setLayout(new FlowLayout());

        JButton btnCharger = new JButton("Charger");
        btnCharger.addActionListener( new PopupsListener(popCharger,f, this, 2));
        btnCharger.setEnabled(false);

        JLabel warning = new JLabel("Attention, en chargeant une partie, la partie courante sera perdue !");
        warning.setVisible(true);

        ButtonGroup grpSlot = new ButtonGroup();
        for (index = 1; index <= 10; index++) {
        	String val;
        	boolean ready = true;
        	if(slot[index-1] == null){
	            val = (String.valueOf(index) + "e Slot ");
	            ready = false;
        	}else{
                val = (index + slot[index-1].toString().substring(SavePath.length()+1, (int)slot[index-1].toString().length()-4));
        	}
            JRadioButton slot = new JRadioButton(val);
            slot.setEnabled(ready);
            slot.addActionListener( new PopupsListener(btnCharger, this, 3));
            grpSlot.add(slot);
            popCharger.add(slot);
        }

        popCharger.add(warning);

        popCharger.add(btnCharger);

        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.addActionListener(new PopupsListener(popCharger));
        popCharger.add(btnAnnuler);

        popCharger.setVisible(true);
    }

    public void popSauver(Partie partie) {
    	TrouverChemin();
    	Sauvegarde();
    	this.part = partie;
        JDialog popSauver = new JDialog();
        popSauver.setTitle("Sauvegarder");

        JDialog.setDefaultLookAndFeelDecorated(true);

        popSauver.setSize(500, 200);

        popSauver.setLocationRelativeTo(null);
        popSauver.setLayout(new FlowLayout());

        JButton btnSauvegarder = new JButton("Sauvegarder");
        btnSauvegarder.addActionListener( new PopupsListener(popSauver, this, 8));
        btnSauvegarder.setEnabled(false);
        
        JLabel warning = new JLabel("Attention une partie est déjà sauvegardée sur ce slot, vous allez l'écraser !");
        warning.setVisible(false);

        ButtonGroup grpSlot = new ButtonGroup();
        for (index = 1; index <= 10; index++) {
        	String val;
        	if(slot[index-1] == null){
	            val = (String.valueOf(index) + "e Slot" );
        	}else{
                val = (index + slot[index-1].toString().substring(SavePath.length()+1, (int)slot[index-1].toString().length()-4));
        	}
            JRadioButton slot = new JRadioButton(val);
            slot.addActionListener(new PopupsListener(btnSauvegarder, this, 3));
            if (this.slot[index-1] != null) {
                slot.addActionListener(new PopupsListener(warning, this, 4));
            }
            else{
                slot.addActionListener(new PopupsListener(warning, this, 5) );
            }
            grpSlot.add(slot);
            popSauver.add(slot);
        }

        popSauver.add(warning);

        JLabel labelNom = new JLabel("Nom de la sauvegarde :");
        popSauver.add(labelNom);
        JTextField nom = new JTextField("Sauvegarde", 20);
        nom.addKeyListener(new KeyAdapter() {
              public void keyReleased(KeyEvent e) {
                  SaveName = ((JTextField)e.getSource()).getText();
              }
        });
        popSauver.add(nom);
        popSauver.add(btnSauvegarder);

        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.addActionListener(new PopupsListener(popSauver));
        popSauver.add(btnAnnuler);

        popSauver.setVisible(true);
    }

    public void popRecommencer() {
        JDialog popRecommencer = new JDialog();
        popRecommencer.setTitle("Recommencer");

        JDialog.setDefaultLookAndFeelDecorated(true);

        popRecommencer.setSize(500, 200);

        popRecommencer.setLocationRelativeTo(null);
        popRecommencer.setLayout(new FlowLayout());

        JLabel warning = new JLabel("Attention : la partie en cours sera perdue si elle n'est pas sauvegardée! Voulez-vous vraiment recommencer la partie ?");
        popRecommencer.add(warning);

        JButton btnRecommencer = new JButton("Recommencer");
        btnRecommencer.addActionListener( new PopupsListener(popRecommencer, f,	6));
        popRecommencer.add(btnRecommencer);

        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.addActionListener(new PopupsListener(popRecommencer));
        popRecommencer.add(btnAnnuler);

        popRecommencer.setVisible(true);
    }

    public void popAbandonner() {
        JDialog popAbandonner = new JDialog();
        popAbandonner.setTitle("Abandonner");

        JDialog.setDefaultLookAndFeelDecorated(true);

        popAbandonner.setSize(500, 200);

        popAbandonner.setLocationRelativeTo(null);
        popAbandonner.setLayout(new FlowLayout());

        JLabel warning = new JLabel("Attention : la partie sera perdue si elle n'est pas sauvegardée! Voulez-vous vraiment abandonner la partie en cours, et retourner au menu principale ?");
        popAbandonner.add(warning);

        JButton btnAbandonner = new JButton("Abandonner");
        btnAbandonner.addActionListener(new PopupsListener(popAbandonner, f, 7));
        popAbandonner.add(btnAbandonner);

        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.addActionListener(new PopupsListener(popAbandonner));
        popAbandonner.add(btnAnnuler);

        popAbandonner.setVisible(true);
    }

  	@SuppressWarnings("null")
	
	/**
	 * Creation des emplacements de sauvegarde
	 */
	
	public void Sauvegarde(){
    	TrouverChemin();
  		this.slot = new File[10];
		File chemin = new File(SavePath);
		int size = (int)chemin.toString().length();
		System.out.println(size);
		File[] files = chemin.listFiles();
		String name;
		String n;
		int num;
		for (File f : files){
			try{
				num = 20;
				name = f.toString(); //Exception
				n = name.substring(size+1,size+2);
				num = Integer.parseInt(n);
				System.out.println(num);
				System.out.println(name);
				if( num >=0 && num <= 9 ){ //name.endsWith(".ser") && 
					//f = new File(name.substring(size+1, (int)name.length()));
					this.slot[num] = f;
				}
			} catch (Exception e) {
				System.out.println("got you!"); //debug
			}
		}
	}
	public void Sauvegarder(Partie p){
		System.out.println("Save initiated, " + SaveNum);
		if(SaveNum >=0 && SaveNum <=9){
			System.out.println("index ok");
			try{
				System.out.println("Try start");
				//File f = new File(i + "." + p.getJoueur1().getNom() + "-vs-" + p.getJoueur2().getNom() + ".ser");
				SaveName = (SaveNum-1 + "-" + SaveName + ".ser");
				File fichier = new File(SavePath + SaveName);
				FileOutputStream fos = new FileOutputStream(fichier);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
		    	System.out.println("ok");
				oos.writeObject(p);
		    	System.out.println("ok");
				oos.close();
				//slot[i] =  fichier;
				SaveName = "Sauvegarde";
				System.out.println("Save Done" + SaveNum);
				if(erase){
					slot[SaveNum-1].delete();
				}
				SaveNum = 20;
			}
			catch(IOException|NullPointerException e){
				System.out.println("Erreur lors de la sauvegarde du fichier");
			}
		}
	}
	/**
	 * Charger la partie au slot i
	 * @param i le numero de l'emplacement de sauvegarde
	 * @return null
	 */
	
	public Partie Charger(){
		if(SaveNum >=0 && SaveNum <=9 ){
                        System.out.println(SaveNum);
			try{
                                System.out.println(slot[SaveNum-1]);
				FileInputStream fis = new FileInputStream(slot[SaveNum-1]);
				ObjectInputStream ois = new ObjectInputStream(fis);
				Object p = ois.readObject();
				ois.close();
				if( p instanceof Partie ){
					SaveNum = 20;
					return (Partie)p;
				}
			}
			catch(IOException|NullPointerException|ClassNotFoundException e){
				System.out.println("Erreur lors du chargement du fichier");
			}
		}
		return null;
	}
	
	public void TrouverChemin(){
		try{
			String path = Popups.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			String decodedPath = URLDecoder.decode(path, "UTF-8");
			SavePath = (decodedPath + "Saves/");
			System.out.println(SavePath);
		}
		catch(Exception e){
			System.out.println("Erreur lors de la definition du chemin");
		}
	}

	public Fenetre getF() {
		return f;
	}

	public void setF(Fenetre f) {
		this.f = f;
	}

	public File[] getSlot() {
		return slot;
	}

	public void setSlot(File[] slot) {
		this.slot = slot;
	}

	public Partie getPart() {
		return part;
	}

	public void setPart(Partie part) {
		this.part = part;
	}

	public int getSaveNum() {
		return SaveNum;
	}

	public void setSaveNum(int saveNum) {
		SaveNum = saveNum;
	}

	public String getSaveName() {
		return SaveName;
	}

	public void setSaveName(String saveName) {
		SaveName = saveName;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public boolean isErase() {
		return erase;
	}

	public void setErase(boolean erase) {
		this.erase = erase;
	}

	public String getSavePath() {
		return SavePath;
	}

	public void setSavePath(String savePath) {
		SavePath = savePath;
	}
}
