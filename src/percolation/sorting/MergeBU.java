package sorting;

import java.util.Arrays;

public class MergeBU extends Sort {
	
	private static Comparable[] aux;
	
	private static void merge(Comparable[] a, int lo, int mid, int hi) {
		aux = Arrays.copyOf(a, a.length);
		
		int i = lo, j=mid+1;
		for (int k = lo; k <= hi; k++) {
			if (i > mid)					a[k] = aux[j++];
			else if (j > hi)				a[k] = aux[i++];
			else if (less(aux[j], aux[i])) 	a[k] = aux[j++];
			else 							a[k] = aux[i++];
		}
	}

	public static void sort(Comparable[] a) {
		int N = a.length;
		aux = new Comparable[N];
		for (int sz = 1; sz < N; sz = sz+sz) {
			for (int lo = 0; lo < N-sz; lo += sz+sz) {
				merge(a, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1));
			}
		}
	}
}
