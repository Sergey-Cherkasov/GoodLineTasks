package com.svcdev;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Task2
{

// Предусмотреть, что может быть несколько пробелов подряд. 
	
	public static void main(String[] args) throws FileNotFoundException
	{
		File readFile = new File("data/strings.txt");
		int i = 0;
		int y = 0;
		String str;

		Scanner s = new Scanner(readFile);
		str = s.nextLine().trim();

		while (i < str.length())
		{
			if (str.charAt(i) == ' ')
				y++;
			i++;
		}

		System.out.println("Количество слов в строке: " + ++y);

		s.close();
	
	}
}
