package api.forohub.forohub.domain.user;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Retrieves a page of active users.
     *
     * @param pageable Pagination information.
     * @return A page of active users.
     */
    Page<User> findByActiveTrue(Pageable paged);

    /**
     * Retrieves a UserDetails object for a user based on username.
     *
     * @param username The username of the user to retrieve.
     * @return UserDetails object representing the user.
     */
    UserDetails findByUsername(String username);


}
