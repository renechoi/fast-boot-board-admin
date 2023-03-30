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

    private final UserAccountRepository userAccountRepository;

    public JpaRepositoryTest(@Autowired UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @DisplayName("회원 정보 select 테스트")
    @Test
    void test() {
        List<AdminAccount> adminAccounts = userAccountRepository.findAll();
        assertThat(adminAccounts).isNotNull().hasSize(4);
    }

    @Test
    void insert(){
        long previous = userAccountRepository.count();
        AdminAccount adminAccount = AdminAccount.of("test", "pw", Set.of(RoleType.DEVELOPER), "email", "nickname", "memo");

        userAccountRepository.save(adminAccount);

        assertThat(userAccountRepository.count()).isEqualTo(previous+1);
    }

    @DisplayName("회원 정보 update 테스트")
    @Test
    void givenUserAccountAndRoleType_whenUpdating_thenWorksFine() {
        // Given
        AdminAccount adminAccount = userAccountRepository.getReferenceById("uno");
        adminAccount.addRoleType(RoleType.DEVELOPER);
        adminAccount.addRoleTypes(List.of(RoleType.USER, RoleType.USER));
        adminAccount.removeRoleType(RoleType.ADMIN);

        // When
        AdminAccount updatedAccount = userAccountRepository.saveAndFlush(adminAccount);

        // Then
        assertThat(updatedAccount)
                .hasFieldOrPropertyWithValue("userId", "uno")
                .hasFieldOrPropertyWithValue("roleTypes", Set.of(RoleType.DEVELOPER, RoleType.USER));
    }

    @DisplayName("회원 정보 delete 테스트")
    @Test
    void givenUserAccount_whenDeleting_thenWorksFine() {
        // Given
        long previousCount = userAccountRepository.count();
        AdminAccount adminAccount = userAccountRepository.getReferenceById("uno");

        // When
        userAccountRepository.delete(adminAccount);

        // Then
        assertThat(userAccountRepository.count()).isEqualTo(previousCount - 1);
    }


}