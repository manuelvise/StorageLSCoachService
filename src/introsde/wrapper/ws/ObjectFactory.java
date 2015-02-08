
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
    private final static QName _GetUserId_QNAME = new QName("http://ws.wrapper.introsde/", "getUserId");
    private final static QName _GetUser_QNAME = new QName("http://ws.wrapper.introsde/", "getUser");
    private final static QName _GetMeasureHistoryFromWeightRKResponse_QNAME = new QName("http://ws.wrapper.introsde/", "getMeasureHistoryFromWeightRKResponse");
    private final static QName _GetMeasureHistoryFromWeightRK_QNAME = new QName("http://ws.wrapper.introsde/", "getMeasureHistoryFromWeightRK");
    private final static QName _GetFitnessActivities_QNAME = new QName("http://ws.wrapper.introsde/", "getFitnessActivities");
    private final static QName _GetUserIdResponse_QNAME = new QName("http://ws.wrapper.introsde/", "getUserIdResponse");
    private final static QName _GetUserResponse_QNAME = new QName("http://ws.wrapper.introsde/", "getUserResponse");

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
     * Create an instance of {@link GetUser }
     * 
     */
    public GetUser createGetUser() {
        return new GetUser();
    }

    /**
     * Create an instance of {@link GetUserId }
     * 
     */
    public GetUserId createGetUserId() {
        return new GetUserId();
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
     * Create an instance of {@link GetUserResponse }
     * 
     */
    public GetUserResponse createGetUserResponse() {
        return new GetUserResponse();
    }

    /**
     * Create an instance of {@link GetUserIdResponse }
     * 
     */
    public GetUserIdResponse createGetUserIdResponse() {
        return new GetUserIdResponse();
    }

    /**
     * Create an instance of {@link GetFitnessActivities }
     * 
     */
    public GetFitnessActivities createGetFitnessActivities() {
        return new GetFitnessActivities();
    }

    /**
     * Create an instance of {@link MeasureWeight }
     * 
     */
    public MeasureWeight createMeasureWeight() {
        return new MeasureWeight();
    }

    /**
     * Create an instance of {@link MeasureActivity }
     * 
     */
    public MeasureActivity createMeasureActivity() {
        return new MeasureActivity();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.wrapper.introsde/", name = "getUserId")
    public JAXBElement<GetUserId> createGetUserId(GetUserId value) {
        return new JAXBElement<GetUserId>(_GetUserId_QNAME, GetUserId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.wrapper.introsde/", name = "getUser")
    public JAXBElement<GetUser> createGetUser(GetUser value) {
        return new JAXBElement<GetUser>(_GetUser_QNAME, GetUser.class, null, value);
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

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.wrapper.introsde/", name = "getUserIdResponse")
    public JAXBElement<GetUserIdResponse> createGetUserIdResponse(GetUserIdResponse value) {
        return new JAXBElement<GetUserIdResponse>(_GetUserIdResponse_QNAME, GetUserIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.wrapper.introsde/", name = "getUserResponse")
    public JAXBElement<GetUserResponse> createGetUserResponse(GetUserResponse value) {
        return new JAXBElement<GetUserResponse>(_GetUserResponse_QNAME, GetUserResponse.class, null, value);
    }

}
