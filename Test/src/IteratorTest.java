import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class IteratorTest {
	public static void main(String[] args) {
		
		List<Integer> list  = new ArrayList<Integer>();
		list.add(12);
		list.add(40);
		list.add(-20);
		list.add(3);
		list.add(7);		
		
		Iterator<Integer> itr = list.iterator();
		
		while(itr.hasNext()) {
			if(itr.next()<0) {
				itr.remove();
			}
		}
		
		System.out.println(list);
	}

}
