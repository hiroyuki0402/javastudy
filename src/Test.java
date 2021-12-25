import java.util.Scanner;

public class Test {

	private static Scanner scanner;
	private static int computerHp = 0;
	private static int playerHp = 0;
	private static String playerName = "";
	private static String computerName = "";
	private static Properties[] properties = new Properties[2];

	public static void main(String[] args) {
		batleSetUp();
		isBattle();
	}

	// MARK: METHODS

	/// システム入力
	/// - Parameters: enum FormatBrunch
	/// - param: 型の指定
	/// - Returns: オブジェクト
	public static Object scanner(FormatBrunch formart) {
		// FIXME: ジェネリックスの方が良いかも
		scanner = new Scanner(System.in);
		switch (formart) {
		case Int:
			return scanner.nextInt();
		case String:
			return scanner.next();
		default:
			return "err";
		}
	}

	/// システム入力
	/// - Parameters: enum ParameterBranch
	/// - param: 用途の指定
	/// - Returns: ランダムな数字
	static int randomNumber(ParameterBranch parameterBranch) {
		switch (parameterBranch) {
		case DAMAGE:
			return (int) (Math.random() * 3) + 5;
		case COMPUTERHUND:
			return (int) (Math.random() * 3);
		default:
			return (int) Math.random() * 0;
		}
	}

	/// バトルの準備
	/// - Parameters:
	/// - Returns: -
	static void batleSetUp() {
		// FIXME: java構造体できるのか問題
		System.out.println("名前を入力してください");
		properties[Member.player.ordinal()] = new Properties(scanner(FormatBrunch.String).toString(), 15);
		properties[Member.computer.ordinal()] = new Properties("JAVA", 15);
		playerHp = properties[Member.player.ordinal()].getHitpoint();
		computerHp = properties[Member.computer.ordinal()].getHitpoint();
		playerName = properties[Member.player.ordinal()].getName();
		computerName = properties[Member.computer.ordinal()].getName();
	}

	/// バトル
	/// - Parameters:
	/// - Returns: -
	static void isBattle() {
		System.out.println("最初はぐー");

		for (;;) {
			if (playerHp > 0 && computerHp > 0) {
				System.out.println("じゃんけん");
				System.out.println("グー:0\nチョキ:1\nパー:2");
				int computerHand = randomNumber(ParameterBranch.COMPUTERHUND);
				int playerHand = (int) scanner(FormatBrunch.Int);
				if (playerHand >= 3) {
					System.out.println("入力数字を確認してください: 入力可能数字0~2");
					continue;
				}
				System.out.println("ポイ");
				System.out.println(computerHand);
				battleIsChecked(playerHand, computerHand);
			} else {
				WinOrLoseChecked();
				break;
			}
		}
	}

	/// バトルのチェック
	/// - Parameters:
	/// - paramA: prayerの選択したじゃんけんの数字
	/// - paramB: Computerの選択したじゃんけんの数字
	/// - Returns: 戻り値の説明
	static void battleIsChecked(int player, int computer) {
		// FIXME: きたない・冗長すぎ
		var damage = randomNumber(ParameterBranch.DAMAGE);

		var check = (player - computer + 3) % 3;
		switch (check) {
		case 0:
			System.out.println("アイコ");
			break;
		case 2:
			System.out.println(playerName + "さんは:" + player);
			System.out.println(computerName + "さんは:" + computer);
			System.out.println(playerName + "の勝ち");
			System.out.println(computerName + "さんに" + (1 + damage) + "ダメージを与えた");
			computerHp -= 1 + damage;
			System.out.println(playerName + "さんのHP:" + playerHp);
			System.out.println(computerName + "さんのHP:" + computerHp);
			break;
		default:
			System.out.println(playerName + "さんは:" + player);
			System.out.println(computerName + "さんは:" + computer);
			System.out.println(computerName + "の勝ち");
			System.out.println(playerName + "さんに" + (1 + damage) + "ダメージを与えた");
			playerHp -= 1 + damage;
			System.out.println(playerName + "さんのHP:" + playerHp);
			System.out.println(computerName + "さんのHP:" + computerHp);
			break;
		}
	}

	/// 勝敗の判定
	/// - Parameters:
	/// - Returns: -
	static void WinOrLoseChecked() {
		// TODO: 再実施処理
		if (playerHp < 0) {
			System.out.println("勝負あり!!勝者:" + computerName);
		} else if (computerHp < 0) {
			System.out.println("勝負あり!!勝者:" + playerName);
		}
	}
	
	// MARK: ENUMS

	public enum ParameterBranch {
		HITPOINT, DAMAGE, COMPUTERHUND
	};

	public enum Member {
		player, computer
	};

	public enum FormatBrunch {
		Int, String
	}

	public enum Hand {
		Rock, paper, scissors
	}
}

class Properties {
	private String name;
	private int hitpoint;
	public Properties(String name, int hitpoint) {
		this.name = name;
		this.hitpoint = hitpoint;
	}
	public String getName() {
		return name;
	}
	public int getHitpoint() {
		return hitpoint;
	}
};
