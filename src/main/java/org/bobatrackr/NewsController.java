package org.bobatrackr;

import org.bobatrackr.domain.NewsEntry;
import org.bobatrackr.repository.NewsRepository;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by dburban on 2/1/15.
 */
@RestController
class NewsController {
    @Resource
    private NewsRepository newsRepository;

    @RequestMapping("/news")
    Iterable<NewsEntry> entries() {
        return newsRepository.findAll();
    }

    @RequestMapping(value = "/news/{id}", method = RequestMethod.DELETE)
    NewsEntry remove(@PathVariable Long id) {
        NewsEntry newsEntry = newsRepository.findOne(id);
        newsRepository.delete(newsEntry);
        return newsEntry;
    }

    @RequestMapping(value = "/news/{id}", method = RequestMethod.GET)
    NewsEntry entry(@PathVariable Long id) {
        return newsRepository.findOne(id);
    }

    @RequestMapping(value = "/news/{id}", method = RequestMethod.POST)
    NewsEntry update(@RequestBody NewsEntry news) {
        newsRepository.save(news);
        return news;
    }

    @RequestMapping(value = "/news", method = RequestMethod.POST)
    NewsEntry add(@RequestBody NewsEntry news) {
//        long id = 10 + new Random().nextInt(99);
//        news.setId(id);
//        this.entries.put(id, news);
        newsRepository.save(news);
        return news;
    }

    NewsController() {
//        for (long i = 0; i < 5; i++)
//        newsRepository.save(new NewsEntry(i, "Title #" + i));
    }
}
