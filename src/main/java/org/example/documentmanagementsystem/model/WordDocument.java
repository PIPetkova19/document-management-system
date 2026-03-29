package org.example.documentmanagementsystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.example.documentmanagementsystem.visitor.DocumentVisitor;

import java.time.LocalDate;

@Entity
public class WordDocument extends Document {

    int wordVersion;

    public WordDocument() {
    }

    public WordDocument(String title, String author,
                        LocalDate createdDate, DocumentType type,
                        int wordVersion) {
       super(title,author,createdDate,type);
        this.wordVersion = wordVersion;
    }

    public int getWordVersion() {
        return wordVersion;
    }

    public void setWordVersion(int wordVersion) {
        this.wordVersion = wordVersion;
    }

    @Override
    public Document clone() {
        return new WordDocument(this.title, this.author,
                this.createdDate, this.type, this.wordVersion);
    }

    @Override
    public void accept(DocumentVisitor visitor) {
        visitor.visit(this);
    }
}
