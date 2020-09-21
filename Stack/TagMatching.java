import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
	
public class TagMatching
{
	static String path = null;
	
	public static void main(String args[]) throws FileNotFoundException 
	{
		StringStackImpl stackA= new StringStackImpl();//Creates a new stack named, stackA.
		path = args[0];//Saves the path that we give him in the variable path.
		FileInputStream file = new FileInputStream(path);////Creates a new file using FileInputStream with path as argument.
		Scanner scanner = new Scanner(file);//Creates scanner using file.
		int b=0;//We will use this to check if the file is empty or not.
		while(scanner.hasNextLine())//While there is a next line repeat.
		{
			String line = scanner.nextLine();//Moves to next line.
			    	
			/*
			 * Checking if the file is empty or not by removing all spaces. When all the spaces are gone
			 * if the line is empty and null then the file is empty.
			 */
			if(line.replaceAll("\\s", "") != null && !line.replaceAll("\\s", "").isEmpty())
			{
				b=1;//We just change the b to whatever just not to be equal to 0.
				int i=0;//First character of the line.
				while(i!=line.length())//Searching from the beginning until the end of the line.
				{
					
					/*
		        	 * If its an opening tag then pushes <whatever> in our stack.
		        	 */
		        	if(line.charAt(i)=='<' && line.charAt(i+1)!='/')//If its an opening tag
		        	{	
		        	
		        		/*
		        		 * (i) states where the character < is,so we start a new search from there to find
		        		 * the character >. When the search is done we push in the stack <whatever>.
		        		 */
		        		int j=i;
		        		while(line.charAt(j)!='>')
		        		{
		        			j++;
		        		}//end while
		        		stackA.push(line.substring(i,j+1));	//line.substring(i,j+1)=<whatever>
		        	}//end if
		        			
		        			
		        	/*
		        	 * If its a closing tag then we compare the top item of our stack with </whatever>, 
		        	 * and if they are equal we pop the top item of the stack.
		        	 * If they are not equal we push </whatever> in our stack,
		        	 * we do that because there could be closing tags without their matching opening tags. 
		        	 * And we put this under an if(stackA.size()!=0) because we can't peek the top item 
		        	 * if the stack is empty. If there is a closing tag at the first or last line 
		        	 * without it's matching opening tag opened somewhere the else just makes sure to
		        	 * catch that fault.
		        	 */
		        	else if(line.charAt(i)=='<' && line.charAt(i+1)=='/')//If its a closing tag
		        	{
		        				
		        		/*
		        		 * (i) states where the character < is, so we start a new search from there to find
		        		 * the character >. When the the search is done we do as said above.
		        		 */
		        		int k=i;
		        		while(line.charAt(k)!='>')
		        		{
		        			k++;
		        		}//end while
		        					
		        		if(stackA.size()!=0)
		        		{	
		        			
		        			/*
		        			 * if </whatever> without "/" is equal to top item.
		        			 */
		        			if(line.substring(i,k+1).replaceAll("/","").equalsIgnoreCase(stackA.peek()))//line.substring(i,k+1).replaceAll("/","") = </whatever> without "/" 
		        			{
		        				stackA.pop();
		        			}//end if
		        			else
					        {
					        	stackA.push(line.substring(i,k+1));	//line.substring(i,k+1)=</whatever>
					        }//end else
		        		}//end if
		        		else
		        		{
		        			stackA.push(line.substring(i,k+1));
		        		}//end else
		        					
		        					
	        		}//end else if
		        	i++;
		        }//end while
				        
			}//end if
			        
		}//end while
			   
		/*
		 * If b=0 then we never entered the first if,this means that the file is empty.
		 * If b!= then we entered the first if, so the file is not empty.
		 */
		if(b==0)
		{
			System.out.println("The file is empty.");
		}//end if
		else
		{
			if(!stackA.isEmpty())
			{
				System.out.println("File is not good");
			}//end if  
			else
			{
				System.out.println("File is good");
			}//end else
		}//end else
			    
		scanner.close();
			    
	}//end main 
		 
} //end class