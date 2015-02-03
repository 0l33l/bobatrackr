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
class LocationController {

    Map<Long, Location> entries = new ConcurrentHashMap<>();

    @RequestMapping("/location")
    Collection<Location> entries() {
        return this.entries.values();
    }

    @RequestMapping(value = "/location/{id}", method = RequestMethod.DELETE)
    Location remove(@PathVariable Long id) {
        return this.entries.remove(id);
    }

    @RequestMapping(value = "/location/{id}", method = RequestMethod.GET)
    Location entry(@PathVariable Long id) {
        return this.entries.get(id);
    }

    @RequestMapping(value = "/location/{id}", method = RequestMethod.POST)
    Location update(@RequestBody Location location) {
        this.entries.put(location.getId(), location);
        return location;
    }

    @RequestMapping(value = "/location", method = RequestMethod.POST)
    Location add(@RequestBody Location location) {
        long id = 10 + new Random().nextInt(99);
        location.setId(id);
        this.entries.put(id, location);
        return location;
    }

    LocationController() {
        for (long i = 0; i < 5; i++)
            this.entries.put(i, new Location(i, "yo #" + i, "w00t " + i*2));
    }

    public static class Location {
        private long id;
        private String title;
        private String address;

        public Location() {}

        public Location(long id, String b, String c) {
            this.id = id;
            this.title = b;
            this.address = c;
        }

        public long getId() {
            return this.id;
        }

        public String getTitle() {
            return this.title;
        }

        public void setId(long id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }

}
