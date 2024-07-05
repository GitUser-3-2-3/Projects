use std::io;
use std::io::Write;

struct Matrix {
    rows: usize,
    cols: usize,
    data: Vec<f64>,
}

impl Matrix {
    fn new(rows: usize, cols: usize, data: Vec<f64>) -> Self {
        assert_eq!(data.len(), rows * cols, "Data does not match matrix dimensions.");
        Self { rows, cols, data }
    }

    fn _zeros(rows: usize, cols: usize, data: Vec<f64>) -> Self {
        assert_eq!(data.len(), rows * cols, "Data does not match matrix dimensions.");
        Self { rows, cols, data }
    }

    fn _identity(size: usize, mut data: Vec<f64>) -> Self {
        for i in 0..size {
            data[i * size + i] = 1.0;
        }
        Self { rows: size, cols: size, data }
    }

    fn add(&self, other: &Matrix) -> Result<Matrix, &'static str> {
        if self.rows != other.rows || self.cols != other.cols {
            return Err("Matrices must have the same dimensions to be added.");
        }

        let data: Vec<f64> = self.data.iter()
            .zip(&other.data)
            .map(|(a, b)| a + b)
            .collect();
        return Ok(Matrix::new(self.rows, self.cols, data));
    }

    fn subtract(&self, other: &Matrix) -> Result<Matrix, &'static str> {
        if self.rows != other.rows || self.cols != self.cols {
            return Err("Matrices must have the same dimensions to be added.");
        }

        let data: Vec<f64> = self.data.iter()
            .zip(&other.data)
            .map(|(a, b)| a - b)
            .collect();
        return Ok(Matrix::new(self.rows, self.cols, data));
    }

    fn multiply(&self, other: &Matrix) -> Result<Matrix, &'static str> {
        if self.rows != other.rows || self.cols != self.cols {
            return Err("Matrices must have the same dimensions to be added.");
        }

        let mut data = vec![0.0; self.rows * other.cols];
        for i in 0..self.rows {
            for j in 0..other.cols {
                for k in 0..self.cols {
                    data[i * other.cols + j] += self.data[i * self.cols + k] *
                        other.data[k * other.cols + j];
                }
            }
        }
        return Ok(Matrix::new(self.rows, self.cols, data));
    }

    fn display(&self) {
        for i in 0..self.rows {
            for j in 0..self.cols {
                print!("{:8.2} ", self.data[i * self.cols + j]);
            }
            println!();
        }
    }
}

fn get_input(prompt: &str) -> usize {
    println!("{prompt}");
    io::stdout().flush().unwrap();

    let mut input = String::new();
    io::stdin().read_line(&mut input)
        .expect("Failed to read line.");

    return input.trim().parse()
        .expect("Please enter a valid number");
}

fn get_matrix(rows: usize, cols: usize) -> Matrix {
    let mut data = Vec::with_capacity(rows * cols);

    for i in 0..rows {
        for j in 0..cols {
            let val = get_input(&format!("Enter value for [{}] [{}]: ", i, j));
            data.push(val as f64);
        }
    }

    return Matrix::new(rows, cols, data);
}

fn main() {
    println!();

    let rows = get_input("Enter the number of rows for the matrices: ");
    let cols = get_input("Enter the number of columns for the matrices: ");

    println!("Enter values for the first matrix:");
    let matrix1 = get_matrix(rows, cols);

    println!("Enter values for the second matrix:");
    let matrix2 = get_matrix(rows, cols);

    println!("Choose an operation: ");
    println!("1. Add");
    println!("2. Subtract");
    println!("3. Multiply");

    let choice = get_input("Enter your choice: ");

    match choice {
        1 => match matrix1.add(&matrix2) {
            Ok(result) => {
                println!("Result of addition");
                result.display();
            }
            Err(e) => println!("Error: {e}")
        },
        2 => match matrix1.subtract(&matrix2) {
            Ok(result) => {
                println!("Result of subtraction");
                result.display();
            }
            Err(e) => println!("Error: {e}")
        },
        3 => match matrix1.multiply(&matrix2) {
            Ok(result) => {
                println!("Result of multiplication");
                result.display();
            }
            Err(e) => println!("Error: {e}")
        },
        _ => println!("Invalid choice")
    }
}
