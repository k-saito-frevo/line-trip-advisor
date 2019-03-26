package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Beauty {
	public Beauty(){}
	@Getter
	public int female_score;
	@Getter
	public int male_score;
}
