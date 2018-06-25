import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {
	private LineSegment[] lines;
	
	public FastCollinearPoints(Point[] points){
		 List<LineSegment> tempLines = new ArrayList<LineSegment>();
		int size = points.length;
		
		for(int i = 0; i < size; i++){
			List<Point> tempPoints = new ArrayList<Point>();
			for(int j = 0; i < size; j++){
				if (i == j)continue;
				tempPoints.add(points[i]);
			}
			Point[] arrPoints = new Point[tempPoints.size()];
			
			arrPoints = tempPoints.toArray(arrPoints);
			
			Arrays.sort(arrPoints, points[i].slopeOrder());
			
			Point[] tempiPoints = {arrPoints[0],arrPoints[1],arrPoints[2]};
			Arrays.sort(tempiPoints);
			
			 double pq =  points[i].slopeTo(tempiPoints[0]);
			 double pr = points[i].slopeTo(tempiPoints[1]);
			 double ps = points[i].slopeTo(tempiPoints[2]);
			 
			 
			  
			  if(pq == pr && pq == ps){
				  
				  Point[] temp = {points[0], points[1], points[2]};
				  Arrays.sort(temp);
				  System.out.println("Punto inicio: " + points[0] + "Punto final: " + points[2]);
				  
				  tempLines.add(new LineSegment(points[0], temp[2]));
			  }
			
			
		}
		
		 lines = new LineSegment[tempLines.size()];
		 this.lines = tempLines.toArray(this.lines);
		
		// finds all line segments containing 4 or more points
	}
    public int numberOfSegments(){
    	return this.lines.length;
    	// the number of line segments
    }
    public LineSegment[] segments(){
    	return this.lines;
    	// the line segments
    }
}
