package software5.sunguanghao;

public abstract class Arithmetic
{
    protected int a_int;
    protected int b_int;

    public abstract void show_int();
    public abstract boolean judge_int(int result);
    public abstract boolean judge(int a,int b,int top,int low);
    public abstract boolean judge_repeat(int a,int b);
    
    //public abstract void show_double();
    //public abstract boolean judge_double(double result);

}