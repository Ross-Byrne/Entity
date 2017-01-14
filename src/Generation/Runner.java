package Generation;

import java.util.*;

/**
 * Created by Ross Byrne on 11/01/17.
 */
public class Runner {

    // 2 dimensional array list

    private static Character[][] world;
    private static Map<Node, Set<Node>> nodeAdjacencyList = new HashMap<>();    // adjacencyList


    public static void main(String[] args) {

        Random rand = new Random();

        int width = 52;
        int height = 20;
        int minSpaceBetweenAreas = 2;
        float percentageUsed = 1f;
        world = new Character[height][width];

        int roomsToGen = getNumberOfAreas(width, height, percentageUsed, minSpaceBetweenAreas);

        // populate world with default values
        for(int i = 0; i < height; i++){

            for(int j = 0; j < width; j++){

                world[i][j] = '-';

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
                    world[yPos][xPos] = 'A';

                    // create the node and set its position
                    n = new Node();
                    n.setxPos(xPos);
                    n.setyPos(yPos);

                    // add node to adjacency list
                    nodeAdjacencyList.put(n, new HashSet<>());

                } // if

            }while (isSpotFree == false);

        } // for

        // print out world
        for(int i = 0; i < height; i++){

            for(int j = 0; j < width; j++){

                System.out.print(world[i][j]);
            } // for

            System.out.println();
        } // for

        System.out.println("Rooms to generate: " + getNumberOfAreas(width, height, percentageUsed, minSpaceBetweenAreas));
        System.out.println("Nodes in adjacencyList: " + nodeAdjacencyList.size());

    } // main()

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

                if(world[i][j] != '-')
                    return false;

            } // for
        } // for

        return true;

    } // checkAreaIsFree()

    private static int calculateNodeDistance(int x1, int y1, int x2, int y2){

        int distance = 0;

        // the distance between two points calculation
        distance = (int)(Math.sqrt(Math.pow(Math.abs(x1 - x2), 2) + Math.pow(Math.abs(y1 - y2), 2)));

        return distance;

    } // calculateNodeDistance()

} // class
