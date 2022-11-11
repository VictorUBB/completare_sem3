package socialnetwork.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Friendship extends Entity<Long> {
    private Long id_1;
    private Long id_2;
    private LocalDateTime friendsFrom;

    @Override
    public String toString() {
        return "Friendship{" +
                "id_1=" + id_1 +
                ", id_2=" + id_2 +
                ", friendsFrom=" + friendsFrom +
                '}';
    }

    public Friendship(Long id_1, Long id_2) {
        this.id_1 = id_1;
        this.id_2 = id_2;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime now=LocalDateTime.now();
        String nowT=now.format(formatter);
        LocalDateTime new_date=LocalDateTime.parse(nowT,formatter);
        this.friendsFrom =new_date;
    }

    public Friendship(Long id_1, Long id_2, LocalDateTime friendsFrom) {
        this.id_1 = id_1;
        this.id_2 = id_2;
        this.friendsFrom = friendsFrom;
    }

    public Long getId_1() {
        return id_1;
    }

    public void setId_1(Long id_1) {
        this.id_1 = id_1;
    }

    public Long getId_2() {
        return id_2;
    }

    public void setId_2(Long id_2) {
        this.id_2 = id_2;
    }

    public LocalDateTime getFriendsFrom() {
       // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return friendsFrom;
    }

    public void setFriendsFrom(LocalDateTime friendsFrom) {
        this.friendsFrom = friendsFrom;
    }
}
