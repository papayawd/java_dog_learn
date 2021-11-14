import java.util.Scanner;

public class Student { // 最多容纳100个学生
    public int Score, Point;
    public String ID, Name;
    // 学分 绩点  语数外基础学分为4 修了没挂就有学分 假装都没挂
    // 满绩为5 语数外分别占比 0.3  0.4  0.3
    public int Chinese_s, Math_s, English_s;
    // 支持三个学科的成绩统计  满分都为100分
    public Scanner sc = new Scanner(System.in);
    public int flag;

    public boolean CheckScore(int number) {
        return !(0 <= number && number <= 100);
    }

    public void Change_Score() {
        Student St = new Student();
        System.out.format("1.修改语文成绩\n");
        System.out.format("2.修改数学成绩\n");
        System.out.format("3.修改英语成绩\n");
        flag = sc.nextInt();
        if (flag == 1) {
            System.out.format("请输入语文成绩: ");
            do {
                Chinese_s = sc.nextInt();
            } while (St.CheckScore(Chinese_s));

        } else if (flag == 2) {
            System.out.format("请输入数学成绩: ");
            do {
                Math_s = sc.nextInt();
            } while (!St.CheckScore(Math_s));
        } else {
            System.out.format("请输入英语成绩: ");
            do {
                English_s = sc.nextInt();
            } while (!St.CheckScore(English_s));
        }
    }

    public void AddStudent() {
        System.out.format("请输入学号: ");
        ID = sc.nextLine();
        System.out.format("请输入姓名: ");
        Name = sc.nextLine();
        System.out.format("请输入语文成绩: ");
        Chinese_s = sc.nextInt();
        System.out.format("请输入数学成绩: ");
        Math_s = sc.nextInt();
        System.out.format("请输入英语成绩: ");
        English_s = sc.nextInt();
        System.out.format("新建学生资料成功！！！\n");

    }

    public void PrintInfo() {
        System.out.format("姓名: %s\n", Name);
        System.out.format("学号: %s\n", ID);
        System.out.format("语文 ==> 学分: 4    成绩: %d\n", Chinese_s);
        System.out.format("数学 ==> 学分: 4    成绩: %d\n", Math_s);
        System.out.format("英语 ==> 学分: 4    成绩: %d\n", English_s);
        System.out.format("总学分: 12\n");
        System.out.format("绩点: %.2f\n", (Chinese_s / 10.0 - 5) * 0.3 + (Math_s / 10.0 - 5) * 0.4 + (English_s / 10.0 - 5) * 0.3);
    }
}
