package JsonParser;

import java.util.ArrayList;

// Terminals are string characters(alphanumerics, special char) and constants (number, true, false, null) 

class Terminal {
	int matchAlphanum(ArrayList<Character> jsonString, int index) throws ParserException {
		char token = jsonString.get(index);
		while (token != '"') {
			if (token == '\\') {
				index++;
			}
			index++;
			if (index >= jsonString.size()) {
				throw new ParserException("Missing closing \" at location "+index);
			}
			token = jsonString.get(index);
		}
		index++;
		return index;
	}

	int matchConstant(ArrayList<Character> jsonString, int index) throws ParserException {
		String str = "";
		int in;
		switch (jsonString.get(index)) {
		case 't':
			in = index;
			for (int i = 0; i < 4; i++) {
				str += jsonString.get(in);
				in++;
			}
			if (!str.equals("true")) {
				throw new ParserException(
						"Expecting 'String', 'Number', 'null', 'true', 'false', '{', '[', got 'undefined'");
			}
			index += 4;
			break;
		case 'f':
			in = index;
			for (int i = 0; i < 5; i++) {
				str += jsonString.get(in);
				in++;
			}
			if (!str.equals("false")) {
				throw new ParserException(
						"Expecting 'String', 'Number', 'null', 'true', 'false', '{', '[', got 'undefined'");
			}
			index += 5;
			break;
		case 'n':
			in = index;
			for (int i = 0; i < 4; i++) {
				str += jsonString.get(in);
				in++;
			}
			if (!str.equals("null")) {
				throw new ParserException(
						"Expecting 'String', 'Number', 'null', 'true', 'false', '{', '[', got 'undefined'");
			}
			index += 4;
			break;
		case '-':
			index++;
		case '0': index++;
				  if(Character.isDigit(jsonString.get(index))){
					  throw new ParserException("Cannot preceed digit by 0");
				  }
				  if(jsonString.get(index)=='.'){
					  do{
						  index++;
					  }while(Character.isDigit(jsonString.get(index)));
				  }
		case '1':
		case '2':
		case '3':
		case '4':
		case '5':
		case '6':
		case '7':
		case '8':
		case '9':
			int count = 0;
			while (Character.isDigit(jsonString.get(index))) {
				index++;
				if (jsonString.get(index) == '.') {
					if (count == 0 && Character.isDigit(jsonString.get(index+1))) {
						index++;
						count++;
					} else {
						throw new ParserException("Invalid use of decimal point");
					}
				}
			}
			break;
		default:
			throw new ParserException(
					"Expecting 'String', 'Number', 'null', 'true', 'false', '{', '[', got 'undefined'");
		}
		return index;
	}
}
