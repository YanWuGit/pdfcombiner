"use strict";
(function() {

    const form = document.querySelector(".pdf-form");
    const errorNoPdf = document.querySelector(".pdf-form__error__no-pdf");
    const pdfInput = document.querySelector(".pdf-form__input");
    const fileList = document.querySelector(".file-list");

    form.addEventListener('submit', (e) => {


        showEmptyError(pdfInput.files.length < 2, errorNoPdf, "Please upload at least 2 .pdf files you want to merge.");


        console.log(pdfInput.files.length);
        if (pdfInput.files.length<2) {
            e.preventDefault();
        }
    });

    pdfInput.addEventListener('change', (e) => {
        showEmptyError(pdfInput.files.length < 2, errorNoPdf, "Please upload at least 2 .pdf files you want to merge.");
        updateFileList(pdfInput.files, fileList);
    });

    function showEmptyError(toShow, errorMessageField, errorMessageText) {
        if (toShow) {
            errorMessageField.innerHTML=`${errorMessageText}`;
        } else {
            errorMessageField.innerHTML='';
        }
    }

    function updateFileList(files, fileList) {
        fileList.innerHTML='';

        for (let i=0; i < files.length; i++) {
            const file = files[i];
            if (file.type === 'application/pdf') {
                const li = document.createElement('li');
                li.textContent = file.name;
                fileList.appendChild(li);
            }
        }
    }
    
})();