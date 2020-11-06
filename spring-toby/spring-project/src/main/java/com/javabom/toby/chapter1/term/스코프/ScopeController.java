package com.javabom.toby.chapter1.term.스코프;

import com.javabom.toby.chapter1.term.IoC_용어정리.User;
import com.javabom.toby.chapter1.term.IoC_용어정리.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class ScopeController {
    private final UserRepository requestUserRepository;
    private final UserRepository sessionUserRepository;

    /**
     * 요청스코프: 요청마다 새로운 인스턴스를 생성하여 사용함
     */
    @GetMapping("/request-scope")
    public ResponseEntity<Void> get() {
        requestUserRepository.save(new User());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 세션스코프: 한 세션에 대해 같은 빈을 사용함
     */
    @GetMapping("/session-scope")
    public ResponseEntity<Void> session() {
        sessionUserRepository.save(new User());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*
     * 세션을 끊는 요청
     */
    @GetMapping("/session-expire")
    public ResponseEntity<Void> sessionExpire(HttpServletRequest request) {
        request.getSession().invalidate();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
