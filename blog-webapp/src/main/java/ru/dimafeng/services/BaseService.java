package ru.dimafeng.services;


import ru.dimafeng.util.BlogEntry;

import java.util.List;

public interface BaseService {
    public List<BlogEntry> getEntries();

    public BlogEntry getEntryByName(String name);

    public String getData(BlogEntry blogEntry);
}
