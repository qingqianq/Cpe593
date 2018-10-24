/*
 * Author: Guangqi Qing
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class QuickSortWithInsert {
	public static void main(String[] args) {
		for (int i = 0; i < 1000; i++) {
			test(0,15);
		}
//		test(77, 10000000);
		test(0,400,2000000); // The first parameter is where n starts and the second is where n ends, the third is the length of the random array.
//		int[]array = getArray();
//		sys(array);
//		quickSort(array);
//		sys(array);
	}
	public static int[] quickSort(int[]arr) {
		return quickSort(arr,0,arr.length - 1);
	}
	public static int[] getArray() {
		try {
			FileReader fr = new FileReader("src/hw3.dat");
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
	public static int[] quickSort(int[]arr, int a, int b) {
		return quickSortWihtInsert(arr, 0, a, b);
	}
	public static void test(int start,int end, int num) {
		if(end == start) {
			test(start,num);
			return;
		}
		long[]time = new long[end];
		long shortestTime = Long.MAX_VALUE;
		StringBuilder s = new StringBuilder();
		ArrayList<Integer> shortestIndex = new ArrayList<Integer>();
		int[]arr = createArray(num);
		for (int i = start; i < end; i++) {
				time[i] = sortTime(arr, i);
				System.out.println("n = "+ i + ", time : " + time[i] + " ms");
			if( shortestTime > time[i]) {
				shortestTime = time[i];
			}
		}
		for (int j = 0; j < time.length; j++) {
			if(time[j] == shortestTime) {
			shortestIndex.add(j);
			}
		}
		for (int i : shortestIndex) {
			s.append(i);
			s.append(" ");
		}
		System.out.println("The shortest n is "+ s.toString() + ", and the shorest time is "+ shortestTime + " ms in " + num + " length array");
	}
	public static void test(int n,int num) {
		int[]arr = createArray(num);
			long time = sortTime(arr, n);
			System.out.println("When n is "+ n + ", time : " + time + "ms");
	}
	public static int[] createArray(int n) {
		int[]arr = new int[n];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int)(Math.random() * arr.length);
		}
		return arr;
	}
	public static long sortTime(int[]arr,int n) {
		int[] test = Arrays.copyOf(arr, arr.length);
		long startTime = System.currentTimeMillis();
		test = quickSortWihtInsert(test, n, 0, test.length - 1);
		long endTime = System.currentTimeMillis();
		verify(test);
//		sys(arr);
//		sys(test);
//		System.out.println(endTime - startTime);
		return endTime - startTime;
		
	}
	public static int[] quickSortWihtInsert(int[]arr,int n,int a ,int b) {
		int i = a, j = b;
		if(a >= b)
			return arr;
		int s = b - a + 1;
		if(s <= n) {
			insertSort(arr, a, b);
			return arr;
		}else {
			Random r =new Random();
			int random = a + r.nextInt(b - a);
			int target = arr[random];
			while(j >= i) {
				while(arr[i] < target) {
					i++;
				}
				while(arr[j] > target ) {
					j--;
				}
				if(j >= i) {
				swap(arr,i,j);
				++i;
				--j;
				}
			}
		}
		quickSortWihtInsert(arr, n, a, j);
		quickSortWihtInsert(arr, n, i, b);
		return arr;
	}
//	public static int[] quickSort(int[]arr, int a, int b) {
//		if(a >= b)
//			return arr;
//		int c = quickSortPart(arr,a,b);
//		quickSort(arr,a,c-1);
//		quickSort(arr,c+1,b);
//		return arr;
//	}
	
	public static void swap(int[]arr, int a,int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
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
	public static void sys(int[]arr) {
		for (int i : arr) {
			System.out.print(i+" ");
		}
		System.out.print("\n");
	}
	/**
	 * verify the array is sorted correctly
	 * @param arr
	 */
	public static void verify(int[]arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			if(arr[i] > arr[i + 1]) {
				System.out.println("Sort is wrong");
				throw new RuntimeException();
			}
		}
	}
}
