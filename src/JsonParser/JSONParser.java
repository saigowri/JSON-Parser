package JsonParser;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class JSONParser {
	private static String file;
	
	public JSONParser(String filename) {
		file = filename;
	}
	
	public static void main(String[] args) throws IOException {
		JSONParser jp = new JSONParser("src/file.txt");
		jp.validate();
	}
	
	public void validate() throws IOException{
		FileReader fr = new FileReader(file);
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
			if (index+1 == jsonList.size()) {
				System.out.println("Valid JSON");
				JSONObject jsonObject = new JSONObject();
				Scanner sc = new Scanner(System.in);
				char ch = 'y'; 
				String key, value;
				while (ch != 'x' && ch != 'X') {
					System.out.println("Enter key for which value needed");
					key = sc.next();
					value = jsonObject.getObject(str, key);
					if(value.equals("-1")){
						System.out.println("Invalid key");
					}
					else {
						System.out.println("Key: "+key+"\n"+"Value: "+value);
					}
					System.out.println("Enter any character to get value for another key\n"
							+ "Enter X to exit");
					ch = sc.next().charAt(0);
				}
			}
			else
				System.out.println("Invalid JSON");
		} catch (ParserException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
