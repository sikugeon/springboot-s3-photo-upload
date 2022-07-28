package kr.sikugeon.photoalbum.core.object.infrastructure;

import kr.sikugeon.photoalbum.core.object.application.S3Uploader;
import kr.sikugeon.photoalbum.core.object.domain.ObjectStorage;
import kr.sikugeon.photoalbum.core.user.domain.ProfilePictureStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.nio.file.Path;
import java.util.Objects;
import java.util.UUID;

/**
 * 로컬 저장소 기반으로 사용자 프로필 이미지를 저장하고, 불러오는 {@link ProfilePictureStorage} 구현체
 *
 * @author springrunner.kr@gmail.com
 */
@Repository
public class AmazonS3ObjectStorage implements ObjectStorage, ResourceLoaderAware, InitializingBean {

    private final Logger log = LoggerFactory.getLogger(AmazonS3ObjectStorage.class);
    @Autowired
    private S3Uploader s3Uploader;

    
    private ResourceLoader resourceLoader;
    private Path basePath;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Value("${site.userProfilePicture.basePath:./files/user-profile-picture}")
    public void setBasePath(Path basePath) {
        this.basePath = basePath;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Objects.requireNonNull(resourceLoader, "resourceLoader is required");
        if (!Objects.requireNonNull(basePath, "basePath is required").toFile().exists()) {
            basePath.toFile().mkdirs();
            log.debug("create a directory: {}", basePath.toAbsolutePath().toUri());
        }
    }

    @Override
    public URI save(MultipartFile multipartFile) {
        try {
            File file = new File(System.getProperty("user.dir")+UUID.randomUUID()+multipartFile.getOriginalFilename());
            multipartFile.transferTo(file);
            String url = s3Uploader.upload(file, "");
            return URI.create(url);
        } catch (Exception error) {
            log.debug(error.toString());
            throw new RuntimeException(error);
        }
    }

    @Override
    public Resource load(URI uri) {
        try {
            Resource resource = resourceLoader.getResource(uri.toString());
            if (!resource.exists()) {
                throw new FileNotFoundException(String.format("not found file for uri: %s", uri));
            }
            return resource;
        } catch (Exception error) {
//            throw new FailedLoadingProfilePictureException("로컬 저장소에서 프로필 이미지 불러오기 중 오류가 발생했습니다.", error);
            throw new RuntimeException();
        }
    }

}
