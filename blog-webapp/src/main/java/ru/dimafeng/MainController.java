package ru.dimafeng;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.dimafeng.services.BaseService;

@Controller
public class MainController {

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private BaseService baseService;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Model model) {

        model.addAttribute("items", baseService.getEntries());

        return "view.main";
    }

    @RequestMapping(value="/show/{title}", method = RequestMethod.GET)
    public String show(@PathVariable("title") String title, Model model) {
        model.addAttribute("data", baseService.getData(baseService.getEntryByName(title)));
        return "view.show";
    }

}
