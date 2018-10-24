import java.math.BigDecimal;
import java.util.Random;
/*
 * Author: Guangqi Qing
 * cite : really thanked my clever roommate Guibin Tang
 */
public class Matrix {
	private int col,row;
	double[][]arr;
	public Matrix(int row,int col) {
		this.row = row;
		this.col = col;
		arr = new double[row][col];
	}
	public void getTriangularMatrix(double arr[][]) {
		for(int c = 0; c < arr.length; c++) {
			for(int r = c+1; r < arr[0].length-1; r++) {
				if(arr[r][c]==0)
					continue;
				double ratio = arr[r][c]/arr[c][c] * (-1);
//				System.out.println(ratio+"   ratio");
				for(int k = 0; k < arr[0].length; k++) {
					arr[r][k] =arr[r][k] + arr[c][k]*ratio; 
				}
//				printArray(arr);
//				System.out.println();
			}
		}
	}
	public static void main(String[] args) {
		int n = 9;
		Matrix m = getRandomMatrix(n);
		System.out.println("Cannot find the input data, so use random data.");
		System.out.println("Random matrix(value between -100 and 100 ): ");
		printArray(m.arr);
		System.out.println();
		System.out.println("Changed triangular matrix : ");
		m.getTriangularMatrix(m.arr);
		printArray(m.arr);
		System.out.println();
		System.out.println("Equation : ");
		m.getEquation(n);
//		double[][]array = {{1,2,0,0,0,8},{1,4,1,0,0,3},{0,1,4,1,0,9},{0,0,1,4,1,4},{0,0,0,1,2,5}};
//		Matrix matrix = new Matrix(5,6);
//		matrix.arr = array;
//		matrix.getTriangularMatrix(matrix.arr);
//		printArray(matrix.arr);
//		System.out.println();
//		matrix.getEquation(5);
	}
	private void getEquation(int n) {
//		e[5] = b5 / a[5][5];
//		e[4] = b4 - e[5]* a[4][5] / a[4][4]
//		e[3] = b3 - e[5]* a[3][5] - e[4]*a[3][4]/ a[3][3]
//		e[n-1] = arr[n-1][n]/arr[n-1][n-1];
//		System.out.println(e[n-1]);
		double[]e = new double[n];
		for(int i = 0; i < n ; i++) {
			double sum = 0;
			for(int j=0; j<i; j++) {
				sum = sum + e[n-1-j]*arr[n-1-i][n-1-j];
			}
			e[n-1-i] = (arr[n-1-i][n] - sum) / arr[n-1-i][n-1-i];
		}
		for (int i = 0; i < e.length; i++) {
			System.out.println("X"+(i+1)+" = " +e[i]);
		}
	}
	public static void printArray(double[][]arr) {
		BigDecimal b = null;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				arr[i][j] = getSmallDouble(arr[i][j]);
				System.out.print(arr[i][j] + "    ");
			}
			System.out.println();
		}
	}
	public static Matrix getRandomMatrix(int n){
		Matrix m = new Matrix(n,n+1);
		Random r = new Random(); 
		for (int i = 0; i < m.arr.length; i++) {
			for (int j = 0; j < m.arr[0].length; j++) {
				m.arr[i][j] = r.nextDouble() * 200 - 100;
				m.arr[i][j] = Matrix.getSmallDouble(m.arr[i][j]);
			}
		}
		return m;
	}
	public static double getSmallDouble(double d) {
		BigDecimal b = new BigDecimal(d);
		d = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return d;
	}

}
