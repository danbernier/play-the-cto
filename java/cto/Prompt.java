package cto;

import java.io.*;

/**
 * @author BernierD
 */
public class Prompt
{
	public static String ask (String question) throws IOException
	{
		System.out.print(question);
		System.out.flush();

		BufferedReader keys = new BufferedReader(new InputStreamReader(System.in));
		return keys.readLine();
	}

	public static void pause()
	{
		try {
			BufferedReader keys = new BufferedReader(new InputStreamReader(System.in));
			keys.readLine();
		}
		catch (IOException iox) {
			;
		}
	}
}
