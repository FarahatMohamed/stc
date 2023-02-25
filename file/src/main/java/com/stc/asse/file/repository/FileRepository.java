package com.stc.asse.file.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.stc.asse.file.entity.FileEntity;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Long>{

	@Query(value = "select f.id, f.content_type,1 \"data\" ,f.name, f.size, f.item_id FROM item ,files f where item.id=f.item_id and type ='File' and permission_group_id =?1", nativeQuery = true)
	List<FileEntity> findAllByPermissions(Long permission);
}
