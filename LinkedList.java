/* Blake Impecoven - CSCD320 - prog2
 * Singly LinkedList implementation
**/

public class LinkedList
{  
   public Node head;
   private int size;
   
   public LinkedList()
   {
      this.head = new Node();
      this.size = 0;
   }//end LinkedList
   
   public int size(){return this.size;}
   
   public void addLast(final int place, final int weight)
   {
      Node newNode = new Node(place, weight);
      Node cur = head;
      
      if(this.size == 0)
      {
         cur.next = newNode;
         size++;
      }
      else
      {
         while(cur.next != null)
            cur = cur.next;
            
         cur.next = newNode;
         size++;   
      }//end else
   }//end addLast
      
   public Node search(final int key)
   {
      Node cur = head;
      for(int x = 0; x < key; x++)
         cur = cur.next;
         
      return cur;   
   }//end search
   
   @Override
   public String toString() {
       Node cur = this.head;
       String ret = "";
       
       while(cur.next != null) {
           cur = cur.next;
           ret += cur.place + ") Shortest distance: " + cur.weight + "\n";
       }//end while
       return ret;
   }//end toString
   
}//end class LinkedList