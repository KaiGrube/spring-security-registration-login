package org.grube.springsecurityregistrationlogin.appuser;

// using spring's implementation here to use readOnly property of @Transactional-annotation
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface AppUserRepository {
    Optional<AppUser> findByEmail(String email);
}
