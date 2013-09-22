package sorting;

public abstract class Sort {

	static boolean less(Comparable a, Comparable b) {
		return a.compareTo(b) < 0;
	}
}
