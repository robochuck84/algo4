import java.util.ArrayList;
import java.util.List;


public class Brute {

	public static void main(String[] args) {
        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);

        // read in the input
        String filename = "C:\\Users\\troge_000\\Documents\\Algo1\\collinear\\input8.txt";
        In in = new In(filename);
        int N = in.readInt();
        List<Point> points = new ArrayList<Point>();
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x, y);
            points.add(p);
        }
        
        int length = points.size();
        for (int i = 0; i < length; i++) {
        	Point p = points.get(i);
        	for (int j = i + 1; j < length; j++) {
        		Point q = points.get(j);
        		for (int k = j + 1; k < length; k++) {
        			Point r = points.get(j);
        			for (int l = k + 1; l < length; l++) {
        				Point s = points.get(l);
        				double slopePQ = p.slopeTo(q);
        				double slopePR = p.slopeTo(r);
        				double slopePS = p.slopeTo(s);
        				System.out.println(String.format("PQ:%s PR:%s PS:%s", new Double(slopePQ), new Double(slopePR), new Double(slopePS)));
        				if (slopePQ == slopePR && slopePQ == slopePS) {
        					p.drawTo(s);
        				}
        			}
        		}
        	}
        }
        StdDraw.show();
	}
}
