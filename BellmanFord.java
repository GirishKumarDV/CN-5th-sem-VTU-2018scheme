import java.util.Scanner;

public class BellmanFord {
    private int D[], number_of_vertices;
    public static final int MAX_VALUE = 99;

    public BellmanFord(int number_of_vertices) {
        this.number_of_vertices = number_of_vertices;
        D = new int[number_of_vertices + 1];
    }

    public void BellmanFordEvaluation(int source, int adj_matrix[][]){
        for(int node = 1; node <= number_of_vertices ; node++){
            D[node] = MAX_VALUE;
        }
        D[source]= 0;

        //Relaxation of edges is done n-1 times, where n is the number of vertices
        for(int node=1;node<=number_of_vertices-1;node++){
            for(int source_node = 1 ; source_node <= number_of_vertices ; source_node++){
                for(int destination_node = 1; destination_node <= number_of_vertices ; destination_node++){
                    if(adj_matrix[source_node][destination_node] != MAX_VALUE){
                        if(D[destination_node] > D[source_node] + adj_matrix[source_node][destination_node])
                            D[destination_node] = D[source_node] + adj_matrix[source_node][destination_node];
                    }
                }
            }
        }

        for(int source_node = 1; source_node <= number_of_vertices ; source_node++){
            for(int destination_node = 1; destination_node <= number_of_vertices ; destination_node++){
                if(adj_matrix[source_node][destination_node] != MAX_VALUE){
                    if(D[destination_node] > D[source_node]+adj_matrix[source_node][destination_node]){
                        System.err.println("The Graph contains negative edge cycle!");
                        System.exit(0);
                    }
                }
            }
        }

        System.out.println("Distances are :");
        for(int vertex = 1 ; vertex <= number_of_vertices ; vertex++){
            System.out.println("From "+source+" to "+vertex+" is "+D[vertex]);
        }
    }

    public static void main(String args[]){
        int number_of_vertices = 0;
        int source;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of vertices :");
        number_of_vertices = sc.nextInt();
        int adj_matrix[][] = new int[number_of_vertices+1][number_of_vertices+1];
        System.out.println("\nEnter the Adjacency Matrix");
        for(int source_node = 1 ; source_node <= number_of_vertices ; source_node++){
            for(int destination_node = 1; destination_node <= number_of_vertices ; destination_node++){
                adj_matrix[source_node][destination_node] = sc.nextInt();
                if(source_node == destination_node){
                    adj_matrix[source_node][destination_node] = 0;
                    continue;
                }
                if(adj_matrix[source_node][destination_node] == 0)
                    adj_matrix[source_node][destination_node] = MAX_VALUE;
            }
        }

        System.out.println("Enter the Source Vertex");
        source = sc.nextInt();
        BellmanFord obj = new BellmanFord(number_of_vertices);
        obj.BellmanFordEvaluation(source, adj_matrix);
        sc.close();
    }
}
