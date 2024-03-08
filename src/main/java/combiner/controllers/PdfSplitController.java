package combiner.controllers;


import combiner.services.PDFMergeService;
import combiner.services.PDFSplitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.util.ArrayList;

@Controller

public class PdfSplitController {


    @Autowired
    private PDFSplitService pdfSplitService;

    @RequestMapping("/splitpdf")
    public ResponseEntity<?> splitPDFs(@RequestParam("file") File file, @RequestParam("breakpoints") ArrayList<Integer> breakpoints) {
        return ResponseEntity.ok().build();
    }
}