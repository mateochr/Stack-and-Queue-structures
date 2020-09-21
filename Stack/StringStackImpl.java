import java.io.PrintStream;
import java.util.NoSuchElementException;

public class StringStackImpl implements StringStack
{
	
	public ListNode top;
	public int sz; //size of stack
	public String empty_list;
 
 
	/*
	 * Constructor without arguments.
	 * Calls the second constructor 
	 * and creates a stack with the name
	 * "list."
	 */	
	public StringStackImpl()
	{ 
		this("list");
	}
 
 
	/*
	 * Constructor with a string as an argument.
	 * Creates a list with the name "listName".
	 */	
	public StringStackImpl (String listName) 
	{ 
		empty_list = listName;
		top = null;
		sz = 0;
	}
 
 
	/*
	 * Checks if the stack is empty and returns the result.
	 */
	@Override
	public boolean isEmpty()
	{ 
		if (top == null) 
		{
			return true;
		}//end if
		else 
		{
			return false;
		}//end else
	}//end isEmpty
 
 
	 /*
	  * Pushes a String item to the stack.
	  */
	 @Override
	 public void push(String item) 
	 { 
		sz++;
		ListNode newnode = new ListNode(item);
		if(isEmpty())
		{
			top = newnode;
		}//end if
		else
		{
			newnode.next = top;
	   		top = newnode;
		}//end else
	 }//end push
 
 
	 /*
	  *Removes and returns the item on the top of the stack. 
	  *Throws exception if the stack is empty.
	  */
	 @Override
	 public String pop() throws NoSuchElementException 
	 { 
		sz--;
		if (isEmpty()) 
		{
			throw new NoSuchElementException("Stack is empty, we cant pop item.");
		}//end if
		else 
		{
			String removedItem = (String) top.getElement();
			if(top.next==null)
			{
				top = null;
			}//end if
			else
			{
				top = top.next;
			}//end else
			return removedItem;
		}//end else
	 }//end pop
 
 
	 /*
	  * Returns without removing the item on the top of the stack. 
	  * Throws exception if the stack is empty.
	  */
	 @Override
	 public String peek() throws NoSuchElementException
	 { 
		if (isEmpty()) 
		{
			throw new NoSuchElementException("Stack is empty, we can't peek item.");
		}//end if
		else 
		{
			return (String) top.getElement();
		}//end else
	 }//end peek
 
 
	 /*
	  * Prints the contents of the stack, starting from the item on top.
	  * Throws exception if the stack is empty.
	  */
	 @Override
	 public void printStack(PrintStream stream) 
	 { 
		if(isEmpty())
		{
			stream.println("Stack is empty, no items to print.");
		}//end if
		else
		{
			ListNode temp = top;
			while (temp != null) 
			{
				stream.println(temp.getElement());
				temp = temp.next;
			}//end while
		}//end else
	 }//end printStack
 
 
	 /*
	  * Returns the size of the stack. 
	  */
	 @Override
	 public int size()
	 { 
		 return sz;
	 }//end size
	 
}//end class
