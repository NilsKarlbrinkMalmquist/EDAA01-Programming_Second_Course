package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Holgersson {
	
	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };

	public static void main(String[] args) throws FileNotFoundException {
		//long t0 = System.nanoTime();
		
		ArrayList<TextProcessor> list = new ArrayList<TextProcessor>();
		list.add(new SingleWordCounter("nils"));
		list.add(new SingleWordCounter("norge"));
	
		list.add(new MultiWordCounter(REGIONS)); //Stoppar in landskap i MultiWordCounter i MAP
		
		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning
		
		Scanner u = new Scanner(new File("undantagsord.txt"));	
		Set<String> stopwords = new HashSet<String>();		//Creating an empty set
		while(u.hasNext()) {
			stopwords.add(u.next());
		}
		
		list.add(new GeneralWordCounter(stopwords));
		
		while (s.hasNext()) {
			String word = s.next().toLowerCase();
			
			for(TextProcessor p : list) {
				p.process(word);
			}
		}
		s.close();

		for(TextProcessor p : list) {
			p.report();
		}
		
		//long t1 = System.nanoTime();
		//System.out.println("Tid: "+ (t1-t0)/1000000.0 + " ms");
	}
}