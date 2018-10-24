import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/*
 * Author: Guangqi Qing
 */
public class MyLinkedList {
	private static class Node{
		int data;
		Node prev;
		Node next;
		Node (Node prev, Node next, int data){
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
	}
	private Node head,tail;
	MyLinkedList(){
		head = null;
		tail = null;
	}
	public static void main(String[] args) {
		MyLinkedList list = new MyLinkedList();
//		list.addStart(2);
//		list.addEnd(3);
//		System.out.println(list.head.data);
//		System.out.println(list.tail.data);
//		list.removeEnd();
//		System.out.println(list.head.data);
//		System.out.println(list.tail.data);
//		list.removeStart();
//		System.out.println(list.size());
//		list.addStart(2,3,10);
		Scanner input = new Scanner(System.in);
		System.out.println("Please input the file name :(HW4b.txt)");
		String fileName = input.nextLine();
		list.read(fileName);
	}
	public void show() {
		for(Node p = head; p!= null; p = p.next) {
			System.out.print(p.data+" ");
		}
		System.out.println();
	}
	public void addStart(int start,int step, int end) {
		while(start <= end) {
			addStart(start);
			start = start + step;
		}
	}
	public void addEnd(int start, int step, int end) {
		while(start <= end) {
			addEnd(start);
			start = start + step;
		}
	}
	public void read(String fileName)  {
		Scanner input;
		try {
			input = new Scanner(new File(fileName));
			while(input.hasNextLine()) {
				String s = input.nextLine();
				s = s.trim();
				s = s.replaceAll(" +", " ");
				String[] split = s.split(" ");
				if("ADD_FRONT".equals(split[0])) {
					split[1] = split[1].trim();
					String[] arr = split[1].split(":");
					addStart(Integer.parseInt(arr[0]),Integer.parseInt(arr[1]),Integer.parseInt(arr[2]));
					show();
				}else if("ADD_BACK".equals(split[0])) {
					split[1] = split[1].trim();
					String[] arr = split[1].split(":");
					addEnd(Integer.parseInt(arr[0]),Integer.parseInt(arr[1]),Integer.parseInt(arr[2]));
					show();
				}else if("REMOVE_FRONT".equals(split[0])) {
					removeStart(Integer.parseInt(split[1]));
					show();
				}else if("REMOVE_BACK".equals(split[0])) {
					removeEnd(Integer.parseInt(split[1]));
					show();
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private int size() {
		if(head == null)
			return 0;
		int size = 1;
		for(Node q = head; q.next != null; q = q.next) {
			size++;
		}
		return size;
	}
	private void removeStart(int n) {
		if(n >= size()) {
			head = null;
			tail = null;
		}else {
			while(n > 0) {
				removeStart();
				n--;
			}
		}
	}
	private void removeEnd(int n) {
		if(n > size()) {
			head = null;
			tail = null;
		}else {
			while(n > 0) {
				removeEnd();
				n--;
			}
		}
	}
	private void removeStart() {
		if(head != null && head.next != null) {
			head = head.next;
			head.prev = null;
		}else {
			head = null;
			tail = null;
		}
	}
	private void removeEnd() {
			if(tail != null && tail.prev != null) {
				tail = tail.prev;
				tail.next = null;
			}else{
				head = tail = null;
			}
	}
	private void addEnd(int i) {
		if(tail == null) {
			Node temp = new Node(null,null,i);
			head = temp;
			tail = temp;
		}else {
			Node temp = new Node(tail,null,i);
			tail.next = temp;
			tail = temp;
		}
	}
	private void addStart(int i) {
		if(head == null) {
			Node temp = new Node(null,null,i);
			head = temp;
			tail = temp;
		}else {
			Node temp = new Node(null,head,i);
			head = temp;
			head.next.prev = head;
		}
	}
}
