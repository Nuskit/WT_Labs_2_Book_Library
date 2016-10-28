package by.company.library.bean;

public class Book {
	private KindBook kind;
	private String title;
	private String author;

	public Book(String title, String author, KindBook kind) {
		setTitle(title);
		setAuthor(author);
		setKind(kind);
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String GetTitle() {
		return title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String GetAuthor() {
		return author;
	}

	public void setKind(KindBook kind) {
		this.kind = kind;
	}

	public KindBook GetKind() {
		return kind;
	}

	@Override
	public String toString() {
		return String.format("%s %s %s", title, author, kind.toString());
	}
}
