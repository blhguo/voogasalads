package gameData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Main {
	
	
	public static void main (String[] args) {
		ArrayList<Abs> sixPack = new ArrayList<Abs>();
		
		for(int i=0;i<5;i++) {
			sixPack.add(new Abs(i, i+1));
		}
		ManipData dick = new ManipData();
		dick.saveData(sixPack);
		File a = new File("C:\\Users\\blhgu\\eclipse-workspace\\voogasalad_callussalad\\gameDataSavesomeuniquefactor.xml");
		ArrayList<Abs> t = (dick.loadData(a));
		for(int i=0;i<t.size();i++) {
			System.out.println(t.get(i).getA()==sixPack.get(i).getA());
			System.out.println(t.get(i).getB()==sixPack.get(i).getB());
			System.out.println(t.get(i).getChestC()==sixPack.get(i).getChestC());
			System.out.println(t.get(i).getChestD()==sixPack.get(i).getChestD());



		}
//		Abs t = new Abs(3, 4.0);
//		
//		
//		String filename = "file.ser";
//        
//        // Serialization 
//        try
//        {   
//            //Saving of object in a file
//            FileOutputStream file = new FileOutputStream(filename);
//            ObjectOutputStream out = new ObjectOutputStream(file);
//             
//            // Method for serialization of object
//            out.writeObject(t);
//             
//            out.close();
//            file.close();
//             
//            System.out.println("Object has been serialized");
// 
//        }
//         
//        catch(IOException ex)
//        {
//            System.out.println("IOException is caught");
//        }
// 
// 
//        Abs t1 = null;
// 
//        // Deserialization
//        try
//        {   
//            // Reading the object from a file
//            FileInputStream file = new FileInputStream(filename);
//            ObjectInputStream in = new ObjectInputStream(file);
//             
//            // Method for deserialization of object
//            t1 = (Abs)in.readObject();
//             
//            in.close();
//            file.close();
//             
//            System.out.println("Object has been deserialized ");
//            System.out.println("c = " + t1.getChestC());
//            System.out.println("d = " + t1.getChestD());
//            t1.update();
//            System.out.println("c = " + t1.getChestC());
//            System.out.println("d = " + t1.getChestD());
//            System.out.println(t1.get());
//        }
//         
//        catch(IOException ex)
//        {
//            System.out.println("IOException is caught");
//        }
//         
//        catch(ClassNotFoundException ex)
//        {
//            System.out.println("ClassNotFoundException is caught");
//        }
//        
	}
}
