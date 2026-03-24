package org.example.documentmanagementsystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.example.documentmanagementsystem.DocumentType;
import org.example.documentmanagementsystem.visitor.DocumentVisitor;

import java.time.LocalDate;

@Entity
public class PDFDocument extends Document {

    @Override
    public Document clone() {
        PDFDocument copy = new PDFDocument();
        copy.setTitle(getTitle());
        copy.setAuthor(getAuthor());
        copy.setCreatedDate(this.getCreatedDate());
        copy.setType(this.getType());
        return copy;
    }

    @Override
    public void accept(DocumentVisitor visitor) {
        visitor.visit(this);
    }

}