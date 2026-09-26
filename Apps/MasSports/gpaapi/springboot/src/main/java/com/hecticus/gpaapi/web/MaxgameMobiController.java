package com.hecticus.gpaapi.web;

import com.hecticus.gpaapi.domain.MaxgameActivity;
import com.hecticus.gpaapi.repository.MaxgameActivityRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MaxgameMobiController {

    private static final String CLICK_ID = "CLICKID";
    private static final String IP = "ip";
    private static final String SOURCE = "SOURCE";

    private final MaxgameActivityRepository activityRepository;

    public MaxgameMobiController(MaxgameActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @GetMapping("/mob")
    public String index(@RequestParam(name = CLICK_ID, required = false) String clickId,
                        @RequestParam(name = SOURCE, required = false) String source,
                        Model model) {
        String clickValue = "NA";
        String extras = "NA";
        if (clickId != null && !clickId.isEmpty()) {
            clickValue = clickId;
            extras = source != null && !source.isEmpty() ? source : "";
            try {
                addClickId(clickValue, "");
            } catch (Exception ignored) {
            }
        }
        model.addAttribute("clickValue", clickValue);
        model.addAttribute("extras", extras);
        return "maxgame_mobi_index";
    }

    @GetMapping("/mark_maxgame_mobi")
    public @ResponseBody String mark(@RequestParam(name = CLICK_ID, required = false) String clickId) {
        if (clickId != null && !clickId.isEmpty()) {
            try {
                activityRepository.findFirstByClickIdOrderByIdDesc(clickId).ifPresent(activity -> {
                    activity.setSent(true);
                    activityRepository.save(activity);
                });
            } catch (Exception ignored) {
            }
        }
        return "";
    }

    private void addClickId(String clickId, String ip) {
        if (activityRepository.findFirstByClickIdOrderByIdDesc(clickId).isEmpty()) {
            MaxgameActivity activity = new MaxgameActivity();
            activity.setClickId(clickId);
            activity.setDate(new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date()));
            activity.setIp(ip);
            activity.setSent(false);
            activity.setOrigin("MOBI");
            activityRepository.save(activity);
        }
    }
}
