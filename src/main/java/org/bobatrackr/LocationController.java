package org.bobatrackr;

import org.bobatrackr.domain.LocationEntry;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by dburban on 2/1/15.
 */
@RestController
class LocationController {

    Map<Long, LocationEntry> entries = new ConcurrentHashMap<>();

    @RequestMapping("/location")
    Collection<LocationEntry> entries() {
        return this.entries.values();
    }

    @RequestMapping(value = "/location/{id}", method = RequestMethod.DELETE)
    LocationEntry remove(@PathVariable Long id) {
        return this.entries.remove(id);
    }

    @RequestMapping(value = "/location/{id}", method = RequestMethod.GET)
    LocationEntry entry(@PathVariable Long id) {
        return this.entries.get(id);
    }

    @RequestMapping(value = "/location/{id}", method = RequestMethod.POST)
    LocationEntry update(@RequestBody LocationEntry locationEntry) {
        this.entries.put(locationEntry.getId(), locationEntry);
        return locationEntry;
    }

    @RequestMapping(value = "/location", method = RequestMethod.POST)
    LocationEntry add(@RequestBody LocationEntry locationEntry) {
        long id = 10 + new Random().nextInt(99);
        locationEntry.setId(id);
        this.entries.put(id, locationEntry);
        return locationEntry;
    }

    LocationController() {
        for (long i = 0; i < 5; i++)
            this.entries.put(i, new LocationEntry(i, "yo #" + i, "w00t " + i * 2));
    }

}
