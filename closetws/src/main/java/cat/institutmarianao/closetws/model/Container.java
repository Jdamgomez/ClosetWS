package cat.institutmarianao.closetws.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="closets")
@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type", visible = true)
@JsonSubTypes({ @JsonSubTypes.Type(value = Closet.class, name = Container.CLOSET),
		@JsonSubTypes.Type(value = Suitcase.class, name = Container.SUITCASE)})
public abstract class Container {
	
	public static final String CLOSET="CLOSET";
	public static final String SUITCASE="SUITCASE";
	
	public enum Type{
		CLOSET,SUITCASE
	}

	@EqualsAndHashCode.Include
	@Id
	@NotNull
	@NonNull
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	protected String name;
	
	@NotNull
	@NonNull
	@Column(nullable = false, insertable = false, updatable = false)
	@Enumerated(EnumType.STRING)
	protected Type type;
	
	@NotNull
	@NonNull
	@JoinColumn(nullable = false, name = "owner")
	@ManyToOne(fetch = FetchType.EAGER)
	protected User user;
	
	@OneToMany(mappedBy = "container",cascade = CascadeType.ALL)
	@JsonManagedReference
	protected List<Clothes> clothes;
}
