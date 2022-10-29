package socialnetwork.ui;
import java.io.*;


import socialnetwork.domain.Utilizator;
import socialnetwork.service.UserService;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class CRUD_ui {
    private UserService<Long, Utilizator> service=new UserService<>();

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

    public void addFriend(){
        showUsers();
        System.out.println("Introduceti id-ul userului caruia doriti sa i adaugati un prieten:");
        Scanner s1= new Scanner(System.in);
        String option=s1.nextLine();
        Long id=Long.valueOf(option);
        System.out.println("Introduceti id-ul noului prieten:");
        String option1=s1.nextLine();
        Long id1=Long.valueOf(option1);
        service.addFriend(id1,id);
       // s1.close();
    }

    public void showFriends(){
        showUsers();
        System.out.println("Introduceti id-ul userului caruia doriti sa i vedeti prietenii:");
        Scanner s2= new Scanner(System.in);
        String option=s2.nextLine();
        Long id=Long.valueOf(option);
        List<Utilizator> users=service.getFriends(id);
        for(Utilizator user:users){
            System.out.println(user.toString());
        }
        //s2.close();
    }
    public void run() throws IOException {
        while(true){

            System.out.println("1.Adauga utilizator nou");
            System.out.println("2.Afisati toti utilizatorii");
            System.out.println("3.Stergeti un urilizator");
            System.out.println("4.Adaugati un prieten");
            System.out.println("5.Afisati prietenii cuiva");
            System.out.println("Optiunea dumneavoastra este:");
            //Scanner s= new Scanner(System.in);
          //  int option=s.nextInt();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int option=Integer.parseInt(reader.readLine());
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
                case 4:
                    addFriend();
                    break;
                case 5:
                    showFriends();
                    break;
                default:
                    return;
            }
        }
    }


}
