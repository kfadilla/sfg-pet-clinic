package guru.springframework.sfgpetclinic.services;

public interface CrudService<T, ID> {

    Iterable<T> findAll();
    T findById(ID id);
    T save(T object);
    void deleteById(ID id);
    void delete(T object);
}
