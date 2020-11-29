package mountain;

import fractal.*;
import java.util.HashMap;


public class Mountain extends Fractal {
	private Point a;
	private Point b;
	private Point c;
	private double dev;
	private int random;
	
	public HashMap<Side, Point> sideMap = new HashMap<>();
	
	public Mountain(Point a, Point b, Point c, double dev) {
		//super();
		this.a = a;
		this.b = b;
		this.c = c;
		this.dev = dev;
	}
	
	
	@Override
	public String getTitle(){
		return "Mountain";
	}


	@Override
	public void draw(TurtleGraphics turtle) {
		fractalLine(turtle, order, a, b, c, dev);
		
	}
	
	private void fractalLine(TurtleGraphics turtle, int order, Point a, Point b, Point c, double dev) {
		if(order == 0) {
			turtle.moveTo(a.getX(), a.getY());
			turtle.penDown();
			turtle.forwardTo(b.getX(), b.getY());
			turtle.forwardTo(c.getX(), c.getY());
			turtle.forwardTo(a.getX(), a.getY());
			turtle.penUp();
		}
		else {
			random = (int)RandomUtilities.randFunc(dev);	//Konstigt anropningssätt????
			
			Point ab = mapPoint(a, b);
			Point ac = mapPoint(a, c);
			Point bc = mapPoint(b, c);
			
			fractalLine(turtle, order-1, a, ab, ac, dev/2);
			fractalLine(turtle, order-1, ab, b, bc, dev/2);
			fractalLine(turtle, order-1, ac, bc, c, dev/2);
			fractalLine(turtle, order-1, ab, bc, ac, dev/2);
		}
	}
	
	//Handledningen borde tipsa om att man ska skapa en ny metod i klassen Mountain som ska skapa, kolla och ta bort points i map:en
	private Point mapPoint(Point a, Point b) {
		Side side = new Side(a, b);
		
		//Om punkten finns returnera och ta bort från map:en
		if(sideMap.containsKey(side)) {
			Point point = sideMap.get(side);
			sideMap.remove(side);
			return point;
		}
		//Om punkten inte finns räkna ut och ta bort
		else {
			Point point = new Point((a.getX()+b.getX())/2, (a.getY()+b.getY())/2 + random);
			sideMap.put(side, point);
			return point;
		}
	}
}
