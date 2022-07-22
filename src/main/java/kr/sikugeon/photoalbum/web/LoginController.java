package kr.sikugeon.photoalbum.web;

import kr.sikugeon.photoalbum.core.user.application.UserPasswordVerifier;
import kr.sikugeon.photoalbum.core.user.application.UserRegistration;
import kr.sikugeon.photoalbum.core.user.domain.User;
import kr.sikugeon.photoalbum.core.user.domain.UserEntityNotFoundException;
import kr.sikugeon.photoalbum.core.user.domain.UserPasswordNotMatchedException;
import kr.sikugeon.photoalbum.security.UserSession;
import kr.sikugeon.photoalbum.security.UserSessionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.Objects;

@Controller
public class LoginController {
    
    private final UserPasswordVerifier verifier;
    private final UserRegistration registration;
    private final UserSessionRepository userSessionRepository;
    private final Logger log = LoggerFactory.getLogger(getClass());
    
    public LoginController(UserPasswordVerifier verifier, UserRegistration registration, UserSessionRepository userSessionRepository) {
        this.verifier = Objects.requireNonNull(verifier);
        this.registration = Objects.requireNonNull(registration);
        this.userSessionRepository = Objects.requireNonNull(userSessionRepository);
    }

    @GetMapping("/login")
    public String loginForm() {
        if (Objects.nonNull(userSessionRepository.get())) {
            return "redirect:/todos"; 
        }
        return "login";
    }
    
    @PostMapping("/login")    
    public String loginProcess(@Valid LoginCommand command, Model model) {
        log.debug("login command: {}", command);
        log.debug("jdlkfaj");


        User user;
        try {
            // 1. 사용자 저장소에 사용자가 있을 경우: 비밀번호 확인 후 로그인 처리
            user = verifier.verify(command.getUsername(), command.getPassword());
        } catch (UserEntityNotFoundException error) {
            // 2. 사용자가 없는 경우: 회원가입 처리 후 로그인 처리
            user = registration.join(command.getUsername(), command.getPassword());
        } catch (UserPasswordNotMatchedException error) {
            // 3. 비밀번호가 틀린 경우: login 페이지로 돌려보내고, 오류 메시지 노출
            model.addAttribute("message", error.getMessage());
            return "login";
        }
        userSessionRepository.set(new UserSession(user));
        
        return "redirect:/todos";
    }
    
    @RequestMapping("/logout")
    public View logout() {
        userSessionRepository.clear();
        return new RedirectView("/todos");
    }
    
    /*
     * 입력 값 검증에 실패한 경우: login 페이지로 돌려보내고, 오류 메시지 노출
     */
    @ExceptionHandler(BindException.class)
    public String handleBindException(BindException error, Model model) {
        model.addAttribute("bindingResult", error.getBindingResult());
        model.addAttribute("message", "입력 값이 없거나 올바르지 않아요.");
        return "login";
    }
    
    static class LoginCommand {
        @Size(min = 4, max = 20)
        String username;
        String password;
        
        public String getUsername() {
            return username;
        }
        public void setUsername(String username) {
            this.username = username;
        }
        public String getPassword() {
            return password;
        }
        public void setPassword(String password) {
            this.password = password;
        }
        
        @Override
        public String toString() {
            return String.format("[username=%s, password: %s]", username, password);
        }
    }

}
