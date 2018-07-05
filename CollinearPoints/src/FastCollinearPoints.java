
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FastCollinearPoints {
	private LineSegment[] lineSegments;
	private List<Point> startPoints,endPoints;
	
   public FastCollinearPoints(Point[] points){
	   
	   if (points == null)throw new java.lang.IllegalArgumentException();
		
		int size = points.length;
		
		for(int i = 0; i < size; i++){
			if(points[i] == null)throw new java.lang.IllegalArgumentException();
		}
		
		List<LineSegment> lines = new ArrayList<LineSegment>();
		startPoints = new ArrayList<Point>();
		endPoints = new ArrayList<Point>();
		
		Point[] slopePoints = points.clone();
		
		for (int i = 0; i < size; i++){
			Arrays.sort(slopePoints,points[i].slopeOrder());
			Point p = slopePoints[0];
			
			double oldSlope = Double.NEGATIVE_INFINITY;
			
			List<Point> posiblePoints = new ArrayList<Point>();
			
			for(int j=1 ; j < size; j++){
				Point q = slopePoints[j];
				if(p.compareTo(q) == 0 )throw new java.lang.IllegalArgumentException();
				
				double currSlope = p.slopeTo(q);
				
				if(posiblePoints.isEmpty()){
					posiblePoints.add(q);
					oldSlope = currSlope;
				}else{
					if(oldSlope == currSlope){
						posiblePoints.add(q);
						if(j >= size - 1 && posiblePoints.size() >= 3 ){
							Point[] tempPoints = posiblePoints.toArray(new Point[posiblePoints.size()]);
							Arrays.sort(tempPoints);
							int tempSize = tempPoints.length;
							insertSegment(lines,tempPoints[0],tempPoints[tempSize-1]);
							posiblePoints.clear();
							
						}
					}else{
						if(posiblePoints.size() >= 3){
							Point[] tempPoints = posiblePoints.toArray(new Point[posiblePoints.size()]);
							Arrays.sort(tempPoints);
							int tempSize = tempPoints.length;
							insertSegment(lines,tempPoints[0],tempPoints[tempSize-1]);
							}else{
								posiblePoints.clear();
								posiblePoints.add(q);
								oldSlope = currSlope;
							}
					
					}
					
					
					
				}
				
		
				
				
			}
			
			
		}
		
		lineSegments = lines.toArray(new LineSegment[lines.size()]);
	
	
		
	   // finds all line segments containing 4 or more points
   }
   public           int numberOfSegments(){
	   return lineSegments.length;
	   // the number of line segments
   }
   public LineSegment[] segments(){
	   return lineSegments;
	   // the line segments
   }
   
   private void insertSegment(List<LineSegment> lines, Point p, Point q){
	   boolean exists = false;
		
		for(int n = 0; n < lines.size(); n++){
			if(startPoints.get(n).compareTo(p) == 0 && endPoints.get(n).compareTo(q) == 0 ){
				exists = true;
			}
		}
		if (!exists){
			lines.add(new LineSegment(p,q));
		    startPoints.add(p);
			endPoints.add(q);	
		}
   }
   
 }
