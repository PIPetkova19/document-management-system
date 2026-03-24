package org.example.documentmanagementsystem.repository;

import org.example.documentmanagementsystem.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    Document getDocumentById(long id);
}
