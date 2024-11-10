import java.awt.Graphics;
import java.util.ArrayList;

public class Square extends Form {

	public Square(double x, double y) {
		super(x, y);
	}
	
	public void update(ArrayList<Form> squares) 
	{
		moveForward();
		//stopEdge();
		//spawnCenter();
		wrapEdge();
		//bounceEdge();
		//spawnRegion();
		//changeDirection(forms);
		spreadOut(squares);
		//stall(squares);
		//converge(squares);

		
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
		g.drawRect((int)(posx-size),  (int)(posy-size),  (int)(2*size),  (int)(2*size));
		
		double x = posx + size*Math.cos(angle);
		double y = posy - size*Math.sin(angle);
		g.drawLine((int)posx, (int)posy, (int)x, (int)y);
		
	}

}
