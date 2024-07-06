enum Coin {
    Penny,
    Nickel,
    Dime,
    Quarter,
}

fn value_in_cents(coin: Coin) -> u8 {
    match coin {
        Coin::Penny => { 1 }
        Coin::Nickel => { 5 }
        Coin::Dime => { 10 }
        Coin::Quarter => { 25 }
    }
}

fn main() {
    println!();

    let enum_value = Coin::Dime;
    let val = value_in_cents(enum_value);

    match val {
        1 => println!("Dime it is"),
        5 => println!("Nickel it is"),
        10 => println!("Dime it is"),
        25 => println!("Quarter it is"),
        _ => println!("kuch match nahi kar raha bkl")
    }

    println!("{}", make_separator("hello word"))
}

fn make_separator(user_str: &str) -> String {
    if user_str == "" {
        let default = "=".repeat(10);
        default
    } else {
        return user_str.to_string();
    }
}
