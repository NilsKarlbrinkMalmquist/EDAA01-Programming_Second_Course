package mountain;

public class Side {
	private Point p1;
	private Point p2;
	
	public Side(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	
	@Override
	public boolean equals(Object a) {
		if(a instanceof Side) {
			Side b = (Side) a;
			return b.p1 == p1 && b.p2 == p2 || b.p1 == p2 && b.p2 == p1;
		}
		else {
			return false;
		}
//		Side b = (Side) a;
//		if(b.p1 == p1 && b.p2 == p2 || b.p1 == p2 && b.p2 == p1) {
//			return true;
//		}
//		
//		return false;
	}
	
	@Override
	public int hashCode() {
		return p1.hashCode() + p2.hashCode();
	}

}
