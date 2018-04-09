package gameData;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {
	
	
	public static void main (String[] args) {
		Abs t = new Abs(3, 4.0);
		
		
		String filename = "file.ser";
        
        // Serialization 
        try
        {   
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);
             
            // Method for serialization of object
            out.writeObject(t);
             
            out.close();
            file.close();
             
            System.out.println("Object has been serialized");
 
        }
         
        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }
 
 
        Abs t1 = null;
 
        // Deserialization
        try
        {   
            // Reading the object from a file
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);
             
            // Method for deserialization of object
            t1 = (Abs)in.readObject();
             
            in.close();
            file.close();
             
            System.out.println("Object has been deserialized ");
            System.out.println("c = " + t1.getChestC());
            System.out.println("d = " + t1.getChestD());
            t1.update();
            System.out.println("c = " + t1.getChestC());
            System.out.println("d = " + t1.getChestD());
            System.out.println(t1.get());
        }
         
        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }
         
        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
        }
        
	}
}
