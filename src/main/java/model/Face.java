package model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Face {
    public Face(){
    }

	@Getter
	public String image_id;
	@Getter
	public String request_id;
	@Getter
	public String time_used;
	@Getter
	public List<FaceDetail> faces;
	
	
}
