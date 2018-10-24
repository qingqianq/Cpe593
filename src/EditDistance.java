import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
/*
 * Author: Guangqi Qing
 */
public class EditDistance {
	public static char[] readFile()  {
		String path;
		StringBuffer sb = new StringBuffer();
		Scanner input = new Scanner(System.in);
		System.out.println("Please print one of the file paths（such as ：src/packageName/fileName.txt）: ");
		path = input.nextLine();
		try {
			FileReader fr = new FileReader(path);
			int ch;
			while(( ch = fr.read()) != -1) {
				sb.append((char) ch);
			}
			String content = sb.toString();
			return content.toCharArray();
		} catch (IOException e) {
			System.out.println("The file doesn't exist.");
			e.printStackTrace();
		}
		return null;
	}
	public static int editDistance(char[] a,char[] b){
		int m = a.length;
		int n = b.length;
		if(m == 0 || n == 0)
			return m + n;
		int[][] c= new int[m + 1][n + 1];
		c[0][0] = (a[0] == b[0]) ? 0 : 1;
		for (int i = 0; i <= m; i++) {
			c[i][0] = i;
			}
		for(int j = 0; j <= n; j++) {
			c[0][j] = j;
		}
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				int min = min(c[i - 1][j], c[i][j - 1], c[i - 1][ j - 1]);
				c[i][j] = (b[j-1] == a[i-1]) ?c[i - 1][j - 1] : min + 1;
			}
		}
		return c[m][n];
	}
	public static int min(int a, int b, int c) {
		return min(min(a,b), c);
	}
	public static int min(int a, int b) {
		return a > b ? b : a;
	}
	public static void main(String[] args) {
		char[] c1 = readFile();
		char[] c2 = readFile();
//		char[]c2= {'a','b','b','b','d'};
//		char[]c1= {'a','b','c','d'};
		int distance = editDistance(c1, c2);
		System.out.println("The shortest way to change 2 files to be same is "+ distance+" .");
	}
}
