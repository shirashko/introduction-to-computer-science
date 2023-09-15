/**
 * class Polygon Represents a polygon in the first quadrant 
 * @author (Shir Rashkovits)
 * @version 209144013
 */
public class Polygon 
{
    private Point[] _vertices; //this array contains the vertices of the polygon
    private int _noOfVertices; //number of vertices in the polygon
    private static final int MAX_VERTICES = 10; 
    
    /**
    * construct an array of Point objects in the size of ten
    * the points that will be added to the array will represent a Conveged polygon
    */
    public Polygon()
    {
        _vertices = new Point[MAX_VERTICES];
        _noOfVertices = 0;
    }
    
    /**
     * Adding a vertex to the Polygon in the next available cell of the array.
     * if the array is already full, the new point won't be addaed to the array as a new vertex.
     * @param x - the x coordinate for the new vertex
     * @param y - the y coordinate for the new vertex
     * @return true if the recieved point has been added to the array
     */
    public boolean addVertex(double x, double y)
    {
        if (_noOfVertices >= MAX_VERTICES)
            return false;
        _vertices[_noOfVertices] = new Point(x,y); 
        _noOfVertices++;
        return true;
    }
    
    /**
     * return the Point of the highest vertex in the polygon. 
     * if there is more than one vertex with the highest value,
     * return the first vertex with that high
     * if the polygon doesn't have any vertices yet, returns null
     * @return - Point
     */
    public Point highestVertex()
    {
     if (_noOfVertices == 0)
            return null; 
     Point max = _vertices[0]; //initializing
     for (int i = 1 ; i < _noOfVertices ; i++)
     {
         if (max.isUnder(_vertices[i])) 
            max = _vertices[i]; 
     }
     return new Point(max); 
    }
    
    /**
    * Returns a String representation of the Polygon
    * @return - string representation of this Polygon
    */
    public String toString()
    {
        if (_noOfVertices == 0)
            return "The polygon has 0 vertices.";
        String str = "";
        for (int i=0 ; i < _noOfVertices-1 ; i++) //scan without the last vertex to avoid "," after the last vertex
            str += _vertices[i] + ",";
        return "The polygon has " + _noOfVertices + " vertices:" + "\n" + "(" + str + _vertices[_noOfVertices-1] + ")";   
    }
    
    /**
    * calculate the perimeter of the polygon
    * if the number of vertices is zero or one - reutrn zero
    * @return ther perimeter of the polygon
    */
    public double calcPerimeter()
    {
        if (_noOfVertices <= 1)
            return 0;
        if (_noOfVertices == 2)
            return _vertices[0].distance(_vertices[1]); 
        
        double perimeter = 0;  
        for (int i=0 ; i < _noOfVertices - 1 ; i++)
        {
            perimeter += _vertices[i].distance(_vertices[i+1]);
        }
        perimeter += _vertices[_noOfVertices-1].distance(_vertices[0]);
        return perimeter; 
    }
    
    //getting three vertices and calculating the area of the triangle they create
    private static double triangleArea(Point x, Point y, Point z)
    {
        double a = x.distance(y);
        double b = y.distance(z);
        double c = z.distance(x);
        double s = (a+b+c)/2.0; //half of the perimeter
        return Math.sqrt(s*(s-a)*(s-b)*(s-c));
    }
    
    /**
    * return the area of the polygon
    * if the number of vertices is ledd than three than return zero
    * @return - the area of the polygon
    */
    public double calcArea()
    {
        if (_noOfVertices < 3)
            return 0;
        double polygonArea = 0;  
        for (int i=0 ; i < _noOfVertices - 2 ; i++) //to avoid out of bound exception:  i < _noOfVertices - 2
            polygonArea += triangleArea(_vertices[0], _vertices[i+1], _vertices[i+2]); //allways using same base vertex
        return polygonArea;
    }
    
    /**
    * return true if this polygon area is bigger than other polygon area
    * @return true if this polygon area is bigger than other polygon area
    * @param - other is the polygon to be compared with
    */
    public boolean isBigger(Polygon other)
    {
        return calcArea() > other.calcArea();
    }
    
    /**
    * retruns the index in the array of the recieved point 
    * if the point is not in the array- return -1.
    * @return - the index in the array of the recieved point 
    * @param p - the recieved point to find the index for
    */
    public int findVertex(Point p)
    {
        for (int i=0; i < _noOfVertices ; i++)
        {
            if (_vertices[i].equals(p))
                return i;
        }
        return -1; //p not found
    }
    
    /**
    * return the next vertex of the polygon
    * if the recieved point is not a vertex of the polygon, than return null
    * if the recieved vertex is the only vertex in the polygon than return the vertex itself
    * if the recieved vertex is the lest one in the array than return the first vertex in the array
    * @return - the next vertex of the polygon
    * @param p - the recieved point to look for the next point in the polygon
    */
    public Point getNextVertex(Point p)
    {
        for (int i=0; i < _noOfVertices ; i++)
        {
            if (_vertices[i].equals(p)) 
            {
                if (_noOfVertices == 1) //if p is the only vertex in the polygon than return the vertex itself
                    return new Point(_vertices[0]);
                else 
                    if(i == _noOfVertices-1) //if p is the last vertex in the array than return this vertex
                        return new Point(_vertices[0]); //copy constructor to avoidi aliasing
                    else // the normal situation- returning the next point in the array
                        return new Point(_vertices[i+1]); 
            }
        }
        return null; //p is not a vertex of the polygon at all
    }
    
    /**
    * return the rectangle that bounds the polygon
    * if the polygon has less than three vertices than return null
    * @return - the rectangle that bounds the polygon
    */
    public Polygon getBoundingBox()
    {
        if (_noOfVertices < 3) 
            return null;
        //find the bounds of the polygon from all sides(up, down, left, right)
        double minY = _vertices[0].getY();
        double maxY = _vertices[0].getY(); 
        double leftX = _vertices[0].getX();
        double rightX = _vertices[0].getX();
        for (int i = 1; i < _noOfVertices; i++)
        {
            if(_vertices[i].getY() < minY)
                minY = _vertices[i].getY();
            if(_vertices[i].getY() > maxY)
                maxY = _vertices[i].getY();
            if(_vertices[i].getX() < leftX)
                leftX = _vertices[i].getX();
            if(_vertices[i].getX() > rightX)
                rightX = _vertices[i].getX();
        }
        
        //use the bounds found to create the bounging rectangle
        Polygon rectangle = new Polygon();
        rectangle.addVertex(leftX, minY);
        rectangle.addVertex(rightX, minY);
        rectangle.addVertex(rightX, maxY);
        rectangle.addVertex(leftX, maxY);
        return rectangle; 
    }
}
