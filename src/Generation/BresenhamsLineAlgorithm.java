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

        for (int x = x1; x <= x2; x++ )  {

            points.add(new Point(x, y));

            eps += dy;
            if ( (eps << 1) >= dx )  {
                y++;
                eps -= dx;

            } // if
        } // for

        return points;

    } // calculatePoints()

} // class
