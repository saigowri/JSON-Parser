package JsonParser;

import java.util.ArrayList;

// Value -> "Alphanum" | [ Array ] | { Element } | Constant

class Value {
	int matchValue(ArrayList<Character> jsonString, int index) throws ParserException {
		switch (jsonString.get(index)) {
		case '{':
			Start s = new Start();
			index = s.matchStart(jsonString, index);
			if (jsonString.get(index) != '}') {
				throw new ParserException("Missing } at location "+index);
			}
			index++;
			break;
		case '[':
			index++;
			ArrayClass a = new ArrayClass();
			index = a.matchArray(jsonString, index);
			break;
		case '"':
			index++;
			Terminal t1 = new Terminal();
			index = t1.matchAlphanum(jsonString, index);
			break;
		default:
			Terminal t2 = new Terminal();
			index = t2.matchConstant(jsonString, index);
			break;
		}
		return index;
	}
}
