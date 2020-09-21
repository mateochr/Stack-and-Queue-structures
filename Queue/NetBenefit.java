import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class NetBenefit 
{
		static String path = null;//Creates a string named path with null.
	 	public static void main(String args[]) throws FileNotFoundException 
	 	{
	 			IntQueueImpl buyQ = new IntQueueImpl();//Creates a new queue named buyQ.
	 			path = args[0];//Saves at path the path that we give as argument when we run the program.
				FileInputStream file = new FileInputStream(path);//Creates a new file using FileInputStream with path as argument.
		        Scanner scanner = new Scanner(file);//Creates scanner using file.
		        int x = 0;//Initialization of the stocks that we buy.
		        int sum = 0;//Initialization of the total, profit or loss.
		        int b=0;//We will use this to check if the file is empty or not.
		         while(scanner.hasNextLine())//While there is a next line repeat.
		         {
		        	 String line = scanner.nextLine();//Moves to next line.
		        	 line = line.replaceAll("\\s", "");//Removes spaces.
		        	 if(line != null && !line.isEmpty())//If line is null and empty then the file is empty end it doesn't continue.
		        	 {
        		 	 	 	 b=1;//We just change the b to whatever just not to be equal to 0.
			        	  	 if(line.substring(0,3).equalsIgnoreCase("buy"))//Checks if we buy or we sell.
				        	 {
				        		 
				        		 /*
				        		  * Finding if the number of stocks that we are buying
				        		  * is a one or two or three or more
				        		  * digit number.
				        		  */
				        		 int i = 3;////Initialization of our counter from the next character of buy plus the space.
				            	 while (line.charAt(i) < 'p' && line.charAt(i) < 'P' )//While the character is not 'p' and 'P' repeat.
				            	 {
				            		 i++;
				            		 
				            	 }//end while
				            	 
				            	 
				            	 /*
				            	  * Putting the number of stocks that we buy
				            	  * and the price we buy them in the list buyQ
				            	  * one following the other e.x ( if we buy 50 for 20
				            	  * and 30 for 10 the queue will be 50 20 30 10).
				            	  */
				            	 buyQ.put(Integer.parseInt(line.substring(3,i)));//line.substring(3,i)=number of stocks we buy.
				            	 buyQ.put(Integer.parseInt(line.substring(i+5)));//line.substring(i+5)=price we buy them.
				            	  x = x + Integer.parseInt(line.substring(3,i));//Adding the number of stocks we buy.
				            	  
				        	 }//end if
				        	 else//Else means that we sell.
					         {
					        	 
					        	 /*
				        		  * Finding if the number of stocks that we want to sell
				        		  * is a one or two or three or more
				        		  * digit number.
				        		  */
					        	 int i = 4;
				            	 while (line.charAt(i) < 'p' && line.charAt(i) < 'P' )
				            	 {
				            		 i++;
				            		 
				            	 }//end while
				            	 
				            	 
				            	 if(x < Integer.parseInt(line.substring(4,i)))//If the number of stocks we want to sell is larger than the number of stocks that we have bought.
				            	 {												//line.substring(3,i)=number of stocks that we want to sell.
				            		System.out.println("We don't have enough items to sell."); 
				            		
				            	 }//end if
				            	 else if(x == Integer.parseInt(line.substring(4,i)))//If the number of stocks we want to sell is equal to the number of stocks that we have bought.
				            	 {
				            		 /*
				            		  * At every repeat we add to sum (bought stocks*(price of selling - price of buying) )using .get() 
				            		  * because we want to remove what we sell and the price that we bought it
				            		  * that means that the size of the queue is getting smaller that's why there is no j++
				            		  * e.x(if a queue is 50 20 30 10 with size 4 after the first repeat it will be 30 10 and size 2 
				            		  * and sum will be sum+50(price of selling - 20)
				            		  */
				            		 int j=0;
				            		 while(j<buyQ.size())
				            		 {
				            			 sum = sum + buyQ.get()*(Integer.parseInt(line.substring(i+5))-buyQ.get());
				            			 
				            		 }//end while
				            		 
				            		 x=0;//We change x to 0 cause we sold every stock that we had.
				            		 
				            	 }//end else if
				            	 else if(x > Integer.parseInt(line.substring(4,i)))//If the number of stocks we want to sell is smaller than the number of stocks that we have bought
				            	 {
				            		 /*
				            		  * At every repeat our algorithm checks when the j+first item
				            		  * will surpass the number of stocks that we want to sell. When they are about to do that
				            		  * we want to keep the rest of them (cause number of stocks bought > number of stocks to sell)
				            		  * for a later sell.
				            		  */
				            	 	 int j=0;
				            		 while(j<Integer.parseInt(line.substring(4,i)))//line.substring(4,i)=number of stocks we want to sell
				            		 {
				            			 /*
				            			  * if j+first item <= number of stocks we want to sell we 
				            			  * use .peek() and .first.getNext().getElement() to add at sum the profit or loss
				            			  * number of stocks*(price of selling - price of stocks)
				            			  * line.substring(i+5)=price of selling 
				            			  * we use .get() to change j and remove the first item which is the number of stocks bought
				            			  * and then use again .get() to remove the first item which now is the price of stocks bought
				            			  */
				            			 if(j+buyQ.peek() <= Integer.parseInt(line.substring(4,i)))
				            			 {
				            				 sum=sum+buyQ.peek()*(Integer.parseInt(line.substring(i+5))-(int)buyQ.first.getNext().getElement());
				            				 j=j+buyQ.get();
				            				 buyQ.get();
				            				 
				            			 }//end if
				            			 
				            			 
				            			 /*
				            			  * if j+first item > number of stocks we want to sell
				            			  * means that we are about to surpass the number of stocks we want sell
				            			  * so there will be some stocks left for a later purchase 
				            			  * w=the stocks that will be left after the else
				            			  * sum is the same as above
				            			  * we use peek() to change j now cause we don't want to lose the first item yet
				            			  * we want the first item to be the stocks that will be left(w)
				            			  * we use .first.element for that
				            			  * e.x(if queue is 30 10 20 30 and we want sell 25
				            			  * w will be 30-25 = 5 and queue will be 5 10 20 30)
				            			  */
				            			 else
				            			 {
				            				 int w = j+buyQ.peek()-Integer.parseInt(line.substring(4,i));
				            				 sum=sum+(buyQ.peek()-w)*(Integer.parseInt(line.substring(i+5))-(int)buyQ.first.getNext().getElement());
				            				 j=j+buyQ.peek();
				            				 buyQ.first.element=w;
				            				 
				            			 }//end else
				            			 
				            		 }//end while
				            		 x=x-Integer.parseInt(line.substring(4,i));//x will be now the remaining stocks that we have
				            	 }//end else if
					        	 
					         }//end else
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
		         
			         System.out.println("Result is : " + sum);
			         
			         if(sum<0){
			        	 System.out.println("Loss is : " + Math.abs(sum));
			         }
			         else{
			        	 System.out.println("Profit is : " +sum);
			         }
		         }//end else 
		         
		         scanner.close();
		         
	 	}//end main 
	 	
	 	
} //end class
