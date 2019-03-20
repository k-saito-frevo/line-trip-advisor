package model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Attributes {
	public Attributes(){}
	@Getter
	public Map<String,Emotion> emotion;
	@Getter
	public Map<String,Beauty> beauty;
	@Getter
	public Map<String,Object> gender;
	@Getter
	public Map<String,Object> age;
}
