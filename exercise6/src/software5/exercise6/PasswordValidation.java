package software5.exercise6;

public class PasswordValidation implements IValidation {

	@Override
	public boolean validate2(String s) {
		// TODO Auto-generated method stub
		boolean b = true;
		int l = s.length();
		if(l<8||l>20) {
			b = false;
		}
		else {
			String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).+$";
			if(!s.matches(regex))
			{
				System.out.println("password is illegal");
				b=false;
			}
		}
		return b;
	}

}
