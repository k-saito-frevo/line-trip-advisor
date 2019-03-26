package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown=true)
public class FaceDetail {
    public FaceDetail(){
    }
    @Getter
	public Attributes attributes;	
}
