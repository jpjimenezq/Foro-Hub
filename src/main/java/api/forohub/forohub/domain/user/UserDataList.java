package api.forohub.forohub.domain.user;

public record UserDataList (Long id,
                            String name,
                            String email){
    public UserDataList(User user){
        this(user.getId(),user.getName(),user.getEmail());
    }
}
