package org.example.documentmanagementsystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.example.documentmanagementsystem.visitor.DocumentVisitor;

import java.time.LocalDate;

@Entity
public class PDFDocument extends Document {

    public PDFDocument() {}

    public PDFDocument(String title, String author,
                       LocalDate createdDate, DocumentType type) {
      super(title,author,createdDate,type);
    }

    @Override
    public Document clone() {
        return new PDFDocument(this.title, this.author, this.createdDate, this.type);
    }

    @Override
    public void accept(DocumentVisitor visitor) {
        visitor.visit(this);
    }

}