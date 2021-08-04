package spring.study.security.form;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Slf4j
@Controller
public class SampleController {
    @GetMapping("/")
    public String index(Model model, Principal principal) {
        model.addAttribute("message", (principal == null) ? "Hello Spring Security" : "Hello" + principal.getName());
        return "index";
    }

    @GetMapping("/info")
    public String info(Model model) {
        model.addAttribute("message", "Hello Spring Security");
        return "info";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        model.addAttribute("message", "Hello" + principal.getName());


//        form 로그인 요청시 폼 인증을 처리하는 usernamepasswordauthentication필터의 추상클래스인
//        AbstractAuthenticationProcessingFilter의 doFilter 수행, doFilter에서
//        usernamepasswordauthentication필터의 attemptAuthentication메서드 호출
//        attemptAuthentication메서드에서 authenticationmanager의 authentication메서드를 호출하여 authentication객체를 리턴
//        authentication객체를 리턴받은 doFilter메서드는 successfulAuthentication메서드를 호출
//        successfulAuthentication메서드는 authentication객체를 가지고 securityContext를 만들고,
//        securityContextHolder를 사용해 threadLocal에 저장
//        요청이 종료 될 때 SecurityContextPersistenceFilter 은 securityContextHolder를 사용해 threadLocal에 저장중인
//        securityContext를 session에 저장
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        log.info(userDetails.getUsername());
        log.info(userDetails.getAuthorities().toString());
        log.info(authentication.getCredentials() == null ? "null" : "");
        log.info(authentication.isAuthenticated() ? "true" : "false");
        return "dashboard";
    }

    @GetMapping("/admin")
    public String admin(Model model, Principal principal, HttpServletRequest request) {
        model.addAttribute("message", "Hello" + principal.getName());
        request.getSession(false).getAttributeNames().asIterator().forEachRemaining(s -> {
            log.info(s + " = " + request.getSession().getAttribute(s));
        });
        return "admin";
    }

//    @GetMapping("/login")
//    public String login(Model model) {
//        model.addAttribute("message", "Hello Spring Security");
//        return "index";
//    }
}
