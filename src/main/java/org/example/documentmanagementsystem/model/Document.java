package org.example.documentmanagementsystem.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.example.documentmanagementsystem.visitor.DocumentVisitor;

import java.time.LocalDate;

@Entity
public abstract class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

     String title;
     String author;
     LocalDate createdDate;
     DocumentType type;

     public Document(){}

    public Document(String title, String author,
                    LocalDate createdDate, DocumentType type) {
        this.title = title;
        this.author = author;
        this.createdDate = createdDate;
        this.type = type;
    }

    //za testove
    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public DocumentType getType() {
        return type;
    }

    public void setType(DocumentType type) {
        this.type = type;
    }

    //visitor
    public abstract void accept(DocumentVisitor visitor);

    //prototype(interface)
    public abstract Document clone();

}