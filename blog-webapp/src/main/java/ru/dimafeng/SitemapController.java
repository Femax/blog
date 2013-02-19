package ru.dimafeng;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.dimafeng.services.BaseService;
import ru.dimafeng.util.BlogEntry;
import ru.dimafeng.util.XmlUrl;
import ru.dimafeng.util.XmlUrlSet;

@Controller
public class SitemapController {

    @Autowired
    private BaseService baseService;

    @RequestMapping(value = "/sitemap.xml", method = RequestMethod.GET)
    @ResponseBody
    public XmlUrlSet main() {
        XmlUrlSet xmlUrlSet = new XmlUrlSet();
        create(xmlUrlSet, "main", XmlUrl.Priority.HIGH);
        create(xmlUrlSet, "contact/", XmlUrl.Priority.HIGH);

        for (BlogEntry be : baseService.getEntries()) {
            create(xmlUrlSet, "show/" + be.getTitleEnglish(), XmlUrl.Priority.MEDIUM);
        }

        return xmlUrlSet;
    }

    private void create(XmlUrlSet xmlUrlSet, String link, XmlUrl.Priority priority) {
        xmlUrlSet.addUrl(new XmlUrl("http://dimafeng.ru/" + link, priority));
    }

}