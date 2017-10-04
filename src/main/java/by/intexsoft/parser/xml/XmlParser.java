package by.intexsoft.parser.xml;

import by.intexsoft.parser.Parser;
import by.intexsoft.parser.json.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Служит для построения дерева иерархий объектов JSON
 */
public class XmlParser implements Parser {

    /**Содержимое файла*/
    private String object;

    /**Хранилище для объектов типа {@link JsonObject <b>JsonObject</b>}*/
    private List<XmlObject> arrayList = new ArrayList<>();

    /**Вложенность объекта типа  {@link JsonObject <b>JsonObject</b>}*/
    private int chaches = 0;

    /**
     * Инициализирует свойство {@link JsonParser#object}
     * @param file путь к файлу
     * @see JsonParser
     */
    public XmlParser(String file) {
        try {
            this.object = readFile(file);
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    /**
     * Метод получения содержимого файла
     * @param file путь к файлу
     * @return String полное содержимое файла
     * @exception IOException <b>IOException</b>
     * @throws IOException {@link JsonParser#readFile(String) readFile(String)} стандартное исключение чтения данных
     */
    private String readFile(String file) throws IOException{
        StringBuffer temp = new StringBuffer();
        Files.lines(Paths.get(file), StandardCharsets.UTF_8).forEach(x->temp.append(x));
        return temp.toString().replaceAll("[\\s]{1,}","");
    }

    /**
     * Метод для получения главного объекта строки
     * @return строку с набором объектов без главного
     */
    public String getRootObject() {

        Matcher matcher = search(object);
        matcher.find();
        return matcher.group(3);

    }

    /**
     * Метод для создания поиска по заданному шаблону XML тега
     * @param object строка
     * @return объект {@link Matcher Macher}
     */
    private Matcher search(String object){
        Pattern pattern = Pattern.compile("<([a-zA-Z]+)([^>]+)*>(.*?)<\\/\\1>");
        return pattern.matcher(object);
    }

    /**
     * Метод для проверки является ли строка объектом типа {@link XmlField <b>XmlField</b>}
     * @param object строка
     * @return boolean
     */
    private boolean isField(String object){
        Matcher matcher = search(object);
        return !matcher.find();
    }

    /**
     * Ключевой метод построения дерева иерархии JSON объектов
     * @return {@link XmlDocument <b>XmlDocument</b>}
     */
    @Override
    public XmlDocument parse(){

        recursion(null,getRootObject());
        XmlArray listObjects = new XmlArray(arrayList);

        return new XmlDocument(listObjects);
    }

    /**
     * Рекурсивный мето для прохода в глубину иерархии
     * @param objectName название объекта
     * @param object его содержимое
     * @return List типа {@link XmlField <b>XmlField</b>}
     */
    private List recursion(String objectName, String object){

        Matcher matcher = search(object);
        List<XmlField> fields = new ArrayList<>();
        while (matcher.find() ){

            String nameObject = matcher.group(1);
            String valueObject = matcher.group(3);
            if(!isField(valueObject)){
                fields.add(new XmlField("",new XmlObject(nameObject, recursion(nameObject,valueObject))));
            }
            else {
                fields.add(new XmlField(nameObject,valueObject));
            }
        }

        if(objectName==null || ++chaches<2){
            return fields;
        }else {
            arrayList.add(new XmlObject(objectName, fields));
            return null;
        }
    }

}
