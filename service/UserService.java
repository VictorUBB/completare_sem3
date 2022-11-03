package socialnetwork.service;

import socialnetwork.domain.Entity;
import socialnetwork.domain.Utilizator;
import socialnetwork.domain.validators.UtilizatorValidator;
import socialnetwork.graf.Graf;
import socialnetwork.repository.memory.InMemoryRepository0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

public class UserService<ID, E extends Entity<ID>> {
    private InMemoryRepository0<Long,Utilizator> repository;

    private Long id;
    public UserService() {
        this.repository = new InMemoryRepository0<Long,Utilizator>(new UtilizatorValidator());
        this.id= 3L;
        populate();
    }

    public void add() throws IOException {
        repository.save(readUser());
    }

    public void addFriend(Long id,Long user_id){
        Utilizator friend = repository.findOne(id);
        Utilizator user=repository.findOne(user_id);
        user.addFriend(friend);
        friend.addFriend(user);
    }

    public List<Utilizator> getFriends(Long id){
        Utilizator user=repository.findOne(id);
        return user.getFriends();
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

      return user;
    }

    public void deleteFriend(Long id,Long user_id){
        Utilizator friend = repository.findOne(id);
        Utilizator user=repository.findOne(user_id);
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
