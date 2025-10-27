# Beautiful Music Player - Queue Implementation

## Overview

A desktop music player application built with Java Swing that demonstrates Queue data structure concepts through an interactive playlist management system. The application features a modern pink-themed interface with animated vinyl record visualization.

## Features

- Custom Queue implementation using array-based circular buffer
- FIFO (First In, First Out) playlist management
- Animated vinyl record that spins during playback
- Modern gradient-based UI with pink color scheme
- Keyboard shortcuts for quick access
- Real-time capacity monitoring
- Preview functionality to view next song without removal
- Educational Queue status display

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
├── Main.java              # Entry point with UI initialization
├── MusicPlayerGUI.java    # GUI implementation with animations
├── MusicPlayer.java       # Queue-based music player logic
└── CustomQueue.java       # Custom Queue data structure
```

## Components Description

### 1. Main.java
Application entry point that:
- Sets system look and feel
- Launches GUI on Event Dispatch Thread
- Provides startup confirmation messages

### 2. CustomQueue.java
Custom Queue implementation with the following features:

**Properties:**
- Array-based circular buffer
- Fixed capacity (default: 20 elements)
- Front and rear pointers for efficient operations
- Size tracking

**Operations:**
- `enqueue(String item)`: Add element to rear (O(1))
- `dequeue()`: Remove element from front (O(1))
- `peek()`: View front element without removal (O(1))
- `isEmpty()`: Check if queue is empty
- `isFull()`: Check if queue is at capacity
- `size()`: Get current element count
- `clear()`: Empty the queue
- `toArray()`: Convert to array for display

### 3. MusicPlayer.java
Music player logic implementing Queue behavior:

**Core Functions:**
- `addSong(String songName)`: Add song to playlist (enforces capacity limit)
- `playNext()`: Play and remove next song from queue (FIFO)
- `peekNext()`: Preview next song without removal
- `getCurrentSong()`: Get currently playing song
- `clearPlaylist()`: Remove all songs and stop playback
- `stopCurrentSong()`: Stop current song without affecting queue
- `getQueueStatus()`: Get detailed queue information

**Queue Properties:**
- Maximum capacity: 20 songs
- Strict FIFO ordering
- Rejects additions when full
- Validates input before adding

### 4. MusicPlayerGUI.java
Comprehensive GUI with the following sections:

**Header Section:**
- Application title with gradient background
- Currently playing song display

**Left Section (Controls):**
- Animated vinyl record visualization
- Song input field
- Control buttons (Add, Play, Preview, Stop, Clear, Help)

**Center Section (Playlist):**
- Scrollable list displaying all queued songs
- Alternating row colors
- FIFO order visualization

**Right Section (Status):**
- Queue capacity progress bar
- Currently playing song indicator
- Next song in queue display
- Queue size information

**Footer Section:**
- Application branding with gradient

## User Interface Features

### Color Scheme
- Primary Pink: `#EC4899`
- Light Pink: `#FB7185`
- Accent Pink: `#F43F5E`
- Rose Gold: `#E11D48`
- Deep Rose: `#BE123C`
- Background: `#FDF2F8`
- Cream White: `#FFFBEB`

### Visual Effects
- Gradient backgrounds on header and footer
- Animated vinyl record rotation during playback
- Smooth hover effects on buttons
- Shadow effects on panels
- Rounded corners throughout
- Custom progress bar with gradient fill
- Flash animation for preview function

### Keyboard Shortcuts
- **Enter**: Add song to playlist
- **Space**: Play next song
- **S**: Stop current music
- **Escape**: Clear entire playlist
- **F1**: Preview next song

## Queue Algorithm Details

### Circular Buffer Implementation
The queue uses a circular buffer to maximize space efficiency:

```
Enqueue Operation:
1. Check if queue is full
2. Increment rear pointer (with wrap-around)
3. Insert element at rear position
4. Increment size counter

Dequeue Operation:
1. Check if queue is empty
2. Store element at front position
3. Increment front pointer (with wrap-around)
4. Decrement size counter
5. Return stored element

Circular Wrap-around:
position = (position + 1) % capacity
```

### FIFO Behavior
The application strictly enforces First In, First Out ordering:
- Songs are added to the rear of the queue
- Songs are played from the front of the queue
- Preview shows front element without removal
- No random access or reordering allowed

## Usage Examples

### Adding Songs
1. Type song name in input field
2. Press Enter or click "Add" button
3. Song appears at bottom of playlist
4. Capacity bar updates to show space used

### Playing Songs
1. Click "Play" button or press Space
2. First song in queue begins playing
3. Song is removed from queue
4. Vinyl record begins spinning
5. Next song moves to front

### Preview Function
1. Click "Preview" button or press F1
2. Next song is highlighted in playlist
3. Song remains in queue (not removed)
4. Flash animation indicates preview action

### Queue Full Scenario
1. When 20 songs are added, queue is full
2. Add button remains enabled
3. Attempting to add shows "Queue Full" warning
4. Must play or clear songs to add more

## Educational Features

The application demonstrates Queue concepts:

**FIFO Principle:**
- Songs play in exact order added
- No skipping or reordering possible
- Visual representation of queue order

**Capacity Constraints:**
- Fixed maximum size (20 songs)
- Addition rejected when full
- Progress bar shows capacity usage

**Queue Operations:**
- Enqueue: Adding songs to rear
- Dequeue: Playing removes from front
- Peek: Viewing without removal
- Clear: Emptying entire queue

**Status Information:**
- Real-time size monitoring
- Empty/Full state display
- Next element preview
- Current operation feedback

## Error Handling

The application handles:
- Empty queue operations (play when empty)
- Full queue operations (add when full)
- Invalid input (empty song names)
- Null values
- Whitespace-only input

## Design Patterns

### UI Components
- Custom painted components for gradients
- Fixed button sizes to prevent jittering
- Responsive layout with BorderLayout
- Custom cell renderer for list styling

### Animation System
- Timer-based vinyl rotation (50ms interval)
- Rotation state management
- Smooth visual updates

### Event Handling
- Action listeners for buttons
- Key bindings for shortcuts
- Mouse listeners for hover effects
- Focus listeners for input fields

## Technical Details

- **Language**: Java
- **GUI Framework**: Swing
- **Data Structure**: Circular Array Queue
- **Queue Capacity**: 20 elements
- **Animation FPS**: ~20 (50ms timer)
- **Layout Manager**: BorderLayout with nested layouts

## Practical Applications

- Understanding Queue data structure behavior
- Learning FIFO ordering principles
- Experiencing capacity constraints
- Playlist management simulation
- Educational tool for data structures course

## Performance Characteristics

| Operation | Time Complexity | Space Complexity |
|-----------|----------------|------------------|
| Enqueue   | O(1)           | O(1)             |
| Dequeue   | O(1)           | O(1)             |
| Peek      | O(1)           | O(1)             |
| isEmpty   | O(1)           | O(1)             |
| isFull    | O(1)           | O(1)             |
| toArray   | O(n)           | O(n)             |

## Learning Outcomes

Students will understand:
- Queue data structure implementation
- Circular buffer technique
- FIFO ordering principles
- Capacity management
- GUI event-driven programming
- Custom component painting
- Animation techniques in Java

## License
- Rasha Zreaq
This project is available for educational purposes.
