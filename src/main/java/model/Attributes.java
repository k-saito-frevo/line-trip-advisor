package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Attributes {
	public Attributes(){}
	@Getter
	public Emotion emotion;
	@Getter
	public Beauty beauty;
	@Getter
	public ValueSet gender;
	@Getter
	public ValueSet age;
}
