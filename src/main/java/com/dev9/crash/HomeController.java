package com.dev9.crash;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
class HomeController {

    @Autowired
    ApplicationContext context;

    @RequestMapping("/")
    String index() {
        return "index";
    }

    @ModelAttribute("allBadThings")
    List<BadThing> allBadThings() {
        List result = new ArrayList<>(allBadThingsMap().values());

        return result;
    }

    Map<String, BadThing> allBadThingsMap() {
        return context.getBeansOfType(BadThing.class);
    }

    @RequestMapping("/crash")
    String crash(@RequestParam(value = "id") String id, Model model) throws Exception {

        System.out.println("Got a crash request for " + id);

        BadThing found = null;

        for (BadThing bad : allBadThingsMap().values()) {
            if (bad.getBadThingId().compareToIgnoreCase(id) == 0)
                found = bad;
        }

        if (found == null) {
            model.addAttribute("crash", "Can't find " + id);
            return "crash";
        }

        String result = found.doBadThing();

        if (found.doBadThing() != null)
            model.addAttribute("crash", "Crash " + id + "returned" + result);
        else
            model.addAttribute("crash", "Run " + id + " successfully.");

        return "crash";
    }
}
