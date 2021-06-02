package com.fullstack.FoodBase.service;

import org.springframework.web.multipart.MultipartFile;

public interface S3ServiceInterface {

    void uploadFile(MultipartFile multipartFile);
}
