package org.example.documentmanagementsystem.visitor;

import org.example.documentmanagementsystem.model.PDFDocument;
import org.example.documentmanagementsystem.model.WordDocument;

public interface DocumentVisitor {
     void visit(WordDocument word);
     void visit(PDFDocument pdf);
}
