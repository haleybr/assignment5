import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
//for this example, the names of the files are testDoc1.txt and testDoc2.txt
public class App {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.println("Will you be entering file names or integer? Type 1 for file names and type 2 for integer.");
        Scanner scanner = new Scanner(System.in);
        int type = scanner.nextInt();
        if (type == 1) {
          System.out.println("enter file name: ");
          String fileName1 = scan.next();
          File file1 = new File(fileName1);
          System.out.println("Enter second file name: ");
          String fileName2 = scan.next();
          File file2 = new File(fileName2);
          scan.close();
          int[][] array1 = readFromFile(file1);
          int[][] array2 = readFromFile(file2);
          if (array1[0].length != array2.length){
            System.out.println("Number of columns must equal to the number of rows");
            return;
          }
          int[][] product = multiply(array1, array2);
          System.out.println("result is: ");
          printProduct(product);
        } else if (type == 2){
          System.out.println("Enter the amount of rows: ");
          Scanner rowScanner = new Scanner(System.in);
          int rows = rowScanner.nextInt();
          rowScanner.close();
          int[][] randomArray1 = randomSquare(rows);
          int[][] randomArray2 = randomSquare(rows);
          int[][] product = multiply(randomArray1, randomArray2);
          System.out.println("result of random matrix generation is: ");
          printProduct(product);
          matrixFile(randomArray1, "matrix1.txt");
          matrixFile(randomArray2, "matrix2.txt");
        }
      }
//creates random numbers for the matrix in the arrays
    public static int[][] randomSquare(int rows) {
      int[][] numList = new int[rows][rows];
      Random random = new Random();
      for (int i = 0; i < rows; i++){
        for (int g = 0; g < rows; g++){
          numList[i][g] = random.nextInt(10);
        }
      }
      return numList;
    }
//runs through the array(randomArray1 or randomArray2) and then writes the lines to their respective matrix file. accounts for new lines as well.
    private static void matrixFile(int[][] matrix, String filename){
      try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))){
         for(int i = 0; i < matrix.length; i++) {
          for(int h = 0; h < matrix[i].length; h++) {
            writer.write(matrix[i][h] + " ");
          }
          writer.newLine();
         } 
      } catch (IOException e) {
        System.out.println("An error occurred");
        e.printStackTrace();
      }
    }
//reads the numbers into the arrays
    private static int[][] readFromFile(File file){
      ArrayList<int[]> numList = new ArrayList<>();
      try {
          Scanner readFile = new Scanner(file);
          while (readFile.hasNextLine()) {
            String num = readFile.nextLine();
            String[] numbers = num.trim().split("\\s+");
            int[] row = new int[numbers.length];
            for (int i = 0; i < numbers.length; i++){
              row[i] = Integer.parseInt(numbers[i]);
            }
            numList.add(row);
          }
          readFile.close();
      } catch (FileNotFoundException e) {
        System.out.println("An error occurred: File not found. ");
        e.printStackTrace();
      } catch (Exception e) {
        System.out.println("An error occurred while reading the file. ");
        e.printStackTrace();
      }
      int[][] matrix = new int[numList.size()][];
      return numList.toArray(matrix);
    }
//multiplies the numbers
    private static int[][] multiply(int[][] array1, int[][] array2){
      int rowNum1 = array1.length;
      int colNum1 = array1[0].length;
      int colNum2 = array2[0].length;
      int[][] product = new int[rowNum1][colNum2];
      for ( int i = 0; i < rowNum1; i++) {
        for (int r = 0; r < colNum2; r++) {
          for (int m = 0; m < colNum1; m++) {
            product[i][r] += array1[i][m] * array2[m][r];
          }
        }
      }
      return product;
    }
//prints result in terminal and saves to matrix3.txt
    private static void printProduct(int[][] numList){
      File newFile = new File("matrix3.txt");
      try (BufferedWriter writeToFile = new BufferedWriter(new FileWriter(newFile))){
        for (int[] y: numList) {
          for (int x : y) {
            System.out.print(x + " ");
            writeToFile.write(x + " ");
          }
          System.out.println();
          writeToFile.newLine();
  
        }
        System.out.println("Written to file, 'matrix3.txt' was successful");
          
      } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }
    }
      }
    
