import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OldBruteCollinearPoints {
   private LineSegment[] lines;
	
   public OldBruteCollinearPoints(Point[] points){
	   if(points == null)throw new java.lang.IllegalArgumentException();
	   
	   int size = points.length;
	   
	   
	   for(int i = 0; i < size - 1; i++){
		   if(points[i] == null)throw new java.lang.IllegalArgumentException();
		   for(int j = i + 1; j < size; j++){
			   if(points[i] == points[j])throw new java.lang.IllegalArgumentException();
		   }
		 
	   }
	   
	   
	   List<Line> tempLines = new ArrayList<Line>();
	   List<LineSegment> uniqueLines = new ArrayList<LineSegment>();
	   
	   for(int i = 0; i < size-3; i++)
		   for(int j = i + 1; j < size - 2; j++)
			   for(int k = j + 1; k < size - 1; k++)
				   for(int l = k + 1; l < size; l++){
					   
					  double pq =  points[l].slopeTo(points[i]);
					  double pr = points[l].slopeTo(points[k]);
					  double ps = points[l].slopeTo(points[j]);
					  
					  if(pq == pr && pq == ps){
						  Point[] temp = {points[i], points[j], points[k], points[l]};
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
	   // the number of line segments
	   return this.lines.length;
   }
   
   public LineSegment[] segments(){
	   return this.lines;
	   // the line segments
   }
}