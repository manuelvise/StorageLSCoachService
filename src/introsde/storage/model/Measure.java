package introsde.storage.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import introsde.storage.model.MeasureDefinition;


public class Measure implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5426858282319981486L;
	
	private String value;
	private MeasureDefinition measureDefinition;
	private String timestamp;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@XmlElement (name = "mDefinition", type=MeasureDefinition.class)
	public MeasureDefinition getMeasureDefinition() {
		return measureDefinition;
	}

	public void setMeasureDefinition(MeasureDefinition measureDefinition) {
		this.measureDefinition = measureDefinition;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

}
