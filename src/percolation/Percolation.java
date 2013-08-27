package percolation;

public class Percolation {

	private static final int OPEN = 1;
	private static final int CLOSED = 0;
	private static final int TOP_ROW = 0;
	private final int BOTTOM_ROW;

	private int[] sites;
	 WeightedQuickUnionUF qu;
	private int N;

	public Percolation(int N) {
		// create N-by-N grid, with all sites blocked
		this.sites = new int[N * N];
		this.qu = new WeightedQuickUnionUF((N * N) + 2);
		this.N = N;
		BOTTOM_ROW = (N * N) + 1;
	}

	private int find(int i, int j) {
		return ((i -1) * N) + (j - 1);
	}

	private int findInUF(int i, int j) {
		return find(i, j) + 1;
	}

	public void open(int i, int j) {
		// open site (row i, column j) if it is not already
		if (i <= 0 || i > N)
			throw new IndexOutOfBoundsException("row index i out of bounds");
		if (j <= 0 || j > N)
			throw new IndexOutOfBoundsException("row index j out of bounds");

		if (i == 1)
			qu.union(TOP_ROW, findInUF(i, j));
		if (i == (N))
			qu.union(BOTTOM_ROW, findInUF(i, j));

		int iless = i - 1, imore = i + 1, jless = j - 1, jmore = j + 1;
		if (iless > TOP_ROW && isOpen(iless, j))
			qu.union(findInUF(iless, j), findInUF(i, j));
		else if (jless > TOP_ROW && isOpen(i, jless))
			qu.union(findInUF(i, jless), findInUF(i, j));
		else if (imore <= N && isOpen(imore, j))
			qu.union(findInUF(imore, j), findInUF(i, j));
		else if (jmore <= N && isOpen(i, jmore))
			qu.union(findInUF(i, jmore), findInUF(i, j));

		sites[find(i, j)] = OPEN;
	}

	public boolean isOpen(int i, int j) {
		// is site (row i, column j) open?
		if (i <= 0 || i > N)
			throw new IndexOutOfBoundsException("row index i out of bounds");
		if (j <= 0 || j > N)
			throw new IndexOutOfBoundsException("row index j out of bounds");
		return sites[find(i, j)] == OPEN;
	}

	public boolean isFull(int i, int j) {
		// is site (row i, column j) full?
		if (i <= 0 || i > N)
			throw new IndexOutOfBoundsException("row index i out of bounds");
		if (j <= 0 || j > N)
			throw new IndexOutOfBoundsException("row index j out of bounds");

		return qu.connected(TOP_ROW, findInUF(i, j));
	}

	public boolean percolates() {
		return qu.connected(TOP_ROW, BOTTOM_ROW);
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < sites.length; i++) {
			sb.append(sites[i]);
			if (i != 0 && (i+1) % N == 0)
				sb.append("\n");
		}
		sb.append(qu);
		return sb.toString();
	}
	
	public static void main(String[] args) {
		Percolation perc = new Percolation(3);
		System.out.println(perc);
		
		perc.open(1, 1);
		System.out.println(perc);

		
		perc.open(2, 2);
		System.out.println(perc);

		
		System.out.println(perc.percolates());
		
		perc.open(3, 2);
		System.out.println(perc);
		
		perc.open(1, 2);
		System.out.println(perc);
		System.out.println(perc.percolates());
	}
}
