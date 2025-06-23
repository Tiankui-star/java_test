package software5.exercise6;

public interface IValidation {
	//Pattern & Matcher
	public default boolean validate(String s, String reg) {
		return s.matches(reg);
	}
	
	public boolean validate2(String s);
}
