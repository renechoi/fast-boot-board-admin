//package fast.boardadmin.config;
//
//import fast.boardadmin.domain.constant.RoleType;
//import fast.boardadmin.dto.AdminAccountDto;
//import fast.boardadmin.dto.security.BoardAdminPrincipal;
//import fast.boardadmin.service.AdminAccountService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.stream.Collectors;
//
///**
//스프잉 시큐리티의 UserDetailService를 구현하는 샘플 코드
// **/
//
//
//@Service
//public class MyUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private AdminAccountService adminAccountService;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) {
//        AdminAccountDto adminAccountDto = adminAccountService.searchUser(username).orElseThrow(()->new UsernameNotFoundException("user not found"));
//        BoardAdminPrincipal boardAdminPrincipal = BoardAdminPrincipal.from(adminAccountDto);
//
////        return new User(boardAdminPrincipal.getUsername(), boardAdminPrincipal.getPassword(), boardAdminPrincipal.getAuthorities());
//
//        return new User(adminAccountDto.userId(), adminAccountDto.userPassword(),
//                adminAccountDto.roleTypes().stream()
//                        .map(RoleType::getRoleName)
//                        .map(SimpleGrantedAuthority::new
//                ).collect(Collectors.toList()));
//    }
//
//    @Configuration
//    @EnableWebSecurity
//    public class SecurityConfig2 extends WebSecurityConfigurerAdapter {
//
//        @Autowired
//        private MyUserDetailsService customUserDetailsService;
//
//        @Override
//        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//            auth.userDetailsService(customUserDetailsService);
//        }
//
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http.authorizeRequests()
//                    .antMatchers("/admin/**").hasRole("ADMIN")
//                    .antMatchers("/user/**").hasRole("USER")
//                    .and()
//                    .formLogin()
//                    .and()
//                    .logout()
//                    .logoutSuccessUrl("/")
//                    .and()
//                    .csrf().disable();
//        }
//    }
//}
//
//
//
//
//
//
//
