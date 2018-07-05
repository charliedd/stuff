import java.util.Arrays;

public class BruteCollinearPoints {
	private LineSegment[] lineSegments;
	
	public BruteCollinearPoints(Point[] points){
		
		if (points == null)throw new java.lang.IllegalArgumentException();
		
		int size = points.length;
		
		if(points[size-1] == null)throw new java.lang.IllegalArgumentException();
		
		for (int i = 0;i < size - 1; i++ ){
			if(points[i] == null)throw new java.lang.IllegalArgumentException();
			for (int j = i + 1; j < size; j++){
				if(points[i].compareTo(points[j]) == 0) throw new java.lang.IllegalArgumentException();
			}
		}
		
		Point[] myPoints = points.clone();
		Arrays.sort(myPoints);
		
		
		int sizeTemp = size * size;
		
		int[] startPointPos = new int[sizeTemp];
		int[] endPointPos = new int[sizeTemp];
		
		int posIndex = 0;

		for(int i = 0; i < size-3; i++)
			   for(int j = i + 1; j < size - 2; j++)
				   for(int k = j + 1; k < size - 1; k++)
					   for(int l = k + 1; l < size; l++){
						   Point p = myPoints[i];
						   Point q = myPoints[j];
						   Point r = myPoints[k];
						   Point s = myPoints[l];
						   
				
						   if (isCollinear(p,q,r,s)){
							   if (posIndex == 0){
								   startPointPos[posIndex] = i;
								   endPointPos[posIndex] = l;
								   posIndex++;
							   }else{
								   boolean exists = false;
								   for(int n = 0; n < posIndex; n++){
									   if(startPointPos[n] == i && endPointPos[n] == l ){
										   exists = true;
										   break;
									   }
								   }
								   
								   if(!exists){
									   startPointPos[posIndex] = i;
									   endPointPos[posIndex] = l;
									   posIndex++;
								   }
							   }
							 
						   }
						   
					   }
		lineSegments = new LineSegment[posIndex];
		
		for (int i = 0; i < posIndex; i++){
			
			int startPos = startPointPos[i];
			int endPos = endPointPos[i];
			lineSegments[i] = new LineSegment(myPoints[startPos],myPoints[endPos]);
			
			}
		
	   // finds all line segments containing 4 points
		}
	public           int numberOfSegments(){
		return lineSegments.length;
	   // the number of line segments
		}
	public LineSegment[] segments(){
		return this.lineSegments;
	   // the line segments
		}
	
	private boolean isCollinear(Point p,Point q,Point r, Point s){
		double pq =  p.slopeTo(q);
		double pr = p.slopeTo(r);
		double ps = p.slopeTo(s);
		
		
		if(pq == pr && pq == ps){
			return true;
		}
		else{
			return false;
		}
	}
}