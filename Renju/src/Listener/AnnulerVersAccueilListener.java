/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listener;

import Vue.Fenetre;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Mathieu
 */
public class AnnulerVersAccueilListener implements ActionListener {
    private Fenetre f;
    
    public AnnulerVersAccueilListener(Fenetre f) {
        setF(f);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        f.changePanel(f.getACCUEILPANEL());
    }

    /**
     * @return the f
     */
    public Fenetre getF() {
        return f;
    }

    /**
     * @param f the f to set
     */
    public void setF(Fenetre f) {
        this.f = f;
    }
    
}
