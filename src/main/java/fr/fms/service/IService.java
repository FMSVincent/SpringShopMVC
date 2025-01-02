package fr.fms.service;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface IService<Obj> {

    List<Obj> findByAttribute(Long id);

    Page<Obj> getAll(String kw, int page);

    List<Obj> getAll();

    Optional<Obj> getOne(Long id);

    void deleteOne(Long id);

    void createOne(Obj obj);

}
