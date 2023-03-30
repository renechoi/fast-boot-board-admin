package fast.boardadmin.repository;

import fast.boardadmin.domain.AdminAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<AdminAccount, String> {
}
