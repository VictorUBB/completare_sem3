package socialnetwork.service;

import socialnetwork.domain.Entity;
import socialnetwork.domain.Utilizator;
import socialnetwork.domain.validators.UtilizatorValidator;
import socialnetwork.repository.memory.InMemoryRepository0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class UserService<ID, E extends Entity<ID>> {
    private InMemoryRepository0<Long,Utilizator> repository;

    private Long id;
    public UserService() {
        this.repository = new InMemoryRepository0<Long,Utilizator>(new UtilizatorValidator());
        this.id= 0L;
    }

    public void add() throws IOException {
        repository.save(readUser());
    }

    public void addFriend(Long id,Long user_id){
        Utilizator friend = repository.findOne(id);
        Utilizator user=repository.findOne(user_id);
        user.addFriend(friend);
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
      return user;
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
}
