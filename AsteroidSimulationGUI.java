/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package asteroidfieldsimulation;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.text.AbstractDocument.Content;
/**
 *
 * @author cBeezy
 */
public class AsteroidSimulationGUI extends JFrame{
    private int shields;
    private int num_asteroids;
    private int num_probes;
    private int storage_size;
    
    private JPanel header;
    private JPanel body;
    
    private JPanel shields_panel;
    private JPanel storage_panel;
    private JPanel probes_panel;
    private JPanel asteroids_panel;
    
    private JPanel shield_contents_panel;
    private JPanel storage_contents_panel;
    private JPanel probe_contents_panel;
    private JPanel asteroid_contents_panel;
    
    private JPanel[] shield;
    private JPanel[] storage;
    private JPanel[] probes;
    private JPanel[] asteroids;
    
    private JLabel sim_status_label;
    private JLabel shield_contents_label;
    private JLabel storage_contents_label;
    private JLabel probe_contents_label;
    private JLabel asteroid_contents_label;
    
    private JLabel[] probe_labels;
    
    
    public AsteroidSimulationGUI(int s, int a, int p, int c){
        shields = s;
        num_asteroids = a;
        num_probes = p;
        storage_size = c;
        
        setTitle("Asteroid Simulation");
        setLayout(null);
        setSize(500,500);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        header = new JPanel();
        header.setBounds(0,0,500,40);
        
        body = new JPanel();
        body.setBounds(0,50,500,450);
        
        add(header);
        add(body);
        
        sim_status_label = new JLabel("SIMULATION STATUS");
        
        header.add(sim_status_label);
        
        shields_panel = new JPanel();
        shields_panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        shields_panel.setBounds(5,0,75,400);
        
        storage_panel = new JPanel();
        storage_panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        storage_panel.setBounds(85,0,75,400);
        
        probes_panel = new JPanel();
        probes_panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        probes_panel.setBounds(165,0,210,400);
        
        asteroids_panel = new JPanel();
        asteroids_panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        asteroids_panel.setBounds(380,0,100,400);
        
        body.setLayout(null);
        body.add(shields_panel);
        body.add(storage_panel);
        body.add(probes_panel);
        body.add(asteroids_panel);
        
        shields_panel.setLayout(null);
        shield_contents_label = new JLabel("Sheilds");
        shield_contents_label.setBounds(5,5,65,20);
        shield_contents_label.setHorizontalAlignment(JLabel.CENTER);
        shield_contents_panel = new JPanel();
        shield_contents_panel.setBounds(5,25,65,370);
        shields_panel.add(shield_contents_label);
        shields_panel.add(shield_contents_panel);
        
        storage_panel.setLayout(null);
        storage_contents_label = new JLabel("Storage");
        storage_contents_label.setBounds(5,5,65,20);
        storage_contents_label.setHorizontalAlignment(JLabel.CENTER);
        storage_contents_panel = new JPanel();
        storage_contents_panel.setBounds(5,25,65,370);
        storage_panel.add(storage_contents_label);
        storage_panel.add(storage_contents_panel);
        
        probes_panel.setLayout(null);
        probe_contents_label= new JLabel("Probes");
        probe_contents_label.setBounds(5,5,200,20);
        probe_contents_label.setHorizontalAlignment(JLabel.CENTER);
        probe_contents_panel = new JPanel();
        probe_contents_panel.setBounds(5,25,200,370);
        probes_panel.add(probe_contents_label);
        probes_panel.add(probe_contents_panel);
        
        asteroids_panel.setLayout(null);
        asteroid_contents_label = new JLabel("Asteroids");
        asteroid_contents_label.setBounds(5,5,90,25);
        asteroid_contents_label.setHorizontalAlignment(JLabel.CENTER);
        asteroid_contents_panel = new JPanel();
        asteroid_contents_panel.setBounds(5,25,90,370);
        asteroids_panel.add(asteroid_contents_label);
        asteroids_panel.add(asteroid_contents_panel);
        
        shield_contents_panel.setLayout(new BoxLayout(shield_contents_panel, BoxLayout.Y_AXIS));
        shield = new JPanel[shields];
        for(int i = 0; i < shields; i++){
            shield[shields - 1 - i] = new JPanel();
            shield_contents_panel.add(shield[shields - 1 - i]);
        }
        
        storage_contents_panel.setLayout(new BoxLayout(storage_contents_panel,BoxLayout.Y_AXIS));
        storage = new JPanel[storage_size];
        for(int i = 0; i < storage_size; i++){
            storage[storage_size - 1 - i] = new JPanel();
            storage_contents_panel.add(storage[storage_size - 1 - i]);
        }
        
        probe_contents_panel.setLayout(new GridLayout(num_probes,1,0,5));
        probes = new JPanel[num_probes];
        probe_labels = new JLabel[num_probes];
        for(int i = 0; i < num_probes; i++){
            probes[i] = new JPanel();
            probe_labels[i] = new JLabel("Probe " + i);
            probe_contents_panel.add(probes[i]);
            probes[i].add(probe_labels[i]);
        }
        
        asteroid_contents_panel.setLayout(new GridLayout(num_asteroids/2, 2, 5, 5));
        asteroids = new JPanel[num_asteroids];
        for(int i = 0; i < num_asteroids; i++){
            asteroids[i] = new JPanel();
            asteroid_contents_panel.add(asteroids[i]);
        }
        setShields(shields);
        setNumAsteroids(num_asteroids);
        for(int i = 0; i < num_probes; i++)setProbeStatus(i, "AWAITING_ORDER");
        setVisible(true);
    }
    
    public void setStatus(String s){
        sim_status_label.setText("Simulation Status: " + s);
    }
    
    public void setShields(int s){
        Color c;
        double shield_amount = (double) s / (double) shields;
        if(shield_amount >= 1.0) c = Color.GREEN;
        else if(shield_amount < 1.0 && shield_amount >= 0.66) c = Color.YELLOW;
        else if(shield_amount < 0.66 && shield_amount >= 0.33) c = Color.ORANGE;
        else c = Color.RED;
        for(int i = 0; i < shields; i++){
            if(i < s) shield[i].setBackground(c);
            else shield[i].setBackground(null);
        }
    }
    
    public void setStorage(int num_items){
        Color c;
        double fill_amount = (double)num_items / (double)storage_size;
        if(fill_amount >= 1.0) c = Color.RED;
        else if(fill_amount < 1.0 && fill_amount >= 0.66) c = Color.ORANGE;
        else if(fill_amount < 0.66 && fill_amount >= 0.33) c = Color.YELLOW;
        else c = Color.GREEN;
        for(int i = 0; i < storage_size; i++){
            if( i < num_items) storage[i].setBackground(c);
            else storage[i].setBackground(null);
        }
    }
    
    public void setNumAsteroids(int num_left){
        for(int i = 0; i < num_asteroids; i++){
            if( i < num_left)asteroids[i].setBackground(Color.RED);
            else asteroids[i].setBackground(Color.GREEN);
        }
    }
    
    public void setProbeStatus(int i, String s){
        if(s.equals("AWAITING_ORDER"))probes[i].setBackground(Color.YELLOW);
        else if(s.equals("ACQUIRING_TARGET"))probes[i].setBackground(Color.ORANGE);
        else if(s.equals("REPORTING_TARGET"))probes[i].setBackground(Color.BLUE);
        else if(s.equals("DESTROYING_TARGET"))probes[i].setBackground(Color.RED);
        else if(s.equals("DESTROYED"))probes[i].setBackground(Color.BLACK);
        else if(s.equals("ABANDONING_MISSION"))probes[i].setBackground(Color.MAGENTA);
        else if(s.equals("RETURNING_TO_BASE"))probes[i].setBackground(Color.GREEN);
    }
}
