package org.bobatrackr.repository;

import org.bobatrackr.domain.LocationEntry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by dburban on 2/3/15.
 */
@Repository
public interface LocationRepository extends CrudRepository<LocationEntry, Long> {
}
