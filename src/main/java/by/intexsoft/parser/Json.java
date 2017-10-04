package by.intexsoft.parser;

import by.intexsoft.parser.json.JsonParser;

public class Json implements TypeParser {
    @Override
    public Parser createObject(String filename) {
        return new JsonParser(filename);
    }
}
