
package introsde.wrapper.ws;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="fitnessActivities" type="{http://ws.wrapper.introsde/}measureActivity" maxOccurs="unbounded" minOccurs="0"/>
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

    protected List<MeasureActivity> fitnessActivities;

    /**
     * Gets the value of the fitnessActivities property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fitnessActivities property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFitnessActivities().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MeasureActivity }
     * 
     * 
     */
    public List<MeasureActivity> getFitnessActivities() {
        if (fitnessActivities == null) {
            fitnessActivities = new ArrayList<MeasureActivity>();
        }
        return this.fitnessActivities;
    }

}
