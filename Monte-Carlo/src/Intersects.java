/**
 * Created by AlinaCh on 05.02.2017.
 */
public class Intersects {

    /**
     *
     * @param a
     * @param b
     * @param c
     * @param d
     * @return whether the lines are intersects
     */
    public boolean intersects(Point2D a, Point2D b, Point2D c, Point2D d) {

        /**
         * makes matrix for the equation
         */
        double[][] matrix = new double[2][2];
        matrix[0][0] = b.getX() - a.getX();
        matrix[1][0] = b.getY() - a.getY();
        matrix[0][1] = c.getX() - d.getX();
        matrix[1][1] = c.getY() - d.getY();

        /**
         * finds determinant of the function if it is zero the lines are parallel
         */
        double determinant = matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];

        if (determinant == 0) {
            return false;
        } else {
            double determinantU = (c.getX() - a.getX()) * matrix[1][1] - (c.getY() - a.getY()) * matrix[0][1];
            double determinantV = (c.getY() - a.getY()) * matrix[0][0] - (c.getX() - a.getX()) * matrix[1][0];

            double u = determinantU / determinant;
            double v = determinantV / determinant;

            return u > 0 && u < 1 && v > 0 && v < 1;
        }
    }
}
