package project1_sample;

public class primeno {
	
     public static void main(String[] args) {
	        System.out.println("Prime numbers up to 500 are:");

	        for (int i = 2; i <= 500; i++) {
	            int c=0;

	            for (int j = 2; j <= Math.sqrt(i); j++) {
	                if (i % j == 0) {
	                    c++;
	                   break;
	                }
	            }

	            if (c==0)
	                System.out.print(i + " ");
	        }
	    }
	}



