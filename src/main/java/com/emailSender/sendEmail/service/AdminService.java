package com.emailSender.sendEmail.service;

import com.emailSender.sendEmail.utils.FileUploadUtil;
import com.emailSender.sendEmail.utils.ResponseJsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Map;

@Service
@Slf4j
@Component
public class AdminService {

    @Autowired
    private FileUploadUtil fileUploadUtil;

    public Map saveUserProfile(MultipartFile file)
            throws IOException {
        log.info("<====== Started saveUserProfile(MultipartFile file) ======>");
        // Get the filename and build the local file path
        if(file == null || file.isEmpty()){
            log.error("file found null/empty file");
        }
        String receivedFilename = file.getOriginalFilename();
        String extension = fileUploadUtil.getExtension(receivedFilename);
        if(extension == null){
            log.error("File extension not supported");
            throw new MultipartException("File extension not supported");
        }
        boolean doesExtMatched = fileUploadUtil.matchProfilePicExtension(extension);
        if(!doesExtMatched){
            log.error("File extension not supported");
            throw new MultipartException("File extension not supported");
        }

        if (!fileUploadUtil.doesProfileFileSizeLessThenMaxSize(file)) {
            log.info("Size of the file - " + String.valueOf(file.getSize()) +
                    " and maxFileSize allowed - " + fileUploadUtil.getProfilePicMaxFileSize());
            throw new MultipartException("File larger than maximum size limit!");
        }

        String finalUserProfilePicFileName = fileUploadUtil.getUserProfilePicFileName(receivedFilename);

        String profilePicUploadPath = fileUploadUtil.getProfilePicUploadPath(finalUserProfilePicFileName);

        // deleting old file
        fileUploadUtil.deleteFile(profilePicUploadPath);

        // Save the file locally
        fileUploadUtil.saveUserProfilePic(file,profilePicUploadPath);


        log.info("<====== Ended saveUserProfile(MultipartFile file, Long userId) ======>");
        return ResponseJsonUtil.getSuccessResponseJson("");
    }
}
