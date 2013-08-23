package com.transgrid.mib.ellipse.hr.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
/**
 * 
 * @author Anil Kanike
 *
 */
public class EllipseTrainingCourse {

	private String id = "";
	private String name = "";
	private String desc = "";
	private String status = "";
	private String deliveryMethod = "";
	private String deliveryMethodDesc = "";
	private String type = "";
	private String typeDesc = "";
	private BigDecimal requalMonths;
	private String requalInd = "";
	private List<TrainerType> trainer;
	private List<TrainerType> assessor;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDeliveryMethod() {
		return deliveryMethod;
	}

	public void setDeliveryMethod(String deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}

	public String getDeliveryMethodDesc() {
		return deliveryMethodDesc;
	}

	public void setDeliveryMethodDesc(String deliveryMethodDesc) {
		this.deliveryMethodDesc = deliveryMethodDesc;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	public BigDecimal getRequalMonths() {
		return requalMonths;
	}

	public void setRequalMonths(BigDecimal requalMonths) {
		this.requalMonths = requalMonths;
	}

	public String getRequalInd() {
		return requalInd;
	}

	public void setRequalInd(String requalInd) {
		this.requalInd = requalInd;
	}

	public List<TrainerType> getTrainer() {
		if (trainer == null) {
			trainer = new ArrayList<TrainerType>();
		}
		return trainer;
	}

	public void setTrainer(TrainerType trainerType) {
		if (trainer == null) {
			trainer = new ArrayList<TrainerType>();
		}
		this.trainer.add(trainerType);
	}

	public List<TrainerType> getAssessor() {
		if (assessor == null) {
			assessor = new ArrayList<TrainerType>();
		}
		return assessor;
	}

	public void setAssessor(TrainerType trainerType) {
		if (assessor == null) {
			assessor = new ArrayList<TrainerType>();
		}
		this.assessor.add(trainerType);
	}

	@Override
	public String toString() {

		return ToStringBuilder.reflectionToString(this);
	}

}
