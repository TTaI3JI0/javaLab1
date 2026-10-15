public class ComplexMatrix {
    private int rows;
    private int cols;
    private Complex[][] data;

    public ComplexMatrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.data = new Complex[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.data[i][j] = new Complex();
            }
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public Complex get(int i, int j) {
        return data[i][j];
    }

    public void set(int i, int j, Complex value) {
        data[i][j] = value;
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result = result + data[i][j];
                if (j < cols - 1) {
                    result = result + ", ";
                }
            }
            result = result + "\n";
        }
        return result;
    }

    public ComplexMatrix plus(ComplexMatrix other) {
        if (this.rows != other.rows || this.cols != other.cols) {
            throw new IllegalArgumentException("Размеры матриц не совпадают");
        }

        ComplexMatrix result = new ComplexMatrix(this.rows, this.cols);

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                Complex a = this.data[i][j];
                Complex b = other.data[i][j];
                Complex sum = a.plus(b);
                result.data[i][j] = sum;
            }
        }

        return result;
    }

    public ComplexMatrix mult(ComplexMatrix other) {
        if (this.cols != other.rows) {
            throw new IllegalArgumentException("Несовместимые размеры для умножения");
        }

        ComplexMatrix result = new ComplexMatrix(this.rows, other.cols);

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < other.cols; j++) {
                Complex sum = new Complex();

                for (int k = 0; k < this.cols; k++) {
                    Complex a = this.data[i][k];
                    Complex b = other.data[k][j];
                    Complex product = a.mult(b);
                    sum = sum.plus(product);
                }

                result.data[i][j] = sum;
            }
        }
        return result;
    }

    public ComplexMatrix transpose() {
        ComplexMatrix result = new ComplexMatrix(this.cols, this.rows);

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                Complex value = this.data[i][j];
                result.data[j][i] = value;
            }
        }

        return result;
    }
    public Complex det() {
        if (this.rows != this.cols) {
            throw new IllegalArgumentException("Определитель только для квадратной матрицы");
        }
        return detOf(this.data, this.rows);
    }

    private static Complex detOf(Complex[][] matrix, int n) {
        if (n == 1) {
            return matrix[0][0];
        }
        if (n == 2) {
            Complex a = matrix[0][0];
            Complex b = matrix[0][1];
            Complex c = matrix[1][0];
            Complex d = matrix[1][1];
            Complex ad = a.mult(d);
            Complex bc = b.mult(c);
            return ad.minus(bc);
        }

        Complex result = new Complex();

        for (int j = 0; j < n; j++) {
            Complex minor[][] = new Complex[n - 1][n - 1];

            for (int i = 1; i < n; i++) {
                int newJ = 0;
                for (int k = 0; k < n; k++) {
                    if (k == j) {
                        continue;
                    }
                    minor[i - 1][newJ] = matrix[i][k];
                    newJ = newJ + 1;
                }
            }

            Complex subDet = detOf(minor, n - 1);
            Complex term = matrix[0][j].mult(subDet);

            if (j % 2 == 0) {
                result = result.plus(term);
            } else {
                result = result.minus(term);
            }
        }
        return result;
    }

    public ComplexMatrix inverse() {
        if (this.rows != this.cols) {
            throw new IllegalArgumentException("Обратная только для квадратной матрицы");
        }

        Complex d = this.det();
        if (d.getRe() == 0 && d.getIm() == 0) {
            throw new ArithmeticException("Матрица вырождена, обратной нет");
        }

        ComplexMatrix result = new ComplexMatrix(this.rows, this.cols);

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {

                Complex[][] minor = new Complex[this.rows - 1][this.cols - 1];
                int newI = 0;

                for (int row = 0; row < this.rows; row++) {
                    if (row == i) {
                        continue;
                    }
                    int newJ = 0;
                    for (int col = 0; col < this.cols; col++) {
                        if (col == j) {
                            continue;
                        }
                        minor[newI][newJ] = this.data[row][col];
                        newJ = newJ + 1;
                    }
                    newI = newI + 1;
                }

                Complex minorDet = detOf(minor, this.rows - 1);

                if ((i + j) % 2 != 0) {
                    minorDet = minorDet.mult(new Complex(-1, 0));
                }

                Complex cofactor = minorDet.divide(d);
                result.data[j][i] = cofactor;
            }
        }

        return result;
    }

    public ComplexMatrix divide(ComplexMatrix other) {
        return this.mult(other.inverse());
    }
}
