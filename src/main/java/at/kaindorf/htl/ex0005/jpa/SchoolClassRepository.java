package at.kaindorf.htl.ex0005.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolClassRepository extends JpaRepository<SchoolClass, Integer> {
    SchoolClass findSchoolClassByName(String name);
}
