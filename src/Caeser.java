/**
 * The class which implements the Caeser cipher for alphabets only.
 * @author Asad zia
 * @Version 1.0
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Caeser implements Constants
{
	/**
	 * A naive implementation of the encryption algorithm for Caesers cipher.
	 * @param input
	 * @param key
	 * @return result
	 */
	public String encrypt (String input, int key)
	{
		String result = "";
		
		for (char x : input.toCharArray())
		{
			if (x == ' ')
			{
				result = result.concat(" ");
			}
			else if (Character.isUpperCase(x) && Character.isAlphabetic(x))
			{
				if ((((int)x - UPPER_ALPHABET_ASCII) + key) <= 25)
				{
					char answer = (char)((int)x + key);
					result = result.concat(Character.toString(answer));
				}
				else if ((((int)x - UPPER_ALPHABET_ASCII) + key) > 25)
				{
					int answer = ((int)x - UPPER_ALPHABET_ASCII) + key - 26;
					char ans = (char) (UPPER_ALPHABET_ASCII + answer);
					result = result.concat(Character.toString(ans));
				}
			}
			else if (Character.isLowerCase(x) && Character.isAlphabetic(x))
			{
				if ((((int)x - LOWER_ALPHABET_ASCII) + key) <= 25)
				{
					char answer = (char)((int)x + key);
					result = result.concat(Character.toString(answer));
				}
				else if ((((int)x - LOWER_ALPHABET_ASCII) + key) > 25)
				{	
					int answer = ((int)x - LOWER_ALPHABET_ASCII) + key - 26;
					char ans = (char) (LOWER_ALPHABET_ASCII + answer);
					result = result.concat(Character.toString(ans));
				}
			}
			else
			{
				result = result.concat(Character.toString(x));
			}
		}
		return result;
		
	}
	
	public String encryptFile(String fileName, int key)
	{
		String result = "";
		
		try 
		{
			String line = new String(Files.readAllBytes(Paths.get(fileName)));
			result = encrypt(line, key);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return result;
	}
	
	public String encryptWithTwoKeys (String input, int key_one, int key_two)
	{
		String result = "";
		int count = 0;
		
		for (char x : input.toCharArray())
		{
			if (count % 2 == 0)
			{
				result = result.concat(encrypt(Character.toString(x), key_one));
			}
			else if (count % 2 != 0)
			{
				result = result.concat(encrypt(Character.toString(x), key_two));
			}
			++count;
		}
		return result;
	}
	
	/**
	 * The driver method.
	 * @param args
	 */
	/*public static void main (String args[])
	{
		Caeser cc = new Caeser();
		System.out.println(cc.encrypt("Using longer sentences usually works!", 23));	
		System.out.println(cc.encryptFile("EXAMPLE.txt", 23));	
		System.out.println(cc.encryptWithTwoKeys("Hello. How are you? I am doing fine. How old are you?", 17, 23));	
	}*/
}
