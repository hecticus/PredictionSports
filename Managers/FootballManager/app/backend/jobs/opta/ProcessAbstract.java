package backend.jobs.opta;

import backend.HecticusThread;
import backend.jobs.opta.service.AppServiceImpl;
import backend.jobs.scrapers.perform.OptasportsScraper;
import exceptions.BadConfigException;
import models.Apps;
import models.Config;
import models.Language;
import utils.Utils;
import java.util.Map;

public class ProcessAbstract extends HecticusThread {
    protected Language language;
    protected Apps app;
    protected AppServiceImpl appService;

    public void process(Map args){
        try {
            if (args.containsKey("language")) {
                language = Language.getByID(Integer.parseInt((String) args.get("language")));
                if(language == null)
                    throw new BadConfigException("el language configurado no existente");
            } else
                language = Language.getByID(300);

            if (args.containsKey("app")) {
                app = Apps.findId(Integer.parseInt((String) args.get("app")));
                if(app == null)
                    throw new BadConfigException("el app configurado no existente");
            } else {
                app = Apps.findId(1);
            }

            appService = new AppServiceImpl(app, language);
        } catch (BadConfigException ex){
            Utils.printToLog(OptasportsScraper.class,
                    "Error en OptasportsScraperNEW",
                    "el job esta mal configurado por favor revisar, el proceso no se esta revisando",
                    true,
                    ex,
                    "support-level-1",
                    Config.LOGGER_ERROR);
        } catch (Exception ex){
            Utils.printToLog(OptasportsScraper.class,
                    "Error en OptasportsScraperNEW",
                    "error inesperado en el OptasportsScraper, el proceso no fue finalizado. se reintentara",
                    true,
                    ex,
                    "support-level-1",
                    Config.LOGGER_ERROR);
        }
    }
}
