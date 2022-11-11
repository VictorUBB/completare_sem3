package socialnetwork.repository.file;

import socialnetwork.domain.validators.ValidationException;
import socialnetwork.domain.validators.Validator;
import socialnetwork.entities.Friendship;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FriendshipFile extends AbstractFileRepository0<Long, Friendship> {
        public FriendshipFile(String fileName) {

                super(fileName, new Validator<Friendship>() {
                        @Override
                        public void validate(Friendship entity) throws ValidationException {

                        }
                });

        }

        @Override
        public Friendship extractEntity(List<String> attributes) {
                DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String time=attributes.get(3);
                Friendship friend= new Friendship(Long.valueOf(attributes.get(1)),Long.valueOf(attributes.get(2)), LocalDateTime.parse(time,formatter) );
                friend.setId(Long.valueOf(attributes.get(0)));
                return  friend;
        }

        @Override
        protected String createEntityAsString(Friendship entity) {
                DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                return entity.getId()+";"+entity.getId_1()+";"+ entity.getId_2()+";"+entity.getFriendsFrom().format(formatter);
        }



}

