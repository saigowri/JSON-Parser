# JSON Parser

Simple JSON Validator and Parser created using JSON grammar

## Implementation covers :

1. Validation of the input JSON
2. Raising appropriate exceptions and printing error messages to describe the error and the index location it occurred at.
3. Parsing the valid JSON file to return a JSON Object.


## Methods used for validation :

* *matchStart()* - to ensure presence of open and closing curly braces ( { , } )
* *matchElement()* 
	1. to ensure key is string 
	2. to make sure key and value are separated by colon ( : )
	3. to ensure multiple elements are separated by comma ( , )
* *matchValue()* - to cover all possible value cases ( [Array], {"Key":Value}, Constants, String )
* *matchArray()* - to check array syntax and all values that can be present inside an area ( All valid values and array within array s)
* *matchAlphanum()* and *matchConstants()* - to take care of all terminals ( String, Integer, Float, Boolean, Null )

NOTE: *ParserException Class* created to raise user defined exceptions

## Parsing and returning object :

Once JSON is validated, user is asked to enter the "key" for which he wants value returned.
getObject() method return the value for a particular "key".


