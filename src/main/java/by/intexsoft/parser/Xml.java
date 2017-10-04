package by.intexsoft.parser;

import by.intexsoft.parser.xml.XmlParser;

public class Xml implements TypeParser {
    @Override
    public Parser createObject(String filename) {
        return new XmlParser(filename);
    }
}
