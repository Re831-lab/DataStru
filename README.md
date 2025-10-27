# Data Structures Course Projects - Spring 2025

## Overview

This repository contains educational projects and practice implementations developed during the Data Structures course for Spring 2025. The repository includes both complete GUI-based projects and foundational practice exercises that demonstrate fundamental data structure concepts.

## Repository Structure

```
DataStru/
│
├── ChemicalStackGUI_SmallProject/        # Stack-based bracket validation
├── EngineerCalc_InfixPostfixPrefix_Small Project/  # Expression evaluation
├── MusicQueueProject_SmallProject/       # Queue implementation
├── SmartDictionaryProject/               # HashMap with GUI
├── HashTable/                            # HashMap practice files
├── PolynomialCalculatorProject/          # Polynomial operations
├── Queue/                                # Queue practice files
└── Stack/                                # Stack practice files
```

---

## Complete GUI Projects

### 1. Chemical Formula Bracket Checker
**Location**: `ChemicalStackGUI_SmallProject/`  
**Data Structure**: Stack

A professional GUI application that validates balanced brackets in chemical formulas using Stack data structure.

**Key Features:**
- Validates three bracket types: `()`, `[]`, `{}`
- Custom Stack implementation (array-based)
- Step-by-step validation display
- Purple-themed professional interface
- Real-time validation feedback
- Example formulas for testing

**Technical Implementation:**
- Stack operations: push, pop, peek, isEmpty
- Bracket matching algorithm
- LIFO (Last In, First Out) demonstration
- Error detection and reporting

**Files:**
- `CharStack.java` - Custom Stack implementation
- `FormulaChecker.java` - Validation logic
- `ChemicalFormulaGUI.java` - User interface
- `CharNode.java` - Node structure
- `Main.java` - Entry point

**Example Usage:**
- ✓ Valid: `Ca(OH)2`, `Al2[SO4]3`, `{[Ca(OH)2]3}2`
- ✗ Invalid: `H2SO4(aq`, `Ca(OH]2`, `)Ca(OH2`

---

### 2. EngineerCalc - Expression Calculator
**Location**: `EngineerCalc_InfixPostfixPrefix_Small Project/`  
**Data Structure**: Stack

Engineering calculator that converts and evaluates mathematical expressions for concrete volume calculations.

**Key Features:**
- Infix to Postfix conversion
- Infix to Prefix conversion
- Postfix expression evaluation
- Construction-specific variables (Length, Width, Height, Holes_Volume)
- Dark-themed modern interface with orange accents
- Real-time calculation with multiple notation displays

**Technical Implementation:**
- Shunting-yard algorithm for conversion
- Stack-based expression evaluation
- Operator precedence handling (^, *, /, +, -)
- Token parsing with regex
- Bilingual interface (Arabic/English)

**Files:**
- `ExpressionConverter.java` - Notation conversion logic
- `Calculator.java` - Postfix evaluator
- `GUI.java` - Dark-themed interface
- `Main.java` - Entry point

**Supported Operations:**
- Addition (`+`), Subtraction (`-`)
- Multiplication (`*`), Division (`/`)
- Exponentiation (`^`)
- Parentheses for grouping

**Example:**
```
Input: Length * Width * Height - Holes_Volume
Variables: Length=10, Width=5, Height=3, Holes_Volume=2
Postfix: Length Width * Height * Holes_Volume -
Prefix: - * * Length Width Height Holes_Volume
Result: 148.0000
```

---

### 3. Beautiful Music Player
**Location**: `MusicQueueProject_SmallProject/`  
**Data Structure**: Queue

An interactive music playlist manager demonstrating Queue (FIFO) principles with animated vinyl record visualization.

**Key Features:**
- Custom Queue implementation with circular buffer
- FIFO playlist management (maximum 20 songs)
- Animated spinning vinyl record
- Pink gradient-themed beautiful interface
- Keyboard shortcuts for all operations
- Real-time capacity monitoring with progress bar
- Preview next song without removal
- Visual feedback with flash animations

**Technical Implementation:**
- Circular array implementation
- Queue operations: enqueue, dequeue, peek, clear
- Fixed capacity enforcement
- FIFO ordering demonstration
- Animation with Java Timer (50ms refresh)
- Custom painted components with gradients

**Files:**
- `CustomQueue.java` - Circular queue implementation
- `MusicPlayer.java` - Queue-based player logic
- `MusicPlayerGUI.java` - Advanced GUI with animations
- `Main.java` - Entry point

**Queue Operations:**
- **Enqueue**: Add song to rear of queue
- **Dequeue**: Play and remove from front
- **Peek**: Preview next song without removal
- **Clear**: Empty entire queue

**Keyboard Shortcuts:**
- Enter → Add song
- Space → Play next
- S → Stop current
- Escape → Clear playlist
- F1 → Preview next

---

### 4. Smart Dictionary Application
**Location**: `SmartDictionaryProject/`  
**Data Structure**: HashMap

A word dictionary with custom HashMap implementation using separate chaining for collision resolution.

**Key Features:**
- Custom HashMap with separate chaining
- Add, search, and delete word definitions
- Display all dictionary entries
- Classic book-themed interface
- Real-time word lookup
- Collision handling with linked lists
- Split-pane layout for organized content

**Technical Implementation:**
- Hash function: `Math.abs(key.hashCode()) % capacity`
- Separate chaining collision resolution
- O(1) average-case operations
- Key-value pair storage with validation
- Linked list for bucket chaining

**Files:**
- `MyHashMap.java` - Custom HashMap implementation
- `EntryNode.java` - Linked list node for chaining
- `DictionaryManager.java` - Business logic layer
- `DictionaryGUI.java` - Book-themed interface
- `Main.java` - Entry point

**HashMap Details:**
- Capacity: 10 buckets
- Collision resolution: Linked list chaining
- Operations: put, get, remove, isEmpty, showAll
- Average time complexity: O(1) for basic operations

---

### 5. Polynomial Calculator Project
**Location**: `PolynomialCalculatorProject/`  
**Data Structure**: Linked List / Array

Application for polynomial operations and calculations.

**Expected Features:**
- Polynomial representation
- Addition and subtraction operations
- Multiplication of polynomials
- Evaluation at specific values
- Display in standard mathematical notation

---

## Practice Files and Exercises

### Stack Practice
**Location**: `Stack/`

Fundamental Stack implementations and exercises for understanding LIFO behavior.

**Expected Content:**
- Basic Stack implementation
- Stack using arrays
- Stack using linked lists
- Common Stack problems:
  - Balanced parentheses
  - Reverse string
  - Infix to Postfix conversion
  - Expression evaluation
  - Undo/Redo operations

**Learning Objectives:**
- Understanding LIFO principle
- Stack overflow and underflow
- Time complexity: O(1) for push/pop
- Space complexity: O(n)

---

### Queue Practice
**Location**: `Queue/`

Fundamental Queue implementations and exercises for understanding FIFO behavior.

**Expected Content:**
- Basic Queue implementation
- Linear queue using arrays
- Circular queue implementation
- Queue using linked lists
- Common Queue problems:
  - Job scheduling simulation
  - Breadth-first traversal
  - Buffer management

**Learning Objectives:**
- Understanding FIFO principle
- Circular buffer technique
- Front and rear pointer management
- Time complexity: O(1) for enqueue/dequeue
- Space complexity: O(n)

---

### HashTable Practice
**Location**: `HashTable/`

HashMap/HashTable implementations and collision resolution techniques.

**Expected Content:**
- Hash function design
- Collision resolution methods:
  - Separate chaining
  - Linear probing
  - Quadratic probing
- Load factor management
- Rehashing techniques

**Learning Objectives:**
- Hash function properties
- Collision handling strategies
- Average vs worst-case analysis
- Trade-offs between different methods
- Time complexity: O(1) average, O(n) worst case

---

## Data Structures Summary

### Stack (LIFO - Last In, First Out)
- **Projects**: Chemical Formula Checker, EngineerCalc
- **Practice**: Stack folder
- **Implementation**: Array-based and Linked List
- **Time Complexity**: O(1) for push, pop, peek
- **Space Complexity**: O(n)
- **Applications**: Expression evaluation, bracket matching, undo operations

### Queue (FIFO - First In, First Out)
- **Project**: Music Player
- **Practice**: Queue folder
- **Implementation**: Circular array and Linked List
- **Time Complexity**: O(1) for enqueue, dequeue, peek
- **Space Complexity**: O(n)
- **Applications**: Playlist management, job scheduling, BFS

### HashMap (Hash Table)
- **Project**: Smart Dictionary
- **Practice**: HashTable folder
- **Implementation**: Array with separate chaining
- **Time Complexity**: O(1) average, O(n) worst case
- **Space Complexity**: O(n + m) where m is bucket count
- **Applications**: Dictionary, caching, symbol tables

### Linked List
- **Usage**: Stack nodes, Queue implementation, HashMap chaining, Polynomials
- **Implementation**: Singly linked
- **Time Complexity**: O(1) insert at head, O(n) search
- **Space Complexity**: O(n)
- **Applications**: Dynamic data storage, collision resolution

---

## Common Features Across GUI Projects

### User Interface Design
- Modern, themed GUI designs
- Responsive layouts with proper spacing
- Custom styled components
- Hover effects and smooth animations
- Gradient backgrounds
- Professional color schemes (Purple, Dark, Pink, Book themes)

### Code Architecture
- Clean separation of concerns
- MVC-like pattern (GUI, Logic, Data)
- Comprehensive input validation
- Robust error handling
- Detailed comments and documentation
- Reusable components

### Educational Value
- Clear demonstration of data structure concepts
- Step-by-step operation visualization
- Real-world application examples
- Performance characteristic awareness
- Algorithm implementation details

---

## Technologies Used

- **Language**: Java (JDK 8+)
- **GUI Framework**: Java Swing
- **Layout Managers**: BorderLayout, GridLayout, FlowLayout, BoxLayout
- **Graphics**: Graphics2D with anti-aliasing and gradients
- **Animation**: javax.swing.Timer
- **Design Patterns**: MVC, Observer (event listeners)

---

## Installation and Usage

### Prerequisites
```bash
# Verify Java installation
java -version
javac -version
```

### Running GUI Projects
Navigate to any project directory:
```bash
cd ChemicalStackGUI_SmallProject
javac *.java
java Main
```

### Running Practice Files
Navigate to practice folder:
```bash
cd Stack
javac *.java
java Main  # or specific class name
```

---

## Project Complexity Comparison

| Project | Data Structure | GUI | Lines of Code | Complexity | Educational Value |
|---------|----------------|-----|---------------|------------|-------------------|
| Chemical Formula | Stack | ✓ | ~400 | Medium | High |
| EngineerCalc | Stack | ✓ | ~500 | Medium-High | High |
| Music Player | Queue | ✓ | ~800 | High | Very High |
| Dictionary | HashMap | ✓ | ~350 | Medium | High |
| Polynomial Calc | List/Array | ✓ | ~400 | Medium | Medium-High |
| Stack Practice | Stack | ✗ | ~200 | Low-Medium | Medium |
| Queue Practice | Queue | ✗ | ~200 | Low-Medium | Medium |
| HashTable Practice | HashMap | ✗ | ~250 | Medium | Medium |

---

## Learning Path Recommendation

### Beginner Level
1. **Stack Practice** - Start with basic Stack operations
2. **Queue Practice** - Understand FIFO behavior
3. **Chemical Formula Checker** - First GUI project with Stack

### Intermediate Level
4. **HashTable Practice** - Learn collision resolution
5. **EngineerCalc** - Advanced Stack usage (expression conversion)
6. **Dictionary Project** - HashMap with GUI

### Advanced Level
7. **Music Player** - Complex GUI with animations
8. **Polynomial Calculator** - Advanced data structure usage

---

## Performance Analysis

### Time Complexity Comparison

| Operation | Stack | Queue | HashMap | Linked List |
|-----------|-------|-------|---------|-------------|
| Insert | O(1) | O(1) | O(1) avg | O(1) head |
| Delete | O(1) | O(1) | O(1) avg | O(1) head |
| Search | O(n) | O(n) | O(1) avg | O(n) |
| Access | O(n) | O(n) | O(1) avg | O(n) |
| Space | O(n) | O(n) | O(n+m) | O(n) |

---

## Key Learning Outcomes

### Stack Projects
- LIFO ordering and behavior
- Expression evaluation techniques
- Bracket/parentheses matching algorithms
- Operator precedence handling
- Notation conversions (Infix/Postfix/Prefix)

### Queue Projects
- FIFO ordering and behavior
- Circular buffer implementation
- Capacity management and constraints
- Real-time system simulation
- Animation integration techniques

### HashMap Projects
- Hash function design and properties
- Collision resolution strategies
- Load factor and performance
- Key-value storage patterns
- Trade-offs in data structure selection

### General Skills
- GUI development with Swing
- Event-driven programming
- Custom component painting
- Algorithm implementation
- Code organization and architecture

---



## Course Information

**Course**: Data Structures  
**Term**: Spring 2025  
**Focus**: Practical implementation of fundamental data structures  
**Approach**: Hands-on learning through GUI projects and practice exercises  
**Goal**: Understanding both theoretical concepts and real-world applications

---

## Contributing

These projects are educational implementations. Contributions welcome for:
- Bug fixes and improvements
- Additional features
- Documentation enhancements
- New practice exercises
- Performance optimizations
- Code refactoring

Please open issues or submit pull requests.

---

## License
Rasha Zreaq
This repository is available for educational purposes.

---

## Acknowledgments

All projects developed as part of Data Structures course curriculum, demonstrating theoretical concepts through practical applications with modern, professional user interfaces.
