package vn.techmaster.blog.DTO;

import javax.annotation.processing.Generated;
import vn.techmaster.blog.model.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-27T00:31:03+0700",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 17.0.3 (Oracle Corporation)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public UserInfo userToUserInfo(User user) {
        if ( user == null ) {
            return null;
        }

        UserInfo userInfo = new UserInfo();

        userInfo.setId( user.getId() );
        userInfo.setFullname( user.getFullname() );
        userInfo.setEmail( user.getEmail() );

        return userInfo;
    }

    @Override
    public User userInfoToUser(UserInfo userInfo) {
        if ( userInfo == null ) {
            return null;
        }

        User user = new User();

        user.setId( userInfo.getId() );
        user.setFullname( userInfo.getFullname() );
        user.setEmail( userInfo.getEmail() );

        return user;
    }
}
