package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BookReaderApplication {

	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning
		
		Scanner u = new Scanner(new File("undantagsord.txt"));	
		Set<String> stopwords = new HashSet<String>();		//Creating an empty set
		while(u.hasNext()) {
			stopwords.add(u.next());
		}
		
		GeneralWordCounter counter = new GeneralWordCounter(stopwords);
		while (s.hasNext()) {
			String word = s.next().toLowerCase();
			counter.process(word);
		}
		new BookReaderController(counter);	
		
	} 
	
}
