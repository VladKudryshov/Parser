package by.intexsoft.parser.xml;

/**
 * Представляет собой поле объекта XML. У которого свойства
 * <b>fieldName</b> и <b>value</b>.
 */
public class XmlField {

    /**Имя поля*/
    private String fieldName;
    /**Значение поля*/
    private Object value;

    /**
     * Инициализирует свойства {@link XmlField#fieldName} и {@link XmlField#value}
     * @param fieldName {@link XmlField#fieldName}
     * @param value     {@link XmlField#value}
     * @see XmlField
     */
    public XmlField(String fieldName, Object value) {
        this.fieldName = fieldName;
        this.value = value;
    }

    /**
     * Метод получения значения поля {@link XmlField#fieldName fieldName}
     *
     * @return String
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * Метод получения значения поля {@link XmlField#value value}
     *
     * @return Object
     */
    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "XmlField{" +
                "fieldName='" + fieldName + '\'' +
                ", value=" + value +
                '}';
    }
}
