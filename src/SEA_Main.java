import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Scanner;

import javax.xml.bind.DatatypeConverter;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;


public class SEA_Main {

	
	
	
	@SuppressWarnings("resource")
	public static void main(String [] args) {
		
		
		System.out.println("Would you like to encrypt [E] or decrypt [D] ? ");
		Scanner e_or_d_reader = new Scanner(System.in);
		String e_or_d = e_or_d_reader.next();
		e_or_d.toUpperCase();
		
		if (e_or_d == "D") {
			
			Decryption dd = new Decryption();
			dd.decrpt(null);
		}
		
		System.out.println("Enter a message you'd like to encrypt: ");
		Scanner input_reader = new Scanner(System.in);
		String input = input_reader.nextLine();
		
		
		Scanner sea_reader = new Scanner(System.in);
		System.out.println("Chose encryption level: 1.SEA_1  2.SEA_2  3.SEA_3 ");
		int SEA_choice = sea_reader.nextInt();
	
		
		encryption_variables ev = new encryption_variables();
		ev.seachoice(SEA_choice,input);
	}
}	
	class encryption_variables extends SEA_Main{
		
		BigInteger i = new BigInteger("1");
		BigInteger n = new BigInteger("7");
		BigInteger check = new BigInteger("2");
		BigInteger zero = new BigInteger("0");
		BigInteger prime;
		int x = 0;
	  
		public void seachoice (int SEA_choice, String input){
		if (SEA_choice== 1) {
			 prime = new BigInteger("1415198").pow(262144).add(BigInteger.ONE);
		}
		
		
		if (SEA_choice== 2) {
			 prime = new BigInteger("75898").pow(524288).add(BigInteger.ONE);
		}
		
		
		if (SEA_choice== 3) {
			 prime = check.pow(13466917).subtract(BigInteger.ONE);
		}
		
		encryptions e = new encryptions();
		
		e.encrypt(input,prime);
		
		Decryption dd = new Decryption();
		dd.decrpt(prime);
		
	}  
		
}
	
	
	class encryptions extends encryption_variables{
	
		public void encrypt(String input,BigInteger prime) {
		
			byte [] bytes = input.getBytes(Charset.forName("UTF-8"));
			
			while (x<bytes.length) {
				bytes[x] +=   prime.mod(n).intValue() ;
				x++;
				n = n.nextProbablePrime();
				
				
				}
			
			String output = new String(bytes,Charset.forName("UTF-8"));
			output = Base64.encode(bytes);
			System.out.println(output);
			}	
	
	}	
	
	class Decryption extends encryption_variables{
		
		public  void decrpt(BigInteger prime)  {
			
			String de ="eG5qgmYjgnJ4iJgoQnpWeWR1iEF3nK+As22cZqC+aOPtjG9ijZ2VciKflwxQeSeWlw6kivk+hA==" ;
			byte[] de2 = null;
			
			try {
				de2 = Base64.decode(de);
			} catch (Base64DecodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			while (x<de2.length) {
				de2[x] -=   prime.mod(n).intValue() ;
				x++;
				n = n.nextProbablePrime();
				}
			String deoutput = new String(de2,Charset.forName("UTF-8"));
			System.out.println(deoutput);
		}
		
	}
		
	
	
