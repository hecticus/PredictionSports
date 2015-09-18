package models;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import comparators.CompetitionsSortComparator;
import models.football.*;
import play.db.ebean.Model;
import play.libs.Json;
import utils.DateAndTime;
import utils.Utils;

import javax.persistence.*;
import java.text.ParseException;
import java.util.*;

/**
 * Created by sorcerer on 9/24/14.
 */


@Entity
@Table(name="apps")
public class Apps extends FootballModel {

    @Id
    private Integer idApp;
    private String name;
    private Integer status;
    private Boolean debug;
    private Integer type;


    @ManyToOne
    @JoinColumn(name = "id_language")
    private Language language;

    @OneToOne
    @JoinColumn(name = "id_timezone")
    private Timezone timezone;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="app", cascade = CascadeType.ALL)
    private List<Competition> competitions;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="app", cascade = CascadeType.ALL)
    @OrderBy("publicationDate desc")
    private List<News> news;

    private static Finder<Integer, Apps> finder = new Model.Finder<Integer, Apps>(Integer.class, Apps.class);

    public Apps() {
        //default
    }

    public Apps(Integer idApp, String name, Integer status, Boolean debug, Integer type, Language language) {
        this.idApp = idApp;
        this.name = name;
        this.status = status;
        this.debug = debug;
        this.type = type;
        this.language = language;
    }

    @Override
    public ObjectNode toJson() {
        ObjectNode obj = Json.newObject();
        obj.put("id_app", idApp);
        obj.put("name",name);
        obj.put("status", status);
        obj.put("language", language.toJson());
        return obj;
    }

    /**************************** GETTERS AND SETTERS ****************************************************/

    public Integer getIdApp() {
        return idApp;
    }

    public void setIdApp(Integer idApp) {
        this.idApp = idApp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getDebug() {
        return debug;
    }

    public void setDebug(Boolean debug) {
        this.debug = debug;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Timezone getTimezone() {
        return timezone;
    }

    public void setTimezone(Timezone timezone) {
        this.timezone = timezone;
    }

    public List<Competition> getCompetitions() {
        List<Competition> tr;
        try {
            Predicate<Competition> validObjs = new Predicate<Competition>() {
                public boolean apply(Competition obj) {
                    return obj.getStatus().intValue() == 1;
                }
            };
            Collection<Competition> result = Utils.filterCollection(competitions, validObjs);
            tr = (List<Competition>) result;
            Collections.sort(tr, new CompetitionsSortComparator());
        } catch (NoSuchElementException e){
            tr = null;
        }
        return tr;
    }

    public void setCompetitions(List<Competition> competitions) {
        this.competitions = competitions;
    }

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }

    public Competition getCompetition(final long idCompetition){
        Competition tr = null;
        try {
            tr = Iterables.find(competitions, new Predicate<Competition>() {
                public boolean apply(Competition obj) {
                    return obj.getIdCompetitions().longValue() == idCompetition;
                }
            });
        } catch (NoSuchElementException ex){
            tr = null;
        }
        return tr;
    }

    public News getNews(final long idNews){
        News tr = null;
        try {
            tr = Iterables.find(news, new Predicate<News>() {
                public boolean apply(News obj) {
                    return obj.getIdNews() == idNews;
                }
            });
        } catch (NoSuchElementException ex){
            tr = null;
        }
        return tr;
    }

    public List<News> getNews(final Calendar pivotNewsCalendar, final boolean newest, int maxSize, final int idLanguage){
        List<News> tr;
        final TimeZone timeZone = this.timezone.getTimezone();
        class NewsComparator implements Comparator<News> {
            @Override
            public int compare(News c1, News c2) {
                try {
                    Calendar c1PublicationDateCalendar = new GregorianCalendar(timeZone);
                    Date c1PublicationDate = DateAndTime.getDate(c1.getPublicationDate(), "yyyyMMddHHmmss");
                    c1PublicationDateCalendar.setTime(c1PublicationDate);

                    Calendar c2PublicationDateCalendar = new GregorianCalendar(timeZone);
                    Date c2PublicationDate = DateAndTime.getDate(c2.getPublicationDate(), "yyyyMMddHHmmss");
                    c2PublicationDateCalendar.setTime(c2PublicationDate);

                    return c2PublicationDateCalendar.before(c1PublicationDateCalendar)?-1:(c1PublicationDateCalendar.before(c2PublicationDateCalendar)?1:0);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return 0;
            }
        }
        Collections.sort(news, new NewsComparator());
        try {
            Predicate<News> validObjs = new Predicate<News>() {
                public boolean apply(News obj) {
                    if(pivotNewsCalendar != null){
                        Calendar publicationDateCalendar = new GregorianCalendar(timeZone);
                        try {
                            Date publicationDate = DateAndTime.getDate(obj.getPublicationDate(), "yyyyMMddhhmmss");
                            publicationDateCalendar.setTime(publicationDate);
                            if(newest){
                                return publicationDateCalendar.after(pivotNewsCalendar) && obj.getLanguage().getIdLanguage().intValue() == idLanguage;
                            }
                            return publicationDateCalendar.before(pivotNewsCalendar) && obj.getLanguage().getIdLanguage().intValue() == idLanguage;
                        } catch (ParseException e) {
                            e.printStackTrace();
                            return false;
                        }
                    }
                    return obj.getLanguage().getIdLanguage().intValue() == idLanguage;
                }
            };
            Collection<News> result = Utils.filterCollection(news, validObjs, 0, maxSize);
            tr = (List<News>) result;


        } catch (NoSuchElementException e){
            tr = null;
        }
        return tr;
    }

    public int countNews(final Calendar pivotNewsCalendar, final boolean newest, final int idLanguage){
        int tr;
        final TimeZone timeZone = this.timezone.getTimezone();
        try {
            Predicate<News> validObjs = new Predicate<News>() {
                public boolean apply(News obj) {
                    if(pivotNewsCalendar != null){
                        Calendar publicationDateCalendar = new GregorianCalendar(timeZone);
                        try {
                            Date publicationDate = DateAndTime.getDate(obj.getPublicationDate(), "yyyyMMddhhmmss");
                            publicationDateCalendar.setTime(publicationDate);
                            if(newest){
                                return publicationDateCalendar.after(pivotNewsCalendar) && obj.getLanguage().getIdLanguage().intValue() == idLanguage;
                            }
                            return publicationDateCalendar.before(pivotNewsCalendar) && obj.getLanguage().getIdLanguage().intValue() == idLanguage;
                        } catch (ParseException e) {
                            e.printStackTrace();
                            return false;
                        }
                    }
                    return  obj.getLanguage().getIdLanguage().intValue() == idLanguage;
                }
            };
            Collection<News> result = Utils.filterCollection(news, validObjs);
            tr = result.size();
        } catch (NoSuchElementException e){
            tr = 0;
        }
        return tr;
    }

    /**************************** FINDER ****************************************************/

    public static TimeZone getTimezone(Integer idApp){
        Apps app = finder.byId(idApp);
        if(app != null){
            return app.getTimezone().getTimezone();
        }
        return TimeZone.getDefault();
    }


    public static Apps findId(Integer id){
        return finder.byId(id);
    }
}

