import java.util.Random;
/*
 * Author: Guangqi Qing
 * cite: the hash function contains the HashMapSize, so I don't make the HashMapSize auto increase if it equals to size
 * if the HashMapSize equals to size, you cannot put new element but update the old key;
 */
public class HashMapLinearProbing<Key,Value> {
	private static class HashMapNode<Key,Value>{
		private Key key;
		private Value value;
		HashMapNode(Key key, Value value){
			this.key = key;
			this.value = value;
		}
		public Key getKey() {
			return this.key;
		}
		public Value getValue() {
			return this.value;
		}
		public void setValue(Value value) {
			this.value = value;
		}
	}
	private HashMapNode[] list;
	private int HashMapSize;
	private int size;
	HashMapLinearProbing() {
		this.list = new HashMapNode[50000];
	}
	HashMapLinearProbing(int HashMapSize){
		this.list = new HashMapNode[HashMapSize];
		this.HashMapSize = HashMapSize;
	}
	public void check() {
		if(size == HashMapSize) {
			grow();
		}
	}
	public void grow() {
		HashMapNode[]temp =new HashMapNode[2 * list.length];
		for (int i = 0; i < temp.length; i++) {}
		list = temp;
		HashMapSize = list.length;
	}
	public int hash(Key key) {
		return key.hashCode() % HashMapSize;
	}
	public void put(Key key, Value value) {
		if(size == HashMapSize) {
			System.out.println("put("+key+", "+value+") already full. Only update" );
		}
		int index = hash(key);
		int collides = 0;
		if(list[index] == null) {
			list[index] = new HashMapNode<Key, Value>(key, value);
			size++;
			System.out.println("put("+key+", "+value+") has " +collides + " conllides");
			return;
		}else if(list[index].getKey().equals(key)){
			list[index].setValue(value);
			System.out.println("put("+key+", "+value+") has " +collides + " conllides");
			return;
		}else {
			int probe = (index + 1) % HashMapSize;
			collides = 1;
			while(probe != index) {
				if(list[probe] == null){
					list[probe] = new HashMapNode<Key, Value>(key, value);
					size++;
					System.out.println("put("+key+", "+value+") has " +collides + " conllides");
					return;
				}else if(key.equals(list[probe].getKey())) {
					list[probe].setValue(value);
					size++;
					System.out.println("put("+key+", "+value+") has " +collides + " conllides");
					return;
				}else {
					probe = (probe + 1) % HashMapSize;
					collides++;
				}
			}
		}
		System.out.println("put("+key+", "+value+") " +collides + " conllides");
	}
	public Value get(Key key) {
		int index = hash(key);
		if(list[index] !=null && key.equals(list[index].getKey())) {
			return (Value) list[index].value;
		}
		int probe = (index + 1) % HashMapSize;
		while(probe != index) {
			if(list[probe] == null) {
				probe = (probe + 1) % HashMapSize;
				continue;
			}
			else if(list[probe] != null && key.equals(list[probe].getKey())) {
				return (Value) list[probe].value;
			}else {
				probe = (probe + 1) % HashMapSize;
			}
		}
		
		return null;
	}
	public static void main(String[] args) {
		HashMapLinearProbing<String, Integer> map = new HashMapLinearProbing<String,Integer>(500);
		Random r = new Random();
		for (int i = 0; i < 500; i++) {
			map.put(String.valueOf(r.nextInt(500)), r.nextInt(10000));
		}
//		map.put("hello",2);
//		map.put("abc", 3);
//		map.put("acc", 4);
//		map.put("abc", 4);
//		Integer integer1 = map.get("hello");
//		Integer integer2 = map.get("abc");
//		Integer integer3 = map.get("acc");
//		System.out.println(integer1);
//		System.out.println(integer2);
		
	}
}
