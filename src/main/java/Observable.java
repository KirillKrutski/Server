import java.util.Observer;

public interface Observable {
    void addObserver(Observer o);
    void stopObserver(Observer o);
    void notifyObservers(String message);
}