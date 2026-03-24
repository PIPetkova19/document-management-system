package org.example.documentmanagementsystem.model;

import jakarta.persistence.Entity;
import org.example.documentmanagementsystem.visitor.DocumentVisitor;

@Entity
public class WordDocument extends Document {
    private int wordVersion;

    public int getWordVersion() {
        return wordVersion;
    }

    public void setWordVersion(int wordVersion) {
        this.wordVersion = wordVersion;
    }

    @Override
    public Document clone() {
        WordDocument copy = new WordDocument();
        copy.setTitle(getTitle());
        copy.setAuthor(getAuthor());
        copy.setCreatedDate(this.getCreatedDate());
        copy.setWordVersion(this.getWordVersion());
        copy.setType(this.getType());
        return copy;
    }

    @Override
    public void accept(DocumentVisitor visitor) {
        visitor.visit(this);
    }
}
