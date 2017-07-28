package JsonParser;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class JSONParser {
	public static void main(String[] args) throws IOException {
		FileReader fr = new FileReader("src/file.txt");
		int i;
		char c;
		String str = "";
		ArrayList<Character> jsonList = new ArrayList<Character>();
		while ((i = fr.read()) != -1) {
			c = (char) i;
			if (!Character.isWhitespace(c)) {
				// System.out.print(c);
				str += c;
				jsonList.add(c);
			}
		}
		System.out.println(str);
		fr.close();
		try {
			Start s = new Start();
			int index = s.matchStart(jsonList, 0);
			if (index+1 == jsonList.size())
				System.out.println("Valid JSON");
			else
				System.out.println("Invalid JSON");
		} catch (ParserException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
