package at.kaindorf.htl.ex0005.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolClassRepository extends JpaRepository<SchoolClass, Integer> {
    SchoolClass findSchoolClassByName(String name);
}
