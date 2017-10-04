package by.intexsoft.parser;

import by.intexsoft.parser.json.JsonParser;
import by.intexsoft.parser.xml.XmlParser;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by developer on 14.07.17.
 */
public class BuilderParser implements Parser {

    private String contentFile;
    private String filename;

    public BuilderParser(String filename) {
        this.filename = filename;
        try{
            contentFile = readFile(filename);
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }



    @Override
    public Object parse() {
        if(isFileJson()){
            return new JsonParser(filename).parse();
        }
        else if(isFileXml()){
            return new XmlParser(filename).parse();
        }
        throw new Error("Unsupported type file");
    }

    private String readFile(String file) throws IOException{
        StringBuffer temp = new StringBuffer();
        Files.lines(Paths.get(file), StandardCharsets.UTF_8).forEach(x->temp.append(x));
        return temp.toString().replaceAll("[\\s]{1,}","");
    }

    private boolean isFileJson(){
        return contentFile.matches("(^\\{(.*)\\})|(^\\[(.*)\\])");
    }

    private boolean isFileXml(){
        return contentFile.matches("^<([a-zA-Z0-9]+)([^>]+)*>(.*?)<\\/\\1>");
    }

}
