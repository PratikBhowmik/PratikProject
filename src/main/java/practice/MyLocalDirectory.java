package practice;

public class MyLocalDirectory {

    public static void main(String[] args) {


        String filepath = System.getProperty("user.dir");
        System.out.println("Current working directory of this system is "+filepath);
    }
}
