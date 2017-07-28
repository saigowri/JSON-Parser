package JsonParser;

import java.util.ArrayList;

//Array -> Value(, Value)* | Value, Array

class ArrayClass {
	int matchArray(ArrayList<Character> jsonString, int index) throws ParserException {
		if (jsonString.get(index) != ']') {
			Value v = new Value();
			index = v.matchValue(jsonString, index);
			while (jsonString.get(index) != ']') {
				if (jsonString.get(index) != ',') {
					throw new ParserException("Missing , or ] in array at location "+index);
				}
				index++;
				if (index >= jsonString.size()) {
					throw new ParserException("Missing ] for array at location "+index);
				}
				{
					Value v1 = new Value();
					index = v1.matchValue(jsonString, index);
				}
			}

		}
		if (jsonString.get(index) != ']') {
			throw new ParserException("Missing ] for array at location "+index);
		}
		index++;
		return index;
	}
}
