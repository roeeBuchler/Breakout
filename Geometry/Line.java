//212067466 Roee Buchler
package Geometry;

/**
 * The Line class represents a line segment in 2D space defined by two points.
 */
public class Line {
    // Fields
    private Point start = new Point(0, 0);
    private Point end = new Point(0, 0);
    private static final double EPSILON = 0.0001;

    // Constructors

    /**
     * Constructs a line with the given start and end points.
     *
     * @param start the start point of the line.
     * @param end   the end point of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs a line with the given coordinates.
     *
     * @param x1 the x-coordinate of the start point.
     * @param y1 the y-coordinate of the start point.
     * @param x2 the x-coordinate of the end point.
     * @param y2 the y-coordinate of the end point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Returns the length of the line.
     *
     * @return the length of the line.
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * Returns the middle point of the line.
     *
     * @return the middle point of the line.
     */
    public Point middle() {
        return new Point((start.getX() + end.getX()) / 2, (start.getY() + end.getY()) / 2);
    }

    /**
     * Returns the start point of the line.
     *
     * @return the start point of the line.
     */
    public Point start() {
        return start;
    }

    /**
     * Returns the end point of the line.
     *
     * @return the end point of the line.
     */
    public Point end() {
        return end;
    }

    /**
     * Returns the slope of the line defined by two points.
     *
     * @param start the start point of the line.
     * @param end   the end point of the line.
     * @return the slope of the line.
     */
    public double slope(Point start, Point end) {
        if ((start.getX() - end.getX()) == 0) {
            return 0;
        }
        return (start.getY() - end.getY()) / (start.getX() - end.getX());
    }

    /**
     * Checks if the line is vertical.
     *
     * @return true if the line is vertical, false otherwise.
     */
    private boolean isVertical() {
        if (this.start.equals(this.end)) {
            return false;
        }
        return Math.abs(this.start.getX() - this.end.getX()) < EPSILON;
    }

    /**
     * Checks if the line is horizontal.
     *
     * @return true if the line is horizontal, false otherwise.
     */
    private boolean isHorizontal() {
        return Math.abs(this.start.getY() - this.end.getY()) < EPSILON;
    }

    /**
     * Checks if a point (x, y) is within the bounds of the line segment.
     *
     * @param x the x-coordinate of the point.
     * @param y the y-coordinate of the point.
     * @return true if the point is within the bounds, false otherwise.
     */
    private boolean isWithinBounds(double x, double y) {
        double tolerance = 0.000001;
        if ((((x + tolerance >= this.start.getX()) && (x - tolerance <= this.end.getX()))
                || ((x + tolerance >= this.end.getX()) && (x - tolerance <= this.start.getX())))
                && (((y + tolerance >= this.start.getY()) && (y - tolerance <= this.end.getY()))
                || ((y + tolerance >= this.end.getY()) && (y - tolerance <= this.start.getY())))) {
            return true;
        }
        Point p = new Point(x, y);
        return p.equals(this.start) || p.equals(this.end);
    }

    /**
     * Checks if a point is on the line.
     *
     * @param point the point to check.
     * @return true if the point is on the line, false otherwise.
     */
    public boolean isOnTheLine(Point point) {
        if (!isWithinBounds(point.getX(), point.getY())) {
            return false;
        }
        if (Double.isFinite(this.slope())) {
            return Math.abs(point.getY() - (this.slope() * point.getX() + this.freeParameter())) < EPSILON;
        }
        if (this.isVertical()) {
            return Math.abs(point.getX() - this.start().getX()) < EPSILON;
        }
        if (this.isHorizontal()) {
            return Math.abs(point.getY() - this.end().getY()) < EPSILON;
        }
        return false;
    }

    /**
     * Returns true if the lines intersect, false otherwise.
     *
     * @param other the other line to check intersection with.
     * @return true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        if (this.isOnTheLine(other.start) || this.isOnTheLine(other.end)) {
            return true;
        }
        if (other.isOnTheLine(this.start) || other.isOnTheLine(this.end)) {
            return true;
        }
        if (this.start.equals(other.start) || this.start.equals(other.end)
                || this.end.equals(other.start) || this.end.equals(other.end)) {
            return true;
        }
        if (this.equals(other)) {
            return true;
        }

        // Check if both lines are vertical
        if (this.isVertical() && other.isVertical()) {
            if (this.isOnTheLine(other.start) || this.isOnTheLine(other.end)) {
                return true;
            }
            return this.start.equals(other.start) || this.end.equals(other.end)
                    || this.start.equals(other.end) || this.end.equals(other.start);
        }

        // Check if one of the lines is vertical
        if (this.isVertical() || other.isVertical()) {
            double verticalX;
            double otherSlope;
            double otherFreeParameter;

            if (this.isVertical()) {
                verticalX = this.start.getX();
                otherSlope = other.slope();
                otherFreeParameter = other.freeParameter();
            } else {
                verticalX = other.start.getX();
                otherSlope = this.slope();
                otherFreeParameter = this.freeParameter();
            }

            double yIntersect = otherSlope * verticalX + otherFreeParameter;
            return this.isWithinBounds(verticalX, yIntersect) && other.isWithinBounds(verticalX, yIntersect);
        }

        // General case where both lines have slopes
        if (Math.abs(this.slope() - other.slope()) < EPSILON) {
            if (this.isOnTheLine(other.start) || this.isOnTheLine(other.end)) {
                return true;
            }
            return this.start.equals(other.start) || this.end.equals(other.end)
                    || this.start.equals(other.end) || this.end.equals(other.start);
        }

        double xIntersect = (other.freeParameter() - this.freeParameter()) / (this.slope() - other.slope());
        double yIntersect = this.slope() * xIntersect + this.freeParameter();

        return this.isWithinBounds(xIntersect, yIntersect) && other.isWithinBounds(xIntersect, yIntersect);
    }

    /**
     * Returns the slope of the line.
     *
     * @return the slope of the line.
     */
    private double slope() {
        return (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
    }

    /**
     * Returns the y-intercept of the line.
     *
     * @return the y-intercept of the line.
     */
    private double freeParameter() {
        return this.start.getY() - this.slope() * this.start.getX();
    }

    /**
     * Returns true if the line intersects with both given lines.
     *
     * @param other1 the first line to check intersection with.
     * @param other2 the second line to check intersection with.
     * @return true if the line intersects with both given lines, false otherwise.
     */
    public boolean isIntersecting(Line other1, Line other2) {
        return isIntersecting(other1) && isIntersecting(other2);
    }

    /**
     * Returns the intersection point if the lines intersect, and null otherwise.
     *
     * @param other the other line to find the intersection with.
     * @return the intersection point if the lines intersect, null otherwise.
     */
    public Point intersectionWith(Line other) {
        if (this.equals(other)) {
            return null;
        }
        if (isIntersecting(other)) {
            if (this.start.equals(other.start) || this.end.equals(other.end)
                    || this.start.equals(other.end) || this.end.equals(other.start)) {
                if (this.start.equals(other.start)) {
                    return this.start;
                } else if (this.end.equals(other.end)) {
                    return this.end;
                } else if (this.start.equals(other.end)) {
                    return this.start;
                } else if (this.end.equals(other.start)) {
                    return this.end;
                }
            }
            if ((this.isOnTheLine(other.start) || this.isOnTheLine(other.end))
                    && Math.abs(this.slope() - other.slope()) < EPSILON) {
                return null;
            }
            if (Math.abs(this.slope() - other.slope()) < EPSILON) {
                return null;
            }
            if (this.isOnTheLine(other.start)) {
                return other.start;
            }
            if (this.isOnTheLine(other.end)) {
                return other.end;
            }
            if (other.isOnTheLine(this.start)) {
                return this.start;
            }
            if (other.isOnTheLine(this.end)) {
                return this.end;
            }
            if (this.isVertical()) {
                double xIntersect = this.end().getX();
                double yIntersect = other.slope() * xIntersect + other.freeParameter();
                return new Point(xIntersect, yIntersect);
            }
            if (other.isVertical()) {
                double xIntersect = other.end().getX();
                double yIntersect = this.slope() * xIntersect + this.freeParameter();
                return new Point(xIntersect, yIntersect);
            }
            if (this.isHorizontal()) {
                double yIntersect = this.end().getY();
                double xIntersect = (yIntersect - other.freeParameter()) / other.slope();
                return new Point(xIntersect, yIntersect);
            }
            if (other.isHorizontal()) {
                double yIntersect = other.end().getY();
                double xIntersect = (yIntersect - this.freeParameter()) / this.slope();
                return new Point(xIntersect, yIntersect);
            }
            double xIntersect = (other.freeParameter() - this.freeParameter())
                    / (this.slope(start, end) - other.slope(other.start(), other.end()));
            return new Point(xIntersect, this.slope(start, end) * xIntersect + this.freeParameter());
        }
        return null;
    }

    /**
     * Checks if the lines are equal.
     *
     * @param other the other line to compare.
     * @return true if the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        return this.start.equals(other.start()) && this.end.equals(other.end());
    }
    /**
     * Finds the closest intersection point to the start of this line within a given rectangle.
     * This method calculates intersection points between this line and the rectangle's boundaries
     * and returns the point closest to the start of the line. If no intersection points are found,
     * this method returns null.
     *
     * @param rect The rectangle with which to calculate intersection points.
     * @return The closest intersection point to the start of this line, or null if no intersections occur.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {

        java.util.List<Point> list = rect.intersectionPoints(this);

        if (!list.isEmpty() && list != null) {
            Point closestIntersection = list.get(0);
            for (int i = 1; i < list.toArray().length; i++) {
                if (list.get(i).distance(this.start) <= closestIntersection.distance(this.start)) {
                    closestIntersection = list.get(i);
                }
            }
            return closestIntersection;
        }
        return null;
    }


}

