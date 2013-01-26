package ru.dimafeng.services;

import com.petebevin.markdown.MarkdownProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.dimafeng.util.BlogEntry;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class BaseServiceImpl implements BaseService {

    private static final Logger log = LoggerFactory.getLogger(BaseServiceImpl.class);

    @Cacheable("entries")
    public List<BlogEntry> getEntries() {
        log.info("getEntries");

        List<BlogEntry> blogEntries = new ArrayList<BlogEntry>();
        try {
            URL url = new URL("https://raw.github.com/dimafeng/blog-data/master/list.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

            String buff = null;
            while ((buff = reader.readLine()) != null) {
                String[] items = buff.split("\\|\\|");

                BlogEntry blogEntry = new BlogEntry();
                blogEntry.setTitle(items[0]);
                blogEntry.setTitleEnglish(items[1]);
                blogEntry.setUrl(items[2]);
                blogEntry.setTags(new ArrayList<String>());
                for (String tag : items[3].split("::")) {
                    blogEntry.getTags().add(tag);
                }

                blogEntries.add(blogEntry);
            }

        } catch (Exception e) {
            log.error(e.getLocalizedMessage(), e);
        }

        return blogEntries;
    }

    @Override
    public BlogEntry getEntryByName(String name) {
        for (BlogEntry e : getEntries()) {
            if (e.getTitleEnglish().equals(name)) {
                return e;
            }
        }
        return null;
    }

    @Override
    public String getData(BlogEntry blogEntry) {
        try {
            URL url = new URL(blogEntry.getUrl());
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

            StringBuilder sb = new StringBuilder();

            String buff = null;
            while ((buff = reader.readLine()) != null) {
                sb.append(buff);
                sb.append("\n");
            }
            MarkdownProcessor mp = new MarkdownProcessor();
            return mp.markdown(sb.toString());
        } catch (Exception e) {
            log.error(e.getLocalizedMessage(), e);
        }

        return null;
    }
}
