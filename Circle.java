import java.awt.Graphics;
import java.util.ArrayList;

public class Circle extends Form {
	//posx and posy are the center of the circle 
	//size is the radius
	public Circle(double x, double y) {
		super(x,y);
	}
	public void update(ArrayList<Form> forms) 
	{
		moveForward();
		//stopEdge();
		spawnCenter();
		//wrapEdge();
		//bounceEdge();
		//spawnRegion();
		//changeDirection(forms);
		//spreadOut(forms);
		//stall(forms);
		converge(forms);
		line(forms);
		
	}
	public boolean intersects(Form f) {
		double dist = Math.sqrt((posx-f.posx)*(posx-f.posx)+ (posy-f.posy)*(posy-f.posy) );
		if(dist<size + f.size)
			return true;
		else
			return false;
		
		
	}
	
	
	public void draw(Graphics g) {
		g.setColor(tint);
		g.drawOval((int)(posx-size),  (int)(posy-size),  (int)(2*size),  (int)(2*size));
		
		
		

		
		double x = posx + size*Math.cos(angle);
		double y = posy - size*Math.sin(angle);
		g.drawLine((int)posx, (int)posy, (int)x, (int)y);
		
	}
}
