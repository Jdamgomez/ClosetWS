package cat.institutmarianao.closetws.model;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import cat.institutmarianao.closetws.ClosetwsApplication;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="clothes")
@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Clothes {
	
	public enum Collection{
		WINTER,SPRING,SUMMER,AUTUMN
	}
	
	public enum Category{
		JACKET,SWEATER_SHIRT,PANTS_SKIRT,SHOES,UNDERWEAR,COMPLEMENT
	}

	@EqualsAndHashCode.Include
	@Id
	@NotNull
	@NonNull
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@NonNull
	private String name;
	
	@NotNull
	@NonNull
	private String color;
	
	@NotNull
	@NonNull
	private String size;
	
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(columnDefinition = "BLOB")
	private byte[] picture;
	
	@NotNull
	@NonNull
	@Column(nullable = false, insertable = false, updatable = false)
	@Enumerated(EnumType.STRING)
	private Collection collection;
	
	@NotNull
	@NonNull
	@Column(nullable = false, insertable = false, updatable = false)
	@Enumerated(EnumType.STRING)
	private Category category;
	
	@NotNull
	@JoinColumn(nullable = false)
	@ManyToOne
	private Container container;
	
	@NonNull
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ClosetwsApplication.DATE_PATTERN, locale ="es_ES")
	@Column(name = "last_use",nullable = false)
	@Temporal(TemporalType.DATE)
	private Date lastUse;
	
	@ManyToMany(mappedBy = "clothes", cascade = {CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Outfit> outfits;
	
	
	@Transient
	@JsonIgnore
	public String getBase64Image() {
		return Base64.getEncoder().encodeToString(picture);
	}
	@Transient
	@JsonIgnore
	public void setBase64Image(String b64Image) {
		this.picture=Base64.getDecoder().decode(b64Image);
	}
}
