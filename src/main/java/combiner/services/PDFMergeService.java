package combiner.services;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Service
public class PDFMergeService {

    public File mergePDFs(MultipartFile[] files) throws IOException {
        List<File> fileList = new ArrayList<>();
        PDFMergerUtility pdfMerger = new PDFMergerUtility();

        File mergedFile = File.createTempFile("merged", ".pdf");

        pdfMerger.setDestinationFileName(mergedFile.getAbsolutePath());
        Path tempPdfToMerge = Files.createTempDirectory("tempPdfToMerge");

        for (MultipartFile file : files) {
            if (!file.isEmpty() && file.getOriginalFilename().endsWith(".pdf")) {
                Path filePath = Files.createTempFile(tempPdfToMerge, "uploaded", ".pdf");
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                fileList.add(filePath.toFile());
                // Add the stream to the merger
                pdfMerger.addSource(filePath.toFile());
            }
        }
        pdfMerger.mergeDocuments(null);

        //clean up
        for (File file : fileList) {
            Files.deleteIfExists(file.toPath());
        }
        Files.deleteIfExists(tempPdfToMerge);

        System.out.println("finished");
        return mergedFile;
    }
}
