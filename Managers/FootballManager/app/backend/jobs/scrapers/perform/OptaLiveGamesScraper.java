package backend.jobs.scrapers.perform;

import models.Config;
import models.football.Competition;
import models.football.CompetitionType;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import utils.Utils;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.StringReader;

/**
 * Created by sorcerer on 5/7/15.
 */
public class OptaLiveGamesScraper extends OptasportsScraper {

    @Override
    protected void initProcess() {
        try {
            Utils.printToLog(OptaLiveGamesScraper.class, null, "Iniciando OptaLiveGamesScraper", false, null, "support-level-1", Config.LOGGER_INFO);
            //get avaible leagues
            String url = "http://api.core.optasports.com/soccer/get_seasons?authorized=yes&username=" + optaUserName + "&authkey=" + optaAuthKey + "&lang=" + language.getShortName();
            String xmlRespose = sendRequest(url, "");
            InputSource source = new InputSource(new StringReader(xmlRespose));
            XPath xPath =  XPathFactory.newInstance().newXPath();
            NodeList competitions = (NodeList) xPath.compile("gsmrs/competition").evaluate(source, XPathConstants.NODESET);
            for (int i = 0; isAlive() && i < competitions.getLength(); i++) {
                Node currentCompetition = competitions.item(i);
                try {
                    //get data from current comp
                    String competitionName = xPath.compile("@name").evaluate(currentCompetition),
                            competitionsExternalId = xPath.compile("@competition_id").evaluate(currentCompetition),
                            areaId = xPath.compile("@area_id").evaluate(currentCompetition),
                            areaIdName = xPath.compile("@area_name").evaluate(currentCompetition),
                            formatName = xPath.compile("@format").evaluate(currentCompetition),
                            competitionLastUpdated = xPath.compile("@last_updated").evaluate(currentCompetition);
                    CompetitionType category = new CompetitionType(competitionName, Long.parseLong(competitionsExternalId));
                    category.validate(language);
                    //seasons
                    NodeList competitionSeasons = (NodeList) xPath.compile("season").evaluate(currentCompetition, XPathConstants.NODESET);
                    for (int j = 0; isAlive() && j < competitionSeasons.getLength(); j++) {
                        //for each season
                        Node currentSeason = competitionSeasons.item(j);
                        String currentSeasonId = xPath.compile("@season_id").evaluate(currentSeason),
                                currentSeasonName = xPath.compile("@name").evaluate(currentSeason),
                                currentSeasonStartDate = xPath.compile("@start_date").evaluate(currentSeason),
                                currentSeasonEndDate = xPath.compile("@end_date").evaluate(currentSeason),
                                currentSeasonLastUptdated = xPath.compile("@last_updated").evaluate(currentSeason);
                        //String name = category.getName() + " " + currentSeasonName + " (" + areaIdName + ")" ;
                        String name = category.getName() + " " + currentSeasonName;
                        Competition c = new Competition(name, Long.parseLong(currentSeasonId), app, category);
                        c.validate(language);
                        //get stuff
                        getMinuteByMinute(currentSeasonId);
                    }

                } catch (Exception ex) {
                    //dont break cycle
                    Utils.printToLog(OptasportsScraper.class,
                            "Error en OptaLiveGamesScraper",
                            "error inesperado procesando los torneos disponibles, el proceso continua",
                            false,
                            ex,
                            "support-level-1",
                            Config.LOGGER_ERROR);
                }
            }
            Utils.printToLog(OptaLiveGamesScraper.class, null, "finalizando OptaLiveGamesScraper", false, null, "support-level-1", Config.LOGGER_INFO);
            //not alarm exception
        }catch (Exception ex){
            //alarm
            Utils.printToLog(OptasportsScraper.class,
                    "Error en OptaLiveGamesScraper",
                    "error inesperado procesando los torneos disponibles, no se pudieron procesar los torneos es necesario tomar acciones.",
                    true,
                    ex,
                    "support-level-1",
                    Config.LOGGER_ERROR);
        }

    }
}
