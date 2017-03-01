/* Blake Impecoven - CSCD320 - prog2
 * Node class for use with LinkedList.java
**/

public class Node
{
   public int place;
   public int weight;
   public Node next;
      
   public Node()
   {
      this.place = 0;
      this.weight = 0;
      this.next = null;
   }//end Node 0
      
   public Node(final int place, final int weight)
   {
      this.place = place;
      this.weight = weight;
      this.next = null;
   }//end Node 1
      
}//end class