package by.intexsoft.parser.json;

import by.intexsoft.parser.Parser;

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
public class JsonParser implements Parser {

    /**Содержимое файла*/
    private String object;

    /**Хранилище для объектов типа {@link JsonObject <b>JsonObject</b>}*/
    private List<JsonObject> arrayList = new ArrayList<>();

    /**
     * Инициализирует свойство {@link JsonParser#object}
     * @param file путь к файлу
     * @see JsonParser
     */
    public JsonParser(String file) {
        try {
            this.object = readFile(file);
        } catch (IOException ex) {
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
    private String readFile(String file) throws IOException {
        StringBuffer temp = new StringBuffer();
        Files.lines(Paths.get(file), StandardCharsets.UTF_8).forEach(x -> temp.append(x));
        return temp.toString().replaceAll("[\\s]{2,}", "");
    }

    /**
     * Метод для проверки является ли строка объектом типа {@link JsonArray <b>JsonArray</b>}
     * @param object строка
     * @return boolean
     */
    private boolean isJsonArray(String object) {
        return object.matches("^\\[(.*)\\]$");
    }

    /**
     * Метод для проверки является ли строка объектом типа {@link JsonObject <b>JsonObject</b>}
     * @param object строка
     * @return boolean
     */
    private boolean isJsonObject(String object) {
        return object.matches("((^\\{(.*)\\}$)|(\"(.*)\"\\s*:\\s\\{(.*)\\}))");
    }

    /**
     * Метод для проверки является ли строка объектом типа {@link JsonField <b>JsonField</b>}
     * @param object строка
     * @return boolean
     */
    private boolean isJsonField(String object) {
        return object.matches("\"(.*)\"\\s*:\\s*(\"(.*)\")|((.*))");
    }

    /**
     * Метод для получения объектов которые предполагаются как {@link JsonArray <b>JsonArray</b>}
     * @param object строка
     * @return List
     */
    private List<String> getArrayObjects(String object) {

        Pattern pattern = Pattern.compile("\\[(\\{(.*?)\\})\\]");
        Matcher matcher = pattern.matcher(object);
        matcher.find();

        pattern = Pattern.compile("(\\{(.*?)\\}{1,})");
        matcher = pattern.matcher(matcher.group(1));

        List<String> array = new ArrayList<>();
        while (matcher.find()) {
            array.add(matcher.group(1));
        }
        if (array.isEmpty() && array.size() <= 1) return null;

        return array;
    }

    /**
     * Метод для получения объектов которые предполагаются как {@link JsonObject <b>JsonObject</b>}
     * @param object строка
     * @return List
     */
    private String getObject(String object) {

        Pattern pattern = Pattern.compile("(\\{(.*)\\})");
        Matcher matcher = pattern.matcher(object);
        matcher.find();
        return matcher.group(2);
    }

    /**
     * Метод для получения объектов которые предполагаются как {@link JsonField <b>JsonField</b>}
     * @param object строка
     * @return List
     */
    private List<String> getFieldObjects(String object) {
        ArrayList<String> fields = new ArrayList<>();
        Pattern pattern = Pattern.compile("(\"(.*?)\"\\s*:\\s*((\"(.*?)\")|(\\{(.*?)\\})|(\\[(.*?)\\])|[a-z1-9.]*))");
        Matcher matcher = pattern.matcher(object);
        while (matcher.find()) {
            fields.add(matcher.group(1));
        }

        return fields;
    }

    /**
     * Метод для получения объекта типа {@link JsonObject <b>JsonObject</b>}
     * @param object строка
     * @return JsonObject
     */
    private String getJsonObject(String object){

        Pattern pattern = Pattern.compile( "\"(.*)\"\\s*:\\s\\{(.*)\\}");
        Matcher matcher = pattern.matcher(object);
        matcher.find();

        return matcher.group(1);
    }

    /**
     * Метод для получения объекта типа {@link JsonField <b>JsonField</b>}
     * @param object строка
     * @return JsonField
     */
    private JsonField getJsonField(String object){

        Pattern pattern = Pattern.compile( "\"(.*)\"\\s*: ((.*)|(\"(.*)\"))");
        Matcher matcher = pattern.matcher(object);
        matcher.find();
        String objectName = matcher.group(1);
        Object value  = matcher.group(2);
        return new JsonField(objectName,value);
    }


    /**
     * Ключевой метод построения дерева иерархии JSON объектов
     * @return {@link JsonDocument <b>JsonDocument</b>}
     */
    @Override
    public JsonDocument parse() {
        if (isJsonObject(object)) {
            arrayList.add(new JsonObject("",recursion(object)));
        } else if (isJsonArray(object)) {
            List<String> objects = getArrayObjects(object);
            for (String s : objects) {
                arrayList.add(new JsonObject("",recursion(s)));
            }
        } else throw new Error("Invalid json file!");

        JsonArray listObjects = new JsonArray(arrayList);

        return new JsonDocument(listObjects);
    }

    /**
     * Рекурсивный мето для прохода в глубину иерархии
     * @param object строка объекта
     * @return List типа {@link JsonField <b>JsonField</b>}
     */
    private List recursion(String object) {

        String temp = getObject(object);
        if (isJsonField(temp)) {
            List<String> objectField = getFieldObjects(temp);
            List<JsonField> jsonFields = new ArrayList<>();
            for (String field : objectField) {

                if (isJsonObject(field)) {
                    JsonObject jsonObject = new JsonObject("",recursion(field));
                    jsonFields.add(new JsonField(getJsonObject(field),jsonObject));
                }else if(isJsonArray(field)){

                }else {
                    jsonFields.add(getJsonField(field));
                }
            }
            return jsonFields;
        }
        return null;

    }

}
