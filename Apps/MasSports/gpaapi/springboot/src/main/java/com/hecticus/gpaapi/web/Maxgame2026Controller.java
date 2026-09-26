package com.hecticus.gpaapi.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hecticus.gpaapi.domain.MaxgameActivity;
import com.hecticus.gpaapi.repository.MaxgameActivityRepository;
import com.hecticus.gpaapi.service.tracking.ClickData;
import com.hecticus.gpaapi.service.tracking.ClickParameterExtractor;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class Maxgame2026Controller {

    public static final String ORIGIN = "MG2026";
    private static final Logger log = LoggerFactory.getLogger(Maxgame2026Controller.class);

    private final ClickParameterExtractor parameterExtractor;
    private final MaxgameActivityRepository activityRepository;
    private final ObjectMapper mapper;

    public Maxgame2026Controller(ClickParameterExtractor parameterExtractor,
                                 MaxgameActivityRepository activityRepository,
                                 ObjectMapper mapper) {
        this.parameterExtractor = parameterExtractor;
        this.activityRepository = activityRepository;
        this.mapper = mapper;
    }

    @GetMapping("/maxgame_2026")
    public String index(HttpServletRequest request) {
        try {
            ClickData clickData = parameterExtractor.extractFromRequest(request);
            log.info("Maxgame2026 index - ClickData: {}", clickData);
            if (clickData.isValid()) {
                addClickId(clickData.getCombinedValue());
            }
        } catch (Exception e) {
            log.error("Error in Maxgame2026Controller.index()", e);
        }
        return "maxgame_2026";
    }

    @GetMapping("/mark_maxgame_2026")
    public @ResponseBody ObjectNode mark(HttpServletRequest request) {
        ObjectNode result = mapper.createObjectNode();
        try {
            ClickData clickData = parameterExtractor.extractFromRequest(request);
            if (clickData.isValid()) {
                log.info("Maxgame2026 mark - Saving: {}", clickData);
                addClickId(clickData.getCombinedValue());
                result.put("status", "success");
                result.put("token", clickData.getCombinedValue());
                result.put("origin", ORIGIN);
                result.put("message", "Token saved successfully");
            } else {
                result.put("status", "error");
                result.put("error", "Invalid or missing token");
                result.put("message", "No valid click data found in request");
            }
        } catch (Exception e) {
            log.error("Error in Maxgame2026Controller.mark()", e);
            result.put("status", "error");
            result.put("error", e.getClass().getSimpleName());
            result.put("message", e.getMessage() != null ? e.getMessage() : "Unknown error occurred");
        }
        return result;
    }

    private void addClickId(String clickId) {
        if (activityRepository.findFirstByClickIdOrderByIdDesc(clickId).isEmpty()) {
            MaxgameActivity activity = new MaxgameActivity();
            activity.setClickId(clickId);
            activity.setDate(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
            activity.setOrigin(ORIGIN);
            activity.setSent(false);
            activityRepository.save(activity);
            log.info("Saved MaxgameActivity: clickId={}, origin={}", clickId, ORIGIN);
        }
    }
}
