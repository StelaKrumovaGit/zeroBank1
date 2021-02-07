package ZeroBankUtils;

import java.lang.reflect.Method;
import java.util.Random;

import org.testng.annotations.DataProvider;

public class DP {

	@DataProvider(name = "myData")
	public Object[][] createData1(Method m) {

		if (m.getName().equals("loginValid")) {
			return new Object[][] { { "username", "password" }

			};
		} else if (m.getName().equals("loginInvalid")) {
			return new Object[][] { { "td", "1" }, { " ", "" }, { "username", "_" }, { "st**", "password" },
					{ randomString(), randomString() }

			};
		} else if (m.getName().equals("LoginInValid1") || m.getName().equals("LoginInValid2")) {
			return new Object[][] { { "username1", "password1" }, { "@username1", "@password1" },
					{ randomString(), randomString() }

			};

		} else if (m.getName().equals("CreditCardVerfyValid")) {
			return new Object[][] { { "username", "password", "VISA 4485-5368-3381-1879" }

			};
		}
		
		else if (m.getName().equals("CreditCardVerfyInvalid")) {
			return new Object[][] {
				{ "username", "password", "VISA 4485-5368-3381-1111" }

			};
		}
		
		/** If no test method is recognized run with valid data */
		return new Object[][] { { "username1", "password1" }, { "1username", "1password" }, };

	};

	public static String randomString() {
		String characters = "abcdefghijklmnopqrstuvwxyz";

		String randomString = "";
		int lenght = 8;

		Random rand = new Random();

		char[] text = new char[lenght];

		for (int i = 0; i < lenght; i++) {
			text[i] = characters.charAt(rand.nextInt(characters.length()));
		}

		for (int i = 0; i < text.length; i++) {
			randomString += text[i];
		}
		return randomString;
	}

}
