import java.io.*;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;


/**
 * Created by AlinaCh on 04.02.2017.
 */
public class Main {

    private static ArrayList<Point2D> polygon;
    private static double areaOfRectangle;
    private static ArrayList<Point2D> rectangle;
    private static double minX, minY, maxX, maxY;
    private static ThreadLocalRandom random = ThreadLocalRandom.current();

    /**
     * reads text from input file
     * @return
     */
    public static String readString() {
        try {
            Scanner sc = new Scanner(new File("input.txt"));
            String read = sc.nextLine();
            return read;
        } catch (FileNotFoundException ex) {
            return null;
        }
    }

    /**
     * writes answer to the output file
     * @param s
     */
    public static void writeString(String s) {
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream("output.txt"), "ascii"))) { writer.write(s); }
        catch (IOException ex) { }
    }

    /**
     * gets coordinates onto the temporal ArrayList
     * @param s
     * @return ArrayList
     */
    public static ArrayList<Double> getCoordinates(String s) {
        ArrayList<Double> temporal = new ArrayList();
        for (String coordinate : s.split(" ")) {
            double coor = Double.parseDouble(coordinate);
            temporal.add(coor);
        }
        return temporal;
    }

    /**
     * makes ArrayList with coordinates of polygon
     * finds max and min values of x & y to find bigger rectangle
     * @param myArray
     */
    public static void setPolygon(ArrayList<Double> myArray) {
        polygon = new ArrayList<>();
        minX = myArray.get(1);
        minY = myArray.get(2);
        maxY = myArray.get(2);
        maxX = myArray.get(1);
        for (int i = 1; i <= myArray.length(); i = i + 2) {
            if (myArray.get(i) < minX) minX = myArray.get(i);
            if (myArray.get(i) > maxX) maxX = myArray.get(i);
            if (myArray.get(i + 1) < minY) minY = myArray.get(i + 1);
            if (myArray. get(i + 1) > maxY) maxY = myArray.get(i + 1);
            Point2D coordinate = new Point2D(myArray.get(i), myArray.get(i + 1));
            polygon.add(coordinate);
        }
        getAreaOfRectangle();
    }

    /**
     * finds area of the rectangle
     */
    public static void getAreaOfRectangle() {
        areaOfRectangle = (maxX - minX) * (maxY - minY);
    }

    /**
     * checks whether the random created point is inside of polygon
     * @param point
     * @return
     */
    public static boolean isInside(Point2D point) {
        int flag = 0;
        Intersects check = new Intersects();
        double x = random.nextDouble(maxX, maxX * 1000);
        double y = random.nextDouble(maxY, maxY * 1000);
        Point2D temporal = new Point2D(x, y);
        int i = 1;
        if (check.intersects(polygon.get(i), polygon.get(polygon.length()), point, temporal)) flag++;
        for (i = 1; i < polygon.length(); i++) {
            if (check.intersects(polygon.get(i), polygon.get(i + 1), point, temporal)) flag++;
        }
        if (flag % 2 == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * finds random points
     * calculates the approximate area
     * @return
     */
    public static String monteCarlo() {
        int n = 0, i;
        for (i = 1; i < 400000; i++) {
            double x = random.nextDouble(minX, maxX);
            double y = random.nextDouble(minY, maxY);
            Point2D randomPoint = new Point2D(x,y);
            if(isInside(randomPoint)) n++;
        }
        double area = areaOfRectangle * n / i;
        String result = String.format("%.2f",area);
        return result;
    }

    public static void main(String[] args) {
        String text = readString();
        setPolygon(getCoordinates(text));
        writeString(monteCarlo());
    }
}
