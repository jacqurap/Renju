package Modele;

/**
 * Le joueur
 */

public abstract class Joueur {

    private String nom;
    
    /**
     * Creation d'un joueur
     * @param nom le nom du joueur
     */
    
    public Joueur(String nom){
        this.nom = nom;
    }
    
    /**
     * Recupere le nom du joueur
     * @return nom
     */
    
    public String getNom(){
        return nom;
    }
}
