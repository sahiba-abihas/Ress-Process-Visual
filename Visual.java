import java.awt.Color; 
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import java.awt.event.KeyEvent; 
import java.awt.event.KeyListener; 
import java.util.ArrayList;
import javax.swing.JFrame; 
import javax.swing.JPanel; 
import javax.swing.Timer;
    
 
 
public class Visual implements ActionListener, KeyListener {
 
    private JFrame frame;       //REQUIRED! The outside shell of the window
    public DrawingPanel panel;  //REQUIRED! The interior window
    private Timer visualtime;   //REQUIRED! Runs/Refreshes the screen. 
   
    //Adjust these values:
    public static final int WIDE = 1000;
    public static final int HIGH = 700;
    
    //All other public data members go here:
    public ArrayList<Form> forms;
    public ArrayList<Artwork> art;
    public boolean showforms;
    public boolean showsquares;
    public boolean showart;
    public ArrayList<Form> squares;
    
    public Visual()
    {
        //Adjust the name, but leave everything else alone.
        frame = new JFrame("Ress Process Visual... ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new DrawingPanel();
        panel.setPreferredSize(new Dimension(WIDE, HIGH));
        frame.getContentPane().add(panel);
        panel.setFocusable(true);
        panel.requestFocus();
        panel.addKeyListener(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true); 
        
        Initialize();
        
        //This block of code is fairly constant too -- always have it.
        visualtime = new Timer(20, this);     
        visualtime.start();
        showforms = false;
        showsquares = false;
        showart = true;
    } 
 
    public void Initialize()
    {
        //Initialize all data members here...
    	forms = new ArrayList<Form>();
    	squares = new ArrayList<Form>();
    	for(int n = 0; n<10; n++) {
    		double x = Math.random()*WIDE;
    		double y = Math.random()*HIGH;
    		forms.add(new Circle(x,y));
    	}
    	for(int n = 0; n<10; n++) {
    		double x = Math.random()*WIDE;
    		double y = Math.random()*HIGH;
    		squares.add(new Square(x,y));
    	}
    	forms.get(0).tint = Color.GREEN;
    	squares.get(0).tint = Color.GREEN;
    	
    	art = new ArrayList<Artwork>();
        
    }
    public void actionPerformed(ActionEvent e)
    {    
        //Once the new Visual() is launched, this method runs an infinite loop
    	for(int x = 0; x<forms.size(); x++) {
    		forms.get(x).update(forms);
    		squares.get(x).update(squares);
    	}
    	
    	for(int x = 0; x<forms.size()-1; x++) {
    		for(int y = x+1; y<forms.size(); y++) {
    			if(forms.get(x).intersects(forms.get(y)))
    				art.add(new Artwork(forms.get(x), forms.get(y)));
    			if(squares.get(x).intersects(squares.get(y)))
    				art.add(new Artwork(squares.get(x), squares.get(y)));
    		}
    	}
//    	for(int x = 0; x<forms.size()-1; x++) {
//    		for(int y = x+1; y<forms.size(); y++) {
//    			if(forms.get(x).intersects(forms.get(y)))
//    				art.add(new Artwork(forms.get(x), forms.get(y)));
//    			if(squares.get(x).intersects(squares.get(y)))
//    				art.add(new Artwork(squares.get(x), squares.get(y)));
//    			if(forms.get(x).intersects(squares.get(y)))
//    				art.add(new Artwork(forms.get(x), squares.get(y)));
//    			if(squares.get(x).intersects(forms.get(y)))
//    				art.add(new Artwork(squares.get(x), forms.get(x)));
//    		}
//    	}
//    	
    	
    	
    	
    	
    	for(int x=0; x<art.size(); x++)
    		art.get(x).update();
    	
    	
    	for(int x = art.size()-1; x>=0; x--) {
    		if(!art.get(x).alive)
    			art.remove(x); 
    	}
        
        panel.repaint();
    }
 
    public void keyPressed(KeyEvent e)
    {      
        if(e.getKeyCode() == KeyEvent.VK_HOME)
            Initialize();
        if(e.getKeyCode() == KeyEvent.VK_SPACE)
            showforms = !showforms;
        if(e.getKeyCode() == KeyEvent.VK_SHIFT)
            showsquares = !showsquares;
        if(e.getKeyCode() == KeyEvent.VK_ENTER)
            showart = !showart;
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
            System.exit(0); 
        
    }
    
    public void keyTyped(KeyEvent e) {  }   //not used
    public void keyReleased(KeyEvent e) {  }//not used
        
//BIG NOTE:  The coordinate system for the output screen is as follows:     
//  (x,y) = (0, 0) is the TOP LEFT corner of the output screen;    
//  (x,y) = (WIDE, 0) is the TOP RIGHT corner of the output screen;     
//  (x,y) = (0, HIGH) is the BOTTOM LEFT corner of the screen;   
//  (x,y) = (WIDE, HIGH) is the BOTTOM RIGHT corner of the screen;
//REMEMBER::
// Strings are referenced from their BOTTOM LEFT corner.
// Virtually all other objects (Rectangles, Ovals, Images...) 
//    are referenced from their TOP LEFT corner.
    private class DrawingPanel extends JPanel { 
        public void paintComponent(Graphics g)         
        {
            super.paintComponent(g);
            panel.setBackground(Color.black);
            
            //this is where you draw items on the screen.    
            if(showforms) {
	        	for(int x = 0; x<forms.size(); x++) {
	        		forms.get(x).draw(g);
	        	}
            }
            if(showsquares) {
	        	for(int x = 0; x<squares.size(); x++) {
	        		squares.get(x).draw(g);
	        	}
            }
            if(showart) {
	        	for(int x=0; x<art.size(); x++)
	        		art.get(x).draw(g);
            }
        }
    }
}