/*
 * Author: Guangqi Qing
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class InsertSort {
	public static void main(String[] args) {
		int[]arr = getArray();
		sys(arr);
		insertSort(arr);
		sys(arr);
	}
	public static void sys(int[]arr) {
		for (int i : arr) {
			System.out.print(i+" ");
		}
		System.out.print("\n");
	}
	public static int[] insertSort(int[]arr,int a,int b) {
		int key ;
		for (int i = a + 1; i <= b; i++) {
			key = arr[i];
			int n = i - 1;
			while(n >= a && arr[n] > key) {
				arr[n+1] = arr[n];
				n--;
			}
			arr[n+1] = key;
		}
		return arr;
	}
	public static int[] insertSort(int[]arr) {
		return insertSort(arr,0,arr.length - 1);
	}
	public static int[] getArray() {
		try {
			FileReader fr = new FileReader("hw3.dat");
			BufferedReader buf  = new BufferedReader(fr);
			String line = null;
			ArrayList<String> list = new ArrayList<String>();
			while((line = buf.readLine()) != null) {
				list.add(line);
			}
			String sArray = list.get(1);
			sArray = sArray.trim();
			sArray = sArray.replaceAll(" +", " ");
			String[] string = sArray.split(" ");
			int[]arr = new int[string.length];
			for (int i = 0; i < string.length; i++) {
				arr[i] = Integer.parseInt(string[i]);
//				System.out.println(arr[i]);
			}
			return arr;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
