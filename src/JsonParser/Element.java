package JsonParser;
import java.util.ArrayList;

// Element -> "Alphanum" : Value | "Alphanum" : Value, Element

class Element {
	int matchElement(ArrayList<Character> jsonString, int index) throws ParserException{
		if(jsonString.get(index)!='"'){
			throw new ParserException("Missing beginning \" at location "+index);
		}
		index++;
		
		Terminal t = new Terminal();
		index = t.matchAlphanum(jsonString, index);
		
		if(jsonString.get(index)!=':'){
			throw new ParserException("Missing : for key:value pair at location "+index);
		}
		index++;
		//System.out.println(index + " " + jsonString.get(index));
		
		Value v = new Value();
		index = v.matchValue(jsonString, index);
		
		if(jsonString.get(index)==','){
			index++;
			index = matchElement(jsonString, index);
		}
		
		return index;
	}
}
