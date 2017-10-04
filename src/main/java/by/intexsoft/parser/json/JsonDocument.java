package by.intexsoft.parser.json;

/**
 * Представляет собой главный объект или же оболочку для JSON
 */
public class JsonDocument {

    /**
     * Хранилище объекта JSON  {@link JsonArray <b>JsonArray</b>}
     */
    private JsonArray listObjects;

    /**
     * Инициализирует свойства {@link JsonDocument#listObjects}
     * @param listObjects {@link JsonDocument#listObjects}
     * @see JsonDocument
     */
    public JsonDocument(JsonArray listObjects) {
        this.listObjects = listObjects;
    }

    /**
     * Метод получения значения поля {@link JsonDocument#listObjects listObjects}
     *
     * @return {@link JsonArray <b>JsonArray</b>}
     */
    public JsonArray getListObjects() {
        return listObjects;
    }

    @Override
    public String toString() {
        return "JsonDocument{" +
                "listObjects=" + listObjects +
                '}';
    }
}
