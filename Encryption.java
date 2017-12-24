import java.math.BigInteger;
import java.util.Random;
import java.io.File;
import java.io.FileWriter;

public class Encryption  {
	 
	
	 
	public static void main(String args[]) {
	
		 int intp = Generator();
		 int intq = Generator();
		 int inte = Generator();
		 		
		 BigInteger p = BigInteger.valueOf(intp);
		 BigInteger q = BigInteger.valueOf(intq);
		 BigInteger n = p.multiply(q);
		 BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		 BigInteger e = BigInteger.valueOf(inte);
		 BigInteger d = e.modInverse(phi);
		 
		 /*System.out.println(p);
		 System.out.println(q);
		 System.out.println(e);
		 System.out.println(d);*/
		 
		
		 String message = "It's a test";
		 int length = message.length();
		 int ASCIImessage[] = new int[length];
		 
		 
		 char charmessage[] = message.toCharArray();
		 System.out.println("ASCII Message");
		 for (int i = 0; i < length; i++){
			 ASCIImessage[i] = (int) charmessage[i];
			 System.out.print(String.valueOf(ASCIImessage[i]));
		 }
		 
		 String messagecoder = "";
		 String messagecode[] = new String[length];
		 System.out.println("");
		 System.out.println("Encryted Message");
		 for (int i=0; i< length; i++){
			 BigInteger code = BigInteger.valueOf(ASCIImessage[i]);
			 messagecode[i] = Integer.toString(code.modPow(e, n).intValue());
			 while(messagecode[i].length()<8){
					messagecode[i]="0"+messagecode[i];
				}
			 System.out.print(String.valueOf(messagecode[i]));
			 messagecoder += messagecode[i];
		 }
		 
		 
		 System.out.println("");
		 System.out.println("Decrypted Message");
		 String messagedecoder = "";
		 int ASCIId[]= convertStringToInt(messagecoder);
		 int ASCIIdecode[]=new int[length];
		 for (int i=0; i<length; i++){
			 BigInteger decode = BigInteger.valueOf(ASCIId[i]);
			 ASCIIdecode[i] = decode.modPow(d, n).intValue();
			 messagedecoder += (char) ASCIIdecode[i];
		 }
		 System.out.print(messagedecoder); 

		 
		 try{
			 File file = new File("D:\\Prog\\Java\\File_Encryption\\Encryption.txt");
			 file.createNewFile();
			 FileWriter filewrite = new FileWriter(file);
			 filewrite.write(String.valueOf(messagecoder));
			 filewrite.write("\r\n");
			 filewrite.write(String.valueOf(messagedecoder));
			 filewrite.write("\r\n");
			 filewrite.close();
		 }
		 catch (Exception e1) {}
	
		 
	 }
	
	public static int Generator() {
        int num = 0;
        Random rand = new Random(); // generate a random number
        num = rand.nextInt(9999) + 1;

        while (!isPrime(num)) {          
            num = rand.nextInt(9999) + 1;
        }
        return num;
    }

    /**
     * Checks to see if the requested value is prime.
     */
    private static boolean isPrime(int inputNum){
        if (inputNum <= 3 || inputNum % 2 == 0) 
            return inputNum == 2 || inputNum == 3; //this returns false if number is <=1 & true if number = 2 or 3
        int divisor = 3;
        while ((divisor <= Math.sqrt(inputNum)) && (inputNum % divisor != 0)) 
            divisor += 2; //iterates through all possible divisors
        return inputNum % divisor != 0; //returns true/false
    }
    
    private static int[]convertStringToInt (String from){
		int to[]= new int [from.length()/8];
		int j=0;
		int k=0;
		String tmp="";
		for(int i=0;i<from.length();i++){
			if(j<8){
				
				tmp = tmp+from.charAt(i);
				j++;
			}
			if(j>=8){
				
				j=0;
				to[k]=Integer.parseInt(tmp);
				tmp="";
				k++;
			}
		}
		
		
		//
		return to;
		
	}
	
}


//A faire : génération des paramètres (done) et modePow (to do)
// ajouter les 0 avant, fixer la taille à 8 (done)