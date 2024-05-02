package curso.java.tienda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import curso.java.tienda.model.UsuarioVO;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioVO, Integer> {
	UsuarioVO findByEmailAndClave(String email, String clave);

	UsuarioVO findByEmail(String email);

	@Query("SELECT u FROM UsuarioVO u WHERE u.id_rol = :idRol")
	List<UsuarioVO> findById_Rol(int idRol);
}
