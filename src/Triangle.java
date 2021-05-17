/**
 * this class implement triangle object
 * triangle described by 3 point
 */
public class Triangle {

    //declarations
    static final double EPSILON = 0.001;
    private Point _point1;
    private Point _point2;
    private Point _point3;

    /**
     * default constructor , initiates default points
     */
    public Triangle()
    {
        _point1 = new Point(1,0);
        _point2 = new Point(-1,0);
        _point3 = new Point(0,1);
    }

    /**
     * constructor initiates points with given points, if points not valid initiate  default values
     * @param point1 - point object
     * @param point2 - point object
     * @param point3 - point object
     */
    public Triangle(Point point1, Point point2, Point point3)
    {
        this();
        if (isValid(point1,point2,point3)) {
            _point1 = new Point(point1);
            _point2 = new Point(point2);
            _point3 = new Point(point3);
        }
    }

    /**
     * constructor initiates points with given x,y`s, if points not valid initiate  default values
     * @param x1 - x of first point
     * @param y1 - y of first point
     * @param x2 - x of second point
     * @param y2 - y of second point
     * @param x3 - x of third point
     * @param y3 - y of third point
     */
    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3 )
    {
        this();
        Point p1 = new Point(x1,y1);
        Point p2 = new Point(x2,y2);
        Point p3 = new Point(x3,y3);
        if (isValid(p1,p2,p3)) {
            _point1 = new Point(p1);
            _point2 = new Point(p2);
            _point3 = new Point(p3);
        }
    }

    /**
     * copy constructor
     * @param other - get a rectangle and copy it
     */
    public Triangle(Triangle other){
        _point1 = new Point(other.getPoint1());
        _point2 = new Point(other.getPoint2());
        _point3 = new Point(other.getPoint3());
    }

    //methods

    /**
     * method makes sure it is possible to build a rectangle with given points
     * rectangle is valid only if every two sides sum is lower than the third side size
     * @param point1 - point1
     * @param point2 - point2
     * @param point3 - point3
     * @return boolean - true if valid, false if not valid.
     */
    public boolean isValid(Point point1, Point point2, Point point3){
        boolean valid = true;
        if (!realDiff(point1.distance(point3), point1.distance(point2) + point2.distance(point3)))
            valid = false;
        if (!realDiff(point1.distance(point2), point2.distance(point3) + point1.distance(point3)))
            valid = false;
        if (!realDiff(point2.distance(point3), point1.distance(point2) + point1.distance(point3)))
            valid = false;

        return valid;
    }//end of isValid

    /**
     * getter for first point
     * @return Point - first point
     */
    public Point getPoint1(){
        return new Point (_point1);
    }//end of getPoint1

    /**
     * getter for second point
     * @return Point - second point
     */
    public Point getPoint2(){
        return new Point (_point2);
    }//end of getPoint2

    /**
     * getter for third point
     * @return Point - third point
     */
    public Point getPoint3(){
        return new Point (_point3);
    }//end of getPoint3

    /**
     * setter for point1
     * @param p - point object
     */
    public void setPoint1(Point p) {
        if (isValid(p, _point2, _point3))
            _point1 = new Point(p);
    }//end of setPoint1

    /**
     * setter for point2
     * @param p - point object
     */
    public void setPoint2(Point p) {
        if (isValid(_point1, p , _point3))
            _point2 = new Point(p);
    }//end of setPoint2

    /**
     * setter for point3
     * @param p - point object
     */
    public void setPoint3(Point p) {
        if (isValid(_point1, _point2, p))
            _point3 = new Point(p);
    }//end of setPoint3

    /**
     * return a string of the rectangle as {p1,p2,p3}
     * @return String - representation of the rectangle
     */
    public String toString(){
        return "{" + _point1.toString() + "," + _point2.toString() + "," + _point3.toString() +"}";
    }//end of toString

    /**
     * calculate a get the Perimeter of the triangle
     * @return double - perimeter of the triangle
     */
    public double getPerimeter(){
        return _point1.distance(_point2) + _point1.distance(_point3) + _point2.distance(_point3);
    }//end of getPerimeter

    /**
     * calculates the triangle area
     * @return double - triangle area
     */
    public double getArea(){
        double a = _point1.distance(_point2); //side a
        double b = _point1.distance(_point3); //side b
        double c = _point2.distance(_point3); //side c
        double s = (a+b+c)/2;
        //this is a formula for triangle area i found
        return Math.sqrt(s*(s-a)*(s-b)*(s-c));
    }//end of getArea

    /**
     * checks if the triangle is Isosceles //Shave Shokaim
     * @return boolean - true if yes
     */
    public boolean isIsosceles(){
        boolean flag = false;
        double a = _point1.distance(_point2);
        double b = _point1.distance(_point3);
        double c = _point2.distance(_point3);
        //check if there is 2 sides that are the same
        if (!realDiff(a,b) || !realDiff(b,c) || !realDiff(a,c))
            flag = true;
        return flag;
    }//end of isIsosceles

    /**
     * checks if the triangle is Pythagorean // a^2+b^2=c^2
     * @return boolean - true if yes
     */
    boolean isPythagorean(){
        boolean flag = false;
        double a = smallest(_point1.distance(_point2),_point1.distance(_point3),_point2.distance(_point3));//side a
        double c = biggest(_point1.distance(_point2),_point1.distance(_point3),_point2.distance(_point3));//side c - the biggest
        double b = this.getPerimeter() - a - c; //side b

        //this is an equation i found in wikipedia that proves a triangle is pythagorean
        //the equation states that in pythagorean triangle: (a+b)^2/2 == (c*c/2+a*b)
        //where c is the biggest side
        if (!realDiff(((a+b) * (a+b) / 2), (c*c/2 + a*b)))
            flag = true;
        return flag;
    }

    /**
     * function finds out if triangle is contained in a circle
     * checking if every point within radius
     * @param x - x position of the center of circle
     * @param y - y position of the center of circle
     * @param r - circle radius
     * @return - boolean - true if contained
     */
    public boolean isContainedInCircle(double x, double y, double r){
        boolean contained = false;
        Point center = new Point(x,y);
        //note, here i did not use realDiff function because in the function it is abs(a-b)
        //but here it matter if it is a-b or b-a
        if (r - _point1.distance(center) + EPSILON > 0)
            if (r - _point2.distance(center) + EPSILON > 0)
                if (r - _point3.distance(center) + EPSILON > 0)
                    contained = true;
        return contained;
    }// end of isContainedInCircle

    /**
     * method finds the lowest point. if two points same height it returns the left of them
     * @return Point - lowest point
     */
    public Point lowestPoint()
    {
        //if some point beneath both
        if (_point1.isUnder(_point2) && _point1.isUnder(_point3))
            return _point1;
        if (_point2.isUnder(_point1) && _point2.isUnder(_point3))
            return _point2;
        if (_point3.isUnder(_point1) && _point3.isUnder(_point2))
            return _point3;

        // since 3 points cannot be on the same levels checking which is above
        //then comparing the two lower points and choosing the left
        if (_point1.isAbove(_point2) && _point1.isAbove(_point3))
        {
            if (_point2.isLeft(_point3))
                return _point2;
            else
                return _point3;
        }
        if (_point2.isAbove(_point1) && _point2.isAbove(_point3))
        {
            if (_point1.isLeft(_point3))
                return _point1;
            else
                return _point3;
        }
        if (_point3.isAbove(_point1) && _point3.isAbove(_point2))
        {
            if (_point1.isLeft(_point2))
                return _point1;
            else
                return _point2;
        }
        //function will never get here, only for syntax purposes
        return _point1;
    }//end of lowestPoint

    /**
     * method finds the highest point. if two points same height it returns the left of them
     * @return Point - highest  point
     */
    public Point highestPoint()
    {
        //same logic as in lowest point
        if (_point1.isAbove(_point2) && _point1.isAbove(_point3))
            return _point1;
        if (_point2.isAbove(_point1) && _point2.isAbove(_point3))
            return _point2;
        if (_point3.isAbove(_point1) && _point3.isAbove(_point2))
            return _point3;

        if (_point1.isUnder(_point2) && _point1.isUnder(_point3))
        {
            if (_point2.isLeft(_point3))
                return _point2;
            else
                return _point3;
        }
        if (_point2.isUnder(_point1) && _point2.isUnder(_point3))
        {
            if (_point1.isLeft(_point3))
                return _point1;
            else
                return _point3;
        }
        if (_point3.isUnder(_point1) && _point3.isUnder(_point2))
        {
            if (_point1.isLeft(_point2))
                return _point1;
            else
                return _point2;
        }
        return _point1;
    }//end of highestPoint

    /**
     * checks if the triangle is located in the same quadrant
     * @return boolean - true if yes, false if not
     */
    public boolean isLocated(){
        boolean located = false;
        if ( _point1.quadrant() == _point2.quadrant() && _point2.quadrant() == _point3.quadrant())
            located = true;
        return located;
    }//end of isLocated

    /**
     * check if this triangle is above the other triangle
     * @param other - triangle object
     * @return boolean - true if above, false if not
     */
    public boolean isAbove(Triangle other){
        boolean above = false;
        if (this.lowestPoint().isAbove(other.highestPoint()))
            above = true;
        return above;
    } // end of isAbove

    /**
     * check if this triangle is under the other triangle
     * @param other - triangle object
     * @return boolean - true if above, false if not
     */
    public boolean isUnder(Triangle other){
        return other.isAbove(this);
    }// end of isUnder

    /**
     * checks if the triangle is the same points and same order
     * @param other - triangle object
     * @return boolean - true if yes, false if not
     */
    public boolean equals(Triangle other){
        boolean same = false;
        if (_point1.equals(other.getPoint1()))
            if (_point2.equals(other.getPoint2()))
                if (_point3.equals(other.getPoint3()))
                    same = true;
        return same;
    }

    /**
     * checks if both triangles are Congruent //Hofefim
     * @param other - triangle object
     * @return boolean - true if yes, false if not
     */
    public boolean isCongruent(Triangle other){
        boolean cong = false;
        // if same point so of course Congruent
        if (this.equals(other))
            cong = true;
        else
            //if the perimeter is not the same they are not Congruent
            if (!realDiff(this.getPerimeter(),other.getPerimeter()))
            {
                //calculate all sides for 2 triangle
                double a1 = _point1.distance(_point2);
                double b1 = _point1.distance(_point3);
                double c1 = _point2.distance(_point3);
                double a2 = _point1.distance(_point2);
                double b2 = _point1.distance(_point3);
                double c2 = _point2.distance(_point3);
                //since perimiter is the same between triangles if they are isosceles they must be diffarent
                //isosceles so no need to check b side.
                if (!realDiff(biggest(a1,b1,c1),biggest(a2,b2,c2)) && !realDiff(smallest(a1,b1,c1),smallest(a2,b2,c2)))
                    cong = true;
            }
        return cong;
    }//end of isCongruent

    /**
     * private function to measure real difference (including epsilon)
     * @param a - first number
     * @param b - second number
     * @return boolean - true if the absolete difference between a and b is less than epsilon
     */
    private boolean realDiff (double a, double b){
        return Math.abs(a-b) > EPSILON;
    }//end of realDiff for 2 numbers

    /**
     * private function to measure real difference (including epsilon)
     * @param p1 - first point
     * @param p2 - second point
     * @return boolean - true if the absolete difference between p1 and p2 is less than epsilon
     */
    private boolean realDiff (Point p1, Point p2){
        return p1.distance(p2) > EPSILON;
    }//end of realDiff for 2 points

    /**
     * private function finds biggest number between 3 numbers
     * @param a - first number
     * @param b - second number
     * @param c - third number
     * @return - double - biggest number
     */
    private double biggest (double a, double b, double c){
        return Math.max(a,Math.max(b,c));
    }//end of biggest

    /**
     * private function finds lowest number between 3 numbers
     * @param a - first number
     * @param b - second number
     * @param c - third number
     * @return - double - lowest number
     */
    private double smallest (double a, double b, double c){
        return Math.min(a,Math.min(b,c));
    }////end of smallest
}//end of Triangle class
