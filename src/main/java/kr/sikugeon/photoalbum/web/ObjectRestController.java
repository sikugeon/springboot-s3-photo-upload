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
import java.util.Date;

@RestController
public class ObjectRestController {

    ObjectStorage objectStorage;
    private final Logger log = LoggerFactory.getLogger(ObjectRestController.class);

    public ObjectRestController(ObjectStorage objectStorage){
        this.objectStorage = objectStorage;
    }


    @PostMapping("/api/object")
//    @RolesAllowed(UserSession.ROLE_USER)
    public ResponseEntity<UploadInfo> uploadObject(@RequestParam("images") MultipartFile object, UserSession session) {
        log.debug("profilePicture: {}, {}", object.getOriginalFilename(), object.getContentType());

        // 업로드된 프로필 이미지 파일 저장하기
        URI uploadedObjectUri = objectStorage.save(object);
//        log.debug("profilePictureUri : {}", profilePictureUri);

        UploadInfo uploadInfo = new UploadInfo();
        uploadInfo.setName(object.getName());
        uploadInfo.setUri(uploadedObjectUri);
        uploadInfo.setUploaded_at(new Date());

        return new ResponseEntity<>(uploadInfo, HttpStatus.ACCEPTED);
    }
}
