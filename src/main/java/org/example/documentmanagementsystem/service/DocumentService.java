package org.example.documentmanagementsystem.service;

import exception.DocumentNotFoundException;
import org.example.documentmanagementsystem.model.DocumentType;
import org.example.documentmanagementsystem.model.Document;
import org.example.documentmanagementsystem.model.PDFDocument;
import org.example.documentmanagementsystem.model.WordDocument;
import org.springframework.stereotype.Service;
import org.example.documentmanagementsystem.repository.DocumentRepository;
import org.example.documentmanagementsystem.visitor.DocumentVisitor;
import org.example.documentmanagementsystem.visitor.MetadataVisitor;

import java.util.List;

@Service
public class DocumentService {
    DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    //crud
    public void createDocument(DocumentType type, String title, String author) {
        Document doc = switch (type) {
            case WORD -> new WordDocument();
            case PDF -> new PDFDocument();
        };

        doc.setType(type);
        doc.setAuthor(author);
        doc.setTitle(title);

        documentRepository.save(doc);
        System.out.println("Document created");
    }

    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    public Document getDocument(long id) {

        Document doc = documentRepository.getDocumentById(id);

        if (doc == null) {
            throw new DocumentNotFoundException("doc not found");
        }

        return doc;
    }

    public void updateDocument(long id, DocumentType type, String title, String author) {
        Document document = documentRepository.getDocumentById(id);
            document.setAuthor(author);
            document.setTitle(title);
            document.setType(type);

            documentRepository.save(document);
            System.out.println("Document updated");
    }

    public void deleteDocument(long id) {
        documentRepository.deleteById(id);
        System.out.println("Document deleted");
    }

    //visiting
    public void addMetadata(long id) {
        Document doc = documentRepository.getDocumentById(id);

        DocumentVisitor visitor = new MetadataVisitor();
        doc.accept(visitor);

        documentRepository.save(doc);
        System.out.println("Metadata added");
    }

    //clone
    public void cloneDocument(Long id) {
        Document original = documentRepository.getDocumentById(id);
        Document copy = original.clone();
        documentRepository.save(copy);
        System.out.println("Document cloned");
    }
}
