
package introsde.wrapper.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the introsde.wrapper.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetFitnessActivitiesResponse_QNAME = new QName("http://ws.wrapper.introsde/", "getFitnessActivitiesResponse");
    private final static QName _GetMeasureHistoryFromWeightRKResponse_QNAME = new QName("http://ws.wrapper.introsde/", "getMeasureHistoryFromWeightRKResponse");
    private final static QName _GetMeasureHistoryFromWeightRK_QNAME = new QName("http://ws.wrapper.introsde/", "getMeasureHistoryFromWeightRK");
    private final static QName _GetFitnessActivities_QNAME = new QName("http://ws.wrapper.introsde/", "getFitnessActivities");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: introsde.wrapper.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetMeasureHistoryFromWeightRKResponse }
     * 
     */
    public GetMeasureHistoryFromWeightRKResponse createGetMeasureHistoryFromWeightRKResponse() {
        return new GetMeasureHistoryFromWeightRKResponse();
    }

    /**
     * Create an instance of {@link GetFitnessActivitiesResponse }
     * 
     */
    public GetFitnessActivitiesResponse createGetFitnessActivitiesResponse() {
        return new GetFitnessActivitiesResponse();
    }

    /**
     * Create an instance of {@link GetMeasureHistoryFromWeightRK }
     * 
     */
    public GetMeasureHistoryFromWeightRK createGetMeasureHistoryFromWeightRK() {
        return new GetMeasureHistoryFromWeightRK();
    }

    /**
     * Create an instance of {@link GetFitnessActivities }
     * 
     */
    public GetFitnessActivities createGetFitnessActivities() {
        return new GetFitnessActivities();
    }

    /**
     * Create an instance of {@link ItemActivity }
     * 
     */
    public ItemActivity createItemActivity() {
        return new ItemActivity();
    }

    /**
     * Create an instance of {@link Activities }
     * 
     */
    public Activities createActivities() {
        return new Activities();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFitnessActivitiesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.wrapper.introsde/", name = "getFitnessActivitiesResponse")
    public JAXBElement<GetFitnessActivitiesResponse> createGetFitnessActivitiesResponse(GetFitnessActivitiesResponse value) {
        return new JAXBElement<GetFitnessActivitiesResponse>(_GetFitnessActivitiesResponse_QNAME, GetFitnessActivitiesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMeasureHistoryFromWeightRKResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.wrapper.introsde/", name = "getMeasureHistoryFromWeightRKResponse")
    public JAXBElement<GetMeasureHistoryFromWeightRKResponse> createGetMeasureHistoryFromWeightRKResponse(GetMeasureHistoryFromWeightRKResponse value) {
        return new JAXBElement<GetMeasureHistoryFromWeightRKResponse>(_GetMeasureHistoryFromWeightRKResponse_QNAME, GetMeasureHistoryFromWeightRKResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMeasureHistoryFromWeightRK }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.wrapper.introsde/", name = "getMeasureHistoryFromWeightRK")
    public JAXBElement<GetMeasureHistoryFromWeightRK> createGetMeasureHistoryFromWeightRK(GetMeasureHistoryFromWeightRK value) {
        return new JAXBElement<GetMeasureHistoryFromWeightRK>(_GetMeasureHistoryFromWeightRK_QNAME, GetMeasureHistoryFromWeightRK.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFitnessActivities }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.wrapper.introsde/", name = "getFitnessActivities")
    public JAXBElement<GetFitnessActivities> createGetFitnessActivities(GetFitnessActivities value) {
        return new JAXBElement<GetFitnessActivities>(_GetFitnessActivities_QNAME, GetFitnessActivities.class, null, value);
    }

}
