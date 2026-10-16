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
public class MaxgameMobiController {

    private static final Logger log = LoggerFactory.getLogger(MaxgameMobiController.class);

    private static final String CLICK_ID = "CLICKID";
    private static final String IP = "ip";

    private final MaxgameActivityRepository activityRepository;
    private final ClickParameterExtractor parameterExtractor;

    public MaxgameMobiController(MaxgameActivityRepository activityRepository,
                                 ClickParameterExtractor parameterExtractor) {
        this.activityRepository = activityRepository;
        this.parameterExtractor = parameterExtractor;
    }

    @GetMapping("/mob")
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
            log.error("Error in MaxgameMobiController.index()", e);
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
