# Dictionary Application - HashMap Implementation

## Overview

A desktop dictionary application built with Java Swing that demonstrates HashMap data structure implementation using separate chaining for collision resolution. The application features a classic book-themed interface designed for word storage and retrieval.

## Features

- Custom HashMap implementation with separate chaining
- Add, search, and delete word definitions
- Display all dictionary entries
- Classic book-themed user interface
- Real-time word lookup
- Collision handling through linked lists
- Split-pane layout for organized content

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
├── Main.java                  # Entry point
├── DictionaryGUI.java         # User interface implementation
├── DictionaryManager.java     # Business logic layer
├── MyHashMap.java             # Custom HashMap implementation
└── EntryNode.java             # Linked list node for chaining
```

## Components Description

### 1. Main.java
Application entry point that initializes the dictionary GUI.

### 2. MyHashMap.java
Custom HashMap implementation with the following features:

**Properties:**
- Array of buckets (default capacity: 10)
- Separate chaining for collision resolution
- Key-value storage using EntryNode linked lists

**Operations:**
- `put(String key, String value)`: Insert or update entry
- `get(String key)`: Retrieve value by key
- `remove(String key)`: Delete entry by key
- `isEmpty()`: Check if HashMap is empty
- `showAll()`: Display all entries

**Hash Function:**
```java
index = Math.abs(key.hashCode()) % capacity
```

### 3. EntryNode.java
Node structure for linked list chaining:
- `key`: Word to store
- `value`: Definition/meaning
- `next`: Reference to next node in chain

### 4. DictionaryManager.java
Business logic layer that:
- Manages dictionary operations
- Validates input data
- Interfaces between GUI and HashMap
- Handles null and empty string checks

### 5. DictionaryGUI.java
Graphical user interface with the following sections:

**Left Panel (Input Section):**
- Word input field
- Meaning text area
- Control buttons (Add, Search, Delete, Show All)

**Right Panel (Display Section):**
- Results display area
- Dictionary contents viewer

**UI Features:**
- Book-themed color scheme
- Split-pane layout
- Scrollable text areas
- Hover effects on buttons
- Classic serif fonts

## HashMap Algorithm Details

### Hash Function
The application uses Java's built-in `hashCode()` method with modulo operation:
```
1. Calculate hashCode for the key
2. Take absolute value to ensure positive index
3. Apply modulo with capacity to fit array bounds
```

### Collision Resolution: Separate Chaining
When multiple keys hash to the same index:

```
Insertion:
1. Calculate hash index for key
2. Check if key exists in chain (update if found)
3. Create new node and insert at head of chain
4. Link new node to existing chain

Search:
1. Calculate hash index
2. Traverse linked list at that index
3. Compare each key until match found
4. Return value or null if not found

Deletion:
1. Calculate hash index
2. Traverse chain tracking previous node
3. When key found, update links to skip node
4. Return true if deleted, false otherwise
```

### Time Complexity

| Operation | Average Case | Worst Case |
|-----------|-------------|------------|
| Insert    | O(1)        | O(n)       |
| Search    | O(1)        | O(n)       |
| Delete    | O(1)        | O(n)       |
| Display All| O(n)       | O(n)       |

*Average case assumes good hash distribution. Worst case occurs when all keys collide into one bucket.*

## Usage Examples

### Adding a Word
1. Enter word in "Word" field
2. Enter definition in "Meaning" area
3. Click "Add" button
4. Confirmation appears in display area

### Searching for a Word
1. Enter word in "Word" field
2. Click "Search" button
3. Definition appears if word exists
4. "Word not found" message if word doesn't exist

### Deleting a Word
1. Enter word in "Word" field
2. Click "Delete" button
3. Confirmation message or "not found" alert

### Viewing All Words
1. Click "Show All" button
2. All dictionary entries display in format:
   ```
   word1 = definition1
   word2 = definition2
   ...
   ```

## Data Structure Visualization

### HashMap Structure
```
Buckets Array (size 10):
[0] -> null
[1] -> Node("apple", "fruit") -> null
[2] -> Node("car", "vehicle") -> Node("dog", "animal") -> null
[3] -> null
[4] -> Node("book", "reading") -> null
...
[9] -> null
```

### Chaining Example
When "cat" and "bat" hash to index 5:
```
buckets[5] -> ["cat": "animal"] -> ["bat": "animal"] -> null
```

## HashMap Properties

### Capacity
- Fixed at 10 buckets
- Not dynamically resized
- Distribution depends on Java's hashCode() implementation

### Load Factor
- No explicit load factor management
- Relies on separate chaining to handle any number of entries
- Performance degrades as chains grow longer

### Key Characteristics
- Keys are case-sensitive strings
- Values are string definitions
- Duplicate keys update existing values
- Null keys/values are rejected

## Color Scheme

The interface uses a classic book theme:
- **Book Background**: `#F5EBD7` (Beige)
- **Paper Color**: `#FFF8E6` (Cream)
- **Ink Color**: `#654321` (Brown)
- **Border Color**: `#8B5A2B` (Tan)
- **Title Color**: `#553311` (Dark Brown)

## Design Features

### UI Components
- Split-pane divider for resizable sections
- Scrollable text areas
- Bordered panels with padding
- Custom button styling
- Serif fonts for classic appearance

### User Experience
- Hover effects on buttons
- Immediate feedback on operations
- Clear success/error messages
- Input validation
- Focus on usability

## Educational Purpose

This project demonstrates:

**HashMap Concepts:**
- Hash function implementation
- Collision resolution strategies
- Separate chaining technique
- Key-value pair storage
- O(1) average-case operations

**Data Structures:**
- Array-based hash table
- Linked list for chaining
- Node-based storage

**Software Engineering:**
- Separation of concerns (GUI, logic, data)
- Input validation
- Error handling
- Clean code organization

## Error Handling

The application handles:
- Empty word or meaning fields
- Null input values
- Search for non-existent words
- Delete operations on missing keys
- Empty dictionary display

## Limitations

### Current Implementation
- Fixed capacity (10 buckets)
- No dynamic resizing
- No load factor optimization
- Basic hash function (relies on Java's hashCode)
- No persistence (data lost on exit)

### Potential Improvements
- Dynamic resizing when load factor exceeds threshold
- Better hash function for string distribution
- File-based persistence
- Import/export functionality
- Advanced search features (partial match, wildcards)

## HashMap vs Other Data Structures

| Feature | HashMap | Array | Linked List |
|---------|---------|-------|-------------|
| Search Time | O(1) avg | O(n) | O(n) |
| Insert Time | O(1) avg | O(1) end | O(1) head |
| Delete Time | O(1) avg | O(n) | O(n) |
| Ordered | No | Yes | Yes |
| Direct Access | By key | By index | Sequential |

## Practical Applications

- Word definition storage and retrieval
- Language translation dictionaries
- Terminology databases
- Quick lookup systems
- Educational tool for HashMap concepts

## Technical Details

- **Language**: Java
- **GUI Framework**: Swing
- **Data Structure**: HashMap with Separate Chaining
- **Collision Resolution**: Linked List
- **Default Capacity**: 10 buckets
- **Hash Function**: Java hashCode() % capacity

## Learning Outcomes

Students will understand:
- HashMap data structure implementation
- Hash function design and usage
- Collision resolution techniques
- Separate chaining with linked lists
- Average vs worst-case time complexity
- Trade-offs between different data structures
- GUI application architecture

## License
Rasha Zreaq
This project is available for educational purposes.
