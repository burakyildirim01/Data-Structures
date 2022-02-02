import java.util.Random;
import java.util.Arrays;

public class HW1 {
	// This is the method that returns the count of 'a' chars in the matrix
	// Feel free to add a helper method for the recursive part of the algorithm
	public static int acount(char[][] mat) {
		// Your code goes here
		int result = 0;
		int n = mat.length;
		for(int i=0;i<n;i++){
		    result += searchInRow(mat[i],0,n-1);
		}
		return result;
	}
	
	public static int searchInRow(char[] line,int l,int r) {
	    
	    if(r>l){
	        int m = (r-l)/2 +l;
	        if(line[m]=='a'){
	            return searchInRow(line,m+1,r);
		    }else {
		        return searchInRow(line,l,m);
		    }
	    }
	    
	   return line[r] == 'a'? r+1 : r ;
	    
	 
	}
	

	public static void main(String[] args) {
		// This method creates a test case for you
		int n = 5;
		Random rand = new Random();
		char[][] input = new char[n][n];
		for (int i = 0; i < n; i++) {
			int a_num = rand.nextInt(n);
			for (int j = 0; j < a_num; j++) {
				input[i][j] = 'a';
			}
			for (int j = a_num; j < n; j++) {
				input[i][j] = 'b';
			}
		}
		// Here you can see the matrix row by row 
		System.out.println(Arrays.deepToString(input));
		// Here you can see the result of your function
		System.out.println(acount(input));
	}

}
