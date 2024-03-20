package cat.institutmarianao.closetws.model;

import java.sql.Blob;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {
	
	private String username;
	private String mail;
	private String password;
	private String fullName;
	private Date birthDate;
	private Blob profilePicture;
}
