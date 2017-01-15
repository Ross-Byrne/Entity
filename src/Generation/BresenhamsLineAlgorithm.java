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
