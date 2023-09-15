
/**
 * Class Segment1 represents a line (parallel to the x-axis) in the first quadrant, using
 * a right point and a left point.
 * @author Shir Rashkovits
 * @version 209144013
 */
public class Segment1
{
    private Point _poLeft;
    private Point _poRight;
    
   /**
   * construct a Segment1
   * if the Y coordinate of the right Point is not equal to the Y coordinate of the left Point
   * then it should be initialized to the value of the Y coordinate of the left Point
   * @param left - the left Point
   * @param right - the right Point
   */
   public Segment1 (Point left, Point right)
    {
        _poLeft = new Point(left);
        _poRight = new Point(right);
        if (left.getY() != right.getY())
            _poRight.setY(_poLeft.getY());
         
    }
    
   /**
   * construct a Segment1
   * if the Y coordinate of the right Point is not equal to the Y coordinate of the left Point
   * then it should be initialized to the value of the Y coordinate of the left Point
   * @param leftX - the x coordinate of the left Point
   * @param leftY - the y coordinate of the left Point
   * @param rightX - the x coordinate of the right Point
   * @param rightY - the Y coordinate of the right Point
   */
    public Segment1(double leftX ,double leftY, double rightX ,double rightY)
    {
        _poLeft = new Point(leftX, leftY);
        _poRight = new Point(rightX, rightY);
        if (leftY != rightY)
            _poRight.setY(leftY);
    }
    
    /** 
    * copy constructor for Segment1
    * construct a segment with the same left point and right point as other segment
    * @param other - the segment object from which to construt the new segment
    */
    public Segment1 (Segment1 other)
    {
        _poLeft = new Point(other._poLeft);
        _poRight = new Point(other._poRight);
    }
    
    /**
    * Returns the left point of the segment
    * @return the left point of the segment
    */
    public Point getPoLeft()
    {
        return new Point(_poLeft);
    }
    
    /**
    * Returns the right point of the segment
    * @return the right point of the segment
    */
    public Point getPoRight()
    {
        return new Point(_poRight);
    }
    
    /**
    * Returns the length of the segment that the two points create
    * @return the length of the segment that the two points create
    */
    public double getLength()
    { 
        return  _poRight.getX() - _poLeft.getX();
    }
    
    /**
    * Return a string representation of this segment in the format (3.0,4.0)---(3.0,6.0).
    * this method overrides the toString methos from class.java.lang.object
    * @return - String representation of this segment
    */
    public String toString()
    {
        return _poLeft + "---" + _poRight ;
    }
    
    /**
    * Checks if this segment is equals to other segment
    * @param other - the segment to be compared with this segment
    * @return true if this segment is equals to other segment
    */
    public boolean equals(Segment1 other)
    {
        return _poRight.equals(other._poRight) && _poLeft.equals(other._poLeft) ;
    }
    
    /**
    * Checks if this segment is above other segment
    * @param other - the segment to be compared with this segment
    * @return true if this segment is above other segment
    */
    public boolean isAbove(Segment1 other)
    {
        return _poRight.isAbove(other._poRight) ;
    }
    
    /**
    * Checks if this segment is under other segment
    * @param other - the segment to be compared with this segment
    * @return true if this segment is under other segment
    */
    public boolean isUnder(Segment1 other)
    {
        return other.isAbove(this);
    }
    
    /**
    * Checks if this segment is to the left of other segment
    * @param other - the segment to be compared with this segment
    * @return true if this segment is to the left of other segment
    */
    public boolean isLeft(Segment1 other)
    {
           return _poRight.isLeft(other._poLeft) ;
    }
    
    /**
    * Checks if this segment is to the right of other segment
    * @param other - the segment to be compared with this segment
    * @return true if this segment is to the right of other segment
    */
    public boolean isRight(Segment1 other)
    {
        return other.isLeft(this);
    }
    
    /**
    * Move the segment horizontally by delta.
    * @param delta - the displacement size
    */
    public void moveHorizontal(double delta)
    {
        if (_poLeft.getX() + delta >= Point.MIN_VAL) 
        {
            _poRight.move(delta,0);
            _poLeft.move(delta, 0);
        }
    }
    
    /**
    * Move the segment vertically by delta.
    * @param delta - the displacement size
    */
    public void moveVertical(double delta)
    {
        if (_poRight.getY() + delta >= Point.MIN_VAL)
        {
            _poRight.move(0,delta);
            _poLeft.move(0,delta);
        }
    }
    
    /**
    * Change the segment size by moving the right point by delta. 
    * will be implemented only for a valid delta: only if the new point remains the right point
    * @param delta - the length change
    */
    public void changeSize(double delta)
    {
        if (_poRight.getX() + delta >= Point.MIN_VAL)
            _poRight.setX(_poRight.getX()+delta);
    }
    
    /**
     * Check if a point is located on the segment.
     * @param p - a point to be checked if it is on the segment
     * @return true if p is on this segment
     */
    public boolean pointOnSegment(Point p)
    {
        return (Math.abs(p.getY() - _poRight.getY()) < Point.TOLERANCE && p.getX() <= _poRight.getX() && p.getX() >= _poLeft.getX());
    }
    
    /**
     * check if this segment is bigger than other segment
     * @param other - the segment to be compared with
     * @return true if this segment is bigger than the other segment
     */
    public boolean isBigger (Segment1 other)
    {
        return getLength() > other.getLength();
    }
    
  
    /**
     * Returns the overlap size of this segment and another segment.
     * @param other - the other segment 
     * @returns the overlap size
     */
    public double overlap(Segment1 other)
    {
        // creating a point object with the y value of the other segment
        Segment1 thisWithOtherY = new Segment1(_poLeft.getX(), other._poLeft.getY(), _poRight.getX(), other._poLeft.getY());
        if (thisWithOtherY.pointOnSegment(other._poRight))
        {  
            //if the right point of the other segment is on this segment
                if (thisWithOtherY.pointOnSegment(other._poLeft))
                {
                    //if the left and the right points of the other segment is on this segment the overlap is in the size of other
                        return other.getLength();
                }
                else
                {
                    //only the right point of the other segment is on this segment
                    return other._poRight.getX() - _poLeft.getX();
                }      
        }
        else
        {
        // the right point of other segment is not on this segment
            if (thisWithOtherY.pointOnSegment(other._poLeft))
            {
            //only the left point of the other segment is on the segment
                return _poRight.getX() - other._poLeft.getX();
            }
            else
            {
                if (other.isLeft(this) || other.isRight(this))
                {
                        //there is'nt any overlap
                        return 0;
                }
                else
                //the right point of other is to the right of this and the left point of other is to the left, so the overlap is
                //in the size of this segment
                    return getLength();
            }
        }
    }
                            
    /**
    * Compute the trapeze perimeter, which is constructed by this segment and a reference segment.
    * @param other - the reference segment
    * @returns the trapeze perimeter
    */
    public double trapezePerimeter(Segment1 other)
    {
        return _poRight.distance(other._poRight) + _poLeft.distance(other._poLeft) + getLength() + other.getLength();
    } 
}
    
    
        
