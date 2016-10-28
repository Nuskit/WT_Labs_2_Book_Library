package by.company.library;

import java.util.Scanner;

import by.company.library.controller.Controller;

public class ConsoleExample {
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Hello! You are in electronic library.");
		Byte numberOfOperation;
		String response;
		Controller controller = new Controller();
		do {
			System.out.println("Choose action:");
			System.out.println("1) Register");
			System.out.println("2) Join as user");
			System.out.println("3) Exit");
			System.out.print("Enter: ");
			numberOfOperation = enterNumberOfOperation();

			switch (numberOfOperation) {
			case 1: {
				response = controller.doAction("REGISTRATION?" + enterLoginAndPassword());
				if (response.split("\\?")[0].compareTo("error") != 0) {
					showMenuForUser();
				} else {
					System.out.println(response.split("\\?")[1]);
					numberOfOperation = 0;
				}
			}
				break;
			case 2: {
				response = controller.doAction("LOGINATION?" + enterLoginAndPassword());
				if (response.split("\\?")[0].compareTo("error") != 0) {
					if (response.compareTo("admin") == 0) {
						showMenuForAdmin();
					} else {
						showMenuForUser();
					}
				} else {
					System.out.println(response.split("\\?")[1]);
					numberOfOperation = 0;
				}
			}
				break;
			case 3: {
				System.out.println("Good bye!");
				System.exit(0);
			}
				break;
			}
		} while ((numberOfOperation < 1) || (numberOfOperation > 3));
	}

	private static Byte enterNumberOfOperation() {
		try {
			Scanner sc = new Scanner(System.in);
			return Byte.parseByte(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Wrong number!");
			return 0;
		}
	}

	private static String enterLoginAndPassword() {
		String login = "";
		String password = "";
		Scanner sc = new Scanner(System.in);
		do {
			if ((login.length() == 0) || (password.length() == 0))
				System.out.println("Login and password must not be empty");
			System.out.print("Enter login: ");
			login = sc.nextLine();
			System.out.print("Enter password:");
			password = sc.nextLine();
		} while ((login.length() == 0) || (password.length() == 0));
		return login + "&" + password;
	}

	private static String enterTitleAndAuthorOfBook() {
		String title;
		String author;
		String kindStr;
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter title of book: ");
		title = sc.nextLine();
		System.out.print("Enter author of book: ");
		Byte kind = 0;
		author = sc.nextLine();
		do {
			try {
				System.out.println("Choose kind of book:");
				System.out.println("1)Paper variant");
				System.out.println("2)Electronic variant");
				kindStr = sc.nextLine();
				kind = Byte.parseByte(kindStr);

			} catch (NumberFormatException e) {
				System.out.println("Wrong enter!");
			}
		} while (kind > 2 || kind < 1);
		if (kind == 1) {
			kindStr = "paper";
		} else {
			kindStr = "electronic";
		}
		return title + "&" + author + "&" + kindStr;
	}

	private static boolean isHaveError(String message) {
		boolean isHaveError = false;
		String[] splitMessage = message.split("\\?");
		if (splitMessage[0].compareTo("error") == 0) {
			System.out.println(splitMessage[1]);
			isHaveError = true;
		}
		return isHaveError;
	}

	private static void showMenuForUser() {
		Byte numberOfOperation;
		Controller controller = new Controller();
		do {
			System.out.println("Choose action:");
			System.out.println("1) See all book");
			System.out.println("2) Find book");
			System.out.println("3) Exit");
			System.out.print("Enter: ");
			numberOfOperation = enterNumberOfOperation();
			String message;
			switch (numberOfOperation) {
			case 1: {
				message = controller.doAction("GET_ALL_BOOKS?");
				if (!isHaveError(message)) {
					System.out.println(message);
				}
				numberOfOperation = 0;
			}
				break;
			case 2: {
				message = controller.doAction("FIND_BOOK?" + enterDataOfFindBook());
				if (!isHaveError(message)) {
					System.out.println(message);
					numberOfOperation = 0;
				}
			}
				break;
			case 3: {
				System.out.println("Good bye!");
				System.exit(0);
			}
				break;
			}
		} while ((numberOfOperation < 1) || (numberOfOperation > 3));
	}

	private static void showMenuForAdmin() {
		Byte numberOfOperation;
		String message;
		Controller controller = new Controller();
		do {
			System.out.println("Choose action:");
			System.out.println("1) See all book");
			System.out.println("2) Find book");
			System.out.println("3) Add book");
			System.out.println("4) Delete book");
			System.out.println("5) Exit");
			System.out.print("Enter: ");
			numberOfOperation = enterNumberOfOperation();

			switch (numberOfOperation) {
			case 1: {
				message = controller.doAction("GET_ALL_BOOKS?");
				if (!isHaveError(message)) {
					System.out.println(message);
				}
				numberOfOperation = 0;
			}
				break;
			case 2: {
				message = controller.doAction("FIND_BOOK?" + enterDataOfFindBook());
				if (!isHaveError(message)) {
					System.out.println(message);
				}
				numberOfOperation = 0;
			}
				break;
			case 3: {
				message = controller.doAction("ADD_NEW_BOOK?" + enterTitleAndAuthorOfBook());
				if (!isHaveError(message)) {
					System.out.println(message);
				}
				numberOfOperation = 0;
			}
				break;
			case 4: {
				message = controller.doAction("DELETE_BOOK?" + enterTitleAndAuthorOfBook());
				if (!isHaveError(message)) {
					System.out.println(message);
				}
				numberOfOperation = 0;
			}
				break;
			case 5: {
				System.out.println("Good bye!");
				System.exit(0);
			}
				break;
			}
		} while ((numberOfOperation < 1) || (numberOfOperation > 3));
	}

	private static String enterDataOfFindBook() {
		String title;
		String author;
		Byte kind;
		String kindStr;
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter title of book:");
		title = sc.nextLine();
		if ("".compareTo(title) == 0) {
			title = " ";
		}
		System.out.print("Enter author of book:");
		author = sc.nextLine();
		if ("".compareTo(author) == 0) {
			author = " ";
		}
		System.out.println("Choose book type:");
		System.out.println("1)Paper variant");
		System.out.println("2)Electronic variant");
		kindStr = sc.nextLine();
		try {
			kind = Byte.parseByte(kindStr);
			if (kind == 1) {
				kindStr = "paper";
			} else if (kind == 2) {
				kindStr = "electronic";
			} else {
				kindStr = " ";
			}
		} catch (NumberFormatException e) {
			kindStr = " ";
		}
		return title + "&" + author + "&" + kindStr;
	}
}
