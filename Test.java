import java.util.Arrays;



public class Test {
	public static void main(String [] args)
	{

		int [] b = {1, 2, 3, 4, 5, 7, 8, 9,10};
		System.out.println(find(b));
	}
	
	
	public static int find(int []patchNumbers)
	{
		Boolean[] present = new Boolean[patchNumbers.length+2];	//creating a Boolean array and setting to FALSE
		Arrays.fill(present, Boolean.FALSE);
		present[0]=Boolean.TRUE;						//setting the 0th value to TRUE 
		int spy=0;
		for(int i=0;i<patchNumbers.length;i++)
		{
			present[patchNumbers[i]]=Boolean.TRUE;
		}
		//finding the index of the element that was not present in the patchNumbers array
		spy=java.util.Arrays.asList(present).indexOf(Boolean.FALSE);	 
		return spy;
	}
}
