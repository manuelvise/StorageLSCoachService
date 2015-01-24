
package introsde.wrapper.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getFitnessActivitiesResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getFitnessActivitiesResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fitnessActivities" type="{http://ws.wrapper.introsde/}activities" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getFitnessActivitiesResponse", propOrder = {
    "fitnessActivities"
})
public class GetFitnessActivitiesResponse {

    protected Activities fitnessActivities;

    /**
     * Gets the value of the fitnessActivities property.
     * 
     * @return
     *     possible object is
     *     {@link Activities }
     *     
     */
    public Activities getFitnessActivities() {
        return fitnessActivities;
    }

    /**
     * Sets the value of the fitnessActivities property.
     * 
     * @param value
     *     allowed object is
     *     {@link Activities }
     *     
     */
    public void setFitnessActivities(Activities value) {
        this.fitnessActivities = value;
    }

}
