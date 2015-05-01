/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidfield;

import static java.lang.Thread.sleep;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author cory
 */
public class AsteroidField {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String str = null;
        String shd = null;
        String ast = null;
        String pro = null;
        int max_storage = 15;
        int max_shields = 5;
        int num_asteroids = 55;
        int num_probes = 10;
        
        boolean valid_entry = false;
        
        while(!valid_entry){
            str = JOptionPane.showInputDialog(null, "Storage Size?");
            if(str != null){
                if(Pattern.matches("[0-9]+",str) == true){
                    max_storage = Integer.parseInt(str);
                    valid_entry = true;
                }
            }
        }
        valid_entry = false;
        while(!valid_entry){
            shd = JOptionPane.showInputDialog(null, "Shield Size?");
            if(shd != null){
                if(Pattern.matches("[0-9]+",shd)==true){
                    max_shields = Integer.parseInt(shd);
                    valid_entry = true;
                }
            }
        }
        valid_entry = false;
        while(!valid_entry){
            ast = JOptionPane.showInputDialog(null, "Number of Asteroids?");
            if(ast != null){
                if(Pattern.matches("[0-9]+",shd)==true){
                    num_asteroids = Integer.parseInt(ast);
                    valid_entry = true;
                }
            }
        }    
        valid_entry = false;
        while(!valid_entry){
            pro = JOptionPane.showInputDialog(null, "Number of Probes?");
            if(pro != null){
                if(Pattern.matches("[0-9]+",shd)==true){
                    num_probes = Integer.parseInt(pro);
                    valid_entry = true;
                }
            }
        }
        
        TaskForceCommand command = new TaskForceCommand();
        command.Init(max_storage, max_shields, num_asteroids, num_probes);
        command.start();
        
        
        
    }
    
}
