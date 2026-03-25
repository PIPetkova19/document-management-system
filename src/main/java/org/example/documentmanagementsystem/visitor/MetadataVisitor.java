package org.example.documentmanagementsystem.visitor;

import org.example.documentmanagementsystem.model.PDFDocument;
import org.example.documentmanagementsystem.model.WordDocument;

import java.time.LocalDate;

public class MetadataVisitor implements DocumentVisitor {

    public void visit(WordDocument word) {
        word.setCreatedDate(LocalDate.now());
        word.setWordVersion(word.getWordVersion() + 1);
    }

    public void visit(PDFDocument pdf) {
        pdf.setCreatedDate(LocalDate.now());
    }
}
