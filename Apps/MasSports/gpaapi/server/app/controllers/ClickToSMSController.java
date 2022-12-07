package controllers;

import modeles.BliveActivity;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;
import play.mvc.Controller;
import play.mvc.Result;
import utils.Constants;

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

}
