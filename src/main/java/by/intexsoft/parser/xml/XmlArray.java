package by.intexsoft.parser.xml;

import by.intexsoft.parser.json.JsonArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Представляет массив XML, упорядоченный набор значений XML.
 * <p>
 * Элементами массива явлются объекты
 * {@link XmlObject <b>XmlObject</b>}
 * </p>
 */
public class XmlArray {

    /**
     * Хранилище объектов XML
     */
    private List<XmlObject> array = new ArrayList<>();

    /**
     * Инициализирует свойства {@link JsonArray#array}
     *
     * @param array {@link XmlArray#array}
     * @see XmlArray
     */
    public XmlArray(List<XmlObject> array) {
        this.array = array;
    }

    /**
     * Метод для получения объекта типа {@link XmlObject <b>XmlObject</b>} по индексу
     *
     * @param index порядковый нормер
     * @return возвращает объект типа {@link XmlObject <b>XmlObject</b>}
     */
    public XmlObject get(int index){
        if(index>=array.size() || index<0) throw new IndexOutOfBoundsException();
        return array.get(index);
    }


    /**
     * Метод для получения количества объектов
     *
     * @return возвращает количество объектов {@link XmlObject <b>XmlObject</b>}
     */
    public int size(){
        return array.size();
    }

    @Override
    public String toString() {
        return "XmlArray{" +
                "array=" + array +
                '}';
    }
}
