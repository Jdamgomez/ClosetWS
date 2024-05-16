package cat.institutmarianao.closetws.model;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import cat.institutmarianao.closetws.ClosetwsApplication;
import cat.institutmarianao.closetws.PasswordSerializer;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {
	
	public static final int MIN_USERNAME = 4;
	public static final int MAX_USERNAME = 25;
	public static final int MIN_PASSWORD = 4;
	public static final int MAX_PHONE=15;
	public static final int MIN_FULL_NAME = 3;
	public static final int MAX_FULL_NAME = 100;
	

	@Id
	@NonNull
	@NotNull
	@Size(min = MIN_USERNAME, max = MAX_USERNAME)
	@Column(nullable = false)
	private String username;
	
	@NonNull
	@NotNull
	@Size(min = MIN_PASSWORD)
	@Column(nullable = false)
	@JsonSerialize(using = PasswordSerializer.class)
	private String password;
	
	@NonNull
	@NotNull
	@Column(nullable = false, unique = true)
	@Email
	private String mail;
	
	@Column(unique = true)
	@Size(max = MAX_PHONE)
	private String phone;
	
	@NotNull
	@NonNull
	@Size(min = MIN_FULL_NAME, max = MAX_FULL_NAME)
	@Column(name = "full_name",nullable = false)
	private String fullName;
	
	
	@NonNull
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ClosetwsApplication.DATE_PATTERN, locale ="es_ES")
	@Column(name = "birth_date",nullable = false)
	@Temporal(TemporalType.DATE)
	private Date birthDate;
	
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "profile_picture", columnDefinition = "BLOB")
	private byte[] profilePicture;
	
	@OneToMany(mappedBy = "owner", cascade = {CascadeType.REMOVE}, fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Container> containers;
	
	@OneToMany(mappedBy = "owner", cascade = {CascadeType.REMOVE}, fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Outfit> outfits;

	@Transient
	@JsonIgnore
	public String getBase64Image() {
		return Base64.getEncoder().encodeToString(profilePicture);
	}
	@Transient
	@JsonIgnore
	public void setBase64Image(String b64Image) {
		this.profilePicture=Base64.getDecoder().decode(b64Image);
	}
}
