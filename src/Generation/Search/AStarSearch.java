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

    private Map<Node, Integer> pathCosts = new HashMap<>(); // key is node, pair is path cost
    private Map<Node, Node> childParent = new HashMap<>(); // key is child, pair is parent

    public void traverse(Node[][] world, Node start, Node goal) {

        // save the start node
        this.start = start;

        // save the goal node
        this.goal = goal;

        Node node = start;

        // create open priority queue and closed queue
        PriorityQueue<Node> open = new PriorityQueue<>(20, Comparator.comparingInt(current -> (pathCosts.get(current) + current.getDistance(goal))));
        List<Node> closed = new ArrayList<Node>();

        // add start node to open queue
        open.offer(node);

        // set the start node's cost as 0
        pathCosts.put(node, 0);

        // loop while there are nodes in the open queue
        while(!open.isEmpty()){

            // get node from open queue
            node = open.poll();

            // add node to closed queue
            closed.add(node);

            // if goal node is found
            if (node.equals(goal)){

                // break out of loop, path to goal has been set
                break;
            } // if

            //Process adjacent nodes
            Node[] children = node.adjacentNodes(world);

            // loop through node's children
            for (int i = 0; i < children.length; i++) {

                // get child
                Node child = children[i];

                // if already in queue
                if ((open.contains(child) || closed.contains(child)) ){
                    // ignore
                }else{
                    // remove from open and closed queues
                    open.remove(child);
                    closed.remove(child);

                    // set the parent node of child
                    childParent.put(child, node);

                    // set the path cost (parent + 1)
                    pathCosts.put(child, pathCosts.get(node) + 1);

                    // add to open queue for processing
                    open.add(child);
                } // if
            } // for
        } // while
    } // traverse()

    // gets the next node to move to in path
    // back following the path back from the
    // goal node
//    public Node getNextNode() {
//
//        Node last = null;
//        Node current = null;
//
//        // get parent of goal node
//        current = childParent.get(goal);
//
//        // loop while not at start node
//        do {
//
//            // if not start node
//            if(!current.equals(start)){
//
//                // save node
//                last = current;
//            } // if
//
//            // get the next nodes parent
//            current = childParent.get(current);
//
//        }while(!current.equals(start));
//
//        // last node visited is next move
//        return last;
//
//    } // getNextNode()


} // class
