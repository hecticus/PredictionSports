package controllers;

import modeles.BliveActivity;
import modeles.LearnLiveActivity;
import modeles.MaxgameActivity;
import modeles.PaxxionActivity;
import modeles.log;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;
import play.mvc.Controller;
import play.mvc.Result;
import utils.Constants;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ClickToSMSController extends Controller {

    private WSClient ws;

    @Inject
    public ClickToSMSController(WSClient ws) {
        this.ws = ws;
    }

    public Result markItem(String country, String business, String msisdn) {
        String command = "";
        if (request().getQueryString("command") != null && !request().getQueryString("command").equals("")) {
            command = request().getQueryString("command");
        }

        log log2 = new log();
        log2.setIdentifier("LOL");
        log2.setExtra(command);
        log2.setMsisdn(msisdn);
        log2.save();

        if (Constants.HAITI_COUNTRY_ID.equals(country) && Constants.HAITI_BLIVE_BUSINESS_ID.equals(business)) {
            BliveActivity blive = BliveActivity.finder.where()
                    .eq("msisdn", null)
                    .lt("date", getDateAddSeconds(-20))
                    .orderBy()
                    .desc("id")
                    .setMaxRows(1)
                    .findUnique();

            if (blive != null) {
                blive.setMsisdn(msisdn);
                blive.save();
                String[] values = blive.getClickId().split("---");
                sendMessageToMobipium(values[0], values[values.length > 1 ? 1 : 0]);
            }
        }


        if (Constants.HAITI_COUNTRY_ID.equals(country) && Constants.HAITI_PAXION_BUSINESS_ID.equals(business)) {
            String origin = "MOB";
            switch (command) {
                case "LANDING2":
                    origin = "VIA";
                case "LANDING3":
                    origin = "TRA";
                default:
                    origin = "MOB";
            }

            PaxxionActivity blive = PaxxionActivity.finder.where()
                    .eq("msisdn", null)
                    .lt("date", getDateAddSeconds(-20))
                    .eq("origin", origin)
                    .orderBy()
                    .desc("id")
                    .setMaxRows(1)
                    .findUnique();

            if (blive != null) {
                blive.setMsisdn(msisdn);
                blive.save();
                if (blive.getOrigin().equals("VIA")) {
                    sendMessageToVia(blive.getClickId());


                } else if (blive.getOrigin().equals("TRA")) {
                    sendMessageToTrafficCompany("11240", "0dd1b688a16aa53c03fe0cfe2c114e71", blive.getClickId());

                } else {
                    String[] values = blive.getClickId().split("---");
                    sendMessageToMobipium(values[0], values[values.length > 1 ? 1 : 0]);
                }
            }
        }

        if (Constants.HAITI_COUNTRY_ID.equals(country) && Constants.HAITI_TEACH_BUSINESS_ID.equals(business)) {
            LearnLiveActivity blive = LearnLiveActivity.finder.where()
                    .eq("msisdn", null)
                    .lt("date", getDateAddSeconds(-20))
                    .orderBy()
                    .desc("id")
                    .setMaxRows(1)
                    .findUnique();

            if (blive != null) {
                blive.setMsisdn(msisdn);
                blive.save();
                String[] values = blive.getClickId().split("---");
                sendMessageToMobipium(values[0], values[values.length > 1 ? 1 : 0]);
            }
        }


        if (Constants.VEN_COUNTRY_ID.equals(country) && Constants.VEN_MAXGAME_BUSINESS_ID.equals(business)) {
            MaxgameActivity blive = MaxgameActivity.finder.where()
                    .eq("msisdn", null)
                    .lt("date", getDateAddSeconds(-20))
                    .orderBy()
                    .desc("id")
                    .setMaxRows(1)
                    .findUnique();

            if (blive != null) {
                blive.setMsisdn(msisdn);
                blive.save();
                if (command.equals("LANDING")) {
                    sendMessageToTrafficCompany("11191", "3c71abda6be99653251370ff838fa4ab", blive.getClickId());
                }
            }
        }

        return ok();
    }

    private String getDateAddSeconds(int seconds) {
        String DATE_FORMAT = "yyyyMMddHHmmss";
        SimpleDateFormat sdf =
                new SimpleDateFormat(DATE_FORMAT);
        Calendar c1 = Calendar.getInstance(); // today
        c1.add(Calendar.SECOND, seconds);
        return sdf.format(c1.getTime());
    }

    private void sendMessageToMobipium(String clickId, String source) {
        String call = String.format("https://smobipiumlink.com/conversion/index.php?jp=%s&source=%s", clickId, source);
        System.out.println(call);
        this.ws.url(call)
                .get()
                .thenAccept((WSResponse r) -> {
                    String body = r.getBody();
                });
    }

    OkHttpClient client = new OkHttpClient();

    private void sendMessageToTrafficCompany(String handler, String hash, String clickId) {
        String url = "https://postback.level23.nl/?currency=USD&handler=" + handler + "&hash=" + hash + "&tracker=" + clickId;

        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        String call = "https://postback.level23.nl/?currency=USD&handler=11191&hash=3c71abda6be99653251370ff838fa4ab&tracker="+ clickId;
//        System.out.println(call);
//        this.ws.url(call)
//                .get()
//                .thenAccept((WSResponse r) -> {
//                    String body = r.getBody();
//                });
    }

    private void sendMessageToVia(String clickId) {
        String call = String.format("http://api.doblevialatam.com:9090/cget.php?token=%s", clickId);
        System.out.println(call);
        this.ws.url(call)
                .get()
                .thenAccept((WSResponse r) -> {
                    String body = r.getBody();
                });
    }

}
