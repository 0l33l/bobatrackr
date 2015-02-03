package org.bobatrackr;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by dburban on 2/1/15.
 */
@RestController
class NewsController {

    Map<Long, NewsEntry> entries = new ConcurrentHashMap<>();

    @RequestMapping("/news")
    Collection<NewsEntry> entries() {
        return this.entries.values();
    }

    @RequestMapping(value = "/news/{id}", method = RequestMethod.DELETE)
    NewsEntry remove(@PathVariable Long id) {
        return this.entries.remove(id);
    }

    @RequestMapping(value = "/news/{id}", method = RequestMethod.GET)
    NewsEntry entry(@PathVariable Long id) {
        return this.entries.get(id);
    }

    @RequestMapping(value = "/news/{id}", method = RequestMethod.POST)
    NewsEntry update(@RequestBody NewsEntry news) {
        this.entries.put(news.getId(), news);
        return news;
    }

    @RequestMapping(value = "/news", method = RequestMethod.POST)
    NewsEntry add(@RequestBody NewsEntry news) {
        long id = 10 + new Random().nextInt(99);
        news.setId(id);
        this.entries.put(id, news);
        return news;
    }

    NewsController() {
        for (long i = 0; i < 5; i++)
            this.entries.put(i, new NewsEntry(i, "Title #" + i));
    }

    public static class NewsEntry {
        private long id;
        private String content;

        public NewsEntry() {}

        public NewsEntry(long id, String b) {
            this.id = id;
            this.content = b;
        }

        public long getId() {
            return this.id;
        }

        public String getContent() {
            return this.content;
        }

        public void setId(long id) {
            this.id = id;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

}
