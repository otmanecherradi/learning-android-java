package com.dev.tp1.data;

public class DataRepository {
    public static final String TAG = "DataRepository";

    private static final ThemeRepository themeRepository = new ThemeRepository();
    private static final DocumentRepository documentRepository = new DocumentRepository();

    public static ThemeRepository getThemeRepository() {
        return themeRepository;
    }

    public static DocumentRepository getDocumentRepository() {
        return documentRepository;
    }
}
