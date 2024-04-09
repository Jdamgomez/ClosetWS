package cat.institutmarianao.closetws.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="closets")
@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Container {
	
	public enum Type{
		CLOSET,SUITCASE
	}

	@EqualsAndHashCode.Include
	@Id
	@NotNull
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	protected String name;
	
	protected Type type;
	
	@NotNull
	@JoinColumn(nullable = false)
	@ManyToOne
	protected User user;
	
	@OneToMany(mappedBy = "container",cascade = CascadeType.ALL)
	@JsonManagedReference
	protected List<Clothes> clothes;
}
