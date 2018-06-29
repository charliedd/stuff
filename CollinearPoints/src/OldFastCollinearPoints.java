import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class OldFastCollinearPoints {
	private LineSegment[] lines;
	
	public OldFastCollinearPoints(Point[] points){
		List<Line> tempLines = new ArrayList<Line>();
		List<LineSegment> uniqueLines = new ArrayList<LineSegment>();

		   
		int size = points.length;
		
		Point[] tempPoints = points.clone();
		
		for(int i = 0; i < size; i++){
			   Arrays.sort(tempPoints, points[i].slopeOrder());
			   double pq = points[i].slopeTo(tempPoints[1]);
			   double pr = points[i].slopeTo(tempPoints[2]);
			   double ps = points[i].slopeTo(tempPoints[3]);
			   
			   
				  if(pq == pr && pq == ps){
					  Point[] temp = {points[i], tempPoints[0], tempPoints[1], tempPoints[2]};
					  Arrays.sort(temp);
					  
					  if(tempLines.isEmpty()){
						  tempLines.add(new Line(temp[0],temp[3]));
					  }else{
						  boolean exists = false;
						  for(Line line : tempLines){
							  if(temp[0] == line.start && temp[3] == line.end){
								  exists = true;
								  break;
							  }
						  }
						  
						  if (!exists){
							  tempLines.add(new Line(temp[0],temp[3]));
						  }
					  }
					
				  }
		}
		
		 for(Line line : tempLines){
			 uniqueLines.add(new LineSegment(line.start,line.end));
		 }
		 
		 this.lines = uniqueLines.toArray(new LineSegment[uniqueLines.size()]);
			 
			 
			
		
		
	}
	
	private class Line{
		   Point start,end;
		   Line(Point s,Point e){
			   start = s;
			   end = e;
		   }
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