package Generation;

import java.util.*;

/**
 * Created by Ross Byrne on 11/01/17.
 */
public class Node {

    private String name;
    private int xPos;
    private int yPos;

    //private Set<Node> adjacentNodes = new HashSet<>();

//    public void addAdjacentNode(Node n){
//
//        adjacentNodes.add(n);
//    }
//
//    public Set<Node> getAdjacentNodes(){
//
//        return new HashSet<>(adjacentNodes);
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

} // class
