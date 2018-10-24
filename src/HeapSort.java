
public class HeapSort {
	public static void main(String[] args) {
		int[]a = createArray(1000);
		heap(a,a.length - 1);
		verify(a);
		sys(a);
	}
	private static void sys(int[] a) {
		for (int i : a) {
			System.out.print(i + " ");
		}
	}
	public static int[] createArray(int n) {
		int[]arr = new int[n];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int)(Math.random() * arr.length);
		}
		return arr;
	}
	public static void verify(int[]arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			if(arr[i] > arr[i + 1]) {
				System.out.println("Sort is wrong");
				throw new RuntimeException();
			}
		}
	}
	public static void heapify(int[]arr, int i, int heapSize) {
		int left, right, maxIndex;
		left = 2 * i + 1;
		right = 2 * i + 2;
		if(left <= heapSize && arr[left] > arr[i]) {
			maxIndex = left;
		}else {
			maxIndex = i;
		}
		if(right <= heapSize && arr[right] > arr[maxIndex]) {
			maxIndex = right;
		}
		if(maxIndex != i) {
			swap(arr,i,maxIndex);
			heapify(arr, maxIndex, heapSize);
		}
	}
	private static void swap(int[]arr,int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	public static void heap(int[]arr, int heapSize) {
//		if(heapSize == 0)
//			return;
		int t = ((arr.length - 1) - 1) / 2;
		for (int i = t; i >= 0; i--) {
			heapify(arr, i, heapSize);
		}
		for(int i = arr.length - 1; i > 0; i--) {
			swap(arr,0,i);
			heapify(arr, 0, i - 1);
		}
	}
}
