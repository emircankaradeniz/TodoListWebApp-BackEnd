package com.example.todoWebApplication.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.todoWebApplication.model.AnaGorev;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "ourusers")
@Data
public class OurUsers implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String name;
    private String password;
    private String city;
    private String role;
    
    @Lob // Avatar için uzun veri saklama desteği
    private String avatar;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<AnaGorev> gorevler;

    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password; // Eksik olan metot burada tanımlanıyor
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

	public void setEmail(String email2) {
		this.email=email2;
		
	}

	public void setCity(String city2) {
		this.city=city2;
		
	}

	public void setRole(String role2) {
		this.role=role2;
		
	}

	public void setName(String name2) {
		this.name=name2;
		
	}

	public void setPassword(String encode) {
		this.password=encode;
		
	}

	public int getId() {
		
		return this.id;
	}

	public String getRole() {
		return this.role;
	}

	public String getEmail() {
		return this.email;
	}

	public String getName() {
		return this.name;
	}

	public String getCity() {
		return this.city;
	}

	public Object getAvatar() {
		// TODO Auto-generated method stub
		return this.avatar;
	}

	public void setAvatar(Object object) {
		this.avatar=(String) object;
		
	}
}
