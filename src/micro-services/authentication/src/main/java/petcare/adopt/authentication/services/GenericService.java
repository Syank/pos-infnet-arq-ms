package petcare.adopt.authentication.services;

import java.util.List;

public interface GenericService<T> {

    public List<T> getAll();

    public T get(Long id, String noSuchElementException);

    public void save(T entity);

    public void update(T entity);

    public void delete(Long id);

}
