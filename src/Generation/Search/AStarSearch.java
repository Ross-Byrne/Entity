package Generation.Search;

import Generation.Node;

import java.util.*;

/**
 * Created by Ross Byrne on 06/08/17.
 *
 * A* search. Guaranteed to find best, optimal path between two nodes in a graph.
 */
public class AStarSearch {

    private Node goal;
    private Node start;

    public void traverse(Node[][] maze, Node start, Node goal) {

        // save the start node
        this.start = start;

        // save the goal node
        this.goal = goal;

        Node node = start;

        // create open priority queue and closed queue
        PriorityQueue<Node> open = new PriorityQueue<>(20, (Node current, Node next)-> (current.getPathCost() + current.getDistance(goal)) - (next.getPathCost() + next.getDistance(goal)));
        List<Node> closed = new ArrayList<>();

        // add start node to open queue
        open.offer(node);

        // set the start nodes cost as 0
        node.setPathCost(0);

        // loop while there are nodes in the open queue
        while(!open.isEmpty()){

            // get node from open queue
            node = open.poll();

            // add node to closed queue
            closed.add(node);

            // if goal node is found
            if (node.equals(goal)){

                System.out.println("Goal Found!");
                // break out of loop, path to goal has been set
                break;
            } // if

            //Process adjacent nodes
            Node[] children = node.adjacentNodes(maze);

            // loop through node's children
            for (int i = 0; i < children.length; i++) {

                // get child
                Node child = children[i];

                // if already in queue
                if ((open.contains(child) || closed.contains(child)) ){
                    continue; // ignore
                }else{
                    // remove from open and closed queues
                    open.remove(child);
                    closed.remove(child);

                    // set the parent node of child
                    child.setParent(node);

                    // set the path cost (parent + 1)
                    child.setPathCost(node.getPathCost() + 1);

                    // add to open queue for processing
                    open.add(child);
                } // if
            } // for
        } // while
    } // traverse()

    // gets the path from start to goal node
    // starts at the goal node and works it's way back to the start
    // by getting the goal nodes parent, and then its parent etc
    // until the start node is found
    public Node[] getPath(){

        LinkedList<Node> path = new LinkedList<>();
        Node current = null, last = null;

        // get parent of goal node
        current = goal.getParent();

        // loop while not at start node
        do {

            // if not start node
            if(!current.equals(start)){

                // save node
                last = current;

                // add last to the start of the path
                path.addFirst(last);
            } // if

            // get the next nodes parent
            current = last.getParent();



        }while(!current.equals(start));

        // return the path as an array
        return path.toArray(new Node[path.size()]);

    } // getPath


} // class
