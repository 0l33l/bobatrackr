package org.bobatrackr;

import org.bobatrackr.domain.ReviewEntry;
import org.bobatrackr.repository.LocationRepository;
import org.bobatrackr.repository.ReviewRepository;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by dburban on 2/1/15.
 */
@RestController
class ReviewController {

    @Resource
    private ReviewRepository reviewRepository;
    @Resource
    private LocationRepository locationRepository;

    @RequestMapping("/review")
    Iterable<ReviewEntry> entries() {
        return reviewRepository.findAll();
    }

    @RequestMapping(value = "/review/{id}", method = RequestMethod.DELETE)
    ReviewEntry remove(@PathVariable Long id) {
        ReviewEntry reviewEntry = reviewRepository.findOne(id);
        reviewRepository.delete(reviewEntry);
        return reviewEntry;
    }

    @RequestMapping(value = "/review/{id}", method = RequestMethod.GET)
    ReviewEntry entry(@PathVariable Long id) {
        return reviewRepository.findOne(id);
    }

    @RequestMapping(value = "/review/{id}", method = RequestMethod.POST)
    ReviewEntry update(@RequestBody ReviewEntry reviewEntry) {
        reviewRepository.save(reviewEntry);
        return reviewEntry;
    }

    @RequestMapping(value = "/review/{locationId}", method = RequestMethod.POST)
    ReviewEntry add(@RequestBody ReviewEntry reviewEntry, @PathVariable Long locationId) {
        reviewEntry.setLocationEntry(locationRepository.findOne(locationId));
        reviewRepository.save(reviewEntry);
        return reviewEntry;
    }
}
