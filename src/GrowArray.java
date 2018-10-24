import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/*
 * Author: Guangqi Qing
 * cite: Really thank for tutor Yifan Qu
 */
public class GrowArray {
	private MyPoint[] myPoint;
	private int used;
	private int capacity;
	public GrowArray() {
		myPoint = new MyPoint[10];
		capacity = 10;
	}
	public MyPoint[] getMyPoint() {
		return myPoint;
	}
	public GrowArray(int x) {
		myPoint = new MyPoint[x];
		capacity = x;
	}
	private void grow() {
		MyPoint[]m = new MyPoint[capacity *= 2];
		for(int i = 0; i < myPoint.length; i++) {
			m[i] = myPoint[i];
		}
		myPoint = m;
	}
	private void checkGrow() {
		if(used == capacity)
			grow();
	}

	public void addEnd(MyPoint p) {
		checkGrow();
		myPoint[used] = p;
		used++;
	}
	public void addStart(MyPoint p) {
		checkGrow();
		for (int i = used; i > 0; i--) {
			myPoint[i] = myPoint[i-1];
		}
		myPoint[0] = p;
		used++;
	}
	public void removeEnd() {
		if(used == 0)
			return;
		myPoint[used - 1] = null;
		used = used - 1;
		return;
	}
	public void removeStart() {
		if(used == 0)
			return;
		for(int i = 0; i < myPoint.length; i++) {
			myPoint[i] = myPoint[i + 1];
		}
		used = used - 1;
	}
	public void remove(int i) {
		if(i > used)
			return;
		int j = i - 1;
		for(; j < used - 1; j++ ) {
			myPoint[j] = myPoint[j + 1];
		}
		myPoint[j-1] = null;
		used = used - 1;
	}
	public int size() {
		return used;
	}
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		for (MyPoint m : myPoint) {
			if(m == null) continue;
			stringBuilder.append(m.toString());
		}
		return stringBuilder.toString();
	}
	public static void main(String[] args) {
//		GrowArray g = new GrowArray();
//		for (int i = 0; i < 10; i++) {
//			g.addEnd(new MyPoint(i,2));
//			System.out.println(g.used);
//			System.out.println(g.size());
//		}
//		g.removeStart();
//		g.removeEnd();
//		g.remove(4);
//		System.out.println(g.toString());
//		System.out.println(g.size());
		Scanner input = new Scanner(System.in);
		System.out.println("Please input the file name :");
		String fileName = input.nextLine();
		ConvexHull convex = new ConvexHull(16);
		convex.read(fileName);
		convex.printMinMax();
		convex.printAllListSizes();
		System.out.println("---------------");
		convex.printPerimeterClockWiseOrder();
		
	}
}
class ConvexHull{
	private GrowArray[]data;
	double maxX =Double.MIN_VALUE, maxY = Double.MIN_VALUE, minX = Double.MAX_VALUE, minY = Double.MAX_VALUE;
	private int size;
	public ConvexHull(int x) {
		size = x;
		data = new GrowArray[size * size];
		for (int i = 0; i < x * x; i++) {
			data[i] = new GrowArray();
		}
	}
	public  void printAllListSizes() {
		for (int i = 0; i < data.length; i++) {
			System.out.println("gird["+i+"] has "+data[i].size()+" points.");
		}
	}
	public void printMinMax() {
		System.out.println("maxX = "+maxX+"  minX = "+minX+"  maxY = "+maxY+"  minY = "+minY);
	}
	public void printPerimeterClockWiseOrder() {
		for (int i = 0; i < size; i++) {
			System.out.println("gird["+i+"] has "+data[i].size()+" points."+data[i].toString());
		}
		System.out.println();
		for (int i = 1; i < size ; i++) {
			int temp = size*(i+1)-1;
			System.out.println("gird["+temp+"] has "+data[temp].size()+" points."+data[temp].toString());
		}
		System.out.println();
		for(int i = 1; i < size; i++) {
			int temp = data.length - i - 1;
			System.out.println("gird["+temp+"] has "+data[temp].size()+" points."+data[temp].toString());
		}
		System.out.println();
		for (int i = size - 2; i > 0; i--) {
			int temp = i * size;
			System.out.println("gird["+temp+"] has "+data[temp].size()+" points."+data[temp].toString());
		}
	}

	public void read(String fileName) {
		double x,y;
		GrowArray g = new GrowArray();
		Scanner input = null;
		try {
			input = new Scanner(new File(fileName));
			while(input.hasNext()) {
				x = input.nextDouble();
				y = input.nextDouble();
				g.addEnd(new MyPoint(x,y));
				maxX = (maxX > x) ? maxX : x;
				minX = (minX > x) ? x : minX;
				maxY = (maxY > y) ? maxY : y;
				minY = (minY > y) ? y : minY;
			}
			double rangeX,rangeY;
			System.out.println(maxX);
			System.out.println(minX);
			System.out.println(maxY);
			System.out.println(minY);
			rangeX = (maxX - minX)/size ;
			rangeY = (maxY - minY)/size ;
			int p,q,target;
			MyPoint[] m = g.getMyPoint();
			for (int i = 0; i < g.size(); i++) {
				p = (int)((m[i].getX() - minX)/rangeX);
				q = (int)((m[i].getY() - minY)/rangeY);
				p = (p==size)?(p-1):p;
				q = (q==size)?(q-1):q;
				target = p + q * size;
				data[target].addEnd(m[i]);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
class MyPoint{
	private double x, y;
	public MyPoint() {}
	public MyPoint(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public String toString() {
		return " x = "+ x +" ,y = "+ y + ";	";
	}
}