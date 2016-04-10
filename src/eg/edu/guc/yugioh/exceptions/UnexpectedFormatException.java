package eg.edu.guc.yugioh.exceptions;

public class UnexpectedFormatException extends Exception{
	private String sourceFile;
	private int sourceLine;

	public UnexpectedFormatException(String sf, int sl){
		super("Unexpected Format");
		this.sourceFile=sf;
		this.sourceLine=sl;
	}

	public String getSourceFile() {
		return sourceFile;
	}

	public void setSourceFile(String sourceFile) {
		this.sourceFile = sourceFile;
	}

	public int getSourceLine() {
		return sourceLine;
	}

	public void setSourceLine(int sourceLine) {
		this.sourceLine = sourceLine;
	}
	
	
}
