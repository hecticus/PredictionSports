package backend.jobs.opta.service;

import models.Apps;
import models.Language;

public class AppServiceImpl {
    protected Language language;
    protected Apps app;

    public AppServiceImpl(){}

    public AppServiceImpl(Apps app, Language language) {
        this.app = app;
        this.language = language;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Apps getApp() {
        return app;
    }

    public void setApp(Apps app) {
        this.app = app;
    }
}
