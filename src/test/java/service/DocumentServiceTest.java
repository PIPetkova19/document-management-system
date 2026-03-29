package service;

import org.example.documentmanagementsystem.model.Document;
import org.example.documentmanagementsystem.model.DocumentType;
import org.example.documentmanagementsystem.model.PDFDocument;
import org.example.documentmanagementsystem.repository.DocumentRepository;
import org.example.documentmanagementsystem.service.DocumentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DocumentServiceTest {

@Mock
DocumentRepository documentRepository;

@InjectMocks
DocumentService documentService;

    private PDFDocument sampleDocument;

    @BeforeEach
    void setUp() {
        sampleDocument = new PDFDocument();
        sampleDocument.setId(1L);
        sampleDocument.setTitle("Title");
        sampleDocument.setAuthor("Author");
        sampleDocument.setType(DocumentType.PDF);
    }

    //test za getDocumentById
    @Test
    void shouldReturnDocument() {
        when(documentRepository.getDocumentById(1L)).thenReturn(sampleDocument);

        Document found = documentService.getDocument(1L);

        assertEquals("Title", found.getTitle());
    }

    //test za updateDocument
    @Test
    void shouldUpdateDocument() {
        when(documentRepository.getDocumentById(1L)).thenReturn(sampleDocument);

        documentService.updateDocument(1L,DocumentType.PDF,"Title 1","Author");

        assertEquals("Title 1", sampleDocument.getTitle());

        //tova garantira ce promenite naistina sa izprateni kam repo
        verify(documentRepository, times(1)).save(sampleDocument);
    }
}
