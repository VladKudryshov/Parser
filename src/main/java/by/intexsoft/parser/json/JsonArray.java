package by.intexsoft.parser.json;

import java.util.ArrayList;
import java.util.List;

/**
 * Представляет массив JSON, упорядоченный набор значений JSON.
 * <p>
 * Элементами массива явлются объекты
 * {@link JsonObject <b>JsonObject</b>}
 * </p>
 */
public class JsonArray {

    /**
     * Хранилище объектов JSON
     */
    private List<JsonObject> array = new ArrayList<>();

    /**
     * Инициализирует свойства {@link JsonArray#array}
     *
     * @param array {@link JsonArray#array}
     * @see JsonArray
     */
    public JsonArray(List<JsonObject> array) {
        this.array = array;
    }

    /**
     * Метод для получения объекта типа {@link JsonObject <b>JsonObject</b>} по индексу
     *
     * @param index порядковый нормер
     * @return возвращает объект типа {@link JsonObject <b>JsonObject</b>}
     */
    public JsonObject get(int index) {
        if (index >= array.size() && index < 0) throw new IndexOutOfBoundsException();
        return array.get(index);
    }

    /**
     * Метод для получения количества объектов
     *
     * @return возвращает количество объектов {@link JsonObject <b>JsonObject</b>}
     */
    public int size() {
        return array.size();
    }

    @Override
    public String toString() {
        return "JsonArray{" +
                "array=" + array +
                '}';
    }
}
