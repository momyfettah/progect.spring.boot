package net.java.springboot.springsecurity.web.dto;

import java.sql.Date;
import java.time.LocalDate;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Transient;
import org.springframework.web.multipart.MultipartFile;

import net.java.springboot.springsecurity.constraint.FieldMatch;

@FieldMatch.List({
    @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match"),
    @FieldMatch(first = "email", second = "confirmEmail", message = "The email fields must match")
})
public class UserRegistrationDto {

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;
    
    @NotEmpty
    private String birthday;

    @NotEmpty
    private String password;

    @NotEmpty
    private String confirmPassword;
    
    @Email
    @NotEmpty
    private String email;

    @Email
    @NotEmpty
    private String confirmEmail;
    
    @Transient
    private Date filedata;

    @AssertTrue
    private Boolean terms;

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
    
    public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
    
    public String getBirthday() {
		return birthday;
	}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmEmail() {
        return confirmEmail;
    }

    public void setConfirmEmail(String confirmEmail) {
        this.confirmEmail = confirmEmail;
    }
    
	public Date getFiledata() {
		return filedata;
	}
	
	public void setFileData(Date filedata) {
		this.filedata= filedata;
	}

    public Boolean getTerms() {
        return terms;
    }

    public void setTerms(Boolean terms) {
        this.terms = terms;
    }


}