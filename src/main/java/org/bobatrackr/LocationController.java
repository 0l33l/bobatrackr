package org.bobatrackr;

import org.bobatrackr.domain.LocationEntry;
import org.bobatrackr.repository.LocationRepository;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by dburban on 2/1/15.
 */
@RestController
class LocationController {

    @Resource
    private LocationRepository locationRepository;

    @RequestMapping("/location")
    Iterable<LocationEntry> entries() {
        return locationRepository.findAll();
    }

    @RequestMapping(value = "/location/{id}", method = RequestMethod.DELETE)
    LocationEntry remove(@PathVariable Long id) {
        LocationEntry locationEntry = locationRepository.findOne(id);
        locationRepository.delete(locationEntry);
        return locationEntry;
    }

    @RequestMapping(value = "/location/{id}", method = RequestMethod.GET)
    LocationEntry entry(@PathVariable Long id) {
        return locationRepository.findOne(id);
    }

    @RequestMapping(value = "/location/{id}", method = RequestMethod.POST)
    LocationEntry update(@RequestBody LocationEntry locationEntry) {
        locationRepository.save(locationEntry);
        return locationEntry;
    }

    @RequestMapping(value = "/location", method = RequestMethod.POST)
    LocationEntry add(@RequestBody LocationEntry locationEntry) {
        locationRepository.save(locationEntry);
        return locationEntry;
    }
}
