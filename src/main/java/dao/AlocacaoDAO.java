package dao;

import model.Alocacao;
import java.util.List;

public interface AlocacaoDAO {
    void create(Alocacao alocacao);
    Alocacao read(Long id);
    void update(Alocacao alocacao);
    void delete(Alocacao alocacao);
    List<Alocacao> findAll();
    List<Alocacao> findByEquipamentoId(Long equipamentoId);
    List<Alocacao> findByProjetoId(Long projetoId);
}
