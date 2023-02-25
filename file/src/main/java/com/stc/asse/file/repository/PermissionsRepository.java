package com.stc.asse.file.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stc.asse.file.entity.PermissionEntity;

@Repository
public interface PermissionsRepository extends JpaRepository<PermissionEntity, Long>{
public PermissionEntity findAllByUserEmail(String userEmail);
}
