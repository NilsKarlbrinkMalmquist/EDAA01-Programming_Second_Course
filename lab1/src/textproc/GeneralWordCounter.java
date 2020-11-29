package textproc;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class GeneralWordCounter implements TextProcessor{
	
	public Map<String, Integer> GeneralWordCounter = new TreeMap<>();
	Set<String> stopwords = new HashSet<>();
	
	public GeneralWordCounter(Set<String> stopwords) {
		this.stopwords = stopwords;
	}
	
	public void process(String w) {
		
		if (stopwords.contains(w)) {
			return;
		}
		
		else if(GeneralWordCounter.containsKey(w)) {
			GeneralWordCounter.put(w, GeneralWordCounter.get(w)+1);
		}
			
		else {
			GeneralWordCounter.put(w,1);
		}
		
	}
	
	public void report() {
		
		/*for(String key : GeneralWordCounter.keySet()) {	//rapportera endast de ord som förekommer fler än 200 ggr
			if (GeneralWordCounter.get(key)>= 200) {
				System.out.println(key + ": " + GeneralWordCounter.get(key));
			}
		}*/
		
		Set<Map.Entry<String, Integer>> wordSet = GeneralWordCounter.entrySet();
		List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);
		wordList.sort(new WordCountComparator());
		
		for(int i = 0; i<15 ; i++) {
			System.out.println(wordList.get(i));
		}
	}
	
	public List<Map.Entry<String, Integer>> getWordList(){
//		Set<Map.Entry<String, Integer>> wordSet = GeneralWordCounter.entrySet();
//		List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);
		return new ArrayList<>(GeneralWordCounter.entrySet());
	}
	
}
