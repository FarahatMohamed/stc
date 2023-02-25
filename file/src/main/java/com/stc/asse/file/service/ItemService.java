package com.stc.asse.file.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stc.asse.file.entity.ItemEntity;
import com.stc.asse.file.entity.PermissionEntity;
import com.stc.asse.file.repository.ItemRepository;
import com.stc.asse.file.repository.PermissionsRepository;


@Service
public class ItemService {
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private PermissionsRepository permissionsRepository;
	public void save(String spaceName,Long permissionGroupId) {
		ItemEntity itemEntity=new ItemEntity();
		itemEntity.setname(spaceName);
		itemEntity.setPermission_group_id(permissionGroupId);
		itemEntity.setType("Space");
		itemRepository.save(itemEntity);
	}
	@Transactional
	public ItemEntity saveFolder(String folderName,Long parentId,String user,String type) throws Exception {
		
		Optional<ItemEntity> itemSpace =itemRepository.findById(parentId);
		ItemEntity itemEntity=new ItemEntity();
		if(hasPermission(user, itemSpace.get().getPermission_group_id()))
		{
		
		itemEntity.setname(folderName);
		itemEntity.setPermission_group_id(itemSpace.get().getPermission_group_id());
		itemEntity.setname(folderName);
		itemEntity.setParent_id(parentId);
		itemEntity.setType(type);
		itemEntity=itemRepository.save(itemEntity);
		}
		else 
			throw new Exception ("Not Have Permissions");
		
		return itemEntity;
	}
	
	private Boolean hasPermission(String user,Long permission)
	{
		
		PermissionEntity permissionUser=permissionsRepository.findAllByUserEmail(user);
		return permissionUser !=null && permission.equals(permissionUser.getPermission_group_id())
				&&permissionUser.getPermission_level().equals("edit");
	}
}
