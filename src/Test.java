import java.util.Random;
import java.util.Scanner;

public class Test {

	private static Scanner scanner;
	private static int comHp = 0;
	private static int playerHp = 0;
	private static String pName = "";
	private static String cName = "JAVA";

	public static void main(String[] args) {
		nameAndHitPoints(scanner(IsType.String));
		battle();

	}

	public static Object scanner(IsType isType) {
		scanner = new Scanner(System.in);
		switch (isType) {
		case Int:
			return scanner.nextInt();
		default:
			return scanner.next();
		}
	}

	static void nameAndHitPoints(Object name) {
		comHp = randomNumber(ParameterBranch.HITPOINT);
		playerHp = randomNumber(ParameterBranch.HITPOINT);
		pName = (String)name;
		System.out.println("挑戦者は" + name + "さん");
		System.out.println(name + "さんのHPは" + playerHp + "です");
		System.out.println("対戦相手は" + cName +"さん");
		System.out.println(cName + "さんのHPは" + comHp + "です");
	}

	static int randomNumber(ParameterBranch parameterBranch) {
		Random random = new Random();
		switch (parameterBranch) {
		case HITPOINT:
			return random.nextInt(10) + 5;
		case DAMAGE:
			return random.nextInt(1) + 5;
		case COMPUTER:
			return random.nextInt(1) + 2;
		default:
			return random.nextInt(0);
		}
	}

	static void hitPoint() {
	}

	static void battle() {
		for (;;) {
			var choiceNumber = (int) scanner(IsType.Int);
			var computerHand = randomNumber(ParameterBranch.COMPUTER);
			var randomNumber = randomNumber(ParameterBranch.HITPOINT);
			if (playerHp <= 0 || comHp <= 0) {
				isChecked();
			} else {
				if (choiceNumber == computerHand) {
					continue;

				} else if (choiceNumber == 1 && computerHand == 2) {
					System.out.println(pName + "さんは:" + choiceNumber);
					System.out.println(cName + "さんは:" + computerHand);
					comHp -= 1 + randomNumber;
					System.out.println(cName + ":" + comHp);
					System.out.println(pName + ":" + playerHp);
					isChecked();
					continue;
				} else if (choiceNumber == 2 && computerHand == 3) {
					comHp -= 1 + randomNumber;
					System.out.println(pName + "さんは:" + choiceNumber);
					System.out.println(cName + "さんは:" + computerHand);
					System.out.println(cName + ":" + comHp);
					System.out.println(pName + ":" + playerHp);
					isChecked();
					continue;
				} else if (choiceNumber == 3 && computerHand == 1) {
					playerHp -= 1 + randomNumber;
					System.out.println(pName + "さんは:" + choiceNumber);
					System.out.println(cName + "さんは:" + computerHand);
					System.out.println(cName + ":" + comHp);
					System.out.println(pName + ":" + playerHp);
					isChecked();
					continue;
				}
				isChecked();
				break;
			}
		}
	}

	static void isChecked() {
		if (playerHp <= 0) {
			System.out.println(cName + ":" + comHp);
			System.out.println(pName + ":" + playerHp);
			System.out.println("勝者:" + pName);
		} else if (comHp <= 0) {
			System.out.println(cName + ":" + comHp);
			System.out.println(pName + ":" + playerHp);
			System.out.println("勝者:" + cName);
		}

	}

	public enum ParameterBranch {
		HITPOINT, DAMAGE, COMPUTER
	};

	public enum IsType {
		Int, String,
	};

};
