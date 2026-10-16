package com.hecticus.gpaapi.web;

import com.hecticus.gpaapi.domain.MaxgameActivity;
import com.hecticus.gpaapi.repository.MaxgameActivityRepository;
import com.hecticus.gpaapi.service.tracking.ClickData;
import com.hecticus.gpaapi.service.tracking.ClickParameterExtractor;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MaxgameController {

    private static final Logger log = LoggerFactory.getLogger(MaxgameController.class);

    private static final String CLICK_ID = "clickid";
    private static final String IP = "ip";

    private final MaxgameActivityRepository activityRepository;
    private final ClickParameterExtractor parameterExtractor;

    public MaxgameController(MaxgameActivityRepository activityRepository,
                             ClickParameterExtractor parameterExtractor) {
        this.activityRepository = activityRepository;
        this.parameterExtractor = parameterExtractor;
    }

    @GetMapping({"/", "/mg", "/maxgame"})
    public String index(HttpServletRequest request, Model model) {
        String clickValue = ClickData.DEFAULT_CLICK_VALUE;
        String extras = ClickData.DEFAULT_EXTRAS;
        try {
            ClickData clickData = parameterExtractor.extractFromRequest(request);
            clickValue = clickData.getClickId();
            extras = clickData.getExtras();
            if (clickData.isValid()) {
                addClickId(clickData.getClickId(), "");
            }
        } catch (Exception e) {
            log.error("Error in MaxgameController.index()", e);
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
