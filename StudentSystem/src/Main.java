import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    private static void clearConsole()
    {
        try
        {
            String os = System.getProperty("os.name");

            if (os.contains("Windows"))
            {
                Runtime.getRuntime().exec("cls");
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (Exception exception)
        {
            //  Handle exception.
        }
    }

    public static Student[] St = new Student[105];
    public static Scanner sc = new Scanner(System.in) ;
    public static int cnt = 0;

    public static void LoadData() throws IOException {

        FileInputStream inputStream = new FileInputStream("./data.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String s = bufferedReader.readLine();
        while(!s.equals("end")) {
            String[] _s = s.split(" ");
            St[cnt].ID = _s[0];
            St[cnt].Name = _s[1];
            St[cnt].Chinese_s = Integer.parseInt(_s[2]);
            St[cnt].Math_s = Integer.parseInt(_s[3]);
            St[cnt].English_s = Integer.parseInt(_s[4]);
            cnt = cnt + 1;
            s = bufferedReader.readLine();
        }
        bufferedReader.close();
    }
    public static void SaveData() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(new File("./data.txt"));
        for(int i = 0;i < cnt;i = i + 1){
            String s = St[i].ID + " ";
            s += St[i].Name + " ";
            s += Integer.toString(St[i].Chinese_s) + " ";
            s += Integer.toString(St[i].Math_s) + " ";
            s += Integer.toString(St[i].English_s) + "\n";
            fileOutputStream.write(s.getBytes());

        }
        fileOutputStream.close();

    }
    public static void InfoPut() {
        System.out.format("1.新建学生资料\n");
        System.out.format("2.修改某学生的某课成绩\n");
        System.out.format("3.打印所有学生信息\n");
        System.out.format("4.保存目前所有学生信息\n");
        System.out.format("5.退出\n");
        // 暂时不想写删除功能 没有numpy那种功能
    }
    public static void main(String[] args) throws IOException {
        for(int i = 0;i < 100;i = i + 1){
            St[i] = new Student();
        }
        int flag;
        LoadData();
        while(true){
            clearConsole();
            InfoPut();
            flag = sc.nextInt();
            clearConsole();
            if(flag == 1){
                St[cnt].AddStudent();
            }
            else if(flag == 2){
                System.out.format("请输入要修改同学的学号");
                String s = sc.nextLine();
                int index = -1;
                for(int i = 0;i < cnt ;i = i + 1){
                    if(s.equals(St[cnt].ID)){
                        index = i;
                    }
                }
                if(index == -1){
                    System.out.format("未找到对应的学生信息\n");
                }
                St[index].Change_Score();
            }
            else if(flag == 3){
                for(int i = 0;i < cnt;i = i + 1){
                    St[i].PrintInfo();
                }
            }
            else if(flag == 4){
                SaveData();
            }
            else if(flag == 5){
                System.exit(0);
            }
            else{
                System.out.format("错误的输入！！！\n");
            }


        }


    }

}
