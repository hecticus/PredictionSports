package com.hecticus.gpaapi.web;

import com.hecticus.gpaapi.domain.BliveActivity;
import com.hecticus.gpaapi.repository.BliveActivityRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class BliveController {

    private static final String CLICK_ID = "CLICKID";
    private static final String SOURCE = "SOURCE";

    private final BliveActivityRepository activityRepository;

    public BliveController(BliveActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @GetMapping("/blive")
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
        return "blive_index";
    }

    @GetMapping("/mark_bl")
    public @ResponseBody String mark(@RequestParam(name = CLICK_ID, required = false) String clickId,
                                     @RequestParam(name = SOURCE, required = false) String source) {
        String clickValue = "NA";
        String extras = "NA";
        if (clickId != null && !clickId.isEmpty()) {
            clickValue = clickId;
            extras = source != null && !source.isEmpty() ? source : "";
            try {
                addClickId(clickValue + "---" + extras);
            } catch (Exception ignored) {
            }
        }
        return "";
    }

    private void addClickId(String clickId) {
        BliveActivity activity = new BliveActivity();
        activity.setClickId(clickId);
        activity.setDate(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        activityRepository.save(activity);
    }
}
