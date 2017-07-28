package JsonParser;
import java.util.ArrayList;

// Start -> { } | { Element }

class Start {
	int matchStart(ArrayList<Character> jsonString, int index) throws ParserException{
		if(jsonString.get(index)!='{'){
			throw new ParserException("Missing { at location "+index);
		}
		index++;
		if(jsonString.get(index)!='}'){
			Element e = new Element();
			index = e.matchElement(jsonString, index);
		}
		if(jsonString.get(index)!='}'){
			throw new ParserException("Missing } at location "+index);
		}
		return index;
	}
}
