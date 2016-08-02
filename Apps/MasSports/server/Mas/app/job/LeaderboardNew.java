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
 * Modified by Fer
 */
public class LeaderboardNew extends HecticusThread {

    public LeaderboardNew() {
        long start = System.currentTimeMillis();
        setName("Leaderboardnator-"+start);
        setInitTime(start);
        setActTime(start);
        setPrevTime(start);
    }

    public LeaderboardNew(String name, AtomicBoolean run, Cancellable cancellable) {
        super("Leaderboardnew-"+name, run, cancellable);
    }

    public LeaderboardNew(String name, AtomicBoolean run) {
        super("Leaderboardnew-"+name, run);
    }

    public LeaderboardNew(AtomicBoolean run) {
        super("Leaderboardnew",run);
    }

    @Override
    public void process(Map args) {
        try {
            Utils.printToLog(Leaderboardnator.class, null, "Iniciando LeaderboardNew", false, null, "support-level-1", Config.LOGGER_INFO);
            executeLeaderboardNew();
            Utils.printToLog(Leaderboardnator.class, null, "Terminando LeaderboardNew", false, null, "support-level-1", Config.LOGGER_INFO);
        } catch (Exception ex) {
            Utils.printToLog(Leaderboardnator.class, null, "Error calculando leadeboards en NEW", true, ex, "support-level-1", Config.LOGGER_ERROR);
        }
    }

    private void executeLeaderboardNew() throws SQLException {
        Connection connection = null;
        CallableStatement cs = null;
        try {
            connection = DB.getConnection();
            cs = connection.prepareCall("call sp_LeaderboardCalculatorNew()");
            isAlive();
            cs.execute();
            isAlive();
        } finally {
            try {cs.closeOnCompletion();}catch (Exception e){}
            try {connection.close();}catch (Exception e){}
        }

    }

}
