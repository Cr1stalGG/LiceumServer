package by.grsu.edu.banking.utils.api.grsu;

public class AuthException extends Exception{
    public AuthException(){
        super("Invalid credentials");
    }
}
