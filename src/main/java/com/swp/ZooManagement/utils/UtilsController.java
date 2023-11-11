package com.swp.ZooManagement.utils;

import com.swp.ZooManagement.errors.ZooManagementException;
import org.apache.http.HttpHeaders;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/utils")
public class UtilsController {
    @GetMapping("/login-page")
    public String loginPage() {
        return "/google.html";
    }

    @GetMapping("/enums")
    @ResponseBody
    private Map<String, List<Object>> getEnums(@RequestParam(name = "enums") String enumsStr) throws ZooManagementException {
        String[] enumNames = enumsStr.split(",");
        Map<String, List<Object>> map = new HashMap<>();
        for (String enumName : enumNames) {
            String className = "com.swp.ZooManagement.utils.enums." + enumName;
            try {
                Class<?> enumClass = Class.forName(className);
                map.put(enumName, List.of(enumClass.getEnumConstants()));
            } catch (ClassNotFoundException e) {
                System.out.println(className);
            }
        }
        return map;
    }

    @PostMapping("/upload")
    @ResponseBody
    private String uploadFile(@RequestParam("file") MultipartFile multipartFile, @RequestHeader(HttpHeaders.HOST) String host) throws IOException {
        String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();

        String uploadFolder = "uploads";
        File dir = new File(uploadFolder);
        if (!(dir.exists() && dir.isDirectory())) {
            dir.mkdir();
        }
        String fileName = multipartFile.getOriginalFilename();
        String localFilePath = uploadFolder + "/" + fileName;
        File f = new File(localFilePath);
        if (!f.exists()) {
            f.createNewFile();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(multipartFile.getInputStream());
            FileOutputStream fos = new FileOutputStream(f);
            bufferedInputStream.transferTo(fos);
            fos.close();
            bufferedInputStream.close();
        }
        return baseUrl + "/utils/files?fileName=" + fileName;
    }

    @PostMapping("/upload-many")
    @ResponseBody
    private List<String> uploadMultipleFile(@RequestParam("files") MultipartFile[] multipartFiles, @RequestHeader(HttpHeaders.HOST) String host) throws IOException {
        String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        List<String> listPath = new ArrayList<>();
        String uploadFolder = "uploads";
        File dir = new File(uploadFolder);
        if (!(dir.exists() && dir.isDirectory())) {
            dir.mkdir();
        }
        for (MultipartFile file: multipartFiles){
            String fileName = file.getOriginalFilename();
            String localFilePath = uploadFolder + "/" + fileName;
            File f = new File(localFilePath);
            if (!f.exists()) {
                f.createNewFile();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(file.getInputStream());
                FileOutputStream fos = new FileOutputStream(f);
                bufferedInputStream.transferTo(fos);
                fos.close();
                bufferedInputStream.close();
            }
            String path = baseUrl + "/utils/files?fileName=" + fileName;
            listPath.add(path);
        }
        return listPath;
    }

    @GetMapping("/files")
    @ResponseBody
    private ResponseEntity<Resource> getFile(@RequestParam("fileName") String fileName) throws IOException {
        String uploadFolder = "uploads";
        String localFilePath = uploadFolder + "/" + fileName;
        File f = new File(localFilePath);
        Resource resource = new FileSystemResource(localFilePath);
        if (f.exists()) {
            String contentType = Files.probeContentType(Path.of(localFilePath));
            MediaType mediaType = MediaType.parseMediaType(contentType);
            return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders -> {
                httpHeaders.setContentType(mediaType);
            }).body(resource);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
