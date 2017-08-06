package Generation;

import Generation.Search.AStarSearch;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.*;

/**
 * Created by Ross Byrne on 11/01/17.
 */
public class Runner {

    // 2 dimensional array list

    private static Node[][] world;
    private static Map<Node, Set<Node>> nodeAdjacencyList = new HashMap<>();    // adjacencyList

    public static void main(String[] args) {

        Random rand = new Random();

        int width = 32;
        int height = 20;
        int minSpaceBetweenAreas = 2;
        float percentageUsed = 1f;
        world = new Node[height][width];

        List<Point> points;

        int roomsToGen = getNumberOfAreas(width, height, percentageUsed, minSpaceBetweenAreas);

        // populate world with default values
        for(int i = 0; i < height; i++){

            for(int j = 0; j < width; j++){

                // create a new Node
                Node n = new Node();
                n.setId(0);
                n.setyPos(i);
                n.setxPos(j);

                // add the node to the world
                world[i][j] = n;

            } // for
        } // for

        // generate rooms
        for(int i = 0; i < roomsToGen; i++){

            boolean isSpotFree = false;
            int xPos;
            int yPos;
            Node n;

            do {

                // generate a random xPos
                xPos = rand.nextInt(width);

                // generate a random yPos
                yPos = rand.nextInt(height);

                // check that nothing is already there
                if(checkAreaIsFree(xPos, yPos, minSpaceBetweenAreas)){

                    // space is free
                    isSpotFree = true;

                    // add room
                    world[yPos][xPos].setId(1);

                    // add node to adjacency list
                    nodeAdjacencyList.put(world[yPos][xPos], new HashSet<>());

                } // if

            }while (isSpotFree == false);

        } // for

        // connect nodes together
        connectNodes();

       Node n1 = nodeAdjacencyList.keySet().iterator().next();
       Set<Node> cons = nodeAdjacencyList.get(n1);

       Node c1 = cons.iterator().next();

        System.out.println("N1: (" + n1.getxPos() + "," + n1.getyPos() + ")");
        System.out.println("C1: (" + c1.getxPos() + "," + c1.getyPos() + ")");

        // test A*
        AStarSearch aStarSearch = new AStarSearch();
        aStarSearch.traverse(world, n1, c1);
        Node[] path = aStarSearch.getPath();
        System.out.println("Number of Connecting nodes: " + path.length);

        for(Node n : path){

            System.out.print(" -> ");
            System.out.print("(" + n.getxPos() + "," + n.getyPos() + ")");
        }
        System.out.println();

        // show calculated connection on map
        for(Node n : path){

            if(n.getId() == 1){ // if a room

                // don't over ride
            }
            else {

                // mark as connection
                n.setId(2);
            }

        } // for

        // print out world
        printWorld();


        System.out.println("Rooms to generate: " + getNumberOfAreas(width, height, percentageUsed, minSpaceBetweenAreas));
        System.out.println("Nodes in adjacencyList: " + nodeAdjacencyList.size());

    } // main()

    // prints out the world
    public static void printWorld(){

        String space = " ";

        // print to number line for X axis

        // print to second digit of the two digit number
        System.out.print("\n    ");
        for(int x = 0; x < world[0].length; x++){

            if(x < 10)
                System.out.print("  ");
            else
                System.out.print(x % 10 + " ");

        } // for

        // print the first digit of the two digit number
        System.out.print("\n    ");
        for(int x = 0; x < world[0].length; x++){

            if(x < 10)
                System.out.print(x + " ");
            else
                System.out.print(x / 10 + " ");

        } // for

        System.out.println();
        // print out world
        for(int i = 0; i < world.length; i++){

            // print out Y axis numbers
            if(i < 10)
                System.out.print(" ");
            System.out.print(i + ".");

            for(int j = 0; j < world[i].length; j++){

                switch(world[i][j].getId()){
                    case 0: // empty space
                        System.out.print(space + "-");
                        break;
                    case 1: // area
                        System.out.print(space + "A");
                        break;
                    case 2: // connection
                        System.out.print(space + "*");
                } // switch

            } // for
            System.out.print(space);
            System.out.println();
        } // for

    } // printWorld()

    private static int getNumberOfAreas(int width, int height, float percent, int minSpaceBetweenAreas){

        // calculate the number of blocks taken by an area
        // each area takes up 1 + the minSpaceBetweenAreas to the power of 2
        // ie minSpace = 2, that is 2 blocks in every direction around placed area
        int areaTakenByZone = (int)Math.pow((double)(1 + (minSpaceBetweenAreas * 2)), 2);

        // calculate the number of areas that can fit in the area give (plus percentage calc)
        float numberToGen = ((width * height) / areaTakenByZone) * percent;

        // return number of areas to generate, casting it to an int
        return (int)numberToGen;

    } // getNumberOfAreas()

    private static boolean checkAreaIsFree(int targetX, int targetY, int minSpaceBetweenAreas){

        int iMin = targetY - minSpaceBetweenAreas; // height
        int jMin = targetX - minSpaceBetweenAreas; // width

        int iMax = targetY + minSpaceBetweenAreas;
        int jMax = targetX + minSpaceBetweenAreas;

        if(iMin < 0 || jMin < 0)
            return false;

        if(iMax >= world.length || jMax >= world[iMax].length)
            return false;

        for(int i = iMin; i <= iMax; i++){

            for(int j = jMin; j <= jMax; j++){

                if(world[i][j].getId() != 0)
                    return false;

            } // for
        } // for

        return true;

    } // checkAreaIsFree()

    private static void connectNodes(){

        double distance;
        Map<Node, NodeConnection[]> connectionCandidatesMap = new HashMap<>();
        List<NodeConnection> nodeConnectionList;
        NodeConnection con;
        NodeConnection[] connectionCandidates;

        // loop through each node
        for(Node n : nodeAdjacencyList.keySet()){

            // create object to store node and it's possible connections
            nodeConnectionList = new ArrayList<>();

            for(Node t: nodeAdjacencyList.keySet()){

                // if not selected node
                if(!n.equals(t)){

                    // create object to hold distance
                    con = new NodeConnection();
                    con.setSelectedNode(n);
                    con.setTargetNode(t);

                    // calculate the distance between the two nodes
                    distance = calculateNodeDistance(n.getxPos(), n.getyPos(), t.getxPos(), t.getyPos());

                    // save distance
                    con.setDistance(distance);

                    // add to list
                    nodeConnectionList.add(con);

                } // if
            } // for

            // create array to hold top 4 connections
            connectionCandidates = new NodeConnection[4];

            // sort the collection
            Collections.sort(nodeConnectionList, (a, b) -> a.getDistance() < b.getDistance() ? -1 : a.getDistance() == b.getDistance() ? 0 : 1);

            int count = 0;

            // loop through the sorted nodes, save first 4 (the closest nodes)
            for(NodeConnection nc : nodeConnectionList){

                // save the node
                connectionCandidates[count] = nc;

                // increment counter
                count++;

                // if 4 are collected, break out of loop
                if(count > 3){
                    break;
                } // if

            } // for

            // save the top 4 nodes to connect
            connectionCandidatesMap.put(n, connectionCandidates);

        } // for

        // decide which connections to keep

        // add connections to adjacency list

        for(Node n : connectionCandidatesMap.keySet()){

            for (NodeConnection nc : connectionCandidatesMap.get(n)){

                // get target node and add it to adjacency list for selected node
                nodeAdjacencyList.get(nc.getSelectedNode()).add(nc.getTargetNode());
            } // for
        } // for

    } // connectNodes

    private static double calculateNodeDistance(int x1, int y1, int x2, int y2){

        double distance = 0;

        // the distance between two points calculation
        distance = (Math.sqrt(Math.pow(Math.abs(x1 - x2), 2) + Math.pow(Math.abs(y1 - y2), 2)));

        return distance;

    } // calculateNodeDistance()

} // class
