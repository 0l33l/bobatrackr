package org.bobatrackr.repository;

import org.bobatrackr.domain.NewsEntry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by dburban on 2/3/15.
 */
@Repository
public interface NewsRepository extends CrudRepository<NewsEntry, Integer> {
}
