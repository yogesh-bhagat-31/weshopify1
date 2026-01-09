package com.weshopifyapp.features.users.data.models;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleToPermisions implements Serializable {
	
	private static final long serialVersionUID = 8658109771083486086L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne // ManyRoleToPermissons belongs to one Role 
	private Roles roles;//Bahut saari RoleToPermissions(Different records having unique PK) rows same role_id ko refer kar sakti hain
	
	@ManyToOne //Many RoleToPermissions(Different records having unique PK) belongs to one Permission.
	private Permissions permissions;

}

//Ab table dikhao 👀
//id | role_id | permission_id
//--------------------------------
//1  | 1       | 1   (ADMIN → VIEW)
//2  | 1       | 2   (ADMIN → DELETE)
//3  | 1       | 3   (ADMIN → UPDATE)
//4  | 2       | 1   (EDITOR → VIEW)
//
//8️⃣ Ab relation clearly dikhega 🔥
//ADMIN role:
//Row 1
//Row 2
//Row 3
//👉 Many RoleToPermissions → ONE Role

//VIEW permission:
//Row 1
//Row 4
//👉 Many RoleToPermissions → ONE Permission

//💡 THIS IS EXACTLY WHAT @ManyToOne MEANS
