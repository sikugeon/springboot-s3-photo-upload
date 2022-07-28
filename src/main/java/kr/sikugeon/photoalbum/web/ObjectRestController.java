package kr.sikugeon.photoalbum.web;

import kr.sikugeon.photoalbum.core.object.domain.ObjectStorage;
import kr.sikugeon.photoalbum.core.object.domain.UploadInfo;
import kr.sikugeon.photoalbum.security.UserSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class ObjectRestController {

    ObjectStorage objectStorage;
    private final Logger log = LoggerFactory.getLogger(ObjectRestController.class);

    public ObjectRestController(ObjectStorage objectStorage){
        this.objectStorage = objectStorage;
    }


    @PostMapping("/api/object")
    @RolesAllowed(UserSession.ROLE_USER)
    public ResponseEntity<List<UploadInfo>> uploadObject(@RequestParam("images") List<MultipartFile> objects, UserSession session) {

        List<UploadInfo> uploadInfos = new ArrayList<>();

        for(MultipartFile object:objects){
            log.debug("profilePicture: {}, {}", object.getOriginalFilename(), object.getContentType());

            // multipartfile을 s3에 업로드하기
            URI uploadedObjectUri = objectStorage.save(object);

            UploadInfo uploadInfo = new UploadInfo();
            uploadInfo.setName(object.getName());
            uploadInfo.setUri(uploadedObjectUri);
            uploadInfo.setUploaded_at(new Date());

            uploadInfos.add(uploadInfo);
        }


        return new ResponseEntity<>(uploadInfos, HttpStatus.ACCEPTED);
    }
}
