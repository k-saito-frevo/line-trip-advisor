package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Emotion {
	public Emotion() {}
	@Getter
	public float sadness;
	@Getter
	public float neutral;
	@Getter
	public float disgust;
	@Getter
	public float anger;
	@Getter
	public float surprise;
	@Getter
	public float fear;
	@Getter
	public float happiness;
}
