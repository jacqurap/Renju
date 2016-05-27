/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Mathieu
 */
public class SlotChargerListener implements ActionListener{

    private JButton btnCharger;

    public SlotChargerListener(JButton btnCharger) {
        setBtnCharger(btnCharger);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!btnCharger.isEnabled()) {
            btnCharger.setEnabled(true);
        }
    }

    /**
     * @return the btnCharger
     */
    public JButton getBtnCharger() {
        return btnCharger;
    }

    /**
     * @param btnCharger the btnCharger to set
     */
    public void setBtnCharger(JButton btnCharger) {
        this.btnCharger = btnCharger;
    }

}
