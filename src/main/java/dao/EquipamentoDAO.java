package dao;

import model.Equipamento;
import java.util.List;

public interface EquipamentoDAO {
    void create(Equipamento equipamento);
    Equipamento read(Long id);
    void update(Equipamento equipamento);
    void delete(Equipamento equipamento);
    List<Equipamento> findAll();
}