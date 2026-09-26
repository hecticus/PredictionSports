package com.hecticus.gpaapi.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hecticus.gpaapi.domain.LearnLiveActivity;
import com.hecticus.gpaapi.repository.LearnLiveActivityRepository;
import com.hecticus.gpaapi.service.tracking.ClickData;
import com.hecticus.gpaapi.service.tracking.ClickParameterExtractor;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class LearnliveController {

    private static final Logger log = LoggerFactory.getLogger(LearnliveController.class);

    private final ClickParameterExtractor parameterExtractor;
    private final LearnLiveActivityRepository activityRepository;
    private final ObjectMapper mapper;

    public LearnliveController(ClickParameterExtractor parameterExtractor,
                               LearnLiveActivityRepository activityRepository,
                               ObjectMapper mapper) {
        this.parameterExtractor = parameterExtractor;
        this.activityRepository = activityRepository;
        this.mapper = mapper;
    }

    @GetMapping("/learnlive")
    public String index(HttpServletRequest request, Model model) {
        try {
            ClickData clickData = parameterExtractor.extractFromRequest(request);
            model.addAttribute("clickValue", clickData.getClickId());
            model.addAttribute("extras", clickData.getExtras());
        } catch (Exception e) {
            log.error("Error in LearnliveController.index()", e);
            model.addAttribute("clickValue", ClickData.DEFAULT_CLICK_VALUE);
            model.addAttribute("extras", ClickData.DEFAULT_EXTRAS);
        }
        return "learnlive_index";
    }

    @GetMapping("/mark")
    public @ResponseBody ObjectNode mark(HttpServletRequest request) {
        ObjectNode result = mapper.createObjectNode();
        try {
            ClickData clickData = parameterExtractor.extractFromRequest(request);
            if (clickData.isValid()) {
                addClickId(clickData.getCombinedValue(), clickData.getOrigin());
                result.put("status", "success");
                result.put("token", clickData.getCombinedValue());
                result.put("origin", clickData.getOrigin());
                result.put("message", "Token saved successfully");
            } else {
                result.put("status", "error");
                result.put("error", "Invalid or missing token");
                result.put("message", "No valid click data found in request");
            }
        } catch (Exception e) {
            log.error("Error in LearnliveController.mark()", e);
            result.put("status", "error");
            result.put("error", e.getClass().getSimpleName());
            result.put("message", e.getMessage() != null ? e.getMessage() : "Unknown error occurred");
        }
        return result;
    }

    private void addClickId(String clickId, String origin) {
        LearnLiveActivity activity = new LearnLiveActivity();
        activity.setClickId(clickId);
        activity.setDate(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        activity.setOrigin(origin);
        activityRepository.save(activity);
        log.info("Saved LearnLiveActivity: clickId={}, origin={}", clickId, origin);
    }
}
