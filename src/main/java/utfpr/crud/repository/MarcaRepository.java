package utfpr.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import utfpr.crud.model.Marca;

import java.util.List;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {
}
