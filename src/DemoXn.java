
public class DemoXn {
	public static void main(String[] args) {
		System.out.println((int)'a'+"   " + (int)'z');
		for (int i = 2; i < 560; i++) {
			long p = 561;
			int a = xNMod(i, p-1, p );
//			if(a != 1)
			System.out.println(i+"--"+a);
		}
	}
	public static int xNMod(long x, long n, long m) {
		long prod = 1;
		while (n > 0) {
			if(n % 2 != 0) {
				prod = (x * prod) % m;
			}
			x = x * x % m; // x represents x^2 seconds time x represents x^4
			n = n / 2;
		}
		return (int)prod;
	}
	public static int xN(int x, int n) {
		int prod = 1;
		while (n > 0) {
			if (n % 2 != 0) {
				prod *= x;
			}
			x = x * x;
			n = n / 2;
			System.out.println(n);
		}
		return prod;
	}
}
