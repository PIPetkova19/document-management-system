package service;

import exception.DocumentNotFoundException;
import org.example.documentmanagementsystem.model.Document;
import org.example.documentmanagementsystem.model.DocumentType;
import org.example.documentmanagementsystem.model.PDFDocument;
import org.example.documentmanagementsystem.repository.DocumentRepository;
import org.example.documentmanagementsystem.service.DocumentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;
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

    @Test
    void should_returnDocTitle_whenDocRead() {
        when(documentRepository.getDocumentById(1L)).thenReturn(sampleDocument);

        Document found = documentService.getDocument(1L);

        assertEquals("Title", found.getTitle(), "Title should be the same");
      /*  assertNotEquals("Title", found.getTitle(), () ->
                "Title: " + found.getTitle() + " shouldn't be the same");
                */
    }
    
    @Test
    void should_returnDocType_whenDocRead() {
        when(documentRepository.getDocumentById(1L)).thenReturn(sampleDocument);

        Document found = documentService.getDocument(1L);

        assertTrue(found.getType()==DocumentType.PDF);
    }

    @Test
    void should_returnAllFields_whenDocRead() {
        when(documentRepository.getDocumentById(1L)).thenReturn(sampleDocument);

        Document found = documentService.getDocument(1L);

        assertAll(
                () -> assertNotNull(found),
                () -> assertEquals("Title", found.getTitle()),
                () -> assertEquals("Author", found.getAuthor()),
                () -> assertEquals(DocumentType.PDF, found.getType())
        );
    }

    @Test
    void should_checkTime_whenDocRead() {
      assertTimeout( Duration.ofSeconds(4), ()-> documentService.getAllDocuments());
    }

    @Test
    void should_checkTimePreemptively_whenDocRead() {
        assertTimeoutPreemptively(Duration.ofSeconds(4), ()-> documentService.getAllDocuments());
    }

    @Test
    void should_throwException_whenDocumentNotFound() {
        when(documentRepository.getDocumentById(2L)).thenReturn(null);

        DocumentNotFoundException exception = assertThrows(
                DocumentNotFoundException.class,
                () -> documentService.getDocument(2L)
        );

        assertEquals("doc not found", exception.getMessage());
    }

    @Test
    void should_checkTitle_whenTypeIsPDF() {
        when(documentRepository.getDocumentById(1L)).thenReturn(sampleDocument);

        Document doc=documentService.getDocument(1L);

        assumeTrue(doc.getType()==DocumentType.PDF); //word->aborted

        assertEquals("Title", doc.getTitle());
    }

    //условието е false -> кодът вътре не се изпълнява -> няма assert -> няма fail
    @Test
    void should_checkFields_whenTypeIsPDF() {
        when(documentRepository.getDocumentById(1L)).thenReturn(sampleDocument);

        Document doc = documentService.getDocument(1L);

        //runs a block if true
        assumingThat(doc.getType() == DocumentType.PDF, () -> {
            assertEquals("Title", doc.getTitle());
            assertEquals("Author", doc.getAuthor());
        });
    }

    @Test
    void should_saveDoc_whenDocIsSaved() {
        when(documentRepository.save(any(Document.class)))
                .thenReturn(sampleDocument);

        documentService.createDocument(DocumentType.PDF, "Title", "Author");

        verify(documentRepository, times(1)).save(any(Document.class));
    }


    @Test
    void shouldU_updateDoc_whenDocIsUpdated() {
        when(documentRepository.getDocumentById(1L)).thenReturn(sampleDocument);

        documentService.updateDocument(1L,DocumentType.PDF,"Title 1","Author");

        assertEquals("Title 1", sampleDocument.getTitle());

        verify(documentRepository, times(1)).save(sampleDocument);
    }

    //force test failure immediately; for unfinished tests/@Disabled; for try..catch
    @Test
    @Disabled
    void should_doSomething() {
        fail("Not implemented yet");
    }
}
