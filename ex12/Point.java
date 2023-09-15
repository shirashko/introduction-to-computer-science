
/**
 * class Point Represents a point in the first quadrant 
 * @author (Shir Rashkovits)
 * @version 209144013
 */
public class Point
{
    public static final double TOLERANCE = 0.000000001; 
    public static final double MIN_VAL = 0;
    private static final int ROUND_FACTOR = 10000;
    private static final int RIGHT_ANGLE = 90;
    private static final int FLAT_ANGLE = 180;
    
    private double _radius; 
    private double _alpha; 

    // counstructor1
    /**
    * construct a Point
    * if one of the parameters is negative then it should be initialized to zero
    * @param x - the coordinate x
    * @param y - the coordinate y
    */
    public Point(double x, double y)
    {
        if (x < MIN_VAL)
            x = MIN_VAL;
        if (y < MIN_VAL)
            y = MIN_VAL;
        _radius = Math.sqrt( Math.pow(x,2) + Math.pow(y,2) ); //using Pitagoras to calculate radius
        if(x == MIN_VAL)
            _alpha = RIGHT_ANGLE;
        else if (y == MIN_VAL)
                _alpha = MIN_VAL;
             else
                _alpha = (FLAT_ANGLE * Math.atan(y/x)) / Math.PI; // according to the formula that converts radians to degrees
    }
    
    // constructor2
    /** 
    * copy constructor for Point
    * construct a point with the same radius and alpha as other point
    * @param other - the Point object from which to construt the new point
    */
    public Point(Point other)
    {
        if (other != null) 
        { 
            _radius = other._radius;
            _alpha = other._alpha;
        }
    }
    
    // converts alpha from degrees to radians, @return alpha in radians 
    private double getAlphaInRadians()
    {
        return (_alpha * Math.PI) / FLAT_ANGLE;
    }
    
    /**
    * Returns the x of the point
    * @return 
    */
    public double getX()
    {
        //by the formula: x = radius*cos(a)
        //converting alpha from degrees into radians to fit the formula
        return  Math.round(_radius * Math.cos(getAlphaInRadians())*ROUND_FACTOR)/(double)ROUND_FACTOR   ; 
    }
    
    /**
    * Returns the x of the point
    * @return 
    */
    public double getY()
    {
        //by the formula: y = sin(a)*radius
        //converting alpha from degrees into radians to fit the formula
        return Math.round(_radius * Math.sin(getAlphaInRadians())*ROUND_FACTOR)/(double)ROUND_FACTOR ;    
    }
    
    /**
     * Sets the x coordinate of the point
     * if a negative number is received then the x coordinate does not change
     * @param x - the new x coordinate
     */
    public void setX(double x)
    {
        if (x>=MIN_VAL)
        {
            double y = getY();
            _radius = Math.sqrt( Math.pow(x,2) + Math.pow(y,2) ); //using Pitagoras to calculate radius
            if(x == MIN_VAL)
                _alpha = RIGHT_ANGLE;
            else 
            {
                 if (y == MIN_VAL)
                    _alpha = MIN_VAL;
                 else
                    _alpha = (FLAT_ANGLE * Math.atan(y/x)) / Math.PI; // according to the formula that converts radians to degrees
            }
        }
    }
    
    /**
     * Sets the y coordinate of the point
     * if a negative number is received then the y coordinate does not change
     * @param y - the new y coordinate
     */
    public void setY(double y)
    {
        if (y >= MIN_VAL)
        {
            double x = getX();
            _radius = Math.sqrt( Math.pow(x,2) + Math.pow(y,2) ); //using Pitagoras to calculate radius
            if(y == MIN_VAL)
                _alpha = MIN_VAL;
            else 
            {
                if (x == MIN_VAL)
                    _alpha = RIGHT_ANGLE;
                 else
                    _alpha = (FLAT_ANGLE * Math.atan(y/x)) / Math.PI; // according to the formula that converts radians to degrees
            } 
        }    
    }
    
    /**
     * Returns a String representation of the point
     * @return - string representation of this point
     */
    public String toString()
    {
        return "(" + getX() + "," + getY() + ")" ; 
    }
    
    /**
     * check if this point equals other point
     * @param other - the point to be compared with this point
     * @return true if this point is equals other point
     */
    public boolean equals(Point other)
    {
        return Math.abs(_radius - other._radius) < TOLERANCE && Math.abs(_alpha - other._alpha) < TOLERANCE ;
    }
    
    /**
     * check if this point is above other point
     * @param other - the point to be compared with this point 
     * @return true if this point is above other point
     */
     public boolean isAbove(Point other)
     {
         return getY() > other.getY();
      }
      
    /**
     * check if this point is under other point
     * @param other - the point to be compared with this point
     * @return true if this point is under other pont
     */  
     public boolean isUnder(Point other)
     {
         return other.isAbove(this);
     }
      
    /**
     * check if this point is to the left of other point
     * @param other - the point to check if this point is left of
     * @return true if this point is to the left of other pont
     */   
     public boolean isLeft(Point other)
     {
         return getX() < other.getX();
      }
      
    /**
     * check if this point is to the right of other point
     * @param other - the point to check if this point is right of
     * @return true if this point is to the right of other pont
     */   
     public boolean isRight(Point other)
     {
         return other.isLeft(this);
      } 
    
    /** 
     * calculate the distance between this point and other point 
     * @param p - the point to calculate the distance from 
     * @return the distance between this and p point
     */
    public double distance(Point p)
    {
        //according distance formula 
        return Math.sqrt( Math.pow( getX() - p.getX() , 2) + Math.pow( getY() - p.getY() , 2 ) );
    }
    
    /**
     * moves the x coordinate by dX and the y coordinate by dY 
     * if movement moves the point outside first quadrant then the point will 
     * remain at the same place and not move
     * @param dX - the amount to move in the x direction
     * @param dY - the amount to move in the y directions
     */
   public void move(double dX, double dY)
   {
       double x = getX();
       double y = getY();
       if ( x + dX >= MIN_VAL || y + dY >= MIN_VAL ) 
            _radius = Math.sqrt( Math.pow(x+dX,2) + Math.pow(y+dY,2) ); //using distance formula
            _alpha = ( FLAT_ANGLE * Math.atan((y+dY)/(x+dX)) ) / Math.PI; // according to the formula that converts radians to degrees
   }
   
   
    
    
}    