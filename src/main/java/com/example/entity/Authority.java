package com.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "authority")
public class Authority {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
  
    @Column(name = "authority", nullable = false)
    private String authority;
    
    @Column(name = "username", nullable = false)
    private String username;


	public Authority() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

    
}
