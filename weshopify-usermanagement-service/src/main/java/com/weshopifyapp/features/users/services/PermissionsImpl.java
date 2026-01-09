package com.weshopifyapp.features.users.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.weshopifyapp.features.users.beans.PermissionsBean;
import com.weshopifyapp.features.users.data.exceptions.PermissionsNotFoundException;
import com.weshopifyapp.features.users.data.models.Permissions;
import com.weshopifyapp.features.users.data.repository.PermissionsRepository;

import lombok.Data;

@Service
@Data
public class PermissionsImpl implements PermissionsService {

	private PermissionsRepository permissionsRepo;

	private ModelMapper modelMapper;

	public PermissionsImpl(PermissionsRepository permissionsRepo, ModelMapper modelMapper) {
		this.permissionsRepo = permissionsRepo;
		this.modelMapper = modelMapper;
	}

	@Override
	public PermissionsBean createPermission(PermissionsBean pbean) throws PermissionsNotFoundException {
		try {
			return mapEntityToBean(permissionsRepo.save(mapBeanToEntity(pbean)));
		} catch (Exception e) {
			throw PermissionsNotFoundException.builder().message(e.getLocalizedMessage()).build();
		}

	}

	@Override
	public PermissionsBean updatePermission(PermissionsBean pbean) throws PermissionsNotFoundException {
		try {
			return mapEntityToBean(permissionsRepo.save(mapBeanToEntity(pbean)));
		} catch (Exception e) {
			throw PermissionsNotFoundException.builder().message(e.getLocalizedMessage()).build();
		}
	}

	@Override
	public PermissionsBean findPermissionById(int permissionsId) throws PermissionsNotFoundException {
		Permissions permissions = permissionsRepo.findById(permissionsId).orElseThrow(() -> PermissionsNotFoundException
				.builder().message("No permissions Found with the Id:\t" + permissionsId).build());
		return mapEntityToBean(permissions);

	}

	// ******** I Implemented this logic***********//
//	@Override
//	public List<PermissionsBean> deletePermissionById(int permissionsId) {
//		List<Permissions> lisPermissions = permissionsRepo.findAll();
//		for(Permissions p :lisPermissions) {
//			if(p.getPermissionId() == permissionsId)
//				permissionsRepo.deleteById(p.getPermissionId());
//		}
//		
//		List<PermissionsBean> permissionsBeans = lisPermissions
//							 					.stream()
//							 					.map(l->modelMapper.map(l, PermissionsBean.class))
//							 					.collect(Collectors.toList());
//		return permissionsBeans;
//	}

	@Override
	public List<PermissionsBean> deletePermissionById(int permissionsId) {
		Optional.ofNullable(permissionsRepo.existsById(permissionsId))
				.ifPresentOrElse(i -> permissionsRepo.deleteById(permissionsId), () -> PermissionsNotFoundException
						.builder().message("No permissions Found with the Id:\t" + permissionsId).build());
		return getAllPermissions();

	}

	@Override
	public List<PermissionsBean> getAllPermissions() {
		List<PermissionsBean> liPermissionsBeans = new ArrayList<>();
		Optional.ofNullable(permissionsRepo.findAll()).ifPresentOrElse(
				(listOfPermissions -> listOfPermissions.stream()
						.forEach((permission) -> liPermissionsBeans.add(mapEntityToBean(permission)))),
				() -> PermissionsNotFoundException.builder().message("No permissions Found in The Database:-").build());

		return liPermissionsBeans;
	}

	@Override
	public List<PermissionsBean> searchPermissions() {
		// TODO Auto-generated method stub
		return null;
	}

	private Permissions mapBeanToEntity(PermissionsBean permissionsBean) {
		Permissions permissionsEntity = modelMapper.map(permissionsBean, Permissions.class);
		permissionsEntity.setCreatedBy("admin");
		permissionsEntity.setModifiedBy("admin");
		Date now = new Date();
		permissionsEntity.setCreatedDate(now);
		permissionsEntity.setModifiedDate(now);
		return permissionsEntity;
	}

	private PermissionsBean mapEntityToBean(Permissions permissionsEntity) {
		PermissionsBean permissionsBean = modelMapper.map(permissionsEntity, PermissionsBean.class);
		return permissionsBean;

	}

}
