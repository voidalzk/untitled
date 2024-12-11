package dao;

import model.Empregado;
import java.util.List;

public interface EmpregadoDAO {
    void create(Empregado empregado);
    Empregado read(Long id);
    void update(Empregado empregado);
    void delete(Empregado empregado);
    List<Empregado> findAll();
}