import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Trie {
	private static trieNode root;
	private static int SIZE = 26;
	private static class trieNode{
		private int num;	// how many words pass this node
		trieNode[]son;	// its son Node
		char c;
		boolean isEnd;
		trieNode(){
			num = 1; 
			son = new trieNode[SIZE];
			isEnd = false;
		}
	}
	Trie (){
		this.root = new trieNode();
	}
	public void insert(String s) {
		if(s.length() == 0 || s==null)
			return;
		char[] c = s.toCharArray();
		trieNode node = root;
		for (int i = 0; i < c.length; i++) {
			int pos = getIndex(c[i]);
			if(node.son[pos] == null) {
				node.son[pos] = new trieNode();
				node.son[pos].c = c[i];
			}else {
				node.son[pos].num++;
			}
			node = node.son[pos];
		}
		node.isEnd = true;
	}

	private int getIndex(char c) {
		if ((int)c >= (int)'a' && (int)c <= (int)'z')
			return (int)c - (int)'a';
		else 
			throw new RuntimeException("Not a lower character in trie");
	}
	public boolean find(String s) {
		if(s.length() == 0 || s == null) 
			return false;
		char[] cs = s.toCharArray();
		trieNode node= root;
		for(int i = 0; i < cs.length; i++) {
			int pos = getIndex(cs[i]);
			if(node.son[pos]!= null) {	//node.son[pos] != null  写成node.son != null
				node = node.son[pos];
			}
			else {
				return false;
			}
		}
		return node.isEnd;
	}
	public void findPrefix(String prefix) {
		if(prefix.length() == 0 || prefix == null)
			return;
		char[]letters = prefix.toCharArray();
		trieNode node = root;
		for(int i = 0; i < letters.length; i++) {
			int pos = getIndex(letters[i]);
			if(node.son[pos] == null) {
				System.out.println("No matches");
				return;
			}else {
				node = node.son[pos];
			}
		}
		preTraverse(node,prefix);
	}
	public void preTraverse(trieNode node) {
		if(node!=null) {
			if(node.isEnd) 
				System.out.print(node.c);
			for(trieNode son : node.son) {
				preTraverse(son);
			}
		}
	}
	public void preTraverse(trieNode node,String prefix) {
//		if(node == null)
//			return;
//		else	 {
			if(node.isEnd)
				syso(prefix);
			for (trieNode son : node.son) {
				if(son == null)
					continue;
				preTraverse(son,prefix+son.c);
			}
//		}
	}
	public static void syso(Object o) {
		System.out.println(o.toString());
	}
	public void initial() throws FileNotFoundException {
		Scanner scanner = new Scanner(System.in);
		syso("Please input the file name:");
		String fileName = scanner.nextLine();
		scanner = new Scanner(new File(fileName));
		while(scanner.hasNextLine()) {
			String word = scanner.nextLine();
			this.insert(word);
		}
		syso("initialed");
		syso("-------------");
	}
	public static void main(String[] args) throws FileNotFoundException {
		Trie trie = new Trie();
		trie.initial();
		trie.findPrefix("abcd");
		syso(trie.find("bbb"));
		new LinkedList<>().add(3, 5);
	}
}
