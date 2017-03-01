import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/* Blake Impecoven - May, 2016 - CSCD320 - Programming Assignment 2
 * Tester File.
**/

/**
 * There is a problem in the runDijkstra method. when checking to see if a list is
 * null it is throwing a null pointer exception.
 */

public class Test_Dijkstra {

    public static void main(String[]args)
    {
        /*
         * We are guaranteed two parameters to be passed in from the command line,
         * the first is the name of the input file, the second being the value
         * of the source vertex needed for the Dijkstra's Algorithm.
        **/
        String inputFileName = args[0];
        Scanner inputFileReader = openInputFile(inputFileName);
        int sourceVertex = Integer.parseInt(args[1]);
        int rows = 4;//countRows(inputFileReader);
        LinkedList[] graph = buildGraph(inputFileReader, rows);
        
        LinkedList[]results = runDijkstra(graph, sourceVertex);
        printResults(results, sourceVertex);
    }//end main

    public static Scanner openInputFile(final String fileName) {
        if(fileName.isEmpty()) {
            throw new RuntimeException("File name could not be found.");
        }//end if

        try {
            return new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }//end try/catch
        return null;
    }//end openInputFile

    public static int countRows(final Scanner reader) {
        int count = 0;

        while(reader.hasNextLine()) {
            count++;
            reader.nextLine();
        }//end while

        return count;
    }//end while

    public static LinkedList[] buildGraph(final Scanner inputFileReader, int rows) {
        if (inputFileReader == null) {
            throw new RuntimeException("Scanner is null within buildGraph");
        }//end if
        LinkedList newList;
        LinkedList[]graph = new LinkedList[rows];

        int place;
        int weight;

        while(inputFileReader.hasNextLine()) {
            String tempLine = inputFileReader.nextLine();
            int row = Character.getNumericValue(tempLine.charAt(0));
            newList = new LinkedList();

            for(int x = 1; x < tempLine.length(); x++) {
                char choice = tempLine.charAt(x);

                if(x + 3 < tempLine.length() && choice == ':' || choice == ';') {
                    place = Character.getNumericValue(tempLine.charAt(x+1));
                    weight = Character.getNumericValue(tempLine.charAt(x+3));
                    newList.addLast(place, weight);
                    x += 3;
                }//end if
            }//end for
            graph[row] = newList;
        }//end while

        return graph;
    }//end parseFile
    
    public static void printList(final LinkedList[] myList) {
        for(int x = 0; x < myList.length; x++) {
            System.out.println("Row " + x + ": " + myList[x]);
        }//end for
    }//end printList

    public static void printResults(final LinkedList[] myList, final int vertex) {
        Node cur;

        for(int x = 0; x < myList.length; x++) {
            
            if(x != vertex) {
                if(myList[x] == null) { //no path
                    System.out.println("[" + x + "]unreachable");
                }//end if
                else {
                    System.out.print("[" + x + "]Shortest path: (" + vertex);
                    cur = myList[x].head;
                    int weight = 0;
                    int lastPrint = vertex;
                    while(cur.next != null) {
                        
                        cur = cur.next;
                        
                        if(cur.place != vertex && lastPrint != cur.place) {
                           System.out.print("," + cur.place);
                           weight += cur.weight;
                           lastPrint = cur.place;
                        }//end if
                    }//end while
                        System.out.print(") Shortest distance: " + weight + "\n");

                }//end else
            }//end if
        }//end for
    }//end printResults

    public static LinkedList[] runDijkstra(final LinkedList[]graph, final int vertex) {
        /*
         * The Nodes will be stored in order
         * This order will repeat for all numbers.
        **/
        LinkedList[]results = new LinkedList[(graph.length)];
        Node holder = new Node();
        boolean isLink;
        LinkedList path;

        Node cur = graph[vertex].head;
        int weightSum = 0;

        while(cur.next != null) {
            cur = cur.next;

            int checkPos = cur.place;
            weightSum = cur.weight;

            if(results[checkPos] == null) { //check if list exists
                results[checkPos] = new LinkedList();
            }//end if

            results[checkPos].addLast(cur.place, weightSum);
            Node checkPath = graph[checkPos].head;
            while(checkPath.next != null) {
                checkPath = checkPath.next;
                if(results[checkPath.place] == null) {
                    results[checkPath.place] = new LinkedList();
                }//end if
                results[checkPath.place].addLast(cur.place, cur.weight);
                
                checkPos = checkPath.place;
                weightSum += checkPath.weight;

                results[checkPath.place].addLast(checkPath.place, checkPath.weight);
                if(checkPath.next == null) {
                    checkPath = graph[checkPath.place].head;
                    checkPos = checkPath.next.place;
                    if(results[checkPos] != null) {
                        checkPath.next = null;
                    }//end if
                }//end if
            }//end while
//            while (checkPath.next != null) {
//                checkPath = checkPath.next;
//                results[cur.place].addLast(checkPath.place, checkPath.weight);
//                if(checkPath.next == null) { //end of this path
//                    checkPath =
//                }
//            }

        }//end while

        return results;
    }//end runDijkstra
}//end class
