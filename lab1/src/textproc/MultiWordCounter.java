package textproc;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MultiWordCounter implements TextProcessor{
	
	public Map<String, Integer> wordCounter = new TreeMap<>();
		
	public MultiWordCounter(String[] landskap) {
		for(String s : landskap) {
			wordCounter.put(s, 0);
		}	
	}
	
	
	public void process(String w) {
		
		/*for(String key : wordCounter.keySet()) {
			if(key.equals(w)) {
				wordCounter.put(key, wordCounter.get(key)+1);
			}
		}*/
		
		if(wordCounter.containsKey(w)) {
			wordCounter.put(w, wordCounter.get(w)+1);	
		}
	}
	
	public void report() {
		
		for(String key : wordCounter.keySet()) {
			System.out.println(key + ": " + wordCounter.get(key));
		}
	}	
}
