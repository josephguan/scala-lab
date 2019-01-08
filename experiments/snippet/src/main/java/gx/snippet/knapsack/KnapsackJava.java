/*
 * Copyright (c) 2018 josephguan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package gx.snippet.knapsack;


public class KnapsackJava {

    int pack(int maxCore, int maxMemory, Task[] tasks) {

        int i, j;
        int number = tasks.length;

        // copy all tasks
        Task[] allTasks = new Task[number + 1];
        allTasks[0] = new Task(0, 0, 0);
        for (i = 1; i <= number; i++) {
            allTasks[i] = tasks[i - 1];
        }

        // init
        int[][] V = new int[number + 1][maxCore + 1];
        for (i = 0; i <= number; i++)
            for (j = 1; j <= maxCore; j++)
                V[i][j] = 0;


        // algorithm
        for (i = 1; i <= number; i++) {
            for (j = 1; j <= maxCore; j++) {
                if (j < allTasks[i].core()) {
                    V[i][j] = V[i - 1][j];
                } else {
                    if (V[i - 1][j] > V[i - 1][j - allTasks[i].core()] + allTasks[i].value()) {
                        V[i][j] = V[i - 1][j];
                    } else {
                        V[i][j] = V[i - 1][j - allTasks[i].core()] + allTasks[i].value();
                    }
                }
            }
        }

        // for debug
        for (i = 0; i <= number; i++) {
            for (j = 0; j <= maxCore; j++) {
                System.out.print(V[i][j] + " ");
            }
            System.out.print("\n");
        }


        return 0;
    }


    int packBetter(int maxCore, int maxMemory, Task[] tasks) {

        int i, j;
        int number = tasks.length;

        // copy all tasks
        Task[] allTasks = new Task[number + 1];
        allTasks[0] = new Task(0, 0, 0);
        for (i = 1; i <= number; i++) {
            allTasks[i] = tasks[i - 1];
        }

        // init
        int[] V = new int[maxCore + 1];
        for (j = 1; j <= maxCore; j++)
            V[j] = 0;


        // algorithm
        for (i = 1; i <= number; i++) {
            for (j = maxCore; j >= 0; j--) {
                if (j - allTasks[i].core() >= 0 && V[j] <= V[j - allTasks[i].core()] + allTasks[i].value()) {
                    V[j] = V[j - allTasks[i].core()] + allTasks[i].value();
                }
            }
            // for debug
            for (int n = 0; n <= maxCore; n++) {
                System.out.print(V[n] + " ");
            }
            System.out.print("\n");
        }

        System.out.print("----------------\n");
        // for debug
        for (j = 0; j <= maxCore; j++) {
            System.out.print(V[j] + " ");
        }


        return 0;
    }


    int pack2D(int maxCore, int maxMemory, Task[] tasks) {

        int i, j, k;
        int number = tasks.length;

        // copy all tasks
        Task[] allTasks = new Task[number + 1];
        allTasks[0] = new Task(0, 0, 0);
        for (i = 1; i <= number; i++) {
            allTasks[i] = tasks[i - 1];
        }

        // init
        int[][][] V = new int[number + 1][maxCore + 1][maxMemory + 1];
        for (i = 0; i <= number; i++)
            for (j = 0; j <= maxCore; j++)
                for (k = 0; k <= maxMemory; k++)
                    V[i][j][k] = 0;


        // algorithm
        for (i = 1; i <= number; i++) {
            for (j = 1; j <= maxCore; j++) {
                for (k = 1; k <= maxMemory; k++) {
                    if (j < allTasks[i].core() || k < allTasks[i].memory()) {
                        V[i][j][k] = V[i - 1][j][k];
                    } else {
                        if (V[i - 1][j][k] > V[i - 1][j - allTasks[i].core()][k - allTasks[i].memory()] + allTasks[i].value()) {
                            V[i][j][k] = V[i - 1][j][k];
                        } else {
                            V[i][j][k] = V[i - 1][j - allTasks[i].core()][k - allTasks[i].memory()] + allTasks[i].value();
                        }
                    }
                }
            }
        }

        // for debug
        for (i = 0; i <= number; i++) {
            for (j = 0; j <= maxCore; j++) {
                for (k = 0; k <= maxMemory; k++) {
                    System.out.print(V[i][j][k] + " ");
                }
                System.out.print("\n");
            }
            System.out.print("\n");
        }


        return 0;
    }


    int pack2DBetter(int maxCore, int maxMemory, Task[] tasks) {

        int i, j, k;
        int number = tasks.length;

        // copy all tasks
        Task[] allTasks = new Task[number + 1];
        allTasks[0] = new Task(0, 0, 0);
        for (i = 1; i <= number; i++) {
            allTasks[i] = tasks[i - 1];
        }

        // init
        int[][] V = new int[maxCore + 1][maxMemory + 1];
        for (j = 0; j <= maxCore; j++)
            for (k = 0; k <= maxMemory; k++)
                V[j][k] = 0;


        // algorithm
        for (i = 1; i <= number; i++) {
            for (j = maxCore; j >= 0; j--) {
                for (k = maxMemory; k >= 0; k--) {
                    if (j - allTasks[i].core() >= 0 && k - allTasks[i].memory() >= 0 && V[j][k] <= V[j - allTasks[i].core()][k - allTasks[i].memory()] + allTasks[i].value()) {
                        V[j][k] = V[j - allTasks[i].core()][k - allTasks[i].memory()] + allTasks[i].value();
                    }
                }
            }
        }

        // for debug
//        for (j = 0; j <= maxCore; j++) {
//            for (k = 0; k <= maxMemory; k++) {
//                System.out.print(V[j][k] + " ");
//            }
//            System.out.print("\n");
//        }
//        System.out.print("\n");


        return 0;
    }
}
