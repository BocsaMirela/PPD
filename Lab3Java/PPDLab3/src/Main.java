import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Random;

public class Main {
    private static boolean okT1;
    private static boolean okT2;

    public static void main(String[] args) {
//       gain();
       course();
    }

    public static void gain(){
        try {
            FineGrainSynchronization sortedLinkedList = new FineGrainSynchronization();
            FileRepository fileRepository = new FileRepository("src\\logFine.txt");

            FineGrainSynchronization sortedLinkedList2 = new FineGrainSynchronization();
            FileRepository fileRepository2 = new FileRepository("src\\listaFine.txt");

            FileRepository fileRepositoryIT = new FileRepository("src\\iterationsFine.txt");

            test1(sortedLinkedList, fileRepository, fileRepositoryIT);

            long media = 0;
            for (int i = 0; i < 10; i++) {
                long timeBefore = System.currentTimeMillis();
//                test2(sortedLinkedList2, fileRepository2);
//                test1(sortedLinkedList2, fileRepository2, fileRepositoryIT);
                long timeAfter = System.currentTimeMillis();
                media += timeAfter - timeBefore;

            }
            double d = media / 10;
            System.out.println("TIMP FineGrainSynchronization " + d);

            fileRepository.getBufferedWriter().close();
            fileRepository2.getBufferedWriter().close();
            fileRepositoryIT.getBufferedWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void course() {
        try {
            CourseGrainSynchronization courseGrainSynchronization = new CourseGrainSynchronization();
            FileRepository fileRepositoryC = new FileRepository("src\\logCourse.txt");

            CourseGrainSynchronization sortedLinkedListCC = new CourseGrainSynchronization();
            FileRepository fileRepositoryCC = new FileRepository("src\\listaCourse.txt");

            FileRepository fileRepositoryITC = new FileRepository("src\\iterationsCourse.txt");

            test1(courseGrainSynchronization, fileRepositoryC, fileRepositoryITC);
            long media = 0;
            for (int i = 0; i < 10; i++) {
                long timeBefore = System.currentTimeMillis();
                test2(sortedLinkedListCC, fileRepositoryCC);
                long timeAfter = System.currentTimeMillis();
                media += timeAfter - timeBefore;

            }
            double d = media / 10;
            System.out.println("TIMP CourseGrainSynchronization " + d);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void test1(SortedLinkedList sortedLinkedList, FileRepository fileRepository, FileRepository itTxt) {
        okT1 = false;
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                Random random = new Random();
                double number = random.nextDouble();
                number = number % 100;
                sortedLinkedList.insert(number);
                String stringToWrite = "insert " + number + " Thread: " + Thread.currentThread().getId() + " at " + LocalDateTime.now() + "\n";
                try {
                    fileRepository.writeToFile(stringToWrite);
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
        });
        Thread thread2 = new Thread(() -> {

            for (int i = 0; i < 5; i++) {
                Random random = new Random();
                double number = random.nextDouble();
                sortedLinkedList.insert(number);
                String stringToWrite = "insert " + number + " Thread: " + Thread.currentThread().getId() + " at " + LocalDateTime.now() + "\n";
                try {
                    fileRepository.writeToFile(stringToWrite);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 7; i++) {
                Random random = new Random();
                double number = random.nextDouble();
                sortedLinkedList.remove(number);
                String stringToWrite = "DELETE " + number + "Thread: " + Thread.currentThread().getId() + " at " + LocalDateTime.now() + "\n";
                try {
                    fileRepository.writeToFile(stringToWrite);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread4 = new Thread(() -> {
            int it = 0;
            while (!okT1) {
                SortedLinkedList.Iteratorr iteratorr = sortedLinkedList.getIterator();
                while (iteratorr.hasNext()) {
                    System.out.println("ITERATE: iteratia nr: " + it + " NODE: " + iteratorr.next() + " at " + LocalDateTime.now());
                    try {
                        itTxt.writeToFile("ITERATE: iteratia nr: " + it + " NODE: " + iteratorr.next() + " at " + LocalDateTime.now() + " \n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println();
                try {
                    itTxt.writeToFile("\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                it++;
                try {
                    Thread.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            SortedLinkedList.Iteratorr iteratorr = sortedLinkedList.getIterator();
            while (iteratorr.hasNext()) {
                System.out.println("ITERATE: iteratia nr: " + it + " NODE: " + iteratorr.next() + " at " + LocalDateTime.now());
                try {
                    itTxt.writeToFile("ITERATE: iteratia nr: " + it + " NODE: " + iteratorr.next() + " at " + LocalDateTime.now() + " \n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        try {
            thread.start();
            thread2.start();
            thread3.start();
            thread4.start();
            thread.join();
            thread2.join();
            thread3.join();
            okT1 = true;
            thread4.join();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void test2(SortedLinkedList sortedLinkedListT2, FileRepository fileRepositoryT2) {
        okT2 = false;
        Thread thread11 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                Random random = new Random();
                double number = random.nextDouble();
                number = number % 100;
                sortedLinkedListT2.insert(number);
            }
        });
        Thread thread22 = new Thread(() -> {

            for (int i = 0; i < 50; i++) {
                Random random = new Random();
                double number = random.nextDouble();
                sortedLinkedListT2.insert(number);
            }
        });
        Thread thread33 = new Thread(() -> {
            for (int i = 0; i < 70; i++) {
                Random random = new Random();
                double number = random.nextDouble();
                sortedLinkedListT2.remove(number);
            }
        });
        Thread thread4 = new Thread(() -> {
            int it = 0;
            while (!okT2) {
                SortedLinkedList.Iteratorr iteratorr = sortedLinkedListT2.getIterator();
                while (iteratorr.hasNext()) {
                    try {
                        fileRepositoryT2.writeToFile("ITERATE: iteratia nr: " + it + " NODE: " + iteratorr.next() + " at " + LocalDateTime.now() + " \n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                it++;
                try {
                    fileRepositoryT2.writeToFile("\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (it == 1) break;
            }
            SortedLinkedList.Iteratorr iteratorr = sortedLinkedListT2.getIterator();
            while (iteratorr.hasNext()) {
                try {
                    fileRepositoryT2.writeToFile("ITERATE: iteratia nr: " + it + " NODE: " + iteratorr.next() + " at " + LocalDateTime.now() + " \n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        try {
            thread11.start();
            thread22.start();
            thread33.start();
            thread4.start();

            thread11.join();
            thread22.join();
            thread33.join();

            okT2 = true;

            thread4.join();


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
