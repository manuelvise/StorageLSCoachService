package introsde.storage.model;

import introsde.storage.model.MeasureDefinition;

public class Measure {
	private String value;
	private MeasureDefinition measureDefinition;
	private Long timestamp;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public MeasureDefinition getMeasureDefinition() {
		return measureDefinition;
	}

	public void setMeasureDefinition(MeasureDefinition measureDefinition) {
		this.measureDefinition = measureDefinition;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

}
