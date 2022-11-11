package socialnetwork.graf;

import socialnetwork.entities.Utilizator;

import java.util.ArrayList;
import java.util.Map;

public class Graf {
        int V;
        ArrayList<ArrayList<Integer>> adjList;

    public Graf(int v ) {
        V = v;
        this.adjList = new ArrayList<>();
        for(int i=0;i<V;i++){
            adjList.add(i,new ArrayList<>());
        }

    }

    public void addEdge(int src,int dest){
        adjList.get(src).add(dest);
        adjList.get(dest).add(src);
    }

   public int DFS(int v,boolean[] visit,int lenght){
        visit[v]=true;
       // System.out.print(v+" ");
       lenght++;
        for(int x:adjList.get(v)){
            if (!visit[x])
                DFS(x,visit,lenght);
        }
       return lenght;
    }
    public int[] connect(){
        int count=0;
        int max=0;
        int vmax=0;
        boolean[] visit=new boolean[ V];
        for(int v=0;v<V;v++){
            int lenght=0;
            if(!visit[v]){

                lenght=DFS(v,visit,lenght);
                if(lenght>max){
                    max=lenght;
                    vmax=v;
                }
                count++;
            }

            //System.out.println();

        }
        int [] lst=new int[4];
        lst[0]=count;
        lst[1]=max;
        lst[2]=vmax;
        return lst;
    }

    public void showcomp(int v, Map<Long,Utilizator> users){
        ArrayList<Integer> lst=adjList.get(v);
        System.out.println(users.get(Long.valueOf(v)).toString());
        boolean[] visit=new boolean[ V];
        visit[v]=true;
        int remember=0;
        while(lst.size()>0){
            for(int x:lst)
                if (!visit[x]){
                    System.out.println(users.get(Long.valueOf(x)).toString());
                    visit[x]=true;
                    remember=1;
                }
            if(remember==1){
                break;
            }
            int c=lst.get(1);
            lst=adjList.get(c);
        }
    }

}
