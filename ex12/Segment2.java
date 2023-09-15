
/**
 * Class Segment2 represents a line (parallel to the x-axis) in the first quadrant, 
 * using a center point and length.
 * @author Shir Rashkovits
 * @version 209144013
 */
public class Segment2
{
    Point _poCenter;
    double _length;
    
    //constructors
    
    /**
     * Constructs a new segment using 4 specified x and y coordinates: two coordinates for the
     * left point and two coordinates for the right point. If the y coordinates are different,
     * change the y of the right point to be equal to the y of the left point.
     * @param leftX - the x value of left point
     * @param lefyY - the y value of left point
     * @param rightX the x value of right point
     * @param rightY - the y value of right point
     */
    public Segment2(double leftX, double leftY, double rightX, double rightY)
    {
        if (Math.abs(leftY - rightY) > Point.TOLERANCE) //making sure that the two points have the same Y coordinate 
            rightY = leftY;
        _poCenter = new Point((rightX + leftX)/2.0, rightY); //using avarage formula
        _length = rightX - leftX;
    }
    
    /**
     * Constructs a new segment using a center point and the segment length.
     * @param poCneter - the center point
     * @param length - the segment length
     */
    public Segment2(Point poCenter, double length)
    {   
        _poCenter = new Point(poCenter); 
        _length = length; 
    }
    
    /**
     * Constructs a new segment using two Points. If the y coordinates are different,
     * change the y of the right point to be equal to the y of the left point.
     * @param left - the left of the segment
     * @param right - the right of the segment
     */
    public Segment2(Point left, Point right)
    {
         if (Math.abs(left.getY() - right.getY()) > Point.TOLERANCE)
            right.setY(left.getY());
        _poCenter = new Point((left.getX()+right.getX())/2.0, left.getY()); //using avarage formula
        _length = left.distance(right);
    }
    
   /**
    * Copy Constructor. Construct a segment using a reference segment.
    * @param other - the reference segment
    */
    public Segment2(Segment2 other)
    {
        _poCenter = new Point(other._poCenter);
        _length = other._length;
    }
    
    //other methods
       
    /**
    * Returns the left point of the segment.
    * @return the left Point of the segment
    */
    public Point getPoLeft()
    {
        return new Point(_poCenter.getX() - _length / 2.0 , _poCenter.getY()); //using avarage formula
    }
    
    /**
    * returns the right point of the segment.
    * @return the right Point of the segment
    */
    public Point getPoRight()
    {
       return new Point(_poCenter.getX() + _length / 2.0, _poCenter.getY()); //using avarage formula
    }

    /**
    * Returns the segment length
    * @return the segment length
    */
    public double getLength()
    {   
        return _length;
    }
    
    /**
     * Change the segment size by moving the right point by delta. Will be implemented only
     * for a valid delta: only if the new right point remains the right point.
     * @param delta -  the length change
     */
    public void changeSize(double delta)
    {      
        if (getLength() + delta >= Point.MIN_VAL) //making sure the right point will stay the right point
        {
            _length =+ delta;
            _poCenter.setX(getPoRight().getX()+delta + getPoLeft().getX() / 2.0); //using avarage formula
        }
    }
    
    /**
    * Check if the reference segment is equal to this segment
    * @param other - the reference segment
    * @return true if the reference segment is equal to this segment
    */
    public boolean equals(Segment2 other)
    {       
        return _poCenter.equals(other._poCenter) && Math.abs(_length - other._length) < Point.TOLERANCE ;  
    }
    
     
    /**
     * Check if this segment is above a reference segment.
     * @param other - the reference segment
     * @return true if this segment is above the reference segment
     */
    public boolean isAbove(Segment2 other)
    {
        return _poCenter.isAbove(other._poCenter);
    }
    
    /**
     * Check if this segment is under a reference segment.
     * @param other - the reference segment
     * @return true if this segment is under the reference segment
     */
    public boolean isUnder(Segment2 other)
    {
        return other.isAbove(this);
    }
    
    /**
     * Check if this segment is bigger than a reference segment.
     * @param other - the reference segment
     * @reurn true if this segment is bigger than the reference segment
     */
    public boolean isBigger(Segment2 other)
    {
        return _length - other._length > Point.TOLERANCE;
    }
    
    /**
     * Check if this segment is (really) left of a received segment. 
     * @param other - the  received segment
     * @return true if this segment is left to the recived segment
     */
    public boolean isLeft(Segment2 other)
    {
        return other.isRight(this);
    }
   
    /**
     * Check if this segment is (really) right of a received segment. 
     * @param other - the  received segment
     * @return true if this segment is right to the recived segment
     */
    public boolean isRight(Segment2 other)
    {   
        return getPoLeft().isRight(other.getPoRight());
    }
    
    /**
     * Move the segment horizontally by delta. Will be implemented only for a valid delta
     * @param delta - the displacement size
     */
    public void moveHorizontal(double delta)
    {
        if (getPoLeft().getX() + delta >= Point.MIN_VAL) 
             _poCenter.setX(_poCenter.getX()+delta);
            
    }
    
    /**
     * Move the segment Vertically by delta. Will be implemented only for a valid delta
     * @param delta - the displacement size
     */
    public void moveVertical(double delta)
    {
        if (_poCenter.getY() + delta >= Point.MIN_VAL) 
            _poCenter.setY(_poCenter.getY()+delta);
    }
    
    /**
     * Returns the overlap size of this segment and a reference segment.
     * @param other - the reference segment
     * @return thr overlap size
     */
    public double overlap(Segment2 other)
    {
        // creating a point object with the y value of the other segment
        Segment2 thisWithOtherY = new Segment2(getPoLeft().getX(), other.getPoLeft().getY(), getPoRight().getX(), other.getPoLeft().getY());
        if (thisWithOtherY.pointOnSegment(other.getPoRight()))
        {  
            //if the right point of the other segment is on this segment
                if (thisWithOtherY.pointOnSegment(other.getPoLeft()))
                {
                    //if the left and the right points of the other segment is on this segment the overlap is in the size of other
                        return other.getLength();
                }
                else
                {
                    //only the right point of the other segment is on this segment
                    return other.getPoRight().getX() - getPoLeft().getX();
                }      
        }
        else
        {
        // the right point of other segment is not on this segment
            if (thisWithOtherY.pointOnSegment(other.getPoLeft()))
            {
            //only the left point of the other segment is on the segment
                return getPoRight().getX() - other.getPoLeft().getX();
            }
            else
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
    
    /**
     * Check if a point is located on the segment
     * @param p - a point to be checked
     * @return true if p is on this segment
     */
    public boolean pointOnSegment(Point p)
    {
        return (getPoRight().getX() >= p.getX() && getPoLeft().getX() <= p.getX());
    }
    
    /**
     * Return a string representation of this segment in the format (3.0,4.0)---(3.0,6.0).
     * overrides the toString metod from class java.util.object
     * @return String representation of this segment
     */
    public String toString()
    {
        return getPoLeft() + "---" + getPoRight();
    }
    
    /**
     * Compute the trapeze perimeter, which constructed by this segment and a reference segment.
     * @param other - the reference segment
     * @return the trapeze primeter
     */
    public double trapezePerimeter(Segment2 other)
    {
        return getPoRight().distance(other.getPoRight()) + getPoLeft().distance(other.getPoLeft()) + getLength() + other.getLength();
    }

}
