package at.kaindorf.htl.ex0005.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

interface  SchoolClassRepository extends JpaRepository<SchoolClass, Integer> {
    SchoolClass findSchoolClassByName(String name);
}
