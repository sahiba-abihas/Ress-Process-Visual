import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


public class Form {
// this class will never display... I'm never going to instantiate a Form Object
	//This will be a PARENT Class to a list of Children that all have these
	// characteristics and behaviors. 

	public static final int MAXSIZE = 65;
	public static final int MINSIZE = 20;
	public static final double MAXSPEED = 8;
	public static final double MINSPEED = .2;
	public static final double DELANGLE = 0.02;
	
	public double posx;
	public double posy;
	public double size;
	public double angle;
	public double speed;
	public int region;
	public double midptx = 0.0;
	public double midpty = 0.0;


	public Color tint;
	
	public Form(double x, double y) {
		posx = x;
		posy = y;
		size = Math.random()*(MAXSIZE - MINSIZE + 1) + MINSIZE;
		angle = Math.random()*Math.PI*2;
		speed = Math.random()*(MAXSPEED - MINSPEED +1) + MINSPEED;
		tint = Color.white;
		region = (int)(Math.random()*3 + 1);

	}
	
	public void moveForward() {
		posx += speed*Math.cos(angle);
		posy -= speed*Math.sin(angle);
	}
	
	public void stopEdge() {
		if(posx<0+size) posx = size;
		if(posx>Visual.WIDE - size) posx = Visual.WIDE - size;
		
		if(posy<0+size) posy = size;
		if(posy>Visual.HIGH - size) posy = Visual.HIGH - size;
	}
	
	public void changeDirection(ArrayList<Form> forms) {
		for(int n = 0; n<forms.size(); n++) {
			if(forms.get(n)!= this && intersects(forms.get(n)))
				angle +=DELANGLE;
		}
	}
	
	public void spreadOut(ArrayList<Form> forms) {
		for(int n = 0; n<forms.size(); n++) {
			if(forms.get(n) !=this && intersects(forms.get(n))) {
				double dx = forms.get(n).posx - posx;
				double dy = forms.get(n).posy - posy;
				double mag = Math.sqrt(dx*dx + dy*dy);
				
				if(mag!=0) {
					dx /= mag;
					dy /= mag;
				} else {
					dx = Math.random()*1 - .5;
					dy = Math.random()*1 - .5;
				}
				double scale = speed/1.5;
				dx = -1*scale *dx;
				dy = -1*scale * dy;
				
				posx +=dx;
				posy +=dy;
			}
		}
	}
	
	public void wrapEdge() {
		if(posx<0-size) posx = Visual.WIDE + size - 1;
		if(posx>Visual.WIDE + size) posx = 1-size;
		
		if(posy<0-size) posy = Visual.HIGH + size - 1;
		if(posy>Visual.HIGH + size) posy = 1-size;
	}
	
	public void bounceEdge() {
		if(posx<0+size) angle+=Math.PI;
		if(posx>Visual.WIDE - size) angle+=Math.PI;
		
		if(posy<0+size) angle+=Math.PI;
		if(posy>Visual.HIGH - size) angle+=Math.PI;
		
	} //**check this**
	
	public void spawnRegion() {
		boolean spawncent = false;
		int centerx;
		int centery;
		int radius;
		
		if(region == 1) {
			centerx = 200;
			centery = 200;
			radius = 10000;
		} else if(region == 2) {
			centerx = 550;
			centery = 200;
			radius = 10000;
		} else {
			centerx = 400;
			centery = 400;
			radius = 10000;
		}
		
		if(((centerx-posx)*(centerx - posx) + (centery - posy)*(centery - posy))>radius) {
			 spawncent = true;
		}
		if(spawncent) {
			posx = centerx;
			posy = centery;
		}
			
	}
	
	public boolean intersects(Form f) {
		return false;
	}
	
	public void stall(ArrayList<Form> f) {
		boolean stall = false;
		for(int c = 0; c<f.size(); c++) {
			if(f.get(c)!=this && this.intersects(f.get(c))) {

				stall = true;
				

			} 
			if(stall) {
				if(f.get(c).speed == MINSPEED)
					angle+=DELANGLE;
				else
					this.speed = MINSPEED;
			}
			if(!stall) {
				if(this.speed == MINSPEED)
					this.speed = Math.random()*(MAXSPEED - MINSPEED +1) + MINSPEED; 
			}
				
			
		}

	}
	
	public void converge(ArrayList<Form> f) {
		boolean temp = false;
		for(int n = 0; n<f.size(); n++) {
			if(f.get(n)!= this && intersects(f.get(n)) && f.get(n).angle>this.angle)
				temp = true;
		}
		if(temp) angle+=DELANGLE;
		else angle-=DELANGLE;
	}
	
	public void spawnCenter() {
		boolean spawn = false;
		if(posx<0-size) spawn = true;
		if(posx>Visual.WIDE + size) spawn = true;
		
		if(posy<0-size)spawn = true;
		if(posy>Visual.HIGH +  size) spawn = true;
		
		if(spawn) {
			posx = Visual.WIDE/2;
			posy = Visual.HIGH/2;
		}
	}
	
	public void line(ArrayList<Form> f) {
		boolean check = false;
		for(int n = 0; n<f.size(); n++) {
			if(f.get(n)!= this && intersects(f.get(n))) {
				midptx = (f.get(n).posx + this.posx)/2;
				midpty = (f.get(n).posy + this.posy)/2;
				check = true;
			}
			
		}
		if(!check && midptx ==0) {
			midptx = 0;
			midpty = 0;
		}
		
	}
	
	
	public void update(ArrayList<Form> forms) 
	{
		
		
	}
	
	public void draw(Graphics g) {
		
	}
	
	
	
	
}
