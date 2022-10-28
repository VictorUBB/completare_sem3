package socialnetwork.ui;

import socialnetwork.domain.Entity;
import socialnetwork.domain.Utilizator;
import socialnetwork.service.Service;

import java.io.IOException;
import java.util.Scanner;

public class CRUD_ui {
    private Service<Long, Utilizator> service=new Service<>();

    public void showUsers(){
        Iterable<Utilizator> users=service.getAll();
        for(Utilizator user:users){
            System.out.println(user.toString());
        }

    }

    public void delteUser(){
        System.out.println("Introduceti id-ul userului ce vreti sa l stergeti:");
        Scanner s= new Scanner(System.in);
        int option=s.nextInt();
        Long id=Long.valueOf(option);
        Utilizator delUser=service.remove(id);
        System.out.println(delUser.toString()+" a fost ster cu succes");
    }
    public void run() throws IOException {
        while(true){

            System.out.println("1.Adauga utilizator nou");
            System.out.println("2.Afisati toti utilizatorii");
            System.out.println("3.Stergeti un urilizator");
            System.out.println("Optiunea dumneavoastra este:");
            Scanner s= new Scanner(System.in);
            int option=s.nextInt();
            switch (option){
                case 1:
                    service.add();
                    break;
                case 2:
                    showUsers();
                    break;
                case 3:
                    delteUser();
                    break;
                default:
                    return;
            }
        }
    }


}
