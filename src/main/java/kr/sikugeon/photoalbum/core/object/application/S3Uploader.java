package kr.sikugeon.photoalbum.core.object.application;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.URI;
import java.net.URL;

@Service
public class S3Uploader {
    @Autowired
    private AmazonS3Client amazonS3Client;

    @Value("")
    private String bucketName;

    public String upload(File uploadFile, String dirName){
        String key = dirName+uploadFile.getName();
        amazonS3Client.putObject(new PutObjectRequest(bucketName, key, uploadFile));

        return amazonS3Client
                .getUrl(bucketName, key)
                .toString();
    }
}
