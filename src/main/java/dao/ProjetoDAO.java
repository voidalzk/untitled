package dao;

import model.Projeto;
import java.util.List;

public interface ProjetoDAO {
    void create(Projeto projeto);
    Projeto read(Long id);
    void update(Projeto projeto);
    void delete(Projeto projeto);
    List<Projeto> findAll();
}