package software5.sunguanghao;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.util.*;


public class Run {
    public static int count=0;
    public static int currentIndex=0,remain;
    public static int flag=1;
    public static int choice;
    public static int scope;
    public static int top=0,low=0;
    public static int wrong_right=0;
    public static AddArithmetic add=new AddArithmetic();
    public static SubArithmetic sub=new SubArithmetic();
    public static MulArithmetic mul=new MulArithmetic();
    public static DivArithmetic div=new DivArithmetic();
    public static ArrayList<String>wrong_test=new ArrayList<>();
    public static int count_right=0;
    public static void run()
    {

        Scanner scanner=new Scanner(System.in);




//        System.out.println("是否指定运算？");
//        flag=scanner.nextInt();
//        if(flag==1){
//            System.out.println("0:+ ,1:- ,2:*, 3:/");
//            choice=scanner.nextInt();
//        }
//        System.out.println("输入想要的范围");
//        scope=scanner.nextInt();
//        System.out.println("输入想做的次数");
//        remain=scanner.nextInt();
//        System.out.println("请输入计算结果的上界和下限");
//        top=scanner.nextInt();
//        low=scanner.nextInt();
        show_in();
        int a,b;

    }
    public static void show_in(){
        init_show();

    }
    public static void wrong_op(){
        JFrame frame=new JFrame("错误题目");
        java.util.List<JTextField>answerGields=new ArrayList<>();
        ArrayList<Integer> rsu=new ArrayList<>();

        int wrong=remain-count_right;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,600);
        frame.setLayout(new BorderLayout());
        JPanel panel=new JPanel(new GridLayout(wrong,2,10, 10));
        for(String title:wrong_test){
            JLabel label = new JLabel(title);
            JTextField field=new JTextField(5);
            JPanel qPannel=new JPanel();
            qPannel.add(label);
            qPannel.add(field);
            panel.add(qPannel);
            answerGields.add(field);
        }
        JButton nextBtn=new JButton("完成");
        nextBtn.addActionListener(e-> {
            for(int i=0;i<wrong;i++){
                String text;
                text=answerGields.get(i).getText().trim();
                int ans=Integer.parseInt(text);
                if(ans==wrong_calculate(wrong_test.get(i))){
                    wrong_right++;
                }
            }
            frame.dispose();
            JOptionPane.showMessageDialog(null, "错误题目完成！正确率是："+
                    String.valueOf((double)wrong_right/wrong*100)+"%"+'\n'+"错误的题目数是 "+
                    String.valueOf(wrong - wrong_right));
            System.exit(0);
        });
        frame.add(panel, BorderLayout.CENTER);
        frame.add(nextBtn, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
    public static int wrong_calculate(String tmp){

            char ch;
            int i = 0;
            int a = 0;
            int b=0;
            int rsu=0;
            while (tmp.charAt(i) >= '0' && tmp.charAt(i) <= '9') {
                a = a * 10 + tmp.charAt(i) - '0';
                i++;
            }
            ch = tmp.charAt(i++);
            while (i < tmp.length()) {
                b = b * 10 + tmp.charAt(i) - '0';
                i++;
            }
            switch (ch) {

                case '+': {
                    add.set_int(a, b);
                    //add.show_int();
                    rsu= a+b;
                }
                case '-': {
                    sub.set_int(a, b);
                    //sub.show_int();
                    rsu= a-b;
                }
                case '*': {
                    mul.set_int(a, b);
                    //mul.show_int();
                    rsu= a*b;
                }
                case '/': {
                    div.set_int(a, b);
                    //div.show_int();
                    rsu= a/b;
                }
            }
        return rsu;
    }
    public static ArrayList<String> op(int need,ArrayList<Integer> rsu){
        int a,b;

        ArrayList<String> Title = new ArrayList<>();
        Scanner scanner=new Scanner(System.in);
        Random random=new Random();

        for(int i=0;i<need;i++)
        {
            if(flag==0)
                choice=random.nextInt(4);
            switch(choice)
            {
                case 0:{
                    a=random.nextInt(scope);
                    b=random.nextInt(scope);
                    while(!add.judge(a,b,top,low)||add.judge_repeat(a, b))
                    {
                        a=random.nextInt(scope);
                        b=random.nextInt(scope);
                    }
                    add.add_Vector.get(0).add(a);
                    add.add_Vector.get(1).add(b);
                    add.set_int(a, b);
                    //add.show_int();
                    Title.add(String.valueOf(a)+'+'+String.valueOf(b));
//                    result=scanner.nextInt();
//                    if(add.judge_int(result))
//                    {
//                        System.out.println("Right");
//                        count_right++;
//                    }
//                    else {
//                        System.out.println("Wrong");
//                        wrong_test.add(String.valueOf(a)+'+'+String.valueOf(b));
//                    }
                    rsu.add(a+b);
                    break;

                }
                case 1:{
                    a=random.nextInt(scope);
                    b=random.nextInt(scope);
                    while(!sub.judge(a,b,top,low)||sub.judge_repeat(a, b))
                    {
                        a=random.nextInt(scope);
                        b=random.nextInt(scope);
                    }
                    sub.sub_Vector.get(0).add(a);
                    sub.sub_Vector.get(1).add(b);
                    sub.set_int(a, b);
                    //sub.show_int();
                    Title.add(String.valueOf(a)+'-'+String.valueOf(b));
//                    result=scanner.nextInt();
//                    if(sub.judge_int(result))
//                    {
//                        System.out.println("Right");
//                        count_right++;
//                    }
//                    else {
//                        System.out.println("Wrong");
//                        wrong_test.add(String.valueOf(a)+'-'+String.valueOf(b));
//                    }
                    rsu.add(a-b);
                    break;

                }
                case 2:{
                    a=random.nextInt(scope);
                    b=random.nextInt(scope);
                    while((!mul.judge(a,b,top,low))||mul.judge_repeat(a, b)||a==1||a==0||b==1||b==0)
                    {
                        a=random.nextInt(scope);
                        b=random.nextInt(scope);
                    }
                    mul.mul_Vector.get(0).add(a);
                    mul.mul_Vector.get(1).add(b);
                    mul.set_int(a, b);
                    //mul.show_int();
                    Title.add(String.valueOf(a)+'*'+String.valueOf(b));
//                    result=scanner.nextInt();
//                    if(mul.judge_int(result))
//                    {
//                        System.out.println("Right");
//                        count_right++;
//                    }
//                    else {
//                        System.out.println("Wrong");
//                        wrong_test.add(String.valueOf(a)+'*'+String.valueOf(b));
//                    }
                    rsu.add(a*b);
                    break;

                }
                case 3:{
                    a=random.nextInt(scope);
                    b=random.nextInt(scope);
                    while(b==0||a%b!=0||b==1||a==0||!div.judge(a, b, top, low)||div.judge_repeat(a, b)||a-b==0)
                    {
                        a=random.nextInt(scope);
                        b=random.nextInt(scope);
                    }
                    div.div_Vector.get(0).add(0);
                    div.div_Vector.get(1).add(b);
                    div.set_int(a, b);
                    //div.show_int();
                    Title.add(String.valueOf(a)+'/'+String.valueOf(b));
//                    result=scanner.nextInt();
//                    if(div.judge_int(result))
//                    {
//                        System.out.println("Right");
//                        count_right++;
//                    }
//                    else {
//                        System.out.println("Wrong");
//                        wrong_test.add(String.valueOf(a)+'/'+String.valueOf(b));
//                    }
                    rsu.add(a/b);
                    break;


                }
            }
        }
        return Title;
    }
    public static void init_show(){
        java.util.List<JTextField> answerFields = new ArrayList<>();

        JFrame frame = new JFrame("初始化");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(7, 1, 10, 10));

        String[] prompts = {
                "是否指定运算（0 否，1 是）",
                "若指定输入选择（0:+ ,1:- ,2:*, 3:/）",
                "输入操作数的范围上界",
                "输入想做的次数",
                "输入结果的上界",
                "输入结果的下界"
        };

        for (String prompt : prompts) {
            JLabel label = new JLabel(prompt);
            JTextField field = new JTextField(5);
            JPanel qPanel = new JPanel();
            qPanel.add(label);
            qPanel.add(field);
            panel.add(qPanel);
            answerFields.add(field);
        }

        JButton startBtn = new JButton("开始");
        startBtn.addActionListener(e -> {
            try {
                int t = 0;
                String text;
                text = answerFields.get(t++).getText().trim();
                if (text.isEmpty()) throw new NumberFormatException("不能为空");
                flag = Integer.parseInt(text);

                text = answerFields.get(t++).getText().trim();
                choice = Integer.parseInt(text);

                text = answerFields.get(t++).getText().trim();
                scope = Integer.parseInt(text);

                text = answerFields.get(t++).getText().trim();
                remain = Integer.parseInt(text);

                text = answerFields.get(t++).getText().trim();
                top = Integer.parseInt(text);

                text = answerFields.get(t++).getText().trim();
                low = Integer.parseInt(text);




                frame.dispose(); // 关闭配置界面
                show();
                // run(flag, choice, scope, remain, top, low); // 进入下一阶段

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "请输入有效整数，且不能为空！");
            } catch (IndexOutOfBoundsException ex) {
                JOptionPane.showMessageDialog(frame, "字段读取出错！");
            }
        });

        frame.add(panel, BorderLayout.CENTER);
        frame.add(startBtn, BorderLayout.SOUTH);
        frame.setVisible(true);

    }
    public static void show(){
        JFrame frame = new JFrame("题目页 " + (currentIndex / 10 + 1));
        java.util.List<JTextField> answerFields = new ArrayList<>();
        ArrayList<Integer>rsu=new ArrayList<>();
        int need_show=Math.min(remain-currentIndex, 10);
        ArrayList<String> Titles = op(need_show,rsu);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setLayout(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(need_show, 2, 10, 10));
        for(String title:Titles){
            JLabel label=new JLabel(title);
            JTextField field = new JTextField(5);
            JPanel qPanel = new JPanel();
            qPanel.add(label);
            qPanel.add(field);
            panel.add(qPanel);
            answerFields.add(field);
            currentIndex++;
        }


        JButton nextBtn = new JButton(currentIndex < remain ? "下一页" : "提交");
        nextBtn.addActionListener(e -> {

            for(int i=0;i<need_show;i++){
                String text;
                text = answerFields.get(i).getText().trim();
                int ans=Integer.parseInt(text);
                if(ans!= rsu.get(i)){
                    wrong_test.add(Titles.get(i));
                }
                else{
                    count_right++;
                }
            }

            frame.dispose(); // 关闭当前窗口
            if (currentIndex < remain) {
                // 显示下一页
                show();
            } else {
                int result = JOptionPane.showConfirmDialog(
                        null,
                        "题目完成！正确率是：" + String.valueOf((double)count_right / remain * 100) + "%" + '\n' +
                                "错误的题目数是 " + String.valueOf(remain - count_right),
                        "结果",
                        JOptionPane.OK_CANCEL_OPTION
                );

                if (remain-count_right!=0&&result == JOptionPane.OK_OPTION) {
                    wrong_op();
                } else {
                    System.exit(0);
                }
            }
        });

        frame.add(panel, BorderLayout.CENTER);
        frame.add(nextBtn, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
    
    
    

}