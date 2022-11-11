package socialnetwork.ui;
import java.io.*;


import socialnetwork.entities.Friendship;
import socialnetwork.entities.Utilizator;
import socialnetwork.service.UserService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
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
       try {
           System.out.println("Introduceti id-ul userului ce vreti sa l stergeti:");
           Scanner s= new Scanner(System.in);
           int option=s.nextInt();
           Long id=Long.valueOf(option);
           Utilizator delUser=service.remove(id);
           System.out.println(delUser.toString()+" a fost ster cu succes");
       }
       catch (RuntimeException ve){
           System.out.println("\u001B[31m"+"Invalid input"+"\u001B[0m");
       }
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
     try {
         service.addFriend(id1,id);
     }
     catch (RuntimeException ve){
         System.out.println("\u001B[31m"+"Invalid input"+"\u001B[0m");
     }
/*     catch (IllegalArgumentException ie){
         System.out.println(ie.toString());
     }*/

       // s1.close();
    }

    public void showFriends(){
       try {
           showUsers();
           System.out.println("Introduceti id-ul userului caruia doriti sa i vedeti prietenii:");
           Scanner s2= new Scanner(System.in);
           String option=s2.nextLine();
           Long id=Long.valueOf(option);
           List<Utilizator> users=service.getFriends(id);
/*           Map<Long, Friendship> friendshipMap= service.g
           for(Utilizator user:users){
               System.out.println(user.toString());
           }*/
       }
       catch (RuntimeException ve){
           System.out.println("\u001B[31m"+"Invalid input"+"\u001B[0m");
       }
        //s2.close();
    }


    public void deleteFriend(){
       try {
           showUsers();
           System.out.println("Introduceti id-ul userului caruia vreti sa i stergeti un prieten:");
           Scanner s2= new Scanner(System.in);
           String option=s2.nextLine();
           Long id=Long.valueOf(option);
/*           List<Utilizator> users=service.getFriends(id);
           for(Utilizator user:users){
               System.out.println(user.toString());
           }*/
           System.out.println("Introduceti id-ul prietenului ce doriti sa il stergeti:");
           String option1=s2.nextLine();
           Long id_friend=Long.valueOf(option1);
           service.deleteFriend(id_friend,id);
       }
       catch (RuntimeException ve){
           System.out.println("\u001B[31m"+ve.toString()+"\u001B[0m");
       }


    }
    public void graph_ui(){
        int [] lst =service.graf();
        //System.out.println("Numarul de componente conexe este: "+lst[0] );
        System.out.println("Cea mai marem comunitatea este:");
    //    service.biggestComunity();
        service.graf_friend();

    }
    public void run() throws IOException {
        while(true){

            System.out.println("\u001B[34m"+"1.Adauga utilizator nou");
            System.out.println("2.Afisati toti utilizatorii");
            System.out.println("3.Stergeti un urilizator");
            System.out.println("4.Adaugati un prieten");
            System.out.println("5.Afisati prietenii cuiva");
            System.out.println("6.Strgeeti un prieten");
            System.out.println("7.Graf");
            System.out.println("Optiunea dumneavoastra este:"+"\u001B[0m");
            //Scanner s= new Scanner(System.in);
          //  int option=s.nextInt();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int option;
            try{
                 option=Integer.parseInt(reader.readLine());
            }
            catch (IllegalArgumentException e){
                System.out.println("\u001B[31m"+"Invalid option"+"\u001B[0m");
                option=9;
            }

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
                case 6:
                    deleteFriend();
                    break;
                case 7:
                    graph_ui();
                    break;
                case 9:
                    break;
                default:
                    return;
            }
        }
    }


}
