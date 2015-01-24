
package introsde.wrapper.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for itemActivity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="itemActivity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="duration" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="entry_mode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="has_path" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="source" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="start_time" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="total_calories" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="total_distance" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="uri" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="utc_offset" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "itemActivity", propOrder = {
    "duration",
    "entryMode",
    "hasPath",
    "source",
    "startTime",
    "totalCalories",
    "totalDistance",
    "type",
    "uri",
    "utcOffset"
})
public class ItemActivity {

    protected double duration;
    @XmlElement(name = "entry_mode")
    protected String entryMode;
    @XmlElement(name = "has_path")
    protected Boolean hasPath;
    protected String source;
    @XmlElement(name = "start_time")
    protected String startTime;
    @XmlElement(name = "total_calories")
    protected double totalCalories;
    @XmlElement(name = "total_distance")
    protected double totalDistance;
    protected String type;
    protected String uri;
    @XmlElement(name = "utc_offset")
    protected int utcOffset;

    /**
     * Gets the value of the duration property.
     * 
     */
    public double getDuration() {
        return duration;
    }

    /**
     * Sets the value of the duration property.
     * 
     */
    public void setDuration(double value) {
        this.duration = value;
    }

    /**
     * Gets the value of the entryMode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntryMode() {
        return entryMode;
    }

    /**
     * Sets the value of the entryMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntryMode(String value) {
        this.entryMode = value;
    }

    /**
     * Gets the value of the hasPath property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isHasPath() {
        return hasPath;
    }

    /**
     * Sets the value of the hasPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHasPath(Boolean value) {
        this.hasPath = value;
    }

    /**
     * Gets the value of the source property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSource() {
        return source;
    }

    /**
     * Sets the value of the source property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSource(String value) {
        this.source = value;
    }

    /**
     * Gets the value of the startTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Sets the value of the startTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartTime(String value) {
        this.startTime = value;
    }

    /**
     * Gets the value of the totalCalories property.
     * 
     */
    public double getTotalCalories() {
        return totalCalories;
    }

    /**
     * Sets the value of the totalCalories property.
     * 
     */
    public void setTotalCalories(double value) {
        this.totalCalories = value;
    }

    /**
     * Gets the value of the totalDistance property.
     * 
     */
    public double getTotalDistance() {
        return totalDistance;
    }

    /**
     * Sets the value of the totalDistance property.
     * 
     */
    public void setTotalDistance(double value) {
        this.totalDistance = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the uri property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUri() {
        return uri;
    }

    /**
     * Sets the value of the uri property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUri(String value) {
        this.uri = value;
    }

    /**
     * Gets the value of the utcOffset property.
     * 
     */
    public int getUtcOffset() {
        return utcOffset;
    }

    /**
     * Sets the value of the utcOffset property.
     * 
     */
    public void setUtcOffset(int value) {
        this.utcOffset = value;
    }

}
