package eg.edu.guc.yugioh.exceptions;

public class EmptyFieldException extends UnexpectedFormatException{
	private int sourceField;
	public EmptyFieldException(String s, int l, int sf){
		super(s,l);
		this.sourceField=sf;
		
		
	}
	public int getSourceField() {
		return sourceField;
	}
	public void setSourceField(int sourceField) {
		this.sourceField = sourceField;
	}

}
