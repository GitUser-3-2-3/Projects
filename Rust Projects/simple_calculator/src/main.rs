use std::io;
use std::io::Write;

enum Operation {
    Add,
    Subtract,
    Multiply,
    Divide,
}

struct Calculator {
    num1: f64,
    num2: f64,
    operation: Operation,
    result: Option<f64>,
}

impl Calculator {
    fn new(num1: f64, num2: f64, operation: Operation) -> Self {
        Self {
            num1,
            num2,
            operation,
            result: None,
        }
    }

    fn calculate(&mut self) {
        self.result = Some(match self.operation {
            Operation::Add => { self.num1 + self.num2 }
            Operation::Subtract => { self.num1 - self.num2 }
            Operation::Multiply => { self.num1 * self.num2 }
            Operation::Divide => {
                if self.num2 != 0.0 {
                    self.num1 / self.num2
                } else {
                    println!("Error: Division by zero");
                    return;
                }
            }
        });
    }
}

fn get_input(prompt: &str) -> f64 {
    println!("{}", prompt);
    io::stdout().flush().unwrap();

    let mut input = String::new();
    io::stdin().read_line(&mut input)
        .expect("Failed to read line");

    input.trim().parse()
        .expect("Please enter a valid number")
}

fn main() {
    let first_number = get_input("Enter the first number: ");
    let second_number = get_input("Enter the second number: ");

    println!("Choose and operation: ");
    println!("1. Add");
    println!("2. Subtract");
    println!("3. Multiply");
    println!("4. Divide");

    let mut operation_choice = String::new();
    io::stdin().read_line(&mut operation_choice)
        .expect("Failed to read line");

    let operation = match operation_choice.trim() {
        "1" => Operation::Add,
        "2" => Operation::Subtract,
        "3" => Operation::Multiply,
        "4" => Operation::Divide,
        _ => {
            println!("Invalid choice");
            return;
        }
    };

    let mut calculator = Calculator::new(first_number, second_number, operation);
    calculator.calculate();

    match calculator.result {
        None => println!("No result"),
        Some(result) => println!("The result is: {result}")
    }
}