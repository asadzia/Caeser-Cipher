/**
 *  The class for decrypting a caeser cipher.
 * @author Asad Zia
 * @Version 1.0
 */

import java.util.Scanner;

public class CaeserDecrypter implements Constants
{
	public static int[] countLetters (String input)
	{
		int[] alphabet_array = new int[26];
		
		for (char x : input.toCharArray())
		{
			if (Character.isAlphabetic(x))
			{
				int index = 0;
				char y = Character.toUpperCase(x);
				
				for (char w : ALPHABETS.toCharArray())
				{
					if (w == y)
					{
						break;
					}
					++index;
				}
				
				++alphabet_array[index];
			}
		}
		/*for (int x = 0; x < alphabet_array.length; ++x)
		{
			System.out.print(alphabet_array[x] + " ");
		}*/
		return alphabet_array;
	}
	
	/**
	 * The method for finding the maximum index for frequency analysis of statement.
	 * @param arr
	 * @return
	 */
	public static int maxIndex (int[] arr)
	{
		int max = arr[0];
		int max_index = 0;
		
		for (int i = 1; i < arr.length; ++i)
		{
			if (max < arr[i])
			{
				max = arr[i];
				max_index = i;
			}
		}
		return max_index;
	}
	
	/**
	 * The decryption algorithm which uses the brute force AND the frequency analysis technique.
	 * @param input
	 */
	public String decrypt (String input)
	{
		Caeser myCipher = new Caeser();
		String answer = "";
				
		/* METHOD 1: FREQUENCY ANALYSIS */
		
		int[] letterCount = countLetters(input);	
		int index_max = maxIndex(letterCount);
		
		/* assuming that the letter 'e' has the highest count */
		int key = index_max - 4;
		
		if (index_max < 4)
		{
			/* wrap around the alphabet */
			key = 26 - (4 - index_max);
		}
		/** TODO
		 * Works well with large samples. Fix for larger samples.
		 */
		//System.out.println("Key " + key + " max_index: " + index_max);
		System.out.println(myCipher.encrypt(input, 26 - key));
		
		/* Is it correct? */
		System.out.println("\nIs this a logical decryption? Yes/No");
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		
		while (true)
		{
			answer = scanner.nextLine();
			answer = answer.toLowerCase();
			
			if (answer.equals("yes") || answer.equals("no"))
			{
				break;
			}
			System.out.println("Please enter either 'yes' or 'no'");
		}
		
		if (answer.equals("no"))
		{
			/* METHOD 1: GET ALL POSSIBLE COMBINATIONS */
			
			System.out.println("Choose the most readable and logical decrypted statement manually:\n");
			for (int i = 0; i <= 25; ++i)
			{
				System.out.println("Decrypted STATEMENT " + i + ": " + myCipher.encrypt(input, i));
			}
			
			System.out.println("\nWhich statement seems like the proper decrypted message? Write down the statement number.");
			
			answer = scanner.nextLine();
			
			return ("The decrypted message is: " + myCipher.encrypt(input, Integer.parseInt(answer)));
		}
		else
		{
			return ("The decrypted message is: " + myCipher.encrypt(input, 26 - key));
		}
	}
	
	/**
	 * A helper method for decrypting a message which has 2 keys used on it.
	 * @param input
	 * @return
	 */
	public String decryptWithTwoKeys_helper (String input)
	{
		Caeser myCipher = new Caeser();
				
		/* METHOD 1: FREQUENCY ANALYSIS */
		
		int[] letterCount = countLetters(input);	
		int index_max = maxIndex(letterCount);
		
		/* assuming that the letter 'e' has the highest count */
		int key = index_max - 4;
		
		if (index_max < 4)
		{
			/* wrap around the alphabet */
			key = 26 - (4 - index_max);
		}
		/** TODO
		 * Works well with large samples. Fix for larger samples. 
		 */
		
		return (myCipher.encrypt(input, 26 - key));
	}
	
	/**
	 * A method for decrypting a message which was encrypted with two keys.
	 * @param input
	 * @return
	 */
	@SuppressWarnings("resource")
	public String decryptWithTwokeys (String input)
	{
		int index = 0;
		String string_one = "";
		String string_two = "";
		String answer = "";
		CaeserDecrypter cc = new CaeserDecrypter();
		Caeser myCipher = new Caeser();
		
		/* FREQUENCY ANALYSIS METHOD */
		
		for (char x : input.toCharArray())
		{
			if (index % 2 == 0)
			{
				string_one = string_one.concat(Character.toString(x));
			}
			else
			{
				string_two = string_two.concat(Character.toString(x));
			}
			
			++index;
		}
		
		String one = cc.decryptWithTwoKeys_helper(string_one);
		String two = cc.decryptWithTwoKeys_helper(string_two);
		
		String result = "";
		index = 0;
		int odd = 0;
		int even = 0;
		
		for (int i = 0; i < input.length(); ++i)
		{
			if (index % 2 == 0)
			{
				result = result.concat(Character.toString(one.charAt(even)));
				++even;
			}
			else
			{
				result = result.concat(Character.toString(two.charAt(odd)));
				++odd;
			}
			++index;
		}
		
		/* Is it correct? */
		System.out.println("The decrypted message is " + result);
		System.out.println("\nIs this a logical decryption? Yes/No");
		
		Scanner scanner = new Scanner(System.in);
		
		while (true)
		{
			answer = scanner.nextLine();
			answer = answer.toLowerCase();
			
			if (answer.equals("yes") || answer.equals("no"))
			{
				break;
			}
			System.out.println("Please enter either 'yes' or 'no'");
		}
		
		if (answer.equals("no"))
		{
			/* USE BRUTE FORCE METHOD TO DISPLAY ALL COMBINATIONS */
			
			System.out.println("Choose the most readable and logical decrypted statement manually:\n");
			
			/* delay the program for a bit */
			try 
			{
			    Thread.sleep(2000);
			} 
			catch(InterruptedException ex)
			{
			    Thread.currentThread().interrupt();
			}
			
			for (int i = 0; i <= 25; ++i)
			{
				for (int j = 0; j <= 25; ++j)
				{
					System.out.println("Decrypted STATEMENT " + j + ": " + myCipher.encryptWithTwoKeys(input, i, j));
				}
				
				System.out.println("\nDoes any one of the above statement look valid? Yes/No");
				
				Scanner scan = new Scanner(System.in);
				
				while (true)
				{
					answer = scanner.nextLine();
					answer = answer.toLowerCase();
					
					if (answer.equals("yes") || answer.equals("no"))
					{
						break;
					}
					System.out.println("Please enter either 'yes' or 'no'");
				}
				
				if (answer.toLowerCase().equals("yes"))
				{
					System.out.println("\nWhich statement number is it?");
					answer = scan.nextLine();
					return (myCipher.encryptWithTwoKeys(input, i, Integer.parseInt(answer)));
				}
				else
				{
					System.out.println("\n");
					continue;
				}
			}	
		}
		else
		{
			return result;
		}
		return "";
	}
	
	/**
	 * the driver method
	 * @param args
	 */
	public static void main (String args [])
	{
		CaeserDecrypter cc = new CaeserDecrypter();
		System.out.println("Result: " + cc.decryptWithTwokeys("Ybcif. Eft xib vfr? Z rj affed czkv. Eft lca xib vfr?"));
	}
}