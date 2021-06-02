package com.fullstack.FoodBase.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDateTime;

@Service
public class S3Service {

    @Autowired
    private AmazonS3 amazonS3;
    @Value("${aws.s3.bucket}")
    private String bucketName;

    public String uploadFile(String imageData){
        final String uniqueFileName = LocalDateTime.now() + "_" + "foodbase";
        imageData = imageData.replaceFirst("^data:image/[^;]*;base64,?","");

        byte[] bI = java.util.Base64.getDecoder().decode(imageData);
        InputStream fileIS = new ByteArrayInputStream(bI);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(bI.length);
        metadata.setContentType("image/png");
        metadata.setCacheControl("public, max-age=31536000");

        amazonS3.putObject(new PutObjectRequest(
                bucketName, uniqueFileName, fileIS, metadata)
                .withCannedAcl(CannedAccessControlList.PublicRead));

        return getImageURL(uniqueFileName);
    }

    public String getImageURL(String imageKey) {
       return amazonS3.getUrl(bucketName, imageKey).toExternalForm();
    }
}
