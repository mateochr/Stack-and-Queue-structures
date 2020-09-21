import java.io.PrintStream;
import java.util.NoSuchElementException;

public class IntQueueImpl implements IntQueue
{
	
	public ListNode first;
	public ListNode last;
	public String empty_list;
 
	
	/*
	 * Constructor without arguments.
	 * Calls the second constructor 
	 * and creates a queue with the name
	 * "list."
	 */
	public IntQueueImpl()
	{
		this("list");
	}
 
 
	/*
	 * Constructor with a string as an argument.
	 * Creates a list with the name "listName".
	 */
	public IntQueueImpl (String listName)
	{
		empty_list = listName;
		first = last = null;
	}
 
 
	/*
	 * Checks if the queue is empty and returns the result.
	 */
	@Override
	public boolean isEmpty() 
	{
		if (first == last & first == null)
		{
			return true;
		}//end if
		else 
		{
			return false;
		}//end else
	}//end isEmpty

 
	/*
	 * Inserts an integer to the queue.
	 */
	@Override
	public void put(int item) 
	{
		ListNode newnode = new ListNode(item);
		if(isEmpty())
		{
			first = last = newnode;
		}//end if
		else
		{
			last.next = newnode;
			last = newnode;
		}//end else
	}//end put
 

	/*
	 * Removes and returns the oldest item of the queue.
	 * Throws exception if the queue is empty. 
	 */
	@Override
	public int get() throws NoSuchElementException 
	{
		if(isEmpty())
		{
			throw new NoSuchElementException("Queue is empty, we can't get item.");
		}//end if
		else
		{
			int removedItem = (int) first.getElement();
			if (first == last)
			{
				first = last = null;
			}//end if
			else
			{
				first = first.next;
			}//end else
			return removedItem; 
		}//end else
	}//end get
 
 
	/*
	 * Returns without removing the oldest item of the queue.
	 * Throws exception if the queue is empty.
	 */
	@Override
	public int peek() throws NoSuchElementException 
	{
		if(isEmpty())
		{
			throw new NoSuchElementException("Queue is empty,we can't peek item.");
		}//end if
		else
		{
			int returnedItem = (int)first.getElement();
			return returnedItem; 
		}//end else
	}//end peek
 

	/*
	 * Prints the elements of the queue, starting from the oldest item.
	 */
	@Override
	public void printQueue(PrintStream stream)
	{
		if(isEmpty())
		{
			stream.println("Queue is empty, no items to print");
		}//end if
		else
		{
			ListNode temp = first;
			while(temp != last)
			{
				stream.println(temp.getElement());
				temp = temp.next;
			}//end while
			stream.println(last.getElement());
		}//end else
	}//end printQueue
 
 
	/*
	 * Returns the size of the queue.
	 */
	@Override
	public int size() 
	{
		ListNode temp = new ListNode(null);
		int i = 0;
		if(isEmpty())
		{
			return i;
		}//end if
		else
		{
			for(temp = first; temp != last; temp = temp.next)
			{
				i++;
			}//end for
			return i+1;
		}//end else
	}//end size
	
}//end class