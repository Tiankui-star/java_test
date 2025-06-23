package software5.exercise6;

public class UserValidation implements IValidation {

	@Override
	public boolean validate2(String s) {
		// TODO Auto-generated method stub
		return validate(s, "[1][345678][0-9]{9}");
	}

}
