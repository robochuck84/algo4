import java.math.BigDecimal;
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
			while (!perc.percolates()) {
				x = random.nextInt(N) + 1;
				y = random.nextInt(N) + 1;
				if (!perc.isOpen(x, y)) {
					perc.open(x, y);
					numOpen = numOpen + 1;
				}
			}
			experimentValues[i] = numOpen / (double) totalSquares;
		}

		double sum = 0.0;
		for (double exp : experimentValues)
			sum += exp;

		mean = sum / (double) T;

		sum = 0.0;
		for (double exp : experimentValues) 
			sum += Math.pow(exp - mean, 2);

		stddev = Math.sqrt(sum / (double) (T - 1));

		double calc1 = (1.96 * stddev) / Math.sqrt(T);
		confidenceLo = mean - calc1;
		confidenceHi = mean + calc1;
	}

	public double mean() {
		// sample mean of percolation threshold
		return mean;
	}

	public double stddev() {
		// sample standard deviation of percolation threshold
		return stddev;
	}

	public double confidenceLo() {
		// returns lower bound of the 95% confidence interval
		return confidenceLo;
	}

	public double confidenceHi() {
		// returns upper bound of the 95% confidence interval
		return confidenceHi;
	}

	public static void main(String[] args) {
		int N = 200;//Integer.parseInt(args[0]);
		int T = 100;//Integer.parseInt(args[1]);

		PercolationStats stats = new PercolationStats(N, T);

		String mean = "mean                    = " + stats.mean();
		String stddev = "stddev                  = " + stats.stddev();
		String conf = "95% confidence interval = " + stats.confidenceLo()
				+ ", " + stats.confidenceHi();

		System.out.println(mean);
		System.out.println(stddev);
		System.out.println(conf);
	}
}
