package Generation;

import java.util.*;

/**
 * Created by Ross Byrne on 11/01/17.
 */
public class Node {

    private int id;  // 0 = empty space, 1 = area, 2 = connection space
    private int xPos;
    private int yPos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    // get an array of nodes that are adjacent to this node in the world
    public Node[] adjacentNodes(Node[][] world){

        java.util.List<Node> adjacents = new java.util.ArrayList<Node>();

        if (yPos > 0) {
           // if(maze[xPos - 1][yPos].getId() != 0) // only add if not a hedge
                adjacents.add(world[yPos - 1][xPos]); //Add North
        }
        if (yPos < world.length - 1) {
            //if(maze[yPos + 1][xPos].getId() != 0) // only add if not a hedge
                adjacents.add(world[yPos + 1][xPos]); //Add South
        }
        if (xPos > 0) {
            //if(maze[yPos][xPos - 1].getId() != 0) // only add if not a hedge
                adjacents.add(world[yPos][xPos - 1]); //Add West
        }
        if (xPos < world[yPos].length - 1) {
            //if(maze[yPos][xPos + 1].getId() != 0) // only add if not a hedge
                adjacents.add(world[yPos][xPos + 1]); //Add East
        }

        return (Node[]) adjacents.toArray(new Node[adjacents.size()]);
    }

    // calculate the distance between this node and Node n
    public int getDistance(Node n){

        double x1 = this.xPos;
        double y1 = this.yPos;
        double x2 = n.getxPos();
        double y2 = n.getyPos();

        return (int) Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));

    } // calculateNodeDistance()

} // class
