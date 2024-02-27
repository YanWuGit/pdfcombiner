package combiner.services;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@Service
public class PDFMergeService {

    public void mergePDFs() throws IOException {
        PDFMergerUtility pdfMerger = new PDFMergerUtility();
        File output = new File("output/merged.pdf");
        pdfMerger.setDestinationFileName(output.getAbsolutePath());

        // read files to merge
        File allFiles = new File("pdfToMerge");
        FilenameFilter pdfFilter = (dir, name) -> name.endsWith(".pdf");
        File[] pdfToMerge = allFiles.listFiles(pdfFilter);

        for (File pdf : pdfToMerge) {
            pdfMerger.addSource(pdf);
        }
        pdfMerger.mergeDocuments(null);

        System.out.println("finished");
    }
}
