import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class dvd extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel panel;
    private JLabel label;
    
    private int x = 0, y = 0;
    
    private int xSpeed = 1;
    private int ySpeed = 1;
    
    private int cornerHit = 0;
    
    private boolean collision = false;
    
    private int nbCollisions = 0;
    
    private int dvdId = 0;
    
    private final String[] dvdList = {"white","green","pink","red","aqua","green","blue"};

	public static void main(String[] args) {
		new dvd();
	}
	
	public dvd () { super("DVD");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		
		panel = new JPanel();
		panel.setBackground(Color.BLACK);
		this.getContentPane().add(panel);
		
		label = new JLabel();
	    label.setIcon(new ImageIcon("dvd\\white.png"));
	    Dimension size = label.getPreferredSize();
	    panel.add(label);
	    
	    this.setVisible(true);

	    Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	x = x + xSpeed;
            	y = y + ySpeed;
            	
            	if (x <= 0 || x + size.width >= panel.getWidth() && panel.getWidth() != 0) {
                    xSpeed = -xSpeed;
                    collision = true;
                }
            	if (y <= 0 || y + size.height >= panel.getHeight() && panel.getHeight() != 0) {
                    ySpeed = -ySpeed;
                    if (collision) {
                    	cornerHit++;
                    	System.out.println(cornerHit+":"+x+","+y);
                    }
                    collision = true;
                }
            	if (collision) {
            		if (dvdId != 6) {
            			dvdId++;
            		} else {
            			dvdId = 0;
            		}
            		label.setIcon(new ImageIcon("dvd\\"+dvdList[dvdId]+".png"));
            		nbCollisions++;
            		System.out.println(nbCollisions);
            	}
            	collision = false;
            	
            	label.setBounds(x, y, size.width, size.height); 
            }
	    });
	    timer.start();
	}

}