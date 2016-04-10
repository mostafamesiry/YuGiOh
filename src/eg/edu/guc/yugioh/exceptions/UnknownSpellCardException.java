package eg.edu.guc.yugioh.exceptions;

public class UnknownSpellCardException extends UnexpectedFormatException{
	
	private String unknownSpell;
	
	public UnknownSpellCardException(String s, int l, String u){
		super(s,l);
		this.unknownSpell=u;
	}

	public String getUnknownSpell() {
		return unknownSpell;
	}

	public void setUnknownSpell(String unknownSpell) {
		this.unknownSpell = unknownSpell;
	}

}
