package by.intexsoft.parser.json;

import java.util.ArrayList;
import java.util.List;

/**
 * Представляет собой объект JSON. У которого свойства
 * <b>objectName</b> и <b>fields</b>.
 */
public class JsonObject {

    /** имя объекта */
    private String objectName;
    /** поля объекта  */
    private List<JsonField> fields = new ArrayList<>();

    /**
     * Инициализирует свойства {@link JsonObject#objectName} и {@link JsonObject#fields}
     *
     * @param objectName {@link JsonObject#objectName}
     * @param fields     {@link JsonObject#fields}
     * @see JsonObject
     */
    public JsonObject(String objectName, List<JsonField> fields) {
        this.objectName = objectName;
        this.fields = fields;
    }

    /**
     * Метод для получения объекта типа {@link JsonField <b>JsonField</b>} по имени поля
     *
     * @param name имя поля
     * @return возвращает объект типа {@link JsonField <b>JsonField</b>}
     */
    public JsonField getFieldByTagName(String name){
        JsonField field = fields.stream().filter(xmlField -> xmlField.getFieldName().contains(name)).findFirst().orElse(null);
        if(field==null)throw new NullPointerException("Tag name is missing");
        return field;
    }

    /**
     * Метод для получения объекта типа {@link JsonField <b>JsonField</b>} по индексу
     *
     * @param index порядковый номер
     * @return возвращает объект типа {@link JsonField <b>JsonField</b>}
     */
    public JsonField getField(int index){
        return fields.get(index);
    }

    /**
     * Метод получения значения поля {@link JsonObject#fields fields}
     *
     * @return возвращает коллекцию объектов типа {@link JsonField <b>JsonField</b>}
     */
    public List<JsonField> getFields() {
        return fields;
    }

    /**
     * Метод получения значения поля {@link JsonObject#objectName objectName}
     *
     * @return возвращает имя объекта {@link JsonObject <b>JsonObject</b>}
     */
    public String getObjectName() {
        return objectName;
    }

    @Override
    public String toString() {
        return "JsonObject{" +
                "objectName='" + objectName + '\'' +
                ", fields=" + fields +
                '}';
    }


}
