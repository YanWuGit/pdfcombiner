package combiner.controllers;

import combiner.services.PDFMergeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Controller
public class PDFMergeController {

    @Autowired
    private PDFMergeService pdfMergeService;

    @PostMapping("/mergepdf")
    public ResponseEntity<InputStreamResource> mergePDFs(@RequestParam("files") MultipartFile[] files) {
        try {
            File mergedFile = pdfMergeService.mergePDFs(files);
            // Set up the input stream and headers for the file download
            InputStreamResource resource = new InputStreamResource(new FileInputStream(mergedFile));

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=merged.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(resource);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }

    }

}
