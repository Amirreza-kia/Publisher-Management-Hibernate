package ir.maktabsharif.repository;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<T> {

    void saveOrUpdate(T object);
    void delete(Long id) ;
    Optional<T> findById(Long id);
    List<T> getAll() ;
}
