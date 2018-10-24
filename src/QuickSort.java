public class QuickSort {
	public static void main(String[] args) {
		int[]arr = {5,4,3,3,3,5,4,4,4,5};
		arr = quickSort(arr, 0, arr.length - 1);
		sys(arr);
	}
	private static int[] quickSort(int[] arr, int i, int j) {
		if(j - i <= 0) {
			return arr;
		}else {
			int q = part2(arr, i, j);
			quickSort(arr, i, q-1);
			quickSort(arr, q + 1, j);
			return arr;
		}
	}

	public static int part1(int[] arr, int i, int j) {
		int key = (int) (i + Math.random() * (j - i + 1)); 
		arr = swap(arr, key, j);
		int target = arr[j];
		while (j > i) {
			while(j > i && arr[i] < target) {
				i++;
			}
			arr[j] = arr[i];
			while(j > i && arr[j] >= target) {
				j--;
			}
			arr[i] = arr[j];
		}
		arr[i] = target;
		return i;
	}
	public static int[] swap(int[]arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
		return arr;
	}
	public static void sys(int[] arr) {
		for (int i : arr) {
			System.out.println(i);
		}
	}
	public static int part2(int[]arr, int a, int b) {
		int random = a + (int)(Math.random() * (b - a));
//		System.out.println("random : " + random);
		swap(arr, random, b);
		int m = a, n = b - 1;
		int target = arr[b];
		while(n >= m) {
			while(arr[m] < target) {
				m++;
			}
			while(n >= a && arr[n] >= target ) {
				n--;
			}
			if(m >= n)
				break;
			swap(arr,m,n);
			++m;
			--n;
		}
		swap(arr,n+1,b);
		return n+1;
	}
}
