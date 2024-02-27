package combiner.controllers;

import combiner.services.PDFMergeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Controller
public class PDFMergeController {

    @Autowired
    private PDFMergeService pdfMergeService;

    @PostMapping("/mergepdf")
    public ResponseEntity<String> mergePDFs() {
        try {
            pdfMergeService.mergePDFs();
            return ResponseEntity.ok("Success.");
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Failed");
        }

    }

}
