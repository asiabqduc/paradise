package net.ecoparadise.css.repository.contact;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.ecoparadise.entity.Contact;
import net.ecoparadise.framework.repository.BaseRepository;

@Repository
public interface ContactRepository extends BaseRepository<Contact, Long> {
	Optional<Contact> findByEmail(String email);

	Optional<Contact> findByCode(String code);
	Long countByCode(String code);
	
  @Query(value = "SELECT entity.code FROM #{#entityName} entity ", nativeQuery = true)
  List<String> findCode();

	@Query("SELECT entity FROM #{#entityName} entity WHERE ("
			+ " LOWER(entity.code) like LOWER(CONCAT('%',:keyword,'%')) or "
			//+ " LOWER(entity.accountName) like LOWER(CONCAT('%',:keyword,'%')) or "
			+ " LOWER(entity.title) like LOWER(CONCAT('%',:keyword,'%')) or "
			+ " LOWER(entity.email) like LOWER(CONCAT('%',:keyword,'%')) "
			+ ")"
	)
	Page<Contact> search(@Param("keyword") String keyword, Pageable pageable);
}
