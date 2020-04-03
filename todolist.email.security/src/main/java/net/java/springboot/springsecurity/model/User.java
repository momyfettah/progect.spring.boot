package net.java.springboot.springsecurity.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
	private LocalDate birthday;
	;
	
	// Upload files. 
	@Lob
	private byte[] image;
	

	private String typeImage;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection < Role > roles;
    
    
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "users_activities",joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "activity_id"))
    private List<Activity> activities;

    
    
    public User() {}

    public User(String firstName, String lastName, String email, String password, LocalDate birthday, byte[] image, String typeImage) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.birthday= birthday;
        this.image= image;
        this.typeImage=typeImage;
    }
    
    /*

    public User(String firstName, String lastName, String email, String password,  LocalDate birthday, byte[] image , String typeImage, Collection < Role > roles, List<Activity> activities) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.birthday= birthday;
        this.image= image;
        this.typeImage=typeImage;
        
        this.roles=roles;
        this.activities=activities;
        
    }
    */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday( LocalDate birthday) {
		this.birthday = birthday;
	}
	
	public byte[] getImage() {
		return image;
	}
	
	public void setImage(byte[] image) {
		this.image=image;
	}
	
	public String getTypeImage() {
		return typeImage;
	}

	public void setTypeImage(String typeImage) {
		this.typeImage = typeImage;
	}

    public Collection < Role > getRoles() {
        return roles;
    }

    public void setRoles(Collection < Role > roles) {
        this.roles = roles;
    }

    public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}	
    
    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", birthday='" + birthday + '\'' +
            ", password='" + "*********" + '\'' +
            ", roles=" + roles +
            '}';
    }
}