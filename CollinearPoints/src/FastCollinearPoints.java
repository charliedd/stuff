import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {
	private LineSegment[] lineSegments;
	
   public FastCollinearPoints(Point[] points){
	   
	   if (points == null)throw new java.lang.IllegalArgumentException();
		
		int size = points.length;
		
		if(points[size-1] == null)throw new java.lang.IllegalArgumentException();
		
		for (int i = 0;i < size - 1; i++ ){
			if(points[i] == null)throw new java.lang.IllegalArgumentException();
			for (int j = i + 1; j < size; j++){
				if(points[i].compareTo(points[j]) == 0) throw new java.lang.IllegalArgumentException();
			}
		}
		
		int nLines = 0;
		Point[] startPoints = new Point[size * size];
		Point[] endPoints = new Point[size * size];
		Point[] slopePoints = points.clone();
		
		for (int i = 0; i < size; i++){
			Arrays.sort(slopePoints,points[i].slopeOrder());
			System.out.println(Arrays.toString(slopePoints));
			Point p = slopePoints[0];
			for(int j=1 ; j < size - 2; j++){
				Point q = slopePoints[j];
				Point r = slopePoints[j+1];
				Point s = slopePoints[j+2];
				
				
				if(isCollinear(p,q,r,s)){
					
					Point[] tempPoints = {p,q,r,s};
				    Arrays.sort(tempPoints);
					
					
						//boolean exists = false;
						
						//for(int n = 0; n < nLines; n++){
							//if(startPoints[n].compareTo(tempPoints[0]) == 0 && endPoints[n].compareTo(tempPoints[3]) == 0 ){
								//exists = true;
							//}
						//}
						
						//if (!exists){
							System.out.println("YEAHHHHHHH    p: " + p + " q: " + q + " r: " + r + "s" + s);
							startPoints[nLines] = tempPoints[0];
							endPoints[nLines++] = tempPoints[3];
							
							
						//}else{
						//	System.out.println("NOOOOOOOOo  p: " + p + " q: " + q + " r: " + r + "s" + s);
						//}
					
				}
			}
			
			
		}
		
		lineSegments = new LineSegment[nLines];
		for (int i = 0; i< nLines ; i++){
			lineSegments[i] = new LineSegment(startPoints[i],endPoints[i]);
		}
		
	
	
		
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
   
   private boolean isCollinear(Point p,Point q,Point r, Point s){
		double pq =  p.slopeTo(q);
		double pr = p.slopeTo(r);
		double ps = p.slopeTo(s);
		System.out.println(" pq: " + pq + " pr: "+ pr + " ps : "+ps);
		
		if(pq == pr && pq == ps)return true;
		else return false;
		
	}
}
