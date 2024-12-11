package dao;

import model.Alocacao;
import java.util.List;
import java.time.LocalDate;

public interface AlocacaoDAO {
    void create(Alocacao alocacao) throws AlocacaoException;
    Alocacao read(Long id);
    void update(Alocacao alocacao) throws AlocacaoException;
    void delete(Alocacao alocacao);
    List<Alocacao> findAll();
    List<Alocacao> findByEquipamentoId(Long equipamentoId);
    List<Alocacao> findByProjetoId(Long projetoId);
    boolean hasOverlappingAllocation(Long equipamentoId, LocalDate dataInicio, LocalDate dataFim, Long alocacaoId);
}