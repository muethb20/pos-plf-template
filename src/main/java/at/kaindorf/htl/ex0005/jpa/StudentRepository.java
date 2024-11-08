package at.kaindorf.htl.ex0005.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

interface StudentRepository extends JpaRepository<Student, Integer> {
}
