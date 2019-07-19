import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;

public class Main {
  public static String getXMLData() {
    return "<a><person  number='' dept=''><name>myName</name></person></a>";
  }

  public static void main(String[] argv) throws Exception {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    Document doc = factory.newDocumentBuilder().parse(
        new InputSource(new StringReader(getXMLData())));

    findByID(doc,"uniqueID");

    System.out.println(documentToString(doc));

  }
  public static void findByID(Document doc,String idName) {
    Element name = doc.getElementById(idName);
    if(name == null) {
        System.out.println("There is no element with the ID "
                + idName);
    } else {
        Text text = (Text)name.getFirstChild();
        System.out.println("The ID " + idName
                + " locates the name " + text.getData());
    }
  }


  public static String documentToString(Document document) {
    try {
      TransformerFactory tf = TransformerFactory.newInstance();
      Transformer trans = tf.newTransformer();
      StringWriter sw = new StringWriter();
      trans.transform(new DOMSource(document), new StreamResult(sw));
      return sw.toString();
    } catch (TransformerException tEx) {
      tEx.printStackTrace();
    }
    return null;
  }
}