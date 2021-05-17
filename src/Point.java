/**
 * this class implements point
 * point described by two doubles
 */
public class Point
{
    //declarations
    private double _x;
    private double _y;

    /**
     * constructor initiates the x , y points. any double points
     * @param x - x coordinate
     * @param y - y coordinate
     */
    public Point(double x, double y)
    {
        _x = x;
        _y = y;
    }

    /**
     * constructor initiates the x , y points. with other point
     * @param other - Point object
     */
    public Point (Point other)
    {
        _x = other.getX();
        _y = other.getY();
    }

    //methods
    /**
     * getter for x
     * @return x axis
     */
    public double getX ()
    {
        return _x;
    }//end of getX

    /**
     * getter for y
     * @return y axis
     */
    public double getY ()
    {
        return _y;
    }//end of getY

    /**
     * setter for x axis
     * @param num - double number
     */
    public void setX (double num)
    {
        _x = num;
    }//end of setX

    /**
     * setter for y axis
     * @param num - double number
     */
    public void setY (double num)
    {
        _y = num;
    }//end of setY

    /**
     * returns the point in (x,y) format
     * @return String - (x,y)
     */
    public String toString()
    {
        return "(" + _x + "," + _y +")";
    }//end of toString

    /**
     * compares 2 points
     * @param other - Point object
     * @return - True if same or False if not
     */
    public boolean equals (Point other )
    {
        return _x == other.getX() && _y == other.getY();
    }//end of equals

    /**
     * checks if my point is above the other point
     * @param other - Point object
     * @return - True if above or False if not
     */
    public boolean isAbove(Point other)
    {
        return _y > other.getY();
    }//end of isAbove

    /**
     * checks if my point is not above the other point
     * @param other - Point object
     * @return - True if not above or False if yes
     */
    public boolean isUnder(Point other)
    {
        return other.isAbove(this);
    }//end of isUnder

    /**
     * checks if my point is left the other point
     * @param other - Point object
     * @return - True if left or False if not
     */
    public boolean isLeft(Point other)
    {
        return _x < other.getX();
    }//end of isLeft

    /**
     * checks if my point is not left the other point
     * @param other - Point object
     * @return - True if not left or False if yes
     */
    public boolean isRight(Point other)
    {
        return other.isLeft(this);
    }//end of isLeft

    /**
     * checks the distance between 2 points
     * @param p - Point object
     * @return - distance between 2 points
     */
    public double distance (Point p)
    {
        return Math.sqrt(Math.abs(Math.pow(_x - p.getX(),2) + Math.pow(_y - p.getY(),2)));
    }//end of distance

    /**
     * checks the quadrant of the point
     * @return - the number of the quadrant
     */
    public int quadrant (){
        int quad = 0;
        if (_x != 0 || _y != 0)
        {
            if (_x > 0) {
                if (_y > 0)
                    quad = 1;
                else
                    quad = 4;
            }
            else {
                if (_y > 0)
                    quad = 2;
                else
                    quad = 3;
            }
        }
        return quad;
    }//end of quadrant

} //end of class Point
