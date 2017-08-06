package Generation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rossbyrne on 15/01/17.
 * Code taken from stackoverflow: http://stackoverflow.com/questions/39166316/bresenham-line-algorithm
 * https://en.wikipedia.org/wiki/Bresenham's_line_algorithm#Algorithm
 */
public class BresenhamsLineAlgorithm {

    // calculates the points between two points and returns them. Includes start and end point
    
    public List<Point> calculatePoints(int x1, int y1, int x2, int y2){

        // check if x1 > x2
        if(x1 > x2){
            int tx, ty;

            // if so, swap them
            tx = x1;
            ty = y1;

            // set x1/y1 to x2/y2
            x1 = x2;
            y1 = y2;

            // set x2/y2 to x1/y1
            x2 = tx;
            y2 = ty;

           // System.out.println("Swapped nodes");
        } // if

        List<Point> points = new ArrayList<>();

        int dx  = x2 - x1;
        int dy  = y2 - y1;
        int y   = y1;
        int eps = 0;

        for (int x = x1; x < x2+1; x++ )  {

            points.add(new Point(x, y));

            eps += dy;
            if ( (eps << 1) >= dx )  {
                y++;
                eps -= dx;

            } // if
        } // for

        return points;

    } // calculatePoints()

    public List<Point> calculatePointsUpdated(int x0, int y0, int x1, int y1){

        if (Math.abs(x1 - x0) > Math.abs(y1 - y0)) {
            // check if x1 > x2
            if (x0 > x1) {

                int[] pair = swap(x0, x1);
                x0 = pair[0];
                x1 = pair[1];

            } // if

        }
        else
        {

            // check if y1 > y2
            if (y0 > y1) {

                int[] pair = swap(y0, y1);
                y0 = pair[0];
                y1 = pair[1];

            } // if
        }

        List<Point> points = new ArrayList<>();

        int dx = x1 - x0;
        int dy = y1 - y0;
        int D = 2*dy - dx;
        int y = y0;
        int x = x0;

        System.out.println("dx: " + dx + " dy: " + dy);

        if(Math.abs(dx) > Math.abs(dy)) {

            for (x = x0; x < x1 + 1; x++) {

                points.add(new Point(x, y));

                if (D > 0) {

                    y = y + 1;
                    D = D - 2 * dx;
                }

                D = D + 2 * dy;

            } // for

        } else {

            for (y = y0; y < y1 + 1; y++) {

                points.add(new Point(x, y));

                if (D > 0) {

                    x = x + 1;
                    D = D - 2 * dy;
                }

                D = D + 2 * dx;

            } // for
        } // if

        return points;

    } // calculatePoints()


    // swaps two variables
    private int[] swap(int a, int b){

        return new int[]{b, a};
    } // swap()

} // class
