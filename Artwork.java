import java.awt.Color;
import java.awt.Graphics;

public class Artwork {
	public int x1;
	public int y1;
	public int x2;
	public int y2;
	public double midptx;
	public double midpty;
	
	public Color tint;
	public boolean alive;
	
	public Artwork(Form f1, Form f2) {
		x1 = (int)f1.posx;
		y1 = (int)f1.posy;
		x2 = (int)f2.posx;
		y2 = (int) f2.posy;
		midptx = (f1.posx + f2.posx)/2;
		midpty = (f1.posy + f2.posy)/2;
		
		double dist = Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-1));
		int R = (int)(dist*.9);
		int G = (int)(dist*1.8);
		int B = 255;
		
		if(R>255) R = 255;
		if(R<10) R = 10;
		if(G>255) G = 255;
		if(G<10) G = 10;
		
		
		tint = new Color(R, G, B, 220);
		
		//tint = new Color(255, 255, 0, 250);
		//Red, Green, Blue, Alpha (0-255)
		//Alpha: 255 opaque, 0 transparent
		alive = true;
	}
	
	public void update() {
		int R = tint.getRed();
		int G = tint.getGreen();
		int B = tint.getBlue();
		int A = tint.getAlpha();

		A-=2;

		if(A<0) 
			{
				A = 0;
				alive = false;
			}
		tint = new Color(R, G, B, A);
	}
	
	public void draw(Graphics g) {
		g.setColor(tint);

		g.drawLine(x1,  y1, x2, y2);
		


		g.drawLine((int)midptx, (int)midpty, 500, 350);
			
		}
	}

