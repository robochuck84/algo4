package percolation;

import java.util.Random;

public class PercolationStats {
	private double mean;
	private double stddev;
	private double confidenceLo;
	private double confidenceHi;

	public PercolationStats(int N, int T) {
		// perform T independent computational experiments on an N-by-N grid
		double[] experimentValues = new double[T];
		int totalSquares = N * N;
		
		Random random = new Random();
		
		for (int i = 0; i < T; i++) {
			Percolation perc = new Percolation(N);
			int numOpen = 0;
			int x, y;
			while(!perc.percolates()) {
				x = random.nextInt();
				y = random.nextInt();
				perc.open(x, y);
				numOpen++;
			}
			experimentValues[i] = numOpen / totalSquares;
		}
	}

	public double mean() {
		// sample mean of percolation threshold
		return 0.0;
	}

	public double stddev() {
		// sample standard deviation of percolation threshold
		return 0.0;
	}

	public double confidenceLo() {
		// returns lower bound of the 95% confidence interval
		return 0.0;
	}

	public double confidenceHi() {
		// returns upper bound of the 95% confidence interval
		return 0.0;
	}

	public static void main(String[] args) {
		// test client, described below
	}
}
