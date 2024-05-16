package cat.institutmarianao.closetws.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="outfits")
@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Outfit {
	
	private static final String OUTFIT_DATE_PATTERN="dd/MM/yyyy HH:mm:ss";
	
	@EqualsAndHashCode.Include
	@Id
	@NotNull
	@NonNull
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@NonNull
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = OUTFIT_DATE_PATTERN, locale ="es_ES")
	@Column(name = "creation_date",nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = OUTFIT_DATE_PATTERN, locale ="es_ES")
	@Column(name = "modification_date",nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date modificationDate;
	
	@NotNull
	@NonNull
	@JoinColumn(nullable = false)
	@ManyToOne(fetch = FetchType.EAGER)
	protected User owner;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			  name = "outfits_clothes", 
			  joinColumns = @JoinColumn(name = "outfit_id"), 
			  inverseJoinColumns = @JoinColumn(name = "clothes_id"))
	private List<Clothes> clothes;

}
