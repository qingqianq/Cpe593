import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/*
 * Random(seed,ger)
 * 	
 * Author: Guangqi Qing
 * cite: copy the hash function from 
 */
public class HashMapLinearChaining<K,V> {
		private Node<K,V>[] list;
		private int size;
		private int multiplier;
		private int modulus;
		private int HashMapSize;
		private int []num;
		private static class Node<K,V>{
			private K key;
			private V value;
			private Node<K,V> next;
			Node(K key,V value){
				this.key = key;
				this.value = value;
				this.next = null;
			}
			public K getKey() {
				return key;
			}
			public void setKey(K key) {
				this.key = key;
			}
			public V getValue() {
				return value;
			}
			public void setValue(V value) {
				this.value = value;
			}
			public Node getNext() {
				return next;
			}
			public void setNext(Node next) {
				this.next = next;
			}
		}
		public HashMapLinearChaining() {
			this(1,Integer.MAX_VALUE);
		}
		public HashMapLinearChaining(int multiplier,int modulus) {
			this(multiplier,modulus,4000);
		}
		public HashMapLinearChaining(int multiplier,int modulus,int HashMapSize) {
			list = new Node[HashMapSize];
			this.multiplier = multiplier;
			this.modulus = modulus;
			size = 0;
			this.HashMapSize = HashMapSize;
			num = new int[HashMapSize];
		}
		public int hash(K key) {
			return Math.abs(multiplier * key.hashCode()) % modulus  % this.HashMapSize; 
		}
		public void put(K key,V value) {
			int index = hash(key);
			if(list[index]==null) {
				list[index] = new Node<K,V>(key,value);
				size++;
				num[index]++;
				System.out.println(num[index]);
				return;
			}else {
				Node<K,V> temp = list[index];
				Node<K,V> pre = temp;
				while(temp != null) {
					if(key.equals(temp.getKey())) {
						temp.setValue(value);
						return;
					}else {
						pre = temp;
						temp = temp.next;
					}
				}
				pre.next = new Node<K,V>(key,value);
				num[index]++;
				size++;
				System.out.println(num[index]);
				return;
			}
		}
		public V get(K key) {
			int index = hash(key);
			if(list[index]==null)
				return null;
			Node<K,V>temp = list[index];
			while(temp != null) {
				if(key.equals(temp.getKey())) {
					return temp.getValue();
				}else {
					temp = temp.next;
				}
			}
			return null;
		}
		public static void main(String[] args) throws FileNotFoundException {
			HashMapLinearChaining map = new HashMapLinearChaining();
			Scanner input = new Scanner(System.in);
			System.out.println("Please input the file name: ");
			String fileName = input.nextLine();
//			String fileName = "src/dict.txt";
			Scanner s = new Scanner(new File(fileName));
			int num = 0;
			while(s.hasNext()) {
				String word = s.nextLine();
				System.out.print(num+"              ");
				map.put(word, num);
				num++;
			}
//			System.out.println(map.get("cxdsgsdgabac"));
		}
	}
