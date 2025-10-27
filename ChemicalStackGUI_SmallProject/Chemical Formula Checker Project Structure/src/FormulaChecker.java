// FormulaChecker class implementation
/**
 * Chemical Formula Checker
 */
public class FormulaChecker {

    public static boolean isValid(String formula) {
        CharStack stack = new CharStack();

        for (int i = 0; i < formula.length(); i++) {
            char c = formula.charAt(i);

            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            }
            else if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty()) {
                    return false;
                }
                char last = stack.pop();
                if (!matches(last, c)) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static String validateWithSteps(String formula) {
        CharStack stack = new CharStack();
        StringBuilder result = new StringBuilder();

        result.append(" Checking Formula: ").append(formula).append("\n");
        result.append("================================\n\n");

        for (int i = 0; i < formula.length(); i++) {
            char c = formula.charAt(i);

            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
                result.append("Step ").append(i + 1).append(": Found '").append(c)
                        .append("' → PUSH to stack\n");
                result.append("Stack: ").append(stack.toString()).append(" (Size: ")
                        .append(stack.size()).append(")\n\n");
            }
            else if (c == ')' || c == ']' || c == '}') {
                result.append("Step ").append(i + 1).append(": Found '").append(c)
                        .append("' → Check for match\n");

                if (stack.isEmpty()) {
                    result.append(" ERROR: Stack is empty! No matching opening bracket\n");
                    result.append("\n RESULT: Formula is INVALID!\n");
                    return result.toString();
                }

                char last = stack.pop();
                if (matches(last, c)) {
                    result.append(" Matches with '").append(last).append("' → POP from stack\n");
                    result.append("Stack: ").append(stack.toString()).append(" (Size: ")
                            .append(stack.size()).append(")\n\n");
                } else {
                    result.append(" ERROR: '").append(c).append("' does NOT match '")
                            .append(last).append("'\n");
                    result.append("\n RESULT: Formula is INVALID!\n");
                    return result.toString();
                }
            }
        }

        result.append("================================\n");
        if (stack.isEmpty()) {
            result.append(" RESULT: Formula is VALID! All brackets are balanced.\n");
        } else {
            result.append(" RESULT: Formula is INVALID! Unclosed brackets: ")
                    .append(stack.toString()).append("\n");
        }

        return result.toString();
    }

    private static boolean matches(char open, char close) {
        return (open == '(' && close == ')') ||
                (open == '[' && close == ']') ||
                (open == '{' && close == '}');
    }
}