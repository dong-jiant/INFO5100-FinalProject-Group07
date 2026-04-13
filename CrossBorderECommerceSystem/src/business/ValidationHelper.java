package business;

import java.util.regex.Pattern;

public class ValidationHelper {
    private static final Pattern EMAIL_REGEX = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 50;
    private static final int MIN_PASSWORD_LENGTH = 6;

    public static String validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) return "Email is required.";
        if (!EMAIL_REGEX.matcher(email.trim()).matches()) return "Invalid email format (e.g. user@example.com).";
        return null;
    }

    public static String validateName(String name) {
        if (name == null || name.trim().isEmpty()) return "Name is required.";
        if (name.trim().length() < MIN_NAME_LENGTH) return "Name must be at least " + MIN_NAME_LENGTH + " characters.";
        if (name.trim().length() > MAX_NAME_LENGTH) return "Name must be at most " + MAX_NAME_LENGTH + " characters.";
        return null;
    }

    public static String validatePassword(String password) {
        if (password == null || password.isEmpty()) return "Password is required.";
        if (password.length() < MIN_PASSWORD_LENGTH) return "Password must be at least " + MIN_PASSWORD_LENGTH + " characters.";
        if (!password.matches(".*[A-Za-z].*")) return "Password must contain at least one letter.";
        if (!password.matches(".*[0-9].*")) return "Password must contain at least one digit.";
        return null;
    }

    public static String validateUsername(String username) {
        if (username == null || username.trim().isEmpty()) return "Username is required.";
        if (username.trim().length() < 3) return "Username must be at least 3 characters.";
        if (!username.matches("^[A-Za-z0-9_]+$")) return "Username can only contain letters, digits, and underscores.";
        return null;
    }
}
