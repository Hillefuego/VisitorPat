/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpat;

import FileIO.FileReader;
import compositePat.CompositeGroup;

import javax.swing.SwingUtilities;
import java.awt.*;
import java.util.ArrayList;

/**
 *
 * @author HCH
 */
public class DesignPat {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
               
            }
        });
    }
}

