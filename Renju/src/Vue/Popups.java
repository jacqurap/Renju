/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Controleur.Partie;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

/**
 *
 * @author bochatom
 */
public class Popups {

    public Fenetre f;
	public File[] slot = new File[10];
	public Partie part;
	public int SaveNum = 20;
	public String SaveName = "Sauvegarde";
	int index;
	boolean erase = false;
	public String SavePath;

    public Popups(Fenetre f) {
        this.f = f;
    }

    public void popRegles() {
        final JDialog popRegle = new JDialog();
        popRegle.setTitle("Aide de jeu");

        JDialog.setDefaultLookAndFeelDecorated(true);
        popRegle.setSize(500, 200);
        popRegle.setLocationRelativeTo(null);
        popRegle.setLayout(new FlowLayout());
        popRegle.add(new JLabel("Coups interdits"));

        JButton btnRetour = new JButton("Retour");
        btnRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popRegle.dispose();
            }
        });
        popRegle.add(btnRetour);

        popRegle.setVisible(true);
    }

    public void popQuitterMenu() {
        final JDialog popQuitterMenu = new JDialog();
        popQuitterMenu.setTitle("Quitter");

        JDialog.setDefaultLookAndFeelDecorated(true);

        popQuitterMenu.setSize(500, 200);
        popQuitterMenu.setLocationRelativeTo(null);
        popQuitterMenu.setLayout(new FlowLayout());

        JButton btnQuitter = new JButton("Quitter");
        btnQuitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.closeGame();
                popQuitterMenu.dispose();
                System.exit(0);
            }
        });

        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popQuitterMenu.dispose();
            }
        });

        popQuitterMenu.add(new JLabel("Voulez-vous vraiment quitter l'application ?"));
        popQuitterMenu.add(btnQuitter);
        popQuitterMenu.add(btnAnnuler);
        popQuitterMenu.setVisible(true);
    }

    public void popCharger(Partie partie) {
    	Sauvegarde();
    	this.part = partie;
        final JDialog popCharger = new JDialog();
        popCharger.setTitle("Charger");

        JDialog.setDefaultLookAndFeelDecorated(true);

        popCharger.setSize(500, 200);

        popCharger.setLocationRelativeTo(null);
        popCharger.setLayout(new FlowLayout());

        final JButton btnCharger = new JButton("Charger");
        btnCharger.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.getAire().setPartie(Charger());
                f.getAire().repaint();
                popCharger.dispose();
                f.changePanel(f.getINTERFACEPANEL());
                popCharger.dispose();
            }
        });
        btnCharger.setEnabled(false);

        final JLabel warning = new JLabel("Attention, en chargeant une partie, la partie courante sera perdue !");
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
            slot.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!btnCharger.isEnabled()) {
                        SaveNum = Character.getNumericValue(((JRadioButton)e.getSource()).getText().charAt(0));
                        btnCharger.setEnabled(true);
                    }
                }
            });
            grpSlot.add(slot);
            popCharger.add(slot);
        }

        popCharger.add(warning);

        popCharger.add(btnCharger);

        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popCharger.dispose();
            }
        });
        popCharger.add(btnAnnuler);

        popCharger.setVisible(true);
    }

    public void popSauver(Partie partie) {
    	TrouverChemin();
    	Sauvegarde();
    	this.part = partie;
        final JDialog popSauver = new JDialog();
        popSauver.setTitle("Sauvegarder");

        JDialog.setDefaultLookAndFeelDecorated(true);

        popSauver.setSize(500, 200);

        popSauver.setLocationRelativeTo(null);
        popSauver.setLayout(new FlowLayout());

        final JButton btnSauvegarder = new JButton("Sauvegarder");
        btnSauvegarder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popSauver.dispose();
                Sauvegarder(part);
            }
        });
        btnSauvegarder.setEnabled(false);
        
        final JLabel warning = new JLabel("Attention une partie est déjà sauvegardée sur ce slot, vous allez l'écraser !");
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
            slot.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!btnSauvegarder.isEnabled()) {
                        SaveNum = Character.getNumericValue(((JRadioButton)e.getSource()).getText().charAt(0));
                        btnSauvegarder.setEnabled(true);
                    }
                }
            });
            if (this.slot[index-1] != null) {
                slot.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        warning.setVisible(true);
                        erase = true;
                    }
                });
            }
            else{
                slot.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        warning.setVisible(false);
                        erase = false;
                    }
                });
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
        btnAnnuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popSauver.dispose();
            }
        });
        popSauver.add(btnAnnuler);

        popSauver.setVisible(true);
    }

    public void popRecommencer() {
        final JDialog popRecommencer = new JDialog();
        popRecommencer.setTitle("Recommencer");

        JDialog.setDefaultLookAndFeelDecorated(true);

        popRecommencer.setSize(500, 200);

        popRecommencer.setLocationRelativeTo(null);
        popRecommencer.setLayout(new FlowLayout());

        JLabel warning = new JLabel("Attention : la partie en cours sera perdue si elle n'est pas sauvegardée! Voulez-vous vraiment recommencer la partie ?");
        popRecommencer.add(warning);

        JButton btnRecommencer = new JButton("Recommencer");
        btnRecommencer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AireDeJeu aire = new AireDeJeu(f.getAire().getJoueur1(), f.getAire().getNomJoueur1(), f.getAire().getJoueur2(), f.getAire().getNomJoueur2(),f);
                f.setAire(aire);
                f.changePanel(f.getACCUEILPANEL()); //pour ne pas freeze pendant le removeAll
                f.refreshInterface();
                f.changePanel(f.getINTERFACEPANEL());
                popRecommencer.dispose();
            }
        });
        popRecommencer.add(btnRecommencer);

        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popRecommencer.dispose();
            }
        });
        popRecommencer.add(btnAnnuler);

        popRecommencer.setVisible(true);
    }

    public void popAbandonner() {
        final JDialog popAbandonner = new JDialog();
        popAbandonner.setTitle("Abandonner");

        JDialog.setDefaultLookAndFeelDecorated(true);

        popAbandonner.setSize(500, 200);

        popAbandonner.setLocationRelativeTo(null);
        popAbandonner.setLayout(new FlowLayout());

        JLabel warning = new JLabel("Attention : la partie sera perdue si elle n'est pas sauvegardée! Voulez-vous vraiment abandonner la partie en cours, et retourner au menu principale ?");
        popAbandonner.add(warning);

        JButton btnAbandonner = new JButton("Abandonner");
        btnAbandonner.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.changePanel(f.getACCUEILPANEL());
                popAbandonner.dispose();
            }
        });
        popAbandonner.add(btnAbandonner);

        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popAbandonner.dispose();
            }
        });
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
}
