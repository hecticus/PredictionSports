package com.hecticus.gpaapi.web;

import com.hecticus.gpaapi.domain.MaxgameActivity;
import com.hecticus.gpaapi.repository.MaxgameActivityRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MaxgameController {

    private static final String CLICK_ID = "clickid";
    private static final String IP = "ip";

    private final MaxgameActivityRepository activityRepository;

    public MaxgameController(MaxgameActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @GetMapping({"/", "/mg", "/maxgame"})
    public String index(@RequestParam(name = CLICK_ID, required = false) String clickId, Model model) {
        String clickValue = "NA";
        String extras = "NA";
        if (clickId != null && !clickId.isEmpty()) {
            clickValue = clickId;
            try {
                addClickId(clickValue, "");
            } catch (Exception ignored) {
            }
        }
        model.addAttribute("clickValue", clickValue);
        model.addAttribute("extras", extras);
        return "maxgame_index";
    }

    @GetMapping("/ip_maxgame")
    public @ResponseBody String setip(@RequestParam(name = CLICK_ID, required = false) String clickId,
                                      @RequestParam(name = IP, required = false) String ip) {
        String cId = clickId == null ? "" : clickId;
        String ipId = ip == null ? "" : ip;
        activityRepository.findFirstByClickIdOrderByIdDesc(cId).ifPresent(activity -> {
            activity.setIp(ipId);
            activityRepository.save(activity);
        });
        return "";
    }

    @GetMapping("/mark_maxgame")
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
        activityRepository.findFirstByClickIdOrderByIdDesc(clickId).orElseGet(() -> {
            MaxgameActivity activity = new MaxgameActivity();
            activity.setClickId(clickId);
            activity.setDate(new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date()));
            activity.setIp(ip);
            activity.setOrigin("TRA");
            activity.setSent(false);
            return activityRepository.save(activity);
        });
    }
}
