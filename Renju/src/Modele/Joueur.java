package Modele;

import java.io.Serializable;

/**
 * Le joueur
 */
public abstract class Joueur implements Serializable {

    private String nom;

    /**
     * Creation d'un joueur
     *
     * @param nom le nom du joueur
     */
    public Joueur(String nom) {
        this.nom = nom;
    }

    /**
     * Recupere le nom du joueur
     *
     * @return nom
     */
    public String getNom() {
        return nom;
    }
}
