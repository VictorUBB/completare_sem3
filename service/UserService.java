package socialnetwork.service;

import socialnetwork.entities.Entity;
import socialnetwork.entities.Friendship;
import socialnetwork.entities.Utilizator;
import socialnetwork.domain.validators.UtilizatorValidator;
import socialnetwork.graf.Graf;
import socialnetwork.repository.file.FriendshipFile;
import socialnetwork.repository.file.UtilizatorFile0;

import javax.xml.validation.Validator;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserService<ID, E extends Entity<ID>> {
    //private InMemoryRepository0<Long,Utilizator> repository;
    private UtilizatorFile0 repository;
    private FriendshipFile repoFriend;
    private Long id;
    private Long id_friendship;
    public UserService() {
       // this.repository = new InMemoryRepository0<Long,Utilizator>(new UtilizatorValidator());
        this.repository=new UtilizatorFile0("C:\\Users\\Victor\\Desktop\\faculta\\MAP\\completare_sem3\\repository\\file\\users.txt",new UtilizatorValidator());
        this.repoFriend=new FriendshipFile("C:\\Users\\Victor\\Desktop\\faculta\\MAP\\completare_sem3\\repository\\file\\friens.txt");
        Map<Long,Utilizator> users= repository.getALl();
        int max=-1;
        for(Long id: users.keySet()){
            if(id.intValue()>max){
                max=id.intValue();
            }
        }
        max++;
        int lung=max;
        this.id= Long.valueOf(lung);
        lung=repoFriend.getALl().size();
        this.id_friendship=Long.valueOf(lung);
      //  populate();
    }

    public void add() throws IOException {
        repository.save(readUser());
    }

    public void addFriend(Long id,Long user_id){
        Utilizator friend = repository.findOne(id);
        Utilizator user=repository.findOne(user_id);
        Friendship fr=new Friendship(id,user_id);
        id_friendship++;
        fr.setId(id_friendship);
        repoFriend.save(fr);
        user.addFriend(friend);
        friend.addFriend(user);
    }

    public List<Utilizator> getFriends(Long id){
        Utilizator user=repository.findOne(id);
        Map<Long,Friendship> friens=repoFriend.getALl();
        List<Utilizator> utilizators=new ArrayList<>();
        for (Friendship friendship: friens.values()){
            if(friendship.getId_1()==id){
                utilizators.add(repository.findOne(friendship.getId_2()));
                System.out.println(repository.findOne(friendship.getId_2())+" friends from: "+friendship.getFriendsFrom().toString());
            }
            else if(friendship.getId_2()==id){
                utilizators.add(repository.findOne(friendship.getId_1()));
                System.out.println(repository.findOne(friendship.getId_1())+" friends from: "+friendship.getFriendsFrom().toString());
            }
        }
        return utilizators;
    }
    public Iterable<Utilizator> getAll(){
        return repository.findAll();
    }

    public Utilizator remove(Long id){

      Utilizator user= repository.delete(id);
      for(Utilizator users: repository.findAll()){
          List<Utilizator> entities=users.getFriends();
          entities.remove(user);
          }
        Map<Long,Friendship> friens=repoFriend.getALl();
        for(Friendship friendship:friens.values()){
            if (friendship.getId_1()==id){
                repoFriend.delete(friendship.getId());
                break;
            }
            if (friendship.getId_2()==id){
                repoFriend.delete(friendship.getId());
                break;
            }
        }
      return user;
    }

    public void deleteFriend(Long id,Long user_id){
        Utilizator friend = repository.findOne(id);
        Utilizator user=repository.findOne(user_id);
        Map<Long,Friendship> friens=repoFriend.getALl();
        for(Friendship friendship:friens.values()){
            if (friendship.getId_1()==id&&friendship.getId_2()==user_id){
                repoFriend.delete(friendship.getId());
                break;
            }
            if (friendship.getId_2()==id&&friendship.getId_1()==user_id){
                repoFriend.delete(friendship.getId());
                break;
            }
        }
        user.deleteFriend(friend);
    }
    private Utilizator readUser() throws IOException {
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Introduceti numele:");
        String firstName=reader.readLine();
        System.out.println("Introduceti prenumele:");
        String secondName=reader.readLine();
        Utilizator user=new Utilizator(firstName,secondName);
        user.setId(id);
        id++;
        return user;
    }
    private void populate(){
       Utilizator u1=new Utilizator("ion","Popescu");
       u1.setId(0L);
        repository.save(u1);
        Utilizator u2=new Utilizator("dan","Marian");
        u2.setId(1L);
        repository.save(u2);
        Utilizator u3=new Utilizator("ana","Maria");
        u3.setId(2L);
       repository.save(u3);
    }

    public int[] graf(){
        Map<Long,Utilizator> users=repository.getALl();
        Graf graf=new Graf(users.size());
        for(Utilizator user:users.values()){
            for(Utilizator friend : user.getFriends()){
                graf.addEdge(user.getId().intValue(),friend.getId().intValue());
            }
        }
        int [] number=graf.connect();
        return number;
    }

    public  void graf_friend(){
        Map<Long,Utilizator> users=repository.getALl();

        Graf graf=new Graf(id.intValue());
        Map<Long,Friendship> friendshipMap=repoFriend.getALl();
        for(Friendship friendship:friendshipMap.values()){
            graf.addEdge(friendship.getId_1().intValue(),friendship.getId_2().intValue());

        }
        int[] number=graf.connect();
        System.out.println("Numarul de componente conexe este: "+number[0] );
        graf.showcomp(number[2],users );
    }

    public void biggestComunity(){
        Map<Long,Utilizator> users=repository.getALl();
        Graf graf=new Graf(users.size());
        for(Utilizator user:users.values()){
            for(Utilizator friend : user.getFriends()){
                graf.addEdge(user.getId().intValue(),friend.getId().intValue());
            }
        }
        int [] number=graf.connect();
        graf.showcomp(number[2], users);
    }
}
