import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class SecretSantaService {
    public static void main(String[]  args) {
        //args[0] stores the email username and args[1] is the password for the email service
        EmailService javaEmail =new EmailService("smtp.gmail.com", 587, args[0], args[1]);

        ArrayList<SecretSantaObject> objects = readPeople();

        Collections.shuffle(objects);
        for(int i=0;i<objects.size();i++){
            if(i==objects.size()-1){
                objects.get(i).setIndex(0);
            }else{
                objects.get(i).setIndex(i+1);
            }
        }
        for(SecretSantaObject ob:objects){
            SecretSantaObject newOb = objects.get(ob.getIndex());
            writeRecord(ob.name,objects.get(ob.getIndex()).name);
            javaEmail.sendMail(ob.name,ob.email,newOb.name,newOb.bio);
        }
    }

    /**
     * A method that will write out the results of the secret santa
     * @param name1
     * @param name2
     */
    private static void writeRecord(String name1, String name2){
        String data = name1+" has to buy a gift for "+name2;
        File file = new File("output.txt");
        FileWriter fr = null;
        try {
            fr = new FileWriter(file,true);
            fr.append(data+"\n");
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * A method to read all the people from the people.txt file, written in csv format
     * @return ArrayList<> of all the people
     */
    private static ArrayList<SecretSantaObject> readPeople(){
        ArrayList<SecretSantaObject> people = new ArrayList<SecretSantaObject>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("people.txt"));
            String line = reader.readLine();
            while(line!=null){
                String[] person=line.split((","));
                people.add(new SecretSantaObject(person[0],person[1],person[2]));
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return people;
    }
}
