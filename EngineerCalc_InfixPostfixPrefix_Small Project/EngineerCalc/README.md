# EngineerCalc - Concrete Volume Calculator

## Overview

EngineerCalc is a Java-based desktop application designed to calculate concrete volumes for construction projects. The application converts mathematical expressions between different notations (Infix, Postfix, Prefix) and evaluates them using user-defined variables such as length, width, height, and holes volume.

## Features

- Mathematical expression conversion (Infix to Postfix and Prefix)
- Postfix expression evaluation
- Support for construction-specific variables
- Dark-themed graphical user interface
- Real-time calculation results
- Expression validation and error handling
- Bilingual interface (Arabic/English)

## System Requirements

- Java Development Kit (JDK) 8 or higher
- Any operating system (Windows, macOS, Linux)

## Installation and Execution

### Compilation
```bash
javac *.java
```

### Execution
```bash
java Main
```

## Project Structure

```
├── Main.java                      # Entry point
├── GUI.java                       # User interface implementation
├── ExpressionConverter.java       # Expression notation converter
└── Calculator.java                # Postfix expression evaluator
```

## Components Description

### 1. Main.java
Entry point of the application that initializes the GUI.

### 2. GUI.java
Implements the graphical user interface with the following features:
- Input field for mathematical expressions (Infix notation)
- Input fields for construction variables (Length, Width, Height, Holes_Volume)
- Result display area showing all conversions and final calculation
- Three action buttons: Calculate, Clear, and Example
- Dark theme with orange accent colors

### 3. ExpressionConverter.java
Handles expression notation conversions:
- `infixToPostfix(String exp)`: Converts infix to postfix notation
- `infixToPrefix(String exp)`: Converts infix to prefix notation
- `precedence(String op)`: Determines operator precedence
- `addSpaces(String exp)`: Formats expressions for parsing

Supported operators: `+`, `-`, `*`, `/`, `^`

### 4. Calculator.java
Evaluates postfix expressions:
- `evaluatePostfix(String exp, ...)`: Calculates the result using Stack-based algorithm
- Supports numerical values and variable substitution
- Handles all basic arithmetic operations and exponentiation

## Supported Variables

The application recognizes the following construction-specific variables:
- `Length`: Length dimension
- `Width`: Width dimension
- `Height`: Height dimension
- `Holes_Volume`: Volume of holes/cavities to subtract

## Algorithm Details

### Infix to Postfix Conversion
1. Add spaces around operators for tokenization
2. Process each token:
   - Operands (numbers/variables) → Output directly
   - Opening parenthesis → Push to stack
   - Closing parenthesis → Pop until matching opening parenthesis
   - Operators → Pop higher/equal precedence operators, then push
3. Pop remaining operators from stack

### Infix to Prefix Conversion
1. Reverse the infix expression
2. Swap parentheses
3. Convert to postfix
4. Build prefix by processing postfix tokens with stack
5. Return final result

### Postfix Evaluation
1. Process tokens left to right
2. Operands → Push to stack
3. Operators → Pop two operands, apply operation, push result
4. Final stack value is the result

## Usage Example

### Input
- **Expression**: `Length * Width * Height - Holes_Volume`
- **Length**: 10
- **Width**: 5
- **Height**: 3
- **Holes_Volume**: 2

### Output
- **Postfix**: `Length Width * Height * Holes_Volume -`
- **Prefix**: `- * * Length Width Height Holes_Volume`
- **Result**: 148.0000

### Calculation Steps
```
10 × 5 × 3 - 2 = 150 - 2 = 148
```

## User Interface Components

### Input Section
- Mathematical expression field (Infix notation)
- Four variable input fields with labels

### Control Buttons
- **Calculate**: Processes expression and displays results
- **Clear**: Resets all fields
- **Example**: Loads sample calculation

### Results Section
Displays:
- Original expression (Infix)
- Converted expression (Postfix)
- Converted expression (Prefix)
- Variable values used
- Final calculated result

## Error Handling

The application handles:
- Empty expression field
- Empty variable fields
- Invalid number format
- Expression syntax errors
- Division by zero
- Mismatched parentheses

## Operator Precedence

| Operator | Precedence | Associativity |
|----------|------------|---------------|
| `^`      | 3          | Right         |
| `*`, `/` | 2          | Left          |
| `+`, `-` | 1          | Left          |

## Design Features

### Color Scheme
- Background: Dark Gray (#2D2D30)
- Secondary Background: Darker Gray (#232326)
- Accent Color: Orange (#FF8C00)
- Text Color: White and Light Gray

### UI Elements
- Custom styled text fields
- Hover effects on buttons
- Scrollable result area
- Error message display with timeout
- Monospace font for expressions

## Educational Purpose

This project demonstrates:
- Stack data structure implementation
- Expression notation conversions
- Operator precedence handling
- GUI development with Java Swing
- Event-driven programming
- Input validation and error handling
- Token parsing and processing

## Practical Applications

- Construction volume calculations
- Concrete quantity estimation
- Material requirement planning
- Educational tool for expression conversion
- Stack algorithm demonstration

## Technical Details

- **Language**: Java
- **GUI Framework**: Swing
- **Data Structure**: Stack (java.util.Stack)
- **Parsing Method**: Token-based with regex
- **Number Format**: Double precision floating-point

## Example Expressions

### Valid Expressions
- `Length * Width * Height`
- `(Length + Width) * Height / 2`
- `Length * Width * Height - Holes_Volume`
- `Length ^ 2 + Width ^ 2`

### Invalid Expressions
- `Length * * Width` (consecutive operators)
- `(Length + Width` (unclosed parenthesis)
- `Length Width` (missing operator)

## License
- Rasha Zreaq
This project is available for educational and commercial purposes.
