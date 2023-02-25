package com.stc.asse.file.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.stc.asse.file.entity.FileEntity;
import com.stc.asse.file.entity.FileMetaDataEntity;
import com.stc.asse.file.entity.FileResponse;
import com.stc.asse.file.entity.ItemEntity;
import com.stc.asse.file.entity.PermissionEntity;
import com.stc.asse.file.repository.PermissionsRepository;
import com.stc.asse.file.service.FileService;
import com.stc.asse.file.service.ItemService ;
@RestController
@RequestMapping("stc")
public class FilesController {
	@Autowired
	private PermissionsRepository permissionsRepository;
	@Autowired
    private  FileService fileService;
    @Autowired
    private ItemService ItemService;
    
    @PostMapping("/createSpace")
    public ResponseEntity<String>  createSpace(@RequestParam("spaceName")String spaceName,@RequestParam("permissionGroupId")Long permissionGroupId)
    {
    	ItemService.save(spaceName, permissionGroupId);
    	 return ResponseEntity.status(HttpStatus.OK).body(String.format("create Space successfully: %s",spaceName));
                 
    } @PostMapping("/createFolder")
    public ResponseEntity<String>  createFolder(@RequestParam("folderName")String folderName,@RequestParam("parent_id")Long parent_id,@RequestParam("userMail")String userMail)
    {
    	try {
			ItemService.saveFolder(folderName, parent_id,userMail,"Folder");
		} catch (Exception e) {
			  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                      .body(String.format("Not Have Permissions" ));
			
		}
    	 return ResponseEntity.status(HttpStatus.OK).body(String.format("create Space successfully: %s",folderName));
                 
    }
    
    @PostMapping("/createFile")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file,@RequestParam("fileName")String fileName,@RequestParam("parent_id")Long parent_id,@RequestParam("useEmail")String useEmail) {
        try {
        	ItemEntity itemEntity=ItemService.saveFolder(fileName, parent_id,useEmail,"File");
            fileService.save(file,itemEntity.getId());

            return ResponseEntity.status(HttpStatus.OK)
                                 .body(String.format("File uploaded successfully: %s", file.getOriginalFilename()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(String.format("Could not upload the file: %s!", file.getOriginalFilename()));
        }
    }

    @GetMapping("/viewFileMetadata")
    public List<FileResponse> list(@RequestParam("useEmail")String useEmail) {
    	PermissionEntity permissionUser=permissionsRepository.findAllByUserEmail(useEmail);
    	System.out.println("permissionUser.getPermission_group_id() == "+permissionUser.getPermission_group_id());
        return fileService.getAllFiles(permissionUser.getPermission_group_id())
                          .stream()
                          .map(this::mapToFileResponse)
                          .collect(Collectors.toList());
    }

    private FileResponse mapToFileResponse(FileMetaDataEntity fileEntity) {
        String downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath()
                                                        .path("/stc/")
                                                        .path(fileEntity.getId().toString())
                                                        .toUriString();
        FileResponse fileResponse = new FileResponse();
        fileResponse.setId(fileEntity.getId());
        fileResponse.setName(fileEntity.getName());
        fileResponse.setContentType(fileEntity.getContentType());
        fileResponse.setSize(fileEntity.getSize());
        fileResponse.setUrl(downloadURL);

        return fileResponse;
    }

    @GetMapping("{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
        Optional<FileEntity> fileEntityOptional = fileService.getFile(id);

        if (!fileEntityOptional.isPresent()) {
            return ResponseEntity.notFound()
                                 .build();
        }

        FileEntity fileEntity = fileEntityOptional.get();
        return ResponseEntity.ok()
                             .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getName() + "\"")
                             .contentType(MediaType.valueOf(fileEntity.getContentType()))
                             .body(fileEntity.getData());
    }
}