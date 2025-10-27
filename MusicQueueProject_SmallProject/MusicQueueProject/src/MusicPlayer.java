/**
 * Music Player using Custom Queue implementation
 * Demonstrates proper Queue behavior (FIFO - First In, First Out)
 */
public class MusicPlayer {

    private CustomQueue playlist;
    private String currentSong;
    private static final int MAX_SONGS = 20;

    public MusicPlayer() {
        playlist = new CustomQueue(MAX_SONGS);
        currentSong = null;
    }

    /**
     * Add song to playlist - follows strict Queue rules
     * @param songName name of the song to add
     * @return true if added successfully, false if queue is full or invalid input
     */
    public boolean addSong(String songName) {
        if (songName == null || songName.trim().isEmpty()) {
            return false;
        }

        // Strict Queue behavior - reject if full
        return playlist.enqueue(songName.trim());
    }

    /**
     * Play next song in queue (FIFO - First In, First Out)
     * @return name of the song now playing, null if queue was empty
     */
    public String playNext() {
        if (playlist.isEmpty()) {
            currentSong = null;
            return null;
        }

        currentSong = playlist.dequeue();
        return currentSong;
    }

    /**
     * Peek at next song without removing it from queue
     * @return name of next song, null if queue is empty
     */
    public String peekNext() {
        return playlist.peek();
    }

    /**
     * Get currently playing song
     * @return name of current song, null if nothing is playing
     */
    public String getCurrentSong() {
        return currentSong;
    }

    /**
     * Check if playlist queue is empty
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return playlist.isEmpty();
    }

    /**
     * Check if playlist queue is full
     * @return true if full, false otherwise
     */
    public boolean isFull() {
        return playlist.isFull();
    }

    /**
     * Get current number of songs in queue
     * @return number of songs
     */
    public int size() {
        return playlist.size();
    }

    /**
     * Get maximum capacity of queue
     * @return maximum capacity
     */
    public int capacity() {
        return playlist.capacity();
    }

    /**
     * Clear entire queue and stop current song
     */
    public void clearPlaylist() {
        playlist.clear();
        currentSong = null;
    }

    /**
     * Get array representation of current queue
     * Shows queue in FIFO order (first element will be played first)
     * @return array of song names in queue order
     */
    public String[] getPlaylistArray() {
        return playlist.toArray();
    }

    /**
     * Stop current song without affecting queue
     */
    public void stopCurrentSong() {
        currentSong = null;
    }

    /**
     * Get detailed queue status for learning purposes
     * @return formatted status showing queue properties
     */
    public String getQueueStatus() {
        StringBuilder status = new StringBuilder();
        status.append("Queue Status:\n");
        status.append("- Size: ").append(size()).append("/").append(capacity()).append("\n");
        status.append("- Empty: ").append(isEmpty()).append("\n");
        status.append("- Full: ").append(isFull()).append("\n");

        if (currentSong != null) {
            status.append("- Currently Playing: ").append(currentSong).append("\n");
        }

        if (!isEmpty()) {
            status.append("- Next in Queue: ").append(peekNext()).append("\n");
        }

        return status.toString();
    }
}