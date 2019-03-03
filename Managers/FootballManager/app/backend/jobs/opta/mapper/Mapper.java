package backend.jobs.opta.mapper;

public interface Mapper<T, U> {
    U MapTo(T obj);
    T MapFrom(U obj);
}
