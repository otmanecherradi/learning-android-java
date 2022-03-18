package com.dev.tp1.data;

import com.dev.tp1.models.Document;

import java.util.ArrayList;
import java.util.UUID;

public class DocumentRepository {
    public static final String TAG = "DocumentRepository";

    private final ArrayList<Document> documents;

    public DocumentRepository() {
        documents = new ArrayList<>();
    }

    public ArrayList<Document> getDocuments() {
        return documents;
    }

    public void add(Document document) {
        documents.add(document);
    }

    public Document search(UUID documentId) {
        for (Document document : documents) {
            if (document.getCode().equals(documentId)) {
                return document;
            }
        }
        return null;
    }

    public void update(UUID documentId, Document document) {
        if (search(documentId) != null) {
            search(documentId).setDescription(document.getDescription());
            search(documentId).setState(document.getState());
            search(documentId).setThemeId(document.getThemeId());
        }
    }

    public void delete(Document document) {
        documents.remove(document);
    }

}
