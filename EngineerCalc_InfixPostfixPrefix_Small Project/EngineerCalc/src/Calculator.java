import java.util.*;

public class Calculator {

    public double evaluatePostfix(String exp, double length, double width, double height, double holesVolume) {
        Stack<Double> stack = new Stack<>();
        String[] tokens = exp.split("\\s+");

        for (String token : tokens) {
            if (token.equals("Length")) {
                stack.push(length);
            } else if (token.equals("Width")) {
                stack.push(width);
            } else if (token.equals("Height")) {
                stack.push(height);
            } else if (token.equals("Holes_Volume")) {
                stack.push(holesVolume);
            } else if (token.matches("\\d+(\\.\\d+)?")) {
                stack.push(Double.parseDouble(token));
            } else {
                double b = stack.pop();
                double a = stack.pop();
                switch (token) {
                    case "+": stack.push(a + b); break;
                    case "-": stack.push(a - b); break;
                    case "*": stack.push(a * b); break;
                    case "/": stack.push(a / b); break;
                    case "^": stack.push(Math.pow(a, b)); break;
                }
            }
        }
        return stack.pop();
    }
}

