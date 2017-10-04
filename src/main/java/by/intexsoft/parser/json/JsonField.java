package by.intexsoft.parser.json;

/**
 * Представляет собой поле объекта JSON. У которого свойства
 * <b>fieldName</b> и <b>value</b>.
 */
public class JsonField {
    /**Имя поля*/
    private String fieldName;
    /**Значение поля*/
    private Object value;

    /**
     * Инициализирует свойства {@link JsonField#fieldName} и {@link JsonField#value}
     * @param fieldName {@link JsonField#fieldName}
     * @param value     {@link JsonField#value}
     * @see JsonField
     */
    public JsonField(String fieldName, Object value) {
        this.fieldName = fieldName;
        this.value = value;
    }

    /**
     * Метод получения значения поля {@link JsonField#fieldName fieldName}
     *
     * @return String
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * Метод получения значения поля {@link JsonField#value value}
     *
     * @return Object
     */
    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "JsonField{" +
                "fieldName='" + fieldName + '\'' +
                ", value=" + value +
                '}';
    }
}
