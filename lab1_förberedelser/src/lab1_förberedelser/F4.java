package lab1_förberedelser;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

public class F4 {
	public static void main(String args[]) {
		Set<Integer> nbrs = new HashSet<Integer>();
		for (int i = 0; i < 100; i += 10) {
		nbrs.add(i);
		nbrs.add(i); // notera: talet l�ggs till tv� g�nger
		}
		for (int a : nbrs) {
		System.out.println(a);
		}
		
		if(nbrs.contains(10) == true) {
			System.out.println("Ja");

		}

	}

}
