package software5.sunguanghao;
import java.util.Vector;
public class AddArithmetic extends Arithmetic{
    public Vector<Vector<Integer>>add_Vector=new Vector<>();
    AddArithmetic(){
        add_Vector.add(new Vector<>());
        add_Vector.add(new Vector<>());
    }
    public void set_int(int a,int b) {
        this.a_int=a;
        this.b_int=b;
    }
    @Override
    public void show_int()
    {
        System.out.print(a_int+"+"+b_int+"=");
    }
    @Override
    public boolean judge_int(int result)
    {
        if(result==this.a_int+this.b_int) return true;
        else return false;
    }
    @Override
    public boolean judge(int a,int b,int top,int low)
    {
        if(a+b>top||a+b<low)
            return false;
        return true;
    }
    @Override
    public boolean judge_repeat(int a,int b)
    {
        for(int i=0;i<add_Vector.get(0).size();i++)
        {
            if(a==add_Vector.get(0).get(i)||a==add_Vector.get(1).get(i))
            {
                if(b==add_Vector.get(0).get(i)||b==add_Vector.get(1).get(i))
                    return true;
            }
        }
        return false;
    }
    
}