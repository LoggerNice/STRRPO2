package Practic_2;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.Enumeration;

public class Client {
    public static void main(String[] args) throws NamingException, RemoteException {
        Context context = new InitialContext();
        Enumeration<NameClassPair> nameServers = context.list("rmi://localhost:1999/");
        while (nameServers.hasMoreElements())
            System.out.println("Название севера: " + nameServers.nextElement().getName());

        String url = "rmi://localhost:1999/employee";
        IEmployee iEmployee = (IEmployee)context.lookup(url);
        System.out.println(iEmployee.getData());
    }
}
