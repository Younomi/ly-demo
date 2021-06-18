package com.leyou.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface UploadService {

    String upload(MultipartFile file);

    Map<String, Object> getSignature();
}
