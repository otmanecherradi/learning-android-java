package com.dev.tp1.ui.documents.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dev.tp1.data.DataRepository;
import com.dev.tp1.models.Document;

import java.util.ArrayList;
import java.util.UUID;

public class DocumentViewModel extends ViewModel {

    private final MutableLiveData<ArrayList<Document>> documents = new MutableLiveData<>(DataRepository.getDocumentRepository().getDocuments());

    public LiveData<ArrayList<Document>> getDocuments() {
        return documents;
    }

    public void add(Document document) {
        DataRepository.getDocumentRepository().add(document);
        refresh();
    }

    public void update(UUID documentId, Document document) {
        DataRepository.getDocumentRepository().update(documentId, document);
        refresh();
    }

    public void remove(Document document) {
        DataRepository.getDocumentRepository().delete(document);
        refresh();
    }

    private void refresh() {
        this.documents.postValue(DataRepository.getDocumentRepository().getDocuments());
    }

}
