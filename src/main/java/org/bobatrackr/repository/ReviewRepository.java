package org.bobatrackr.repository;

import org.bobatrackr.domain.LocationEntry;
import org.bobatrackr.domain.ReviewEntry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by dburban on 2/3/15.
 */
@Repository
public interface ReviewRepository extends CrudRepository<ReviewEntry, Long> {
}
