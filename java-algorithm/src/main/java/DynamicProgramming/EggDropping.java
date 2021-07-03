package DynamicProgramming;

/**
 * DynamicProgramming solution for the Egg Dropping Puzzle
 * https://www.cnblogs.com/EIMadrigal/p/12545868.html
 */
public class EggDropping {

    // min trials with n eggs and m floors 

    private static int minTrials(int n, int m) {
        System.out.println("n=" + n + ",m = " + m);
        int[][] eggFloor = new int[n + 1][m + 1];
        int result, x;

        for (int i = 1; i <= n; i++) {
            eggFloor[i][0] = 0;   // Zero trial for zero floor.
            eggFloor[i][1] = 1;   // One trial for one floor 
        }
        System.out.println("----1--------");
        printArr(eggFloor,n+1,m+1);

        // j trials for only 1 egg

        for (int j = 1; j <= m; j++)
            eggFloor[1][j] = j;
        System.out.println("----2--------");
        printArr(eggFloor,n+1,m+1);
        // Using bottom-up approach in DP

        for (int i = 2; i <= n; i++) {
            System.out.println("----二-----begin---i="+i);
            printArr(eggFloor,n+1,m+1);
            for (int j = 2; j <= m; j++) {
                //eggFloor[i][j] = Integer.MAX_VALUE;
                eggFloor[i][j] = 999;
                //printArr(eggFloor,n+1,m+1);
                System.out.println("----三--------i="+i+",j="+j);
                for (x = 1; x <= j; x++) {
                    System.out.println("----四--------x="+x);
                    System.out.println("----四--------eggFloor["+(i - 1)+"]["+(x - 1)+"]="+eggFloor[i - 1][x - 1]+",eggFloor["+i+"]["+(j - x)+"]="+eggFloor[i][j - x]);
                    result = 1 + Math.max(eggFloor[i - 1][x - 1], eggFloor[i][j - x]);
                    System.out.println("----四--------result=" + result+",eggFloor["+i+"]["+j+"]="+eggFloor[i][j]);
                    // choose min of all values for particular x
                    if (result < eggFloor[i][j]){
                        eggFloor[i][j] = result;
                        //printArr(eggFloor,n+1,m+1);
                    }
                }
                System.out.println("----三-----finish---i="+i+",j="+j);
                printArr(eggFloor,n+1,m+1);
            }
            System.out.println("----二-----end---i="+i);
            printArr(eggFloor,n+1,m+1);
        }
        System.out.println("----五--------finish");
        printArr(eggFloor,n+1,m+1);
        return eggFloor[n][m];
    }

    private static void printArr(int arr[][],int m, int n){

        for(int i=0;i<m;i++)
        {
            for(int j=0;j<n;j++)
            {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println("\n");
        }
    }


    public static void main(String args[]) {
        int n = 6, m = 8;
        // result outputs min no. of trials in worst case for n eggs and m floors
        int result = minTrials(n, m);
        System.out.println(result);

        /**begin-----
        int arr[][]=new int[4][6];
        //数组中未赋值部分默认为0
        arr[1][2]=1;
        arr[2][1]=2;
        arr[2][3]=3;
        printArr(arr,4,6);
        end-------------*/
    }
}
