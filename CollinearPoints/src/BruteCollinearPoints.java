import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {
   private LineSegment[] lines;
	
   public BruteCollinearPoints(Point[] points){
	   List<LineSegment> tempLines=new ArrayList<LineSegment>();  
	   int size = points.length;
	   System.out.println(size);
	   for(Point p : points){
		   System.out.println("P : " + p);
	   }
	   
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
						 // System.out.println("Punto inicio: " + temp[0] + "Punto final: " + temp[3]);
						  
						  LineSegment newLine =  new LineSegment(temp[0], temp[3]);
						  
						  if(tempLines.isEmpty())tempLines.add(newLine);
						  else{
							  boolean exists = false;
							  for(LineSegment line : tempLines){
								  if(line.equals(newLine)){
									  exists =true;
									  break;
								  }
							  }
							  if(!exists){
								  tempLines.add(newLine);
							  }
						  }
						  
					  }
				   }
					
	  this.lines = tempLines.toArray(new LineSegment[tempLines.size()]);
	   
	 //  for(int i = 0; i < this.lines.length ; i++){
		//   System.out.println("Bitch" + lines[i]);
	 //  }
	   
   }
   
   public           int numberOfSegments(){
	   // the number of line segments
	   return this.lines.length;
   }
   
   public LineSegment[] segments(){
	   return this.lines;
	   // the line segments
   }
}