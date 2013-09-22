/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new SlopeOrderComparator(this);
    
    private class SlopeOrderComparator implements Comparator<Point> {

    	private Point origin;
    	
    	SlopeOrderComparator(Point origin) {this.origin = origin;}
    	
    	/*
    	 *  Formally, the point (x1, y1) is less than the point (x2, y2) if and only if 
    	 *  the slope (y1 − y0) / (x1 − x0) is less than the slope (y2 − y0) / (x2 − x0).
    	 *  Treat horizontal, vertical, and degenerate line segments as in the slopeTo() method.
    	 */
		@Override
		public int compare(Point p1, Point p2) {
			double slopeP0P1 = origin.slopeTo(p1);
			double slopeP0P2 = origin.slopeTo(p2);
			return (int) (slopeP0P1 - slopeP0P2);
		}
    }

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        /* The slopeTo() method should return the slope between the invoking point (x0, y0) and the argument point (x1, y1), 
         * which is given by the formula (y1 − y0) / (x1 − x0). Treat the slope of a horizontal line segment as positive zero 
         * [added 7/29]; treat the slope of a vertical line segment as positive infinity; 
         * treat the slope of a degenerate line segment (between a point and itself) as negative infinity.
         */
    	int compareVal = this.compareTo(that);
    	
    	double returnVal = 0.0; // horizontal case
    	if (compareVal == 0) { // degenerate case
    		returnVal = Double.NEGATIVE_INFINITY;
    	} else if (this.x == that.x) { // vertical case
    		returnVal = Double.POSITIVE_INFINITY;
    	} else if (this.y != that.y) { 
    		returnVal = ((double) that.y - this.y) / ((double) that.x - this.x);    		
    	}
    	return returnVal;
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        // Formally, the invoking point (x0, y0) is less than the argument point (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
    	if (this.y < that.y || (this.y == that.y && this.x < that.x)) return -1;
    	else if (this.y == that.y && this.x == that.x) return 0;
    	else return 1;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
    	System.out.println("Starting tests...");
    	
        // compare tests
    	Point p0 = new Point(0,0);
    	Point p1 = new Point(1,1);
    	Point p2 = new Point(1,2);
    	Point p3 = new Point(2,3);
    	Point p4 = new Point(3,2);
    	
    	// <
    	System.out.println("Testing <...");
    	assert(p0.compareTo(p1) < 0);
    	assert(p1.compareTo(p2) < 0);
    	// ==
    	System.out.println("Testing ==...");
    	assert(p0.compareTo(p0) == 0);
    	// >
    	System.out.println("Testing >...");
    	assert(p3.compareTo(p0) > 0);
    	assert(p2.compareTo(p1) > 0);
    	
    	// slope tests
    	System.out.println("Testing p0 slope to p1");
    	assert(p0.slopeTo(p1) == 1.0);
    	System.out.println("Testing p0 slope to p1");
    	assert(p0.slopeTo(p2) == 2.0);
    	System.out.println("Testing degenerate");
    	assert(p0.slopeTo(p0) == Double.NEGATIVE_INFINITY);
    	System.out.println("Testing vertical");
    	assert(p1.slopeTo(p2) == Double.POSITIVE_INFINITY);
    	System.out.println("Testing horizontal");
    	assert(p2.slopeTo(p4) == 0.0);
    	
    	// comparator tests
    	System.out.println("Compare p0 to p1 and p2");
    	System.out.println(p0.SLOPE_ORDER.compare(p1, p2));
    }
}
