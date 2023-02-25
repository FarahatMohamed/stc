package com.stc.asse.file.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.stc.asse.file.entity.FileEntity;
import com.stc.asse.file.entity.FileMetaDataEntity;
import com.stc.asse.file.repository.FileMetaDataRepository;
import com.stc.asse.file.repository.FileRepository;


@Service
public class FileService {
	@Autowired
    private FileRepository fileRepository;
	@Autowired
    private FileMetaDataRepository fileMetaDataRepository;

   

    public void save(MultipartFile file,Long itemId) throws IOException {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setName(StringUtils.cleanPath(file.getOriginalFilename()));
        fileEntity.setContentType(file.getContentType());
        fileEntity.setData(file.getBytes());
        fileEntity.setSize(file.getSize());
        fileEntity.setItem_id(itemId);

        fileRepository.save(fileEntity);
    }

    public Optional<FileEntity> getFile(Long id) {
        return fileRepository.findById(id);
    }

    public List<FileMetaDataEntity> getAllFiles(Long permissionGroupId) {

        return fileMetaDataRepository.findAllByPermissions(permissionGroupId);
//        return fileRepository.findAll();
    }
}