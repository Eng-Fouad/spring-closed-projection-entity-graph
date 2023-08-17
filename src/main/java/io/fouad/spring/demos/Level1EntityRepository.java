package io.fouad.spring.demos;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface Level1EntityRepository extends JpaRepository<Level1Entity, Integer> {

	@EntityGraph(value = "Level1")
	Level1Entity queryLevel1EntityWithEntityGraphById(int id);

	Level1Entity queryLevel1EntityWithoutEntityGraphById(int id);

	@Query("""
		SELECT l1
		FROM Level1Entity l1
		LEFT JOIN FETCH l1.level2
		LEFT JOIN FETCH l1.level2.level3
		WHERE l1.id = :id
			""")
	Level1Entity queryLevel1EntityWithJpqlFetchById(@Param("id") int id);

	@EntityGraph(value = "Level1")
	Level1EntityProjection queryLevel1ProjectionWithEntityGraphById(int id);

	Level1EntityProjection queryLevel1ProjectionWithoutEntityGraphById(int id);

	@Query("""
		SELECT l1
		FROM Level1Entity l1
		LEFT JOIN FETCH l1.level2
		LEFT JOIN FETCH l1.level2.level3
		WHERE l1.id = :id
			""")
	Level1EntityProjection queryLevel1ProjectionWithJpqlFetchById(@Param("id") int id);
}