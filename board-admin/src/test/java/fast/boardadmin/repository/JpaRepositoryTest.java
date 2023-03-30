package fast.boardadmin.repository;

import fast.boardadmin.config.TestJpaConfig;
import fast.boardadmin.domain.AdminAccount;
import fast.boardadmin.domain.constant.RoleType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

@Import(TestJpaConfig.class)
@DisplayName("JPA 연결 테스트")
@DataJpaTest
class JpaRepositoryTest {

    private final AdminAccountRepository adminAccountRepository;

    public JpaRepositoryTest(@Autowired AdminAccountRepository adminAccountRepository) {
        this.adminAccountRepository = adminAccountRepository;
    }

    @DisplayName("회원 정보 select 테스트")
    @Test
    void test() {
        List<AdminAccount> adminAccounts = adminAccountRepository.findAll();
        assertThat(adminAccounts).isNotNull().hasSize(4);
    }

    @Test
    void insert(){
        long previous = adminAccountRepository.count();
        AdminAccount adminAccount = AdminAccount.of("test", "pw", Set.of(RoleType.DEVELOPER), "email", "nickname", "memo");

        adminAccountRepository.save(adminAccount);

        assertThat(adminAccountRepository.count()).isEqualTo(previous+1);
    }

    @DisplayName("회원 정보 update 테스트")
    @Test
    void givenUserAccountAndRoleType_whenUpdating_thenWorksFine() {
        // Given
        AdminAccount adminAccount = adminAccountRepository.getReferenceById("uno");
        adminAccount.addRoleType(RoleType.DEVELOPER);
        adminAccount.addRoleTypes(List.of(RoleType.USER, RoleType.USER));
        adminAccount.removeRoleType(RoleType.ADMIN);

        // When
        AdminAccount updatedAccount = adminAccountRepository.saveAndFlush(adminAccount);

        // Then
        assertThat(updatedAccount)
                .hasFieldOrPropertyWithValue("userId", "uno")
                .hasFieldOrPropertyWithValue("roleTypes", Set.of(RoleType.DEVELOPER, RoleType.USER));
    }

    @DisplayName("회원 정보 delete 테스트")
    @Test
    void givenUserAccount_whenDeleting_thenWorksFine() {
        // Given
        long previousCount = adminAccountRepository.count();
        AdminAccount adminAccount = adminAccountRepository.getReferenceById("uno");

        // When
        adminAccountRepository.delete(adminAccount);

        // Then
        assertThat(adminAccountRepository.count()).isEqualTo(previousCount - 1);
    }


}