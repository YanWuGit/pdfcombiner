package combiner.services;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PDFSplitService {

    public List<File> splitPdf(File inputPdf, List<Integer> breakpoints) throws IOException {
        List<File> outputFiles = new ArrayList<>();

        try (PDDocument document = Loader.loadPDF(inputPdf)) {
            List<PDDocument> documents = new ArrayList<>();
            Splitter splitter = new Splitter();

            int startPage = 1;
            for (int breakpoint : breakpoints) {
                splitter.setStartPage(startPage);
                splitter.setEndPage(breakpoint);
                List<PDDocument> splittedDocs = splitter.split(document);
                documents.addAll(splittedDocs);
                startPage = breakpoint + 1;
            }

            // Handle the case for the last segment of the PDF
            if (startPage <= document.getNumberOfPages()) {
                splitter.setStartPage(startPage);
                splitter.setEndPage(document.getNumberOfPages());
                documents.addAll(splitter.split(document));
            }

            // Save the split documents and collect File references
            int fileIndex = 1;
            for (PDDocument doc : documents) {
                File outputFile = new File(inputPdf.getParent(), "split_" + fileIndex + ".pdf");
                doc.save(outputFile);
                outputFiles.add(outputFile);
                doc.close();
                fileIndex++;
            }
        }

        return outputFiles;
    }
}
