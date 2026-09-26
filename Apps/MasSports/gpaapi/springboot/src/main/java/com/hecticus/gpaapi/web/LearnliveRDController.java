package com.hecticus.gpaapi.web;

import com.hecticus.gpaapi.domain.LearnLiveRDActivity;
import com.hecticus.gpaapi.repository.LearnLiveRDActivityRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class LearnliveRDController {

    private static final String CLICK_ID = "CLICKID";
    private static final String SOURCE = "SOURCE";

    private final LearnLiveRDActivityRepository activityRepository;

    public LearnliveRDController(LearnLiveRDActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @GetMapping("/learnlive_rd")
    public String index(@RequestParam(name = CLICK_ID, required = false) String clickId,
                        @RequestParam(name = SOURCE, required = false) String source,
                        Model model) {
        String clickValue = "NA";
        String extras = "NA";
        if (clickId != null && !clickId.isEmpty()) {
            clickValue = clickId;
            extras = source != null && !source.isEmpty() ? source : "";
        }
        model.addAttribute("clickValue", clickValue);
        model.addAttribute("extras", extras);
        return "learnlive_rd_index";
    }

    @GetMapping("/mark_rd")
    public @ResponseBody String mark(@RequestParam(name = CLICK_ID, required = false) String clickId,
                                     @RequestParam(name = SOURCE, required = false) String source) {
        String clickValue = "NA";
        String extras = "NA";
        if (clickId != null && !clickId.isEmpty()) {
            clickValue = clickId;
            extras = source != null && !source.isEmpty() ? source : "";
            try {
                LearnLiveRDActivity activity = new LearnLiveRDActivity();
                activity.setClickId(clickValue + "---" + extras);
                activity.setDate(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
                activityRepository.save(activity);
            } catch (Exception ignored) {
            }
        }
        return "";
    }
}
