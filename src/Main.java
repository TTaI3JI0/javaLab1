import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("Выберите операцию");
            System.out.println("1 - сложить");
            System.out.println("2 - умножить");
            System.out.println("3 - разделить");
            System.out.println("4 - транспонировать");
            System.out.println("5 - определитель");
            System.out.println("0 - выход");

            int choice = sc.nextInt();

            if (choice == 0) {
                break;
            }

            try {
                if (choice == 1) {
                    ComplexMatrix a = readMatrix(sc, "A");
                    ComplexMatrix b = readMatrix(sc, "B");
                    System.out.println("A + B:");
                    System.out.print(a.plus(b));
                } else if (choice == 2) {
                    ComplexMatrix a = readMatrix(sc, "A");
                    ComplexMatrix b = readMatrix(sc, "B");
                    System.out.println("A * B:");
                    System.out.print(a.mult(b));
                } else if (choice == 3) {
                    ComplexMatrix a = readMatrix(sc, "A");
                    ComplexMatrix b = readMatrix(sc, "B");
                    System.out.println("A / B:");
                    System.out.print(a.divide(b));
                } else if (choice == 4) {
                    ComplexMatrix a = readMatrix(sc, "A");
                    System.out.println("A транспонированная:");
                    System.out.print(a.transpose());
                } else if (choice == 5) {
                    ComplexMatrix a = readMatrix(sc, "A");
                    System.out.println("det(A) = " + a.det());
                } else {
                    System.out.println("Некорректный ввод");
                }
            } catch (IllegalArgumentException | ArithmeticException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
        sc.close();
    }

    public static ComplexMatrix readMatrix(Scanner sc, String name) {
        System.out.println("Матрица " + name);
        System.out.print("строк: ");
        int rows = sc.nextInt();
        System.out.print("столбцов: ");
        int cols = sc.nextInt();

        ComplexMatrix matrix = new ComplexMatrix(rows, cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(name + "[" + i + "][" + j + "] re = ");
                double re = sc.nextDouble();
                System.out.print(name + "[" + i + "][" + j + "] im = ");
                double im = sc.nextDouble();
                matrix.set(i, j, new Complex(re, im));
            }
        }

        return matrix;
    }
}
