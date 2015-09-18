package backend.jobs.scrapers.perform;


import backend.HecticusThread;
import exceptions.BadConfigException;
import models.Config;
import models.football.News;
import utils.Utils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by sorcerer on 10/3/14.
 */
public class NewsCleaner extends HecticusThread {

    private int months;

    public NewsCleaner() {
        this.setActTime(System.currentTimeMillis());
        this.setInitTime(System.currentTimeMillis());
        this.setPrevTime(System.currentTimeMillis());
        //set name
        this.setName("NewsCleaner-" + System.currentTimeMillis());
    }

    @Override
    public void process(Map args) {
        try {
            Utils.printToLog(NewsCleaner.class, null, "Iniciando NewsCleaner", false, null, "support-level-1", Config.LOGGER_INFO);
            if (args.containsKey("months")) {
                months = Integer.parseInt((String) args.get("months"));
            } else throw new BadConfigException("es necesario configurar el parametro file_route");

            Calendar window = new GregorianCalendar(TimeZone.getDefault());
            window.add(Calendar.MONTH, -months);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            List<News> news = News.getNewsForNewsCleaner(simpleDateFormat.format(window.getTime()));
            for(News n : news){
                n.deleteResources();
                n.delete();
            }
            news.clear();
        } catch(Exception ex) {

        }
        Utils.printToLog(NewsCleaner.class,null,"Finalizando NewsCleaner",false,null,"support-level-1",Config.LOGGER_INFO);
    }
}
