package by.intexsoft.parser.xml;

import java.util.ArrayList;
import java.util.List;

/**
 * Представляет собой объект XML. У которого свойства
 * <b>objectName</b> и <b>fields</b>.
 */
public class XmlObject {

    /** имя объекта */
    private String objectName;
    /** поля объекта  */
    private List<XmlField> fields = new ArrayList<>();

    /**
     * Инициализирует свойства {@link XmlObject#objectName} и {@link XmlObject#fields}
     *
     * @param objectName {@link XmlObject#objectName}
     * @param fields     {@link XmlObject#fields}
     * @see XmlObject
     */
    public XmlObject(String objectName, List<XmlField> fields) {
        this.objectName = objectName;
        this.fields = fields;
    }

    /**
     * Метод для получения объекта типа {@link XmlField <b>XmlField</b>} по имени поля
     *
     * @param name имя поля
     * @return возвращает объект типа {@link XmlField <b>XmlField</b>}
     */
    public XmlField getFieldByTagName(String name){
        XmlField field = fields.stream().filter(xmlField -> xmlField.getFieldName().contains(name)).findFirst().orElse(null);
        if(field==null)throw new NullPointerException("Tag name is missing");
        return field;
    }

    /**
     * Метод для получения объекта типа {@link XmlField <b>XmlField</b>} по индексу
     *
     * @param index порядковый номер
     * @return возвращает объект типа {@link XmlField <b>XmlField</b>}
     */
    public XmlField getField(int index){
        return fields.get(index);
    }

    /**
     * Метод получения значения поля {@link XmlObject#fields fields}
     *
     * @return возвращает коллекцию объектов типа {@link XmlField <b>XmlField</b>}
     */
    public List<XmlField> getFields() {
        return fields;
    }

    /**
     * Метод получения значения поля {@link XmlObject#objectName objectName}
     *
     * @return возвращает имя объекта {@link XmlObject <b>XmlObject</b>}
     */
    public String getObjectName() {
        return objectName;
    }

    @Override
    public String toString() {
        return "XmlObject{" +
                "objectName='" + objectName + '\'' +
                ", fields=" + fields +
                '}';
    }


}
