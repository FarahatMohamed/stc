package com.stc.asse.file.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "item")
public class ItemEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;

	    private String type;

	    private String name;

	    private Long permission_group_id;
	    
	    private Long parent_id;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getname() {
			return name;
		}

		public void setname(String name) {
			this.name = name;
		}

		public Long getPermission_group_id() {
			return permission_group_id;
		}

		public void setPermission_group_id(Long permission_group_id) {
			this.permission_group_id = permission_group_id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Long getParent_id() {
			return parent_id;
		}

		public void setParent_id(Long parent_id) {
			this.parent_id = parent_id;
		}
}
