package by.grsu.utils.api.grsu;

public class AuthException extends Exception{
    public AuthException(){
        super("Invalid credentials");
    }
}
