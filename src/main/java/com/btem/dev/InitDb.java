package com.btem.dev;

import com.btem.dev.app.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void dbInit1() {
            System.out.println("Init1" + this.getClass());
            User user = createUser("tmbaek", "백태민", "Setsuna16!", "010-3239-4127", "tmbaek@lgcns.com");
            em.persist(user);
        }

        User createUser(String id, String name, String pwd, String phoneNumb, String emailAddr) {
            return new User(id, name, pwd, phoneNumb, emailAddr);
        }
    }
}

