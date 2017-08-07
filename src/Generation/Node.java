package Generation;

import java.util.*;

/**
 * Created by Ross Byrne on 11/01/17.
 */
public class Node {

    private int id;  // 0 = empty space, 1 = area, 2 = connection space
    private int xPos;
    private int yPos;
    private Node parent; // used for a* search
    private int pathCost; // used for a* search

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

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getPathCost() {
        return pathCost;
    }

    public void setPathCost(int pathCost) {
        this.pathCost = pathCost;
    }

    // get an array of nodes that are adjacent to this node in the world
    public Node[] adjacentNodes(Node[][] world){

        List<Node> adjacents = new java.util.ArrayList<>();

        if (yPos > 0) {
            //if(world[yPos - 1][xPos].getId() == 0) // only add if empty space
                adjacents.add(world[yPos - 1][xPos]); //Add North
        }
        if (yPos < world.length - 1) {
            //if(world[yPos + 1][xPos].getId() == 0) // only add if empty space
                adjacents.add(world[yPos + 1][xPos]); //Add South
        }
        if (xPos > 0) {
            //if(world[yPos][xPos - 1].getId() == 0) // only add if empty space
                adjacents.add(world[yPos][xPos - 1]); //Add West
        }
        if (xPos < world[yPos].length - 1) {
            //if(world[yPos][xPos + 1].getId() == 0) // only add if empty space
                adjacents.add(world[yPos][xPos + 1]); //Add East
        }


        return adjacents.toArray(new Node[adjacents.size()]);
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
