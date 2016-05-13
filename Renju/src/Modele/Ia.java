/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.awt.Point;

/**
 *
 * @author jacqurap
 */
public abstract class Ia extends Joueur{
    
    public Ia(String nom){
        super(nom);
    }
    
    public abstract Point solver();
}
