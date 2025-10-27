import java.util.*;

public class ExpressionConverter {

    private int precedence(String op) {
        switch (op) {
            case "+": case "-": return 1;
            case "*": case "/": return 2;
            case "^": return 3;
        }
        return -1;
    }

    // تضيف مسافات حول الرموز
    private String addSpaces(String exp) {
        return exp.replaceAll("([()+\\-*/^])", " $1 ");
    }

    // Infix → Postfix
    public String infixToPostfix(String exp) {
        String spacedExp = addSpaces(exp);
        StringBuilder result = new StringBuilder();
        Stack<String> stack = new Stack<>();

        String[] tokens = spacedExp.trim().split("\\s+");

        for (String token : tokens) {
            if (token.matches("[a-zA-Z_][a-zA-Z0-9_]*") || token.matches("\\d+(\\.\\d+)?")) {
                result.append(token).append(" ");
            } else if (token.equals("(")) {
                stack.push(token);
            } else if (token.equals(")")) {
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    result.append(stack.pop()).append(" ");
                }
                if (!stack.isEmpty()) stack.pop();
            } else {
                while (!stack.isEmpty() && precedence(token) <= precedence(stack.peek())) {
                    result.append(stack.pop()).append(" ");
                }
                stack.push(token);
            }
        }

        while (!stack.isEmpty()) {
            result.append(stack.pop()).append(" ");
        }
        return result.toString().trim();
    }

    // Infix → Prefix
    public String infixToPrefix(String exp) {
        String spacedExp = addSpaces(exp);
        String[] tokens = spacedExp.trim().split("\\s+");

        // اعكس التوكنز
        List<String> list = Arrays.asList(tokens);
        Collections.reverse(list);
        tokens = list.toArray(new String[0]);

        // بدّل الأقواس
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals("(")) tokens[i] = ")";
            else if (tokens[i].equals(")")) tokens[i] = "(";
        }

        // استعمل نفس خوارزمية postfix
        String postfix = infixToPostfix(String.join(" ", tokens));

        // حوّل postfix إلى prefix
        String[] pfTokens = postfix.split("\\s+");
        Stack<String> stack = new Stack<>();

        for (String token : pfTokens) {
            if (token.matches("[a-zA-Z_][a-zA-Z0-9_]*") || token.matches("\\d+(\\.\\d+)?")) {
                stack.push(token);
            } else {
                String op1 = stack.pop();
                String op2 = stack.pop();
                String expr = token + " " + op2 + " " + op1;
                stack.push(expr);
            }
        }

        return stack.pop();
    }
}

