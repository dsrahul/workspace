package com.marketplace.offer.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

@Entity(name="offer")
@Table(name = "TOFFER")
public class OfferDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "TITLE", nullable=false)
	private String title;
	
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "TYPE_ID", nullable=false)
	private Long typeId;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "TYPE_ID", nullable = false, insertable = false, updatable = false)
	private OfferTypeDTO offerType;
	
	
	@Column(name = "CATEGORY_ID", nullable=false)
	private Long categoryId;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "CATEGORY_ID", nullable = false, insertable = false, updatable = false)
	private CategoryDTO categoryDTO;

	@Column(name = "MERCHANT_ID", nullable=false)
	private Long merchantId;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "MERCHANT_ID", nullable = false, insertable = false, updatable = false)
	private MerchantDTO merchantDTO;

	@Column(name = "VALID_FROM")
	@JsonDeserialize(using=LocalDateDeserializer.class)
	@JsonSerialize(using=LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate validFrom;

	@Column(name = "VALID_TO")
	@JsonDeserialize(using=LocalDateDeserializer.class)
	@JsonSerialize(using=LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate validTo;
	
	
	@Column(name = "DELETED")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private String deleted;

	public OfferDTO() {
	}

	public OfferDTO(String title, String description, Long typeId, Long merchantId, Long categoryId, LocalDate validFrom, LocalDate validTo, String deleted) {
		super();
		this.title = title;
		this.description = description;
		this.typeId = typeId;
		this.merchantId = merchantId;
		this.categoryId = categoryId;
		this.validFrom = validFrom;
		this.validTo = validTo;
		this.deleted = deleted;
	}

	public OfferDTO(Long id, String title, String description, Long typeId, Long categoryId, Long merchantId, LocalDate validFrom,
			LocalDate validTo, String active) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.typeId = typeId;
		this.merchantId = merchantId;
		this.categoryId = categoryId;
		this.validFrom = validFrom;
		this.validTo = validTo;
		this.deleted = active;
	}

	public OfferDTO(Long id, String title, String description, Long typeId, OfferTypeDTO offerType, Long categoryId,
			CategoryDTO categoryDTO, Long merchantId, MerchantDTO merchantDTO, LocalDate validFrom, LocalDate validTo, String deleted) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.typeId = typeId;
		this.offerType = offerType;
		this.categoryId = categoryId;
		this.categoryDTO = categoryDTO;
		this.merchantId = merchantId;
		this.merchantDTO = merchantDTO;
		this.validFrom = validFrom;
		this.validTo = validTo;
		this.deleted = deleted;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public OfferTypeDTO getOfferType() {
		return offerType;
	}

	public void setOfferType(OfferTypeDTO offerType) {
		this.offerType = offerType;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public CategoryDTO getCategoryDTO() {
		return categoryDTO;
	}

	public void setCategoryDTO(CategoryDTO categoryDTO) {
		this.categoryDTO = categoryDTO;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public MerchantDTO getMerchantDTO() {
		return merchantDTO;
	}

	public void setMerchantDTO(MerchantDTO merchantDTO) {
		this.merchantDTO = merchantDTO;
	}

	public LocalDate getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(LocalDate validFrom) {
		this.validFrom = validFrom;
	}

	public LocalDate getValidTo() {
		return validTo;
	}

	public void setValidTo(LocalDate validTo) {
		this.validTo = validTo;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	
	
	/*@Override
	public String toString() {
		return "OfferDTO [id=" + id + ", typeId=" + typeId + ", categoryId=" + categoryId + ", merchantId=" + merchantId
				+ ", deleted=" + deleted + "]";
	}*/
	
	

}
