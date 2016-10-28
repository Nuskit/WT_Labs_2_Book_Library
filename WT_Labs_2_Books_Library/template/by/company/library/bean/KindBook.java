package by.company.library.bean;

public enum KindBook {
	PAPER("paper"), ELECTRONIC("electronic"), UNSUPPORTED("unsupported");
	
    private final String text;
    
    private KindBook(final String text) {
        this.text = text;
    }
    
    @Override
    public String toString() {
        return text;
    }
    
	public static KindBook getKind(String value) {
		KindBook kindBook = null;
		for (KindBook kind : KindBook.values()) {
			if (value.compareTo(kind.toString()) == 0) {
				kindBook = kind;
				break;
			}
		}
		return kindBook == null ? KindBook.UNSUPPORTED : kindBook;
	}
}