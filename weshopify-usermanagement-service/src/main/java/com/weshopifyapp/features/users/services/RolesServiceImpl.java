package com.weshopifyapp.features.users.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.weshopifyapp.features.users.beans.PermissionsBean;
import com.weshopifyapp.features.users.beans.RolesBean;
import com.weshopifyapp.features.users.data.exceptions.RolesNotFoundException;
import com.weshopifyapp.features.users.data.models.Permissions;
import com.weshopifyapp.features.users.data.models.RoleToPermisions;
import com.weshopifyapp.features.users.data.models.Roles;
import com.weshopifyapp.features.users.data.repository.PermissionsRepository;
import com.weshopifyapp.features.users.data.repository.RolesRepository;

@Service
public class RolesServiceImpl implements RolesService {

	private RolesRepository rolesRepository;
	private ModelMapper modelMapper;
	PermissionsRepository permissionsRepository;
	
	public RolesServiceImpl(RolesRepository rolesRepository, ModelMapper modelMapper,
			PermissionsRepository permissionsRepository) {
		this.rolesRepository = rolesRepository;
		this.modelMapper = modelMapper;
		this.permissionsRepository = permissionsRepository;
	}

//My Logic----
//	@Override
//	public RolesBean createRole(RolesBean rbean) throws RolesNotFoundException {
//	  
//		AtomicReference<RolesBean> roles = new AtomicReference<>(null);
//		Optional.ofNullable(rbean).ifPresentOrElse((i) -> {
//			Roles role= rolesRepository.save(mapRolesBeanToRoles(rbean));
//			roles.set(modelMapper.map(role, RolesBean.class));
//		}, () -> RolesNotFoundException.builder().message("Roles has not created:t/").build());
//
//		return roles.get();
//	}
	
	

	@Override
	public RolesBean createRoles(RolesBean rbean) throws RolesNotFoundException {
		Roles roles = Optional.ofNullable(rolesRepository.save(mapRolesBeanToRoles(rbean)))
							  .orElseThrow(()-> RolesNotFoundException
									  			.builder()
									  			.message("Roles creation failed")
									  			.build());
		return mapRolesToRolesBean(roles);
	}

	@Override
	public RolesBean updateRoles(RolesBean rbean) throws RolesNotFoundException {
		Roles roles = Optional.ofNullable(rolesRepository.save(mapRolesBeanToRoles(rbean)))
				  			  .orElseThrow(()-> RolesNotFoundException
						  			             .builder()
						  			             .message("Roles creation failed")
						  			             .build());
		return mapRolesToRolesBean(roles);
	}

	@Override
	public RolesBean findRoleById(int rolesId) throws RolesNotFoundException {
		Roles roles = Optional.ofNullable(rolesRepository.findById(rolesId))
										   .orElseThrow(()-> RolesNotFoundException
												   				.builder()
												   				.message("No roles present:t/")
												   				.build()).get();
		return mapRolesToRolesBean(roles);
	}

	@Override
	public List<RolesBean> deleteRoleById(int rolesId) throws RolesNotFoundException {
		 Optional.ofNullable(rolesRepository.findById(rolesId))
				.ifPresentOrElse((i)-> rolesRepository.deleteById(rolesId),
						         ()-> RolesNotFoundException.builder().message("Given roles is not present:t/").build());		 
		return getAllRoles();
	}

	@Override
	public List<RolesBean> getAllRoles() throws RolesNotFoundException {
		List<RolesBean> rolesBeans = new ArrayList<>();
		List<Roles> roles = Optional.ofNullable(rolesRepository.findAll())
		                                   .orElseThrow(()-> RolesNotFoundException
		                                		            .builder()
		                                		            .message("No roles present:t/")
		                              		                .build());
		
	    if(Optional.of(roles).isPresent()) {
	    	roles.stream().forEach((i)-> rolesBeans.add(mapRolesToRolesBean(i)));
	    }
		
		return rolesBeans;
	}

	@Override
	public List<RolesBean> searchRoles() {
		// TODO Auto-generated method stub
		return null;
	}

	private Roles mapRolesBeanToRoles(RolesBean rolesBean) {
		List<RoleToPermisions> list = new ArrayList<>();
		Roles roles = modelMapper.map(rolesBean, Roles.class);
		roles.setCreatedBy("admin");
		roles.setModifiedBy("admin");
		Date now = new Date();
		roles.setCreatedDate(now);
		roles.setModifiedDate(now);
		//*****
		if(rolesBean.getPermissions() != null && rolesBean.getPermissions().size() > 0) {
			RoleToPermisions roleToPermisions = new RoleToPermisions();
			rolesBean.getPermissions().stream().forEach((roleBean)->{
			Permissions permissions = permissionsRepository.findById(roleBean.getPermissionId()).get();	
			roleToPermisions.setPermissions(permissions);
			roleToPermisions.setRoles(roles);
			list.add(roleToPermisions);	
			});
		}		
		roles.setRole_to_permissions(list);
		return roles;
	}

	private RolesBean mapRolesToRolesBean(Roles roles) {
		List<PermissionsBean> list = new ArrayList<>();
		RolesBean rolesBean = modelMapper.map(roles, RolesBean.class);
		Optional.ofNullable(roles.getRole_to_permissions()).get()
		.stream()
		.forEach((roleToPermission)->list.add(modelMapper.map(roleToPermission.getPermissions(), PermissionsBean.class)));
		rolesBean.setPermissions(list);
		return rolesBean;
	}

}

//
//if(rolesBean.getPermissions() != null && rolesBean.getPermissions().size() > 0) {
//having null as one of the elements in the list doesn't mean that the list object itself is null
//List<Permission> list = null; we can say this list is pointing to null.

//Difference between List<Permission> list = null; List<Permission> list = new ArrayList<>(); 
//When an object refernce is not ponting to any object bydefault it will ponted to null.
//When an object refecne is poiting to any object that is new Permission() then it is empty but
// not refering to null.
//}
