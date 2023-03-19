package com.mericaltikardes.suffixoperations.controller;

import com.mericaltikardes.suffixoperations.model.SuffixDatas;
import com.mericaltikardes.suffixoperations.repository.MongoJpa;
import com.mericaltikardes.suffixoperations.service.CompareToStringsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class SuffixController {

    MongoJpa mongoRepository;
    CompareToStringsService compareToStringsService;
    List<String> inputStringsToSaved;
    String resultToSaved = " ";


    public SuffixController(MongoJpa mongoRepository, CompareToStringsService compareToStringsService,
                            List<String> inputStringsToSaved) {
        this.mongoRepository = mongoRepository;
        this.compareToStringsService = compareToStringsService;
        this.inputStringsToSaved = inputStringsToSaved;


    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public String getAllStrings() {
        return "page";
    }

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public String compareToStrings(@RequestParam("myStrings") List<String> myStrings, ModelMap model) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Optional<String> result = compareToStringsService.getResult(myStrings);

        inputStringsToSaved = new ArrayList<>(myStrings);
        if (result.isPresent()) {
            model.put("result", result.get());
            resultToSaved = result.get();
        } else {
            model.put("result", "No Matching");
        }
        stopWatch.stop();
        long resultResponseTime = stopWatch.getTotalTimeNanos();
        model.put("resultResponseTime",resultResponseTime);
        return "page";
    }

    @RequestMapping("/save")
    public String saveResult(ModelMap model) {
        SuffixDatas suffixDatas = new SuffixDatas(inputStringsToSaved, resultToSaved);
        mongoRepository.save(suffixDatas);
        return "redirect:page";
    }

}
