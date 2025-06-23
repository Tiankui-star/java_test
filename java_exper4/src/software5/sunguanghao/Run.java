package software5.sunguanghao;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Run {
    public static void run()
    {
        Random random=new Random();
        Scanner scanner=new Scanner(System.in);
        int a,b;
        int result;
        int count_right=0;
        int scope;
        int flag=1;
        int choice=0;

        AddArithmetic add=new AddArithmetic();
        SubArithmetic sub=new SubArithmetic();
        MulArithmetic mul=new MulArithmetic();
        DivArithmetic div=new DivArithmetic();
        ArrayList<String>wrong_test=new ArrayList<>();
        System.out.println("是否指定运算？");
        flag=scanner.nextInt();
        if(flag==1){
            System.out.println("0:+ ,1:- ,2:*, 3:/");
            choice=scanner.nextInt();
        }
        System.out.println("输入想要的范围");
        scope=scanner.nextInt();
        System.out.println("输入想做的次数");
        int n=scanner.nextInt();
        System.out.println("请输入计算结果的上界和下限");
        int top,low;
        top=scanner.nextInt();
        low=scanner.nextInt();
        for(int i=0;i<n;i++)
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
                    add.show_int();
                    result=scanner.nextInt();
                    if(add.judge_int(result))
                    {
                        System.out.println("Right");
                        count_right++;
                    }
                    else {
                        System.out.println("Wrong");
                        wrong_test.add(String.valueOf(a)+'+'+String.valueOf(b));
                    }

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
                    sub.show_int();
                    result=scanner.nextInt();
                    if(sub.judge_int(result))
                    {
                        System.out.println("Right");
                        count_right++;
                    }
                    else {
                        System.out.println("Wrong");
                        wrong_test.add(String.valueOf(a)+'-'+String.valueOf(b));
                    }
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
                    mul.show_int();
                    result=scanner.nextInt();
                    if(mul.judge_int(result))
                    {
                        System.out.println("Right");
                        count_right++;
                    }
                    else {
                        System.out.println("Wrong");
                        wrong_test.add(String.valueOf(a)+'*'+String.valueOf(b));
                    }
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
                    div.show_int();
                    result=scanner.nextInt();
                    if(div.judge_int(result))
                    {
                        System.out.println("Right");
                        count_right++;
                    }
                    else {
                        System.out.println("Wrong");
                        wrong_test.add(String.valueOf(a)+'/'+String.valueOf(b));
                    }
                    break;

                
            }
            }
        }
        int wrong=n-count_right;
        System.out.println("正确率是 "+(double)count_right/n*100+"%");
        System.out.println("错误的题目数是 "+wrong);
        if(wrong!=0) {
            System.out.println("继续做错误的题目吗?");
            int cotiue;
            cotiue = scanner.nextInt();
            if (cotiue == 1) {
                for (String tmp : wrong_test) {
                    char ch;
                    int i = 0;
                    a = b = 0;
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
                            add.show_int();
                            int rsu = scanner.nextInt();
                            if (add.judge_int(rsu)) {
                                System.out.println("Right");

                            } else System.out.println("Wrong");
                            break;
                        }
                        case '-': {
                            sub.set_int(a, b);
                            sub.show_int();
                            int rsu = scanner.nextInt();
                            if (sub.judge_int(rsu)) {
                                System.out.println("Right");

                            } else System.out.println("Wrong");
                            break;
                        }
                        case '*': {
                            mul.set_int(a, b);
                            mul.show_int();
                            int rsu = scanner.nextInt();
                            if (mul.judge_int(rsu)) {
                                System.out.println("Right");

                            } else System.out.println("Wrong");
                            break;
                        }
                        case '/': {
                            div.set_int(a, b);
                            div.show_int();
                            int rsu = scanner.nextInt();
                            if (div.judge_int(rsu)) {
                                System.out.println("Right");

                            } else System.out.println("Wrong");
                            break;
                        }
                    }
                }
            }
        }
    }
    
    
    
    

}