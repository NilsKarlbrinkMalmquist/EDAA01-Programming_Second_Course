package map;

public class SimpleHashMap<K, V> implements Map<K, V> {
	
	public static void main(String[] args) {
		SimpleHashMap<Integer, Integer> map = new SimpleHashMap(10);
		System.out.println(map.show() + "Size = " + map.size() + "\n");
		for(int i = 0; i < 5; i++) {
			map.put(i, i);
		}
		map.put(-10, 10);
		map.put(100, 10);
		map.put(150, 15);
		map.put(1500, 15);
		map.put(1500, -99);
		map.put(18, 3);
		map.put(18, 3);
		System.out.println(map.show() + "Size = " + map.size() + "\n");
	}

	public Entry<K, V> table[];
	public int full;
	public int capacity;
	public int size;
	public double loadfactor = 0.75;
	
	/** Constructs an empty hashmap with the default inital capacity (16)
		and the default load factor (0.75). */
	public SimpleHashMap() {
		this(16);	
	}
	/** Constructs an empty hashmap with the specified inital capacity
	 	and the default load factor (0.75). */
	public SimpleHashMap(int capacity) {
		this.capacity = capacity;
		full = (int) (loadfactor * capacity);
		table = new Entry[capacity];
		size = 0;
	}
	
	public String show(){
		StringBuilder hashTable = new StringBuilder();
		for(int i = 0; i < table.length; i++) {
			if(table[i] != null) {
				hashTable.append(i + "	" + table[i].toString());
				Entry<K, V> temp = table[i];
				while(temp.next != null) {
					hashTable.append(" " + temp.next.toString());
					temp = temp.next;
				}
				hashTable.append("\n");
			}
			else {
				hashTable.append(i + " " + "\n");
			}
		}
		return hashTable.toString();
	}

	@Override
	public V get(Object object) {
		K key = (K) object;
		if(find(index(key), key) != null) {
			return find(index(key), key).getValue();
		}
		else {
			return null;
		}
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public V put(K key, V value) {
		if(size == full) {
			rehash();
		}
		Entry<K, V> temp = find(index(key), key);
		Entry<K, V> newEntry = new Entry(key, value);
		
		if(find(index(key), key) != null) {
			return temp.setValue(value);
		}
		else if(table[index(key)] != null) {
			temp = table[index(key)];
			while(temp.next != null) {
				temp = temp.next;
			}
			temp.next = newEntry;
			size++;
			return null;
		}
		else {
			table[index(key)] = newEntry;
			size++;
			return null;
		}
	}

	@Override
	public V remove(Object object) {
		K key = (K) object;
		Entry<K, V> temp = table[index(key)];
		if(find(index(key), key) != null && !isEmpty()) {
			if(temp.next == null && temp.getKey().equals(key)) {	//Key is first in the list and the list is otherwise empty
				V removedValue = temp.getValue();
				table[index(key)] = null;
				size--;
				return removedValue;
			}
			else if(temp.next != null && temp.getKey().equals(key)) {	//Key is first in the list and there are more elements in the list
				V removedValue = temp.getValue();
				table[index(key)] = temp.next;
				size--;
				return removedValue;
			}
			else {	//key is located somewhere inside the list
				while(!temp.next.getKey().equals(key)) {
					temp = temp.next;
				}
				V removedValue = temp.next.getValue();
				temp.next = temp.next.next;
				size--;
				return removedValue;
			}
		}
		return null;
	}

	@Override
	public int size() {
		return size;
	}
	
	private int index(K key) {
		return Math.abs(key.hashCode() % table.length);
	}
	
	private Entry<K, V> find(int index, K key){
		Entry<K, V> temp = table[index];
		while(temp != null) {
			if(temp.getKey().equals(key)) {
				return temp;
			}
			else {
				temp = temp.next;
			}
		}
		return null;
	}
	
	private void rehash() {
		Entry<K, V>[] newTable = new Entry[capacity * 2];
		Entry<K, V>[] oldTable = table.clone();
		table = newTable;
		
		for(int i = 0; i < oldTable.length; i++) {
			Entry<K, V> temp = oldTable[i];
			while(temp != null) {
				put(temp.getKey(), temp.getValue());
				size --;
				temp = temp.next;
			}
		}
	}
	
public static class Entry<K, V> implements Map.Entry<K, V>{
		
		private K Key;
		private V Value;
		private Entry<K, V> next;
		
		
		public Entry(K Key, V Value) {
			this.Key = Key;
			this.Value = Value;
		}

		@Override
		public K getKey() {
			return Key;
		}

		@Override
		public V getValue() {
			return Value;
		}

		@Override
		public V setValue(V value) {
			V oldValue = this.Value;
			this.Value = value;
			return oldValue;
		}
		
		public String toString() {
			return Key + "=" + Value;
		}

	}

}
