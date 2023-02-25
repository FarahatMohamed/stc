package com.stc.asse.file.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "permissions")
public class PermissionEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    private String permission_level;
    @Column(name = "user_email")
    private String userEmail;
    @Column(name = "group_id")
    private Long permission_group_id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPermission_level() {
		return permission_level;
	}

	public void setPermission_level(String permission_level) {
		this.permission_level = permission_level;
	}

	public String getUser_email() {
		return userEmail;
	}

	public void setUser_email(String userEmail) {
		this.userEmail = userEmail;
	}

	public Long getPermission_group_id() {
		return permission_group_id;
	}

	public void setPermission_group_id(Long permission_group_id) {
		this.permission_group_id = permission_group_id;
	}

}
