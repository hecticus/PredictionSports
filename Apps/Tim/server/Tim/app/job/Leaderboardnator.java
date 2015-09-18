package job;

import akka.actor.Cancellable;
import backend.HecticusThread;
import models.Config;
import play.db.DB;
import utils.Utils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by plesse on 10/30/14.
 */
public class Leaderboardnator extends HecticusThread {

    public Leaderboardnator() {
        long start = System.currentTimeMillis();
        setName("Leaderboardnator-"+start);
        setInitTime(start);
        setActTime(start);
        setPrevTime(start);
    }

    public Leaderboardnator(String name, AtomicBoolean run, Cancellable cancellable) {
        super("Leaderboardnator-"+name, run, cancellable);
    }

    public Leaderboardnator(String name, AtomicBoolean run) {
        super("Leaderboardnator-"+name, run);
    }

    public Leaderboardnator(AtomicBoolean run) {
        super("Leaderboardnator",run);
    }

    @Override
    public void process(Map args) {
        try {
            Utils.printToLog(Leaderboardnator.class, null, "Iniciando Leaderboardnator", false, null, "support-level-1", Config.LOGGER_INFO);
            int points = Integer.parseInt(""+args.get("points"));
            executeLeaderboardnator(points);
            Utils.printToLog(Leaderboardnator.class, null, "Terminando Leaderboardnator", false, null, "support-level-1", Config.LOGGER_INFO);
        } catch (Exception ex) {
            Utils.printToLog(Leaderboardnator.class, null, "Error calculando leadeboards", true, ex, "support-level-1", Config.LOGGER_ERROR);
        }
    }

    private void executeLeaderboardnator(int points) throws SQLException {
        TimeZone timeZone = TimeZone.getDefault();
        Calendar today = new GregorianCalendar(timeZone);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        simpleDateFormat.setTimeZone(timeZone);
        String date = simpleDateFormat.format(today.getTime());

        Connection connection = null;
        CallableStatement cs = null;
        try {
            connection = DB.getConnection();
            cs = connection.prepareCall("call leaderboardnator(?,?)");
            cs.setString(1, date);
            cs.setInt(2, points);
            isAlive();
            cs.execute();
            isAlive();
        } finally {
            try {cs.closeOnCompletion();}catch (Exception e){}
            try {connection.close();}catch (Exception e){}
        }

    }

}
