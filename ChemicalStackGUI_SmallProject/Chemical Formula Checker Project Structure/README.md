# Chemical Formula Bracket Checker

## Overview

This project is a Java-based application that validates the correctness of brackets in chemical formulas using a Stack data structure. The application provides a graphical user interface (GUI) built with Java Swing.

## Features

- Validates balanced brackets in chemical formulas
- Supports three types of brackets: `()`, `[]`, `{}`
- Graphical user interface for easy interaction
- Quick validation with instant results
- Custom Stack implementation
- Error detection and reporting

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
├── Main.java                   # Entry point
├── ChemicalFormulaGUI.java     # GUI implementation
├── FormulaChecker.java         # Validation logic
├── CharStack.java              # Custom Stack implementation
└── CharNode.java               # Node for linked list structure
```

## Components Description

### 1. Main.java
Entry point of the application. Initializes and launches the GUI using SwingUtilities.

### 2. ChemicalFormulaGUI.java
Implements the graphical user interface with the following features:
- Text field for formula input
- Quick check button for validation
- Clear button to reset input
- Results display area
- Example formulas for testing
- Status bar for feedback

### 3. FormulaChecker.java
Contains the core validation logic:
- `isValid(String formula)`: Returns boolean validation result
- `validateWithSteps(String formula)`: Returns detailed step-by-step validation
- `matches(char open, char close)`: Checks bracket pair matching

### 4. CharStack.java
Custom Stack implementation with operations:
- `push(char item)`: Adds element to stack
- `pop()`: Removes and returns top element
- `peek()`: Returns top element without removal
- `isEmpty()`: Checks if stack is empty
- `size()`: Returns number of elements
- `clear()`: Empties the stack

### 5. CharNode.java
Basic node structure for linked list implementation containing a character and reference to next node.

## Algorithm

The validation algorithm works as follows:

1. Iterate through each character in the formula
2. If opening bracket `(`, `[`, or `{` is found:
   - Push it onto the stack
3. If closing bracket `)`, `]`, or `}` is found:
   - Check if stack is empty (error if true)
   - Pop the top element from stack
   - Verify bracket type matches (error if false)
4. After processing all characters:
   - Valid if stack is empty
   - Invalid if stack contains elements

## Examples

### Valid Formulas
- `Ca(OH)2` - Calcium hydroxide
- `Al2[SO4]3` - Aluminum sulfate
- `{[Ca(OH)2]3}2` - Nested brackets

### Invalid Formulas
- `H2SO4(aq` - Unclosed bracket
- `Ca(OH]2` - Mismatched bracket types
- `)Ca(OH2` - Closing bracket before opening

## Usage

1. Launch the application
2. Enter a chemical formula in the input field
3. Click "Quick Check" or press Enter
4. View validation results in the results area
5. Use example buttons for quick testing

## Technical Details

- **Language**: Java
- **GUI Framework**: Swing
- **Data Structure**: Stack (Array-based implementation)
- **Default Stack Capacity**: 100 elements
- **Supported Brackets**: Parentheses, Square brackets, Curly braces

## Error Handling

The application handles the following error cases:
- Empty stack when closing bracket is encountered
- Mismatched bracket types
- Unclosed brackets remaining in stack
- Stack overflow (capacity exceeded)

## Educational Purpose

This project demonstrates:
- Stack data structure implementation and usage
- Object-Oriented Programming principles
- GUI development with Java Swing
- Algorithm design for bracket matching problems
- Exception handling in Java

## License
- Rasha Zreaq
This project is available for educational purposes.
