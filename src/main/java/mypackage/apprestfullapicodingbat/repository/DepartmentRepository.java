package mypackage.apprestfullapicodingbat.repository;

import mypackage.apprestfullapicodingbat.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    boolean existsByNameAndLanguage_Id(String name, Integer language_id);
    boolean existsByNameAndLanguage_IdAndIdNot(String name, Integer language_id, Integer id);
}
