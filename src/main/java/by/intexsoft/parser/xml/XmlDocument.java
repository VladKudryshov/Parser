package by.intexsoft.parser.xml;



/**
 * Представляет собой главный объект или же оболочку для XML
 */
public class XmlDocument {

    /**
     * Хранилище объекта JSON  {@link XmlArray <b>XmlArray</b>}
     */
    private XmlArray listObjects;

    /**
     * Инициализирует свойства {@link XmlDocument#listObjects}
     * @param listObjects {@link XmlDocument#listObjects}
     * @see XmlDocument
     */
    public XmlDocument(XmlArray listObjects) {
        this.listObjects = listObjects;
    }

    /**
     * Метод получения значения поля {@link XmlDocument#listObjects listObjects}
     *
     * @return {@link XmlArray <b>XmlArray</b>}
     */
    public XmlArray getArrayObjects(){
        return listObjects;
    }

    @Override
    public String toString() {
        return "XmlDocument{" +
                "listObjects=" + listObjects +
                '}';
    }
}
